import java.io.*;
import java.lang.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

class ServerImageHandler extends JPanel implements Runnable
{
 static boolean sus_res=false;
 String img_path, systemname;
 Image img,img2;
 int port;
 ServerSocket imagehandlingsocket;
 Socket       cs_imagehandlingsocket;
 DataInputStream imagein;
 JPanel parent;
 Thread t;

   public void suspend()
   {
    try
    {
     t.suspend();
    }catch(Exception e){}
   }

   public void resume()
   {
    try
    {
     t.resume();
    }catch(Exception e){}
   }


   public void stop()
   {
    try
    {
     t.stop();
    }
   catch(Exception e){}
   }

   public void takeControl(Socket cs_mouseordinatessocket,Socket cs_mousebuttonssocket, Socket cs_keyboardsocket)
   {
    t.suspend();
    new TakeCompleteControl(this,imagein,systemname,cs_mouseordinatessocket, cs_mousebuttonssocket, cs_keyboardsocket);
   }

   public ServerImageHandler(int port, String systemname, JPanel parent)
   {
     try
     { 
      this.port=port;
      this.systemname=systemname;
      setLayout(new FlowLayout());
      setPreferredSize(new Dimension(PanelConstants.ImagePanelWidth, PanelConstants.ImagePanelHeight));
      setVisible(true);
      t=new Thread(this);
      parent.add(this);
      this.parent=parent;
      
      t.start();

     }catch(Exception e){System.out.println("Exception in imagehandling :" + e);}
   }

   public JPanel getServerImagePanel()
   {
   return this.parent;
   }

   public void run()
   {
    try
    {
     imagehandlingsocket=new ServerSocket(port);
     cs_imagehandlingsocket=imagehandlingsocket.accept();
     imagein=new DataInputStream(cs_imagehandlingsocket.getInputStream());

     img_path="\\\\"+systemname+"\\shareddocs\\RSTC";
     long l;

     while(true)
     {
      try
      {
      /* reading the image from the cient system and displying it using
         method repaint()
       */
       l=imagein.readLong();
       System.out.println("Before getting image " + l);
       img = Toolkit.getDefaultToolkit().getImage(img_path+"\\Screen"+l+".jpeg");
       img2 = img.getScaledInstance(PanelConstants.ImagePanelWidth,PanelConstants.ImagePanelHeight, 4);
       repaint();
       Thread.currentThread().sleep(800);
      }
      catch(Exception e){System.out.println("Exception in imagehandling :" + e);}
     }
    }catch(Exception e){System.out.println("Exception in imagehandling :" + e);}
   }

   public void paint(Graphics g)
   {
    try
    {
     g.drawImage(img2,0,0,this);
    }
    catch(Exception e){System.out.println("Image Still has not been found");}
   }
}









class TakeCompleteControl extends JDialog implements MouseMotionListener, MouseListener, KeyListener
{
  ServerImageHandler siht;
  Image img;
  String systemname;
  ClientDesktopPanel cdp;
  DataInputStream in;
  String img_path;
  Socket cs_mouseordinatessocket, cs_mousebuttonssocket, cs_keyboardsocket;
  DataOutputStream ordinatesout, buttonsout, keyout;
  String quit=new String("");

  public void mouseClicked(MouseEvent me)
  {
  }

  public void mousePressed(MouseEvent me)
  {
   try
   {
    ordinatesout.writeInt(me.getX());
    ordinatesout.writeInt(me.getY());
    ordinatesout.flush();

    int button=me.getButton();
    buttonsout.writeBytes("press\n");
    if(button==1)
    buttonsout.writeInt(1<<4);
    if(button==2)
    buttonsout.writeInt(1<<3);
    if(button==3)
    buttonsout.writeInt(1<<2);
    buttonsout.flush();
   }catch(Exception e){}
  }

