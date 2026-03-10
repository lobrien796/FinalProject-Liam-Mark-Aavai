import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static Point initialClick;

    public static void main(String[] args) {
        JWindow window = new JWindow();
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(Color.WHITE);

        window.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        window.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = window.getLocation().x;
                int thisY = window.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                window.setLocation(thisX + xMoved, thisY + yMoved);
            }
        });

        JPanel welcomeButtons = new JPanel(new GridLayout(2,3));
        JButton easyButton = new JButton("- EASY -");
        JButton mediumButton = new JButton("- MEDIUM -");
        JButton hardButton = new JButton("- HARD -");
        welcomeButtons.add(easyButton);
        welcomeButtons.add(mediumButton);
        welcomeButtons.add(hardButton);

        window.add(welcomeButtons);

        
        window.setVisible(true);
        
    }
}
