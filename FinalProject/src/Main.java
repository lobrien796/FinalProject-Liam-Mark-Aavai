import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class Main extends JFrame {
    private MazeGenerator maze = new MazeGenerator(5);
    private CardLayout cl = new CardLayout();
    private JPanel cards = new JPanel(cl);
    boolean draw = false;
    
    
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
            draw = true;
            repaint();
            
        });

        mediumButton.addActionListener(e -> {
            maze.setSize(10);
            maze.generate();
            cl.show(cards, "mediumMaze");
            draw = true;
            repaint();
            
        });

        hardButton.addActionListener(e -> {
            maze.setSize(30);
            maze.generate();
            cl.show(cards, "hardMaze");
            draw = true;
            repaint();
            
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
            JFrame window = new Main();
            window.setVisible(true);
            window.setIconImage(icon);
        });
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	  int size = maze.getSize();
          char[][] array = maze.getMaze();
          
          int windowWidth = cards.getWidth();
          int windowHeight= cards.getHeight();
          int blockSize = (int)(windowWidth/2);
         
          blockSize =  Math.max(1,(blockSize/size));
          int mazeSize = blockSize * size;
          int x = (windowWidth/2) - (mazeSize/2);
          int y =40;
          if (draw) {
	          for (int i = 0; i < size; i++) {
	          	for (int i2 = 0; i2 < size; i2++) {
	          		char cha = array[i][i2]; 
	          		
	          		if (cha == '—') {
	          			g.setColor(Color.black);
	          			g.fillRect(x, y, blockSize, blockSize);
	          		} else if (cha == '|' ) {
	          			g.setColor(Color.black);
	      				g.fillRect(x, y, blockSize, blockSize);
	          		} else if (cha == '+') {
	          			g.setColor(Color.WHITE);
	          			g.fillRect(x, y, blockSize, blockSize);
	          		} else if (cha == 'S') {
	          			g.setColor(Color.GREEN);
	          			g.fillRect(x, y, blockSize, blockSize);
	          		} else if (cha == 'E') {
	          			g.setColor(Color.RED);
	          			g.fillRect(x, y, blockSize, blockSize);
	          		}
	          		x = x + blockSize;
	          	}
	              
	              y = y + blockSize;
	              x = windowWidth/2 - mazeSize/2;
	          	
	          }
	          
          }   
    }
    
}
