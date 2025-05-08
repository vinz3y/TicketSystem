package synchronizedSystem;
import java.util.LinkedList;
import java.util.List;


public class TicketPool {
	private final List<String> tickets = new LinkedList<>();
	private int maxCapacity;
	private int ticketsCreated = 0;
	private int ticketsConsumed = 0;
	
	public TicketPool (int capacity ) {
		this.maxCapacity = capacity;
	}
	
	public synchronized boolean addTicket(String ticket) {
		if(tickets.size() >= maxCapacity) {
			return false;
		}
		else {
			tickets.add(ticket);
			return true;
		}
	}
	
	public synchronized String buyTicket () {
		if (tickets.isEmpty()) {
			return null;
		}
		else {
			return tickets.remove(0);
		}
	}
	
	public synchronized int getAvailableTickets() {
		return tickets.size();
	}
	
	public synchronized void displayTickets () {
		System.out.println("Available no. of TIckets : " + tickets.size() );
	}
	
	public void ticketWasCreated() {
		this.ticketsCreated++;
	}
	
	public void ticketWasConsumed() {
		this.ticketsConsumed++;
	}
	
	public int totalTicketsCreated() {
		return ticketsCreated;
	}
	
	public int totalTicketsConsumed() {
		return ticketsConsumed;
	}

}
