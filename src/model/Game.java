package model;

import model.exceptions.CollisionMovementException;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.FixedPieceMovementException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.OffBoardMovementException;
import model.exceptions.WrongSizeException;

/**
 * 	Clase que representa al interaccion del juego en si.
 *  @author  Frenzoid
 *
 */
public class Game {
	/**
	 * Booleano que determina si la partida ha finalizado.
	 */
	private boolean gameEnded;
	
	/**
	 * Tablero del juego.
	 */
	private Gameboard board; 
	
	/**
	 * Pieza actual en control del juego.
	 */
	private Piece currentPiece;
	
	/**
	 * Coordenada actual de la pieza.
	 */
	private Coordinate currentCoordinate;
	
	/**
	 * Crea un nuevo tablero con el tamaño indicado por el parámetro, 
	 * y lo almacena en el atributo board.
	 * @param c, la coordenada con las dimensiones que se creara del tablero.
	 * @throws WrongSizeException 
	 */
	public Game(Coordinate c) throws WrongSizeException {
		this.gameEnded = false;
		this.currentPiece = null;
		this.currentCoordinate = null;
		
		//try {
			this.board = new Gameboard(c);
		/*} catch (WrongSizeException e) {
			System.err.println("Game Error:" + e.getMessage());
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Crea una nueva pieza y la coloca en lo alto del tablero.
	 * @return un booleano, true si se pudo poner correctamente, false si no se puede poner en el tablero.
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws GameEndedMovementException 
	 * @throws CurrentPieceNotFixedException 
	 * @throws NoCurrentPieceException 
	 */
	public boolean nextPiece(String type) throws OffBoardMovementException, GameEndedMovementException, CurrentPieceNotFixedException, NoCurrentPieceException {
		if(this.gameEnded)
			throw new GameEndedMovementException();
		
		if(this.currentPiece != null && !this.isCurrentPieceFixed())
			throw new CurrentPieceNotFixedException();
		
		this.currentCoordinate = new Coordinate(0, board.getWidth() / 2 - 2);
		this.currentPiece = PieceFactory.createPiece(type);
		
		
		if(this.board.isPlaceFree(this.currentCoordinate, this.currentPiece)) {
			this.board.putPiece(this.currentCoordinate, this.currentPiece);
			return true;
		} else 
			this.gameEnded = true;
		
		return false;
	}
	
	/**
	 * Mueve la pieza actual a una posicion nueva ( -1 en el ancho del board) en caso de ser posible.
	 * @throws NoCurrentPieceException 
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	public boolean moveCurrentPieceLeft() throws GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException, NoCurrentPieceException {
		return this.lateralMovement(0);
	}
	
	/**
	 * Mueve la pieza actual a una posicion nueva ( +1 en el ancho del board) en caso de ser posible.
	 * @throws NoCurrentPieceException 
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	public boolean moveCurrentPieceRight() throws GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException, NoCurrentPieceException {
		return this.lateralMovement(1);
	}
	
	/**
	 * Cambia la orientacion de la rotacion de la pieza en sentido contrario de las agujas del reloj
	 * a ser posible.
	 * @throws NoCurrentPieceException 
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	public boolean rotateCurrentPieceCounterclockwise() throws GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException, NoCurrentPieceException {
		return this.rotationalMovement(0);
	}
	
	/**
	 * Cambia la orientacion de la rotacion de la pieza en sentido de las agujas del reloj
	 * a ser posible.
	 * @throws NoCurrentPieceException 
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	public boolean rotateCurrentPieceClockwise() throws GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException, NoCurrentPieceException {
		return this.rotationalMovement(1);
	}
	
	/**
	 * Mueve una pieza hacia abajo, si la pieza a la hora de moverse no puede porque estaria colisionando con otra,
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws NoCurrentPieceException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	public int moveCurrentPieceDown() throws OffBoardMovementException, CollisionMovementException, GameEndedMovementException, FixedPieceMovementException, NoCurrentPieceException {
		int clearedRows = 0;
		if(this.isValidToProceed()) {
		
			Coordinate newPos = new Coordinate(this.currentCoordinate);
	
			newPos.setRow(this.currentCoordinate.getRow() + 1);
			
			if(this.board.isPlaceFree(newPos, this.currentPiece) &&
				this.board.isPlaceValid(newPos, this.currentPiece)) {
				
				this.board.removePiece(this.currentPiece);
				this.board.putPiece(newPos, this.currentPiece);
				this.currentCoordinate = newPos;
			} else {
				this.currentPiece.setFixed(true);
				
				while(this.board.firstRowFullFromBottom() != -1) {
					int fullRow = this.board.firstRowFullFromBottom();
					this.board.clearRow(fullRow);
					this.board.makeUpperRowsFall(fullRow);
					clearedRows++;
				}
			}
		}
		return clearedRows;
	}
	
	/**
	 * Determina si la pieza en control esta encajada o no.
	 * @return true si esta encajada, false si no lo esta.
	 * @throws NoCurrentPieceException 
	 */
	public boolean isCurrentPieceFixed() throws NoCurrentPieceException {
		if(this.currentPiece == null)
			throw new NoCurrentPieceException();
		
		return this.currentPiece.isFixed();
	}
	
	/**
	 * Determina si el juego ha acabado o no.
	 * @return un booleano, true si ha acabado, false si sigue en juego.
	 */
	public boolean isGameEnded() {
		return this.gameEnded;
	}
	

	/**
	 * Devuelve el tablero por la consola.
	 */
	@Override
	public String toString() {
		return this.board.toString();
	}

	/**
	 * Rota la pieza dependiendo de la direccion pasada por parametro.
	 * @param l, la direccion a moverse lateralmente, ( 0 = izquierda, 1 = derecha).
	 * @return un booleano, si se ha podido mover o no.
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws NoCurrentPieceException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	private boolean rotationalMovement(int l) throws OffBoardMovementException, CollisionMovementException, GameEndedMovementException, FixedPieceMovementException, NoCurrentPieceException {
		if(this.isValidToProceed()) {
			Piece newPiec = this.currentPiece.copy();
	
			if(l >= 1) 
				newPiec.rotateClockwise();
			else
				newPiec.rotateCounterclockwise();
			
			if(this.isValidToMove(this.currentCoordinate, newPiec)) {
				this.board.removePiece(this.currentPiece);
				this.board.putPiece(this.currentCoordinate, newPiec);
				this.currentPiece = newPiec;
				
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Mueve la pieza lateralmente dependiendo de la direccion pasada por parametro.
	 * @param l, la direccion a moverse lateralmente, ( 0 = izquierda, 1 = derecha).
	 * @return un booleano, si se ha podido mover o no.
	 * @throws CollisionMovementException 
	 * @throws OffBoardMovementException 
	 * @throws NoCurrentPieceException 
	 * @throws FixedPieceMovementException 
	 * @throws GameEndedMovementException 
	 */
	private boolean lateralMovement(int l) throws OffBoardMovementException, CollisionMovementException, GameEndedMovementException, FixedPieceMovementException, NoCurrentPieceException {
		if(this.isValidToProceed()) {
			Coordinate newPos = new Coordinate(this.currentCoordinate);
			
			if(l >= 1)
				newPos.setColumn(this.currentCoordinate.getColumn() + 1);
			else
				newPos.setColumn(this.currentCoordinate.getColumn() - 1);
			
			if(this.isValidToMove(newPos, this.currentPiece)) {
				this.board.removePiece(this.currentPiece);
				this.board.putPiece(newPos, this.currentPiece);
				this.currentCoordinate = newPos;
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determina si se puede proceder a procesar la nueva interaccion de la pieza actual.
	 * @return un booleano, true si se puede, false si no se puede.
	 * @throws NoCurrentPieceException 
	 * @throws GameEndedMovementException 
	 * @throws FixedPieceMovementException 
	 */
	private boolean isValidToProceed() throws NoCurrentPieceException, GameEndedMovementException, FixedPieceMovementException {
		
		if(this.currentPiece == null)
			throw new NoCurrentPieceException();
		
		if(this.isGameEnded())
			throw new GameEndedMovementException();
		
		if(this.isCurrentPieceFixed())
			throw new FixedPieceMovementException();
		
		return (true);
	}
	
	/**
	 * Determina si se puede ejecutar el siguiente movimiento de la pieza actual.
	 * @param c, la coordenada donde moverse.
	 * @param p, la pieza a mover.
	 * @return un booleano, true si se puede mover, false si no se puede mover.
	 * @throws OffBoardMovementException 
	 * @throws CollisionMovementException 
	 */
	private boolean isValidToMove(Coordinate c, Piece p) throws OffBoardMovementException, CollisionMovementException {
		
		if(!this.board.isPlaceValid(c, p))
			throw new OffBoardMovementException(c);
		
		if(!this.board.isPlaceFree(c, p))
			throw new CollisionMovementException(c);
		
		return (true);
	}

	/**
	 * @return the board
	 */
	protected Gameboard getBoard() {
		return board;
	}
	
	/**
	 * @return the board
	 */
	public Gameboard getGameboard() {
		return board;
	}
	
	
}
