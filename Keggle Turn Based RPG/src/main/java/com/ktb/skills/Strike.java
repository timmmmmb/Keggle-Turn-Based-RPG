package main.java.com.ktb.skills;

public class Strike extends Skill{
	int damagedefault = 8;
	int damagescaling = 2;
	public Strike() {
		this.setName("Strike");
		this.setTooltipp("Attacks an Enemy for 10 Dmg");
	}

	/**
	 * This function is used when the skill is activated.
	 */
	public void activate(main.java.com.ktb.character.Character target) {
		target.loseHealth(damagedefault+damagescaling*1);
	}
	
	public void activate(main.java.com.ktb.character.Character target, main.java.com.ktb.character.Character user) {
		target.loseHealth(damagedefault+damagescaling*user.getLevel());
	}
}
