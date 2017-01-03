import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;


public class MouseOrdinatesHandler implements Runnable
{
 int port;

  public MouseOrdinatesHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_mouseordinatessocket=new Socket(Client.serverip,port);

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
    Robot r=new Robot();
    int x;
    int y;               
    DataInputStream ordinatesin=new DataInputStream(Client.cs_mouseordinatessocket.getInputStream());
    while(true)
    {
     try
     {
      x=ordinatesin.readInt();
      y=ordinatesin.readInt();
      r.mouseMove(x,y);
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
