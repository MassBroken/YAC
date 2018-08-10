import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
public class ProvaNuovaFinestra extends JFrame
{
    public ProvaNuovaFinestra(){
        super("Prova");
        Icon ic = new ImageIcon("settings.png");
        
        //this.setSize(new Dimension(500,300));
        JPanel pan = new JPanel(new BorderLayout());
        JPanel p = new JPanel(new BorderLayout(5,5));
        JPanel p2 = new JPanel(new BorderLayout(5,5));
        JPanel p3 = new JPanel();
        BoxLayout bb = new BoxLayout(p3, BoxLayout.Y_AXIS);
        p3.setLayout(bb);
 
        JScrollPane p4=new JScrollPane(p3);
        for(int i=0;i<150;i++){
            JButton b = new JButton(""+i);
           
            b.setMaximumSize(new Dimension(800,200));
            b.setBorderPainted(false);
            b.setBackground(Color.white);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String Bottone=e.getActionCommand();
                    JOptionPane.showMessageDialog(null,Bottone);
                }
            });
            p3.add(b);
            b.setFocusPainted(false);
            b.setContentAreaFilled(false);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel Temp = new JLabel("____________________________________________________________");
            Temp.setForeground(Color.LIGHT_GRAY);
            Temp.setAlignmentX(Component.CENTER_ALIGNMENT);
            p3.add(Temp);
        } 
        p3.setBorder(new EmptyBorder(2,2,2,2));
        p3.setBackground(Color.white);
        p2.setBorder(new EmptyBorder(0, 0, 5, 0));
        p2.setBackground(new Color(194,0,0).darker());
        p.setBackground(new Color(194,0,0).brighter());
        p2.add(p,BorderLayout.CENTER);
        JLabel lab = new JLabel("Lorem Ipsum",JLabel.CENTER);
        lab.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lab);
        JButton jb = new JButton("+");
        jb.setFont(new Font("helvetica", 1 , 25));
        jb.setForeground(new Color(194,0,0).darker());
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String Bottone=e.getActionCommand();
                if(Bottone.equals("+")){
                    JOptionPane.showMessageDialog(null,"Nuova Chat");
                }
            }
        });
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setMaximumSize(new Dimension(15,15));
        JButton jb1 = new JButton(ic);
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String bottone=e.getActionCommand();
                if(bottone.equals("")){
                    JOptionPane.showMessageDialog(null,"Impostazioni");
                }
            }
        });
        jb1.setFocusPainted(false);
        jb1.setContentAreaFilled(false);
        jb1.setBorderPainted(false);
        p.add(jb1, BorderLayout.WEST);
        p.add(jb,BorderLayout.EAST);
        pan.add(p4);
        lab.setFont(new Font("helvetica", 1 , 15));
        //pan.setBackground(Color.red);
        pan.add(p2,BorderLayout.NORTH);
        this.setContentPane(pan);
        this.setVisible(true);
        this.setResizable(false);
        pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(600, 400, 450, 300);
    }
}
