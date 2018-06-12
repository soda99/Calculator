package cs2s03;

class UnaryMinus extends Expr {
	Expr e;

	UnaryMinus(Expr x) { e = x; }
	
	public String toString() { // Edited a little because using UnaryMinus on a non-ground Expr did not print properly
		if (e.isGround()){ return "-" + e.toString(); } 
		else { return "-" + betweenParens(e); }
	}
	
	public boolean isGround() { return false; }
	
	@Override
	public int evalToInt() throws NotAnInteger {
		if (e.evalToInt()!=e.evalToFloat()){
			throw new NotAnInteger("The expression being negated is a fraction");
		}
		return (-1)*e.evalToInt();
	}
	
	@Override
	public double evalToFloat() {
		return (-1.0)*e.evalToFloat();
	}
	
	public static void main(String[] args){
		Integer2 i = new Integer2(3);
		Integer2 j = new Integer2(1);
		Integer2 k = new Integer2(4);
		Integer2 l = new Integer2(2);
		Plus p = new Plus(i,j);
		UnaryMinus n = new UnaryMinus(p);
		Times m = new Times(l,k);
		Divide a = new Divide(m,n);
		System.out.println(n.toString());
		System.out.println(m.toString());
		System.out.println(a.toString());
		int v;
		try { v = a.evalToInt(); System.out.println(v); } catch (NotAnInteger e) { System.out.println(e.msg); }
		double v2 = a.evalToFloat();
		System.out.println(v2);
	}
}
