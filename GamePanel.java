/**
 * 
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel
{
   private static final int GAP = 8;
   private static final int MAX_PROGRESS = Pokemon.MAX_LVL * 6;
   
   private final JProgressBar progressBar;
   private final JButton championButton;
   
   public GamePanel() { 
      setLayout(new BorderLayout(GAP, GAP));
      
      //Creating the parent container
      JPanel parent = new JPanel();
      parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));

      // Shared progress bar for catches and battle wins
      progressBar = new JProgressBar(0,MAX_PROGRESS);
      progressBar.setValue(0);
      progressBar.setStringPainted(true);
      updateProgressText();
      //
      championButton = createChampionButton("images/Diantha.png");
      championButton.setEnabled(false);
      parent.add(progressBar);
      parent.add(championButton);
      add(parent, BorderLayout.NORTH);

      // Each panel reports progress through addProgress
      BattlePanel battlePanel = new BattlePanel(this::addProgress);
      CatchPanel catchPanel = new CatchPanel(battlePanel::addPokemon);

      // Connect the champion button to battle panel
      championButton.addActionListener(event -> battlePanel.generateChampion());
      
      JPanel playArea = new JPanel();
      playArea.setLayout(new BoxLayout(playArea, BoxLayout.X_AXIS));
      playArea.add(catchPanel);
      playArea.add(battlePanel);
      
      add(playArea, BorderLayout.CENTER);
   }

      private static JButton createChampionButton(String fileName) {
      try 
      {
         // Risky code-- try reading the current pokemon's image file
         BufferedImage image = ImageIO.read(new File(fileName));
         
         // If the image is null, set the image label text to an error message
         if (image == null)
            return new JButton("Battle Champion");
         
         // If the image is valid, set the image label icon to it, scaled
         Image scaledImage = image.getScaledInstance(-1,100, Image.SCALE_SMOOTH);
         return new JButton("Battle Champion", new ImageIcon(scaledImage));

      } catch (IOException exception) {
         // If an exception occurs, set image label text to an error message
          return new JButton("Battle Champion");
      }        
   }
   
   private void addProgress(int amount) {
      int newValue = Math.min(MAX_PROGRESS, progressBar.getValue() + amount);
      progressBar.setValue(newValue);
      updateProgressText();

      if (progressBar.getPercentComplete() >= .8)
         championButton.setEnabled(true);
   }
   
   private void updateProgressText() {
      String progress = String.format("Journey: %.2f%%", progressBar.getPercentComplete() * 100);
      progressBar.setString(progress);
   }
}
