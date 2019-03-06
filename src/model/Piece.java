/**
 * 
 */
package model;

import java.util.Objects;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 *  Clase que representa una pieza (GENERICA).
 *  @author  Frenzoid
 *
 */
public abstract class Piece {
	

	/**
	 * Estado de la pieza, si esta encajada o no (inamovible).
	 */
	private boolean fixed;

	/**
	 * Caracter que tendra el sprite solido de la pieza.
	 */
	protected char blockSymbol;
	
	/**
	 * Orientacion de la pieza.
	 */
	private Rotation orientation;
	      
	 /**
	  * Delimitador de seccion, define de cuanto por cuanto es el area contenedora de la pieza.
	  */
	private static int BOUNDING_SQUARE_SIZE = 4;
	
	/**
	 * Constructor de la clase, acepta un objeto de su misma clase para asignarse los atributos.
	 */
	public Piece() {
		this.fixed = false;
		this.orientation = Rotation.D0;
	}
	
	
	/**
	 * copia los atributos del objeto que viene por parametro al ojeto actual.
	 * @param p
	 */
	protected void copyAttributes(Piece p) {
		this.orientation = p.orientation;
		this.fixed = p.fixed;
		this.blockSymbol = p.blockSymbol;
	}
	
	/**
	 * Devuelve el valor del atributo fixed, el cual indica si la pieza está fijada o no.
	 * @return Un boolean determianando si esta fixed o no.
	 */
	public boolean isFixed() {
		return this.fixed;
	}
	
	/**
	 * Setter del atributo fixed.
	 * @param fixed, un booleado que determina si la pieza esta encajada o no.
	 */
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
	
	/**
	 * @return the shape
	 */
	protected abstract int[][] getShape();


	/**
	 * @return the bOUNDING_SQUARE_SIZE
	 */
	public static int getBOUNDING_SQUARE_SIZE() {
		return BOUNDING_SQUARE_SIZE;
	}

	/**
	 * @param bOUNDING_SQUARE_SIZE the bOUNDING_SQUARE_SIZE to set
	 */
	public static void setBOUNDING_SQUARE_SIZE(int bOUNDING_SQUARE_SIZE) {
		BOUNDING_SQUARE_SIZE = bOUNDING_SQUARE_SIZE;
	}

	/**
	 * @param blockSymbol the blockSymbol to set
	 */
	public void setBlockSymbol(char blockSymbol) {
		this.blockSymbol = blockSymbol;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(Rotation orientation) {
		this.orientation = Objects.requireNonNull(orientation, 
				   "El parametro 'orientation' no puede ser null");;
	}

	/**
	 * Devuelve la orientacion de la rotacion de la pieza.
	 * @return Un enumerador Rotation determinando la rotacion de la pieza.
	 */
	public Rotation getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Devuelve el blocksymbol de la pieza.
	 * @return el blocksymbol de la pieza en un char.
	 */
	public char getBlockSymbol() {
		return this.blockSymbol;
	}
	
	/**
	 * Cambia la orientacion de la rotacion de la pieza en sentido contrario de las agujas del reloj.
	 */
	public void rotateCounterclockwise() {
		this.orientation = 
			(this.orientation.equals(Rotation.D0)) ? Rotation.D90 :
			(this.orientation.equals(Rotation.D90)) ? Rotation.D180 : 
			(this.orientation.equals(Rotation.D180)) ? Rotation.D270 : Rotation.D0;
	}
	
	/**
	 * Cambia la orientacion de la rotacion de la pieza en sentido de las agujas del reloj.
	 */
	public void rotateClockwise() {
		this.orientation = 
			(this.orientation.equals(Rotation.D0)) ? Rotation.D270 :
			(this.orientation.equals(Rotation.D270)) ? Rotation.D180 : 
			(this.orientation.equals(Rotation.D180)) ? Rotation.D90 : Rotation.D0;
	}
	
	/**
	 * Recibe la coordenada de posicionamiento, y devuelve un set de coordenadas ocupadas relativas al tablero.
	 * @param obj, la coordenada a anyadir al set.
	 * @return el set con la coordenada pasada por parametro.
	 */
	public Set<Coordinate> getAbsoluteCells(Coordinate tabCoord) {
	    Set<Coordinate> squares = new HashSet<Coordinate>();
		
		/**
		 * Ya que el sprite de cada rotacion de cada pieza es un array en si, y cada 4 cifras, es una fila
		 * lo que hare sera separar cada fila en un array individual para poder manejarlo correctamente.
		 */
		/**
		 * Numero de separacion por fila. Podemos usar BOUNDING_SQUARE_SIZE ya que es lo que delimita la estructura del sprite.
		 */
		int size = Piece.BOUNDING_SQUARE_SIZE; 
		
		/**
		 * Por cada 4 cifras del array de la rotacion de la orientacion de la pieza, creamos otro array.
		 */
		for(int i = 0 ;i < this.getShape()[this.orientation.ordinal()].length; i += size){
		    int[] row = Arrays.copyOfRange(this.getShape()[this.orientation.ordinal()], i, Math.min(this.getShape()[this.orientation.ordinal()].length,i+size));
		    
		    /**
		     * Por cada numero de la columna de la fila, hacemos la suma con la coordenada de referencia de la tabla.
		     */
		    for(int x = 0; x < row.length; x++) {
		    	if(row[x] != 0) {
		    		
		    		int[] piecePartCoords = {(i/4) + tabCoord.getRow(), x + tabCoord.getColumn()}; 
		    		/**
		    		 * Y anyadimos las coordenadas de la pieza referentes al tablero al set.
		    		 */
		    		Coordinate coordinateSolidPartPiece = new Coordinate(piecePartCoords[0], piecePartCoords[1]);
		    		squares.add(coordinateSolidPartPiece);
		    	}
		    }
		}    
	    
	    return squares;
	}

	/**
	 * Devuelve el sprite de la pieza en un string.
	 */
	@Override
	public String toString() {
		/**
		 * Creamos la variable que contendra el spirte.
		 */
		String sprite = "";
		
		/**
		 * Como el sprite sera de 4 x 4, y el array del sprite es de 16 caracteres, usamos un
		 * for para imprimir los carcteres del sprite. Cada 4 caracteres anyadimos un retorno de carro.
		 */
		for(int i = 0; i < Piece.BOUNDING_SQUARE_SIZE * Piece.BOUNDING_SQUARE_SIZE; i++) {
			sprite += (this.getShape()[this.orientation.ordinal()][i] == 0) ? "·" : this.blockSymbol;
			if((i+1)%4 == 0) sprite += "\n";
		}
		
		/**
		 * y por ultimo devolvemos el sprite en una variable string.
		 */
		return sprite;
	}

	/**
	 * Se usa para copiar objetos del mismo subtipo.
	 * @return la pieza copiada.
	 */
	public abstract Piece copy();	

	
}
