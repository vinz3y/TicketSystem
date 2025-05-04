package main;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Thread Pool capacity: ");
		int threadpoolCapacity = scanner.nextInt();
		
		
		synchronizedSystem.SimulationManager simulationManager1 = new synchronizedSystem.SimulationManager(threadpoolCapacity);
		ReentrantLockedSystem.SimulationManager simulationManager2 = new ReentrantLockedSystem.SimulationManager(threadpoolCapacity);
		blockingQueueSystem.SimulationManager simulationManager3 = new blockingQueueSystem.SimulationManager(threadpoolCapacity);
		
		System.out.print("Enter the no. of Producers : ");
		int noOfProducers = scanner.nextInt();
		
		System.out.print("Enter the no. of Consumers : ");
		int noOfComsumers = scanner.nextInt();
		
		System.out.print("Enter producer rate(cooldown time) in seconds : ");
		int producerRate = scanner.nextInt();
		
		System.out.print("Enter consumers rate(cooldown time) in seconds : ");
		int consumerRate = scanner.nextInt();
		
		
		System.out.println("------ SIMULATION MODE ------");
		System.out.println("1 - Intrinsic Locks (Synchronized)");
		System.out.println("2 - ReentrantReadWriteLock/ReentrantLock");
		System.out.println("3 - Blocking Queue\n");
		System.out.print("Enter the index of the simulation mode you want to select : ");
		
		int simulationMode = scanner.nextInt();
		
		scanner.close();
		
		switch(simulationMode) {
			case 1:
			{
				simulationManager1.startSimulation(noOfProducers,noOfComsumers,producerRate, consumerRate);
			}
			case 2:
			{
				simulationManager2.startSimulation(noOfProducers,noOfComsumers,producerRate, consumerRate);
			}
			case 3:
				simulationManager3.startSimulation(noOfProducers,noOfComsumers,producerRate, consumerRate);
			
		}
		
		
		
	}

}
