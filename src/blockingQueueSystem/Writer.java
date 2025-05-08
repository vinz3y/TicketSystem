package blockingQueueSystem;
import java.util.Random;


public class Writer implements Runnable{
	
	private TicketPool ticketPool;
    
    public Writer(TicketPool pool) {
        this.ticketPool = pool;
    }
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			Random rand = new Random();
			boolean ticketAdded = ticketPool.addTicket("SpecialTicket#"+System.currentTimeMillis()+rand.nextInt(100) );
			if(ticketAdded) {
				System.out.println("Writer added Ticket");
				this.ticketPool.ticketWasCreated();
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

    }

}
