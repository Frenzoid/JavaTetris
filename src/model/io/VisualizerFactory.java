package model.io;

import model.exceptions.io.TetrisIOException;

/**
 * Clase factoria de visualizadores.
 *  @author  Frenzoid
 */
public class VisualizerFactory {
	/**
	 * Constructor de la calse.
	 */
	public VisualizerFactory() {
		
	}
	
	/**
	 * Proporciona un visualizador.
	 * @throws TetrisIOException 
	 */
	public static IVisualizer createVisualizer(String vi) throws TetrisIOException {
		switch(vi) {
		case "console":
			return new VisualizerConsole();
		case "window":
			return new VisualizerWindow();
		default: throw new TetrisIOException("No existe el tipo de visualizador que quieres crear.");
		}
	}
	
}
