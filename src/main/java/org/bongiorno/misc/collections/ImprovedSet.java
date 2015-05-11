package org.bongiorno.misc.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

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
        return this.stream().map(f).collect(toCollection(ImprovedSet::new));
    }

    public ImprovedSetStream<T> filter(Predicate<T> p) {
        return new ImprovedSetStream<>( super.filter(p));
    }


    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    public static class ImprovedSetStream<T> extends ImprovedStream<T> {
        private ImprovedSetStream(ImprovedStream<T> delegate) {
            super(delegate);
        }

        public <O> ImprovedSet<O> transform(Function<? super T, ? extends O> f) {
            return delegate.map(f).collect(toCollection(ImprovedSet::new));
        }


        public  ImprovedSet<T> collect() {
            return delegate.collect(toCollection(ImprovedSet::new));
        }

    }
}
