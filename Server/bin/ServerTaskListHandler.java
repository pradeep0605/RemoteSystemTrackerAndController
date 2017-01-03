import java.io.*;
import java.lang.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ServerTaskListHandler extends JPanel implements ActionListener, Runnable
{
  String items[], new_item;
  int port;
  ServerSocket ss_tasksocket;
  Socket cs_tasksocket;
  DataInputStream cs_sockin;
  DataOutputStream cs_sockout;
  List jl;
  JButton end_process;
  JButton new_process;
  JPanel parent;
  JPanel JListPanel=new JPanel(new BorderLayout(1,1));
  JPanel JButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
  Thread t;

  public void stop()
  {
   try
   {
    t.stop();
   }
   catch(Exception e){}
  }

  public ServerTaskListHandler(int port, JPanel parent)
  {
   try
   {
    this.port = port;
    setPreferredSize(new Dimension(PanelConstants.TaskListPanelWidth,PanelConstants.TaskListPanelHeight));
    JListPanel.setPreferredSize(new Dimension(PanelConstants.TaskListPanelWidth-200, (int)(PanelConstants.TaskListPanelHeight/ 1.5)));
    setBackground(Color.BLACK);
    JListPanel.setBackground(Color.BLACK);
    JButtonPanel.setBackground(Color.BLACK);
    jl = new List(25);
    //jl.setMultipleSelections(true);
    JListPanel.add(jl);
    add(JListPanel);



    JButtonPanel.setPreferredSize(new Dimension(PanelConstants.TaskListPanelWidth,(int) (PanelConstants.TaskListPanelHeight/ 6)));
    end_process = new JButton(new ImageIcon("EndProcessUp.jpg"));
    new_process = new JButton(new ImageIcon("NewProcessUp.jpg"));
    new_process.setMargin(new Insets(0,0,0,0));
    new_process.setBackground(Color.BLACK);
    new_process.setRolloverIcon(new ImageIcon("NewProcessDown.jpg"));
    new_process.setPressedIcon(new ImageIcon("NewProcessPressed.jpg"));
    new_process.setActionCommand("NEW PROCESS");
    new_process.setMnemonic('n');
    

    end_process.setMargin(new Insets(0,0,0,0));
    end_process.setBackground(Color.BLACK);
    end_process.setRolloverIcon(new ImageIcon("EndProcessDown.jpg"));
    end_process.setPressedIcon(new ImageIcon("EndProcessPressed.jpg"));
    end_process.setActionCommand("END PROCESS");
    end_process.addActionListener(this);
    end_process.setMnemonic('e');

    new_process.addActionListener(this);
    JButtonPanel.add(end_process);
    JButtonPanel.add(new_process);

    add(JButtonPanel);
    parent.add(this);
    this.parent=parent;
    
    t = new Thread(this);
    t.start();
   }
   catch(Exception e){System.out.println("Exception in TaskLISt handler : " + e);}
  }

  public JPanel getServerTaskListPanel()
  {
  return this.parent;
  }

  public void run()
  {
   int total_tasks;
   try
    {
    ss_tasksocket = new ServerSocket(this.port);
    cs_tasksocket = ss_tasksocket.accept();
    cs_sockin = new DataInputStream(cs_tasksocket.getInputStream());
    cs_sockout = new DataOutputStream(cs_tasksocket.getOutputStream());
    jl.add("AUTHORS:");
    jl.removeAll();
    while(true)
     {
      try
      {
        total_tasks=cs_sockin.readInt();

        for(int i=0; i<total_tasks; i++)
        jl.addItem(cs_sockin.readLine().trim());
        Thread.currentThread().sleep(5000);
      } catch(Exception e){System.out.println("Exception in TaskLISt handler : " + e);}
    }
   }catch(Exception e){System.out.println("Exception in TaskLISt handler : " + e);}
  }

 public void actionPerformed(ActionEvent ae)
 {
   try
   {
     String cmd = ae.getActionCommand();


      if(cmd.equals("END PROCESS"))
      {
       YesNo y=new YesNo(MainFrame.mainframe,"Do you want to end the Process ?");
       if(y.getOption())
       {
        items=jl.getSelectedItems();
        for(int i=0; i<items.length; i++)
        {
         cs_sockout.writeBytes("tskill "+items[i].substring(0,items[i].indexOf("."))+"\n");
         cs_sockout.flush();
         jl.remove(items[i]);
        }
       }
      } 
     
    if(cmd.equals("NEW PROCESS"))
    {
     try
     {
      DialogKing dk = new DialogKing(MainFrame.mainframe, "Process Creator");
      new_item = dk.getCmd();
      if(!(new_item.equals("")||new_item==null))
      {
       cs_sockout.writeBytes(new_item+"\n");
       cs_sockout.flush();
      }
     }catch(Exception e){System.out.println("Exception that we are finding is :"+e+ " this.");}
    }

   }catch(Exception e) {System.out.println("Exception in TaskLISt handler : " + e);}
 }
}


class DialogKing extends JDialog implements ActionListener
{
  String cmd;
  JTextField jt;
  JButton ok, cancel;
  public DialogKing(JFrame f,String title)
  {
    super(f,title,true);
   try
   {
    JPanel jp = new JPanel();
    setContentPane(jp);
    jp.setPreferredSize(new Dimension(300,120));
    jp.setLayout(new FlowLayout(FlowLayout.CENTER));
    jp.add(new JLabel("Enter the new process:"));
    jt = new JTextField(28);
    jp.add(jt);
    ok = new JButton("OK");
    cancel = new JButton("Cancel");
    ok.addActionListener(this);
    cancel.addActionListener(this);
    jp.add(ok); jp.add(cancel);
    jp.setVisible(true);
    setResizable(false);
    setSize(320,120);
    setVisible(true);
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
   if(ae.getActionCommand().equalsIgnoreCase("OK"))
   {
    cmd=jt.getText();
    dispose();
   }
   else
   {
    cmd=new String("");
    dispose();
   }
  }

  public String getCmd()
  {
   return cmd;
  }
}
