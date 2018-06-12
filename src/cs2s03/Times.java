package cs2s03;

class Times extends BinaryOp {
	Times(Expr x, Expr y) { left = x; right = y; }
	public String toString() { 
		// we are the context
		return super.toString(this, " * "); }
	public boolean isSame(Expr e) { return e instanceof Times; }
	
	@Override
	public int evalToInt() throws NotAnInteger {
		if (this.left.evalToInt()!=this.left.evalToFloat() || this.right.evalToInt()!=this.right.evalToFloat()){
			throw new NotAnInteger("Multiplication will return a fraction");
		}
		return this.left.evalToInt() * this.right.evalToInt(); 
	}
	
	@Override
	public double evalToFloat() {
		return this.left.evalToFloat() * this.right.evalToFloat();
	}
	public static void main(String[] args){
		String s = "1-";
		try {
		Evaluate v = new Evaluate(s,Mode.INTEGER);
			IntVal a = (IntVal)v.eval();
			System.out.println(a.val);
		} catch (NotAnInteger e) { e.printMsg(""); } 
		catch (ParseError e) { System.out.println(e); }
	}
}
