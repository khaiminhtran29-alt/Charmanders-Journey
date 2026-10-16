/**
 * 
 */
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
   private static final int GAP = 8;
   private static final int MAX_PROGRESS = 100;
   
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
      CatchPanel catchPanel = new CatchPanel(this::addProgress);
      BattlePanel battlePanel = new BattlePanel(this::addProgress);
      
      JPanel playArea = new JPanel(new GridLayout(1, 2, GAP, 0));
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
      progressBar.setString("Journey: " + progressBar.getValue() + "%");
   }
}