  public void mouseReleased(MouseEvent me)
  {
   try
   {
    ordinatesout.writeInt(me.getX());
    ordinatesout.writeInt(me.getY());
    ordinatesout.flush();

    int button=me.getButton();
    buttonsout.writeBytes("release\n");
    if(button==1)
    buttonsout.writeInt(1<<4);
    if(button==2)
    buttonsout.writeInt(1<<3);
    if(button==3)
    buttonsout.writeInt(1<<2);
    buttonsout.flush();
   }catch(Exception e){}
  }

  public void mouseEntered(MouseEvent me)
  {
  }

  public void mouseExited(MouseEvent me)
  {
  }

  public void mouseDragged(MouseEvent me)
  {
   try
   {
    ordinatesout.writeInt(me.getX());
    ordinatesout.writeInt(me.getY());
    ordinatesout.flush();
   }catch(Exception e){}
   }

  public void mouseMoved(MouseEvent me)
  {
   try
   {
    ordinatesout.writeInt(me.getX());
    ordinatesout.writeInt(me.getY());
    ordinatesout.flush();
   }catch(Exception e){System.out.println("Exception in sending the oridnates " + e);}
  }

  public void keyPressed(KeyEvent ke)
  {
   try
   {
    
    keyout.writeBytes("press"+"\n");
    keyout.flush();
    keyout.writeInt(ke.getKeyCode());
    keyout.flush();
    
    }catch(Exception e){System.out.println(e);}
 }   

  public void keyReleased(KeyEvent ke)
  {
   try
   {
    keyout.writeBytes("release"+"\n");
    keyout.flush();
    keyout.writeInt(ke.getKeyCode());
    keyout.flush();
    }catch(Exception e){System.out.println(e);}
   }

   public void keyTyped(KeyEvent ke)
   {
    quit+=ke.getKeyChar();
    if(quit.indexOf("rstcquit")!=-1)
    {
     cdp.stop();
     ServerImageHandler.sus_res=true;
     siht.resume();
     setVisible(false);
     quit="";
    }

   }

  TakeCompleteControl (ServerImageHandler siht,DataInputStream in,String systemname,Socket cs_mouseordinatessocket,Socket cs_mousebuttonssocket, Socket cs_keyboardsocket)
  {
   super(MainFrame.mainframe,"RSTC",true);
   try
   {
    this.siht=siht;
    addMouseMotionListener(this);
    addMouseListener(this);
    setUndecorated(true);
    setDefaultCloseOperation(0);
    addKeyListener(this);
    this.in=in;
    this.cs_mouseordinatessocket=cs_mouseordinatessocket;
    this.cs_mousebuttonssocket=cs_mousebuttonssocket;
    this.cs_keyboardsocket=cs_keyboardsocket;
    this.systemname=systemname;
    ordinatesout=new DataOutputStream(this.cs_mouseordinatessocket.getOutputStream());
    buttonsout = new DataOutputStream(this.cs_mousebuttonssocket.getOutputStream());
    keyout= new DataOutputStream(this.cs_keyboardsocket.getOutputStream());

    setLocation(0,0);
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    img_path="\\\\"+systemname+"\\shareddocs\\RSTC";
    long l;
    cdp=new ClientDesktopPanel(systemname,in);
    getContentPane().add(cdp);
    setVisible(true);
   }catch(Exception e){}
  }
}
    
  
class ClientDesktopPanel extends JPanel implements Runnable
{
 String systemname;
 DataInputStream in;
 Thread t;
 String img_path;
 Image img;
 long l=0;

 public void stop()
 {
  try
  {
   t.stop();
  }catch(Exception e){}
 }
 public ClientDesktopPanel(String systemname,DataInputStream in)
 {
   this.systemname=systemname;
   this.in=in;
   t=new Thread(this);
   t.start();
 }

 public void run()
 {
    while(true)
    {
     try
     {
      img_path="\\\\"+systemname+"\\shareddocs\\RSTC";      
      l=in.readLong();
      img=Toolkit.getDefaultToolkit().getImage(img_path+"\\Screen"+l+".jpeg");
      repaint();
      t.sleep(800);
     }
     catch(Exception e){}
    } 
 }

  public void paint(Graphics g)
  {
   try
   {
   g.drawImage(img,0,0,this);
   }catch(Exception e){}
  }
} 
