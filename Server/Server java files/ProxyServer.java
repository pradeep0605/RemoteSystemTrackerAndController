import java.io.*;
import java.lang.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

class PanelConstants
{
	 public static final double IMAGE_WIDTH=1.5753846;
	 public static final double IMAGE_HEIGHT=1.25;
	 public static final double TASK_LIST_WIDTH=2.9257143;
	 public static final double TASK_LIST_HEIGHT=1.25;
	 public static final double BUTTON_WIDTH=1.024;
	 public static final double BUTTON_HEIGHT=7.5;
	 public static final Dimension SCREEN_SIZE= Toolkit.getDefaultToolkit().getScreenSize();
	 public static final int ImagePanelWidth= (int) (SCREEN_SIZE.width/IMAGE_WIDTH);
	 public static final int ImagePanelHeight= (int) (SCREEN_SIZE.height/IMAGE_HEIGHT);
	 public static final int TaskListPanelWidth= (int)(SCREEN_SIZE.width / TASK_LIST_WIDTH);
	 public static final int TaskListPanelHeight= (int)(SCREEN_SIZE.height / TASK_LIST_HEIGHT);
	 public static final int ButtonPanelWidth = (int)(SCREEN_SIZE.width / BUTTON_WIDTH);
	 public static final int ButtonPanelHeight = (int)(SCREEN_SIZE.width / BUTTON_HEIGHT);
}





class ProxyServer implements Runnable,ActionListener
{
 String systemname, cmd;
 int port_sequence=0;
 JPanel  client_panel;
 ServerImageHandler  sih;
 ServerTaskListHandler stlh;

 Thread t;

 DataInputStream authin;
 DataInputStream filelistin;
 DataOutputStream filelistout;
 DataOutputStream blockout;

 ServerSocket bootsocket;
 ServerSocket filesocket;
 ServerSocket filelistsocket;
 ServerSocket warnersocket;
 ServerSocket mouseordinatessocket;
 ServerSocket mousebuttonssocket;
 ServerSocket mousescrollsocket;
 ServerSocket keyboardsocket;
 ServerSocket authenticationsocket;
 ServerSocket idlesocket;
 ServerSocket blocksocket;

 Socket cs_bootsocket;
 Socket cs_filesocket;
 Socket cs_filelistsocket;
 Socket cs_warnersocket;
 Socket cs_mouseordinatessocket;
 Socket cs_mousebuttonssocket;
 Socket cs_mousescrollsocket;
 Socket cs_keyboardsocket;
 Socket cs_authenticationsocket;
 Socket cs_idlesocket;
 Socket cs_blocksocket;


 public JPanel JButtonPanel= new JPanel(new FlowLayout(FlowLayout.CENTER));

 JButton bootbutton=new JButton(new ImageIcon("BootHandlerUp.jpg"));
 JButton filebutton=new JButton(new ImageIcon("FileTransferUp.jpg"));
 JButton warnerbutton=new JButton(new ImageIcon("WarnerHandlerUp.jpg"));
 JButton completectrlbutton=new JButton(new ImageIcon("TakeCompleteControlUp.jpg"));
 JButton authenticationbutton = new JButton();
 JButton removeclient= new JButton(new ImageIcon("RemoveClientUp.jpg"));
 JButton blockimaging=new JButton(new ImageIcon("BlockImageUp.jpg"));
 JButton blockclient=new JButton(new ImageIcon("BlockClientUp.jpg"));
 
 
  public void stop()
 {
  try
  {
    System.out.println("Stopping the proxy server");
    t.stop();
  }
  catch(Exception e){System.out.println("Exception in proxyserver :");}
 }

