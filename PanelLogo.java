// Jadon Schuler 7/9/2015

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class PanelLogo extends JPanel
{

   private BufferedImage myImage;
   private Graphics myBuffer;
   
   public PanelLogo()
   {
      myImage =  new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      paintComponent(myBuffer);
   }
   public void paintComponent(Graphics g)
   {
      ImageIcon logo = new ImageIcon("Logo.jpg");
      g.drawImage(logo.getImage(), 50, 50, 50, 80, null);
   }
}