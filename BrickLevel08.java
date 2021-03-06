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
   
   private Laser laser;
   
   private int lasercount = 0;
   
   private int brick;
   
   private boolean left, right, pause, yay, lasers, space;    
   
   public boolean hasWon = false;
   
   ImageIcon laserpad;  
   
   public LaserShot[] laserArray;
   
   public BrickLevel08()
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME,FRAME);
      
      laserArray = new LaserShot[20];
      laser = new Laser(1000, 0);
      
      brick = (int) (Math.random() * 3);
      
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
         
         laser.move();
         
         if(column4[brick].getX() > FRAME && yay == false)
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
         for(int i = 0; i < 3; i++)
         {
            BrickCollision.collide(column1[i], ball);
            BrickCollision.collide(column2[i], ball);
            BrickCollision.collide(column3[i], ball);
            BrickCollision.collide(column4[i], ball);
            BrickCollision.collide(column5[i], ball);
            BrickCollision.collide(column6[i], ball);
            BrickCollision.collide(column7[i], ball);
            for(int k = 0; k < 20; k ++)
            {
               laserArray[k].hit(column1[i]);
               laserArray[k].hit(column2[i]);
               laserArray[k].hit(column3[i]);
               laserArray[k].hit(column4[i]);
               laserArray[k].hit(column5[i]);
               laserArray[k].hit(column6[i]);
               laserArray[k].hit(column7[i]);
            }
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
      
         // draw ball, bumper & prize
         ball.draw(myBuffer);
         
         bumper.draw(myBuffer);
         if(lasers)
            myBuffer.drawImage(laserpad.getImage(), bumper.getX(), bumper.getY(), bumper.getXWidth(), bumper.getYWidth(), null);
         
         for(int k = 0; k < 20; k ++)
            laserArray[k].draw(myBuffer);
         
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