 public ProxyServer(String systemname,int port_sequence)
 {
  this.systemname=systemname;
  this.port_sequence=port_sequence;
  client_panel = new JPanel();
  client_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
  client_panel.setDoubleBuffered(true);
  client_panel.setPreferredSize(PanelConstants.SCREEN_SIZE);
  
  JButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
  JButtonPanel.setBackground(Color.BLACK);
  JButtonPanel.setPreferredSize(new Dimension(PanelConstants.ButtonPanelWidth,PanelConstants.ButtonPanelHeight));
  
  bootbutton.setRolloverIcon(new ImageIcon("BootHandlerDown.jpg"));
  bootbutton.setPressedIcon(new ImageIcon("BootHandlerPressed.jpg"));
  filebutton.setRolloverIcon(new ImageIcon("FileTransferDown.jpg"));
  filebutton.setPressedIcon(new ImageIcon("FileTransferPressed.jpg"));
  warnerbutton.setRolloverIcon(new ImageIcon("WarnerHandlerDown.jpg"));
  warnerbutton.setPressedIcon(new ImageIcon("WarnerHandlerPressed.jpg"));
  removeclient.setRolloverIcon(new ImageIcon("RemoveClientDown.jpg"));
  removeclient.setPressedIcon(new ImageIcon("RemoveClientPressed.jpg"));
  blockimaging.setRolloverIcon(new ImageIcon("BlockImageDown.jpg"));
  blockimaging.setPressedIcon(new ImageIcon("BlockImagePressed.jpg"));
  blockclient.setRolloverIcon(new ImageIcon("BlockClientDown.jpg"));
  blockclient.setPressedIcon(new ImageIcon("BlockClientPressed.jpg"));
  completectrlbutton.setRolloverIcon(new ImageIcon("TakeCompleteControlDown.jpg"));
  completectrlbutton.setPressedIcon(new ImageIcon("TakeCompleteControlPressed.jpg"));
  
  bootbutton.setActionCommand("Boot Handler");
  filebutton.setActionCommand("File Transfer");
  warnerbutton.setActionCommand("Send Warning Message");
  removeclient.setActionCommand("Remove Client");
  blockimaging.setActionCommand("Block Image");
  blockclient.setActionCommand("Block Client");
  completectrlbutton.setActionCommand("Take Complete Control");

  bootbutton.setMnemonic('b');
  filebutton.setMnemonic('f');
  warnerbutton.setMnemonic('w');
  removeclient.setMnemonic('r');
  blockimaging.setMnemonic('i');
  blockclient.setMnemonic('c');
  completectrlbutton.setMnemonic('p');



  bootbutton.setBackground(Color.BLACK);
  filebutton.setBackground(Color.BLACK);
  warnerbutton.setBackground(Color.BLACK);
  removeclient.setBackground(Color.BLACK);
  blockimaging.setBackground(Color.BLACK);
  blockclient.setBackground(Color.BLACK);
  completectrlbutton.setBackground(Color.BLACK);

  bootbutton.setMargin(new Insets(0,0,0,0));
  filebutton.setMargin(new Insets(0,0,0,0));
  warnerbutton.setMargin(new Insets(0,0,0,0));
  removeclient.setMargin(new Insets(0,0,0,0));
  blockimaging.setMargin(new Insets(0,0,0,0));
  blockclient.setMargin(new Insets(0,0,0,0));
  completectrlbutton.setMargin(new Insets(0,0,0,0));
  
  
  bootbutton.addActionListener(this);
  filebutton.addActionListener(this);
  warnerbutton.addActionListener(this);
  completectrlbutton.addActionListener(this);
  authenticationbutton.addActionListener(this);
  removeclient.addActionListener(this);
  blockimaging.addActionListener(this);
  blockclient.addActionListener(this);
   
  
  JButtonPanel.add(bootbutton);
  JButtonPanel.add(filebutton);
  JButtonPanel.add(warnerbutton);
  JButtonPanel.add(completectrlbutton);
  JButtonPanel.add(blockimaging);
  JButtonPanel.add(blockclient);
  JButtonPanel.add(removeclient);


  t=new Thread(this);
  t.start();
 }

/*PERFORMING APPRORIATE OPERATIONS TO THE RESPECTIVE BUTTONS PRESSED*/

