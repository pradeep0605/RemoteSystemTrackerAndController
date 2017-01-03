import java.io.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.*;


class ServerSetup extends JFrame implements ActionListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jp2;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Cancel");
  Image img;

  public ServerSetup()
  {
   super("Server Setup"); 
   setIconImage(Toolkit.getDefaultToolkit().getImage("rstcicon.ico"));
   try
   {
   File f=new File("c:\\WINDOWS\\System32\\trail.dll");
   if (f.exists())
   {
    JOptionPane.showMessageDialog(null,"Software already exits","RSTC",1);
    System.exit(0);
   }
   else
   {
    try
    {
     DataOutputStream out=new DataOutputStream(new FileOutputStream(f));
     String s="";
     s=new Long(new Date().getTime()).toString();
     byte b[];
     b=s.getBytes();
     for(int i=0;i<s.length();i++)
     {
      byte b1=(byte)((int)b[i]+1);
      out.writeByte(b1);
     }
      out.writeBytes("\n");
      out.flush();
      s=InetAddress.getLocalHost().toString();
     b=s.getBytes();
     for(int i=0;i<s.length();i++)
     {
      byte b1=(byte)((int)b[i]+1);
      out.writeByte(b1);
     }
      out.writeBytes("\n");
      out.flush();
      out.close();

    }catch(Exception e){}
   }

   setSize(600,490);
   setLocation(200,150);
   setDefaultCloseOperation(0);
   b1.addActionListener(this);
   b2.addActionListener(this);
   MainPanel2=(JPanel)getContentPane();
   MainPanel.setPreferredSize(new Dimension(600,500));
   MainPanel.setVisible(true);
   MainPanel.setBackground(Color.WHITE);
   MainPanel2.setBackground(Color.WHITE);
   jp1.setPreferredSize(new Dimension(580,50));
   jp1.add(new JLabel("<html> <font color=blue size=5> Remote System Tracket and Controller (Server) setup Wizard  </font> </html>"));
   jp1.setBackground(Color.WHITE);

   jp2=new ImagePanel();

   jp3.setPreferredSize(new Dimension(600,50));
   jp3.setBackground(Color.WHITE);
   jp3.add(b1);
   jp3.add(b2);

   MainPanel.add(jp1);
   MainPanel.add(jp2);
   MainPanel.add(jp3);

   jp1.setVisible(true);
   jp2.setVisible(true);
   jp3.setVisible(true);


   MainPanel2.add(MainPanel);
   MainPanel.setVisible(true);

   MainPanel2.setVisible(true);
   setResizable(false);

   setVisible(true);
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().trim().equals("Next >>"))
   {
    setVisible(false);
    new ServerSetup2();
   }
   else
   {
    YesNo y=new YesNo(this,"Sure you want to Quit?");
    if(y.getOption())
    System.exit(0);
   }
  }
   
  public static void main(String args[])throws Exception
  {
   try
   {
    new ServerSetup();
   }catch(Exception e){}
  }
}


class ImagePanel extends JPanel
{
 Image img;

 public ImagePanel()
 {
  setPreferredSize(new Dimension(600,360));
  img=Toolkit.getDefaultToolkit().getImage("rstcicon.jpg");
  img=img.getScaledInstance(600,360,4);
  repaint();
  setVisible(true);
 }


  public void paint(Graphics g)
  {
   try
   {
    g.drawImage(img,0,0,this);
   }catch(Exception e){System.out.println(e);}
  }
}


