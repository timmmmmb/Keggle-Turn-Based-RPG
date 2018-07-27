package ktb.character;

import java.io.File;

/**
 * This class is the base class of all characters. 
 * A character is the player and all enemies
 * @author Tim
 *
 */

public class Character {
	private String name = "";
	private boolean playablecharacter = false;
	private boolean activecharacter = false;
	private int health = 0;
	private File imagefile = new File("D:\\Projects\\Keggle Turn Based RPG\\trunk\\Keggle Turn Based RPG\\src\\ktb\\character\\images\\keggle.png");

	
	public void setName(String pname) {
		this.name=pname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setHealth(int phealth) {
		this.health=phealth;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setPlayablecharacter(boolean pplayablecharacter) {
		this.playablecharacter=pplayablecharacter;
	}
	
	public boolean getPlayablecharacter() {
		return this.playablecharacter;
	}
	
	public void setActivecharacter(boolean pactivecharacter) {
		this.activecharacter=pactivecharacter;
	}
	
	public boolean getActivecharacter() {
		return this.activecharacter;
	}
	
	public File getImage() {
		return imagefile;
	}
	
	public void setImage(File pimagefile) {
		imagefile = pimagefile;
	}
	
}
