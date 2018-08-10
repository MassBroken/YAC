import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class ChatClient extends JFrame {
    String Ip, nome;
    PannelloClient pan= null;
    PannelloClient pan1=null;
    private ThreadClient con;
    String F;
    BufferedImage img=null;
    int i=-1;
    public ChatClient(){
        super("Chat Client"); 
        Ip=new String("127.0.0.1");
        //this.setSize(new Dimension(500,300));
        this.setLocationRelativeTo(null);
        this.setEnabled(true);
        this.setBackground(Color.black);
        pan=new PannelloClient(1, this,"127.0.0.1");
        con=pan.connetti();
        this.getContentPane().add(pan);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                if(i!=-1)
                  pan1.Close(nome);
            }
        });
        try{
            img=ImageIO.read(new File("Logo YAC.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.setIconImage(img);
        setBounds(600, 400, 450, 300);
    }
    public void setIp(String Ip){
           this.Ip=Ip;
           PannelloClient Pan = new PannelloClient(1,this,Ip);
           this.setContentPane(Pan);
           con=Pan.connetti();
           this.revalidate();
           
        }
    public void setNewPanel(int fase){
           //this.setVisible(false);
           i=fase;
           switch(fase){
             case 0:
                  //setBounds(600, 400, 450, 300);
                  pan1=new PannelloClient(this,con,Ip);
                  pan1.setNome(nome);
                  this.setContentPane(pan1);
                  break;
             case 1:
                  //setBounds(600, 400, 450, 300);
                  setSize(new Dimension(450, 300));
                  pan1=new PannelloClient(nome,1,this,con, Ip);
                  pan1.setNome(nome);
                  this.setContentPane(pan1);
                  break;
             case 2:
                  setSize(new Dimension(450, 300));
                  pan1= new PannelloClient(con,this,F, Ip);
                  pan1.setNome(nome);
                  con.setPC(pan1);
                  this.setContentPane(pan1);
                  revalidate();
                  break;
            }
            
            //this.setVisible(true);
    }
    public void setNome(String nome){
       this.nome=nome;
    }
    public void setFriend(String nome){
        F=nome;
    }
    public String getFriend(){
        return F;
    }
    public int getI(){
        return i;
    }
}