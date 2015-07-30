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
}
   
   
