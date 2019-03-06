package model.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.exceptions.io.TetrisIOException;

/**
 * 	Clase de factorya de jugadores.
 *  @author  Frenzoid
 *
 */
public class PlayerFactory {

	/**
	 * COnstructor de la calse.
	 */
	public PlayerFactory() {
		
	}

	/**
	 * Factoria de jugadores.
	 * @param playerType, typo de jugador.
	 * @return el jugador.
	 * @throws TetrisIOException, excepcion de creacion de jugador desde fichero.
	 */
	public static IPlayer createPlayer(String playerType) throws TetrisIOException {
		IPlayer player = null;
		
		
		if(playerType.contains(".") || playerType.contains("/") || playerType.contains("\\")) {
			try {
				File fl = new File(playerType);
		        if(!fl.exists() || !fl.canRead())
					throw new TetrisIOException("El archivo no existe, o no se puede leer");

				
				FileReader fr = new FileReader(playerType);
				BufferedReader bf = new BufferedReader(fr);
				player = new PlayerFile(bf);
			} catch(IOException e) {
				throw new TetrisIOException("No se pudo crear el objeto de la clase BufferedReader desde el fichero");
			}
			
		} else if (PlayerFactory.isLong(playerType)){
			player = new PlayerRandom(Long.parseLong(playerType));
			
		} else if (playerType != ""){
			player = new PlayerString(playerType);
		} else if(playerType == ""){
			player = new PlayerKeyboard();
		} 
			
		return player;
		
	}
	
	/**
	 * define si un string es un long o no lo es.
	 * @param possibleSeed el string que puede ser un long.
	 * @return true si lo es, false si no lo es.
	 */
	private static boolean isLong(String possibleSeed) {
		try {
			Long.parseLong(possibleSeed);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
}
