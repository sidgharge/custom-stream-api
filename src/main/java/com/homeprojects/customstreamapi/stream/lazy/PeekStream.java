package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;
import java.util.function.Consumer;

public class PeekStream<T> extends AbstractLazyStream<T> {

    private final Stream<T> prevStream;

    private final Consumer<T> peekConsumer;

    public PeekStream(Stream<T> prevStream, Consumer<T> peekConsumer) {
        this.prevStream = prevStream;
        this.peekConsumer = peekConsumer;
    }

//    @Override
//    public void foreach(Consumer<T> consumer) {
//        prevStream.foreach(t -> {
//            peekConsumer.accept(t);
//            consumer.accept(t);
//        });
//    }

    @Override
    public Iterator<T> iterate() {
        return new Iterator<>() {
            private final Iterator<T> prevIterator = prevStream.iterate();
            @Override
            public boolean hasNext() {
                return prevIterator.hasNext();
            }

            @Override
            public T next() {
                T next = prevIterator.next();
                peekConsumer.accept(next);
                return next;
            }
        };
    }
}
