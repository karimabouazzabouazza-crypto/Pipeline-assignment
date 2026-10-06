package pipeline;

public class ConsoleSink implements Sink<LogRecord> {
    @Override
    public void consume(LogRecord record) {
        if (record != null) {
            System.out.println(record);
        }
    }
}