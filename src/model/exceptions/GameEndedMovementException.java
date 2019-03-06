package model.exceptions;
/**
 * Clase de excepcion
 *  @author  Frenzoid
 *
 */
public class GameEndedMovementException extends MovementException {
	/**
	 * Serial VErsion UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase de excepcion.
	 */
	public GameEndedMovementException() {
        
    }
	
	/**
	 * Metodo que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "El juego ha acabado, no se aceptan nuevos comandos.";
	}
}