class ServerSetup2 extends JFrame implements ActionListener, ItemListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jp2;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Cancel");
  Image img;
  TextArea tx=new TextArea(20,80);
  JCheckBox cb=new JCheckBox("I have read the information and I Accept to install the software");

  public ServerSetup2()
  {
   super("RSTC Setup");
   setIconImage(Toolkit.getDefaultToolkit().getImage("rstcicon.ico"));
   setSize(600,490);
   setLocation(200,150);
   setDefaultCloseOperation(0);
   b1.addActionListener(this);
   b2.addActionListener(this);
   b1.setEnabled(false);
   cb.addItemListener(this);
   cb.setBackground(Color.WHITE);
   tx.setEditable(false);
   tx.setText(" Remote System Tracker and Controller  is a  newtwork oriented software \n"+
               "where this server software is used to track and control all the client \n" +
               "which are connected over lan. \n\n"+

               "   This server software is capable of doing the following operations \n"+
               "      * First and most important, showing the desktop view of the connected clients \n"+
               "      * Next is to show the task or the process that are running in the client systems\n"+
               "      * And provided ability to close those task or process in the client\n"+
               "      * Server is also capable of running new tasks or processes in the client\n"+
               "          Note: Only the process that can be run in command prompt and if given the\n"+
               "                full physical path of the .exe that exists in the client system\n"+
               "      * Server is provided with the ability to shutdown, restart and logoff the connected clients one or all at a time\n"+
               "      * Server is capable of sending, receiving files to and from the client without the intervention of the client, and also delete files at the client\n"+
               "      * Server is also capable of blocking the client when found suspicious, where client is unable to do any GUI operations\n"+
               "      * Server is capable of blocking the viewing of the client desktop, thus by inreasing the server's performance\n"+
               "      * Ultimate and the utmost feature is the complete control, it has the following feature\n"+
               "               ==> When you take complete control of client, keyboard, mouse and display of the client will be under the control of the server\n"+
               "               ==> You can do all the work that can done in the client by sitting at the server\n"+
               "               ==> If you move the mouse or press keyboard the buttons the actions will be performed at client not the server because you are working at client, but sitting in the server\n"+
               "               ==> You can use this complete control to install software at client systems, by sitting at the server and this is only one simple example\n"+
               "               ==> This feature extends the thinking of computer working\n"+
               "               ==> To quit from complete control and just press \"rstcquit \" \n\n\n"+
               "This first version of the software  needs the following requirements\n"+
               "       * Processor with more than 2.4 Ghz\n"+
               "       * RAM with 1 Gb or more\n"+
               "       * Minimum 200 mb of free space in 'c' drive\n"+
               "       * Display dirvers installed\n"+
               "       * Network drives and network adapters installed with ip address configured\n"+
               "       * Customized products are also available, if needed contact the authors of the software\n\n\n"+

               "If you have agreed with the above requirement, you can install the software");







   MainPanel2=(JPanel)getContentPane();
   MainPanel.setPreferredSize(new Dimension(600,500));
   MainPanel.setVisible(true);
   MainPanel.setBackground(Color.WHITE);
   MainPanel2.setBackground(Color.WHITE);
   jp1.setPreferredSize(new Dimension(580,50));
   jp1.add(new JLabel("<html> <font color=blue size=7>  Information About RSTC </font> </html>"));
   jp1.setBackground(Color.WHITE);

   jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
   jp2.setPreferredSize(new Dimension(600,360));
   jp2.setBackground(Color.WHITE);
   jp2.add(tx);
   jp2.add(cb);


   jp3.setPreferredSize(new Dimension(600,50));
   jp3.setBackground(Color.WHITE);
   jp3.add(b1);
   jp3.add(b2);

   MainPanel.add(jp1);
   MainPanel.add(jp2);
   MainPanel.add(jp3);

   jp1.setVisible(true);
   jp2.setVisible(true);
   jp3.setVisible(true);


   MainPanel2.add(MainPanel);
   MainPanel.setVisible(true);

   MainPanel2.setVisible(true);
   setResizable(false);
   setVisible(true);
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().trim().equals("Next >>"))
   { setVisible(false);
     new ServerSetup3();
   }
   else
   {
    YesNo y=new YesNo(this,"Sure yout want to Quit?");
    if(y.getOption())
    System.exit(0);
   }
  }

  public void itemStateChanged(ItemEvent ie)
  {
   if(ie.getStateChange()==1)
   b1.setEnabled(true);
   else
   b1.setEnabled(false);
  }
}   






