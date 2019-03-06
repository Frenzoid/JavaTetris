package model.score;

import java.util.Objects;

import model.io.GamePlay;

/**
 * 	Clase Score
 *  @author  Frenzoid
 *
 */
public abstract class Score implements Comparable<Score> {
	/**
	 * Rankings manipulator
	 */
	private Ranking gamePlays;
	
	/**
	 * Gameplay attribute
	 */
	protected GamePlay gamePlay;
	
	/**
	 * Name attribute
	 */
	
	private String name;
	
	/**
	 * Score attribute
	 */
	private final int score;
	
	/**
	 * Constructor de Score
	 */
	public Score(String name, GamePlay gamePlay) {
		this.name = Objects.requireNonNull(name, "El nombre del jugador no puede ser null!");
		this.gamePlay = Objects.requireNonNull(gamePlay, "Gameplay es null!");
		this.score = this.getScoring();
	}
	
	/**
	 * Devuelve la tabla con los resultados;
	 */
	public String toString() {
		return this.name + ":" + this.score;
	}
	
	/**
	 * Devuelve el nombre del jugador.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * devuelve el puntuaje.
	 */
	public abstract int getScoring();
	
	/**
	 * Compara dos scores
	 */
	
	public int compareTo(Score score) {
		if (score.score > this.score)
			return 1;
		else if(score.score < this.score)
			return -1;
		else
			return name.compareTo(score.name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Score))
			return false;
		Score other = (Score) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
	
	
	
	
}
