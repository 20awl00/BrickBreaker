//Jadon Schuler and Alex Lewis 7/14/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.sound.sampled.*;
import java.io.File;

public class BrickLevel07 extends JPanel
{
   private static final int FRAME = 400;
   private static final Color BACKGROUND = Color.BLACK;
   private static final Color BALL_COLOR = Color.RED;
   private static final Color BUMPER_COLOR = new Color(113, 198, 113);
   private static final double BALL_DIAM = 24;
   private static final int BUMPER_X_WIDTH = 100;
   private static final int BUMPER_Y_WIDTH = 15;
   private int lives = LifeGetter.input("lives.txt") + 1;
   
   File file;
   AudioInputStream stream;
   AudioFormat format;
   DataLine.Info info;
   Clip clip;
   Robot delayer;

   private BufferedImage myImage;
   private Graphics myBuffer;
   private Ball ball;
   private MedBrick[] row1;
   private MedBrick[] row2;
   private SteelBrick[] row3;
   private WeakBrick[] row4;
   private Bumper bumper;
   private Timer timer;
   
   private boolean left, right;    
   
   public BrickLevel07() throws Exception
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME,FRAME);
      
      file = new File("Lose.wav");
      stream = AudioSystem.getAudioInputStream(file);
      format = stream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      clip = (Clip) AudioSystem.getLine(info);
      
      delayer = new Robot();
      
      // create ball and jump
      ball = new Ball(20,300,BALL_DIAM,BALL_COLOR);
   
               
      // create paddle
      bumper = new Bumper(20,350,BUMPER_X_WIDTH,BUMPER_Y_WIDTH,BUMPER_COLOR);
      
      //Create bricks
      row1 = new MedBrick[7];
      row2 = new MedBrick[7];
      row3 = new SteelBrick[7];
      row4 = new WeakBrick[7];
      
      int x = 15;
      for(int i = 0; i < 7; i++)
      {
         row1[i] = new MedBrick(x,65);
         row2[i] = new MedBrick(x, 45);
         row3[i] = new SteelBrick(x, 25);
         row4[i] = new WeakBrick(x,220);
         x += 52;
      }
      
      timer = new Timer(6, new Listener());
      //timer.start();
      
      addKeyListener(new Key());
      setFocusable(true);
   
   }
   
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
       
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // clear buffer and move ball
         myBuffer.setColor(BACKGROUND);
         myBuffer.fillRect(0,0,FRAME,FRAME); 
         ball.move(FRAME, FRAME);
         if(right)
            bumper.setX(bumper.getX()+3);
         if(left)
            bumper.setX(bumper.getX()-3);
         
         BumperCollision.collide(bumper, ball);
         for(int i = 0; i < 7; i++)
         {
            BrickCollision.collide(row1[i], ball);
            BrickCollision.collide(row2[i], ball);
            BrickCollision.collide(row3[i], ball);
            BrickCollision.collide(row4[i], ball);
         }
         
         if(ball.getY()-200 >= FRAME)
         {
            ball.setX(20);
            ball.setY(300);
            ball.setdx(3);
            ball.setdy(-2);
            if (lives <= 0)
            {
               myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
               myBuffer.setColor(Color.RED.darker());
               myBuffer.drawString("YOU LOSE", 80, 150);
               timer.stop();
               clip.start();
               delayer.delay(2000);
               System.exit(0);
            }
            else
               lives --;
         }
         boolean allOk = true ;
         for( int i = 0 ; i < 7; i++)
         {
            allOk = allOk && row1[i].ok ;
            allOk = allOk && row2[i].ok ;
            allOk = allOk && row3[i].ok ;
            allOk = allOk && row4[i].ok ;
         }
           
         if(allOk)
         {
            myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
            myBuffer.setColor(Color.GREEN.darker());
            myBuffer.drawString("YOU WIN", 90, 150);
            LifeGetter.output(lives, "lives.txt");
            timer.stop();
         }
         
         if(ball.getdx() == 0)
            ball.setdx(2);
         if(ball.getdy() == 0)
            ball.setdy(2);
            
         if(bumper.getX()<= 0)
            bumper.setX(0);
         if(bumper.getX() + 100 >= FRAME)
            bumper.setX(FRAME - 100);
            
         myBuffer.setFont(new Font("Monospaced", Font.BOLD, 15));
         myBuffer.setColor(Color.WHITE);
         myBuffer.drawString("Lives: " + lives, 320, 20);
      
         // draw ball, bumper & prize
         ball.draw(myBuffer);
         bumper.draw(myBuffer);
         for(int i = 0; i < 7; i++)
         {
            row1[i].draw(myBuffer);
            row2[i].draw(myBuffer);
            row3[i].draw(myBuffer);
            row4[i].draw(myBuffer);
         }        
                     
         repaint();
      }
   } 
		
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            left = true;
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            right = true;
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            left = false;
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            right = false;
      }
   }
   public void startTimer()
   {
      timer.start();
   }
}