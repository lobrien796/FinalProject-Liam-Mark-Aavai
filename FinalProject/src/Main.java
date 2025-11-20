//  This program is the main window for the program
//  Authors: Aavai Tukhar, Liam O'Brien, Mark Nygaard
//  Rev    : 1
//  Notes  :
//  Date   : 11/20/25
import java.awt.*;
import javax.swing.*;


public class Main 
{

	 public static Main panel;  // the window has a panel
	
	 public static void main(String[] args)
	 {	
		int width = 400;
		int height = 300;
	    JFrame window = new JFrame("Graphics Demo");
	    // Set this window's location and size:
	    // upper-left corner at 300, 300; width 200, height 150
	    window.setBounds(100, 100, width, height);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    JFrame panel = new JFrame();  // creating the panel
	    panel.setBackground(Color.BLUE);  // the default color is light gray
	    Container c = window.getContentPane();
	    c.add(panel); // adding the panel to the window
	
	    window.setVisible(true);  // make the window and the panel visible

	  }
}
