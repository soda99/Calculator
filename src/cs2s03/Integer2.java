package cs2s03;

class Integer2 extends Expr {
	int i;

	Integer2(int x) { i = x; }
	public String toString() { return Integer.toString(i) ; }
	public boolean isGround() { return true; }
  
	@Override
	public int evalToInt() throws NotAnInteger {
		return this.i;	
	}
	
	@Override
	public double evalToFloat() {
		return 1.0*this.i;
	}
}
