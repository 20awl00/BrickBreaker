//Jadon Schuler and Alex Lewis 7/14/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.sound.sampled.*;
import java.io.File;

public class BrickLevel09 extends JPanel
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
   private Ball[] ball;
   private RubberBrick[] rubber;
   private PortalBrick a;
   private PortalBrick b;
   private MedBrick[] row1;
   private SteelBrick[] row2;
   private Bumper bumper;
   private Timer timer;
   
   private Multiball multiball;
   private Laser laser;
   
   private int lasercount = 0;
   
   private int brick;
   private int brick2;
   
   private JLabel label;
   
   ImageIcon laserpad;
   
   private int numBalls = 1;
   
   private boolean left, right, resume, pause, yay, yay2, lasers, space;
   
   public boolean hasWon = false;
   
   public LaserShot[] laserArray;
   
   public BrickLevel09()
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME,FRAME);
      
      label = new JLabel();
      label.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
      label.setForeground(Color.yellow);
      label.setBackground(Color.black);
      label.setOpaque(true);
      add(label);
      
      laserArray = new LaserShot[20];
      
      // create ball and jump
      ball = new Ball[3];
      for (int k = 0; k < numBalls; k ++)
         ball[k] = new Ball(20,300,BALL_DIAM, BALL_COLOR); 
   
               
      // create paddle
      bumper = new Bumper(20,350,BUMPER_X_WIDTH,BUMPER_Y_WIDTH,BUMPER_COLOR);
      
      //Create bricks
      rubber = new RubberBrick[4];
      row1 = new MedBrick[7];
      row2 = new SteelBrick[7];
      
      multiball = new Multiball(1000, 0);
      laser = new Laser(1000, 0);
      
      a = new PortalBrick(20, 20);
      b = new PortalBrick(332, 20);
      for(int i = 0; i < 7; i++)
      {
         row1[i] = new MedBrick(i*52+20, 40);
         row2[i] = new SteelBrick(i*52+20, 70);
      }
      for(int i = 0; i < 4; i++)
         rubber[i] = new RubberBrick(i * 105 + 20, 320);
      timer = new Timer(6, new Listener());
      //timer.start();
      
      addKeyListener(new Key());
      setFocusable(true);
      
      brick = (int) (Math.random() * 7);
      brick2 = (int) (Math.random() * 7);
      
      yay = false;
      yay2 = false;
      lasers = false;
      
      laserpad = new ImageIcon("PaddleV3Laser.jpg");
      
      for(int i = 0; i < 20; i ++)
      {
         laserArray[i] = new LaserShot(1000, 0);
      }
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
         
         multiball.move();
         laser.move();
         
         if(row2[brick].getX() > FRAME && yay == false)
         {
            multiball.setX((int)(Math.random() * FRAME));
            multiball.setY(0);
            yay = true;
         }
         if(row2[brick2].getX() > FRAME && yay2 == false)
         {
            laser.setX((int)(Math.random() * FRAME));
            laser.setY(0);
            yay2 = true;
         }
         
         for(int k = 0; k < numBalls; k ++)
            ball[k].move(FRAME, FRAME);
         for(int k = 0; k < 20; k ++)
            laserArray[k].move();
         
         if(right)
            bumper.setX(bumper.getX()+3);
         if(left)
            bumper.setX(bumper.getX()-3);
            
         if(space)
            fire(lasercount);
         
         for(int k = 0; k < numBalls; k ++)
            BumperCollision.collide(bumper, ball[k]);
         
         for(int i = 0; i < 4; i++)
            for(int k = 0; k < numBalls; k ++)
               BrickCollision.collide(rubber[i], ball[k]);
               
         for(int i = 0; i < 4; i++)
            for(int k = 0; k < 20; k ++)
               laserArray[k].hit(rubber[i]);
            
         for(int i = 0; i < 7; i++)
         {
            for(int k = 0; k < numBalls; k ++)
            {
               BrickCollision.collide(row1[i], ball[k]);
               BrickCollision.collide(row2[i], ball[k]);
            }
         }
         
         for(int i = 0; i < 7; i++)
         {
            for(int k = 0; k < 20; k ++)
            {
               laserArray[k].hit(row1[i]);
               laserArray[k].hit(row2[i]);
            }
         }
         
         
         for(int k = 0; k < numBalls; k ++)
         {
            BrickCollision.collide(a, ball[k]);
            BrickCollision.collide(b, ball[k]);
         
            a.teleport(ball[k]);
            b.teleport(ball[k]);
         }
         
         if(multiball.collideWith(bumper))
            multiball();
         if(laser.collideWith(bumper))
            lasers = true;
         
         if(checkLife())
         {
            ball[numBalls - 1].setX(20);
            ball[numBalls - 1].setY(300);
            ball[numBalls - 1].setdx(3);
            ball[numBalls - 1].setdy(-2);
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
         }
            
         allOk = allOk && a.ok;
         allOk = allOk && b.ok;
           
         if(allOk)
         {
            myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
            myBuffer.setColor(Color.GREEN.darker());
            myBuffer.drawString("YOU WIN", 90, 150);
            LifeGetter.output(lives, "lives.txt");
            hasWon = true;
            timer.stop();
         }
         
         for(int k = 0; k < numBalls; k ++)
         {
            if(ball[k].getdx() == 0)
               ball[k].setdx(2);
            if(ball[k].getdy() == 0)
               ball[k].setdy(2);
         }
            
         if(bumper.getX()<= 0)
            bumper.setX(0);
         if(bumper.getX() + 100 >= FRAME)
            bumper.setX(FRAME - 100);
            
         myBuffer.setFont(new Font("Monospaced", Font.BOLD, 15));
         myBuffer.setColor(Color.WHITE);
         myBuffer.drawString("Lives: " + lives, 320, 20);
      
         // draw ball & bumper
         
         for(int k = 0; k < numBalls; k ++)
            ball[k].draw(myBuffer);
            
         for(int k = 0; k < 20; k ++)
            laserArray[k].draw(myBuffer);
         
         bumper.draw(myBuffer);
         if(lasers)
            myBuffer.drawImage(laserpad.getImage(), bumper.getX(), bumper.getY(), bumper.getXWidth(), bumper.getYWidth(), null);
         
         for(int i = 0; i < 7; i++)       
         {
            row1[i].draw(myBuffer);
            row2[i].draw(myBuffer);
         }
         for(int i = 0; i < 4; i++) 
            rubber[i].draw(myBuffer);  
         a.draw(myBuffer);
         b.draw(myBuffer);
         
         multiball.draw(myBuffer);
         laser.draw(myBuffer);
         
         if(resume)
         {
            int count = 0;
            while(count < 1000000)
            {
               count ++;
               label.setText("No more multiballs!");
               repaint();
               resume = false;
            }
         }
         label.setText("");
         
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
         if(e.getKeyCode()==KeyEvent.VK_SPACE && lasers == true)
         {
            space = true;
            lasercount += 2;
         }
         if(e.getKeyCode()==KeyEvent.VK_T)
            resume = true;
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
   public void multiball()
   {
      try
      {
         for(int k = numBalls; k < numBalls + 2; k ++)
            ball[k] = new Ball(20,300,BALL_DIAM, BALL_COLOR);
         ball[numBalls].setdx(2);
         numBalls += 2;
      }
      catch(IndexOutOfBoundsException a)
      {
         resume = true;
         label.setText("No more multiballs!");
      }
   }
   public boolean checkLife()
   {
      boolean life = true;
      for(int k = 0; k < numBalls; k ++)
      {
         if(ball[k].getY()-12 >= FRAME && life == true)
            life = true;
         else
            life = false;
      }
      if(life == true)
      {
         return true;
      }
      else
         return false;
   }
   
   public void fire(int x)
   {
      laserArray[x % 20].setX(bumper.getX() + bumper.getXWidth() - 5);
      laserArray[x % 20].setY(bumper.getY());
      
      laserArray[(x % 20) + 1].setX(bumper.getX());
      laserArray[(x % 20) + 1].setY(bumper.getY());
   }
}