import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class MouseButtonsHandler implements Runnable
{
 int port;

  public MouseButtonsHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_mousebuttonssocket=new Socket(Client.serverip,port);

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
    DataInputStream in=new DataInputStream(Client.cs_mousebuttonssocket.getInputStream());
    int button;
    String press_release=new String("");
    Robot r=new Robot();
    while(true)
    {
     try
     {
      press_release=in.readLine().trim();
      button=in.readInt();

      if(press_release.equals("press"))
      r.mousePress(button);

      if(press_release.equals("release"))
      r.mouseRelease(button);
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
