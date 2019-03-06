package model;
/**
 * 	Clase que respresenta una pieza concreta
 *  @author  Frenzoid
 *
 */
public class T extends Piece {

	/**
	 * Figura de la pieza expresado en un vector 2D.
	 */
	private int shape[][] = new int[][] {
	      { 0, 1, 0, 0,  1, 1, 1, 0,  0, 0, 0, 0,  0, 0, 0, 0 },
	      { 0, 1, 0, 0,  0, 1, 1, 0,  0, 1, 0, 0,  0, 0, 0, 0 },
	      { 0, 0, 0, 0,  1, 1, 1, 0,  0, 1, 0, 0,  0, 0, 0, 0 },
	      { 0, 1, 0, 0,  1, 1, 0, 0,  0, 1, 0, 0,  0, 0, 0, 0 } };
	   
	/**
	 * Constructor de la clase
	 */
	public T() {
		super();
		this.blockSymbol = '▤';
	}

	/**
	 * revuelve el {@link #shape} de la pieza.
	 */
	@Override
	protected int[][] getShape() {
      return shape;
	}

	/**
	 * Implementacion de la interfaz de la superclase (para copiar usando el operador "=")
	 */
	@Override
	public T copy() {
		T p= new T();
	   p.copyAttributes(this);
	   return p;
	}

}
