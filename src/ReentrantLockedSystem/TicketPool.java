package ReentrantLockedSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TicketPool {
	private final List<String> tickets = new LinkedList<>();
	private int maxCapacity;
	private int ticketsCreated = 0;
	private int ticketsConsumed = 0;
	private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	public TicketPool(int capacity) {
		this.maxCapacity = capacity;
	}

	public boolean addTicket(String ticket) {
		rwLock.writeLock().lock();
		try {
			if (tickets.size() >= maxCapacity) {
				return false;
			} else {
				tickets.add(ticket);
				return true;
			}
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public String buyTicket() {
		rwLock.writeLock().lock();
		try {
			if (tickets.isEmpty()) {
				return null;
			} else {
				return tickets.remove(0);
			}
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public int getAvailableTickets() {
		rwLock.readLock().lock();
		try {
			return tickets.size();
			
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void displayTickets() {
		rwLock.readLock().lock();
		try {
			System.out.println("Available no. of Tickets : " + tickets.size());
		} finally {
			rwLock.readLock().unlock();
		}
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
