//Jadon Schuler and Alex Lewis 7/14/2015

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class SteelBrick extends Brick
{
   private File file;
   private AudioInputStream stream;
   private AudioFormat format;
   private DataLine.Info info;
   private Clip clip;
   public SteelBrick(int x, int y)
   {
      super(x, y, Color.GRAY.darker());
   } 
   public void whatever()
   {
      try
      {
         file = new File("SteelBrickSound.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
      }
      catch(Exception b){}
      if(getFractures() >= 5)
      {
      try
      {
            file = new File("Bomb.wav");
            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
      }
      catch(Exception b){}
         setX(500);
         setY(500);
         
         clip.start();
         
         ok = true;
      }
      
      else if(getFractures() == 4)
      {
      try
      {
         file = new File("Bomb.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
         }
      catch(Exception b){}
         setColor(Color.ORANGE);
         
         clip.start();
      }
      
      else if(getFractures() == 3)
      {
      try
      {
         file = new File("Bomb.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
         }
      catch(Exception b){}
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