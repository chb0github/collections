package org.bongiorno.misc.collections;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * @author chribong
 */
public class ImprovedSet<T> extends QuickCollection<T> implements Set<T> {

    public ImprovedSet() {
        super(new HashSet<>());
    }

    public ImprovedSet(Collection<T> sadCollection) {
        super(new HashSet<T>(sadCollection));
    }

    public ImprovedSet(Set<T> sadDelegate) {
        super(sadDelegate);
    }

    public static <T> ImprovedSet<T> of(Set<T> sadSet) {
        return new ImprovedSet<T>(sadSet);
    }

    @SafeVarargs
    public static <T> ImprovedSet<T> of(T ... stuff) {
        return new ImprovedSet<T>(new HashSet<>(Arrays.asList(stuff)));
    }
    public <O> ImprovedSet<O> transform(Function<? super T, ? extends O> f) {
        return this.parallelStream().map(f).collect(toCollection(ImprovedSet::new));
    }

    public ImprovedSetStream<T> filter(Predicate<T> p) {
        return new ImprovedSetStream<>( super.filter(p));
    }


    public static class ImprovedSetStream<T> extends ImprovedStream<T> {
        public ImprovedSetStream(ImprovedStream<T> delegate) {
            super(delegate);
        }

        public ImprovedSet<T> collect() {
            return delegate.collect(Collectors.toCollection(ImprovedSet::new));
        }
    }
}
