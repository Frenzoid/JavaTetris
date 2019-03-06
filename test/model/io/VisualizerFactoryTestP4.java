package model.io;

import static org.junit.Assert.*;

import model.exceptions.io.TetrisIOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class VisualizerFactoryTestP4 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

        @Rule
        public Timeout globalTimeout = Timeout.seconds(10);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateVisualizer() {
		try {
			IVisualizer iv=VisualizerFactory.createVisualizer("console");
			assertNotNull(iv);
			assertEquals("VisualizerConsole",iv.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test(expected=TetrisIOException.class)
	public void testCreateVisualizerTetrisIOException1() throws TetrisIOException {
			VisualizerFactory.createVisualizer("Console");
	}

}
