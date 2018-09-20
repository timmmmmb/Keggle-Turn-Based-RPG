package main.java.com.ktb.character;

public class Keggle extends Character {

	public Keggle() {
		initialize();
	}
	
	public Keggle(int level) {
		
		initialize();
		this.level = level;
		scalecharacter();
	}
	
	public Keggle(int level, int x, int y) {
		
		initialize();
		this.setPosition(x, y);
		
		this.level = level;
		scalecharacter();
	}
	
	private void initialize() {
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(true);
		this.setName("Keggle") ;
		this.setPosition(100, 50);
		this.healthscaling = 5;
		this.defaulthealth = 45;
		this.manascaling = 10;
		this.defaultmana = 40;
		this.level = 1;
		scalecharacter();
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
		mana.setPosition(posx+width/2-mana.getManaBarLabel().getPreferredSize().width/2, posy-mana.getManaBarLabel().getPreferredSize().height);
		health.setPosition(posx+width/2-health.gethealthBarLabel().getPreferredSize().width/2, posy-health.gethealthBarLabel().getPreferredSize().height*2+1);
	}
}
 