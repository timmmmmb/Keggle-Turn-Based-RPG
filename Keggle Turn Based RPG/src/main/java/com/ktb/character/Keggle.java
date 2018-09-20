package main.java.com.ktb.character;

public class Keggle extends Character {
	public Keggle() {
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(true);
		this.setName("Keggle") ;
		this.setPosition(100, 50);
	}
	public Keggle(int level) {
		
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(true);
		this.setName("Keggle") ;
		this.setPosition(100, 50);
		
		this.level = level;
		this.setMaxHealth(45+5*level);
		this.setMaxMana(40+10*level);
	}
	
	public Keggle(int level, int x, int y) {
		
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(true);
		this.setName("Keggle") ;
		this.setPosition(x, y);
		
		this.level = level;
		this.setMaxHealth(45+5*level);
		this.setMaxMana(40+10*level);
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
		mana.setPosition(posx+width/2-mana.getManaBarLabel().getPreferredSize().width/2, posy-mana.getManaBarLabel().getPreferredSize().height);
		health.setPosition(posx+width/2-health.gethealthBarLabel().getPreferredSize().width/2, posy-health.gethealthBarLabel().getPreferredSize().height*2+1);
	}
}
 