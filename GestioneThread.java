import java.awt.List;
import java.lang.Thread;
import java.net.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import org.hsqldb.jdbc.JDBCDriver;
public class GestioneThread{
    private List lista = null;
    boolean f=true;
    int i=0;
    private java.util.List <ThreadChatConnessioni> listaConnessioni= new java.util.ArrayList<ThreadChatConnessioni>();
    int res;
    Statement stmt;
    Connection con;
    public GestioneThread(ThreadChatConnessioni First, ThreadChatConnessioni Second, int i){
        System.out.println("Creazione stanza");
        try{
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat","SA","");
            stmt=con.createStatement();
            res=stmt.executeUpdate("INSERT INTO CONNECTIONS VALUES ("+i+",'"+First.getName()+"','"+Second.getName()+"')");
            System.out.println("prova:"+res);
        }catch(Exception e){
           e.printStackTrace(System.out);
        }
        this.lista=new List();
        this.listaConnessioni.add(First);
        this.listaConnessioni.add(Second);
        this.i=i;
        Second.addGT(this);
    }
    public void SpedisciMessaggio(String mex){
        lista.add(mex);
        //System.out.println("Server: "+mex);
        lista.select(lista.getItemCount()-1);
        for(int i=0;i<2;i++){
            if(listaConnessioni.get(i)!=null)
                  listaConnessioni.get(i).spedisciMessaggio(mex);
        }
    }
    public int getI(){
        return this.i;
    }
}