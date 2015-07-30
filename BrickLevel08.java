//Jadon Schuler and Alex Lewis 7/14/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.sound.sampled.*;
import java.io.File;

public class BrickLevel08 extends JPanel
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
   private MedBrick[] column1;
   private MedBrick[] column2;
   private MedBrick[] column3;
   private MedBrick[] column4;
   private MedBrick[] column5;
   private MedBrick[] column6;
   private MedBrick[] column7;
   private Bumper bumper;
   private Timer timer;
   
   private boolean left, right;    
   
   public BrickLevel08() throws Exception
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
      column1 = new MedBrick[3];
      column2 = new MedBrick[3];
      column3 = new MedBrick[3];
      column4 = new MedBrick[3];
      column5 = new MedBrick[3];
      column6 = new MedBrick[3];
      column7 = new MedBrick[3];
      
      int x = 15;
      for(int i = 0; i < 3; i++)
      {
         column1[i] = new MedBrick(15, x);
         column2[i] = new MedBrick(67, x + 26);
         column3[i] = new MedBrick(119, x);
         column4[i] = new MedBrick(171, x + 26);
         column5[i] = new MedBrick(223, x);
         column6[i] = new MedBrick(275, x + 26);
         column7[i] = new MedBrick(327, x);
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
         for(int i = 0; i < 3; i++)
         {
            BrickCollision.collide(column1[i], ball);
            BrickCollision.collide(column2[i], ball);
            BrickCollision.collide(column3[i], ball);
            BrickCollision.collide(column4[i], ball);
            BrickCollision.collide(column5[i], ball);
            BrickCollision.collide(column6[i], ball);
            BrickCollision.collide(column7[i], ball);
         }
         boolean allOk = true ;
         for( int i = 0 ; i < 3; i++)
         {
            allOk = allOk && column1[i].ok ;
            allOk = allOk && column2[i].ok ;
            allOk = allOk && column3[i].ok ;
            allOk = allOk && column4[i].ok ;
            allOk = allOk && column5[i].ok ;
            allOk = allOk && column6[i].ok ;
            allOk = allOk && column7[i].ok ;
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
      
         // draw ball, bumper & prize
         ball.draw(myBuffer);
         bumper.draw(myBuffer);
         for(int i = 0; i < 3; i++)
         {
            column1[i].draw(myBuffer);
            column2[i].draw(myBuffer);
            column3[i].draw(myBuffer);
            column4[i].draw(myBuffer);
            column5[i].draw(myBuffer);
            column6[i].draw(myBuffer);
            column7[i].draw(myBuffer);
         }
         
         myBuffer.setFont(new Font("Monospaced", Font.BOLD, 15));
         myBuffer.setColor(Color.WHITE);
         myBuffer.drawString("Lives: " + lives, 320, 20);
         
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