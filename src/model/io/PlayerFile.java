package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.exceptions.TetrisException;
import model.exceptions.io.TetrisIOException;

/**
 * Clase playerFIle, devuelve movimientos desde un archivo.
 *  @author  Frenzoid
 *
 */
public class PlayerFile implements IPlayer {
	
	/**
	 * br, el fichero leeido
	 */
	private BufferedReader br;
	
	/**
	 * Constructor de la clase.
	 * @param operationSheet, fichero leeido.
	 */
	protected PlayerFile(BufferedReader operationSheet) {
		this.br = Objects.requireNonNull(operationSheet, "El archivo no se ha cargado correctamente (es null)" );
	}
	
	/**
	 * Devuelve el siguiente movimiento por hacer.
	 */
	@Override
	public char nextMove() throws TetrisIOException {
		try {
			String move = this.br.readLine();
		
			if(move == null)
				return IPlayer.LAST_MOVE;
			else {
				try {
					move = move.trim().replaceAll(" +", " ");
					String commands[] = move.split(" ");
					
					if(commands.length >= 3 )
						throw new TetrisIOException("Los comandos solo permiten 1 paramentro.");

					move = commands[0] + " " + commands[1];
					
				}catch(Exception e) {
					throw new TetrisIOException("Te has olvidado de parte del comando, macho..");
				}
				
			}
			
			switch(move) {
			case "put I":
				return 'I';
			case "put J":
				return 'J';
			case "put L":
				return 'L';
			case "put O":
				return 'O';
			case "put S":
				return 'S';
			case "put T":
				return 'T';
			case "put Z":
				return 'Z';
			case "move left":
				return '←';
			case "move right":
				return '→';
			case "move down":
				return '↓';
			case "rotate clockwise":
				return '↻';
			case "rotate counterclockwise":
				return '↺';
			default:
				throw new TetrisIOException("Movimiento '" + move + "' no es un movimiento valido.");
			}
			
		}catch(IOException e) {
			throw new TetrisIOException("Error maniobrando con el fichero.");
		}

	}
}
