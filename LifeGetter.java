//Jadon Schuler and Alex Lewis 7/30/2015

import java.io.*;
import java.util.*;
import javax.swing.*;

public class LifeGetter
{
   public static void output(int x, String filename)
   {
      PrintStream outfile = null;
      try
      {
         outfile = new PrintStream(new FileOutputStream(filename));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      } 
      outfile.println(x);
   }
   
   public static int input(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be found.");
         System.exit(0);
      }
      int lives = infile.nextInt();
      infile.close();
      return lives;
   }
   
   
   
   
   
   
   public static int load(String s)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(s + ".txt"));
         return infile.nextInt();
      }
      catch(FileNotFoundException e)
      {
         createNew(s);
      }
      try
      {
         infile = new Scanner(new File(s + ".txt"));
      }
      catch(FileNotFoundException e)
      {
         System.exit(0);
      }
      return infile.nextInt();
   }
   
   public static void save(int level, String s)
   {
      PrintStream outfile = null;
      try
      {
         outfile = new PrintStream(new FileOutputStream(s + ".txt"));
         outfile.println("" + level);
      }
      
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
   }
   
   private static void createNew(String s)
   {
      try
      {
      System.setOut(new PrintStream(new FileOutputStream(s + ".txt")));
      System.out.println("1");
      }
      catch(FileNotFoundException e){}
   }
   
   
   
}
   
   
