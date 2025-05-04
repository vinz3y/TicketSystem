package ReentrantLockedSystem;

public class Writer implements Runnable{
	
	private TicketPool ticketPool;
    private volatile boolean running = true;
    
    public Writer(TicketPool pool) {
        this.ticketPool = pool;
    }
	
	@Override
	public void run() {
		while(running) {
			boolean ticketAdded = ticketPool.addTicket("SpecialTicket#"+System.currentTimeMillis() );
			if(ticketAdded) {
				System.out.println("Writer added Ticket");
				sleep(5000); //Turns inactive after adding the ticket to the pool
			}
		}
		
	
	}
	
	private void sleep(long sleeptime) {
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
