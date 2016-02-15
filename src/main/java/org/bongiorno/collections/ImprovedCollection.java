package org.bongiorno.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * @author chribong
 */
public interface ImprovedCollection<T> extends Collection<T>, Serializable {

    public default <K> Map<K, ImprovedList<T>> groupingBy(Function<? super T, ? extends K> classifier) {

        return this.stream().collect(Collectors.groupingBy(classifier, toCollection(ImprovedList::new)));
    }

    public default <R> ImprovedStream<R> map(Function<? super T, ? extends R> mapper) {
        return ImprovedStream.improve(this.stream().map(mapper));
    }

    public default <R> ImprovedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new ImprovedStream<>(this.stream().flatMap(mapper));
    }

    public static <T> ImprovedCollection<T> of(Collection<T> sadCollection) {
        return new QuickCollection<>(sadCollection);
    }

    public static <T> ImprovedCollection<T> improve(Collection<T> sadCollection) {
        return new QuickCollection<>(sadCollection);
    }

    public default <K> ImprovedMap<K, T> indexBy(Function<? super T, ? extends K> indexer) {
        return this.stream().collect(Collectors.toMap(indexer, Function.identity(), noDupKeys(), ImprovedMap::new));
    }

    public default <K, U> ImprovedMap<K, U> toMap(Function<? super T, ? extends K> fOfK, Function<? super T, ? extends U> fOfV) {
        return this.stream().collect(Collectors.toMap(fOfK, fOfV, noDupKeys(), ImprovedMap::new));
    }


    public default <O> ImprovedCollection<O> transform(Function<? super T, ? extends O> f, Supplier<ImprovedCollection<O>> s) {
        return this.map(f).collect(toCollection(s));
    }

    public default ImprovedStream<T> filter(Predicate<T> p) {
        return new ImprovedStream<>(this.stream().filter(p));
    }

    public default T findAny(Predicate<T> p){
        return this.filter(p)
                .findAny()
                .orElse(null);
    }

    public default boolean anyMatch(Predicate<? super T> predicate) {
        return this.stream().anyMatch(predicate);
    }

    public static <T> BinaryOperator<T> noDupKeys() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

}
