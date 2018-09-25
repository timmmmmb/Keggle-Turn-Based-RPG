package main.java.com.ktb.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.ktb.character.Keggle;
import main.java.com.ktb.character.Rat;
import main.java.com.ktb.skills.Skill;
 
public class Game extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private java.util.Timer timer;
	private boolean isRunning = true;
	private final JPanel panel = new JPanel();
	private ArrayList<main.java.com.ktb.character.Character> characters = new ArrayList<>();
	private List<JButton> buttons= new ArrayList<JButton>();
	private JLabel label = new JLabel();
	/**
	 * 0 = playerturn
	 * 1 = npc turn
	 */
	private int gamestate = 0;
	
	/**
	 * This class starts the game and displays the images.
	 * @param args
	 */
	public Game() {
		panel.setLayout(null);
	    this.add(panel);
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,600);
        
        addCharacters();
        
        loadCharacterImages();
		
		gameLoop();
	}
	
	public void setGameState(int newstate) {
		gamestate = newstate;
	}
	
	public int getGameState() {
		return gamestate;
	}
	
	private void loadCharacterImages() {
		for(main.java.com.ktb.character.Character character:characters) {
			label.setBounds(100, 150, 500, 40);
			label.setAlignmentX(100);
			label.setAlignmentY(100);
			panel.add(label);
			//add the character image
			panel.add(character.getImage());
			//add the characters health 
			panel.add((character).getHealthBarImage());
			panel.add((character).getHealthImage());
			//add the characters manabar if its a playable character
			if(character.getPlayablecharacter()) {	
				panel.add((character).getManaBarImage());
				panel.add((character).getManaImage());	
				int i = 0;
				for (Skill skill: character.getSkills())
				{
				    JButton btn = new JButton(skill.getName());
				    btn.setToolTipText(skill.getTooltipp());
				    btn.addActionListener(this);
				    btn.setBounds(100+i, 100, 100, 20);
				    btn.setAlignmentX(100);
				    btn.setAlignmentY(100);
				    panel.add(btn);
				    buttons.add(btn);
				    i=i+110;
				}
			}
			
		}
		paint();
	}
	
	private void addCharacters() {
		Keggle player = new Keggle();
		characters.add(player);
		Rat rat = new Rat();
		characters.add(rat);
	}
	
	private void gameLoop()
	{
	    timer = new Timer();
	    timer.schedule(new LoopyStuff(), 0, 1000 / 30); //new timer at 60 fps, the timing mechanism
	}
	
	private void paint() {
		panel.validate();
        this.repaint();
        this.setVisible(true);
	}

	private void executeAi() {
		if(gamestate == 1) {
    		for(main.java.com.ktb.character.Character character:characters) {
    			if(!character.getPlayablecharacter()) {
    				for(main.java.com.ktb.character.Character target:characters) {
            			if(target.getPlayablecharacter()) {
            					character.aiTurn(target,this);
            			}
    				}
    			}
    		}
    	}
	}

	private class LoopyStuff extends java.util.TimerTask
	{
	    public void run() //this becomes the loop
	    {
	        //do game updates
	    	for(main.java.com.ktb.character.Character character:characters) {				
				character.animate();
			}
	    	
	    	executeAi();
	    	
	    	//render the game
	    	paint();
	    	
	        if (!isRunning)
	        {
	            timer.cancel();
	        }
	        
	    }
	}


	@Override
	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        for(JButton button: this.buttons) {
        	if(button == ae.getSource()) {
        		for(main.java.com.ktb.character.Character character:characters) {
        			if(character.getPlayablecharacter()) {
        				for (Skill skill: character.getSkills()){
        					if(skill.getName().equals(button.getText())) {
        						for(main.java.com.ktb.character.Character target:characters) {
        		        			if(target.getTargetedablecharacter()) {
        		        				skill.activate(target, character, this);
        		        				return;
        		        			}	
        						}	
        					}
        				}
        			}
        		}
        	}
        }
	}
}
