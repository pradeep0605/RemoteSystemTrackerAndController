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
 String s;
 public Splash()
 {
   try
   {
    getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    //s= new Date().toString();
    setSize(350,300);
    setLocation(300,200);
    getContentPane().setBackground(Color.WHITE);
    img=Toolkit.getDefaultToolkit().getImage("Splash.jpg");
    img= img.getScaledInstance(350,300,4);
    repaint();
    setVisible(true);
    Thread.currentThread().sleep(2000);
    setVisible(false);
   }catch(Exception e){System.out.println(e);}
 }

 public void paint(Graphics g)
 {
  try
  {
   g.drawImage(img,0,0,this);
   g.drawString(s,0,190);
  }catch(Exception e){}
 } 
    
}

