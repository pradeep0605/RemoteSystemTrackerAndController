
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

class RSTC
{
  
 public static void main(String args[])
 {
  try
  {
   new Splash();
   String s= new Password().s;
   if(s.equals("valid password"))
   {
    HandleAll ha = new HandleAll();
    /*
    File f=new File("c:\\WINDOWS\\system32\\trail.dll");
    if(!f.exists())
    {
     JOptionPane.showMessageDialog(null,"RSTC Software is not installed in your computer\nPlease get the software installed \n contact the Authors of the software","RSTC Information",1);
     System.exit(0);
    }
     
    DataInputStream in=new DataInputStream(new FileInputStream(f));
    String sa=in.readLine().trim();
    byte b[]=sa.getBytes();
    for( int i=0;i<b.length;i++)
    {
     b[i]=(byte)((int)b[i]-1);
     }
    long old=Long.parseLong(new String(b));
    long latest=new Date().getTime();
    long diff=latest-old;
    System.out.println(diff);
    if(diff>=1296000000)
    {
     JOptionPane.showMessageDialog(null,"Your software has expired \n please Contatct the authors and get the new one","RSTC information",1);
     System.exit(0);
    }
    else
    {
     int days=0;
     while(diff>86400000)
     {
      diff-=86400000;
      days++;
     }
     JOptionPane.showMessageDialog(null,"Trail pack expires in " + (15-days) +" days","RSTC information",1);
    }
    sa=in.readLine().trim();
    byte ba[]=sa.getBytes();
    for( int i=0;i<ba.length;i++)
    {
     ba[i]=(byte)((int)ba[i]-1);
     }
     sa=new String(ba);
     in.close();

     if(sa.equals(InetAddress.getLocalHost().toString().trim()))
     {
      System.out.println("Same system");
     }
     else
     {
      JOptionPane.showMessageDialog(null,"RSTC Software is not installed in your computer\nPlease get the software installed \n contact the Authors of the software","RSTC Information",1);
      System.exit(0);
     }
    */
    JMenuBar mb=new JMenuBar();
    JMenu file=new JMenu("File");
    JMenu boot = new JMenu("BOOT HANDLE ALL");
    JMenuItem shut    = new JMenuItem("SHUT DOWN ALL");
    JMenuItem restart = new JMenuItem("RESTART ALL");
    JMenuItem logoff  = new JMenuItem("LOG OFF ALL");
    shut.addActionListener(ha);
    restart.addActionListener(ha);
    logoff.addActionListener(ha);
    boot.add(shut);      boot.add(restart);      boot.add(logoff);
   
    JMenuItem item1=new JMenuItem("Exit RSTC",new ImageIcon("close.jpg"));
    JMenuItem item2=new JMenuItem("Shut down the server",new ImageIcon("Boot Handler turn off.jpg"));
    item2.addActionListener(new ShutDownHandler());
    file.add(item2);
    file.add(item1);
    item1.addActionListener(new ExitHandler());
    mb.add(file);
    //mb.add(boot);
    MainFrame.mainframe.setJMenuBar(mb);
    MainFrame.mainframe.setIconImage(Toolkit.getDefaultToolkit().getImage("RSTCICON.jpeg"));
    MainFrame.mainframe.setVisible(true);
    MainFrame.jsp=new JScrollPane(MainTab.clienttabs,22,32);
    MainFrame.c.add(MainFrame.jsp);
    MainFrame.mainframe.setExtendedState(MainFrame.mainframe.MAXIMIZED_BOTH);
    MainFrame.c.setBackground(Color.BLACK);
    new ServerIPBroadcaster();
    new Compit();
   }
   else
   {
    System.exit(0);
   }
  }catch(Exception e){System.out.println(e);}
 }
}

class ShutDownHandler implements ActionListener
{
 public void actionPerformed(ActionEvent ae)
 {
  try
  {
   YesNo y=new YesNo(MainFrame.mainframe,"Sure want to shut down");
   if(y.getOption())
   Runtime.getRuntime().exec("shutdown -s -f -t 00");
  }
  catch(Exception e){}
 }

}



class ExitHandler implements ActionListener    
{
 public void actionPerformed(ActionEvent ae)
 {
  try
  {
   System.exit(0);
  }
  catch(Exception e){}
 }
}

class PortManager
{
 static int Port=2001-25;
}



class Compit extends Thread
{
public Compit()
 {
  start();
 }
 public void run()
 {
  try
  {
   while(true)
   { 
    PortManager.Port+=25;
    ServerSocket SocketCompit=new ServerSocket(1999);
    Socket Response=SocketCompit.accept();
    DataInputStream portin=new DataInputStream(Response.getInputStream());
    String SystemName=portin.readLine().trim();
    DataOutputStream portout=new DataOutputStream(Response.getOutputStream());
    portout.writeInt(PortManager.Port);
    portout.flush();
    portout.close();
    System.out.println(SystemName + " has connected");
    SocketCompit.close();
    Response.close();
    portin.close();
    new ProxyServer(SystemName, PortManager.Port);
   }
  }
  catch(Exception e){System.out.println(e);}
 }
}




class MainTab
{

  static JTabbedPane clienttabs = new JTabbedPane();
}




class MainFrame 
{
  public static JFrame mainframe = new JFrame ("Server RSTC");
  public static Container c=mainframe.getContentPane();
  public static JScrollPane jsp;
}


class HandleAll implements ActionListener
{
  DatagramSocket dgs;
  int port=1990;
  String cmd= new String();
  public HandleAll()
  {
   try
   {
    dgs = new DatagramSocket(1990);
   }catch(Exception e){System.out.println(e);}
  }

 public void actionPerformed(ActionEvent ae)
 {
  try
  {
     if(ae.getActionCommand().equalsIgnoreCase("SHUT DOWN ALL"))
     {
      cmd = "shutdown -s -f -t 00";
      dgs.send(new DatagramPacket(cmd.getBytes(), 0, cmd.getBytes().length,  InetAddress.getByName("255.255.255.255"), port));
      System.out.println("After shutdown");
     } 

     if(ae.getActionCommand().equalsIgnoreCase("RESTART ALL"))
     {
      cmd = "shutdown -r -f -t 00 ";
      dgs.send(new DatagramPacket(cmd.getBytes(), 0, cmd.getBytes().length,  InetAddress.getByName("255.255.255.255"), port));
      System.out.println("After Restart");
     }

     if(ae.getActionCommand().equalsIgnoreCase("LOG OFF ALL"))
     {
      cmd = "shutdown -l -f -t 00";
      dgs.send(new DatagramPacket(cmd.getBytes(), 0, cmd.getBytes().length,  InetAddress.getByName("255.255.255.255"), port));
      System.out.println("After Logoff");
     }
   }catch(Exception e){System.out.println(e);}
 }
}
