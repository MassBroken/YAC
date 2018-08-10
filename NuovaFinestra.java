import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class NuovaFinestra extends JFrame
{
    public NuovaFinestra(){
        //_______________Declarations________________//
        JPanel pan = new JPanel(new BorderLayout(5,5));
        JPanel pan1 = new JPanel(new BorderLayout(5,5));
        JLabel lab = new JLabel("Lorem Ipsum",JLabel.CENTER);
        JButton jb = new JButton("+");
        JPanel pan2 = new JPanel();
        JPanel pan4 = new JPanel(new BorderLayout(5,5));
        BoxLayout box = new BoxLayout(pan2, BoxLayout.PAGE_AXIS);
        JScrollPane pan3 = new JScrollPane(pan2);
        JPanel pan5 = new JPanel(new BorderLayout(10,5));
        JTextField TF = new JTextField("");
        JButton Invio = new JButton(">");
        int[] xpoints = new int[3];
        int[] ypoints = new int[3];
        JScrollBar vertical=pan3.getVerticalScrollBar();
        Runnable Scroll=new Runnable(){
            public void run(){
                vertical.setValue(vertical.getMaximum());
            }
        };
        //______________Instructions_________________//
        pan2.setLayout(box);
        pan2.setBackground(Color.white);
        for(int i=0;i<10;i++){
            if(i%2==0){
                //_______________Declarations________________//
                int Col=25;
                int len;
                String S = new String("prova");
                JTextArea txt = new JTextArea(S);
                //JPanel PanForFun = new JPanel(new BorderLayout(5,5));
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
                        g.fillRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth(),txt.getHeight()-2,5,5);
                        //g.setColor(Color.blue.brighter());
                        g.setColor(new Color(225,225,225).darker());
                        g.drawRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth(),txt.getHeight()-2,5,5);
                    }
                };
                
                //______________Instructions_________________//
                len=txt.getText().length();
                if(len<50){
                    Col=(len/2)+2;
                        txt.setLineWrap(false);
                }
                txt.setEditable(false);
                txt.setLineWrap(true);
                txt.setWrapStyleWord(true);
                txt.setOpaque(false);
                txt.setFont(new Font("Helvetica",0,15));
                txt.setColumns(Col);
                TempPan.add(txt,BorderLayout.EAST);
                TempPan.setAlignmentY(SwingConstants.BOTTOM);
                pan2.add(TempPan);
            }
            else{
                //_______________Declarations________________//
                int Col=25;
                int len;
                String S = new String("Prova");
                JTextArea txt = new JTextArea(S);
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
                            g.fillRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-5,txt.getHeight(),5,5);
                            //g.setColor(Color.black);
                            g.drawRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-5,txt.getHeight(),5,5);
                            
                    }
                };
                
                //______________Instructions_________________//
                len=txt.getText().length();
                if(len<50){
                    Col=(len/2)+2;
                        txt.setLineWrap(false);
                }
                else{
                        txt.setLineWrap(true);
                        txt.setWrapStyleWord(true);
                    }
                txt.setEditable(false);
                //txt.setLineWrap(true);
                //txt.setWrapStyleWord(true);
                txt.setOpaque(false);
                txt.setFont(new Font("Helvetica",0,15));
                txt.setColumns(Col);
                //txt.setForeground(Color.white);
                TempPan.add(txt,BorderLayout.EAST);
                TempPan.add(new JLabel(""),BorderLayout.SOUTH);
                TempPan.setAlignmentY(SwingConstants.BOTTOM);
                pan2.add(TempPan);
            }
        }
        pan1.setBackground(new Color(160,160,255));
        lab.setFont(new Font("helvetica",1,15));
        jb.setFont(new Font("helvetica",1,25));
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        pan1.add(jb,BorderLayout.WEST);
        jb.setMaximumSize(new Dimension(15,15));
        pan1.add(lab,BorderLayout.CENTER);
        pan.add(pan1,BorderLayout.NORTH);
        pan4.add(new JLabel(""),BorderLayout.WEST);
        pan4.add(new JLabel(""),BorderLayout.EAST);
        pan4.setBackground(new Color(160,160,255).darker());
        pan.setBackground(new Color(160,160,255).darker());
        pan4.add(pan3, BorderLayout.CENTER);
        pan.add(pan4,BorderLayout.CENTER);
        pan5.add(TF,BorderLayout.CENTER);
        pan5.setBackground(new Color(160,160,255));
        pan.add(pan5,BorderLayout.SOUTH);
        pan3.setAutoscrolls(true);
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
                    
                        //_______________Declarations________________//
                    int Col=25;
                    int len;
                    String S = new String(TF.getText());
                    if(!S.equals("")){
                    TF.setText("");
                    JTextArea txt = new JTextArea(S);
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
                            g.fillRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-5,txt.getHeight(),5,5);
                            //g.setColor(Color.black);
                            g.drawRoundRect((int)txt.getBounds().getX() -3,(int)txt.getBounds().getY() ,txt.getWidth()-5,txt.getHeight(),5,5);
                            
                        }
                    };
                    
                    //______________Instructions_________________//
                    len=txt.getText().length();
                    txt.setEditable(false);
                    if(len<50){
                        Col=(len/2)+2;
                        txt.setLineWrap(false);
                    }
                    else{
                        txt.setLineWrap(true);
                        txt.setWrapStyleWord(true);
                    }
                    txt.setOpaque(false);
                    txt.setFont(new Font("Helvetica",0,15));
                    txt.setColumns(Col);
                    //txt.setForeground(Color.white);
                    TempPan.add(txt,BorderLayout.EAST);
                    TempPan.setAlignmentY(SwingConstants.BOTTOM);
                    pan2.add(TempPan);
                    pan3.revalidate();
                    pan3.repaint();
                    TempPan.add(new JLabel(""),BorderLayout.NORTH);
                    TempPan.add(new JLabel(""),BorderLayout.SOUTH);
                    SwingUtilities.invokeLater(Scroll);
                }
            }
            }
        });
        pan5.add(new JLabel("  "),BorderLayout.WEST);
        vertical.setUnitIncrement(10);
        
        //___________________Frame settings___________________//
        setContentPane(pan);
        setVisible(true);
        setBounds(400,400,380,300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

