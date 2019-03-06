package model.exceptions.io;

import model.exceptions.TetrisException;

/**
 * Clase de excepcion
 *  @author Frenzoid
 *
 */
public class TetrisIOException extends TetrisException {

	/**
	 * SERIALVERSIONID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Mesaje de error
	 */
	private String message;
	
	/**
	 * Constructor de la clase
	 * @param err, error de la excepcion
	 */
	public TetrisIOException(String err) {
		this.message = err;
	}
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 */
	public String getMessage() {
		return this.message;
	}
}
