package main.java.com.ktb.character;

public class Keggle extends Character {
	public Keggle() {
		this.classLoader =this.getClass().getClassLoader();
		this.initializeLabel();
		
		this.setName("keggle") ;
	}
}
 