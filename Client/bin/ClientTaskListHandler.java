import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;

public class ClientTaskListHandler implements Runnable
{
 int port;

  public ClientTaskListHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);
    t.sleep(100);
    this.port=port;
    Client.cs_tasklistsocket=new Socket(Client.serverip,port);

    t.start();
   }
   catch(Exception e){System.out.println(e);}
  }

  public void run()
  {
   try
   {
    DataOutputStream out=new DataOutputStream(Client.cs_tasklistsocket.getOutputStream());
    new CommandReceiver(Client.cs_tasklistsocket);
    String oldtask=new String("");
    while(true)
    {
     try
     {
      String tasks[]= TaskManager.getTaskList();
      String temp=new String("");

      if(oldtask.length()!=0)
      {
        boolean exits=true;

        for(int i=0;i<tasks.length;i++)
        {
          String x=tasks[i].substring(0,tasks[i].indexOf(".exe")+1);

          if((oldtask.indexOf(x))==-1)
          {
           temp=tasks[i];
           exits=false;
          }
           else
           exits=true;

         if(!exits)
         {
          out.writeInt(1);
          out.flush();
          out.writeBytes(temp+"\n"); out.flush();
         }

       }

        oldtask="";
        for(int i=0;i<tasks.length;i++)
        {
         oldtask+=tasks[i];
        }
       
      }
      else
      {
       out.writeInt(tasks.length);
       out.flush();
       for(int i=0;i<tasks.length;i++)
       {
        out.writeBytes(tasks[i]+"\n");
        out.flush();
        oldtask+=tasks[i];
       }
      }
       Thread.currentThread().sleep(5000);
      }
      catch(Exception e)
      {
      System.out.println(e);
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      if(e.toString().indexOf("Connection")==-1)
      System.exit(0);

      }
     }                           
   }
   catch(Exception e)
    {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      if(e.toString().indexOf("Connection")==-1)
      System.exit(0);
      System.out.println(e);
    }
  }
 }


class CommandReceiver implements Runnable
{
 Socket cs;

 public CommandReceiver(Socket cs)
 {
  try
  {
   this.cs=cs;
   Thread t=new Thread(this);
   t.start();
  }
  catch(Exception e)
  {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
  }
 }                      

 public void run()
 {
  try
  {
   DataInputStream cmdin=new DataInputStream(cs.getInputStream());
   while(true)
   {
    try
    {
     String command=cmdin.readLine().trim();
     System.out.println(command);
     Runtime.getRuntime().exec(command);
    }
    catch(Exception e)
    {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      else
      System.exit(0);
    }
   }
  }
  catch(Exception e)
  {
      if(e.toString().indexOf("Connection")!=-1)
      ClientRSTC.destroyClient();
      if(e.toString().indexOf("Connection")==-1)
      System.exit(0);
  }
 }
}

                         
