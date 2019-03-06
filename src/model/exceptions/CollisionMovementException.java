package model.exceptions;

import model.Coordinate;
/**
 *  Clase de Excepcion
 *  @author  Frenzoid
 *
 */
public class CollisionMovementException extends MovementException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Coordenada detonante de la excepcion.
	 */
	private Coordinate c;
	
	/**
	 * Constructor de la calse excepcion.
	 * @param c, coordenada detonante
	 */
	public CollisionMovementException(Coordinate c) {
        this.c = c;
    }
	
	public String getMessage() {
		return "La nueva posicion " + this.c.toString() + "implica una colision con otra/s piezas.";
	}
}
