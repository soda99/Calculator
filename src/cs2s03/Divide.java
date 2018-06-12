package cs2s03;

class Divide extends Expr { // NOT BinaryOp!
	Expr left;
  	Expr right;
  	Divide(Expr x, Expr y) { left = x; right = y; }
  	public String toString() { 
  		return betweenParens(left) + " / " + betweenParens(right); }
  	public boolean isGround() { return false; }
  	
	@Override
	public int evalToInt() throws NotAnInteger {
		//The below conditional checks if the result is different than float division. If it is, then you know that the resulting quotient involved integer division (answer should be a fraction)
		if (this.left.evalToInt()!=this.left.evalToFloat() || this.right.evalToInt()!=this.right.evalToFloat() || this.left.evalToInt()/this.right.evalToInt()!=this.left.evalToFloat()/this.right.evalToFloat()){
			throw new NotAnInteger("Division will return a fraction");
		}
		return this.left.evalToInt()/this.right.evalToInt();
	}
	
	@Override
	public double evalToFloat() {
		if (this.left.evalToFloat() == 0 && this.right.evalToFloat() == 0){
			throw new ArithmeticException();
		}
		return this.left.evalToFloat()/this.right.evalToFloat();
	}
	
	public static void main(String[] args){
		Integer2 a = new Integer2(4);
		Integer2 b = new Integer2(2);
		Integer2 c = new Integer2(16);
		
		Divide d = new Divide(a,b);
		Divide d2 = new Divide(c,d);
		
		int dval;
		try {
			dval = d2.evalToInt();System.out.println(dval);
		} catch (NotAnInteger e) {System.out.println(e.msg);}
	}
}
