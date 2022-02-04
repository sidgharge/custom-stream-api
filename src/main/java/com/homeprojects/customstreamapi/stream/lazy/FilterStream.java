package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterStream<T> extends AbstractLazyStream<T> implements Stream<T> {

    private final Stream<T> prevStream;

    private final Predicate<T> predicate;

    public FilterStream(Stream<T> prevStream, Predicate<T> predicate) {
        this.prevStream = prevStream;
        this.predicate = predicate;
    }

//    @Override
//    public void foreach(Consumer<T> consumer) {
//        prevStream.foreach(t -> consumer(consumer, t));
//    }

    private void consumer(Consumer<T> consumer, T t) {
        if(predicate.test(t)) {
            consumer.accept(t);
        }
    }

    @Override
    public Iterator<T> iterate() {
        return new Iterator<>() {

            private Iterator<T> prevIterator = prevStream.iterate();

            private T next = null;

            @Override
            public boolean hasNext() {
                while (prevIterator.hasNext()) {
                    T t = prevIterator.next();
                    if(predicate.test(t)) {
                        next = t;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                return next;
            }
        };
    }
}
