package model.score;

import model.io.GamePlay;

public class TimeScore extends Score {

	public TimeScore(String name, GamePlay gamePlay) {
		super(name, gamePlay);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getScoring() {
		return -this.gamePlay.getDuration(); // ?
	}

}
