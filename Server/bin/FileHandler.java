import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class FileHandler implements Runnable
{
 int port;

  public FileHandler(int port)
  {
   try
   {
    this.port=port;
    Client.cs_filesocket=new Socket(Client.serverip,port);
    Thread t=new Thread(this);
    t.start();
   }
   catch(Exception e){System.out.println(e);}
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
     catch(Exception e){System.out.println(e);}
    } */
   }
   catch(Exception e){}
  }
 }
