//Jadon Schuler and Alex Lewis 7/16/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
   
   JButton[] buttonArray1;
   JButton[] buttonArray2;
      
   public BrickMenuV2()
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
      buttonArray1[0].setEnabled(true);
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
            case 1: level1 = new BrickLevel01();
               add(level1, BorderLayout.CENTER);
               level1.requestFocus();
               level1.startTimer();
               buttonArray1[1].setEnabled(true);
               break;
            case 2: level2 = new BrickLevel02();
               add(level2, BorderLayout.CENTER);
               level2.requestFocus();
               level2.startTimer();
               buttonArray1[2].setEnabled(true);
               break;
            case 3: level3 = new BrickLevel03();
               add(level3, BorderLayout.CENTER);
               level3.requestFocus();
               level3.startTimer();
               buttonArray1[3].setEnabled(true);
               break;
            case 4: level4 = new BrickLevel04();
               add(level4, BorderLayout.CENTER);
               level4.requestFocus();
               level4.startTimer();
               buttonArray1[4].setEnabled(true);
               break;
            case 5: level5 = new BrickLevel05();
               add(level5, BorderLayout.CENTER);
               level5.requestFocus();
               level5.startTimer();
               buttonArray1[5].setEnabled(true);
               break;
            case 6: level6 = new BrickLevel06();
               add(level6, BorderLayout.CENTER);
               level6.requestFocus();
               level6.startTimer();
               buttonArray2[0].setEnabled(true);
               break;
            case 7: level7 = new BrickLevel07();
               add(level7, BorderLayout.CENTER);
               level7.requestFocus();
               level7.startTimer();
               buttonArray2[1].setEnabled(true);
               break;
            case 8: level8 = new BrickLevel08();
               add(level8, BorderLayout.CENTER);
               level8.requestFocus();
               level8.startTimer();
               buttonArray2[2].setEnabled(true);
               break;
            case 9: level9 = new BrickLevel09();
               add(level9, BorderLayout.CENTER);
               level9.requestFocus();
               level9.startTimer();
               buttonArray2[3].setEnabled(true);
               break;
            case 10: level10 = new BrickLevel10();
               add(level10, BorderLayout.CENTER);
               level10.requestFocus();
               level10.startTimer();
               buttonArray2[4].setEnabled(true);
               break;
            case 11: level11 = new BrickLevel11();
               add(level11, BorderLayout.CENTER);
               level11.requestFocus();
               level11.startTimer();
               break;
         }
         validate();
         repaint();
      }
   }
}