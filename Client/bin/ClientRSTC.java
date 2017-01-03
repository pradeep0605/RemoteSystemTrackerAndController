import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class ClientRSTC
{
  public static void destroyClient()
  {
   try
   {
    Runtime.getRuntime().exec("ClientRSTC");
    System.exit(0);
   }catch(Exception e){}
  }
 
 public static void main(String args[])throws Exception
 {
  try
  {
   new Client();
  }
  catch(Exception e){}
 }
}


class Client extends Thread
{
  static  int port_sequence;
  static  String serverip;
  static  DataOutputStream out;
  static  Socket cs_bootsocket;
  static  Socket cs_filesocket;
  static  Socket cs_filelistsocket;
  static  Socket cs_warnersocket;
  static  Socket cs_mouseordinatessocket;
  static  Socket cs_mousebuttonssocket;
  static  Socket cs_mousescrollsocket; 
  static  Socket cs_keyboardsocket;
  static  Socket cs_authenticationsocket;
  static  Socket cs_idlesocket;
  static  Socket cs_blocksocket;
  static  Socket cs_imagesocket;
  static  Socket cs_tasklistsocket;

  public Client()
  {
   try
   {
    start();
   }catch(Exception e){}
  }

  public void run()
  {
   try
   {
    String serverinfo[]=new CompeteClient().getServerInfo();
    serverip=serverinfo[0];
    port_sequence=Integer.parseInt(serverinfo[1]);

    out=new DataOutputStream(new FileOutputStream("c:\\Documents and Settings\\All Users\\Documents\\simple.rstc"));
    out.writeBytes(new  String("You can't modify"));


    new BootHandler(port_sequence++);
    sleep(200);
    new FileHandler(port_sequence++);
    sleep(200);
    new FileListHandler(port_sequence++);
    sleep(200);
    new WarnerHandler(port_sequence++);
    sleep(200);
    new MouseOrdinatesHandler(port_sequence++);
    sleep(200);
    new MouseButtonsHandler(port_sequence++);
    sleep(200);
    new MouseScrollHandler(port_sequence++);
    sleep(200);
    new KeyBoardHandler(port_sequence++);
    sleep(200);
    new AuthenticationHandler(port_sequence++);
    sleep(200);
    new IdleHandler(port_sequence++);
    sleep(200);
    new BlockHandler(port_sequence++);
    sleep(200);
    new ClientImageHandler(port_sequence++);
    sleep(200);
    new ClientTaskListHandler(port_sequence++);

    System.out.println("All the sockets have been accepted by the server");
   }
   catch(Exception e)
   {
    System.out.println(e);
    if(e.toString().indexOf("Connection reset")!=-1)
    ClientRSTC.destroyClient();
    if(e.toString().indexOf("NullPointer")!=-1)
    System.exit(0);
    }
  }
}
