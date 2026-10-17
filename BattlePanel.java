/**
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class BattlePanel extends JPanel
{
   private static final int CHARMANDER_START_LVL = 3;
   private static final int GAP = 8;

   private final JButton battleReadyButton;
   private final JButton battleButton;
   private final JLabel battleFeedback;
   //private final JButton battleChampion;

   private final JPanel playerGrid;
   private final JPanel opponentGrid;

   private IntConsumer addProgress;

   private Pokemon[] playerPokemons;
   private int numPlayerPokemon;

   private Pokemon[] opponentPokemons;

   public BattlePanel(IntConsumer addProgress) { 
      // Configure Layout  
      setLayout(new BoxLayout (this, BoxLayout.X_AXIS));
      setBorder(BorderFactory.createTitledBorder("Trainer Battle"));

      // store addProgress
      this.addProgress = addProgress;

      // Initialize game data
      playerPokemons = new Pokemon[6];
      playerPokemons[0] = new Pokemon(PokemonType.CHARMANDER, CHARMANDER_START_LVL);
      numPlayerPokemon = 1;

      // Add charmander's initial progress to progress bar
      addProgress.accept(CHARMANDER_START_LVL);
     
      // Instantiate components
      playerGrid = new JPanel();
      opponentGrid = new JPanel();

      JPanel centerPanel = new JPanel();
      battleReadyButton = new JButton("Find opponent");
      battleButton = new JButton("Battle!");
      battleFeedback = new JLabel();

      // Set panel layouts
      playerGrid.setLayout(new GridLayout (3, 2, GAP, GAP));
      opponentGrid.setLayout(new GridLayout (3, 2, GAP, GAP));
      centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

      // Configure components
      battleReadyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      battleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      battleFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Add JLabels to player and opponent grids
      for (int i = 0; i < 6; i++)
         playerGrid.add(new JLabel());
      
      for (int i = 0; i < 6; i++)
         opponentGrid.add(new JLabel());

      // Add battle buttons to center panel
      centerPanel.add(battleReadyButton);
      centerPanel.add(battleButton);
      centerPanel.add(battleFeedback);

      // Have generateOpponent() run when battleReadyButton clicked
      battleReadyButton.addActionListener(event -> generateOpponent());

      // Have battleOpponent() run when battleButton clicked
      battleButton.addActionListener(event -> battleOpponent());

      // Set battleButton to not enabled (only enable when battleReady has been clicked)
      battleButton.setEnabled(false);

      // Update components with current values
      updatePlayerGrid();
      resetOpponentGrid();

      // add components to Battle Panel
      add(playerGrid);
      add(centerPanel);
      add(opponentGrid);
   }

   /**
   * Adds a pokemon to the player's list of pokemon. Does nothing if player's
   *  pokemon grid is full
   * @param newPokemon The pokemon object to add
   */
   public void addPokemon(Pokemon newPokemon) {
      if (numPlayerPokemon < playerPokemons.length) {
         // Add pokemon to playerPokemons
         playerPokemons[numPlayerPokemon] = newPokemon;

         // Increment number of pokemon
         numPlayerPokemon++;

         // Add progress
         addProgress.accept(newPokemon.getLevel());

         // disable battle button if it was enabled
         battleButton.setEnabled(false);

         // Update player's grid with new pokemon
         updatePlayerGrid();
      }
   }

   /**
    * Generates as many random opponent pokemons as the player has pokemons
    *  and has them battle by comparing levels. The opponent pokemon's levels
    *  will be within 5 levels of the pokemon it's battling.
    */
   private void generateOpponent() {
      // generate the opponent's pokemons
      opponentPokemons = generateOpponents(numPlayerPokemon);

      // Update opponent's grid with their pokemon
      updateOpponentGrid(opponentPokemons);

      // Enable battle button
      battleButton.setEnabled(true);
   }

   private void battleOpponent() {
      // compare levels and count wins
      // win = player level > opponent level
      int wins = 0;
      for (int i = 0; i < numPlayerPokemon; i++)
         if (playerPokemons[i].getLevel() > opponentPokemons[i].getLevel())
            wins++;
      
      // If player won > 50% of games,
      // winners gain (player level - opponent level) levels
      // losers gain 1 level
      if (wins >= Math.ceil(numPlayerPokemon / 2.0)) {
         // Tell user they won and by how much in battle feedback
         StringBuilder results = new StringBuilder("<html>Your team won " + wins + " to " + (numPlayerPokemon - wins) + "<br><br>");

         // Award levels to pokemon
         for (int i = 0; i < numPlayerPokemon; i++) {
            int playerLvl = playerPokemons[i].getLevel();
            int opponentLvl = opponentPokemons[i].getLevel();
            int pointsGained = 0;

            if (playerLvl > opponentLvl) {
               pointsGained = playerPokemons[i].addLevels(playerLvl - opponentLvl);
            } else {
               pointsGained = playerPokemons[i].addLevels(1);
            }

            addProgress.accept(pointsGained);
            if (pointsGained > 0)
               results.append(playerPokemons[i].getName() + " (level "
                  + (playerPokemons[i].getLevel() - pointsGained) + ") gained " + pointsGained + " points.<br>");
         }

         // set battle feedback to display feedback
         battleFeedback.setText(results.toString() + "</>");
      } else {
         // Inform user they lost
         battleFeedback.setText("Your team lost " + wins + " to " + (numPlayerPokemon - wins));
      }

      // Update player's grid with level gain
      updatePlayerGrid();

      // Disable battle button
      battleButton.setEnabled(false);

      // Disable find opponents button and enable battle champion button once reach 100%
      
   }

   private Pokemon[] generateOpponents(int numOpponents) {
      Pokemon[] opponentPokemons = new Pokemon[numOpponents];

      for (int i = 0; i < opponentPokemons.length; i++) {
         int minOppLvl = Math.max(1, playerPokemons[i].getLevel() - 5);
         int maxOppLvl = Math.min(Pokemon.MAX_LVL, playerPokemons[i].getLevel() + 5);
         opponentPokemons[i] = Pokemon.generateRandomPokemon(minOppLvl, maxOppLvl);
      }

      return opponentPokemons;
   }

   /**
    * Updates the GUI to match the pokemon in playerPokemons
    */
   private void updatePlayerGrid() {
      for (int i = 0; i < 6; i++) {
         JLabel pokemonImage = (JLabel) playerGrid.getComponent(i);
         if (playerPokemons[i] == null)
            setVoid(pokemonImage);
            //pokemonImage.setText("No pokemon yet!");
         else
            updateImage(pokemonImage, playerPokemons[i]);
      }
   }

   private void updateOpponentGrid(Pokemon[] opponentPokemons) {
      for (int i = 0; i < opponentPokemons.length; i++) {
         JLabel pokemonImage = (JLabel) opponentGrid.getComponent(i);
         if (opponentPokemons[i] == null) {
            pokemonImage.setText(null);
         }
         else
            updateImage(pokemonImage, opponentPokemons[i]);
      }
      for (int i = opponentPokemons.length; i < 6; i++) {
         setVoid(((JLabel) opponentGrid.getComponent(i)));
      }
   }

   /**
    * Sets all grid items in opponent's grid to empty
    */
   private void resetOpponentGrid() {
      for (int i = 0; i < 6; i++) {
         setVoid(((JLabel) opponentGrid.getComponent(i)));
      }
   }
  
   /**
   * Updates the content of the given JLabel with the image and level of the
   *  given pokemon, handling invalid images.
   * @param imageLabel the JLabel whose content to set
   * @param pokemon the pokemon to update the JLabel with
   */
   private static void updateImage(JLabel imageLabel, Pokemon pokemon) {
      try 
      {
         BufferedImage image = ImageIO.read(new File(pokemon.getImageFilename()));
         
         if (image == null)
         {
            imageLabel.setText("Image could not be read");
         }
         
         Image scaledImage = image.getScaledInstance(-1,150, Image.SCALE_SMOOTH);
         
         imageLabel.setText("Level " + pokemon.getLevel());
         imageLabel.setHorizontalTextPosition(JLabel.CENTER);
         imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
         imageLabel.setIcon(new ImageIcon(scaledImage));
         
      } catch (IOException exception) {
         imageLabel.setText("Could not load " + pokemon.getImageFilename());
      }        
   }

   private static void setVoid(JLabel imageLabel) {
      try 
      {
         BufferedImage image = ImageIO.read(new File("images/pokeball.png"));
         
         if (image == null)
         {
            imageLabel.setText("Image could not be read");
         }
         
         Image scaledImage = image.getScaledInstance(150,-1, Image.SCALE_SMOOTH);
         
         imageLabel.setText(null);
         imageLabel.setIcon(new ImageIcon(scaledImage));
         
      } catch (IOException exception) {
         imageLabel.setText("Could not load images/pokeball.png");
      }      
   }
}
