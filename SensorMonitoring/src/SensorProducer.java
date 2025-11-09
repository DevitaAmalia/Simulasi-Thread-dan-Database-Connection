import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SensorProducer implements Runnable {
    private final String source;
    private final BlockingQueue<SensorReading> queue;
    private final long intervalMs;
    private final Random random = new Random();
    private volatile boolean running = true;

    public SensorProducer(String source, BlockingQueue<SensorReading> queue, long intervalMs) {
        this.source = source;
        this.queue = queue;
        this.intervalMs = intervalMs;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                double value = 20 + random.nextGaussian() * 2; 
                queue.put(new SensorReading(source, value, System.currentTimeMillis()));
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

