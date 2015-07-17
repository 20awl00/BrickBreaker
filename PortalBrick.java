//Jadon Schuler and Alex Lewis 7/16/2015
import java.awt.*;
public class PortalBrick extends WeakBrick
{
   int k = 1;
   public PortalBrick(int x, int y)
   {
      super(x, y);
      setColor(Color.BLUE.darker());
   } 
   public void teleport(Ball arg)
   {
      if(ok == true)
      {
         while (k < 2)
         {
            arg.jump(400, 400);
            k ++;
         }
      }
   }
}