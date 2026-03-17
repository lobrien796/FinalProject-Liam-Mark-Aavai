import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cl = new CardLayout();
        JPanel cards = new JPanel(cl);

        cards.add(createMazeCard("Easy Maze", cl, cards), "easyMaze");
        cards.add(createMazeCard("Medium Maze", cl, cards), "mediumMaze");
        cards.add(createMazeCard("Hard Maze", cl, cards), "hardMaze");

        JPanel welcomeScreen = new JPanel(new GridLayout(1, 3, 5, 5));
        welcomeScreen.setBorder(new EmptyBorder(15, 15, 15, 15));

        JButton easyButton = new JButton("- EASY -");
        JButton mediumButton = new JButton("- MEDIUM -");
        JButton hardButton = new JButton("- HARD -");

        easyButton.addActionListener(e -> cl.show(cards, "easyMaze"));
        mediumButton.addActionListener(e -> cl.show(cards, "mediumMaze"));
        hardButton.addActionListener(e -> cl.show(cards, "hardMaze"));

        welcomeScreen.add(easyButton);
        welcomeScreen.add(mediumButton);
        welcomeScreen.add(hardButton);

        cards.add(welcomeScreen, "menu");
        cl.show(cards, "menu");

        frame.add(cards);
        frame.setSize(new Dimension(600, 375));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createMazeCard(String text, CardLayout cl, JPanel container) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text, SwingConstants.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cl.show(container, "menu"));
        
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }
}
