package main.java.com.ktb.skills;

import main.java.com.ktb.game.Game;

public class Strike extends Skill{
	int damagedefault = 8;
	int damagescaling = 2;
	public Strike() {
		this.setName("Strike");
		this.setTooltipp("Attacks an Enemy for 10 Dmg and costs 10 mana");
	}

	/**
	 * This function is used when the skill is activated.
	 */
	public void activate(main.java.com.ktb.character.Character target, main.java.com.ktb.character.Character user, Game game) {
		if((game.getGameState()==0 && user.getPlayablecharacter()) || (game.getGameState()==1 && !user.getPlayablecharacter())) {
			if(user.getMana()>=10) {
				target.loseHealth(damagedefault+damagescaling*user.getLevel());
				user.useMana(10);
				if(game.getGameState()==0) {
					game.setGameState(1);
				}
				System.out.println("The Character "+user.getName()+" used Strike on "+target.getName());
			}
			else {
				System.out.println("you dont have enough mana");
			}
		}else if(game.getGameState()==2){
			System.out.println("its game over");
		}else {
			System.out.println("its not your turn");
		}
	}
}
