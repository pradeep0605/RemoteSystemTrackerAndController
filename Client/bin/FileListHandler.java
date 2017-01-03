import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.net.*;


public class FileListHandler implements Runnable
{
 int port;
 DataInputStream sockin;
 DataOutputStream sockout;
 String root=new String();
 String fname= new String();
 boolean var;


  public FileListHandler(int port)
  {
   try
   {
    Thread t=new Thread(this);

    t.sleep(100);
    this.port=port;
    Client.cs_filelistsocket=new Socket(Client.serverip,port);
    sockin = new DataInputStream(Client.cs_filelistsocket.getInputStream());
    sockout= new DataOutputStream(Client.cs_filelistsocket.getOutputStream());
    t.start();
   }
   catch(Exception e){System.out.println(e);}
  }

  public void run()
  {
   try
   {
    while(true)
    {
     root = sockin.readLine();

     System.out.println(root);


      if(root.equalsIgnoreCase("send"))
      {
        DataInputStream in=new DataInputStream(Client.cs_filesocket.getInputStream());
        DataOutputStream out=new DataOutputStream(Client.cs_filesocket.getOutputStream());
        String filename=in.readLine().trim();
        System.out.println(filename);

        File f=new File(filename);

        FileInputStream filein=new FileInputStream(f);
        DataInputStream fileinstream=new DataInputStream(filein);
        RandomAccessFile rs=new RandomAccessFile(filename,"r");
        long l=rs.length();
        System.out.println(l);
        out.writeLong(l);
        out.flush();
        rs.close();
        System.out.println("Before sending in the for loop");
        for(long i=0;i<l;i++)
        {
         out.writeByte(fileinstream.readByte());
         out.flush();
        }
        System.out.println("After sending the data");
        filein.close();
        fileinstream.close();
        out.close();
      }
      else
      {
       if(root.equalsIgnoreCase("receive"))
       {
        DataInputStream in=new DataInputStream(Client.cs_filesocket.getInputStream());
        String filename=in.readLine().trim();
        File f=new File(filename);
        System.out.println(filename);
      

        FileOutputStream fileout=new FileOutputStream(f);
        DataOutputStream fileoutstream=new DataOutputStream(fileout);
        long l=in.readLong();
        for(long j=0;j<l;j++)
        {
         fileoutstream.writeByte(in.readByte());
         fileoutstream.flush();
        }
         fileoutstream.close();
         fileout.close();
         in.close();
       }
       else
       {
         if(root.equalsIgnoreCase("delete"))
         {
          DataInputStream in=new DataInputStream(Client.cs_filesocket.getInputStream());
          String filename=in.readLine().trim();
          System.out.println(filename);
          File f=new File(filename);
          f.delete();
         }
         else
         {
          if(root.equalsIgnoreCase("root"))
           listAndSendFileList(root,3);
           else
          listAndSendFileList(root,2);
         }
       }
      } 
    }
    }catch(Exception e){System.out.println("Exception in listing : " + e);}
  }








 void listAndSendFileList(String root, int level)
 {
   try
   {
    String file1= new String();
    String file2= new String();

     if(level==3)
     {
      try
      {
        File f = new File("");
        File fl[] = f.listRoots();
        if(fl==null)
        {
        sockout.writeInt(0);
        sockout.flush();
        System.out.println("Zero 0");
        }
        else
        {
          sockout.writeInt((fl.length));
          sockout.flush();
          System.out.println(fl.length);
      
          for(int i=0; i<fl.length; i++)
          {
            file1=file1+fl[i].toString()+">";
          }                                 
          sockout.writeBytes(file1 +"\n");
          sockout.flush();
          System.out.println(file1);
      
          for(int i=0; i<fl.length; i++)
          {
            if(!fl[i].toString().equals("A:\\"))
            {
             File fl1[] =  fl[i].listFiles();
             
             if(fl1==null)
             {
              sockout.writeInt(0);
              sockout.flush();
              System.out.println("Zero 0");
             }
             
             else
             {
             
              for(int j=0; j<fl1.length; j++)
               {
                 file2 = file2 + fl1[j].toString()+">";
               }
      
               sockout.writeInt(fl1.length);
               sockout.flush();
               System.out.println(fl1.length);
            
               sockout.writeBytes(file2 +"\n");
               sockout.flush();
               System.out.println(file2);
               file2="";
            }
         }else{ sockout.writeInt(0); sockout.flush(); System.out.println("0");}
        }
      }
     }catch(Exception e){System.out.println("Exception in level 3: " + e);}
       
    }





    if(level==2)
    {
      try
      {
       System.out.println("Coming to level 2");
    
            file1="";
            file2="";
    
            File f = new File(root);

            if(f.isDirectory())
            {
             File fl1[]=f.listFiles();
             try
             {
              if(!(fl1==null || fl1.length==0))
              {
               try
               {
                sockout.writeInt(fl1.length);
                sockout.flush();
                 for(int i=0;i<fl1.length;i++)
                 {
                  file1=fl1[i].toString().trim();
                  sockout.flush();
                  sockout.writeBytes(file1+"\n");
                 }
            
                 for(int i=0;i<fl1.length;i++)
                 {
                  File fl2[]=fl1[i].listFiles();
                 
                  if(!(fl2==null || fl2.length==0))
                  {
                   sockout.writeInt(fl2.length);
                   for(int j=0;j<fl2.length;j++)
                   {
                    file2=fl2[j].toString().trim();
                    sockout.writeBytes(file2+"\n");
                    System.out.println(file2);
                   }
                  }else{sockout.writeInt(0); sockout.flush();System.out.println("0");}
                 }
               }catch(Exception e){System.out.println(e);}
              }else { sockout.writeInt(0);sockout.flush();System.out.println("0");}
             }catch(Exception e){System.out.println("error in inner catch" + e );}
            }
      }catch(Exception e){}
    }
   }catch(Exception e){}
  }
 }

