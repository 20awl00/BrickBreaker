//Jadon Schuler and Alex Lewis 7/14/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class SteelBrick extends Brick
{
   public SteelBrick(int x, int y)
   {
      super(x, y, Color.GRAY.darker());
   } 
   public void whatever() throws Exception
   {
      File file = new File("SteelBrickSound.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      
      if(getFractures() >= 5)
      {
         file = new File("BrickBreak.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
         
         setX(500);
         setY(500);
         
         clip.start();
         
         ok = true;
      }
      
      else if(getFractures() == 4)
      {
         file = new File("BrickBreak.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
         
         setColor(Color.ORANGE);
         
         clip.start();
      }
      
      else if(getFractures() == 3)
      {
         file = new File("BrickBreak.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
         
         setColor(Color.YELLOW);
         
         clip.start();
      }
      
      else if(getFractures() == 1)
      {
         setColor(Color.GRAY);
         clip.start();
      }
      
      else if(getFractures() == 2)
      {
         setColor(Color.GREEN);
         clip.start();
      }
      
   }
}