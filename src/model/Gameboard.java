package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.exceptions.WrongSizeException;

/**
 * Enumerador de orientaciones de la rotacion de una pieza.
 *  @author  Frenzoid
 */
public class Gameboard {
	/**
	 * Determina la altura maxima del tablero.
	 */
	private static final int MINIMUM_BOARD_HEIGHT = 4;
	
	/**
	 * Determina la altura minima del tablero.
	 */
	private static final int MAXIMUM_BOARD_WIDTH = 4;

	/**
	 * Determina la altura del tablero.
	 */
	private int height;
	
	/**
	 * Determina la anchura del tablero.
	 */
	private int width;
	
	/**
	 * Rererencia a cada coordenada de cada punto del tablero.
	 */
    private Map<Coordinate, Piece> gameboard = new HashMap<Coordinate, Piece>();

	/**
	 * Constructor de la clase gameboard.
	 * @param c, recibe una coordenda de la cual cojera el row y el column para dar tamanyo al tablero.
	 * @throws WrongSizeException
	 */
	public Gameboard(Coordinate c) throws WrongSizeException {
		if(c.getRow() < Gameboard.MINIMUM_BOARD_HEIGHT || c.getColumn() < Gameboard.MAXIMUM_BOARD_WIDTH )
			throw new WrongSizeException(c);
		
		this.height = c.getRow();
		this.width = c.getColumn();
	}

	/**
	 * Asigna la posicion del tablero a las partes solidas de una pieza.
	 * @param c, La posicion (coordinate) a spawnear.
	 * @param p, la pieza (Piece) a spawnear.
	 */
	public void putPiece(Coordinate c, Piece p) {
		for(int i = 0; i < p.getAbsoluteCells(c).size(); i++) {
			this.gameboard.put((Coordinate)p.getAbsoluteCells(c).toArray()[i], p);
		}
	}
	
