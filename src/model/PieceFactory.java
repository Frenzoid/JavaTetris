package model;

/**
 * factoria de Piezas.
 *  @author  Frenzoid
 *
 */
public class PieceFactory {
	/**
	 * Crea y devuelve un tipo de pieza diferente dependiendo del valor del parámetro.
	 * @param pieceName, el valor a pedir.
	 * @return el objeto pedido.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Piece createPiece(String pieceName) {
		
		try {
			Class<?> reflectedPiece = Class.forName("model." + pieceName); // ?
			Piece piece = (Piece) reflectedPiece.newInstance();
			return piece;
		} catch(ClassNotFoundException e) {
			return null;
		} catch(IllegalAccessException e) {
			System.err.println("Illegal access to the reflected class");
		} catch(InstantiationException e) {
			System.err.println("Error Instantiating the class");
		} catch(Exception e) {
			System.err.println("Error handeling the reflection");
		}
		return null;
	}
}
