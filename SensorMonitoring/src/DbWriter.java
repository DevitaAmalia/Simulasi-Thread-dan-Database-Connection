import java.sql.*;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;

public class DbWriter implements Runnable {
    private final BlockingQueue<SensorReading> queue;
    private volatile boolean running = true;

    private static final String SQL =
        "INSERT INTO sensor_logs (source, value, created_at) VALUES (?, ?, ?)";

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);

    public DbWriter(BlockingQueue<SensorReading> queue) {
        this.queue = queue;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            while (running || !queue.isEmpty()) {

                SensorReading s = queue.take();

                String timeString = FMT.format(Instant.ofEpochMilli(s.timeMillis));

                ps.setString(1, s.source);
                ps.setDouble(2, s.value);
                ps.setString(3, timeString);
                ps.executeUpdate();

                System.out.printf(
                    "Source: %s | Value: %.2f | Time: %s%n",
                    s.source, s.value, timeString
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
