//Jadon Schuler and Alex Lewis 7/13/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class WeakBrick extends Brick
{
   public WeakBrick(int x, int y)
   {
      super(x, y, Color.ORANGE);
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
      if(getFractures() >= 1);
      {
         setX(500);
         setY(500);
         ok = true;
      }
   }
}