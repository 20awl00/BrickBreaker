//Jadon Schuler and Alex Lewis 7/16/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class RubberBrick extends Brick
{
   public RubberBrick(int x, int y)
   {
      super(x, y, new Color(255, 105, 180));
   } 
   public void whatever() throws Exception
   {
      File file = new File("RubberBrick.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
            
      if(getFractures() >= 1)
      {
      }
   }
}