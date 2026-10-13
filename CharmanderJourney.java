/**

*/

import javax.swing.*;
import java.awt.Dimension;

public class CharmanderJourney
{
   private JFrame frame;
   private GamePanel gp;
   
   public CharmanderJourney ()
   {
      //Create the window that containes the game
      frame = new JFrame("Title: Charmander's Journey");
      
      //Config the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      
      //Add GamePanel to Frame
      gp = new GamePanel();
      //gp.setPreferredSize(new Dimension (600, 600));
      frame.setContentPane(gp);
      
      frame.pack();
      frame.setVisible(true);
      
      
      
  
   }
   
   public static void main (String[] args)
   {
      CharmanderJourney game = new CharmanderJourney();
      
   }
}