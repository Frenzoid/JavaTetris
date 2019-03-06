package model.io;

import model.Game;
import model.exceptions.TetrisException;
import model.exceptions.WrongSizeException;
import model.exceptions.io.TetrisIOException;

/**
 * Interfaz del visualizador
 *  @author  Frenzoid
 *
 */
public interface IVisualizer {
	
	/**
	 * Setea el juego con el que se va a jugar.
	 * @param game, el juego a maniobrar.
	 * @throws TetrisException 
	 */
	public void setGame(Game game) throws WrongSizeException;
	
	/**
	 * Muestra el estado del tablero.
	 */
	public void show();
	
}
