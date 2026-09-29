package pipeline;

public interface Sink<I> {
    void consume(I item);

}
