package com.homeprojects.customstreamapi.stream;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.*;

public interface Stream<T> {

    Stream<T> filter(Predicate<T> predicate);

    <R> Stream<R> map(Function<T, R> function);

    <R> Stream<R> flatMap(Function<T, Stream<R>> function);

    Stream<T> peek(Consumer<T> consumer);

    Stream<T> skip(long n);

    void forEach(Consumer<T> consumer);

    Optional<T> findFirst();

    Iterator<T> iterate();

    Optional<T> reduce(BinaryOperator<T> accumulator);

    <R> R reduce(R identity, BiFunction<R, T, R> accumulator);
}