class ServerSetup3 extends JFrame implements ActionListener, ItemListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jpprogress;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  static JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Cancel");
  static JProgressBar jpb;
  static String pbstring=new String("0%");
  static JLabel copyfile=new JLabel("Preparing to copy..");
  static JLabel percent=new JLabel("");
  CopyFiles cf;
  public ServerSetup3()
  {
   super("RSTC Setup");
   setIconImage(Toolkit.getDefaultToolkit().getImage("rstcicon.ico"));
   setSize(600,490);
   setLocation(200,150);
   setDefaultCloseOperation(0);
   b1.addActionListener(this);
   b2.addActionListener(this);
   b1.setEnabled(false);

   MainPanel2=(JPanel)getContentPane();
   MainPanel.setPreferredSize(new Dimension(600,500));
   MainPanel.setVisible(true);
   MainPanel.setBackground(Color.WHITE);
   MainPanel2.setBackground(Color.WHITE);
   jp1.setPreferredSize(new Dimension(580,50));
   jp1.add(new JLabel("<html> <font color=blue size=7>  Installing Server RSTC... </font> </html>"));
   jp1.setBackground(Color.WHITE);

   JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
   jp2.add(percent);
   jp2.add(copyfile);
   jp2.setPreferredSize(new Dimension(600,30));
   jp2.setBackground(Color.WHITE);

   jpb=new JProgressBar(0,100);
   jpb.setString(pbstring);
   jpprogress=new JPanel(new GridLayout(1,1));//new FlowLayout(FlowLayout.LEFT));
   jpprogress.setPreferredSize(new Dimension(590,30));
   jpprogress.setBackground(Color.WHITE);
   jpprogress.add(jpb);


   jp3.setPreferredSize(new Dimension(600,50));
   jp3.setBackground(Color.WHITE);
   jp3.add(b1);
   jp3.add(b2);
   Empty empty=new Empty();
   empty.setPreferredSize(new Dimension(600,300));
   empty.setBackground(Color.WHITE);

   MainPanel.add(jp1);
   MainPanel.add(jp2);
   MainPanel.add(jpprogress);
   MainPanel.add(empty);
   MainPanel.add(jp3);

   jp1.setVisible(true);
   jp2.setVisible(true);
   jpprogress.setVisible(true);
   empty.setVisible(true);
   jp3.setVisible(true);


   MainPanel2.add(MainPanel);
   MainPanel.setVisible(true);

   MainPanel2.setVisible(true);
   setResizable(false);
   setVisible(true);
   try
   {
   cf= new CopyFiles();
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().trim().equals("Next >>"))
   {
    setVisible(false);
    new FinishDialogServer(this);
   }
   else
   {
    YesNo y=new YesNo(this,"Sure yout want to Quit?");
    if(y.getOption())
    {
     String filename="c:\\Program Files\\Server RSTC\\";
     JOptionPane.showMessageDialog(null,"Cancelling installation will remove the copied files ","RSTC",1);
     cf.stop();
     File f=new File(filename);
     File fl[]=f.listFiles();
     for(int i=0;i<fl.length;i++)
     {
      fl[i].delete();
     }
     File f1=new File("C:\\Documents and Settings\\All Users\\Start Menu\\Programs\\Server RSTC");
     File f2=new File("C:\\Documents and Settings\\All Users\\Start Menu\\Programs\\Server RSTC\\RSTC.lnk");
     f2.delete();
     f1.delete();
     System.exit(0);
    }
   }
  }

  public void itemStateChanged(ItemEvent ie)
  {
   if(ie.getStateChange()==1)
   b1.setEnabled(true);
   else
   b1.setEnabled(false);
  }
}   

class Empty extends JPanel
{
 Image img;

  public Empty()
  {
   try
   {
    img=Toolkit.getDefaultToolkit().getImage("rstcicon.jpg");
    img=img.getScaledInstance(600,300,4);
    repaint();
   }catch(Exception e){}
  }

  public void paint(Graphics g)
  {
  try
  {
   g.drawImage(img,0,0,this);
  }catch(Exception e){}
  }

 }
                      
class FinishDialogServer extends JDialog implements ActionListener
{
  boolean run=false;
  boolean restart=false;
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
  JButton b1=new JButton ("Finish");
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JCheckBox cb1=new JCheckBox("Run Server RSTC");
  JCheckBox cb2=new JCheckBox("Restart System");
  public FinishDialogServer(Frame f)
  {
   super(f,"RSTC",true);
   try
   {
    setSize(350,250);
    setLocation(400,300);
    MainPanel.setPreferredSize(new Dimension(300,200));
    MainPanel.setBackground(Color.WHITE);
    setContentPane(MainPanel);
    b1.addActionListener(this);
    jp1.setPreferredSize(new Dimension(300,66));
    jp1.setBackground(Color.WHITE);
    jp2.setPreferredSize(new Dimension(300,66));
    jp2.setBackground(Color.WHITE);
    jp3.setPreferredSize(new Dimension(300,66));
    jp3.setBackground(Color.WHITE);

    jp1.add(cb1);
    jp2.add(cb2);
    jp3.add(b1);
    MainPanel.add(jp1);
    MainPanel.add(jp2);
    MainPanel.add(jp3);
    jp1.setVisible(true);
    jp2.setVisible(true);
    jp3.setVisible(true);
    MainPanel.setVisible(true);
    setVisible(true);
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   try
   {
    if(cb1.isSelected()==true)
    Runtime.getRuntime().exec("c:\\Program Files\\Server RSTC\\RSTC.exe");
    if(cb2.isSelected()==true)
    Runtime.getRuntime().exec("shutdown -r -f -t 00");
    System.exit(0);
   }catch(Exception e){}
  }
}
