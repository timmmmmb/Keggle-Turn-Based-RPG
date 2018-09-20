package main.java.com.ktb.skills;

import main.java.com.ktb.game.Game;

public class EndTurn extends Skill{
	public EndTurn() {
		this.setName("EndTurn");
		this.setTooltipp("Attacks an Enemy for 10 Dmg");
	}
	
	public void activate(Game game) {
		if(game.getGameState()==0) {
			game.setGameState(1);
		}else if (game.getGameState()==1) {
			game.setGameState(0);
		}
	}
}
