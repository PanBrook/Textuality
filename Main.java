import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Constants for dimensions
        final int LEFT_PANEL_WIDTH = 250;
        final int RIGHT_PANEL_WIDTH = 250;
        final int CENTER_PANEL_WIDTH = 300;
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 600;

        // Variables to track game state
        int[] gameDay = {1}; // Use an array to allow modification inside lambdas
        int[] storedWood = {0};
        int[] storedStone = {0};
        int[] currentEnergy = {20};
        int[] currentPopulation = {0};
        int[] maxPopulation = {5};
        int[] maxEnergy = {20};

        // Create player sheet panel at top-level scope
        JPanel playerSheet = new JPanel();

        // Method to update the player sheet
        Runnable updatePlayerSheet = () -> {
            playerSheet.removeAll();
            playerSheet.setLayout(new BoxLayout(playerSheet, BoxLayout.Y_AXIS));
            playerSheet.add(new JLabel("Day: " + gameDay[0]));
            playerSheet.add(new JLabel("Wood: " + storedWood[0]));
            playerSheet.add(new JLabel("Stone: " + storedStone[0]));
            playerSheet.add(new JLabel("Energy: " + currentEnergy[0] + "/" + maxEnergy[0]));
            playerSheet.add(new JLabel("Population: " + currentPopulation[0] + "/" + maxPopulation[0]));
            playerSheet.revalidate();
            playerSheet.repaint();
        };

        // Creates the main window
        JFrame frame = new JFrame("Textuality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLayout(new CardLayout()); // Use CardLayout for screen switching

        // Opening screen
        JPanel openingScreen = new JPanel(new BorderLayout());
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        openingScreen.add(startButton, BorderLayout.CENTER);
        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "MainUIScreen");
        });

        // Main UI screen
        JPanel mainUIScreen = new JPanel(new BorderLayout());
        JPanel buttonArea = new JPanel(new GridLayout(0, 1, 10, 10)); // Grid layout for buttons
        JPanel mainTArea = new JPanel();

        buttonArea.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, FRAME_HEIGHT)); // 25% width
        mainTArea.setPreferredSize(new Dimension(CENTER_PANEL_WIDTH, FRAME_HEIGHT)); // 50% width

        buttonArea.setBackground(new Color(200, 200, 200));
        mainTArea.setBackground(new Color(100, 100, 100)); // dark grey

        JTextArea mainText = new JTextArea("Game Text Here");
        mainText.setEditable(false);
        mainText.setLineWrap(true);
        mainText.setWrapStyleWord(true);
        mainTArea.setLayout(new BorderLayout());
        mainTArea.add(new JScrollPane(mainText), BorderLayout.CENTER);

        JButton forestButton = new JButton("Go to Forest");
        forestButton.setFont(new Font("Arial", Font.BOLD, 24));
        JButton passDayButton = new JButton("Pass Day");
        passDayButton.setFont(new Font("Arial", Font.BOLD, 24));
        passDayButton.addActionListener(e -> {
            gameDay[0]++;
            currentEnergy[0] = maxEnergy[0]; // Reset energy each day
            mainText.setText("Day " + gameDay[0] + " has begun.");
            updatePlayerSheet.run();
        });

        buttonArea.add(forestButton);
        buttonArea.add(passDayButton);

        mainUIScreen.add(buttonArea, BorderLayout.EAST);
        mainUIScreen.add(mainTArea, BorderLayout.CENTER);

        // Forest screen
        JPanel forestScreen = new JPanel(new BorderLayout());
        JPanel forestButtonArea = new JPanel(new GridLayout(0, 1, 10, 10)); // Grid layout for buttons
        JPanel forestMainTArea = new JPanel();

        forestButtonArea.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, FRAME_HEIGHT)); // 25% width
        forestMainTArea.setPreferredSize(new Dimension(FRAME_WIDTH - LEFT_PANEL_WIDTH - RIGHT_PANEL_WIDTH, FRAME_HEIGHT)); // Remaining width

        forestButtonArea.setBackground(new Color(200, 255, 200)); // light green
        forestMainTArea.setBackground(new Color(100, 200, 100)); // dark green

        JButton gatherWoodButton = new JButton("Gather Wood");
        gatherWoodButton.setFont(new Font("Arial", Font.BOLD, 24));
        gatherWoodButton.addActionListener(e -> {
            if (currentEnergy[0] > 0) {
                storedWood[0]++;
                currentEnergy[0]--;
                mainText.setText("You gathered wood. Total wood: " + storedWood[0]);
                updatePlayerSheet.run();
            } else {
                mainText.setText("You are too tired to gather wood. Rest to regain energy.");
            }
        });

        JButton gatherStoneButton = new JButton("Gather Stone");
        gatherStoneButton.setFont(new Font("Arial", Font.BOLD, 24));
        gatherStoneButton.addActionListener(e -> {
            if (currentEnergy[0] > 0) {
                storedStone[0]++;
                currentEnergy[0]--;
                mainText.setText("You gathered stone. Total stone: " + storedStone[0]);
                updatePlayerSheet.run();
            } else {
                mainText.setText("You are too tired to gather stone. Rest to regain energy.");
            }
        });

        JButton returnButton = new JButton("Return to Main");
        returnButton.setFont(new Font("Arial", Font.BOLD, 24));
        returnButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "MainUIScreen");
        });

        forestButtonArea.add(gatherWoodButton);
        forestButtonArea.add(gatherStoneButton);
        forestButtonArea.add(returnButton);

        forestScreen.add(forestButtonArea, BorderLayout.EAST);
        forestScreen.add(forestMainTArea, BorderLayout.CENTER);

        forestButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "ForestScreen");
        });

        // Add screens to the frame
        JPanel mainUIScreenWrapper = new JPanel(new BorderLayout());
        mainUIScreenWrapper.add(playerSheet, BorderLayout.WEST);
        mainUIScreenWrapper.add(mainUIScreen, BorderLayout.CENTER);

        JPanel forestScreenWrapper = new JPanel(new BorderLayout());
        forestScreenWrapper.add(playerSheet, BorderLayout.WEST);
        forestScreenWrapper.add(forestScreen, BorderLayout.CENTER);

        // Fix: Avoid redundant addition of `playerSheet` to multiple wrappers
        frame.add(openingScreen, "OpeningScreen");
        frame.add(mainUIScreenWrapper, "MainUIScreen");
        frame.add(forestScreenWrapper, "ForestScreen");

        updatePlayerSheet.run(); // Initial update of player sheet
        frame.setVisible(true);
    }
}