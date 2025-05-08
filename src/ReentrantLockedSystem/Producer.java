package ReentrantLockedSystem;
import java.util.Random;


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
			Random rand = new Random();
			boolean ticketAdded = ticketPool.addTicket("Ticket#"+ Integer.toString(producerNo)+ticketNo+rand.nextInt(1000));
			if (ticketAdded) {
				System.out.println("Producer "+ producerNo + " addded Ticket : Ticket#"+Integer.toString(producerNo)+ticketNo+rand.nextInt(1000));
				this.ticketPool.ticketWasCreated();
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
