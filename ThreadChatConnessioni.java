import java.lang.Thread;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.hsqldb.jdbc.JDBCDriver;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class ThreadChatConnessioni implements Runnable{
    private List<GestioneThread> gestione = new ArrayList<GestioneThread>();
    List<String> OtherMessages = new ArrayList<String>(); 
    private Socket client=null;
    private BufferedReader input=null;
    Thread me;
    private String name;
    private PrintWriter out=null;
    private Server S; 
    private int i;
    private String Friend = "";
    private int j=0;
    Connection con;
    Statement stmt;
    ResultSet res;
    boolean flag=false;
    public ThreadChatConnessioni(Socket client,Server S, int i){
        
        this.client=client;
        this.S=S;
        this.i=i;
        try{
            this.input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            this.out=new PrintWriter(this.client.getOutputStream(),true);
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            
        }
        catch(Exception e){
            out.println("Errore lettura");
        }
        me=new Thread(this);
        me.start();
    }   
    public void run(){
        while(true){
            try{
                String mex=null;
                
                while((mex = input.readLine())==null){
                
                }
                String[] mex1 = mex.split(":");
                if(mex1[0].equals("SetNick")){
                    name = mex1[1];
                    System.out.println(mex);
                    try{
                        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat", "SA", "");
                        stmt=con.createStatement();
                        stmt.executeUpdate("UPDATE ACCOUNTS SET IND="+this.i+" WHERE NICK='"+this.name+"';");
                    }catch(Exception e){
                        e.printStackTrace(System.out);
                    }
                }
                else if(mex1[0].equals("CH_Chat")){
                    Friend=mex1[1];
                    j=gestione.size();
                    gestione.add(S.getServer(Integer.parseInt(mex1[2])));
                    for(int i=0;i<OtherMessages.size();i++){
                        String[] temp=OtherMessages.get(i).split(":");
                        if(temp[0].equals(Friend)){
                            spedisciMessaggio(OtherMessages.get(i));
                        }
                    }
                }
                else if(mex1[0].equals("New_Chat")){
                    Friend = mex1[1];
                    System.out.println(Friend);
                        try{
                           Class.forName("org.hsqldb.jdbc.JDBCDriver");
                           con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat","SA","");
                           stmt=con.createStatement();
                           ResultSet res;
                           try{
                               stmt=con.createStatement();
                               res=stmt.executeQuery("SELECT ID FROM CONNECTIONS WHERE (NICK = '"+name+"' AND NICK1 = '"+ mex1[1]+"') OR (NICK1 = '"+name+"' AND NICK = '"+ mex1[1]+"')");
                               if(res.next()){
                                   do{
                                       j=gestione.size();
                                       addGT(S.getServer(res.getInt("ID")));
                                    }while(res.next());
                                   for(int i=0;i<OtherMessages.size();i++){
                                       String[] temp=OtherMessages.get(i).split(":");
                                        if(temp[0].equals(Friend)){
                                            spedisciMessaggio(OtherMessages.get(i));
                                       }
                                   }
                             }
                               else{
                                    stmt=con.createStatement();
                                    res=stmt.executeQuery("SELECT IND FROM ACCOUNTS WHERE Nick='"+ mex1[1]+"';");
                                    while(res.next()){
                                        j=gestione.size();
                                        gestione.add(S.CreateConnection(this,res.getInt("IND")));
                                    }
                                    for(int i=0;i<OtherMessages.size();i++){
                                        String[] temp=OtherMessages.get(i).split(":");
                                        if(temp[0].equals(Friend)){
                                            spedisciMessaggio(OtherMessages.get(i));
                                        }
                                        
                                    }
                                }
                            }catch(Exception es){es.printStackTrace();}
                       
                            
                    }catch(Exception ex){ex.printStackTrace(System.out);}
                }
                else if(mex1[0].equals("SetFriend")){
                    Friend="";
                }
                else {
                    if(!flag){
                        this.out=new PrintWriter(this.client.getOutputStream(),true);
                        flag=true;
                    }
                    gestione.get(j).SpedisciMessaggio(mex);
                }
            }
            catch(Exception e){
                e.printStackTrace(System.out);
                out.println("Errore spedizione messaggioou");
            }
        }
    }
    public void spedisciMessaggio(String messaggio){
        String[] mex1 = messaggio.split(":");
        if(Friend.equals(mex1[0])){
            try{
                out.println(messaggio);
                System.out.println("Prova:"+messaggio);
                //gestione.SpedisciMessaggio(messaggio);
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        else{
            if(!mex1[0].equals(name))
                out.println("Notify:"+messaggio);
            /*JOptionPane.showMessageDialog(null, "1 new message from "+mex1[0]);
            OtherMessages.add(messaggio);*/
        }
    }
    public BufferedReader getInput(){
        return input;
    }
    public PrintWriter getPrint(){
        return out;
    }
    public void addGT(GestioneThread gs){
        j=gestione.size();
        gestione.add(gs);
    }
    public String getName(){
        return name;
    }
}