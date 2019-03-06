package model.exceptions;

import model.Coordinate;
/**
 * Clase de excepcion
 *  @author  Frenzoid
 *
 */
public class OffBoardMovementException extends MovementException {
	/**
	 * Serial VErsion UID
	 */
	private static final long serialVersionUID = 1L;
	private Coordinate c;

	/**
	 * Constructor de la clase excepcion
	 * @param c, coordneada culpable de la excepcion
	 */
	public OffBoardMovementException(Coordinate c) {
        this.c = c;
    }
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "La nueva posicion " + this.c.toString() + " hace que la pieza este fuera parcialmente o totalmente del tablero";
	}
}
