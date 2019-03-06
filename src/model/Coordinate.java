package model;
/**
 *  @author  Frenzoid
 */


/**
 * 
 * Clase de la coordenada que tiene atributos y metodos relacionados con las coordenadas.
 * @author frenzoid
 *
 */
public class Coordinate {

	/**
	* Fila de la coordenada.
	* */
	private int row; 
	
	/**
	* Columna de la coordenada
	*/
	private int column; 
	
	/**
	* Contador de coordenadas totales.
	*/
	private static int COORDINATE_COUNT; 
	
	
	/**
	 * Constructor de la clase, acepta row y column (2 ints) para asignarse los atributos.
	 * @param row la fila a asignarse.
	 * @param column la columna a asignarse
	 */
	public Coordinate(int row, int column){
		this.row = row;
		this.column = column;
		Coordinate.COORDINATE_COUNT++;
	}
	
	/**
	 * Constructor de la clase, acepta un objeto de su misma clase para asignarse los atributos.
	 * @param coordinate la coordenada a asignarse.
	 */
	public Coordinate(Coordinate coordinate){
		this.row = coordinate.row;
		this.column = coordinate.column;
		Coordinate.COORDINATE_COUNT++;
	}
	
	
	
	/**
	 * Getter de row
	 * @return devuelve la fila
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Setter de row
	 * @param row la fila a asignar
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * getter de column
	 * @return la columna
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * setter de column
	 * @param column la columna a asignar
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * getter de numero total de coordenadas creadas
	 * @return el numero total de coordenadas creadas,
	 */
	public static int getCoordinateCount() {
		return Coordinate.COORDINATE_COUNT;
	}
	
	
	/**
	 * strigifier de la coordenada
	 * @return la coordenada en string.
	 */
	public String toString() {
		String coordinate = new String("[" + this.row + "," + this.column + "]");
		return coordinate;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}


	/**
	 * Sumador de Coordenadas, recibe una coordenada y la suma con la actual, devuelve la suma de ambas en un objeto Coordinate.
	 * @return un objeto de clase Coordenada con el resultado de la suma de ambas coordenadas.
	 * @param obj la coordenada a sumar.
	 */
	public Coordinate add(Coordinate obj) {
		int row = this.row + obj.getRow();
		int column = this.column + obj.getColumn();
		
		Coordinate localobj = new Coordinate(row, column);
		return localobj;
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}
	
	
}
