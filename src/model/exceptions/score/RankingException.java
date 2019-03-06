package model.exceptions.score;

import model.exceptions.TetrisException;

/**
 *  Clase de Excepcion
 *  @author  Frenzoid
 *
 */
public class RankingException extends TetrisException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase excepcion.
	 */
	public RankingException() {
        
    }
	
	/**
	 * metodo que devuelve el mensaje de error.
	 * @return string, Mensaje de error.
	 */
	public String getMessage() {
		return "Error intentando manejar el ranking, lista de jugadores vacia. (No hay un primer item en la lista)";
	}
}
