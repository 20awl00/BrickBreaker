//Jadon Schuler 7/16/2015

import javax.swing.JFrame;
import javax.swing.*;

public class DriverBrickMenu
{
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("Brick Breaker: HOMEMADE VERSION!!!");
      frame.setSize(720, 840);
      frame.setLocation(300, 90);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new BrickMenuV2());
      frame.setVisible(true);
   }
}