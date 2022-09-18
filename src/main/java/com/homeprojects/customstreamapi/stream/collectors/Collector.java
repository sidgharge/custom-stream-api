package com.homeprojects.customstreamapi.stream.collectors;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Collector<C, T, R> {

    Supplier<C> initializer();

    BiConsumer<C, T> accumulator();

    Function<C, R> finisher();
}
