//package com.homeprojects.customstreamapi.stream.eager;
//
//import com.homeprojects.customstreamapi.stream.Stream;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
//public class EagerStream<T> implements Stream<T> {
//
//    private final Collection<T> collection;
//
//    public EagerStream(Collection<T> collection) {
//        this.collection = collection;
//    }
//
//    @Override
//    public <R> Stream<R> map(Function<T, R> function) {
//        List<R> list = new ArrayList<>();
//        for (T t : collection) {
//            list.add(function.apply(t));
//        }
//        return new EagerStream<>(list);
//    }
//
//    @Override
//    public void foreach(Consumer<T> consumer) {
//        collection.forEach(consumer);
//    }
//}
