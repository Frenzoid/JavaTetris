package model.score;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.RankingException;

public class Ranking<ScoreType extends Score> {
	private SortedSet<ScoreType> gamePlays;

	public Ranking() {
		gamePlays = new TreeSet<>();

	}
	
	public void addScore(ScoreType score) {
		this.gamePlays.add(Objects.requireNonNull(score, "Score no puede ser Null!"));
	}
	
	public Score getWinner() throws RankingException {
		try {
			Score winner = this.gamePlays.first();
			return winner;
		}catch (NoSuchElementException e) {
			throw new RankingException();
		}
	}
	
	public SortedSet<ScoreType> getSortedRanking(){
		return (SortedSet<ScoreType>) this.gamePlays;
	}
}
