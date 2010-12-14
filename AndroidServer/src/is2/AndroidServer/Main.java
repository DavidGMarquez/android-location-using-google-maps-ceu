package is2.AndroidServer;



public class Main {

	public static void main(String[] args) {
		System.out.println("H3llo");
		Thread fst = new Thread(new ServerThread());
		fst.start();
	}
	
}
