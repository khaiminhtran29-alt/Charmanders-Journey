/**
 * 
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.function.IntConsumer;

public class CatchPanel extends JPanel
{
   private static final int STARTING_POKEBALLS = 5;
   private static final int CATCH_CHANCE_PERCENT = 50;
   private static final int CATCH_PROGRESS = 10;

   private final JLabel ballsLabel;
   private final JLabel statusLabel;
   private final JButton catchButton;
   private final Random random = new Random();

   private int pokeballs = STARTING_POKEBALLS;
   private boolean pokemonCaught = false;

   public CatchPanel(IntConsumer onProgress) {   
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBorder(BorderFactory.createTitledBorder("Catch a Pokémon"));
      
      JLabel levelLabel = new JLabel("Wild Pokémon - Level 3");
      levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      JLabel imageLabel =  createPokemonImageLabel();
      imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      catchButton = new JButton("Throw Poké Ball");
      catchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      catchButton.addActionListener(event -> attemptCatch(onProgress));
      
      ballsLabel = new JLabel();
      ballsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      statusLabel = new JLabel("A wild Pokémon appeared!");
      statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      updateBallsLabel();
      
      add(levelLabel);
      add(Box.createVerticalStrut(8));
      add(imageLabel);
      add(Box.createVerticalStrut(8));
      add(catchButton);
      add(ballsLabel);
      add(statusLabel);   
   }
  
   private static JLabel createPokemonImageLabel(String imageFileName)
   {
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
  
   private void attemptCatch(IntConsumer onProgress) 
   {
      if (pokemonCaught)
      {
         statusLabel.setText("You already caught this Pokémon.");
         return;
      }
      
      if (pokeballs <=0)
      {
         statusLabel.setText("You're out of Poké Balls.");
         return;
      }
      
      // A Poke Ball is used on every attempt
      pokeballs--;
      updateBallsLabel();
      
      boolean caught = random.nextInt(100) < CATCH_CHANCE_PERCENT;
      
      if (caught)
      {
         pokemonCaught = true;
         statusLabel.setText("Caught the Pokémon!");
         catchButton.setEnabled(false);
         
         // Tell GamePanel to add catch progress.
         onProgress.accept(CATCH_PROGRESS);
      }
      
      else if (pokeballs == 0)
      {
         statusLabel.setText("It escaped. You're out of Poké Balls!");
      } 
      
      else 
      {
         statusLabel.setText("It escaped. Try again!");
      }
   }
  
   private void updateBallsLabel()
   {
      ballsLabel.setText("Poké Balls: " + pokeballs); 
      catchButton.setEnabled(pokeballs > 0 && !pokemonCaught);
   }
}
