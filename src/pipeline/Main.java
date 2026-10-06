package pipeline;

import java.nio.file.Paths;
import pipeline.ConsoleSink;
import pipeline.FileLinesSource;
import pipeline.LogRecord;
import pipeline.Pipeline;
import pipeline.ParserStage;

public class Main {
    public static void main(String[] args) {
        String filePath = (args.length > 0) ? args[0] : "data/access-small.log";

        ParserStage parser = new ParserStage();
        Pipeline<String, LogRecord> pipeline = new Pipeline<>(
                new FileLinesSource(Paths.get(filePath)),
                new ConsoleSink()
        );
        pipeline.addStage(parser);
        pipeline.run();

        System.out.println("Malformed lines skipped: " + parser.getErrorCount());
    }
}