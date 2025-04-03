import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Creates the main window
        JFrame frame = new JFrame("Textuality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLayout(new CardLayout()); // Use CardLayout for screen switching

        // Opening screen
        JPanel openingScreen = new JPanel(new BorderLayout());
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        openingScreen.add(startButton, BorderLayout.CENTER);

        // Main UI screen
        JPanel mainUIScreen = new JPanel(new BorderLayout());

        // Creates Panels
        JPanel playerSheet = new JPanel();
        JPanel npmSheet = new JPanel();
        JPanel mainTArea = new JPanel();

        playerSheet.setPreferredSize(new Dimension(250, 600)); // 25% width
        npmSheet.setPreferredSize(new Dimension(250, 600)); // 25% width
        mainTArea.setPreferredSize(new Dimension(300, 600)); // 50% width

        // Set bg colors for visibility
        playerSheet.setBackground(new Color(200, 200, 200)); // light grey
        npmSheet.setBackground(new Color(200, 200, 200));
        mainTArea.setBackground(new Color(100, 100, 100)); // dark grey
        mainTArea.setForeground(Color.WHITE);
        mainTArea.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add panels to the main UI screen
        mainUIScreen.add(playerSheet, BorderLayout.WEST);
        mainUIScreen.add(npmSheet, BorderLayout.EAST);
        mainUIScreen.add(mainTArea, BorderLayout.CENTER);

        // Add button to NPC Panel
        JButton addNPCButton = new JButton("Generate NPC");
        JLabel npcInfo = new JLabel("Click to generate NPC");
        npcInfo.setHorizontalAlignment(SwingConstants.CENTER);
        npmSheet.setLayout(new BorderLayout());
        npmSheet.add(addNPCButton, BorderLayout.NORTH);
        npmSheet.add(npcInfo, BorderLayout.CENTER);

        // Add to main area
        JTextArea mainText = new JTextArea("Game Text Here");
        mainText.setEditable(false);
        mainText.setLineWrap(true);
        mainText.setWrapStyleWord(true);
        mainTArea.setLayout(new BorderLayout()); // Ensure proper layout
        mainTArea.add(new JScrollPane(mainText), BorderLayout.CENTER); // Add scrollable text area

        // Button click event - generates random NPC
        addNPCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterGen.NPC randomCharacter = CharacterGen.generateCharacter(); // Fully qualify NPC class

                npcInfo.setText(randomCharacter.name + " - " + randomCharacter.traits + " - " + randomCharacter.age);
                mainText.setText("You have encountered " + randomCharacter.name + ".\n" +
                        "Traits: " + randomCharacter.traits + "\n" +
                        "Age: " + randomCharacter.age + "\n");
            }
        });

        // Add screens to the frame
        frame.add(openingScreen, "OpeningScreen");
        frame.add(mainUIScreen, "MainUIScreen");

        // Start button action to switch to the main UI
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "MainUIScreen");
            }
        });

        frame.setVisible(true);
    }
}