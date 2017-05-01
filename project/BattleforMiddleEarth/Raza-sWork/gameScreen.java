package myPart;

import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Canvas;


public class gameScreen extends Canvas{



	private static final long serialVersionUID = 8884085376231254675L;

	public gameScreen(Game game , String gameName ,int height , int width)
	{
		JFrame frame = new JFrame(gameName); // the frame of our window
		
		frame.setPreferredSize(new Dimension(width , height));
		frame.setMaximumSize(new Dimension(width , height));
		frame.setMinimumSize(new Dimension(width , height));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//the 'X' button on the top left cornor
		frame.setResizable(false); // user canot reseize the window
		frame.setLocationRelativeTo(null);
		frame.add(game);// without serial it doesnot work
		frame.setVisible(true);
		game.start();

		
	}

}
