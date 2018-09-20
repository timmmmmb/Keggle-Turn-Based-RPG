package main.java.com.ktb.skills;
/**
 * This class is the base class of all skills.
 * A skill is an ability that a character can use.
 * @author Tim
 *
 */ 
public class Skill {
	private String name = "";
	private String tooltipp = "";
	
	public void setName(String pname) {
		this.name=pname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setTooltipp(String ptooltipp) {
		this.tooltipp=ptooltipp;
	}
	
	public String getTooltipp() {
		return this.tooltipp;
	}
	/**
	 * This function is used when the skill is activated.
	 */
	public void activate(main.java.com.ktb.character.Character target) {
		
	}
	
	/**
	 * This function is used when the turn is ended and the effect of the skill is reset.
	 */
	public void revert() {
		
	}
}
