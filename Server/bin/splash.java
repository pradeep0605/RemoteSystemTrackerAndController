import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


class Splash extends JWindow
{

 Image img;
 public Splash()
 {
   try
   {
    getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    setSize(350,300);
    setLocation(300,200);
    getContentPane().setBackground(Color.WHITE);

    JPanel developer=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
    developer.add(new JLabel("Developers"));
    developer.setPreferredSize(new Dimension(350,15));
    developer.setBackground(Color.WHITE);

    JPanel vinay=new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
    vinay.add(new JLabel("Vinay S K"));
    vinay.setPreferredSize(new Dimension(170,15));
    vinay.setBackground(Color.WHITE);


    JPanel pradeep=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    pradeep.add(new JLabel("Pradeep Kashyap R"));
    pradeep.setPreferredSize(new Dimension(170,15));
    pradeep.setBackground(Color.WHITE);
    
    JPanel zabee=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    zabee.add(new JLabel("Zabee Ulla"));
    zabee.setPreferredSize(new Dimension(170,15));
    zabee.setBackground(Color.WHITE);

    JPanel sathya=new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
    sathya.add(new JLabel("Sathya Prasad H R"));
    sathya.setPreferredSize(new Dimension(170,15));
    sathya.setBackground(Color.WHITE);



    JPanel jp=new JPanel(new FlowLayout());
    jp.setPreferredSize(new Dimension(350,20));
    jp.setLayout(new FlowLayout(FlowLayout.LEFT));
    jp.setOpaque(true);
    jp.setBackground(new Color(255,255,255,255));
    getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
    jp.add(new JLabel("Loading ....              "));
    jp.add(new JLabel(new Date().toString().trim()));

    getContentPane().add(new ImagePanel()); 
    getContentPane().add(jp);
    getContentPane().add(developer);
    getContentPane().add(pradeep);
    getContentPane().add(vinay);
    getContentPane().add(zabee);
    getContentPane().add(sathya);
    jp.setVisible(true);

    setVisible(true);
    Thread.currentThread().sleep(4000);
    setVisible(false);
   }catch(Exception e){}
 }  
    
}

class ImagePanel extends JPanel
{
 Image img;
 ImagePanel()
 {
    img=Toolkit.getDefaultToolkit().getImage("rstcicon2.jpg");
    img=img.getScaledInstance(350,200,4);
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setOpaque(false);
    setBackground(new Color(255,255,255,0));
    setPreferredSize(new Dimension(350,200));

    repaint();
    setVisible(true);
 }
  public void paint(Graphics g)
  {
   try
   {
    g.drawImage(img,0,0,this);
   }catch(Exception e){}
  }

}
