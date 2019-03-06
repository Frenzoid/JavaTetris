package model.io;

import java.util.Objects;

import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerString, devuelve movimientos desde un sring.
 *  @author  Frenzoid
 *
 */
public class PlayerString implements IPlayer {

	/**
	 * Atributo que guarda en un string los movimientos.
	 */
	private String moves;
	
	/**
	 * Posicion actual del movimiento.
	 */
	private int currentMove = 0;

	/**
	 * Constructor de la clase
	 * @param moves, movimientos.
	 */
	public PlayerString(String moves) {
		this.moves = Objects.requireNonNull(moves, "El parametro 'moves' no puede ser null");
	}
	
	
	/**
	 * Devuelve el siguiente movimiento por hacer.
	 */
	@Override
	public char nextMove() throws TetrisIOException {
		char move = IPlayer.LAST_MOVE;
		char validOperations[] = {'I','J','L','O','S','T','Z','↻','↺','→','←','↓', IPlayer.LAST_MOVE};
		
		if(this.currentMove < this.moves.length()) {
			move = this.moves.charAt(this.currentMove);
			this.currentMove++;
		}
		
		for (char operation : validOperations){
			if (operation == move)
				return move;
		}
		
		throw new TetrisIOException("Movimiento '" + move + "' no es un movimiento valido.");
	}

}
