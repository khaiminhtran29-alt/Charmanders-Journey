/**
 * 
 */
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
   private static final int GAP = 8;
   private static final int MAX_PROGRESS = Pokemon.MAX_LVL * 6;
   
   private final JProgressBar progressBar;
   
   public GamePanel() { 
      setLayout(new BorderLayout(GAP, GAP));
      
      // Shared progress bar for catches and battle wins
      progressBar = new JProgressBar(0,MAX_PROGRESS);
      progressBar.setValue(0);
      progressBar.setStringPainted(true);
      updateProgressText();
      add(progressBar, BorderLayout.NORTH);
      
      // Each panel reports progress through addProgress
      BattlePanel battlePanel = new BattlePanel(this::addProgress);
      CatchPanel catchPanel = new CatchPanel(battlePanel::addPokemon);
      
      JPanel playArea = new JPanel();
      playArea.setLayout(new BoxLayout(playArea, BoxLayout.X_AXIS));
      playArea.add(catchPanel);
      playArea.add(battlePanel);
      
      add(playArea, BorderLayout.CENTER);
   }
   
   private void addProgress(int amount) {
      int newValue = Math.min(MAX_PROGRESS, progressBar.getValue() + amount);
      progressBar.setValue(newValue);
      updateProgressText();
   }
   
   private void updateProgressText() {
      String progress = String.format("Journey: %.2f%%", progressBar.getPercentComplete() * 100);
      progressBar.setString(progress);
   }
}
