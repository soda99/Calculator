package cs2s03;

class Minus extends Expr { // NOT BinaryOp!
	Expr left;
	Expr right;
	Minus(Expr x, Expr y) { left = x; right = y; }
	public String toString() { 
		return betweenParens(left) + " - " + betweenParens(right); }
	public boolean isGround() { return false; }
	
	@Override
	public int evalToInt() throws NotAnInteger {
		if (this.left.evalToInt()!=this.left.evalToFloat() || this.right.evalToInt()!=this.right.evalToFloat()){
			throw new NotAnInteger("Subraction will return a fraction");
		}
		return this.left.evalToInt()-this.right.evalToInt(); 
	}
	
	@Override
	public double evalToFloat() {
		return this.left.evalToFloat()-this.right.evalToFloat();
	}
	
	public static void main(String[] args){
		Integer2 i = new Integer2(3);
		Integer2 j = new Integer2(1);
		Integer2 k = new Integer2(4);
		Minus m = new Minus(i,j);
		Divide a = new Divide(m,k);
		System.out.println(m.toString());
		System.out.println(a.toString());
		int v;
		try { v = a.evalToInt(); System.out.println(v); } catch (NotAnInteger e) { System.out.println(e.msg); }
		double v2 = a.evalToFloat();
		System.out.println(v2);
	}
}
