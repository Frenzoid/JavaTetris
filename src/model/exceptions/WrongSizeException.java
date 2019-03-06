package model.exceptions;

import model.Coordinate;
/**
 *  Clase de Excepcion
 *  @author  Frenzoid
 *
 */
public class WrongSizeException extends TetrisException {
	/**
	 * Serial VErsion UID
	 */
	private static final long serialVersionUID = 1L;
	private Coordinate c;
	
	/**
	 * Constructor de la excepcion
	 * @param c
	 */
	public WrongSizeException(Coordinate c) {
        this.c = c;
    }
	
	/**
	 * Funcion que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "El tamanyo del tablero " + this.c.toString() + " es menor que el tamanyo minimo requerido.";
	}
}
