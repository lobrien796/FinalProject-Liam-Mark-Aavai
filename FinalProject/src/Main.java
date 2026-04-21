import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main extends JFrame {
    private MazeGenerator maze = new MazeGenerator(5);
    private CardLayout cl = new CardLayout();
    private JPanel cards = new JPanel(cl);

    public Main() {
        super("Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 375);
        setLocationRelativeTo(null);
        
        cards.add(createMazeCard("Easy Maze"), "easyMaze");
        cards.add(createMazeCard("Medium Maze"), "mediumMaze");
        cards.add(createMazeCard("Hard Maze"), "hardMaze");

        JPanel welcomeScreen = new JPanel(new GridLayout(1, 3, 5, 5));
        welcomeScreen.setBorder(new EmptyBorder(15, 15, 15, 15));

        JButton easyButton = new JButton("- EASY -");
        JButton mediumButton = new JButton("- MEDIUM -");
        JButton hardButton = new JButton("- HARD -");
        
     
        
        
        easyButton.addActionListener(e -> {
            maze.setSize(5);
            maze.generate();
            cl.show(cards, "easyMaze");
            
            int size = maze.getSize();
            char[][] array = maze.getMaze();
            int x = 0;
            int y = 0;
            for (int i = 0; i < size; i++) {
            	for (int i2 = 0; i2 < size; i2++) {
            		char cha = array[x][y]; 
            		System.out.print(cha);
            		x++;
            	}
            	y++;
            	x=0;
            }
        });

        mediumButton.addActionListener(e -> {
            maze.setSize(10);
            maze.generate();
            cl.show(cards, "mediumMaze");
        });

        hardButton.addActionListener(e -> {
            maze.setSize(30);
            maze.generate();
            cl.show(cards, "hardMaze");
        });

        welcomeScreen.add(easyButton);
        welcomeScreen.add(mediumButton);
        welcomeScreen.add(hardButton);

        cards.add(welcomeScreen, "menu");
        cl.show(cards, "menu");

        add(cards);

        setupKeyBindings();
        
    }

    private void setupKeyBindings() {
        InputMap im = cards.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = cards.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "walkAction");
        am.put("walkAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up arrow was pressed");
                if (maze != null) {
                    maze.walk();
                }
            }
        });
    }

    private JPanel createMazeCard(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text, SwingConstants.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cl.show(cards, "menu"));
        
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
    
}
