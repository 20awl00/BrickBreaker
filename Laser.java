//Jadon Schuler and Alex Lewis 7/13/2015

import java.awt.*;
import javax.swing.*;

public class Laser extends Powerup
{
   private int myX;
   private int myY;
   
   public Laser (int x, int y)
   {
      super(x, y);
   }
   
   public void draw(Graphics myBuffer)
   {
      ImageIcon power = new ImageIcon("lasers.jpg");
      myBuffer.drawImage(power.getImage(), getX(), getY(), 20, 20, null);
   }
}