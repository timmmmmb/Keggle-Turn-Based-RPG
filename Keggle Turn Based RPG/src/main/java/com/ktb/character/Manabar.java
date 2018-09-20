package main.java.com.ktb.character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Manabar {
	private int maxmana = 10;
	private int mana = maxmana;
	private int posy = 0;
	private int posx = 0;
	private JLabel manabarlabel = null;
	private JLabel manalabel = null;
	protected ClassLoader classLoader = null;
	private BufferedImage manaimg = null;
	private BufferedImage manabarimg = null;
	public Manabar( int pmaxmana) {
		this.classLoader =this.getClass().getClassLoader();
		
		try {
			manabarimg = ImageIO.read(new File(classLoader.getResource("images/bar.png").getFile().toString().replaceAll("%20", " ")));
			manaimg = ImageIO.read(new File(classLoader.getResource("images/mana.png").getFile().toString().replaceAll("%20", " ")));
		} catch (IOException e) {
			System.out.println(e);
		}
		manabarlabel = new JLabel(new ImageIcon(manabarimg));
		manalabel = new JLabel(new ImageIcon(manaimg));
		manalabel.setBounds(posx, posy, manalabel.getPreferredSize().width, manalabel.getPreferredSize().height);
		manabarlabel.setBounds(posx, posy, manabarlabel.getPreferredSize().width, manabarlabel.getPreferredSize().height);
		maxmana = pmaxmana;
		mana = maxmana;
	}
	
	protected void setMana(int newmana) {
		mana = newmana;
	}
	
	protected void setmaxMana(int newmaxmana) {
		maxmana = newmaxmana;
		mana = maxmana;
	}
	
	public void regeneratemana(int regenerationrate) {
		if(mana+regenerationrate<maxmana) {
			mana = mana+regenerationrate;
		}else{
			mana = maxmana;
		}
		manalabel.setSize(manaimg.getWidth()/maxmana*mana, manaimg.getHeight());
	}	
	
	public void usemana(int cost) {
		mana = mana - cost;
		manalabel.setSize(manaimg.getWidth()/maxmana*mana, manaimg.getHeight());
	}
	
	public int getMana() {
		return mana;
	}
	
	public JLabel getManaLabel() {
		return manalabel;
	}
	
	public JLabel getManaBarLabel() {
		return manabarlabel;
	}
	
	public void setPosition(int x, int y) {
		posx = x;
		posy = y;
		manalabel.setBounds(posx, posy, manalabel.getPreferredSize().width, manalabel.getPreferredSize().height);
		manabarlabel.setBounds(posx, posy, manabarlabel.getPreferredSize().width, manabarlabel.getPreferredSize().height);
	}
}
