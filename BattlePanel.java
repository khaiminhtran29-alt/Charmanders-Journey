/**
*

*/
import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;

public class BattlePanel extends JPanel
{
  //private final JLabel resultLabel;
  private static final int GAP = 8;
  
  private final JButton battleButton;
  
  private final JPanel playerGrid;
  private final JPanel opponentGrid;
  
  public BattlePanel(IntConsumer onProgress) {   
     setLayout(new GridLayout (1, 3, GAP, GAP));
     setBorder(BorderFactory.createTitledBorder("Trainer Battle"));
     
     //Instantiating child components
     JLabel playerLabel = new JLabel();
     playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     
     JLabel opponentLabel = new JLabel();
     playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     
     battleButton = new JButton("Ready for battle.");
     
     playerGrid = new JPanel();
     playerGrid.setLayout(new GridLayout (3, 2, GAP, GAP));
     
     opponentGrid = new JPanel();
     opponentGrid.setLayout(new GridLayout (3, 2, GAP, GAP));
     
     
  }
  
  private static JLabel createPokemonImageLabel(String imageFileName) {
      try 
      {
         BufferedImage image = ImageIO.read(new File(imageFileName));
         
         if (image == null)
         {
            return new JLabel("Image could not be read");
         }
         
         Image scaledImage = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
         
         return new JLabel(new ImageIcon(scaledImage));
         
      } catch (IOException exception) {
         //Keep the rest of the panel usable if the image is missing
         
         return new JLabel("Could not load " + imageFileName);
      }        
  }

}