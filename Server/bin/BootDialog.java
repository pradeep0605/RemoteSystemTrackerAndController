import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class BootDialog extends JDialog implements ActionListener
{
  JButton cancel, restart, logoff, shutdown;
  String selected, cmd;
  Image backimg;

  public BootDialog(Frame parent, String title) 
  {
   super(parent, "RSTC Boot Handler" , true);
   try
   {
    JPanel c =new JPanel();
    setContentPane(c);
    setBackground(new Color(68,68,255));
    c.setBackground(new Color(68,68,255));
    c.setPreferredSize(new Dimension(300,150));
    c.setLayout(new FlowLayout(FlowLayout.CENTER));

    shutdown = new JButton(new ImageIcon("boot handler turn off.jpg"));
    logoff   = new JButton(new ImageIcon("boot handler log off.jpg"));
    restart  = new JButton(new ImageIcon("boot handler restart.jpg"));
    cancel   = new JButton("Cancel");

    restart.setActionCommand("restart");
    logoff.setActionCommand("logoff");
    shutdown.setActionCommand("shutdown");
    cancel.setActionCommand("cancel"); 

    shutdown.addActionListener(this);
    restart.addActionListener(this);
    logoff.addActionListener(this);
    cancel.addActionListener(this);

    shutdown.setVisible(true);
    restart.setVisible(true);
    logoff.setVisible(true);
    cancel.setVisible(true);

    JPanel jp=new JPanel();
    jp.setLayout(new FlowLayout(FlowLayout.CENTER));
    jp.setPreferredSize(new Dimension(300,25));
    jp.setBackground(new Color(68,68,255));
    c.add(jp);
    c.add(shutdown);
    c.add(restart);
    c.add(logoff);
    c.add(new JLabel("     "));
    c.add(new JLabel("                                                                  "));
    c.add(cancel);

    c.setVisible(true);
    

    setLocation(400,300);
    setSize(300,150);
    setResizable(false);
    setVisible(true);
   }catch(Exception e){System.out.println(e);}
  }

   public void actionPerformed(ActionEvent ae)
   {
    cmd=ae.getActionCommand();

    if(cmd.equals("restart"))
    {
      YesNo y=new YesNo(this,"Yes  /  No");
      if(y.getOption())
      {
       selected=" -r ";
       dispose();
      }
      else
       dispose();
    }


    if(cmd.equals("logoff"))
    {
      YesNo y=new YesNo(this, "Yes  /  No");
      if(y.getOption())
      {
       selected=" -l ";
       dispose();
      }
      else
      dispose();
    }


    if(cmd.equals("shutdown"))
    {
      YesNo y=new YesNo(this,"Yes  /  No");
      if(y.getOption())
      {
       selected=" -s ";
       dispose();
      }
      else
       dispose();
    }

    if(cmd.equals("cancel"))
    dispose();
  }

  public String getSelected()
  {
   return selected;
  }
}


class YesNo extends JDialog implements ActionListener
{

 boolean  yn=false;

 public YesNo(JDialog f, String msg1)
 {
  super(f, msg1, true);
  try
  {
   JPanel jp=(JPanel)getContentPane();
   jp.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
   jp.setBackground(new Color(210,223,248));
   setSize(200,100);                   
   setBackground(new Color(210,223,248));
   setResizable(false);
   setLocation(440,350);
   JButton b1=new JButton("Yes");
   JButton b2=new JButton("No");
   b1.addActionListener(this);
   b2.addActionListener(this);
   b1.setVisible(true);
   b2.setVisible(true);
   
   jp.add(b1);
   jp.add(b2);

   jp.setVisible(true);
   setVisible(true);
  } 
  catch(Exception e){}
 }

 public YesNo(Frame f, String msg1)
 {
  super(f, msg1, true);
  try
  {
   JPanel jp=(JPanel)getContentPane();
   jp.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
   jp.setBackground(new Color(210,223,248));
   setSize(200,100);                   
   setBackground(new Color(210,223,248));
   setResizable(false);
   setLocation(440,350);
   JButton b1=new JButton("Yes");
   JButton b2=new JButton("No");
   b1.addActionListener(this);
   b2.addActionListener(this);
   b1.setVisible(true);
   b2.setVisible(true);
   
   jp.add(b1);
   jp.add(b2);

   jp.setVisible(true);
   setVisible(true);
  } 
  catch(Exception e){}
 }

 public void actionPerformed(ActionEvent ae)
 {
  String cmd=ae.getActionCommand().trim();
  if(cmd.equals("Yes"))
  {
   yn=true;
   dispose();
  }

  if(cmd.equals("No"))
  {
   yn=false;
   dispose();
  }
 }

 public boolean getOption()
 {
   return yn;
 }
} 
