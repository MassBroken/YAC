import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import org.hsqldb.jdbc.JDBCDriver;
import java.sql.ResultSet;
public class Select{
   public static void main(){
       Connection con=null;
       Statement stmt=null;
       ResultSet result=null;
       try{
          Class.forName("org.hsqldb.jdbc.JDBCDriver");
          con=DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9001/datachat", "SA", "");
          stmt=con.createStatement();
          result=stmt.executeQuery("SELECT * FROM Accounts;");
          if(result.next())
              do{
                System.out.println(result.getString("Nick")+"|" + result.getString("PSW")+" | "+result.getInt("IND"));
                
                }while(result.next());
            else System.out.println("No results");
        }
        catch(Exception e){
             e.printStackTrace(System.out);
        
        
        }
    
    }
}