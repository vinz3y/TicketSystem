package synchronizedSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationManager {
	
	private final TicketPool ticketPool ;
	private final List<Thread> threads = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	
	
	public SimulationManager(int capacity) {
		this.ticketPool = new TicketPool(capacity);
	}
	
	public void startSimulation(int noOfProducers, int noOfComsumers, int producerRate, int consumerRate ) {
		
		//Creating and defining roles in the system
		
		for(int i=0; i<noOfProducers; i++) {
			Producer producer = new Producer(i+1, ticketPool, producerRate);
			threads.add(new Thread(producer));	//creating producer threads dynamically
		}
		
		
		for(int i=0; i<noOfComsumers; i++) {
			Consumer consumer = new Consumer(i+1, ticketPool, consumerRate);
			threads.add(new Thread(consumer));	//creating consumer threads dynamically
		}
		
		scanner.close();
		
		//Creating reader writer objects
		
        Reader reader = new Reader(ticketPool);
        Writer writer = new Writer(ticketPool); 
        
        //Creating threads for each role
        
        threads.add(new Thread(reader));
        threads.add(new Thread(writer));
        
		
		System.out.println("\n------ STARTING SIMULATION ------\n");
        
        //Starting each thread - activating the roles
        for (Thread thread : threads) {
            thread.start();
        }
		
	}
	
	public void stopSimulation() {
		for (Thread thread:threads) {
			thread.interrupt();
		}
	}
}
