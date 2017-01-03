import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class BlockHandler implements Runnable
{
 int port;
 static DataInputStream blockin;
 static JFrame f;
 KillEx ke;
 Thread t;
 


    public void sleep(int time)
    {
     try
     {
      t.sleep(time);
     }catch(Exception e){}
    }


  public BlockHandler(int port)
  {
   try
   {
    t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_blocksocket=new Socket(Client.serverip,port);
    blockin=new DataInputStream(Client.cs_blocksocket.getInputStream());

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
    while(true)
    {
     try
     {            
      String s=blockin.readLine().trim();
      System.out.println(s);
      if(s.equals("Block"))
      {
        f=new JFrame("");
        new Robot().keyPress(27);
        ke=new KillEx();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setUndecorated(true);
        f.setBackground(Color.BLACK);
        JPanel jp = new JPanel(new BorderLayout());

        f.setContentPane(jp);
        jp.setBackground(Color.BLACK);
        jp.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        jp.add(new JLabel("<html> <font color=red size=20><center> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; System Has Been Blocked By Administrator</center> </font> </html>"));
        jp.setVisible(true);
        f.setDefaultCloseOperation(0);
        f.setLocation(0,0);
        f.setVisible(true);
        f.setState(1);
        f.setState(0);
      }
      
      else
      {
       ke.stop();
       new Robot().keyRelease(27);
       sleep(1000);
       Runtime.getRuntime().exec("explorer");
       f.setVisible(false);
      }
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
 }


class KillEx  extends Thread
{
  public KillEx()
  {
    start();
  }

  public void run()
  {
   try
   {
    Robot r=new Robot();
    while(true)
    {
    try
    {
     r.mouseMove(2000,2000);    
     Runtime.getRuntime().exec("tskill explorer");
     BlockHandler.f.setState(0);
     sleep(400);
    } catch(Exception e){}
   }
  }catch(Exception e){}
 }
}

