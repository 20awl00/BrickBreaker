//Jadon Schuler and Alex Lewis 7/13/2015

import java.awt.*;
import javax.swing.*;

public class LaserShot
{
   private int myX;
   private int myY;
   
   public LaserShot (int x, int y)
   {
      myX = x;
      myY = y;
   }
   
   public void setX(int x)
   {
      myX = x;
   }
   
   public void setY(int y)
   {
      myY = y;
   }
   
   public int getY()
   {
      return myY;
   }
   
   public int getX()
   {
      return myX;
   }
   
   public void draw(Graphics myBuffer)
   {
      myBuffer.setColor(Color.red);
      myBuffer.fillRect(getX(), getY(), 5, 10);
   }
   
   public void move()
   {
      setY(getY() - 3);
   }
   
   public void hit(Brick brick)
   {
      if(myY <= brick.getY() + brick.getYWidth() && myY >= brick.getY())
         if(myX <= brick.getX() + brick.getXWidth() && myX >= brick.getX())
         {
            brick.setFractures(brick.getFractures() + 1);
            setX(1000);
         }
   }
}