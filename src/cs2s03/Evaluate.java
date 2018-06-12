package cs2s03;

public class Evaluate {
	private Expr e;
	private Mode m;
	
	Evaluate(String s, Mode m) throws ParseError{
		this.m = m;
		
		Parser p = new Parser(s);
		this.e = p.parse();
		
	}
	
	public Value eval() throws NotAnInteger{
		if (this.m == Mode.INTEGER){
			IntVal v = new IntVal();
			v.m = Mode.INTEGER;
			v.val = e.evalToInt();
			return v;
		} else {
			DblVal v = new DblVal();
			v.m = Mode.FLOAT;
			v.val = e.evalToFloat();
			return v;
		}
	}
	
	public static void main(String[] args) throws NotAnInteger, ParseError{
		String s = "(2*4)/(-(3+1))";
		Evaluate i = new Evaluate(s,Mode.INTEGER);
		Evaluate d = new Evaluate(s,Mode.FLOAT);
		IntVal v = (IntVal) i.eval();
		DblVal v2 = (DblVal) d.eval();
		System.out.print(v.m + "\t");System.out.println(v.val);
		System.out.print(v2.m + "\t");System.out.println(v2.val);
	}
}
