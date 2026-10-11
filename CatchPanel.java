/**
*

*/
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;

public class CatchPanel extends JPanel
{
  private JTextField lvl;
   
  public CatchPanel()
  {   
     setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
     
     
     setBorder(BorderFactory.createLineBorder(Color.black));
     
     
     // Instiates all of the child components
     
      lvl = new JTextField("The level of the Pokemon is: ");
      lvl.setBackground(Color.red);
      
      // Add child component to the frame
      
      add(lvl);
      
  }
}