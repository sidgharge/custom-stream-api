package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractLazyStream<T> implements Stream<T> {

    @Override
    public Stream<T> filter(Predicate<T> predicate) {
        return new FilterStream<>(this, predicate);
    }

    @Override
    public <R> Stream<R> map(Function<T, R> function) {
        return new MapStream<>(this, function);
    }

    @Override
    public Stream<T> peek(Consumer<T> consumer) {
        return new PeekStream<>(this, consumer);
    }

    @Override
    public Stream<T> skip(long n) {
        return new SkipNStream<>(this, n);
    }

    @Override
    public Optional<T> findFirst() {
        Iterator<T> iterator = iterate();
        if (iterator.hasNext()) {
            return Optional.ofNullable(iterator.next());
        }
        return Optional.empty();
    }

    @Override
    public void foreach(Consumer<T> consumer) {
        iterate().forEachRemaining(consumer);
    }
}
