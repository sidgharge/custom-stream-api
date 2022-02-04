package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;
import java.util.function.Function;

public class FlatMapStream<I, T> extends AbstractLazyStream<T> {

    private final Stream<I> prevStream;

    private final Function<I, Stream<T>> flatMapFn;

    protected FlatMapStream(Stream<I> prevStream, Function<I, Stream<T>> flatMapFn) {
        this.prevStream = prevStream;
        this.flatMapFn = flatMapFn;
    }

    @Override
    public Iterator<T> iterate() {
        return new Iterator<>() {

            private final Iterator<I> parentIterator = prevStream.iterate();
            private Iterator<T> currIterator = null;

            @Override
            public boolean hasNext() {
                boolean currentIteratorNeedToInitialize = currIterator == null || !currIterator.hasNext();
                if(currentIteratorNeedToInitialize) {
                    if(!parentIterator.hasNext()) {
                        return false;
                    }
                    I input = parentIterator.next();
                    currIterator = flatMapFn.apply(input).iterate();
                    return currIterator.hasNext();
                } else {
                    return true;
                }
            }

            @Override
            public T next() {
                return currIterator.next();
            }
        };
    }
}