	/**
	 * Determina si la posicion esta fuera del tablero o no.
	 * @param c, la coordenada de referencia a comprobar con la pieza.
	 * @param p, la pieza a comprobar.
	 * @return un booleano, true si esta dentro del tablero, false si esta fuera.
	 */
	public boolean isPlaceValid(Coordinate c, Piece p) {
		for(int i = 0; i < p.getAbsoluteCells(c).size(); i++) {
			int row = ((Coordinate)p.getAbsoluteCells(c).toArray()[i]).getRow();
			int col = ((Coordinate)p.getAbsoluteCells(c).toArray()[i]).getColumn();
			
			/**
			 * Si alguna coordenada de alguna parte solida de la pieza esta fuera del rango del tablero, devuelve false.
			 * o en su defecto, si todo esta dentro de los margenes devuelve true.
			 */
			if(row >= this.height || row < 0 || col >= this.width || col < 0)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Determina si la posicion esta vacia o no.
	 * @param c, la coordenada de referencia a comprobar con la pieza.
	 * @param p, la pieza a comprobar.
	 * @return un booleano que determina si la posicion esta vacia o no.
	 */
	public boolean isPlaceFree(Coordinate c, Piece p) {
		for(int i = 0; i < p.getAbsoluteCells(c).size(); i++) {
			Coordinate coord = (Coordinate)(p.getAbsoluteCells(c).toArray())[i];
			
			int row = coord.getRow();
			int col = coord.getColumn();
			
			/**
			 * Si alguna coordenada de alguna parte solida de la pieza esta colisionando con otra pieza y esa pieza
			 * no es ella misma, devuelve false, y si no hay colision, devuelve true.
			 */
			Piece piece = this.gameboard.get(new Coordinate(row, col)); 
			if(piece != null && piece.isFixed())
				return false;
		}
		
		return true;
	}
	
	/**
	 * Quita del tablero todas las coordendas de una pieza.
	 * @param p, la pieza a quitar.
	 */
	public void removePiece(Piece p) {
		
		Set<Coordinate> coordenadas = new HashSet<Coordinate>(this.gameboard.keySet());

		for (Coordinate coord : coordenadas) {
			Piece tablepiece = this.gameboard.get(coord);
			if( tablepiece == p) {
				this.gameboard.remove(coord);
			}
		}
	}
	
	/**
	 * Devuelve el objeto Pieza establecido en esa coordenada.
	 * @param c la coordenada a revisar.
	 * @return un objeto piece que esta sobre esa posicion, o null si no hay ningun pieza.
	 */
	public Piece getCellContent(Coordinate c) {
		return this.gameboard.get(c);
	}
	
	/**
	 * Anyade una unica parte solida de pieza a la celda.
	 * @param c, la coordenada donde anyadir.
	 * @param p, la pieza a anyadir.
	 */
	public void setCellContent(Coordinate c, Piece p) {
		this.gameboard.put(c, p);
	}
	
	/**
	 * Imprime el tablero con las piezas en un string.
	 */
	@Override
	public String toString() {
		String gameboardSprite = "";
		
		for(int i = 0; i < this.height; i++) {
			for(int x = 0; x < this.width; x++) {
				Coordinate coord = new Coordinate(i, x);
				
				if(this.gameboard.get(coord) != null) {
					gameboardSprite += this.gameboard.get(coord).getBlockSymbol();
				} else {
					gameboardSprite += '·';
				}
				
				if ((this.width - 1) == x) gameboardSprite += '\n';
			}
		}
		
		return gameboardSprite;
	}

	/**
	 * devuelve la altura del tablero.
	 * @return un entero que es la altura del tablero.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Devuelve la anchura del tablero.
	 * @return un entero que es la anchura del tablero.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Comprueba si una fila esta llena.
	 * @param row, la fila a comprobar
	 * @return booleano, true si esta llena, false si no lo esta.
	 */
	private boolean isRowFull(int row) {
		if( row > this.height || row < 0)
			throw new IllegalArgumentException();
		
		Coordinate coord;
		Piece pice;
		for(int x = 0; x < this.width; x++) {
			coord = new Coordinate(row, x);
			pice = this.gameboard.get(coord);
			
			if(pice == null || !pice.isFixed())
				return false;
		}
		
		return true;
	}
	
	/**
	 * Devuelve la primera fila llena.
	 * @return int, la primera fila llena.
	 */
	public int firstRowFullFromBottom() {
		for(int i = this.height-1; i >= 0; i--) {
			if(this.isRowFull(i) == true)
				return i;
		}
		return -1;
	}
	
	
	/**
	 * Limpia una fila.
	 * @param row, la fila a borrar.
	 */
	public void clearRow(int row) {
		if( row > this.height || row < 0)
			throw new IllegalArgumentException();
		
		Coordinate coord;
		
		for(int x = 0; x < this.width; x++) {
			coord = new Coordinate(row, x);
			if(this.gameboard.get(coord) != null)
				this.gameboard.remove(coord);
		}
	}
	
	/**
	 * Mueve una fila hacia abajo todas las piezas que están en el tablero por encima de la fila indicada por el parámetro.
	 * @param int, la fila indicada.
	 */
	public void makeUpperRowsFall(int row) {
		if( row >= this.height || row < 0)
			throw new IllegalArgumentException();
		
		for(int i = row-1; i >= 0; i--) {
			this.pushDownWholeRow(i);
		}
	}
	
	/**
	 * Mueve todas las piezas de la fila seleccionada hacia abajo.
	 * @param row, la fila a empujar hacia abajo.
	 */
	private void pushDownWholeRow(int row) {
		
		for (int i = 0; i < this.width; i++) {
			Coordinate coordActual = new Coordinate(row, i);
			Coordinate coordUnderActual = new Coordinate(coordActual);
			coordUnderActual.setRow(row + 1);
			
			Piece pieceActual = this.gameboard.get(coordActual);
			
			
			
			if(	pieceActual != null) {
				this.gameboard.remove(coordActual);
				this.gameboard.put(coordUnderActual, pieceActual);
				
			}
		}
	}
}
