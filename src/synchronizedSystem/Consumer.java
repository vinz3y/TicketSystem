package synchronizedSystem;



public class Consumer implements Runnable{
	
	private final TicketPool ticketPool;
    private volatile boolean running = true;
    private final int consumerNo;
    private final int cooldownTime;
    
    public Consumer(int num, TicketPool ticketpool, int cooldownTime) {
    	this.ticketPool = ticketpool;
    	this.consumerNo = num;
    	this.cooldownTime = cooldownTime;
    }
    
	@Override
	public void run() {
		while(running) {
			String ticket = ticketPool.buyTicket();
			if(ticket != null) {
				System.out.println("Consumer "+consumerNo+" bought Ticket : "+ ticket);
				
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
