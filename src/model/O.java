package model;
/**
 * 	Clase que respresenta una pieza concreta
 *  @author  Frenzoid
 *
 */
public class O extends Piece {

	/**
	 * Figura de la pieza expresado en un vector 2D.
	 */
	private int shape[][] = new int[][] {
	      { 0, 1, 1, 0,  0, 1, 1, 0,  0, 0, 0, 0,  0, 0, 0, 0 },
	      { 0, 1, 1, 0,  0, 1, 1, 0,  0, 0, 0, 0,  0, 0, 0, 0 },
	      { 0, 1, 1, 0,  0, 1, 1, 0,  0, 0, 0, 0,  0, 0, 0, 0 },
	      { 0, 1, 1, 0,  0, 1, 1, 0,  0, 0, 0, 0,  0, 0, 0, 0 } };
	   
	/**
	 * Constructor de la clase
	 */
	public O() {
		super();
		this.blockSymbol = '▣';
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
	public O copy() {
		O p= new O();
	   p.copyAttributes(this);
	   return p;
	}

}
