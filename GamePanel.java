/**
*

*/
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class GamePanel extends JPanel
{
  private CatchPanel cp;
  private BattlePanel bp;
  
  
  public GamePanel()
  {  
     // Give components a horizontal layout 
     setLayout(new BoxLayout (this, BoxLayout.X_AXIS));
     
     //Initializes CatchPanel and BattlePanel
     cp = new CatchPanel();
     bp = new BattlePanel();
     
     cp.setBackground(Color.blue);
     
     add(cp);
     add(bp);
     
  }
}