package pipeline;

import java.util.ArrayList;
import java.util.List;

public class ParserStageTest {

    private static class CollectingEmitter<T> implements Emitter<T> {
        private final List<T> emittedItems = new ArrayList<>();

        @Override
        public void emit(T item) {
            emittedItems.add(item);
        }

        public List<T> getEmittedItems() {
            return emittedItems;
        }
    }

    public static void main(String[] args) {
        System.out.println("Testler baslatiliyor...");

        ParserStage parser = new ParserStage();
        CollectingEmitter<LogRecord> emitter = new CollectingEmitter<>();

        try {
            // Test 1: Gecerli Log Satiri
            String validLine = "127.0.0.1 - - [10/Oct/2026:13:55:36 +0300] \"GET /index.html HTTP/1.1\" 200 2326";
            parser.process(validLine, emitter);

            if (emitter.getEmittedItems().size() == 1) {
                System.out.println("Test 1 (Gecerli Log) Basarili!");
            } else {
                System.out.println("Test 1 Basarisiz!");
            }

            // Test 2: Gecersiz / Bos Satir Hata Sayaci Testi
            parser.process("hatali_log_satiri", emitter);
            if (parser.getErrorCount() == 1) {
                System.out.println("Test 2 (Hata Sayaci) Basarili!");
            } else {
                System.out.println("Test 2 Basarisiz!");
            }

            System.out.println("Tum testler basariyla tamamlandi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}