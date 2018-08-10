import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import org.hsqldb.jdbc.JDBCDriver;
import java.sql.ResultSet;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
public class PannelloClient extends JPanel implements ActionListener, KeyListener//,MouseListener
{   ThreadClient GS=null;
    List <String> lista = new ArrayList<String>();
    JTextField textNuovo=null;
    String F;
    //JFrame IPR=new JFrame("IP Request");
    String Ip = "127.0.0.1";
    ChatClient MW;
    JButton Invia=new JButton("Invia");
    boolean flag=true;
    public String nome=null;
    JTextField text2=new JTextField("");
    /*Icon ic= new ImageIcon("Button.png");
    Icon ic1= new ImageIcon("Button1.png");
    Icon ic2= new ImageIcon("Button2.png");*/
    JButton Invio= new JButton("Invio");
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    JFormattedTextField formattedTextField;
    JScrollPane pan3;
    JPanel pan2;
    Runnable Scroll;
    public PannelloClient(String Name, int j,ChatClient MW,ThreadClient con, String Ip){
        super(new BorderLayout());
        GS=con;
        Icon ic = new ImageIcon("settings.png");
        setBounds(0,0,450,300);
        //this.setSize(new Dimension(500,300));
        this.Ip=Ip;
        JPanel p = new JPanel(new BorderLayout(0,5));
        JPanel p2 = new JPanel(new BorderLayout(0,5));
        JPanel p3 = new JPanel();
        BoxLayout bb = new BoxLayout(p3, BoxLayout.Y_AXIS);
        p3.setLayout(bb);
        System.out.println(Name);
        try{
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
            Statement stmt=conn.createStatement();
            ResultSet res=stmt.executeQuery("SELECT NICK, NICK1, ID FROM CONNECTIONS WHERE NICK='"+Name+"' OR NICK1='"+Name+"';");
            while(res.next()){
                JButton temp = new JButton();
                temp.setMaximumSize(new Dimension(450,40));
                if(res.getString("NICK").equals(Name))
                    temp.setText(res.getString("NICK1"));
                else temp.setText(res.getString("NICK"));
                int i=res.getInt("ID");
                temp.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            GS.spedisciMessaggio("CH_Chat:"+e.getActionCommand()+":"+i);
                            MW.setFriend(e.getActionCommand());
                            MW.setNewPanel(2);
                        }
                });
                temp.setBorderPainted(false);
                temp.setBackground(Color.white);
                temp.setFocusPainted(false);
                temp.setContentAreaFilled(false);
                temp.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel Temp = new JLabel("____________________________________________________________");
                Temp.setForeground(Color.LIGHT_GRAY);
                Temp.setAlignmentX(Component.CENTER_ALIGNMENT);
                p3.add(temp);
                p3.add(Temp);
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
        JScrollPane p4 = new JScrollPane(p3);
        p3.setBorder(new EmptyBorder(2,2,2,2));
        p3.setBackground(Color.white);
        p2.setBorder(new EmptyBorder(0, 0, 5, 0));
        p2.setBackground(new Color(160,160,255).darker());
        p.setBackground(new Color(160,160,255));
        p2.add(p,BorderLayout.CENTER);
        JLabel lab = new JLabel(Name,JLabel.CENTER);
        lab.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lab);
        JButton jb = new JButton("+");
        jb.setFont(new Font("helvetica", 1 , 25));
        jb.setForeground(new Color(160,160,255).darker());
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String Bottone=e.getActionCommand();
                if(Bottone.equals("+")){
                    String NC = JOptionPane.showInputDialog(null, "Insert Nick");
                    if(NC!=null){
                        GS.spedisciMessaggio("New_Chat:"+NC);
                        MW.setFriend(NC);
                        MW.setNewPanel(2);
                   }
                }
            }
        });
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setMaximumSize(new Dimension(15,15));
        p.add(jb,BorderLayout.EAST);
        JButton jb1 = new JButton("   ");
        jb1.setFocusPainted(false);
        jb1.setContentAreaFilled(false);
        jb1.setBorderPainted(false);
        jb1.setSize(new Dimension(10,10));
        p.add(jb1, BorderLayout.WEST);
        lab.setFont(new Font("helvetica", 1 , 15));
        //pan.setBackground(Color.red);
        this.add(p2,BorderLayout.NORTH);
        add(p4);
    }
    public PannelloClient(int i, ChatClient MW, String Ip){
        super();
        this.Ip=Ip;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);
        this.MW=MW;
        formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(114, 62, 203, 20);
        add(formattedTextField);
        
        //JPasswordField formattedTextField_1 = new JPasswordField();
        //formattedTextField_1.setBounds(114, 129, 203, 20);
        //add(formattedTextField_1);
        //System.out.println(formattedTextField_1.getEchoChar());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(114,129,203,20);
        add(passwordField);
        JButton btnNewButton = new JButton("Log in");
        JButton NewIP= new JButton("⚙");
        
        NewIP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("⚙")){
                    String t=JOptionPane.showInputDialog(null,"New IP");
                    if(!t.equals(""))
                        MW.setIp(t);
            }
         }
        });
        NewIP.setFont(NewIP.getFont().deriveFont(NewIP.getFont().getSize()*1.5f));
        //NewIP.setBackground(new Color(160,160,255));
        NewIP.setContentAreaFilled(false);
        NewIP.setFocusPainted(false);
        NewIP.setBorderPainted(false);
        NewIP.setForeground(new Color(160,160,255));
        NewIP.setBounds(394,221,50,50);
        btnNewButton.setBounds(114, 179, 203, 23);
        add(btnNewButton);
        btnNewButton.setBackground(Color.white);
        btnNewButton.setForeground(new Color(160,160,255));
        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.setBounds(114, 220, 203, 23);
        add(btnNewButton_1);
        add(NewIP);
        //NewIP.setBounds(0,0,50,50);
        btnNewButton_1.setBackground(new Color(160,160,255));
        btnNewButton_1.setForeground(Color.white);
        JLabel lblPassword = new JLabel("Password");
        passwordField.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){}
            public void keyTyped(KeyEvent e){}
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == e.VK_ENTER){
                    ResultSet result=null;
                    Statement stmt=null;
                    try{
                       Class.forName("org.hsqldb.jdbc.JDBCDriver");
                       Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
                       stmt=conn.createStatement();
                       result=stmt.executeQuery("SELECT COUNT(*) AS CONTEGGIO FROM accounts WHERE nick='" + formattedTextField.getText()+"' AND psw='"+new String(passwordField.getPassword()).hashCode()+"';");
                       
                       while(result.next()){
                           //System.out.println(formattedTextField_1.getPassword());
                          if(result.getInt("CONTEGGIO")==1){
                            MW.setNome(formattedTextField.getText());
                            MW.setNewPanel(1);
                            GS.spedisciMessaggio("SetNick:"+formattedTextField.getText());
                            }
                          else JOptionPane.showMessageDialog(null,"Wrong combination Nick/password");
                        }
                    }catch(Exception ex){
                        ex.printStackTrace(System.out);
                    }
                    //MW.setNewPanel(1);
                }
            }
        });
        lblPassword.setBounds(114, 93, 59, 30);
        add(lblPassword);
        //connetti();
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String bottone = arg0.getActionCommand();
                if(bottone.equals("Log in")){
                    ResultSet result=null;
                    Statement stmt=null;
                    try{
                       Class.forName("org.hsqldb.jdbc.JDBCDriver");
                       Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
                       stmt=conn.createStatement();
                       result=stmt.executeQuery("SELECT COUNT(*) AS CONTEGGIO FROM accounts WHERE nick='" + formattedTextField.getText()+"' AND psw='"+new String(passwordField.getPassword()).hashCode()+"';");
                       
                       while(result.next()){
                           //System.out.println(formattedTextField_1.getPassword());
                          if(result.getInt("CONTEGGIO")==1){
                            MW.setNome(formattedTextField.getText());
                            MW.setNewPanel(1);
                            GS.spedisciMessaggio("SetNick:"+formattedTextField.getText());
                            }
                          else JOptionPane.showMessageDialog(null,"Wrong combination Nick/password");
                        }
                    }catch(Exception e){
                        e.printStackTrace(System.out);
                    }
                    //MW.setNewPanel(1);
            }
            }
        });
        btnNewButton_1.addActionListener(this);
        JLabel lblNick = new JLabel("Nick");
        lblNick.setBounds(114, 26, 46, 25);
        add(lblNick);
        }
    public PannelloClient(ThreadClient con, ChatClient MW, String F, String Ip){ 
        super(new BorderLayout(5,5));
        GS=con;
        this.F=F;
        this.Ip=Ip;
                 //_______________Declarations________________//
        JPanel pan1 = new JPanel(new BorderLayout(5,5));
        JLabel lab = new JLabel(F ,JLabel.CENTER);
        JButton jb = new JButton("<");
        pan2 = new JPanel();
        JPanel pan4 = new JPanel(new BorderLayout(5,5));
        BoxLayout box = new BoxLayout(pan2, BoxLayout.PAGE_AXIS);
        
        JPanel pan5 = new JPanel(new BorderLayout(10,5));
        JTextField TF = new JTextField("");
        JButton Invio = new JButton(">");
        int[] xpoints = new int[3];
        int[] ypoints = new int[3];
        pan3= new JScrollPane(pan2);
        JScrollBar vertical=pan3.getVerticalScrollBar();
        Scroll=new Runnable(){
            public void run(){
                vertical.setValue(vertical.getMaximum());
            }
        };
        //______________Instructions_________________//
        pan2.setLayout(box);
        GS.setList(lista);
        pan1.setBackground(new Color(160,160,255));
        lab.setFont(new Font("helvetica",1,15));
        jb.setFont(new Font("helvetica",1,15));
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String Button = e.getActionCommand();
                if(Button.equals("<")){
                    MW.setNewPanel(1);
                }
            }
        });
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        pan1.add(jb,BorderLayout.WEST);
        jb.setMaximumSize(new Dimension(15,15));
        pan1.add(lab,BorderLayout.CENTER);
        JButton JB1 = new JButton("   ");
        JB1.setFocusPainted(false);
        JB1.setBorderPainted(false);
        JB1.setContentAreaFilled(false);
        pan1.add(JB1,BorderLayout.EAST);
        add(pan1,BorderLayout.NORTH);
        pan4.add(new JLabel(""),BorderLayout.WEST);
        pan4.add(new JLabel(""),BorderLayout.EAST);
        pan4.setBackground(new Color(160,160,255).darker());
        setBackground(new Color(160,160,255).darker());
        pan4.add(pan3, BorderLayout.CENTER);
        add(pan4,BorderLayout.CENTER);
        pan5.add(TF,BorderLayout.CENTER);
        pan5.setBackground(new Color(160,160,255));
        add(pan5,BorderLayout.SOUTH);
        pan3.setAutoscrolls(true);
        TF.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                
            }
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == e.VK_ENTER){
                    SendMessage(TF.getText());
                    TF.setText("");
                }
            }
            public void keyTyped(KeyEvent e){}
        });
        //Invio.setOpaque(false);
        //Invio.setContentAreaFilled(false);
        Invio.setBackground(new Color(160,160,255));
        Invio.setFont(new Font("helvetica",1,15));
        //Invio.setBorderPainted(true);
        Invio.setFocusPainted(false);
        pan5.add(Invio, BorderLayout.EAST);
        Invio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String Bottone=e.getActionCommand();
                if(Bottone.equals(">")){
                    SendMessage(TF.getText());
                    TF.setText("");
                }
            }
        });
        pan5.add(new JLabel("  "),BorderLayout.WEST);
        vertical.setUnitIncrement(10);
    
    }
    public PannelloClient(ChatClient MW, ThreadClient con,String Ip){
        GS=con;
        setLayout(null);
        setBounds(0, 0, 450, 300);
        passwordField = new JPasswordField();
        passwordField.setBounds(134, 120, 176, 20);
        add(passwordField);
        this.Ip=Ip;
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(134, 169, 176, 20);
        add(passwordField_1);
        
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(134, 70, 176, 20);
        add(formattedTextField);
        
        JLabel lblNick = new JLabel(" Nick");
        lblNick.setBounds(134, 52, 76, 18);
        add(lblNick);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(134, 101, 76, 18);
        add(lblPassword);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setBounds(134, 151, 176, 14);
        add(lblConfirmPassword);
        passwordField_1.addKeyListener(new KeyListener(){
                public void keyPressed(KeyEvent e){}
                public void keyTyped(KeyEvent e){}
                public void keyReleased(KeyEvent e){
                    if(e.getKeyCode()==e.VK_ENTER){
                        Statement stmt=null;
                        int result;
                        try{
                        Class.forName("org.hsqldb.jdbc.JDBCDriver");
                        Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
                        stmt=conn.createStatement();
                        //System.out.println(passwordField.getPassword());
                        if(new String(passwordField.getPassword()).hashCode()== new String(passwordField_1.getPassword()).hashCode()){
                            result=stmt.executeUpdate("INSERT INTO accounts (Nick, psw) VALUES ('" +  formattedTextField.getText() +"' , '"+ new String(passwordField.getPassword()).hashCode() + "');" );
                            MW.setNome(formattedTextField.getText());
                            MW.setNewPanel(1);
                            GS.spedisciMessaggio("SetNick:"+formattedTextField.getText());
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Passwords don't match");
                            
                        }
                        //flag=true;
                    }
                    catch(Exception ex){
                        //flag=false;
                        JOptionPane.showMessageDialog(null,"Nick is not available");
                        ex.printStackTrace(System.out);
                    }
                 }
                }
        });
        JButton btnNewButton = new JButton("Enter");
        btnNewButton.setBounds(134, 200, 176, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String bottone = arg0.getActionCommand();
                if(bottone.equals("Enter")){
                    Statement stmt=null;
                    int result=0;
                    boolean flag=true;
                    String prova="no";
                    ResultSet result1=null;
                    try{
                        Class.forName("org.hsqldb.jdbc.JDBCDriver");
                        Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
                        stmt=conn.createStatement();
                        //System.out.println(passwordField.getPassword());
                        if(new String(passwordField.getPassword()).hashCode()== new String(passwordField_1.getPassword()).hashCode()){
                            result=stmt.executeUpdate("INSERT INTO accounts (Nick, psw) VALUES ('" +  formattedTextField.getText() +"' , '"+ new String(passwordField.getPassword()).hashCode() + "');" );
                            MW.setNome(formattedTextField.getText());
                            MW.setNewPanel(1);
                            GS.spedisciMessaggio("SetNick:"+formattedTextField.getText());
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Passwords don't match");
                            
                        }
                        //flag=true;
                    }
                    catch(Exception e){
                        //flag=false;
                        JOptionPane.showMessageDialog(null,"Nick is not available");
                        e.printStackTrace(System.out);
                    }
                    
            }
            }
        });
        btnNewButton.setBackground(new Color(160,160,255));
        btnNewButton.setForeground(Color.white);
        add(btnNewButton);
    }
    public ThreadClient connetti(){
       return GS=new ThreadClient(lista , this.nome, Ip , 3250,MW);
    }
    public void Close(String nome){
        Statement stmt;
        Connection con;
        try{
                    Class.forName("org.hsqldb.jdbc.JDBCDriver");
                    con=DriverManager.getConnection("jdbc:hsqldb:hsql://"+Ip+":9001/datachat","SA","");
                    stmt=con.createStatement();
                    stmt.executeUpdate("DELETE FROM CONNECTIONS WHERE Nick='"+nome+"' OR Nick1='"+nome+"';");
                    stmt.executeUpdate("UPDATE ACCOUNTS SET IND=-1 WHERE NICK='"+nome+"';");
                }
                catch(Exception ex){ex.printStackTrace();}
    }
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        if(!flag){
            if(e.getKeyCode()== e.VK_ENTER){
                 GS.spedisciMessaggio(this.nome + ":" + textNuovo.getText());
                 textNuovo.setText("");
                }
        }
        else if(flag)
            if(e.getKeyCode()== e.VK_ENTER){
                     Ip=textNuovo.getText();
                     setNome();
                     MW.setNome(nome);
                     MW.setNewPanel(0);
                     //setNome();
                     //System.out.println(nome);
                    }
        }
    public void keyTyped(KeyEvent e){
        
    }
    public void actionPerformed(ActionEvent e){
        String bottone = e.getActionCommand();
        if(bottone.equals("Invia")){
            GS.spedisciMessaggio(this.nome+": "+textNuovo.getText());
            textNuovo.setText("");
        }
        else if(bottone.equals("Register")){
            //Ip=textNuovo.getText();
            setNome();
            MW.setNome(nome);
            MW.setNewPanel(0);
        }
    }
    private void setNome(){
       this.nome=formattedTextField.getText();
    }
    public void setNome(String nome){
      this.nome=nome;
      //System.out.println(this.nome);
    }
    /*public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){
        Invio.setIcon(ic1);
        Invio.setForeground(Color.RED);
        Invia.setIcon(ic1);
        Invia.setForeground(Color.RED);
    }
    public void mouseExited(MouseEvent e){
        Invio.setIcon(ic);
        Invio.setForeground(Color.WHITE);
        Invia.setIcon(ic);
        Invia.setForeground(Color.WHITE);
    }
    public void mousePressed(MouseEvent e){
        Invio.setIcon(ic2);
        Invio.setForeground(Color.BLUE);
        Invia.setIcon(ic2);
        Invia.setForeground(Color.BLUE);
    }
    public void mouseReleased(MouseEvent e){
        Invio.setIcon(ic);
        Invio.setForeground(Color.WHITE);
        Invio.setIcon(ic);
        Invio.setForeground(Color.WHITE);
    }*/
    public int countWords(String s){
    
        int wordCount = 0;
    
        boolean word = false;
        int endOfLine = s.length() - 1;
    
        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
            return wordCount;
    }
    public void AddMex(String Mex){
                //_______________Declarations________________//
                int Col=25;
                int len;
                int[] xpoints=new int[3];
                int[] ypoints=new int[3];
                //String S = new String(Mex);
                JScrollBar vertical=pan3.getVerticalScrollBar();
                JTextArea txt = new JTextArea(Mex);
                Runnable Scroll=new Runnable(){
                public void run(){
                    vertical.setValue(vertical.getMaximum());
                }
            };
                //JPanel PanForFun = new JPanel(new BorderLayout(20,5));
                JPanel TempPan = new JPanel(){
                    @Override
                    public void paintComponent(Graphics g){
                        super.paintComponent(g);
                        xpoints[0]=(int) txt.getBounds().getX()-10;
                        xpoints[1]=xpoints[2]=(int) txt.getBounds().getX()+5;
                        ypoints[0]=ypoints[1]=(int) txt.getBounds().getY();
                        ypoints[2]=(int) txt.getBounds().getY()+10;
                        g.setColor(new Color(225,225,225).darker());
                        Polygon p=new Polygon(xpoints,ypoints,3);
                        g.fillPolygon(p);
                        //g.setColor(Color.blue.brighter());
                        g.drawPolygon(p);
                        //g.setColor(Color.white);
                        g.setColor(new Color(225,225,225));
                        g.fillRoundRect((int)txt.getBounds().getX() -2,(int)txt.getBounds().getY() ,txt.getWidth(),txt.getHeight(),5,5);
                        //g.setColor(Color.blue.brighter());
                        g.setColor(new Color(225,225,225).darker());
                        g.drawRoundRect((int)txt.getBounds().getX() -2,(int)txt.getBounds().getY() ,txt.getWidth(),txt.getHeight(),5,5);
                    }
                };
                
                //______________Instructions_________________//
                len=txt.getText().length();
                
                txt.setEditable(false);
                FlowLayout FL= new FlowLayout(FlowLayout.LEFT, 10,5);
                TempPan.setLayout(FL);
                if(len<50){
                    Col=(len/2)+2;
                    txt.setLineWrap(false);
                    //txt.setWrapStyleWord(false);
                }
                else{
                    txt.setLineWrap(true);
                    if(countWords(txt.getText())!=0)
                      txt.setWrapStyleWord(true);
                    else {
                        txt.setWrapStyleWord(false);
                        txt.setText(txt.getText().trim());
                    }
                }
                txt.setOpaque(false);
               //txt.setFont(new Font("Helvetica",0,15));
                txt.setColumns(Col);
                //TempPan.setMaximumSize(new Dimension(370,txt.getLineCount()*15));
                TempPan.add(txt);
                //txt.setBounds(10,5,Col*15,RXTextUtilities.getWrappedLines(txt)*17);
                //txt.setMaximumSize(new Dimension(Col*15,RXTextUtilities.getWrappedLines(txt)*17));
                //txt.setMinimumSize(txt.getMaximumSize());
                //txt.setPreferredSize(txt.getMaximumSize());
                TempPan.setMaximumSize(new Dimension (420,(RXTextUtilities.getWrappedLines(txt)*17)+10));
                //TempPan.setSize(TempPan.getMaximumSize());
                //TempPan.setPreferredSize(TempPan.getMaximumSize());
                //TempPan.setAlignmentX(Component.LEFT_ALIGNMENT);
                TempPan.setAlignmentY(SwingUtilities.BOTTOM);
                //TempPan.add(new JLabel(""),BorderLayout.WEST);
                //PanForFun.add(TempPan,BorderLayout.WEST);
                pan2.add(TempPan);
                pan3.revalidate();
                SwingUtilities.invokeLater(Scroll);
                //pan3.repaint();
                //TempPan.setMaximumSize(new Dimension(500,500));
    }
    public void SendMessage(String mex){
                int Col=25;
                int len;
                int[] xpoints = new int[3];
                int[] ypoints = new int[3];
                //String S = new String(TF.getText());
                    if(!mex.equals("")){
                    
                    //TF.setText("");
                    JTextArea txt = new JTextArea(mex);
                    len=txt.getText().length();
                    txt.setEditable(false);
                    if(len%2!=0){
                        txt.setText(txt.getText()+" ");
                        len++;
                    }
                    JPanel TempPan = new JPanel(new BorderLayout(5,5)){
                        @Override
                        public void paintComponent(Graphics g){
                            super.paintComponent(g);
                            xpoints[0]=(int) txt.getBounds().getX()+txt.getWidth()-1;
                            xpoints[1]=xpoints[2]=(int) txt.getBounds().getX()+txt.getWidth()-20;
                            ypoints[0]=ypoints[1]=(int) txt.getBounds().getY();
                            ypoints[2]=(int) txt.getBounds().getY()+10;
                            //g.setColor(Color.black);
                            g.setColor(new Color(160,160,255));
                            Polygon p=new Polygon(xpoints,ypoints,3);
                            g.fillPolygon(p);
                            //g.setColor(Color.black);
                            g.drawPolygon(p);
                            //g.setColor(new Color(160,160,255));
                            g.fillRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-4,txt.getHeight()+2,5,5);
                            //g.setColor(Color.black);
                            g.drawRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-4,txt.getHeight()+2,5,5);
                            
                        }
                    };
                    
                    //______________Instructions_________________//
                    
                    if(len<50){
                        Col=(len/2)+2;
                        txt.setLineWrap(false);
                    }
                    else{
                        txt.setLineWrap(true);
                        if(countWords(txt.getText())!=1){
                            
                            txt.setWrapStyleWord(true);
                        }
                        else{
                            
                            txt.setWrapStyleWord(false);
                            
                        }   
                    }
                    txt.setOpaque(false);
                    //txt.setFont(new Font("Helvetica",0,15));
                    txt.setColumns(Col);
                    //txt.setMaximumSize(txt.getPreferredSize());
                    //System.out.println(txt.WIDTH+"||"+txt.HEIGHT);
                    //TempPan.setMaximumSize(txt.getSize());
                    //System.out.println(txt.getLineCount());
                    //System.out.println("num: "+txt.getLineCount());
                    //txt.setMinimumSize(new Dimension(Col*15,RXTextUtilities.getWrappedLines(txt)*17));
                    TempPan.setMaximumSize(new Dimension(420,(RXTextUtilities.getWrappedLines(txt)*17)+10));
                    TempPan.add(txt,BorderLayout.EAST);
                    TempPan.setAlignmentY(SwingConstants.BOTTOM);
                    pan2.add(TempPan);
                    pan3.revalidate();
                    pan3.repaint();
                    
                    TempPan.add(new JLabel(""),BorderLayout.NORTH);
                    TempPan.add(new JLabel(""),BorderLayout.SOUTH);
                    SwingUtilities.invokeLater(Scroll);
                    GS.spedisciMessaggio(nome+":"+txt.getText());
                    System.out.println(nome+":"+txt.getText());
                }
           
            
    }
    public String getF(){
        return F;
    }
}
