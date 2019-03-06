package model.exceptions;

/**
 *  Clase de Excepcion
 *  @author  Frenzoid
 *
 */
public class TetrisException extends Exception{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase excepcion.
	 */
	public TetrisException() {
        
    }
	
	/**
	 * metodo que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "Error en el juego.";
	}
}
