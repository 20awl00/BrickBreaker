//Jadon Schuler and Alex Lewis 7/14/2015

import java.awt.*;
public class SteelBrick extends Brick
{
   public SteelBrick(int x, int y)
   {
      super(x, y, Color.GRAY.darker());
   } 
   public void whatever()
   {
      if(getFractures() >= 5)
      {
         setX(500);
         setY(500);
         ok = true;
      }
      else if(getFractures() == 4)
         setColor(Color.ORANGE);
      else if(getFractures() == 3)
         setColor(Color.YELLOW);
      else if(getFractures() == 1)
         setColor(Color.GRAY);
      else if(getFractures() == 2)
         setColor(Color.GREEN);
      
   }
}