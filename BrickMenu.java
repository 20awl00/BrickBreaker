//Jadon Schuler 7/16/2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickMenu extends JPanel
{
   public BrickLevel01 level1;
   public BrickLevel02 level2;
   public BrickLevel03 level3;
   public BrickLevel04 level4;
   public BrickLevel05 level5;
   public BrickLevel06 level6;
   public BrickLevel07 level7;
   public BrickLevel08 level8;
      
   public BrickMenu()
   {
      setLayout(new BorderLayout());
      setOpaque(true);
      setBackground(Color.black);
      
      JPanel subpanel1 = new JPanel();
      subpanel1.setLayout(new FlowLayout());
      addButton(subpanel1, "One", 1);
      addButton(subpanel1, "Two", 2);
      addButton(subpanel1, "Three", 3);
      addButton(subpanel1, "Four", 4);
      addButton(subpanel1, "Five", 5);
      add(subpanel1, BorderLayout.NORTH);
      
      JPanel subpanel2 = new JPanel();
      subpanel2.setLayout(new FlowLayout()); 
      addButton(subpanel2, "Six", 6);
      addButton(subpanel2, "Seven", 7);
      addButton(subpanel2, "Eight", 8);
      addButton(subpanel2, "Nine", 9);
      add(subpanel2, BorderLayout.SOUTH);
   }
   
   private void addButton(JPanel panel, String s, int x)
   {
      JButton button = new JButton(s);
      button.addActionListener(new Listener(x));
      panel.add(button);
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
               break;
            case 2: level2 = new BrickLevel02();
               add(level2, BorderLayout.CENTER);
               level2.requestFocus();
               level2.startTimer();
               break;
            case 3: level3 = new BrickLevel03();
               add(level3, BorderLayout.CENTER);
               level3.requestFocus();
               level3.startTimer();
               break;
            case 4: level4 = new BrickLevel04();
               add(level4, BorderLayout.CENTER);
               level4.requestFocus();
               level4.startTimer();
               break;
            case 5: level5 = new BrickLevel05();
               add(level5, BorderLayout.CENTER);
               level5.requestFocus();
               level5.startTimer();
               break;
            case 6: level6 = new BrickLevel06();
               add(level6, BorderLayout.CENTER);
               level6.requestFocus();
               level6.startTimer();
               break;
            case 7: level7 = new BrickLevel07();
               add(level7, BorderLayout.CENTER);
               level7.requestFocus();
               level7.startTimer();
               break;
            case 8: level8 = new BrickLevel08();
               add(level8, BorderLayout.CENTER);
               level8.requestFocus();
               level8.startTimer();
               break;
         }
         validate();
         repaint();
      }
   }
}