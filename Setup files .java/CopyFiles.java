import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class CopyFiles extends Thread
{
  int inc=0;
  public CopyFiles()
  {
    try
    {
     start();
    }catch(Exception e){}
  }

   public void run()
   {
    try
    {
     sleep(4000);
     ServerSetup3.copyfile.setText("Installing at the default location   \"c:\\Program Files\\Server RSTC\"");
     sleep(3000);
     File f=new File("c:\\Program Files\\Server RSTC");
     f.mkdirs();
     File f1=new File("Server Installation files\\");
     File list[]=f1.listFiles();
     ServerSetup3.jpb.setMinimum(0);
     ServerSetup3.jpb.setMaximum(list.length);
     float one=100;
     float two=list.length;
     float per=one/two;
     float per2=per;
     System.out.println(per);
     System.out.println(two);
     for(int i=0;i<list.length;i++)
     {
       inc++;
       ServerSetup3.jpb.setValue(inc);
       ServerSetup3.jpb.setString((int)inc+"%");
       ServerSetup3.percent.setText("Progress "+(int)per+"%");
       String filename=list[i].toString().trim();
       filename=filename.substring(filename.lastIndexOf("\\",filename.length()));
       sleep(300);
       ServerSetup3.copyfile.setText("Copying file  : " + filename);
       copy(list[i]);
       per+=per2;
     }
     File shortcut=new File("C:\\Documents and Settings\\All Users\\Start Menu\\Programs\\Server RSTC\\");
     shortcut.mkdirs();
     DataInputStream in=new DataInputStream(new FileInputStream("Server Installation files\\RSTC.lnk"));
     DataOutputStream out=new DataOutputStream(new FileOutputStream(shortcut.toString().trim()+"\\RSTC.lnk"));
     RandomAccessFile r=new RandomAccessFile("Server Installation files\\RSTC.lnk","r");
     int l=(int)r.length();
     r.close();
     for(int j=0;j<l;j++)
     {
      out.writeByte(in.readByte());
      out.flush();
     }
     out.close();
     in.close();

     ServerSetup3.copyfile.setText("All the files have been copied");
     ServerSetup3.b1.setEnabled(true);
    }catch(Exception e){}
   }
    
    public void copy(File f)
    {
     String path="c:\\Program Files\\Server RSTC\\";
     try
     {
      RandomAccessFile r=new RandomAccessFile(f.toString(),"r");
      long l=r.length();
      r.close();
      String filename=f.toString().trim();
      filename=filename.substring(filename.lastIndexOf("\\",filename.length()));
      DataOutputStream out=new DataOutputStream(new FileOutputStream(path+filename));
      DataInputStream in=new DataInputStream(new FileInputStream(f));
      for(long i=0;i<l;i++)
      {
       out.writeByte(in.readByte());
       out.flush();
      }
      out.close();
      in.close();
     }catch(Exception e){}
    }
}

