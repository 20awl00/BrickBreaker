import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
public class BoosterBrick extends Brick
{
   public BoosterBrick(int x, int y)
   {
      super(x, y, Color.RED);
   } 
   public void whatever()
   {
   try
   {
      File file = new File("ShotgunBlast.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
    } 
    catch(Exception e)
    {
    }  
      if(getFractures() >= 2);
      {
         setX(500);
         setY(500);
         ok = true;
      }
   }
}