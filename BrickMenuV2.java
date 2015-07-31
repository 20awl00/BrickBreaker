//Jadon Schuler and Alex Lewis 7/16/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File; 

public class BrickMenuV2 extends JPanel
{
   public BrickLevel01 level1;
   public BrickLevel02 level2;
   public BrickLevel03 level3;
   public BrickLevel04 level4;
   public BrickLevel05 level5;
   public BrickLevel06 level6;
   public BrickLevel07 level7;
   public BrickLevel08 level8;
   public BrickLevel09 level9;
   public BrickLevel10 level10;
   public BrickLevel11 level11;
   
   private Timer t;
   
   File file;
   AudioInputStream stream;
   AudioFormat format;
   DataLine.Info info;
   Clip clip;
   
   File file2;
   AudioInputStream stream2;
   AudioFormat format2;
   DataLine.Info info2;
   Clip clip2;
   
   JButton[] buttonArray1;
   JButton[] buttonArray2;
      
   public BrickMenuV2() throws Exception
   {
      setLayout(new BorderLayout());
      setOpaque(true);
      setBackground(Color.black);
      
      buttonArray1 = new JButton[6];
      buttonArray2 = new JButton[5];
      
      JPanel subpanel1 = new JPanel();
      subpanel1.setLayout(new FlowLayout());
      for(int i = 0; i < 6; i ++)
      {
         buttonArray1[i] = new JButton("Level " + (i + 1));
         buttonArray1[i].addActionListener(new Listener(i + 1));
         subpanel1.add(buttonArray1[i]);
         buttonArray1[i].setEnabled(false);
      } 
      add(subpanel1, BorderLayout.NORTH);
      
      JPanel subpanel2 = new JPanel();
      for(int i = 0; i < 5; i ++)
      {
         buttonArray2[i] = new JButton("Level " + (i + 7));
         buttonArray2[i].addActionListener(new Listener(i + 7));
         subpanel2.add(buttonArray2[i]);
         buttonArray2[i].setEnabled(false);
      }
      add(subpanel2, BorderLayout.SOUTH);
      
      file = new File("Start.wav");
      stream = AudioSystem.getAudioInputStream(file);
      format = stream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      
      file2 = new File("trololo.wav");
      stream2 = AudioSystem.getAudioInputStream(file2);
      format2 = stream2.getFormat();
      info2 = new DataLine.Info(Clip.class, format2);
      clip2 = (Clip) AudioSystem.getLine(info2);
      clip2.open(stream2);
      
      try{level1 = new BrickLevel01();}
      catch(Exception b){}
      try{level2 = new BrickLevel02();}
      catch(Exception b){}
      try{level3 = new BrickLevel03();}
      catch(Exception b){}
      try{level4 = new BrickLevel04();}
      catch(Exception b){}
      try{level5 = new BrickLevel05();}
      catch(Exception b){}
      try{level6 = new BrickLevel06();}
      catch(Exception b){}
      try{level7 = new BrickLevel07();}
      catch(Exception b){}
      try{level8 = new BrickLevel08();}
      catch(Exception b){}
      try{level9 = new BrickLevel09();}
      catch(Exception b){}
      try{level10 = new BrickLevel10();}
      catch(Exception b){}
      try{level11 = new BrickLevel11();}
      catch(Exception b){}
         
      buttonArray1[0].setEnabled(true);
      
      t = new Timer(500, new Listener2());
      t.start();
   }
   
   
   private class Listener implements ActionListener
   {
      private int myX;
      public Listener(int x)
      {
         myX = x;
      }
      
      public void actionPerformed(ActionEvent e)
      {
      
         switch(myX)
         {
            case 1:
            
               try{resetClip();}
               catch(Exception a){}
               clip.start();
               clip2.start();
               add(level1, BorderLayout.CENTER);
               level1.requestFocus();
               level1.startTimer();
               //buttonArray1[1].setEnabled(true);
               break;
               
            case 2:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level2, BorderLayout.CENTER);
               level2.requestFocus();
               level2.startTimer();
               buttonArray1[2].setEnabled(true);
               break;
               
            case 3: 
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level3, BorderLayout.CENTER);
               level3.requestFocus();
               level3.startTimer();
               buttonArray1[3].setEnabled(true);
               break;
               
            case 4: 
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level4, BorderLayout.CENTER);
               level4.requestFocus();
               level4.startTimer();
               buttonArray1[4].setEnabled(true);
               break;
               
            case 5:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level5, BorderLayout.CENTER);
               level5.requestFocus();
               level5.startTimer();
               buttonArray1[5].setEnabled(true);
               break;
               
            case 6:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level6, BorderLayout.CENTER);
               level6.requestFocus();
               level6.startTimer();
               buttonArray2[0].setEnabled(true);
               break;
               
            case 7:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level7, BorderLayout.CENTER);
               level7.requestFocus();
               level7.startTimer();
               buttonArray2[1].setEnabled(true);
               break;
               
            case 8:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level8, BorderLayout.CENTER);
               level8.requestFocus();
               level8.startTimer();
               buttonArray2[2].setEnabled(true);
               break;
               
            case 9:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level9, BorderLayout.CENTER);
               level9.requestFocus();
               level9.startTimer();
               buttonArray2[3].setEnabled(true);
               break;
               
            case 10:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level10, BorderLayout.CENTER);
               level10.requestFocus();
               level10.startTimer();
               buttonArray2[4].setEnabled(true);
               break;
               
            case 11:
               
               try{resetClip();}
               catch(Exception a){}
               clip2.stop();
               try{resetClip2();}
               catch(Exception c){}
               clip.start();
               clip2.start();
               add(level11, BorderLayout.CENTER);
               level11.requestFocus();
               level11.startTimer();
               break;
               
         }
         validate();
         repaint();
      }
   }
   
   public void resetClip() throws Exception
   {
      file = new File("Start.wav");
      stream = AudioSystem.getAudioInputStream(file);
      format = stream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
   }
   
   public void resetClip2() throws Exception
   {
      file2 = new File("TetrisA.wav");
      stream2 = AudioSystem.getAudioInputStream(file2);
      format2 = stream2.getFormat();
      info2 = new DataLine.Info(Clip.class, format2);
      clip2 = (Clip) AudioSystem.getLine(info2);
      clip2.open(stream2);
   }
   
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
            if(level1.hasWon == true)
               buttonArray1[1].setEnabled(true);
      }
   }
}