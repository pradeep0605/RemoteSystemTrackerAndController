import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;


public class WarnerHandler implements Runnable
{
 int port;

  public WarnerHandler(int port)
  {
   try
   {
    this.port=port;
    Client.cs_warnersocket=new Socket(Client.serverip,port);
    Thread t=new Thread(this);
    t.start();
   }
   catch(Exception e){}
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
     catch(Exception e){}
    }  */
   }
   catch(Exception e){}
  }
 }
