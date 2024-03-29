package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;
import com.homeprojects.customstreamapi.stream.collectors.Collector;
import com.homeprojects.customstreamapi.stream.collectors.ListCollector;
import com.homeprojects.customstreamapi.stream.collectors.SetCollector;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.*;

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
    public <R> Stream<R> flatMap(Function<T, Stream<R>> function) {
        return new FlatMapStream<>(this, function);
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
    public void forEach(Consumer<T> consumer) {
        iterate().forEachRemaining(consumer);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        T result = null;
        Iterator<T> iterator = iterate();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if(result == null) {
                result = next;
            } else {
                result = accumulator.apply(result, next);
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public <R> R reduce(R identity, BiFunction<R, T, R> accumulator) {
        R result = identity;
        Iterator<T> iterator = iterate();
        while (iterator.hasNext()) {
            T next = iterator.next();
            result = accumulator.apply(result, next);
        }
        return result;
    }

    @Override
    public List<T> toList() {
        return collect(new ListCollector<>());
    }

    @Override
    public Set<T> toSet() {
        return collect(new SetCollector<>());
    }

    @Override
    public <C, R> R collect(Collector<C, T, R> collector) {
        C collection = collector.initializer().get();
        Iterator<T> iterator = iterate();
        BiConsumer<C, T> accumulator = collector.accumulator();
        iterator.forEachRemaining(item -> accumulator.accept(collection, item));

        Function<C, R> finisher = collector.finisher();
        return finisher.apply(collection);
    }
}
