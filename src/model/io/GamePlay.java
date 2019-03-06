package model.io;

import java.util.Date;
import java.util.Objects;

import model.Coordinate;
import model.Game;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.TetrisException;
import model.exceptions.WrongSizeException;
import model.exceptions.io.TetrisIOException;

/**
 * Clase de gameplay.
 *  @author  Frenzoid
 *
 */
public class GamePlay {
	/**
	 * Duracion de la partida
	 */
	private int duration;
	
	/**
	 * Filas limpiadas
	 */
	private int rowsCleared;
	
	/**
	 * Medida standard de anchura del tablero.
	 */
	protected static int TETRIS_BOARD_STANDARD_WIDTH = 10;
	
	/**
	 * Medida stanrdard de altura del tablero.
	 */
	protected static int TETRIS_BOARD_STANDARD_HEIGHT = 20;

	/**
	 * Juego.
	 */
	private Game game;
	
	/**
	 * Visualizador del tablero.
	 */
	protected IVisualizer visualizer;
	
	/**
	 * Jugador del juego.
	 */
	protected IPlayer player;
	
	public GamePlay(IPlayer player, IVisualizer visualizer) {
		this.rowsCleared = 0;
		this.duration = 0;
		
		this.visualizer = Objects.requireNonNull(visualizer, "Visualizer no puede ser null!");
		this.player = Objects.requireNonNull(player, "Player no puede ser null!");
		
		try {
			this.game = new Game(new Coordinate(GamePlay.TETRIS_BOARD_STANDARD_HEIGHT, GamePlay.TETRIS_BOARD_STANDARD_WIDTH));
			this.visualizer.setGame(this.game);
		}catch(WrongSizeException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void play() throws TetrisIOException {
		long t0 = new Date().getTime();
		char move;
		
			visualizer.show();
	        move = player.nextMove();
	        while (move != IPlayer.LAST_MOVE) {
	        	try {
		           if( move == 'I' || move == 'J' || move == 'L' || move == 'O' || move == 'S' || move == 'T' || move == 'Z')
		        	   this.game.nextPiece(move + "");
		           else {
		        	   switch(move) {
		        	   case '↻':
		        		  this.game.rotateCurrentPieceClockwise();
		        		  break;
		        	   case '↺':
		        		  this.game.rotateCurrentPieceCounterclockwise();
		        		  break;
		        	   case '→':
			    		  this.game.moveCurrentPieceRight();
			    		  break;
		        	   case '←':
				   			this.game.moveCurrentPieceLeft();
				   			break;
		        	   case '↓':
		        		   this.rowsCleared += this.game.moveCurrentPieceDown();
		        		   break;
		        	   }
		           }
		        }catch(NoCurrentPieceException e) {
		    		throw new TetrisIOException("No hay pieza con que maniobrar");
		    	}catch(CurrentPieceNotFixedException e) {
		    		throw new TetrisIOException("La pieza no se ha fijado todavia");
		    	}catch(GameEndedMovementException e) {
		    		return;
		    	}catch(TetrisException e) {
		    		
		    	}
	           visualizer.show();
	           move = player.nextMove();
	        }
       
        
        long t1 = new Date().getTime();
        this.duration = (int)(t1 - t0);
	}
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @return the rowsCleared
	 */
	public int getRowsCleared() {
		return rowsCleared;
	}
}
