package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MapStream<S, T> extends AbstractLazyStream<T> implements Stream<T> {

    private final Stream<S> prevStream;

    private final Function<S, T> function;

    public MapStream(Stream<S> prevStream, Function<S, T> function) {
        this.prevStream = prevStream;
        this.function = function;
    }

    @Override
    public Iterator<T> iterate() {
        return new Iterator<>() {

            private final Iterator<S> prevStreamIterator = prevStream.iterate();

            @Override
            public boolean hasNext() {
                return prevStreamIterator.hasNext();
            }

            @Override
            public T next() {
                return function.apply(prevStreamIterator.next());
            }
        };
//        return new StreamIterator<>(prevStream);
    }
}
