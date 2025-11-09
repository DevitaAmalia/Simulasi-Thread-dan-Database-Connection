import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BlockingQueue<SensorReading> queue = new LinkedBlockingQueue<>();

        SensorProducer sensor1 = new SensorProducer("TEMP-1", queue, 2000);
        SensorProducer sensor2 = new SensorProducer("TEMP-2", queue, 3000);
        DbWriter writer = new DbWriter(queue);

        Thread t1 = new Thread(sensor1);
        Thread t2 = new Thread(sensor2);
        Thread tWriter = new Thread(writer);

        t1.start();
        t2.start();
        tWriter.start();

        System.out.println("System running for 60 seconds...");

        Thread.sleep(60000);

        sensor1.stop();
        sensor2.stop();
        writer.stop();

        t1.join();
        t2.join();
        tWriter.join();

        System.out.println("Program selesai. Data sudah masuk DB.");
    }
}

