/* Designed and developed by students of CSE (SLVP) 2007 */

/* Designed for RST&C software */

/* Developed in CSE LAB #1 */

/* Starting Date: 17-12-2009, 11:47:00 */

import java.io.*;
import java.lang.*;

/* This class has only one method that retrieve the process running in the current system 
   and returns it in an array of string.  */


public class TaskManager
{
  static String[] getTaskList()throws Exception
  {
    Process p = Runtime.getRuntime().exec("tasklist");
    DataInputStream input=new DataInputStream(p.getInputStream());
    String tasks[]=new String[100];
    String compare=new String();
    int counter=0;
    try
     {
      while(true)
      { 
       tasks[counter]=new String(input.readLine().trim());
       counter++;
       if(false)break;
      }
     }
     catch(Exception e){}

  /*   counter=0;
     while(!(compare=tasks[counter]).equals(""))
     {
      counter++;
     }   */

     String optitasks[]=new String[counter];
     for(int i=0;i<counter;i++)
     {
      optitasks[i]=new String(tasks[i]);
     }
   return optitasks;
  }
 }
