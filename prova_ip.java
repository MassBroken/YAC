
import java.net.*;
public class prova_ip
{
   public static void main(){
       try{
       System.out.println(Inet4Address.getLocalHost().getHostAddress());
    }catch(Exception e){
        e.printStackTrace();
    }
   }
}
