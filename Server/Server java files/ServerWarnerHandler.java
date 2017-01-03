import java.io.*;
import java.lang.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

class ServerWarnerHandler extends JDialog implements ActionListener
{
 String warningmsg;
 TextArea msg;

 public ServerWarnerHandler(Frame f,String title)
 {
  super(f,title,true);
  try
  {
   JPanel jp=(JPanel)getContentPane();
   jp.setLayout(new FlowLayout(FlowLayout.CENTER));
   jp.setPreferredSize(new Dimension(300,350));

   JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
   jp1.setPreferredSize(new Dimension(300,300));
   msg=new TextArea("",10,30,TextArea.SCROLLBARS_BOTH);
   Font msgfont=new Font("Dialog",Font.BOLD, 15);
   msg.setFont(msgfont);
   String temp="<html> <font color=red> <b> &lt; html &gt; </b> </fon> </html>";

   JLabel lb=new JLabel(temp);
   JLabel lb2=new JLabel(" can also be used");
   JButton send=new JButton("Send");
   JButton cancel=new JButton("Cancel");
   send.addActionListener(this);
   cancel.addActionListener(this);
   jp1.add(lb);
   jp1.add(lb2);
   jp1.add(msg);
   jp1.add(send);
   jp1.add(cancel);
   jp1.setVisible(true);
   jp.add(jp1);
   jp.setVisible(true);
   setSize(300,350);
   setResizable(false);
   setVisible(true);
  }
  catch(Exception e){}
 }

 public void actionPerformed(ActionEvent ae)
 {
   String cmd=ae.getActionCommand().trim();

   if(cmd.equals("Send"))
   {
    warningmsg=msg.getText().trim();
    dispose();
   }

   if(cmd.equals("Cancel"))
   {
    dispose();
   }
 }

 public String getWarningMessage()
 {
   return warningmsg;
 }

}