 public void actionPerformed(ActionEvent ae)
 {
   cmd=ae.getActionCommand();
  try
  {
   if(cmd.equals("Block Image"))
   {
    sih.suspend();
    blockimaging.setIcon(new ImageIcon("UnBlockImageUp.jpg"));
    blockimaging.setRolloverIcon(new ImageIcon("UnBlockImageDown.jpg"));
    blockimaging.setPressedIcon(new ImageIcon("UnBlockImagePressed.jpg"));
    blockimaging.setActionCommand("UnBlock Image");
   }


   if(cmd.equals("UnBlock Image"))
   {
    sih.resume();
    blockimaging.setIcon(new ImageIcon("BlockImageUp.jpg"));
    blockimaging.setRolloverIcon(new ImageIcon("BlockImageDown.jpg"));
    blockimaging.setPressedIcon(new ImageIcon("BlockImagePressed.jpg"));

    blockimaging.setActionCommand("Block Image");
   }

   if(cmd.equals("Block Client"))
   {
     JOptionPane.showMessageDialog(null,"After flushing","RSTC",2);
    blockout.writeBytes("Block\n");
    blockout.flush();

    blockclient.setIcon(new ImageIcon("UnBlockClientUp.jpg"));
    blockclient.setRolloverIcon(new ImageIcon("UnBlockClientDown.jpg"));
    blockclient.setPressedIcon(new ImageIcon("UnBlockClientPressed.jpg"));
    blockclient.setActionCommand("UnBlock Client");
   }

   if(cmd.equals("UnBlock Client"))
   {
    blockout.writeBytes("UnBlock\n");
    blockout.flush();

    blockclient.setIcon(new ImageIcon("BlockClientUp.jpg"));
    blockclient.setRolloverIcon(new ImageIcon("BlockClientDown.jpg"));
    blockclient.setPressedIcon(new ImageIcon("BlockClientPressed.jpg"));

    blockclient.setActionCommand("Block Client");
   }

   if(cmd.equals("Boot Handler"))
   {
    DataOutputStream sockout = new DataOutputStream(cs_bootsocket.getOutputStream());
    BootDialog bd = new BootDialog(MainFrame.mainframe, "RSTC");
    String option, force, time;
    option=bd.getSelected();
    force="-f ";
    time="-t 00 ";
    String bootcmd = option+force+time;
    System.out.println("shutdown "+bootcmd);
    sockout.writeBytes("shutdown "+bootcmd+"\n");
    sockout.flush();
   }

   if(cmd.equals("File Transfer"))
   {
    try
    {
      new ClientFileListReceiver(MainFrame.mainframe,"Choose List",cs_filelistsocket,systemname,cs_filesocket);
    }catch(Exception e){}
   }


   if(cmd.equals("Send Warning Message"))
   {
    try
    {
     DataOutputStream warnerout=new DataOutputStream(cs_warnersocket.getOutputStream());
     ServerWarnerHandler swh=new ServerWarnerHandler(MainFrame.mainframe,"Warning Message Sender");
     String msg=swh.getWarningMessage();

     if(msg!=null)
     {
      warnerout.writeBytes(msg + "\n");
      warnerout.flush();
      System.out.println(msg);
     }
    }
    catch(Exception e){System.out.println("Exception in proxyserver :");}
   }

   
   if(cmd.equals("Take Complete Control"))
   {
     try
     {
      sih.takeControl(cs_mouseordinatessocket,cs_mousebuttonssocket, cs_keyboardsocket);
     }
     catch(Exception e){}
   }

   if(cmd.equals("Remove Client"))
   {
    try
    {
      YesNo y=new YesNo(MainFrame.mainframe,"Sure you want to remove Client ?");
      if(y.getOption())
      removeClient();
    }
    catch(Exception e){}
   }

   if(cmd.equals("Authentication"))
   {
   }
  }
  catch(Exception e){System.out.println("Proxyser server:");}
 }

