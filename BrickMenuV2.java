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
   public BrickLevel12 level12;
   public Demo demo;
   
   private Timer t;
   
   String s;
   
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
      
   public BrickMenuV2()
   {
      setLayout(new BorderLayout());
      setOpaque(true);
      setBackground(Color.black);
      
      buttonArray1 = new JButton[6];
      buttonArray2 = new JButton[6];
      
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
      for(int i = 0; i < 6; i ++)
      {
         buttonArray2[i] = new JButton("Level " + (i + 7));
         buttonArray2[i].addActionListener(new Listener(i + 7));
         subpanel2.add(buttonArray2[i]);
         buttonArray2[i].setEnabled(false);
      }
      add(subpanel2, BorderLayout.SOUTH);
      
      try
      {
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
      }
      catch(Exception b){}
      
      try
      {
         level1 = new BrickLevel01();
         level2 = new BrickLevel02();
         level3 = new BrickLevel03();
         level4 = new BrickLevel04();
         level5 = new BrickLevel05();
         level6 = new BrickLevel06();
         level7 = new BrickLevel07();
         level8 = new BrickLevel08();
         level9 = new BrickLevel09();
         level10 = new BrickLevel10();
         level11 = new BrickLevel11();
         level12 = new BrickLevel12();
      }
      catch(Exception b){}
         
      buttonArray1[0].setEnabled(true);
      
      s = JOptionPane.showInputDialog("Enter name:");
      if (s.equals("You Suck"))
      {
         JButton demo = new JButton("Demo");
         demo.addActionListener(new Listener(0));
         subpanel2.add(demo);
         demo.setEnabled(true);
      }
      
      else
      {
         int x = LifeGetter.load(s);
         if(x <= 6)
            for(int i = 0; i < x; i ++)
               buttonArray1[i].setEnabled(true);
         else
         {
            for(int i = 0; i < 6; i ++)
               buttonArray1[i].setEnabled(true);
            for(int i = 0; i <= x - 7; i ++)
               buttonArray2[i].setEnabled(true);
         }
            
      
         t = new Timer(500, new Listener2());
         t.start();
      }
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
            case 0:
               
               demo = new Demo();
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(demo, BorderLayout.CENTER);
               demo.requestFocus();
               demo.startTimer();
               break;
               
            case 1:
            
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level1, BorderLayout.CENTER);
               level1.requestFocus();
               level1.startTimer();
               //buttonArray1[1].setEnabled(true);
               break;
               
            case 2:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level2, BorderLayout.CENTER);
               level2.requestFocus();
               level2.startTimer();
               //buttonArray1[2].setEnabled(true);
               break;
               
            case 3: 
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level3, BorderLayout.CENTER);
               level3.requestFocus();
               level3.startTimer();
               //buttonArray1[3].setEnabled(true);
               break;
               
            case 4: 
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level4, BorderLayout.CENTER);
               level4.requestFocus();
               level4.startTimer();
               //buttonArray1[4].setEnabled(true);
               break;
               
            case 5:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level5, BorderLayout.CENTER);
               level5.requestFocus();
               level5.startTimer();
               //buttonArray1[5].setEnabled(true);
               break;
               
            case 6:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level6, BorderLayout.CENTER);
               level6.requestFocus();
               level6.startTimer();
               //buttonArray2[0].setEnabled(true);
               break;
               
            case 7:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level7, BorderLayout.CENTER);
               level7.requestFocus();
               level7.startTimer();
               //buttonArray2[1].setEnabled(true);
               break;
               
            case 8:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level8, BorderLayout.CENTER);
               level8.requestFocus();
               level8.startTimer();
               //buttonArray2[2].setEnabled(true);
               break;
               
            case 9:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level9, BorderLayout.CENTER);
               level9.requestFocus();
               level9.startTimer();
               //buttonArray2[3].setEnabled(true);
               break;
               
            case 10:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level10, BorderLayout.CENTER);
               level10.requestFocus();
               level10.startTimer();
               //buttonArray2[4].setEnabled(true);
               break;
               
            case 11:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level11, BorderLayout.CENTER);
               level11.requestFocus();
               level11.startTimer();
               break;
               
            case 12:
               
               resetLevels();
               resetClips();
               clip.start();
               clip2.start();
               add(level12, BorderLayout.CENTER);
               level12.requestFocus();
               level12.startTimer();
               break;
               
         }
         validate();
         repaint();
      }
   }
   
   public void resetClips()
   {
      clip2.stop();
      try
      {
         file = new File("Start.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
      
         file2 = new File("TetrisA.wav");
         stream2 = AudioSystem.getAudioInputStream(file2);
         format2 = stream2.getFormat();
         info2 = new DataLine.Info(Clip.class, format2);
         clip2 = (Clip) AudioSystem.getLine(info2);
         clip2.open(stream2);
      }
      catch(Exception j){}
   }
   
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(level1.hasWon == true)
         {
            if (LifeGetter.load(s) <= 2)
               LifeGetter.save(2, s);
            buttonArray1[1].setEnabled(true);
         }
         if(level2.hasWon == true)
         {
            if (LifeGetter.load(s) <= 3)
               LifeGetter.save(3, s);
            buttonArray1[2].setEnabled(true);
         }
         if(level3.hasWon == true)
         {
            if (LifeGetter.load(s) <= 4)
               LifeGetter.save(4, s);
            buttonArray1[3].setEnabled(true);
         }
         if(level4.hasWon == true)
         {
            if (LifeGetter.load(s) <= 5)
               LifeGetter.save(5, s);
            buttonArray1[4].setEnabled(true);
         }
         if(level5.hasWon == true)
         {
            if (LifeGetter.load(s) <= 6)
               LifeGetter.save(6, s);
            buttonArray1[5].setEnabled(true);
         }
         if(level6.hasWon == true)
         {
            if (LifeGetter.load(s) <= 7)
               LifeGetter.save(7, s);
            buttonArray2[0].setEnabled(true);
         }
         if(level7.hasWon == true)
         {
            if (LifeGetter.load(s) <= 8)
               LifeGetter.save(8, s);
            buttonArray2[1].setEnabled(true);
         }
         if(level8.hasWon == true)
         {
            if (LifeGetter.load(s) <= 9)
               LifeGetter.save(9, s);
            buttonArray2[2].setEnabled(true);
         }
         if(level9.hasWon == true)
         {
            if (LifeGetter.load(s) <= 10)
               LifeGetter.save(10, s);
            buttonArray2[3].setEnabled(true);
         }
         if(level10.hasWon == true)
         {
            if (LifeGetter.load(s) <= 11)
               LifeGetter.save(11, s);
            buttonArray2[4].setEnabled(true);
         }
         if(level11.hasWon == true)
         {
            if (LifeGetter.load(s) <= 12)
               LifeGetter.save(12, s);
            buttonArray2[5].setEnabled(true);
         }
      }
   }
   
   public void resetLevels()
   {
   
      try
      {
         level1 = new BrickLevel01();
         level2 = new BrickLevel02();
         level3 = new BrickLevel03();
         level4 = new BrickLevel04();
         level5 = new BrickLevel05();
         level6 = new BrickLevel06();
         level7 = new BrickLevel07();
         level8 = new BrickLevel08();
         level9 = new BrickLevel09();
         level10 = new BrickLevel10();
         level11 = new BrickLevel11();
         level12 = new BrickLevel12();
      }
      catch(Exception c){}
   
   }
}