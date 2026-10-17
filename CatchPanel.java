/**
 * 
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class CatchPanel extends JPanel
{
   private static final int STARTING_POKEBALLS = 5;
   private static final double CATCH_CHANCE_PERCENT = .5;
   private static final int SWITCH_DELAY = 3000;

   private final JLabel pokemonLevelLabel;
   private final JLabel pokemonImageLabel;
   private final JLabel statusLabel;
   private final JButton catchButton;
   private final JLabel ballsLabel;
   private final JLabel catchLabel;

   private final Timer switchTimer;

   private int pokeballs = STARTING_POKEBALLS;
   private Pokemon currentPokemon;

   public CatchPanel(Consumer<Pokemon> addPokemon) {   
      // Configure layout
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBorder(BorderFactory.createTitledBorder("Catch a Pokémon"));

      // Generate current pokemon
      currentPokemon = Pokemon.generateRandomPokemon();

      // Instantiate components
      pokemonLevelLabel = new JLabel();
      pokemonImageLabel = new JLabel();
      statusLabel = new JLabel();
      catchButton = new JButton("Throw Poké Ball");
      ballsLabel = new JLabel();
      catchLabel = new JLabel();

      // Center components
      pokemonLevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      pokemonImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      catchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      ballsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      catchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Set attemptCatch to run when catchButton is clicked
      catchButton.addActionListener(event -> attemptCatch(addPokemon));

      // Set switchPokemon to run every 5 seconds
      switchTimer = new Timer(SWITCH_DELAY, event -> switchPokemon());
      switchTimer.start();

      // Update component information with current information
      updateCatchPanel();

      // Add components to the catch panel
      add(Box.createVerticalStrut(50));
      add(pokemonLevelLabel);
      add(Box.createVerticalStrut(8));
      add(pokemonImageLabel);
      add(Box.createVerticalStrut(8));
      add(statusLabel);
      add(Box.createVerticalStrut(20));
      add(catchButton);
      add(ballsLabel);
      add(Box.createVerticalStrut(40));
      add(catchLabel);
      add(Box.createVerticalStrut(100));
   }

   private void attemptCatch(Consumer<Pokemon> addPokemon) 
   {
      // define logic
      if (Math.random() < CATCH_CHANCE_PERCENT) {
         addPokemon.accept(currentPokemon);
         catchLabel.setText("You caught " + currentPokemon.getName() + "!");
         pokeballs--;
      } else {
         catchLabel.setText("Too bad, you missed! " + currentPokemon.getName() + " got away!");
      }

      switchTimer.restart();
      switchPokemon();
   }

   private void switchPokemon() {
      // re-generate current pokemon 
      currentPokemon = Pokemon.generateRandomPokemon();
      
      // update the catch panel with new information
      updateCatchPanel();
   }
  
   private void updateCatchPanel()
   {   
      // If the user has run out of pokeballs, stop the switchTimer
      if (pokeballs <= 0)
      {
         switchTimer.stop();
      }

      // Set pokemon level label text
      pokemonLevelLabel.setText("Level: " + currentPokemon.getLevel());

      // Set pokemon image label image
      updatePokemonImageLabel();

      // Set enabled status of catch button
      if (pokeballs == 0)
         catchButton.setEnabled(false);
      else
         catchButton.setEnabled(true);

      // Set number of balls for balls label
      ballsLabel.setText("Number of PokeBalls: " + pokeballs);

      // Set status label to say a wild [pokemon name] appeared
      statusLabel.setText("Wild " + currentPokemon.getName() + " appeared!");

   }
    
   private void updatePokemonImageLabel() {
      try 
      {
         // Risky code-- try reading the current pokemon's image file
         BufferedImage image = ImageIO.read(new File(currentPokemon.getImageFilename()));
         
         // If the image is null, set the image label text to an error message
         if (image == null)
            pokemonImageLabel.setText("Image could not be read");
         
         // If the image is valid, set the image label icon to it, scaled
         Image scaledImage = image.getScaledInstance(-1,300, Image.SCALE_SMOOTH);
         pokemonImageLabel.setIcon(new ImageIcon(scaledImage));

      } catch (IOException exception) {
         // If an exception occurs, set image label text to an error message
         pokemonImageLabel.setText("Could not load " + currentPokemon.getImageFilename());
      }        
   }
}
