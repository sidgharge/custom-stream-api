package com.homeprojects.customstreamapi.stream.lazy;

import com.homeprojects.customstreamapi.stream.Stream;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

public class LazyStream<T> extends AbstractLazyStream<T> implements Stream<T> {

    private final Collection<T> collection;

    public LazyStream(Collection<T> collection) {
        this.collection = collection;
    }

//    @Override
//    public void foreach(Consumer<T> consumer) {
//        collection.forEach(consumer);
//    }

    @Override
    public Iterator<T> iterate() {
        return collection.iterator();
    }

    @Override
    public Optional<T> findFirst() {
        Iterator<T> iterator = iterate();
        while (iterator.hasNext()) {
            return Optional.ofNullable(iterator.next());
        }
        return Optional.empty();
    }
}
