//Jadon Schuler and Alex Lewis 7/14/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class MedBrick extends Brick
{
   public MedBrick(int x, int y)
   {
      super(x, y, Color.GREEN);
   } 
   public void whatever()
   {
   try
      {

      File file = new File("Bomb.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
      }
      catch(Exception b){}
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