package synchronizedSystem;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Thread Pool capacity: ");
		int threadpoolCapacity = scanner.nextInt();
		
		synchronizedSystem.SimulationManager simulationManager = new synchronizedSystem.SimulationManager(threadpoolCapacity);
		
		scanner.close();
		
		
//		simulationManager.startSimulation();
		
	}

}
