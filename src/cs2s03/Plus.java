package cs2s03;

class Plus extends BinaryOp {
	Plus(Expr x, Expr y) { left = x; right = y; }
	public String toString() { 
		//we are the context
		return super.toString(this, " + "); }
	public boolean isSame(Expr e) { return e instanceof Plus; }
	
	@Override
	public int evalToInt() throws NotAnInteger {
		if (this.left.evalToInt()!=this.left.evalToFloat() || this.right.evalToInt()!=this.right.evalToFloat()){
			throw new NotAnInteger("Addition will return a fraction");
		}
		return this.left.evalToInt()+this.right.evalToInt(); 
	}
	
	@Override
	public double evalToFloat() {
		return this.left.evalToFloat()+this.right.evalToFloat();
	}
	
	public static void main(String[] args){
		Integer2 i = new Integer2(3);
		Integer2 j = new Integer2(1);
		Integer2 k = new Integer2(4);
		Integer2 l = new Integer2(0);
		Plus p = new Plus(i,j);
		Minus m = new Minus(l,k);
		Divide a = new Divide(p,m);
		System.out.println(p.toString());
		System.out.println(m.toString());
		System.out.println(a.toString());
		int v;
		try { v = a.evalToInt(); System.out.println(v); } catch (NotAnInteger e) { e.printMsg(""); }
		double v2 = a.evalToFloat();
		System.out.println(v2);
	}
}
