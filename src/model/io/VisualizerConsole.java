package model.io;

import java.util.Objects;

import model.Coordinate;
import model.Game;
import model.exceptions.TetrisException;
import model.exceptions.WrongSizeException;
/**
 *  Clase visualizadora por consola.
 *  @author  Frenzoid
 *
 */
public class VisualizerConsole implements IVisualizer {

	/**
	 * Juego.
	 */
	private Game game;
	
	public VisualizerConsole() {
		this.game = null;
	}
	
	@Override
	public void setGame(Game game) throws WrongSizeException {
		if(game.getGameboard().getHeight() != GamePlay.TETRIS_BOARD_STANDARD_HEIGHT ||
		   game.getGameboard().getWidth()  != GamePlay.TETRIS_BOARD_STANDARD_WIDTH)
			throw new WrongSizeException(
					new Coordinate(game.getGameboard().getHeight(), game.getGameboard().getWidth())
					);
		
		this.game = Objects.requireNonNull(game, "el parametro Game no puede ser null");

	}

	@Override
	public void show() {

		if(this.game == null)
			throw new NullPointerException();
		
		System.out.println(this.game.toString());
	}
	
	

}
