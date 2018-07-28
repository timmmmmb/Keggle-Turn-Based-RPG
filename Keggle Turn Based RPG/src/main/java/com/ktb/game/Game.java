package main.java.com.ktb.game;

import java.util.ArrayList;
import java.util.Timer;


import javax.swing.JFrame;


import main.java.com.ktb.character.Keggle;
 
public class Game {
	private java.util.Timer timer;
	private boolean isRunning = true;
	private JFrame frame = new JFrame("KTB");
	private ArrayList<main.java.com.ktb.character.Character> characters = new ArrayList<>();
	/**
	 * This class starts the game and displays the images.
	 * @param args
	 */
	public Game() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,600);
        
        addCharacters();
        
        loadCharacterImages();
		
		gameLoop();
	}
	
	private void loadCharacterImages() {
		for(main.java.com.ktb.character.Character character:characters) {				
			frame.add(character.getImage());
		}
	}
	
	private void addCharacters() {
		Keggle player = new Keggle();
		characters.add(player);
	}
	
	private void gameLoop()
	{
	    timer = new Timer();
	    timer.schedule(new LoopyStuff(), 0, 1000 / 30); //new timer at 60 fps, the timing mechanism
	}

	private class LoopyStuff extends java.util.TimerTask
	{
	    public void run() //this becomes the loop
	    {
	        //do game updates
	    	for(main.java.com.ktb.character.Character character:characters) {				
				character.animate();
			}
	    	
	    	//render the game
	    	frame.setVisible(true);
	    	
	        if (!isRunning)
	        {
	            timer.cancel();
	        }
	        
	    }
	}
	
}
