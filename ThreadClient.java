import java.lang.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ThreadClient implements Runnable {
    private List<String> lista;
    Thread me;
    private Socket client;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String nome;
    private PannelloClient PC=null;
    private ChatClient CC= null;
    //private GestioneThread gestoreChat=null;
    public ThreadClient(List lista, String nome, String ipServer, int porta, ChatClient CC){ 
        this.nome=nome;
        this.lista= lista;
        this.CC=CC;
        try{ 
            client = new Socket(ipServer, porta);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(),true);
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null,"Impossibile connettersi al server");
        }
        me=new Thread(this);
        me.start();
    }
    public void run(){
        
        while(true){
            try{
                String mex=null;
                while((mex=in.readLine())==null){}
                lista.add(mex);
                String[] mex1=mex.split(":",2);
                if(mex1[0].equals("Notify")){
                    JOptionPane.showMessageDialog(null, "1 New Message from "+mex1[1]);
                    if(CC.getI()==01){
                        CC.setNewPanel(1);
                    }
                    lista.add(mex1[1]);
                }
                else
                    {
                        if(PC!=null)
                            PC.AddMex(mex1[1]);
                        /*else{
                            JOptionPane.showMessageDialog(null, "1 New Message from "+mex1[0]);
                    }*/
                }
                //lista.setBackground(Color.red);
                //lista.setForeground(Color.red);
                //gestoreChat.SpedisciMessaggio(mex);
                //spedisciMessaggio(mex);
            }
            catch(IOException e){
                out.println("Errore spedizione messaggiooo");
            }
        }
    }
    public void spedisciMessaggio(String mex){
        String[] mex1= mex.split(":");
        
            try{
                out.println(mex);
                System.out.println(mex);
            //out.setForeground(Color.RED);
           }
           catch(Exception e){
               e.printStackTrace(System.out);
           }
        }
    
    public BufferedReader getInput(){
        return in;
    }
    public PrintWriter getPrint(){
        return out;
    }
    public void setList(List lista){
        if(lista==null)
            this.lista=lista;
    }
    public void setPC(PannelloClient PC){
        this.PC=PC;
        String F=PC.getF();
        for(int i=0;i<lista.size();i++){
            String[] l=lista.get(i).split(":",2);
            System.out.println("lista: "+lista.get(i));
            if(l[0].equals(F)){
                PC.AddMex(l[1]);
                
            }
            
        }
        
    }
}