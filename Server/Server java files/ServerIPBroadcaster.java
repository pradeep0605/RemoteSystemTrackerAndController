import java.net.*;
import java.io.*;
import java.lang.*;

class ServerIPBroadcaster extends Thread
{

  DatagramSocket ds;
  String ipaddress;

  public ServerIPBroadcaster()
  { 
   start();
  }

    public void run()
    {
      try
      {
       ds = new DatagramSocket(4998);
       ipaddress = InetAddress.getLocalHost().toString();
       ipaddress = ipaddress.substring(ipaddress.indexOf("/")+1);
       System.out.println("IPaddress = " + ipaddress);
      while(true)
      {
       try
       {
         ds.send(new DatagramPacket(ipaddress.getBytes(), 0, ipaddress.getBytes().length, InetAddress.getByName("255.255.255.255"), 1998));
       }catch(Exception e){System.out.println(e);}
       Thread.currentThread().sleep(3000);
      }
      }
      catch(Exception e){}
    }
}  