   public void removeClient()
    {
     try
     {
      sih.stop();
      stlh.stop();
      MainTab.clienttabs.remove(client_panel);

      System.out.println("Stopping the proxy server");
      cs_bootsocket.close();
      cs_filesocket.close();
      cs_filelistsocket.close();
      cs_warnersocket.close();
      cs_mouseordinatessocket.close();
      cs_mousebuttonssocket.close();
      cs_mousescrollsocket.close();
      cs_keyboardsocket.close();
      cs_authenticationsocket.close();
      cs_idlesocket.close();
      cs_blocksocket.close();

      bootsocket.close();
      filesocket.close();
      filelistsocket.close();
      warnersocket.close();
      mouseordinatessocket.close();
      mousebuttonssocket.close();
      mousescrollsocket.close();
      keyboardsocket.close();
      authenticationsocket.close();
      idlesocket.close();
      blocksocket.close();
      t.stop();
      authin.close();
      

      System.out.println("Removing Proxy ..... removed");
     }
     catch(Exception e){System.out.println("Exception in proxserver :" + e);}
    }

 public void run()                             
 {
  try
  {
   bootsocket           = new ServerSocket(port_sequence++);
   filesocket           = new ServerSocket(port_sequence++);
   filelistsocket       = new ServerSocket(port_sequence++);
   warnersocket         = new ServerSocket(port_sequence++);
   mouseordinatessocket = new ServerSocket(port_sequence++);
   mousebuttonssocket   = new ServerSocket(port_sequence++);
   mousescrollsocket    = new ServerSocket(port_sequence++);
   keyboardsocket       = new ServerSocket(port_sequence++);
   authenticationsocket = new ServerSocket(port_sequence++);
   idlesocket           = new ServerSocket(port_sequence++);
   blocksocket          = new ServerSocket(port_sequence++);
   
   cs_bootsocket           = bootsocket.accept();
   cs_filesocket           = filesocket.accept();
   cs_filelistsocket       = filelistsocket.accept();
   cs_warnersocket         = warnersocket.accept();
   cs_mouseordinatessocket = mouseordinatessocket.accept();
   cs_mousebuttonssocket   = mousebuttonssocket.accept();
   cs_mousescrollsocket    = mousescrollsocket.accept();
   cs_keyboardsocket       = keyboardsocket.accept();
   cs_authenticationsocket = authenticationsocket.accept();
   cs_idlesocket           = idlesocket.accept();
   cs_blocksocket          = blocksocket.accept();

   sih=new ServerImageHandler(port_sequence++,systemname,client_panel);

   client_panel.setBackground(Color.BLACK);
   client_panel=sih.getServerImagePanel();

   stlh=new ServerTaskListHandler(port_sequence++, client_panel);
   client_panel=stlh.getServerTaskListPanel();

   System.out.println("ImageHandler and TaskListHandler has been called");

   client_panel.add(JButtonPanel);

   client_panel.setVisible(true);

   MainTab.clienttabs.addTab(systemname,client_panel);

   authin=new DataInputStream(cs_authenticationsocket.getInputStream());
   blockout=new DataOutputStream(cs_blocksocket.getOutputStream());
   
   while(true)
   {
    try
    {
    String s=authin.readLine().trim();
    }
     catch(Exception e)
     {
      System.out.println("Exception in proxyserver " + e);
      removeClient();
      authin.close();
      new Notify(MainFrame.mainframe, systemname + " client has been removed due to  client program termination ");
      break;
     }

   }

   }
   catch(Exception e){System.out.println("Exception in proxyserver" + e);}
 }
}


class Notify extends JDialog implements ActionListener, Runnable
{
  String s;
  Notify(Frame f,String s)
  {
    super(f,s,true);
    try
    {
     Thread t=new Thread(this);
     t.start();
     this.s=s;
    }catch(Exception e){}
  }  

  public void run()
  {
   try
   {
    JPanel jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
    setContentPane(jp);
    jp.add(new JLabel(s));
    JButton b1=new JButton("OK");
    b1.addActionListener(this);
    jp.add(b1);
    jp.setVisible(true);
    setSize(300,200);
    setLocation(400,300);
    setVisible(true);
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
  dispose();
  }
}
