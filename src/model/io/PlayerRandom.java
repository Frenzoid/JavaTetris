package model.io;

import java.util.Random;

import model.exceptions.TetrisException;
import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerRandom, devuelve movimientos aleatorios
 *  @author  Frenzoid
 *
 */
public class PlayerRandom implements IPlayer {
	/**
	 * atributo randomizador basado en una seed
	 */
	private Random random;
	
	private boolean nextPutPiece = true;
	
	private int downCounter = 0;
	
	/**
	 *Constructor de la clase.
	 */
	public PlayerRandom(long seed) {
		this.random = new Random();
		this.random.setSeed(seed);
	}
	
	/**
	 * Devuelve el siguiente movimiento por hacer.
	 */
	@Override
	public char nextMove() throws TetrisIOException {
		char pieces[] = {'I','J','L','O','S','T','Z',IPlayer.LAST_MOVE};
		char moves[] = {'←','→','↻','↺','↓'};
		
		if(this.nextPutPiece) {
			int randPieceIndex = this.random.nextInt(8);
			this.nextPutPiece = false;

			return pieces[randPieceIndex];	
		} else {
			int randMoveIndex = this.random.nextInt(10);
			
			if(randMoveIndex < 4)
				return moves[randMoveIndex];
			
			this.downCounter++;

			if(this.downCounter == GamePlay.TETRIS_BOARD_STANDARD_HEIGHT) {
				this.nextPutPiece = true;
				this.downCounter = 0;
			}
			
			return moves[4];
		}
	}
}
