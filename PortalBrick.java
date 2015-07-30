//Jadon Schuler and Alex Lewis 7/16/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class PortalBrick extends WeakBrick
{
   int k = 0;
   public PortalBrick(int x, int y)
   {
      super(x, y);
      setColor(Color.BLUE.darker());
   } 
   public void teleport(Ball arg) throws Exception
   {
      if(ok == true)
      {
         while (k < 1)
         {
            File file = new File("Mana_Void.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            
            arg.jump(400, 400);
            k ++;
            
         }
      }
   }
}