import java.io.*;
import java.lang.*;
import java.util.Date;


class DateCompare
{
 public static void main(String args[])throws Exception
 {
  try
  {
   File f=new File("c:\\WINDOWS\\system32\\trail.dll");
   DataInputStream in=new DataInputStream(new FileInputStream(f));
   String s=in.readLine().trim();
   System.out.println(s);
   byte b[]=s.getBytes();
   for(int i=0;i<b.length;i++)
   {
    b[i]=(byte)((int)b[i]-1);
   }
   System.out.println(new String(b));
   Date d=new Date();
   System.out.println(d.getTime());
   long old=new Long(new String(b)).longValue();
   long latest =d.getTime();
   long diff=latest-old;
   System.out.println(diff);
   if(diff<=1296000000)
   {
    System.out.println("Still you have time");
   }
   else
    System.out.println("Expired");
  }catch(Exception e){System.out.println(e);}
 }
}
