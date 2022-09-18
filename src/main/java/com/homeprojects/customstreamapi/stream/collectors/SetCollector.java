package com.homeprojects.customstreamapi.stream.collectors;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetCollector<T> implements Collector<Set<T>, T, Set<T>> {

    @Override
    public Supplier<Set<T>> initializer() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return Set::add;
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        return set -> set;
    }
}
