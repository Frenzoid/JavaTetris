package model.io;

import model.exceptions.io.TetrisIOException;

/**
 *  @author  Frenzoid
 *
 */
public interface IPlayer {

	public static char LAST_MOVE = '●';
	
	public char nextMove() throws TetrisIOException;
}
