package main.java.com.ktb.character;

public class Rat extends Character {
	public Rat() {
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		this.setPlayablecharacter(false);
		this.setName("Rat") ;
		this.setPosition(500, 50);
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
		mana.setPosition(posx+width/2-mana.getManaBarLabel().getPreferredSize().width/2, posy-mana.getManaBarLabel().getPreferredSize().height);
		health.setPosition(posx+width/2-health.gethealthBarLabel().getPreferredSize().width/2, posy-health.gethealthBarLabel().getPreferredSize().height*2+1);
	}
}
 