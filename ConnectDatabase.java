import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.hsqldb.jdbc.JDBCDriver;
public class ConnectDatabase {
   public static void main(String[] args) {
      Connection con = null;
      Statement stmt= null;
      int result=0;
      try {
         //Registering the HSQLDB JDBC driver
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         
         
         //Creating the connection with HSQLDB
         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat", "SA", "");
         if (con!= null){
            System.out.println("Connection created successfully");
            
         }else{
            System.out.println("Problem with creating connection");
         }
      
      }  catch (Exception e) {
         e.printStackTrace(System.out);
      }
      try {
         stmt = con.createStatement();
         result= stmt.executeUpdate("CREATE TABLE accounts (NICK VARCHAR(25) NOT NULL, PSW VARCHAR(25) NOT NULL, IND INT , PRIMARY KEY(NICK))");
         System.out.println(result);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
   }
}