package pipeline;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path filePath = Paths.get("data/access-small.log");

        FileLinesSource source = new FileLinesSource(filePath);
        ConsoleSink sink = new ConsoleSink();

        Pipeline<String, String> pipeline = new Pipeline<>(source, sink);

        try {
            pipeline.run();
            System.out.println("Pipeline başarıyla çalıştı ve tamamlandı!");
        } catch (StageException e) {
            e.printStackTrace();
        }
    }
}