import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class CopyFilesClient extends Thread
{
  int inc=0;
  public CopyFilesClient()
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
     ClientSetup3.copyfile.setText("Installing at the default location ");
     sleep(3000);
     File f=new File("c:\\WINDOWS\\System32\\2000");
     f.mkdirs();
     File f1=new File("Client Installation files\\");
     File list[]=f1.listFiles();
     ClientSetup3.jpb.setMinimum(0);
     ClientSetup3.jpb.setMaximum(list.length);
     float one=100;
     float two=list.length;
     float per=one/two;
     float per2=per;
     System.out.println(per);
     System.out.println(two);
     for(int i=0;i<list.length;i++)
     {
       inc++;
       ClientSetup3.jpb.setValue(inc);
       ClientSetup3.jpb.setString((int)inc+"%");
       ClientSetup3.percent.setText("Progress "+(int)per+"%");
       String filename=list[i].toString().trim();
       filename=filename.substring(filename.lastIndexOf("\\",filename.length()));
       sleep(300);
       ClientSetup3.copyfile.setText("Copying file  : " + filename);
       copy(list[i]);
       copy2(list[i]);
       per+=per2;
     }

     ClientSetup3.copyfile.setText("All the files have been copied");
     ClientSetup3.b1.setEnabled(true);
    }catch(Exception e){}
   }
    
    public void copy(File f)
    {
     String path="c:\\WINDOWS\\System32\\2000\\";
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

    public void copy2(File f)
    {
     String path="c:\\WINDOWS\\System32\\";
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

