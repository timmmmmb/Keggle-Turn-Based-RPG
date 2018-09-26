package main.java.com.ktb.skills;

import main.java.com.ktb.game.Game;

public class Heal extends Skill{
	public Heal() {
		this.setName("Heal");
		this.setTooltipp("Costs 20 Mana and Heals for 20 Health");
	}
	
	 public void activate(main.java.com.ktb.character.Character target, main.java.com.ktb.character.Character user, Game game) {
		if(user.getMana()>=20&&(game.getGameState()==0 && user.getPlayablecharacter()) || (game.getGameState()==1 && !user.getPlayablecharacter())) {
			user.regenerateHealth(20);
			user.useMana(20);
			if(game.getGameState()==0) {
				game.setGameState(1);
			}
			System.out.println("The Character "+user.getName()+" used Heal");
		}else if(game.getGameState()==2){
			game.label.setText("its game over");
			System.out.println("its game over");
		}else if(user.getMana()<20){
			System.out.println("you dont have enough mana");
			game.label.setText("you dont have enough mana");
		}else {
			game.label.setText("its not your turn");
			System.out.println("its not your turn");
		}
		
	}
}
