package ktb.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {
	private java.util.Timer timer;
	private boolean isRunning;
	
	/**
	 * This class starts the game and displays the images.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("KTB");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		Game game = new Game();
		game.gameLoop();
	}
	
	private void gameLoop()
	{
	    timer = new Timer();
	    timer.schedule(new LoopyStuff(), 0, 1000 / 60); //new timer at 60 fps, the timing mechanism
	}

	private class LoopyStuff extends java.util.TimerTask
	{
	    public void run() //this becomes the loop
	    {
	        //do game updates
	    	//render the game

	        if (!isRunning)
	        {
	            timer.cancel();
	        }
	    }
	}
	
}
