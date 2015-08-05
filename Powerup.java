//Jadon Schuler and Alex Lewis 7/13/2015

import java.awt.*;

public abstract class Powerup
{
   private int myX;
   private int myY;
   
   public Powerup(int x, int y)
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
   
   public abstract void draw(Graphics myBuffer);
   
   public void move()
   {
      setY(getY() + 1);
   }
   
   public boolean collideWith(Bumper bumper)
   {
      if(myX >= bumper.getX() && myX <= (bumper.getX() + bumper.getXWidth()) && myY + 20 >= bumper.getY() && myY + 20 <= (bumper.getY() + bumper.getYWidth() + 20))
      {
         setX(1000);
         return true;
      }
      else 
         return false;
   }
}