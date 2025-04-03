import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Creates the main window
        JFrame frame = new JFrame("Textuality");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLayout(new BorderLayout());=

        // Creates Panels
        JPanel playerSheet = new JPanel();
        JPanel npmSheet = new JPanel();
        JPanel mainTArea = new JPanel();

        playerSheet.setPreferredSize(new Dimension(250, 600)); //25% width
        npmSheet.setPreferredSize(new Dimension(250, 600)); // 25% width
        mainTArea.setPreferredSize(new Dimension(300, 600)); //50% width

        // Set bg colors for visibility
        playerSheet.setBackground(new Color(200, 200, 200)); //light grey
        npmSheet.setBackground(new Color(200, 200, 200));
        mainTArea.setBackground(new Color(100, 100, 100)); //dark grey

        // Add panels to the frame
        frame.add(playerSheet, BorderLayout.WEST);
        frame.add(npmSheet, BorderLayout.EAST);
        frame.add(mainTArea, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}