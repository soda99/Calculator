package cs2s03;

import static org.junit.Assert.*;
import org.junit.*;

public class TestingA5 {
	
	private Expr test;
	private int i;
	private double d;
	private String s;
	private Evaluate vi;
	private Evaluate vd;
	private IntVal iv;
	private DblVal dv;
	private static final double ERROR = 1e-20;  // used in assertEquals for double values
	
	@After
	public void tearDown(){
		test = null;
		i = 0;
		d = 0;
		s = null;
		vi = null;
		iv = null;
		vd = null;
		dv = null;
	}
	@Test
	public void TestInteger2() throws NotAnInteger{
		test = new Integer2(2);
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,2);
		assertEquals(d,2.0,ERROR);
	}
	
	@Test
	public void TestPlus1() throws NotAnInteger{
		test = new Plus(new Integer2(5),new Integer2(10));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,15);
		assertEquals(d,15.0,ERROR);
	}
	
	@Test
	public void TestPlus2() throws NotAnInteger{
		test = new Plus(new Plus(new Integer2(5),new Integer2(10)),new Plus(new Integer2(20),new Integer2(3)));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,38);
		assertEquals(d,38.0,ERROR);
	}
	
	@Test 
	public void TestMinus1() throws NotAnInteger{
		test = new Minus(new Integer2(10),new Integer2(5));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,5);
		assertEquals(d,5.0,ERROR);
	}
	
	@Test 
	public void TestMinus2() throws NotAnInteger{
		test = new Minus(new Minus(new Integer2(10),new Integer2(5)),new Minus(new Integer2(13),new Integer2(-13)));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,-21);
		assertEquals(d,-21.0,ERROR);
	}
	
	@Test
	public void TestTimes1() throws NotAnInteger{
		test = new Times(new Integer2(10),new Integer2(5));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,50);
		assertEquals(d,50.0,ERROR);
	}
	
	@Test
	public void TestTimes2() throws NotAnInteger{
		test = new Times(new Times(new Integer2(10),new Integer2(5)), new Times(new Integer2(2),new Integer2(0)));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,0);
		assertEquals(d,0.0,ERROR);
	}
	
	@Test
	public void TestDivide1() throws NotAnInteger{
		test = new Divide(new Integer2(10),new Integer2(5));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,2);
		assertEquals(d,2.0,ERROR);
	}
	
	@Test
	public void TestDivide2() throws NotAnInteger{
		test = new Divide(new Divide(new Integer2(40),new Integer2(5)),new Divide(new Integer2(16),new Integer2(2)));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,1);
		assertEquals(d,1.0,ERROR);
	}
	
	@Test
	public void TestDivide3(){	// Testing to see if exception is thrown for evalToInt() of a fraction
		test = new Divide(new Integer2(5),new Integer2(10));
		try { i = test.evalToInt(); } catch (NotAnInteger e) { e.printMsg("TestDivide3"); }
		d = test.evalToFloat();
		assertEquals(d,0.5,ERROR);
	}
	
	@Test
	public void TestDivide4() throws NotAnInteger{ // Testing 0/0
		test = new Divide(new Integer2(0),new Integer2(0));
		try { i = test.evalToInt(); } catch (ArithmeticException e) { System.out.println("TestDivide4: 0/0 = fraction"); }
		try {d = test.evalToFloat(); fail();} catch (ArithmeticException e) { System.out.println("TestDivide4: 0/0 = NaN"); }
		
	}
	@Test
	public void TestUnaryMinus1() throws NotAnInteger{
		test = new UnaryMinus(new Integer2(2));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,-2);
		assertEquals(d,-2.0,ERROR);
	}
	
	@Test
	public void TestUnaryMinus2() throws NotAnInteger{	// Testing UnaryMinus on an entire expression
		test = new UnaryMinus(new Plus(new Integer2(2),new Integer2(9)));
		i = test.evalToInt();
		d = test.evalToFloat();
		
		assertEquals(i,-11);
		assertEquals(d,-11.0,ERROR);
	}
	
	@Test
	public void TestExpr1() throws NotAnInteger{	// Testing combinations of operations
		Plus a = new Plus(new Integer2(2),new Integer2(4));
		Times b = new Times(a,new Integer2(3));
		Divide c = new Divide(b,new Integer2(9));
		Minus d2 = new Minus(c,new Integer2(5));
		i = d2.evalToInt();
		d = d2.evalToFloat();

		assertEquals(i,-3);
		assertEquals(d,-3.0,ERROR);
	}
	
	@Test
	public void TestExpr2(){
		Plus a = new Plus(new Integer2(2),new Integer2(4));
		Times b = new Times(a,new Integer2(3));
		Divide c = new Divide(b,new Integer2(36));
		test = new Minus(c,new Integer2(5));
		try { i = test.evalToInt(); } catch (NotAnInteger e) { e.printMsg("TestExpr2"); }
		d = test.evalToFloat();

		assertEquals(d,-4.5,ERROR);
	}
	
	@Test
	public void TestParser1() throws NotAnInteger, ParseError{
		s = "3+4-7+1";
		vi = new Evaluate(s,Mode.INTEGER);
		vd = new Evaluate(s,Mode.FLOAT);
		iv = (IntVal)vi.eval();
		dv = (DblVal)vd.eval();
		
		assertEquals(iv.val,1);
		assertEquals(dv.val,1.0,ERROR);
	}
	
	@Test
	public void TestParser2() throws NotAnInteger, ParseError{	// Testing bracket priority
		s = "(7-2)*(15-5)";
		vi = new Evaluate(s,Mode.INTEGER);
		vd = new Evaluate(s,Mode.FLOAT);
		iv = (IntVal)vi.eval();
		dv = (DblVal)vd.eval();
		
		assertEquals(iv.val,50);
		assertEquals(dv.val,50.0,ERROR);
	}
	
	@Test
	public void TestParser3() throws ParseError, NotAnInteger{
		s = "2*8/4+2/4";
		vi = new Evaluate(s,Mode.INTEGER);
		vd = new Evaluate(s,Mode.FLOAT);
		try { iv = (IntVal)vi.eval(); } catch (NotAnInteger e) { e.printMsg("TestParser4"); }
		dv = (DblVal)vd.eval(); 

		assertEquals(dv.val,1.5,ERROR);
	}
	
	@Test
	public void TestParser4(){ // Catching incomplete expressions in the parser
		s = "2-";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser3: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser3: " + e); }
	}
	
	@Test 
	public void TestParser5(){
		s = "3+(";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser5: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser5: " + e); }
	}
	
	@Test 
	public void TestParser6(){
		s = "(4*2";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser6: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser6: " + e); }
	}
	
	@Test 
	public void TestParser7(){
		s = "4-/";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser7: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser7: " + e); }
	}
	
	@Test 
	public void TestParser8(){
		s = ")3+4";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser8: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser8: " + e); }
	}
	
	@Test
	public void TestParser9(){ // Passing in completely invalid strings
		s = "cheese";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser9: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser9: " + e); }
	}
	
	@Test
	public void TestParser10(){ // Passing in completely invalid strings
		s = "3/1+5-x";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser10: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser10: " + e); }
	}
	
	@Test
	public void TestParser11() throws NotAnInteger{ // Testing ArithmeticException for 0/0
		s = "0/0";
		try { vi = new Evaluate(s,Mode.INTEGER); } catch (ParseError e) { System.out.println("TestParser11: " + e); }
		try { vd = new Evaluate(s,Mode.FLOAT); } catch (ParseError e) { System.out.println("TestParser11: " + e); }
		
		try { iv = (IntVal)vi.eval(); } catch(ArithmeticException e){ System.out.println("TestParser11: = fraction"); }
		try { dv = (DblVal)vd.eval(); } catch(ArithmeticException e){ System.out.println("TestParser11: = NaN"); }
	}
}
