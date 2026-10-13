/**
*

*/
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.awt.Image;
import javax.swing.JButton;

public class CatchPanel extends JPanel
{
  private JTextField lvl;
  private JLabel pokeImage;
  private JButton catchButton;
  private JTextField pokeballs;
   
  public CatchPanel() throws IOException
  {   
     setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
     
     
     setBorder(BorderFactory.createLineBorder(Color.black));
     
     
     // Instiates all of the child components
     
      lvl = new JTextField("The level of the Pokemon is: ");
      lvl.setBackground(Color.red);
      
      Image image = ImageIO.read(new File ("charmander.png")).getScaledInstance(-1, 200, Image.SCALE_DEFAULT);
      
      pokeImage = new JLabel(new ImageIcon(image));
      
      pokeImage.setPreferredSize(new Dimension(200,200));
      
      catchButton = new JButton("Catch");
      
      pokeballs = new JTextField("Pokeball: ");
      
       
      
      
      // Add child component to the frame
      
      add(lvl);
      add(pokeImage);
      add(catchButton);
      add(pokeballs);
      

      
      
      
      
  }
}