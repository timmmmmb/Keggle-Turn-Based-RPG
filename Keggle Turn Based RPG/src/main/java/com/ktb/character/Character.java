package main.java.com.ktb.character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	protected Healthbar health = new Healthbar(100); 
	protected Manabar mana = new Manabar(100);
	private String imagefile = "images/keggle.png";
	protected JLabel picLabel = null;
	int width = 16;
	int height = 28;
	int rows = 3;
	int cols = 1;
	protected int level = 1;
	private int currentsprite = 0;
	protected int posx = 42;
	protected int posy = 10;
	protected ClassLoader classLoader = null;
	private BufferedImage[] sprites = new BufferedImage[rows * cols];
	
	protected void initializeLabel() {

		BufferedImage bigImg = null;
		//loads the spritesheet
		try {
			bigImg = ImageIO.read(new File(classLoader.getResource(imagefile).getFile().toString().replaceAll("%20", " ")));
		} catch (IOException e) {
			System.out.println(e);
		}
		sprites = new BufferedImage[rows * cols];
		//loads all sprites into the array
		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
		    }
		}
		
		picLabel = new JLabel(new ImageIcon(sprites[currentsprite]));
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
	}
	
	public void changeSpriteSheet(int prows,int pcols, String pimagefile,int pheight, int pwidth) {
		rows = prows;
		cols = pcols;
		height = pheight;
		width = pwidth;
		imagefile = pimagefile;
		initializeLabel();
	}
	
	public void setName(String pname) {
		this.name=pname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setHealth(int phealth) {
		this.health.sethealth(phealth);
	}
	
	public void setMaxHealth(int phealth) {
		this.health.setmaxhealth(phealth);
	}
	
	public void setMaxMana(int pmana) {
		this.mana.setmaxMana(pmana);
	}
	
	public int getHealth() {
		return this.health.gethealth();
	}
	
	public void regenerateHealth(int regenerationrate) {
		health.regeneratehealth(regenerationrate);
	}
	
	public void useHealth(int cost) {
		health.losehealth(cost);
	}
	
	public JLabel getHealthImage() {
		return health.gethealthLabel();
	}
	
	public JLabel getHealthBarImage() {
		return health.gethealthBarLabel();
	}
	
	public int getMana(){
		return mana.getMana();
	}
	
	public void regenerateMana(int regenerationrate) {
		mana.regeneratemana(regenerationrate);
	}
	
	public void useMana(int cost) {
		mana.usemana(cost);
	}
	
	public JLabel getManaImage() {
		return mana.getManaLabel();
	}
	
	public JLabel getManaBarImage() {
		return mana.getManaBarLabel();
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
	
	public JLabel getImage() {
		return picLabel;
	}
	
	
	public void setImage(String pimagefile) {
		imagefile = pimagefile;
		initializeLabel();
	}
	/**
	 * changes the current sprite
	 */
	public void animate() {
		if(sprites.length==currentsprite+1) {
			currentsprite = 0;
		}else {
			currentsprite++;
		}
		
		picLabel.setIcon(new ImageIcon(sprites[currentsprite]));
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		picLabel.setBounds(posx, posy, picLabel.getPreferredSize().width, picLabel.getPreferredSize().height);
		health.setPosition(posx+width/2-health.gethealthBarLabel().getPreferredSize().width/2, posy-health.gethealthBarLabel().getPreferredSize().height+10);
	}
}
