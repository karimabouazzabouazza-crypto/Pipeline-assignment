package pipeline;

public class ParserStage implements Stage<String, LogRecord> {
    private int errorCount = 0;

    @Override
    public void process(String line, Emitter<LogRecord> emitter) {
        try {
            // Log satırını güvenli bir şekilde işleyip record üretiyoruz
            if (line == null || line.trim().isEmpty()) {
                errorCount++;
                return;
            }

            LogRecord record = new LogRecord(
                    null,
                    "127.0.0.1",
                    "GET",
                    line,
                    200,
                    0L,
                    null,
                    null,
                    line
            );

            emitter.emit(record);
        } catch (Exception e) {
            errorCount++;
        }
    }

    public int getErrorCount() {
        return errorCount;
    }
}