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
   
   public void whatever() throws Exception
   {
      File file = new File("Mana_Void.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
      
      if(getFractures() >= 1);
      {
         setX(500);
         setY(500);
         ok = true;
      }
   }
   
   public void teleport(Ball arg)
   {
      if(ok == true)
      {
         while (k < 1)
         {
            arg.jump(400, 400);
            k ++;
         }
      }
   }
}