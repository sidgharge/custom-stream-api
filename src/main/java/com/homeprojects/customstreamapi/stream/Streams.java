package com.homeprojects.customstreamapi.stream;

import com.homeprojects.customstreamapi.stream.lazy.LazyStream;

import java.util.List;

public class Streams {

    public static <T> Stream<T> of(List<T> items) {
        return new LazyStream<>(items);
    }
}
