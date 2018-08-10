import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import org.hsqldb.jdbc.JDBCDriver;
public class Add_User
{
    public static void main(){
        Connection con=null;
        Statement stmt=null;
        int result=0;
        try{
           Class.forName("org.hsqldb.jdbc.JDBCDriver");
           con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/datachat", "SA", "");
           stmt=con.createStatement();
           result=stmt.executeUpdate("DELETE FROM CONNECTIONS");
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
}
