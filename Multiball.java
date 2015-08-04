//Jadon Schuler and Alex Lewis 7/13/2015

import java.awt.*;
import javax.swing.*;

public class Multiball extends Powerup
{
   private int myX;
   private int myY;
   
   public Multiball (int x, int y)
   {
      super(x, y);
   }
   
   private boolean used = false;
   
   public void draw(Graphics myBuffer)
   {
      ImageIcon power = new ImageIcon("multiball.jpg");
      myBuffer.drawImage(power.getImage(), getX(), getY(), 20, 20, null);
   }
}