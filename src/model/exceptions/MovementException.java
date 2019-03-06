package model.exceptions;
/**
 *  Clase de Excepcion
 *  @author  Frenzoid
 *
 */
public class MovementException extends TetrisException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase excepcion.
	 */
	public MovementException() {
        
    }
	
	/**
	 * Metodo que devuelve el mesaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "Error al mover la pieza.";
	}
}
