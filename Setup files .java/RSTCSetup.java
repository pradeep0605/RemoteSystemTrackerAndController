import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


public class  RSTCSetup extends JFrame implements ActionListener
{
 JButton b1=new JButton("Install ServerRSTC");
 JButton b2=new JButton("Install ClientRSTC");
 JButton b3=new JButton("Cancel");
 JPanel jp=new JPanel(new FlowLayout(FlowLayout.CENTER));

   public RSTCSetup()
   {
    super("RSTC Setup");
    setSize(200,150);
    setResizable(false);
    setLocation(400,300);
    setContentPane(jp);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    jp.add(b1);
    jp.add(b2);
    jp.add(b3);
    jp.setVisible(true);
    setVisible(true);
   }

    public void actionPerformed(ActionEvent ae)
    {
     try
     {
      if(ae.getActionCommand().equals("Install ServerRSTC"))
      {
       Runtime.getRuntime().exec("ServerSetup");
       setVisible(false);
      }
       else
       if(ae.getActionCommand().equals("Install ClientRSTC"))
       {
        Runtime.getRuntime().exec("ClientSetup");
        setVisible(false);
       }
       else
       System.exit(0);
     }catch(Exception e){}
    }

     public static void main(String args[])throws Exception
     {
      new RSTCSetup();
     }

}
