//Jadon Schuler and Alex Lewis 7/13/2015
import java.awt.*;
public class WeakBrick extends Brick
{
   public WeakBrick(int x, int y)
   {
      super(x, y, Color.ORANGE);
   } 
   public void whatever()
   {
      if(getFractures() >= 1);
      {
         setX(500);
         setY(500);
         ok = true;
      }
   }
}