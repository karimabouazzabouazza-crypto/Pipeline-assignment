package pipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLinesSource implements Source<String> {
    private final Path filePath;

    public FileLinesSource(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void produce(Emitter<String> out) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.emit(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}