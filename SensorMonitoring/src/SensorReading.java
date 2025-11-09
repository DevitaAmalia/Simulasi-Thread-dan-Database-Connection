public class SensorReading {
    public final String source;
    public final double value;
    public final long timeMillis;

    public SensorReading(String source, double value, long timeMillis) {
        this.source = source;
        this.value = value;
        this.timeMillis = timeMillis;
    }
}
