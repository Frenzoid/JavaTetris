package model.score;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import model.Coordinate;
import model.Game;
import model.Gameboard;
import model.Piece;
import model.PieceFactory;
import model.exceptions.TetrisException;
import model.exceptions.WrongSizeException;
import model.exceptions.io.TetrisIOException;
import model.io.GamePlay;
import model.io.IPlayer;
import model.io.IVisualizer;
import model.io.PlayerFactory;
import model.io.VisualizerFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class CodeChangesTestP5 {
	static IVisualizer ivc;
	Game game;
	
	final String splayer="S←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓S↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓←↓"+
			"L↻↻←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓↓↓↓↓I↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↻↻↻↓↓↓↓↓"+
			"↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"Z↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↻↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓↓T↻←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓J↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓←↓←↓Z↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓S↻↻↻←←←↓↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓↓↓T↺←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓J↻←↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓L↓↓↓↓↓↓↓↓↓↓↓↓↓I↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓Z↺↓↓↓↓↓↓↓↓L↻↓↓↓↓↓I↺↓J";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ivc=VisualizerFactory.createVisualizer("console");

	}

        @Rule
        public Timeout globalTimeout = Timeout.seconds(10);
        
	@Test
	public void testInitializationGettersDurationAndRowsCleared() {
		IPlayer ip=null;
		try {
			ip = PlayerFactory.createPlayer("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
			GamePlay g = new GamePlay(ip,ivc);
			assertEquals(0,g.getDuration());
			assertEquals(0,g.getRowsCleared());
		} catch (TetrisIOException e) {
		fail ("Error: no debía haberse producido la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void GetDurationtest() {
		IPlayer ip=null;
		try {
			ip = PlayerFactory.createPlayer(splayer);
			GamePlay g = new GamePlay(ip,ivc);
			assertEquals(0,g.getDuration());
			int t0= (int)new Date().getTime();
			g.play();
			int d = (int) new Date().getTime()-t0;
			assertEquals(d,g.getDuration(),10);
		} catch (TetrisIOException e) {
		fail ("Error: no debía haberse producido la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void GetRowsClearedtest() {
		IPlayer ip=null;
		try {
			ip = PlayerFactory.createPlayer(splayer);
			GamePlay g = new GamePlay(ip,ivc);
			assertEquals(0,g.getRowsCleared());
			g.play();
			assertEquals(5,g.getRowsCleared());
		} catch (TetrisIOException e) {
		fail ("Error: no debía haberse producido la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void MoveCurrentPieceDowntest() {
		PrintStream ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown7.alu");
		try {
			game = new Game(new Coordinate(9,5));
			prepareGame(game);
			try {
				game.nextPiece("I");
				game.rotateCurrentPieceCounterclockwise();	
				game.moveCurrentPieceLeft();
				for (int i=0; i<5; i++){
					assertEquals(0,game.moveCurrentPieceDown());
				}
				assertEquals(3,game.moveCurrentPieceDown());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
		} catch (WrongSizeException e1) {
			fail("Error: se produjo la excepción "+e1.getClass().getSimpleName());
		}
		ps.close();
	}
	
	/* *****************************
	 * FUNCIONES DE APOYO
	 * *****************************/
		
	//Abre un fichero para escribir los tableros que genera el alumno.
	private PrintStream openFileForWritingStudentOutput (String fichero) {
			PrintStream s=null;
			try {
				s = new PrintStream(fichero);
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			}
			return s;
	}
	
	private void prepareGame (Game game) {
		Piece []p = new Piece[7];
		p[0] = PieceFactory.createPiece("I");
		p[1] = PieceFactory.createPiece("J");
		p[2] = PieceFactory.createPiece("L");
		p[3] = PieceFactory.createPiece("O");
		p[4] = PieceFactory.createPiece("S");
		p[5] = PieceFactory.createPiece("T");
		p[6] = PieceFactory.createPiece("Z");
		for (int i=0; i<7; i++) p[i].setFixed(true);
		Gameboard gb = game.getGameboard();
		gb.setCellContent(new Coordinate(5,2),p[4]);
		gb.setCellContent(new Coordinate(6,1), p[3]);
		gb.setCellContent(new Coordinate(4,4), p[5]);
		gb.setCellContent(new Coordinate(1,4), p[0]);
		gb.setCellContent(new Coordinate(2,2), p[1]);
		gb.setCellContent(new Coordinate(2,3), p[5]);
		gb.setCellContent(new Coordinate(2,4), p[2]);
		gb.setCellContent(new Coordinate(3,2), p[4]);
		gb.setCellContent(new Coordinate(3,3), p[4]);
		gb.setCellContent(new Coordinate(4,1), p[3]);
		gb.setCellContent(new Coordinate(4,2), p[3]);
		gb.setCellContent(new Coordinate(4,3), p[6]);
		gb.setCellContent(new Coordinate(4,4), p[5]);
		gb.setCellContent(new Coordinate(5,1), p[2]);
		gb.setCellContent(new Coordinate(5,2), p[4]);
		gb.setCellContent(new Coordinate(5,3), p[2]);
		gb.setCellContent(new Coordinate(5,4), p[3]);
		gb.setCellContent(new Coordinate(6,1), p[3]);
		gb.setCellContent(new Coordinate(6,2), p[3]);
		gb.setCellContent(new Coordinate(6,3), p[3]);
		gb.setCellContent(new Coordinate(6,4), p[6]);
		gb.setCellContent(new Coordinate(7,1), p[4]);
		gb.setCellContent(new Coordinate(7,2), p[1]);
		gb.setCellContent(new Coordinate(7,3), p[2]);
		gb.setCellContent(new Coordinate(8,1), p[3]);
		gb.setCellContent(new Coordinate(8,2), p[0]);
		gb.setCellContent(new Coordinate(8,3), p[0]);
		gb.setCellContent(new Coordinate(8,4), p[4]);	
	}


}
