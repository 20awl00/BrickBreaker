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
      
      BrickLevel12 level = new BrickLevel12();
      frame.setContentPane(level);
               //level.requestFocus();
      level.startTimer();
      frame.setVisible(true);
      
   }
}