package main.java.com.ktb.skills;

import main.java.com.ktb.game.Game;

public class EndTurn extends Skill{
	public EndTurn() {
		this.setName("EndTurn");
		this.setTooltipp("Ends the turn and regenerates 20 mana");
	}
	
	 public void activate(main.java.com.ktb.character.Character target, main.java.com.ktb.character.Character user, Game game) {
		if((game.getGameState()==0 && user.getPlayablecharacter()) || (game.getGameState()==1 && !user.getPlayablecharacter())) {
			user.regenerateMana(20);
			if(game.getGameState()==0) {
				game.setGameState(1);
			}
			System.out.println("The Character "+user.getName()+" used EndTurn");
		}else if(game.getGameState()==2){
			System.out.println("its game over");
		}else {
			System.out.println("its not your turn");
		}
		
	}
}
