package cs2s03;

@SuppressWarnings("serial")
public class NotAnInteger extends Exception {
	String msg;
	NotAnInteger(String s){
		this.msg = s;
	}
	
	public void printMsg(String location){
		System.out.println(location+": " + this.msg);
	}
}
