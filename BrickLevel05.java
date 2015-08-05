//Jadon Schuler and Alex Lewis 7/14/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.sound.sampled.*;
import java.io.File;

public class BrickLevel05 extends JPanel
{
   private static final int FRAME = 400;
   private static final Color BACKGROUND = Color.BLACK;
   private static final Color BALL_COLOR = Color.RED;
   private static final Color BUMPER_COLOR = new Color(113, 198, 113);
   private static final double BALL_DIAM = 24;
   private static final int BUMPER_X_WIDTH = 100;
   private static final int BUMPER_Y_WIDTH = 15;
   private int lives = LifeGetter.input("lives.txt") + 1;

   private BufferedImage myImage;
   private Graphics myBuffer;
   private Ball ball;
   private SteelBrick[] row1;
   private SteelBrick[] row2;
   private SteelBrick[] row3;
   private Bumper bumper;
   private Timer timer;
   
   private Laser laser;
   
   private int lasercount = 0;
   
   private int brick;
   
   private boolean left, right, pause, yay, lasers, space;    
   
   public boolean hasWon = false;
   
   ImageIcon laserpad;  
   
   public LaserShot[] laserArray;
   
   public BrickLevel05()
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME,FRAME);
      
      laserArray = new LaserShot[20];
      laser = new Laser(1000, 0);
      
      brick = (int) (Math.random() * 7);
      
      yay = false;
      lasers = false;
      
      for(int i = 0; i < 20; i ++)
         laserArray[i] = new LaserShot(1000, 0);
      
      laserpad = new ImageIcon("PaddleV3Laser.jpg");
      
      // create ball and jump
      ball = new Ball(20,300,BALL_DIAM,BALL_COLOR);
   
               
      // create paddle
      bumper = new Bumper(20,350,BUMPER_X_WIDTH,BUMPER_Y_WIDTH,BUMPER_COLOR);
      
      //Create bricks
      row1 = new SteelBrick[7];
      row2 = new SteelBrick[7];
      row3 = new SteelBrick[7];
      
      int x = 15;
      for(int i = 0; i < 7; i++)
      {
         row1[i] = new SteelBrick(x,65);
         row2[i] = new SteelBrick(x, 45);
         row3[i] = new SteelBrick(x, 25);
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
         
         laser.move();
         
         if(row1[brick].getX() > FRAME && yay == false)
         {
            laser.setX((int)(Math.random() * FRAME));
            laser.setY(0);
            yay = true;
         }
         
         if(right)
            bumper.setX(bumper.getX()+3);
         if(left)
            bumper.setX(bumper.getX()-3);
            
         for(int k = 0; k < 20; k ++)
            laserArray[k].move();
            
         if(space)
            fire(lasercount);
         
         BumperCollision.collide(bumper, ball);
         for(int i = 0; i < 7; i++)
         {
            BrickCollision.collide(row1[i], ball);
            BrickCollision.collide(row2[i], ball);
            BrickCollision.collide(row3[i], ball);
         }
         
         for(int i = 0; i < 7; i++)
         {
            for(int k = 0; k < 20; k ++)
            {
               laserArray[k].hit(row1[i]);
               laserArray[k].hit(row2[i]);
               laserArray[k].hit(row3[i]);
            }
         }
         
         if(ball.getY()-200 >= FRAME)
         {
            ball.setX(20);
            ball.setY(300);
            ball.setdx(3);
            ball.setdy(-2);
            lasers = false;
            if (lives <= 0)
            {
               myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
               myBuffer.setColor(Color.RED.brighter());
               myBuffer.drawString("GAME OVER", 50, 150);
               timer.stop();
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
         }
           
         if(allOk)
         {
            myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
            myBuffer.setColor(Color.GREEN.darker());
            myBuffer.drawString("YOU WIN", 90, 150);
            LifeGetter.output(lives, "lives.txt");
            hasWon = true;
            timer.stop();
         }
         
         if(laser.collideWith(bumper))
            lasers = true;
         
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
         if(lasers)
            myBuffer.drawImage(laserpad.getImage(), bumper.getX(), bumper.getY(), bumper.getXWidth(), bumper.getYWidth(), null);
         
         for(int k = 0; k < 20; k ++)
            laserArray[k].draw(myBuffer);
            
         for(int i = 0; i < 7; i++)
         {
            row1[i].draw(myBuffer);
            row2[i].draw(myBuffer);
            row3[i].draw(myBuffer);
         }
         
         laser.draw(myBuffer);
         
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
         if(e.getKeyCode()==KeyEvent.VK_P)
            if(pause == false)
            {
               myBuffer.setColor(Color.yellow);
               myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
               timer.stop();
               myBuffer.drawString("Game Paused", 60, 250);
               repaint();
               pause = true;
            }
            else
            {
               timer.start();
               pause = false;
            }
         if(e.getKeyCode()==KeyEvent.VK_SPACE && lasers == true)
         {
            space = true;
            lasercount += 2;
         }
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            left = false;
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            right = false;
         if(e.getKeyCode()==KeyEvent.VK_SPACE && lasers == true)
            space = false;
      }
   }
   public void startTimer()
   {
      timer.start();
   }
   
   public void fire(int x)
   {
      laserArray[x % 20].setX(bumper.getX() + bumper.getXWidth() - 5);
      laserArray[x % 20].setY(bumper.getY());
      
      laserArray[(x % 20) + 1].setX(bumper.getX());
      laserArray[(x % 20) + 1].setY(bumper.getY());
   }
}