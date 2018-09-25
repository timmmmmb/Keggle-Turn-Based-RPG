package main.java.com.ktb.character;

import main.java.com.ktb.game.Game;
import main.java.com.ktb.skills.EndTurn;
import main.java.com.ktb.skills.Skill;
import main.java.com.ktb.skills.Strike;

//TODO: create a new Spritesheet for the rat and change it in the code
public class Rat extends Character {
	private Strike strike = new Strike();
	private EndTurn endturn = new EndTurn();
	public Rat() {
		initialize();
	}
	
	public Rat(int level) {
		initialize();
		this.level = level;
		scalecharacter();
	}
	
	public Rat(int level, int x, int y) {
		
		initialize();
		this.setPosition(x, y);
		
		this.level = level;
		scalecharacter();
	}
	
	private void initialize() {
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(false);
		this.setName("Rat") ;
		this.setPosition(500, 50);
		this.healthscaling = 5;
		this.defaulthealth = 45;
		this.manascaling = 10;
		this.defaultmana = 40;
		this.level = 1;
		this.skills.add((Skill)strike);
		this.skills.add((Skill)endturn);
		setTargetedablecharacter(true);
		scalecharacter();
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
		mana.setPosition(posx+width/2-mana.getManaBarLabel().getPreferredSize().width/2, posy-mana.getManaBarLabel().getPreferredSize().height);
		health.setPosition(posx+width/2-health.gethealthBarLabel().getPreferredSize().width/2, posy-health.gethealthBarLabel().getPreferredSize().height*2+1);
	}
	
	/**
	 * This is the ki that executes a turn
	 */
	public void aiTurn(main.java.com.ktb.character.Character target, Game game) {
		int  n = rand.nextInt(2);
		if (n==1 && this.getMana()>=10) {
			strike.activate(target, this, game);
		}else {
			endturn.activate(target, this, game);
		}
		
	}
}
 