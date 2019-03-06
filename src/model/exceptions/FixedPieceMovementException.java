package model.exceptions;

/**
 * Clase de excepcion
 *  @author  Frenzoid
 *
 */
public class FixedPieceMovementException extends MovementException {
	/**
	 * Serial VErsion UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase de excepcion.
	 */
	public FixedPieceMovementException() {
        
    }
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 */
	public String getMessage() {
		return "No se puede maniobrar, la pieza actual esta fija.";
	}
}
