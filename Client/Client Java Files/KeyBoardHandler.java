import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class KeyBoardHandler implements Runnable
{
 int port;
 DataInputStream keyin;

  public KeyBoardHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_keyboardsocket= new Socket(Client.serverip,port);
    keyin=new DataInputStream(Client.cs_keyboardsocket.getInputStream());

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
    String press_release=new String("");
    while(true)
    {

     try
     {
       press_release=keyin.readLine().trim();
       int key=keyin.readInt();
      try
      {
      
       if(press_release.equals("press"))
        {
         r.keyPress(key);         
        }

       if(press_release.equals("release"))
       {
         r.keyRelease(key);
       }

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
