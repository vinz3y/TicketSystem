package main;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int threadPoolCapacity = 10;
        int numProducers = 3;
        int numConsumers = 3;
        int producerRate = 1; // no delay for benchmark
        int consumerRate = 1;
        int durationSeconds = 10;

        System.out.println("=== Starting Benchmarks ===\n");

        runBenchmark("Synchronized", new synchronizedSystem.SimulationManager(threadPoolCapacity),
                numProducers, numConsumers, producerRate, consumerRate, durationSeconds);

        runBenchmark("ReentrantLock", new ReentrantLockedSystem.SimulationManager(threadPoolCapacity),
                numProducers, numConsumers, producerRate, consumerRate, durationSeconds);

        runBenchmark("BlockingQueue", new blockingQueueSystem.SimulationManager(threadPoolCapacity),
                numProducers, numConsumers, producerRate, consumerRate, durationSeconds);
    }

    private static void runBenchmark(String name, Object simManager,
                                     int numProducers, int numConsumers,
                                     int producerRate, int consumerRate, int durationSeconds) {

        System.out.println("Running benchmark: " + name);

        Thread simulationThread = new Thread(() -> {
            if (simManager instanceof synchronizedSystem.SimulationManager) {
                ((synchronizedSystem.SimulationManager) simManager).startSimulation(numProducers, numConsumers, producerRate, consumerRate);
            } else if (simManager instanceof ReentrantLockedSystem.SimulationManager) {
                ((ReentrantLockedSystem.SimulationManager) simManager).startSimulation(numProducers, numConsumers, producerRate, consumerRate);
            } else if (simManager instanceof blockingQueueSystem.SimulationManager) {
                ((blockingQueueSystem.SimulationManager) simManager).startSimulation(numProducers, numConsumers, producerRate, consumerRate);
            }
        });

        simulationThread.start();

        try {
            Thread.sleep(durationSeconds * 1000L); // Benchmark for 'durationSeconds'
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop simulation
        if (simManager instanceof synchronizedSystem.SimulationManager) {
            synchronizedSystem.SimulationManager sm = (synchronizedSystem.SimulationManager) simManager;
            sm.stopSimulation();
            System.out.println("Produced: " + sm.getTicketPool().totalTicketsCreated());
            System.out.println("Consumed: " + sm.getTicketPool().totalTicketsConsumed());
        } else if (simManager instanceof ReentrantLockedSystem.SimulationManager) {
            ReentrantLockedSystem.SimulationManager sm = (ReentrantLockedSystem.SimulationManager) simManager;
            sm.stopSimulation();
            System.out.println("Produced: " + sm.getTicketPool().totalTicketsCreated());
            System.out.println("Consumed: " + sm.getTicketPool().totalTicketsConsumed());
        } else if (simManager instanceof blockingQueueSystem.SimulationManager) {
            blockingQueueSystem.SimulationManager sm = (blockingQueueSystem.SimulationManager) simManager;
            sm.stopSimulation();
            System.out.println("Produced: " + sm.getTicketPool().totalTicketsCreated());
            System.out.println("Consumed: " + sm.getTicketPool().totalTicketsConsumed());
        }

        System.out.println("------------------------------\n");
    }
}
