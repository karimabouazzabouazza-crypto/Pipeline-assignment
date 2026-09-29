package pipeline;

import java.util.ArrayList;
import java.util.List;

public class Pipeline<I, O> {
    private final Source<I> source;
    private final List<Stage<?, ?>> stages = new ArrayList<>();
    private final Sink<O> sink;

    public Pipeline(Source<I> source, Sink<O> sink) {
        this.source = source;
        this.sink = sink;
    }

    public void addStage(Stage<?, ?> stage) {
        stages.add(stage);
    }

    @SuppressWarnings("unchecked")
    public void run() throws StageException {
        source.produce(item -> {
            sink.consume((O) item);
        });
    }
}