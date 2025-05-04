package synchronizedSystem;

public class Reader implements Runnable{
	
	private final TicketPool ticketPool;
    private volatile boolean running = true;
    
    public Reader(TicketPool ticketpool) {
    	this.ticketPool = ticketpool;
    }
	
	@Override
	public void run() {
		while(running) {
			ticketPool.displayTickets();
			sleep(1000);	//Turns inactive after sleeping
		}
	
	}
	
	public void sleep(long sleeptime) {
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop() {
        running = false;
    }
}
	
