package ReentrantLockedSystem;

public class Producer implements Runnable{
	
	private final TicketPool ticketPool;
    private volatile boolean running = true;
    private final int producerNo;
    private final int cooldownTime;

    
    public Producer(int num, TicketPool ticketpool,int cooldownTime ) {
    	this.ticketPool = ticketpool;
    	this.producerNo = num;
    	this.cooldownTime = cooldownTime;
    }
	
	@Override
	public void run() {
		while(running) {
			long ticketNo = System.currentTimeMillis();
			boolean ticketAdded = ticketPool.addTicket("Ticket#"+ ticketNo);
			if (ticketAdded) {
				System.out.println("Producer "+ producerNo + " addded Ticket : Ticket#"+ticketNo);
			}
			sleep(cooldownTime*1000);
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
