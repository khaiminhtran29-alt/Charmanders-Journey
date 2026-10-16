/**

*/

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class CharmanderJourney {

    public static void main(String[] args) {
        
            JFrame frame = new JFrame("Charmander's Journey");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new GamePanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        
    }
}