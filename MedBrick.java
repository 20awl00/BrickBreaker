//Jadon Schuler and Alex Lewis 7/14/2015
import java.awt.*;
public class MedBrick extends Brick
{
   public MedBrick(int x, int y)
   {
      super(x, y, Color.GREEN);
   } 
   public void whatever()
   {
      if(getFractures() >= 3)
      {
         setX(500);
         setY(500);
         ok = true;
      }
      else if(getFractures() == 2)
         setColor(Color.ORANGE);
      else if(getFractures() == 1)
         setColor(Color.YELLOW);
   }
}