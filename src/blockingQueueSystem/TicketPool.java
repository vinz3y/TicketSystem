package blockingQueueSystem;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class TicketPool {
	private int maxCapacity;
	private final BlockingQueue<String> ticketQueue;

	public TicketPool(int capacity) {
		this.maxCapacity = capacity;
		this.ticketQueue = new ArrayBlockingQueue<>(capacity);
	}

	public boolean addTicket(String ticket) {

		try {
			ticketQueue.put(ticket);	//Blocks if queue is full
			return true;
			
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return false;
	}

	public String buyTicket() {
		try {
            String ticket = ticketQueue.take(); // Blocks if queue is empty
            return ticket;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
	}

	public int getAvailableTickets() {
		return ticketQueue.size();
	}

	public void displayTickets() {
		System.out.println("Available no. of Tickets : " + ticketQueue.size());

	}

}
