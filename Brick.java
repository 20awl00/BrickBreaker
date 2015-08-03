//Jadon Schuler and Alex Lewis 7/13/2015
import java.awt.*;
public abstract class Brick extends Bumper
{
   private int myFractures;
   public boolean ok;

   public Brick(int x, int y, Color c)
   {
      super(x, y, 50, 15, c);
   } 
   public abstract void whatever();
   public int getFractures()
   {
      return myFractures;
   }
   public void fracture()
   {
      myFractures++;
   }
   
   public void draw(Graphics myBuffer) 
   {
      //ImageIcon paddle = new ImageIcon("Paddle.jpg");
      //myBuffer.drawImage(paddle.getImage(), getX(), getY(), getXWidth(), getYWidth(), null);
      myBuffer.setColor(getColor());
      myBuffer.fillRect(getX(), getY(), getXWidth(), getYWidth());
   }
}