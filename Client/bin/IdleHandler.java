import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class IdleHandler implements Runnable
{
 int port;

  public IdleHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_idlesocket=new Socket(Client.serverip,port);

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
    /* initialize the variables 
     while(true)
    {
     try
     {
       perform the operation
     }
     catch(Exception e)
     {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
      System.out.println(e);
     }

    }*/
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
