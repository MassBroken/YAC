import java.net.ServerSocket;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.Driver;
import java.net.Socket;
import java.lang.Thread;
import java.sql.DriverManager;
import java.awt.event.*;
import org.hsqldb.jdbc.JDBCDriver;
import javax.swing.*;
public class Server implements Runnable{
    Connection con = null;
    //ResultSet Res = null;
    int result =        0;
    Statement stmt = null;
    ServerSocket SS = null;
    Thread m = null;
    List<GestioneThread> ThreadS = new ArrayList<GestioneThread>();
    List<ThreadChatConnessioni> connessioni=new ArrayList<ThreadChatConnessioni>();
    public void run(){
        boolean flag = true;
        try{
            SS = new ServerSocket(3250);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        if(flag){
            try{
                while(true){
                   Socket tempo=null;
                   tempo = SS.accept();
                   if(tempo.isConnected()){
                       System.out.println("connesso");
                       ThreadChatConnessioni temporaneo = new ThreadChatConnessioni(tempo, this, connessioni.size());
                       connessioni.add(temporaneo);
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
    public Server(){
        m = new Thread(this);
        m.start();
        JFrame js  = new JFrame("Chiudi");
        js.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try{
                    Class.forName("org.hsqldb.jdbc.JDBCDriver");
                    con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat","SA","");
                    stmt=con.createStatement();
                    stmt.executeUpdate("DELETE FROM CONNECTIONS;");
                    stmt.executeUpdate("UPDATE ACCOUNTS SET IND = -1");
                }
                catch(Exception ex){ex.printStackTrace();}
            }
        });
        JButton JB = new JButton("Reset DB");
        JB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String action=e.getActionCommand();
                if(action.equals("Reset DB")){
                    try{
                    Class.forName("org.hsqldb.jdbc.JDBCDriver");
                    con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat","SA","");
                    stmt=con.createStatement();
                    stmt.executeUpdate("Delete From Connections;");
                    stmt.executeUpdate("Delete From Accounts;");
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                }
            }
        });
        JPanel JP = new JPanel();
        JP.add(JB);
        JP.add(new JLabel("Server"));
        js.add(JP);
        js.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        js.setVisible(true);
        js.setSize(new java.awt.Dimension(180,180));
    }
    public GestioneThread CreateConnection(ThreadChatConnessioni First, int second){
        GestioneThread temp = new GestioneThread(First,connessioni.get(second),ThreadS.size());
        ThreadS.add(temp);
        return temp;
    }
    public GestioneThread getServer(int index){
        if(ThreadS.get(index)!=null){
            return ThreadS.get(index);
        }
        else 
            return null;
    }
    public static void main(){
        new Server();
    }
}