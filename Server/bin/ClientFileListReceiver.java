import java.io.*;
import java.lang.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

public class ClientFileListReceiver extends JDialog implements ActionListener, TreeExpansionListener, Runnable
{
 DataInputStream filelistin;
 DataOutputStream filelistout;
 DataInputStream sockin;
 DataOutputStream fileout, sockout; 
 Socket client;
 String send_file;

 Thread t;
 
 JTree root;
 JPanel mainpanel;
 JScrollPane jsp;
 JButton send; JButton receive; JButton cancel;
 String clientcomputer;  String operation=new String("");
 DefaultMutableTreeNode mycomputer;
 int firstlayercount=0, secondlayercount=0;
 JPanel buttonpanel;

  public ClientFileListReceiver(Frame f, String s, Socket filelistsocket, String computer, Socket filesocket)
  {
   super(f, s, true);
   try                                                     
   {
    this.filelistin=new DataInputStream(filelistsocket.getInputStream());
    this.filelistout=new DataOutputStream(filelistsocket.getOutputStream());
    this.client= filesocket;
    sockin =  new DataInputStream(client.getInputStream());
    fileout = new DataOutputStream(client.getOutputStream());
    sockout = new DataOutputStream(client.getOutputStream());
    clientcomputer=computer;
    t=new Thread(this);
    t.start();
   }catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {

   String cmd=ae.getActionCommand();
   if(cmd.equals("Cancel"))
   dispose();

   if(cmd.equalsIgnoreCase("Delete"))
   {
    try
    {
     String str =  root.getSelectionPath().toString().trim();
     System.out.println(str);
     str = str.substring(1, str.length()-1);
     str = str.substring(str.lastIndexOf(",")+1, str.length());
     str = str.substring(str.indexOf(" ")+1, str.length());
     filelistout.writeBytes("delete"+"\n");
     filelistout.flush();
     System.out.println("After removing recket puckas  "+str);

     String filepath = str;
     sockout.writeBytes(filepath+"\n");
     sockout.flush();
     System.out.println(filepath);

    }catch(Exception e){System.out.println(e);}
   }

   if(cmd.equalsIgnoreCase("Send"))
   {
    try
    {
      String filename = send_file;

      File f = new File(filename);

      RandomAccessFile raf = new RandomAccessFile(filename, "r");
      long length = raf.length();
      raf.close();

      String dest = root.getSelectionPath().toString().trim();
      dest = dest.substring(1, dest.length()-1);
      dest = dest.substring(dest.lastIndexOf(",")+1, dest.length());
      dest = dest.substring(dest.indexOf(" ")+1, dest.length());


      dest = dest.trim();

      filename  = filename.substring(filename.lastIndexOf("\\")+1, filename.length());

      dest = dest+"\\"+filename;

      System.out.println(dest);

      filelistout.writeBytes("receive"+"\n");
      sockout.writeBytes(dest+"\n");

      DataInputStream filein = new DataInputStream(new FileInputStream(f));

      sockout.writeLong(length);
      sockout.flush();

      for(long i=0; i<length; i++)
      {
       sockout.writeByte(filein.readByte());
       sockout.flush();
      }
      filein.close();
    }catch(Exception e){System.out.println(e);}
   }

   
  if(cmd.equals("Receive"))
  {
   try
   {
    String filename;
    filename=root.getSelectionPath().toString().trim();
    filename=filename.substring(filename.lastIndexOf(",")+1, filename.length()-1);
    filename=filename.substring(filename.indexOf(" ")+1,filename.length());
    System.out.println(filename);
    filelistout.writeBytes("send"+ "\n");
    fileout.writeBytes(filename+ "\n");

    Receiver r = new Receiver();
    filename=filename.substring(filename.lastIndexOf("\\")+1, filename.length());
    String str = r.destination+"\\"+filename;
    File file = new File(str);
    System.out.println(str);
    
    DataOutputStream fileout = new DataOutputStream(new FileOutputStream(file));
    long length = sockin.readLong();
    System.out.println(length);
    for(long i=0; i<length; i++)
    {
     fileout.writeByte(sockin.readByte());
     fileout.flush();
    }
    sockin.close();
    fileout.close();
   }catch(Exception e){System.out.println(e);}
  }
 }



  public void run()
  {
   try
   {
    setSize(400,400);
    mainpanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    mainpanel.setPreferredSize(new Dimension(400,400));
    setContentPane(mainpanel);
    buttonpanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
    buttonpanel.setPreferredSize(new Dimension(400,100));
    mainpanel.setVisible(true);

    FileOperationChooser opchoose=new FileOperationChooser(this,"Choose the operation");
    operation = new String(opchoose.getOperation());
    System.out.println(operation);

    if(operation.equalsIgnoreCase("Receive"))
    {
     try{
      showRootTree("Receive");
     }catch(Exception e){System.out.println(e);}
    }


    if(opchoose.getOperation().equalsIgnoreCase("Send"))
    {
     try
     {
      Sender s = new Sender();
      send_file = s.source;
      if(!send_file.equals(""))
      showRootTree("Send");
      }catch(Exception e){System.out.println(e);}
    }

    if(operation.equalsIgnoreCase("Delete"))
    {
     try{
      showRootTree("Delete");
     }catch(Exception e){System.out.println(e);}
    }

   }catch(Exception e){System.out.println(e);}
 } 



  public void showRootTree(String type)
  {
   try
   {
     System.out.println("Comming to file receive");
     filelistout.writeBytes("root\n");
     filelistout.flush();
     System.out.println("Sending root");
     receive=new JButton(type);
     cancel=new JButton("Cancel");
     receive.addActionListener(this);
     cancel.addActionListener(this);
     buttonpanel.add(receive);     buttonpanel.add(cancel);
     buttonpanel.setVisible(true);
     firstlayercount=filelistin.readInt();
     mycomputer=new DefaultMutableTreeNode(clientcomputer + " Computer");
     DefaultMutableTreeNode drives[]=new DefaultMutableTreeNode[firstlayercount];
     StringTokenizer st=new StringTokenizer(filelistin.readLine().trim(), ">");
     for(int i=0;i<firstlayercount;i++)
      {
       drives[i]=new  DefaultMutableTreeNode(st.nextToken());
       System.out.println(drives[i].toString());
       secondlayercount=filelistin.readInt();
       System.out.println("Comming to the for loop for drives "+ (i+1));
       DefaultMutableTreeNode innerfile[]=new DefaultMutableTreeNode[secondlayercount];
       if(secondlayercount>0)
       {
        StringTokenizer st1=new StringTokenizer(filelistin.readLine().trim(), ">");
        
        for(int j=0;j<secondlayercount;j++)
        {
         innerfile[j]=new DefaultMutableTreeNode(st1.nextToken());
         System.out.println(innerfile[j].toString());
         drives[i].add(innerfile[j]);
        }
       } 
       mycomputer.add(drives[i]);
       System.out.println("Comming to the for loop for drives closing");
      }

      System.out.println("Comming to after drives");
      root=new JTree(mycomputer);
      root.addTreeExpansionListener(this);

      jsp=new JScrollPane(root,22,32);
      jsp.setPreferredSize(new Dimension(380,300));
      jsp.setVisible(true);
      mainpanel.add(jsp);
      mainpanel.add(buttonpanel);
      mainpanel.setVisible(true);
    //  setResizable(false);
      setLocation(300,200);
      setVisible(true);
     }catch(Exception e){System.out.println(e);}
  }



  public void treeExpanded(TreeExpansionEvent te)
  {
   try
   {

    jsp.remove(root);
    mainpanel.remove(jsp);
    mainpanel.remove(buttonpanel);

    String path = te.getPath().toString();
    StringBuffer sb;

    path=path.substring(1,path.length()-1);
    path=path.substring(path.indexOf(",")+1,path.length());
   // System.out.println(path.trim());

    int count=0;
    for(int i=0; i<path.length(); i++)
    {
     if(path.indexOf("\\ ")!=-1)
      count++;
    }

    sb= new StringBuffer(path);

    for(int j=0; j<count; j++)
    {
     sb.replace(sb.indexOf("\\") , sb.indexOf("\\ ")+1 , "\\");
    }

    path = sb.substring(0,sb.length());
    System.out.println(path.trim());
    filelistout.writeBytes(path.trim() + "\n");
    filelistout.flush();
 
       firstlayercount=0;
       secondlayercount=0;
       mycomputer=new DefaultMutableTreeNode(path);

       firstlayercount=filelistin.readInt();
       if(firstlayercount>=1)
       {
        DefaultMutableTreeNode level1[]=new DefaultMutableTreeNode[firstlayercount];
        for(int i=0;i<firstlayercount;i++)
        {
          String s=filelistin.readLine().trim();
          System.out.println(s);
          level1[i]=new DefaultMutableTreeNode(s);
        }

        for(int j=0;j<firstlayercount;j++)
        {
         secondlayercount=filelistin.readInt();
         if(secondlayercount>=1)
         {
          DefaultMutableTreeNode level2[]=new DefaultMutableTreeNode[secondlayercount];
          for(int k=0;k<secondlayercount;k++)
          {
           String s=filelistin.readLine().trim();
           level2[k]=new DefaultMutableTreeNode(s);
           level1[j].add(level2[k]);
           System.out.println(s);
          }
         }
         mycomputer.add(level1[j]);
        }
       }

         
      System.out.println("Comming to after drives and loops");
      root=new JTree(mycomputer);
      root.addTreeExpansionListener(this);
      jsp=new JScrollPane(root,22,32);
      jsp.setPreferredSize(new Dimension(380,300));
      jsp.setVisible(true);
      mainpanel.add(jsp);
      buttonpanel.setVisible(true);
      mainpanel.add(buttonpanel);
      mainpanel.setVisible(true);
      //setResizable(false);
      setLocation(300,200);
      setVisible(true);
    }catch(Exception e){}
  }


  public void treeCollapsed(TreeExpansionEvent te)
  {}

}










class FileOperationChooser extends JDialog implements ActionListener
{
  String operation=new String("");

