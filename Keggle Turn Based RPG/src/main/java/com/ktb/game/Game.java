package main.java.com.ktb.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.java.com.ktb.character.Keggle;
 
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
        emptyLabel.setPreferredSize(new Dimension(1200, 600));
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		ClassLoader classLoader = new Game().getClass().getClassLoader();
		Game game = new Game();
		ArrayList<main.java.com.ktb.character.Character> characters = new ArrayList<>();
		Keggle player = new Keggle();
		characters.add(player);
		for(main.java.com.ktb.character.Character character:characters) {
			try {
				BufferedImage image = ImageIO.read(new File(classLoader.getResource(character.getImage()).getFile().toString().replaceAll("%20", " ")));
				JLabel picLabel = new JLabel(new ImageIcon(image));
				frame.add(picLabel);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		frame.setVisible(true);
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
