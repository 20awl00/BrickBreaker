//Jadon Schuler and Alex Lewis 7/14/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BrickLevel09 extends JPanel
{
   private static final int FRAME = 400;
   private static final Color BACKGROUND = Color.BLACK;
   private static final Color BALL_COLOR = Color.RED;
   private static final Color BUMPER_COLOR = new Color(113, 198, 113);
   private static final double BALL_DIAM = 24;
   private static final int BUMPER_X_WIDTH = 100;
   private static final int BUMPER_Y_WIDTH = 15;
   private int lives = 3;

   private BufferedImage myImage;
   private Graphics myBuffer;
   private Ball ball;
   private RubberBrick[] rubber;
   private PortalBrick a;
   private PortalBrick b;
   private MedBrick[] row1;
   private SteelBrick[] row2;
   private Bumper bumper;
   private Timer timer;
   
   private boolean left, right;    
   
   public BrickLevel09()
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME,FRAME);
      
      // create ball and jump
      ball = new Ball(20,300,BALL_DIAM,BALL_COLOR);
   
               
      // create paddle
      bumper = new Bumper(20,350,BUMPER_X_WIDTH,BUMPER_Y_WIDTH,BUMPER_COLOR);
      
      //Create bricks
      rubber = new RubberBrick[3];
      row1 = new MedBrick[7];
      row2 = new SteelBrick[7];
      
      a = new PortalBrick(20, 20);
      b = new PortalBrick(380, 20);
      for(int i = 0; i < 7; i++)
      {
         row1[i] = new MedBrick(i*52, 40);
         row2[i] = new SteelBrick(i*52, 70);
      }
      for(int i = 0; i < 3; i++)
         rubber[i] = new RubberBrick(i*120, 320);
   
      timer = new Timer(6, new Listener());
      timer.start();
      
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
            BrickCollision.collide(rubber[i], ball);
         for(int i = 0; i < 7; i++)
         {
            BrickCollision.collide(row1[i], ball);
            BrickCollision.collide(row2[i], ball);
         }
         BrickCollision.collide(a, ball);
         BrickCollision.collide(b, ball);
         
         a.teleport(ball);
         b.teleport(ball);
         if(ball.getY()-12 >= FRAME)
         {
            lives --;
            ball.setX(20);
            ball.setY(300);
            ball.setdx(3);
            ball.setdy(-2);
            if (lives < 1)
            {
               myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
               myBuffer.setColor(Color.RED.darker());
               myBuffer.drawString("YOU LOSE", 80, 150);
               timer.stop();
            }
         }
         boolean allOk = true ;
         for( int i = 0 ; i < 7; i++)
         {
            allOk = allOk && row1[i].ok ;
            allOk = allOk && row2[i].ok ;
         }
         for(int i = 0; i < 3; i++)
            allOk = allOk && rubber[i].ok ;
            
         allOk = allOk && a.ok;
         allOk = allOk && b.ok;
           
         if(allOk)
         {
            myBuffer.setFont(new Font("Garamond", Font.BOLD, 50));
            myBuffer.setColor(Color.GREEN.darker());
            myBuffer.drawString("YOU WIN", 90, 150);
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
         }
         for(int i = 0; i < 3; i++) 
            rubber[i].draw(myBuffer);  
         a.draw(myBuffer);
         b.draw(myBuffer);  
         repaint();
      }
   } 
   
	// checks to see if the ball & prize collide
	// if so, increments hits & relocates prize	
   
   /*public void collide(Ball b, Polkadot p)
   {
      // find distance between ball & prize centers
      double dist = distance(b.getX(), b.getY(), p.getX(), p.getY());
      
      if(dist < p.getRadius() + b.getRadius())
      {
         hits++;
         p.jump(FRAME,FRAME);    	
      }
   }*/
		
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

}