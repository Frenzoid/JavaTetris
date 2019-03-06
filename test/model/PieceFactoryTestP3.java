package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PieceFactoryTestP3 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateIPiece() {
		I ip = (I) PieceFactory.createPiece("I");
		assertNotNull(ip);
	}
	
	@Test
	public void testCreateJPiece() {
		J jp = (J) PieceFactory.createPiece("J");
		assertNotNull(jp);
	}
	
	@Test
	public void testCreateLPiece() {
		L lp = (L) PieceFactory.createPiece("L");
		assertNotNull(lp);
	}
	
	@Test
	public void testCreateOPiece() {
		O op = (O) PieceFactory.createPiece("O");
		assertNotNull(op);
	}
	
	@Test
	public void testCreateSPiece() {
		S sp = (S) PieceFactory.createPiece("S");
		assertNotNull(sp);
	}
	
	@Test
	public void testCreateTPiece() {
		T tp = (T) PieceFactory.createPiece("T");
		assertNotNull(tp);
	}
	
	@Test
	public void testCreateZPiece() {
		Z zp = (Z) PieceFactory.createPiece("Z");
		assertNotNull(zp);
	}
	
	@Test
	public void testCreatePieceNull() {
		assertNull(PieceFactory.createPiece("-"));
	}
}
