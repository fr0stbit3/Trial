

public class Chess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewGame1 g = new NewGame1();
		Runtime runtime = Runtime.getRuntime(); 
		System.out.println("free memory: " + runtime.freeMemory() / 1024); 
		System.out.println("allocated memory: " +( runtime.totalMemory() - runtime.freeMemory())/ 1024 + " Kb");  
		System.out.println("max memory: " + runtime.maxMemory() / 1024 + "\n"); 
		System.out.println("Game Starting...");
		boolean check = g.input();
		System.out.println("GG");
	}

}
