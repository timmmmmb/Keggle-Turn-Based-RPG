package main.java.com.ktb.character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Healthbar {
	private int maxhealth = 10;
	private int health = maxhealth;
	private int posy = 0;
	private int posx = 0;
	private JLabel healthbarlabel = null;
	private JLabel healthlabel = null;
	protected ClassLoader classLoader = null;
	private BufferedImage healthimg = null;
	private BufferedImage healthbarimg = null;
	public Healthbar( int pmaxhealth) {
		this.classLoader =this.getClass().getClassLoader();
		
		try {
			healthbarimg = ImageIO.read(new File(classLoader.getResource("images/bar.png").getFile().toString().replaceAll("%20", " ")));
			healthimg = ImageIO.read(new File(classLoader.getResource("images/health.png").getFile().toString().replaceAll("%20", " ")));
		} catch (IOException e) {
			System.out.println(e);
		}
		healthbarlabel = new JLabel(new ImageIcon(healthbarimg));
		healthlabel = new JLabel(new ImageIcon(healthimg));
		healthlabel.setBounds(posx, posy, healthlabel.getPreferredSize().width, healthlabel.getPreferredSize().height);
		healthbarlabel.setBounds(posx, posy, healthbarlabel.getPreferredSize().width, healthbarlabel.getPreferredSize().height);
		maxhealth = pmaxhealth;
		health = maxhealth;
	}
	
	protected void sethealth(int newhealth) {
		health = newhealth;
	}
	
	protected void setmaxhealth(int newmaxhealth) {
		maxhealth = newmaxhealth;
		health = maxhealth;
	}
	
	public void regeneratehealth(int regenerationrate) {
		if(health+regenerationrate<maxhealth) {
			health = health+regenerationrate;
		}else{
			health = maxhealth;
		}
		healthlabel.setSize(healthimg.getWidth()/maxhealth*health, healthimg.getHeight());
	}	
	
	public void losehealth(int cost) {
		health = health - cost;
		healthlabel.setSize(healthimg.getWidth()/maxhealth*health, healthimg.getHeight());
	}
	
	public int gethealth() {
		return health;
	}
	
	public JLabel gethealthLabel() {
		return healthlabel;
	}
	
	public JLabel gethealthBarLabel() {
		return healthbarlabel;
	}
	
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		healthlabel.setBounds(posx, posy, healthlabel.getPreferredSize().width, healthlabel.getPreferredSize().height);
		healthbarlabel.setBounds(posx, posy, healthbarlabel.getPreferredSize().width, healthbarlabel.getPreferredSize().height);
	}
}
