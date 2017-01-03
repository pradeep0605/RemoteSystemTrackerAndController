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
    DataOutputStream ImageSocOut = new DataOutputStream(Client.cs_imagesocket.getOutputStream());
    Robot imagerobot=new Robot();
    BufferedImage screenimg;
    long counter=1;

    while(true)
    {
     try
     {
      /* Capture the screen */
      screenimg= imagerobot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
      //File imagefile=new File("C:\\Documents and Settings\\All Users\\Documents\\RSTC\\Screen" + (counter) + ".jpeg");
      //imagefile.setHidden(true);
      /* Write the screen shot to ScreenX.jpeg file in the current folder*/
      ImageIO.write(screenimg,"jpeg", new File("Screen" + counter + ".jpeg"));

      File imagefile = new File("Screen" + counter + ".jpeg");
      /* Open the file again and read all the bytes from the jpeg file */
      int  FileLength = 0;
      FileLength = (int) (imagefile.length());
      System.out.println("Image size = " + FileLength);
      byte imagearray[] = new byte[FileLength];
      FileInputStream shfin = new FileInputStream(imagefile);
      shfin.read(imagearray);
      
      /* Write the length of the file to server and then write the
       * Complete content of the image to server through socket stream. */

      ImageSocOut.writeInt(FileLength);
      ImageSocOut.flush();
      ImageSocOut.write(imagearray, 0, FileLength);
      ImageSocOut.flush();
      shfin.close();
      counter++;

      if(counter >=10)
      {
       long temp=counter-9;
       File f=new File("Screen" + (temp) + ".jpeg");
       f.delete();
      }
      Thread.currentThread().sleep(500);
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
