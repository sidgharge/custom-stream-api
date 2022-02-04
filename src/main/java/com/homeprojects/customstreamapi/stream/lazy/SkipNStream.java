package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;

public class SkipNStream<T> extends AbstractLazyStream<T> {

    private final Stream<T> prevStream;

    private final long n;

    public SkipNStream(Stream<T> prevStream, long n) {
        this.prevStream = prevStream;
        this.n = n;
    }

    @Override
    public Iterator<T> iterate() {
        return new Iterator<>() {

            private final Iterator<T> prevIterator = prevStream.iterate();

            {
                int i = 0;
                while (i < n && prevIterator.hasNext()) {
                    prevIterator.next();
                    i++;
                }
            }

            @Override
            public boolean hasNext() {
                return prevIterator.hasNext();
            }

            @Override
            public T next() {
                return prevIterator.next();
            }
        };
    }
}
