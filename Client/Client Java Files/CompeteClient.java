import java.io.*;
import java.lang.*;
import java.net.*;

public class CompeteClient extends Thread
{
  static int port_seq;
  String serverip;

 public void run()
  {
   try
   {
   serverip = new ServerIPReceiver().getServerAddress();
   Socket cs = new Socket(serverip,1999);
   DataOutputStream clientout = new DataOutputStream(cs.getOutputStream());
   String name = InetAddress.getLocalHost().toString();
   name=name.substring(0,name.indexOf("/"));
   clientout.writeBytes(name+"\n");
   clientout.flush();
   DataInputStream sockin = new DataInputStream(cs.getInputStream());
   port_seq=sockin.readInt();
   clientout.close();
   sockin.close();
   cs.close();
   }catch(Exception e){System.out.println(e);}
   }
   public String[] getServerInfo()
   {
    try
    {
      start();
      join();
    }
    catch(Exception e){}
    String s[]=new String[2];
    s[0]=new String(serverip);
    s[1]=new Integer(port_seq).toString();
    return(s);
   }
}
