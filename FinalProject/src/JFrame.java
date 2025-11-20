import java.awt.*;

public class JFrame extends Main
{
  public JFrame(String string) {
		// TODO Auto-generated constructor stub
	}

  public void paintComponent(Graphics g)
  {
	  int width = getWidth();
	  int height = getHeight();
	  int yCenter = height/2;
	  int xCenter = width/2;

        super.paintComponent(g);  // Call JPanel's paintComponent method
                              //  to paint the background
     g.setColor(Color.RED);

// Draw a 150 by 45 rectangle with the upper-left
// corner at x = 20, y = 40:
     g.drawRect(xCenter - 75, yCenter - 20 , 150, 40);
    
   // g.setColor(Color.BLUE);
        g.setColor(Color.RED);

    // Draw a string of text starting at x = 55, y = 65:
    g.drawString("I hate drawing Graphics!", xCenter - 65, yCenter+5);
  }
}
