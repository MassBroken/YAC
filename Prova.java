import javax.swing.*;
import java.awt.*;
public class Prova extends JFrame
{
    public Prova(){
            JPanel panel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            //super.paintComponent(g);
            for(int i=0;i<600;i=i+20){
                g.setColor(new Color(i/3,0,0));
                g.fillRoundRect(0+i/2,0+i/2,600-i,600-i,100,100);}
            
    }
    };
    JLabel p=new JLabel("ciao");
    p.setBackground(Color.white);
    p.setForeground(Color.BLUE);
    p.setBounds(300,300,300,300);
    panel.add(p);
    
        /*Graphics g=pan.getGraphics();
        g.drawOval(0,0,150,150);*/
        this.add(panel);
        panel.setBounds(0,0,600,600);
        this.setVisible(true);
        this.setBounds(600,600,600,600);
        //repaint();
    }
    public static void main(){
        System.out.println(EXIT_ON_CLOSE);
    }
    /*public void paintComponent(Graphics g){
        g.setColor(new Color(194,0,0));
        g.fillOval(0,0,150,150);
    }*/
}
