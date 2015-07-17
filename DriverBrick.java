//Jadon Schuler and Alex Lewis 7/13/2015

import javax.swing.JFrame;
import javax.swing.*;

public class DriverBrick


         /****************************
          *   OBSOLETE!!!!!          *
          *         DO NOT USE!!!    *
          *                          *
          *   Use "DriverBrickMenu   *
          *                          *
          ****************************/



{
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("Brick Breaker: HOMEMADE VERSION!!!");
      frame.setSize(600, 600);
      frame.setLocation(350, 180);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      String filename = JOptionPane.showInputDialog("Which level?");
      if(filename.equals("level 1") || filename.equals("level1") || filename.equals("Level 1") || filename.equals("Level1")){
         frame.setContentPane(new BrickLevel01());
      }
      else if(filename.equals("level 2") || filename.equals("level2") || filename.equals("Level 2") || filename.equals("Level2")){
         frame.setContentPane(new BrickLevel02());
      }
      else if(filename.equals("level 3") || filename.equals("level3") || filename.equals("Level 3") || filename.equals("Level3")){
         frame.setContentPane(new BrickLevel03());
      }
      else if(filename.equals("Demo") || filename.equals("demo") || filename.equals("DEMO")){
         frame.setContentPane(new Demo());
      }
      else if(filename.equals("level 4") || filename.equals("level4") || filename.equals("Level 4") || filename.equals("Level4")){
         frame.setContentPane(new BrickLevel04());
      }
      else if(filename.equals("level 5") || filename.equals("level5") || filename.equals("Level 5") || filename.equals("Level5")){
         frame.setContentPane(new BrickLevel05());
      }
      else if(filename.equals("level 6") || filename.equals("level6") || filename.equals("Level 6") || filename.equals("Level6")){
         frame.setContentPane(new BrickLevel06());
      }
      else if(filename.equals("level 7") || filename.equals("level7") || filename.equals("Level 7") || filename.equals("Level7")){
         frame.setContentPane(new BrickLevel07());
      }
      else if(filename.equals("level 8") || filename.equals("level8") || filename.equals("Level 8") || filename.equals("Level8")){
         frame.setContentPane(new BrickLevel08());
      }
      
      else
         frame.setContentPane(new BrickPanel());
      
      
      frame.setVisible(true);
      
   }
}