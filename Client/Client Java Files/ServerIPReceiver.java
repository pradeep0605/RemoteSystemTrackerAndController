import java.net.*;
import java.io.*;
import java.lang.*;

public class ServerIPReceiver extends Thread
{
 public static int clientPort=1998;
 public static int buffer_size=1024;
 public static DatagramSocket ds;
 public static byte buffer[]=new byte[buffer_size];
 static String serveripaddress;

  public void run()
  {
   try
   {
    ds=new DatagramSocket(clientPort);
    DatagramPacket p=new DatagramPacket(buffer,buffer.length);
    ds.receive(p);
    serveripaddress=new String(p.getData(),0,p.getLength());
    System.out.println("From RD:"+serveripaddress);
   }
   catch(Exception e)
    {
     System.out.println("EXCEPTION:"+e);
    }
  }

   public String getServerAddress()throws Exception
  {
   start();
   join();
   ds.close();
   return (serveripaddress);
  } 
}

