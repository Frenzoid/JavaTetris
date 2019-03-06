package model.score;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import model.exceptions.io.TetrisIOException;
import model.exceptions.score.RankingException;
import model.io.GamePlay;
import model.io.IPlayer;
import model.io.IVisualizer;
import model.io.PlayerFactory;
import model.io.VisualizerFactory;

import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class RankingTestP5 {
	static IVisualizer iv;
	IPlayer []ip; 
	String []names={"LUIS","PACO","JAVIER","ANDRES","PEDRO", "JOSE MIGUEL","PABLO"};
	Ranking<RowsClearedScore> rcs;
	Ranking<TimeScore> rts;
	RowsClearedScore srows;
	TimeScore stime;
	Integer duration;
	String []ranking2 = {"PACO", "ANDRES", "LUIS", "PABLO", "JAVIER", "PEDRO", "JOSE MIGUEL"};
	String []namesranking =	{"JOSE MIGUEL", "PABLO", "PEDRO", "ANDRES", "JAVIER", "LUIS", "PACO"};
	int []valuesranking = {5, 4, 4, 2, 2, 0, 0};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		iv = VisualizerFactory.createVisualizer("console");
	}

        @Rule
        public Timeout globalTimeout = Timeout.seconds(10);


	@Before
	public void setUp() throws Exception {
		ip = new IPlayer[7];
		ip[0] = PlayerFactory.createPlayer("54322");
		ip[1] = PlayerFactory.createPlayer("918273");
		ip[2]= PlayerFactory.createPlayer("test/files/P4/testPlay2.in");
		ip[3] = PlayerFactory.createPlayer("test/files/P4/testPlay4.in");
		ip[4] = PlayerFactory.createPlayer("test/files/P4/testPlay5.in");
		ip[5] = PlayerFactory.createPlayer("test/files/P4/testPlay6.in");
		ip[6] = PlayerFactory.createPlayer("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I" +
				"←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←" + 
				"↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↺→↻→→→↓↓↓↓↓↓↓↓" +
				"↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓" +
				"↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		rcs=new Ranking<RowsClearedScore>();
		rts=new Ranking<TimeScore>();
	}


	@After
	public void tearDown() {
	  rcs.getSortedRanking().clear();
	  rts.getSortedRanking().clear();
	}
	
	@Test
	public void testRanking() {

		SortedSet<RowsClearedScore> sset1=rcs.getSortedRanking();
		assertEquals(0,sset1.size());
		SortedSet<TimeScore> sset2=rts.getSortedRanking();
		assertEquals(0,sset2.size());		
	}

	@Test
	public void testAddScoreRowsCleared1() {
		GamePlay gp = new GamePlay(ip[2],iv);
		SortedSet<RowsClearedScore> sset;
		try {
			gp.play();
			srows = new RowsClearedScore("Andrés",gp);
			rcs.addScore(srows);
			sset=rcs.getSortedRanking();
			assertEquals(1,sset.size());
			assertSame(srows,sset.first());
			assertEquals("Andrés",srows.getName());
			assertEquals(2,srows.getScoring());
			
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testAddScoreTime1() {
		GamePlay gp = new GamePlay(ip[0],iv);
		SortedSet<TimeScore> sset;
		
		try {
			int t0= (int)new Date().getTime();
			gp.play();
			int d = t0 - (int) new Date().getTime();
			stime = new TimeScore("Andrés",gp);
			rts.addScore(stime);
			sset=rts.getSortedRanking();
			assertEquals(1,sset.size());
			assertSame(stime,sset.first());
			assertEquals("Andrés",stime.getName());
			assertEquals(d,stime.getScoring(),10);

		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
	}

	@Test
	public void testAddScoreRowsCleared2() {
		try {
			getRowsClearedScoreRanking();
			assertEquals("JOSE MIGUEL",rcs.getWinner().getName());
			assertEquals(5,rcs.getWinner().getScoring());
			SortedSet<RowsClearedScore> sset=rcs.getSortedRanking();
			int i=0;
			for (RowsClearedScore  sc : sset) {
					assertEquals(namesranking[i],sc.getName());
					assertEquals(valuesranking[i],sc.getScoring());
					i++;
			}
		} catch (RankingException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testAddScoreTime2() {
		try {
			int value;
			SortedMap<String,Integer>tset = getTimeScoreRanking();
			assertEquals("PACO",rts.getWinner().getName());
			assertEquals(tset.get("PACO").intValue(),rts.getWinner().getScoring(),10);
			SortedSet<TimeScore> sset=rts.getSortedRanking();
			for (TimeScore ts : sset){
				value = tset.get(ts.getName());
				assertEquals(value,ts.getScoring(),10);	
			}
			System.out.println(rts.toString());
		} catch (RankingException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: no debió lanzarse la excepcion: "+e.getClass().getSimpleName());
		}
	}
	
	@Test(expected=RankingException.class)
	public void testGetWinnerException1() throws RankingException {
		rcs.getWinner();
	}
	
	@Test(expected=RankingException.class)
	public void testGetWinnerException2() throws RankingException {
		rts.getWinner();
	}

	//FUNCIONES AUXILIARES
	private void getRowsClearedScoreRanking() throws TetrisIOException {
		  int i=0;
		  for (IPlayer ipl: ip) {
			GamePlay gp = new GamePlay(ipl,iv);
			gp.play();
			srows = new RowsClearedScore(names[i],gp);
			rcs.addScore(srows);
			i++;
		 }
	}
	
	//Se almacenan los nombres y la duración de cada partida en un SortedMap para compararlos luego con
	//los de Ranking
	private SortedMap<String,Integer> getTimeScoreRanking() throws TetrisIOException {
		  SortedMap<String,Integer> tset=new TreeMap<String,Integer>();
		  int i=0;
		  Integer duration;
		  int t0;
		  for (IPlayer ipl: ip) {
			GamePlay gp = new GamePlay(ipl,iv);
			t0 = (int) new Date().getTime();
 			gp.play();
 			duration=new Integer(t0 - (int) new Date().getTime());
			stime = new TimeScore(names[i],gp);
			rts.addScore(stime); //Almacenamos el Score en el Ranking
			tset.put(names[i],duration); //Almacenamos la duración y nombre en el SortedMap
			i++;
		  }
		  return tset;
	}
}
