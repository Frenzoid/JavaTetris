package model.exceptions;

/**
 * Clase de excepcion
 *  @author  Frenzoid
 *
 */
public class CurrentPieceNotFixedException extends TetrisException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase excepcion.
	 */
	public CurrentPieceNotFixedException() {
        
    }
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 */
	public String getMessage() {
		return "La pieza actual no esta fija";
	}
}

