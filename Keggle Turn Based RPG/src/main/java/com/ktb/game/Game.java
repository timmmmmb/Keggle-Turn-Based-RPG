package main.java.com.ktb.game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
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
	private JPanel fightpanel = new JPanel();
	private ArrayList<main.java.com.ktb.character.Character> characters = new ArrayList<>();
	private List<JButton> buttons= new ArrayList<JButton>();
	private JLabel label = new JLabel();
	private JPanel menupanel = new JPanel();
	/**
	 * 0 = playerturn
	 * 1 = npc turn
	 * 2 = player lost
	 * 3 = player won
	 */
	private int gamestate = 0;
	
	/**
	 * This class starts the game and displays the images.
	 */
	public Game() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,600);
        startMenu();
	}
	/**
	 * This Function adds the Startmenu
	 */
	private void startMenu() {
		GridLayout grid = new GridLayout(3,1);
		grid.setHgap(10);
		grid.setVgap(10);
		menupanel.setLayout(grid);
		JButton buttonvs1 = new JButton();
		JButton buttonvs2 = new JButton();
		JButton buttonexit = new JButton();
		buttonvs1.addActionListener(new java.awt.event.ActionListener() {
            // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	startFight(1);
            }});
		buttonvs2.addActionListener(new java.awt.event.ActionListener() {
            // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	startFight(2);
            }});
		buttonexit.addActionListener(new java.awt.event.ActionListener() {
            // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	System.exit(0);
            }});
		buttonvs1.setText("Fight VS 1 Rat");
		buttonvs2.setText("Fight VS 2 Rat");
		buttonexit.setText("Exit");
		menupanel.add(buttonvs1);
		menupanel.add(buttonvs2);
		menupanel.add(buttonexit);
		this.add(menupanel);
		paint();
	}
	
	/**
	 * This function creates the fight panel
	 * @param amount is the amount of to be created monsters
	 */
	private void startFight(int amount) {
		removePanels();
		fightpanel = new JPanel();
		fightpanel.setLayout(null);
		this.add(fightpanel);
		addCharacters(amount);
		gameLoop();
	}
	
	private void removePanels() {
		this.remove(fightpanel);
		this.remove(menupanel);
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
			fightpanel.add(label);
			//add the character image
			fightpanel.add(character.getImage());
			//add the characters health 
			fightpanel.add((character).getHealthBarImage());
			fightpanel.add((character).getHealthImage());
			//add the characters manabar if its a playable character
			if(character.getPlayablecharacter()) {	
				fightpanel.add((character).getManaBarImage());
				fightpanel.add((character).getManaImage());	
				int i = 0;
				for (Skill skill: character.getSkills())
				{
				    JButton btn = new JButton(skill.getName());
				    btn.setToolTipText(skill.getTooltipp());
				    btn.addActionListener(this);
				    btn.setBounds(100+i, 100, 100, 20);
				    btn.setAlignmentX(100);
				    btn.setAlignmentY(100);
				    fightpanel.add(btn);
				    buttons.add(btn);
				    i=i+110;
				}
			}
			
		}
		paint();
	}
	
	/**
	 * This Function adds all of the Characters
	 * @param amount is the amount of to be created monsters
	 */
	private void addCharacters(int amount) {
		Keggle player = new Keggle();
		characters.add(player);
		for(int i = 1;i<=amount;i++) {
			characters.add(new Rat(1,300+200*i,50));
		}
		loadCharacterImages();
	}
	
	private void gameLoop()
	{
	    timer = new Timer();
	    timer.schedule(new LoopyStuff(), 0, 1000 / 30); //new timer at 60 fps, the timing mechanism
	}
	
	private void paint() {
		fightpanel.validate();
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
            					checkfordead();
            			}
    				}
    			}
    		}
    		if(gamestate!=2) {
    			setGameState(0);
    		}
    		
    	}
	}
	
	private void checkfordead() {
		for (Iterator<main.java.com.ktb.character.Character> iter = characters.listIterator(); iter.hasNext(); ) {
			main.java.com.ktb.character.Character character = iter.next();
		    if (!character.getAlive()&&!character.getPlayablecharacter()) {
		    	//removes all of the added images
		    	fightpanel.remove(character.getImage());
		    	fightpanel.remove((character).getHealthBarImage());
				fightpanel.remove((character).getHealthImage());
				System.out.println("The Character " + character.getName() +" died");
		        iter.remove();
		        if(characters.size()==1) {
		        	label.setText("Game Over you Won");
		        	gamestate = 3;
		        }
		    }else if(!character.getAlive()&&character.getPlayablecharacter()&&gamestate!=2) {
		    	label.setText("Game Over you Lost");
		    	gamestate = 2;
		    	System.out.println("The Character " + character.getName() +" died");
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
        		        				checkfordead();
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
