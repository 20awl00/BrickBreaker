//Jadon Schuler and Alex Lewis 7/16/2015
import java.awt.*;
public class RubberBrick extends Brick
{
   public RubberBrick(int x, int y)
   {
      super(x, y, new Color(255, 105, 180));
   } 
   public void whatever()
   {
      if(getFractures() >= 1)
      {
      }
   }
}