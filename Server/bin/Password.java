import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


class Password extends JDialog implements ActionListener
{
  static JLabel label= new JLabel ("Enter the Password");
  static JPasswordField pass=new JPasswordField(10);
  static JButton ok=new JButton("OK");
  static JButton cancel=new JButton("Cancel");
  int count=0;
  static String s="";


 public  Password()
  {
   super(new JFrame(),"RSTC",true);
   try
   {
    setSize(300,100);
    setLocation(350,300);
    setResizable(false);
    setDefaultCloseOperation(0);
    setBackground(new Color(255,255,255,0));
    getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

    pass.setEchoChar('?');
    ok.addActionListener(this);
    cancel.addActionListener(this);
    JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel jp2=new JPanel(new FlowLayout(FlowLayout.CENTER));
    jp1.setPreferredSize(new Dimension(300,25));
    jp2.setPreferredSize(new Dimension(300,50));
    jp1.add(label);
    jp1.add(pass);
    jp2.add(ok);
    jp2.add(cancel);
    jp1.setVisible(true);
    jp2.setVisible(true);
    getContentPane().add(jp1);
    getContentPane().add(jp2);
    setVisible(true);
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   String cmd=ae.getActionCommand().trim();
   String password=new String("");

   if(cmd.equals("OK"))
   {
     password=pass.getText().trim();
      if(!password.equals("RZSPTVCS"))
      {
        if(count<3)
       {
        count+=1;
        JOptionPane.showMessageDialog(null,"Please Re-Enter the correct Password " + count,"RSTC",1);
        pass.setText("");
       }
       else
       {
        dispose();
        System.exit(0);
       }
      }
      else
      {
       s="valid password";
       dispose();
      }

    }
    else
    {
     System.exit(0); 
    }
   }
  }











