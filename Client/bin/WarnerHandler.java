import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;
import javax.swing.*;


public class WarnerHandler extends JFrame implements Runnable
{
    int port;
    String warn;
    Thread t;

  public WarnerHandler(int port)
  {
   try
   {
     t = new Thread(this);
     t.sleep(100);
     this.port=port;
     Client.cs_warnersocket=new Socket(Client.serverip, port);
     System.out.println("After getttin ip and port");

     System.out.println("Before Starting thread");
     t.start();
    }
     catch(Exception e)
     {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
      System.out.println(e);
      }
  }

  public void run()
  {
   try
   {
    DataInputStream sockin = new DataInputStream(Client.cs_warnersocket.getInputStream());
    while(true)
    {       
     try
     {
      warn = sockin.readLine();
      System.out.println("WARNING: "+warn);
      if(warn!=null)
      new WarnerHandler2(warn);
     }
     catch(Exception e)
     {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
      System.out.println(e.toString());
      }
    }
   }catch(Exception e)
   {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
     System.out.println(e.toString());
    }
  }

}


class WarnerHandler2 extends JFrame
{
  String warnmsg;
  Image img;
  int count=0;
  boolean exists_img=false;

  public WarnerHandler2(String msg)
  {
    try
    {
     setSize(370,370);
     setLocation(300,200);
     setResizable(true);
     //setUndecorated(true);
     setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
     setVisible(true);
     setState(1);
     setState(0);
     warnmsg=msg;
    }catch(Exception e)
     {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
      System.out.println(e.toString());
     }
     while(true)
      {
       try
        {
         if(false)break;
         img = Toolkit.getDefaultToolkit().getImage("warning1.JPG");
         exists_img=true;
         repaint();
         Thread.currentThread().sleep(300);
         //Runtime.getRuntime().exec("tskill explorer");
         img = Toolkit.getDefaultToolkit().getImage("warning2.JPG");
         exists_img=true;
         repaint();
         Thread.currentThread().sleep(300);
         count++;
         if(count>=11)
         {
          setState(1);
          setState(0);
          //Runtime.getRuntime().exec("explorer");
          System.out.println("Creatimg explorer");
          break;
         }

        }catch(Exception e)
        {
         if(e.toString().indexOf("Connection reset")!=-1)
         ClientRSTC.destroyClient();
         else
         System.exit(0);
         System.out.println(e.toString());
         }
      }
    }

   public void paint(Graphics g)
   {
     System.out.println("Before Painting:");
     if(exists_img)
      g.drawImage(img, 0, 0, this);
         
     if(count>10)
     {
      JOptionPane.showMessageDialog(null,warnmsg,"RSTC Warner",1);
      setVisible(false);
     }
     System.out.println("After Painting:");
  }
}
