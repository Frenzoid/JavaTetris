package model.exceptions;
/**
 * Clase de excepcion
 *  @author  Frenzoid
 *
 */
public class NoCurrentPieceException extends TetrisException {
	
	/**
	 * Serial VErsion UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase
	 */
	public NoCurrentPieceException() {
        
    }
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "No se ha inicializado la pieza.";
	}
}