  public FileOperationChooser(JDialog j,String s)
  {
   super(j,s,true);
   try
   {
    setSize(300,100);
    JPanel jp=new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
    setContentPane(jp);
    setBackground(new Color(238,238,238));
    jp.setBackground(new Color(238,238,238));
    jp.setPreferredSize(new Dimension(300,75));
    JButton rb1=new JButton("Send   ");
    JButton rb2=new JButton("Receive");
    JButton rb3=new JButton("Delete");
    JButton cancel=new JButton("Cancel");
    rb1.addActionListener(this);
    rb2.addActionListener(this);
    rb3.addActionListener(this);
    cancel.addActionListener(this);

    jp.add(rb1);  jp.add(rb2); jp.add(rb3);
    jp.add(cancel);
    jp.setVisible(true);
    //setResizable(false);
    setLocation(400,300);
    setVisible(true);

   }
   catch(Exception e){}
  }

  public void actionPerformed(ActionEvent ae)
  {
    String cmd=ae.getActionCommand().trim();
    if(cmd.equals("Send"))
    {
     operation=new String("Send");
     System.out.println(operation);
     dispose();
    }

   if(cmd.equals("Receive"))
   {
    operation=new String("Receive");
    System.out.println(operation);
    dispose();
   }

   if(cmd.equals("Delete"))
   {
    operation=new String("Delete");
    System.out.println(operation);
    dispose();
   }

   if(cmd.equals("Cancel"))
   {
    operation=" ";
    dispose();
   }
  }

  public String getOperation()
  {
  System.out.println(operation);
  return operation;
  } 
 }





class Receiver 
{
 public String destination;
 JFileChooser jfc = new JFileChooser();
 int i;

 public Receiver()
 {
   i = jfc.showSaveDialog(MainFrame.mainframe);
   if(i==0)
   {
   destination=jfc.getSelectedFile().toString().trim();
   destination = destination.substring(0,destination.lastIndexOf("\\"));
  }

   else
  {
   destination="";
  }
  }
}




class Sender 
{
 public String source;
 JFileChooser jfc = new JFileChooser();
 int i;

 public Sender()
 {
   i = jfc.showOpenDialog(MainFrame.mainframe);
   if(i==0)
    source=jfc.getSelectedFile().toString().trim();
   else
   source="";
  }
}
