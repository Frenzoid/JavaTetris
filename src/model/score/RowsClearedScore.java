package model.score;

import model.io.GamePlay;

public class RowsClearedScore extends Score {

	public RowsClearedScore(String name, GamePlay gamePlay) {
		super(name, gamePlay);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getScoring() {
		return gamePlay.getRowsCleared();
	}

}
