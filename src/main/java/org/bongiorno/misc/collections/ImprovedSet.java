package org.bongiorno.misc.collections;

import org.bongiorno.misc.utils.QuickCollection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

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

}
