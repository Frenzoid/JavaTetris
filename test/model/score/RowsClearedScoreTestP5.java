package model.score;

import static org.junit.Assert.*;

import model.exceptions.io.TetrisIOException;
import model.io.GamePlay;
import model.io.IPlayer;
import model.io.IVisualizer;
import model.io.PlayerFactory;
import model.io.VisualizerFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class RowsClearedScoreTestP5 {

	 IPlayer ip1,ip2;
	 static IVisualizer iv;
	 Score sc1, sc2;
	 GamePlay gp1, gp2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		iv = VisualizerFactory.createVisualizer("console");
		
	}

        @Rule
        public Timeout globalTimeout = Timeout.seconds(10);

	@Before
	public void setUp() throws Exception {
		ip1 = PlayerFactory.createPlayer("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		ip2 = PlayerFactory.createPlayer("54333");
		gp1 = new GamePlay(ip1,iv);
		gp2 = new GamePlay(ip2,iv);
	}

	@Test
	public void testScore() {
		sc1=new RowsClearedScore("Gabriel",gp1);
		sc2=new RowsClearedScore("Gabino",gp2);
		assertEquals(sc1.getName(),"Gabriel");
		assertEquals(sc2.getName(),"Gabino");
	}

	@Test
	public void testToString() {
		try {
			gp1.play();
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
		sc1=new RowsClearedScore("Gabriel",gp1);
		assertEquals("Gabriel:2",sc1.toString());
	}

	//Todavía no se ha jugado
	@Test
	public void testGetScoring0() {
		sc1=new RowsClearedScore("Gabriel",gp1);
		sc2=new RowsClearedScore("Gabino",gp2);
		assertEquals(0,sc1.getScoring());
		assertEquals(0,sc2.getScoring());
	}
	
	@Test
	public void testGetScoring1() {
		
		try {
			gp1.play();
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
		sc1=new RowsClearedScore("Gabriel",gp1);
		assertEquals(2,sc1.getScoring());
	}
	
	@Test
	public void testGetScoring2() {
				
		try {
			gp2.play();
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
		sc2=new RowsClearedScore("Gabino",gp2);
		assertEquals(0,sc2.getScoring());
	}
	
	//Sin haber jugado todavía
	@Test
	public void testCompareTo0() {
		sc1=new RowsClearedScore("Gabriel",gp1);
		sc2=new RowsClearedScore("Gabino",gp2);
		assertEquals(-9, sc2.compareTo(sc1));
		assertEquals(9, sc1.compareTo(sc2));
		assertEquals(0, sc2.compareTo(sc2));
	}

	
	@Test
	public void testCompareTo1() {
		try {
			gp1.play();
			gp2.play();
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
		sc1=new RowsClearedScore("Gabriel",gp1);
		sc2=new RowsClearedScore("Gabino",gp2);
		assertTrue(sc2.compareTo(sc1)>0);
		assertTrue(sc1.compareTo(sc2)<0);
		assertEquals(0, sc2.compareTo(sc2));
	}
	
	@Test
	public void testEqualsObject() {
		try {
			gp1.play();
			gp2.play();
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
		Score sc3=new RowsClearedScore("Gabriel",gp1);
		Score sc4=new RowsClearedScore("Gabrielete",gp1);
	    sc1=new RowsClearedScore("Gabriel",gp1);
		sc2=new RowsClearedScore("Gabino",gp2);
		assertFalse(sc1.equals(sc2));
		assertTrue(sc1.equals(sc1));
		assertFalse(sc1.equals(null));
		assertTrue(sc1.equals(sc3));
		assertFalse(sc3.equals(sc4));
	}

}
