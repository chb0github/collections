package org.bongiorno.misc.collections;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toCollection;

/**
 * @author chribong
 */
public class ImprovedStream<T> implements Stream<T>{

    private Stream<T> delegate;

    public ImprovedStream(Stream<T> delegate) {
        this.delegate = delegate;
    }

    public static <T> ImprovedStream<T> improve(Stream<T> inferiorStream) {
        return new ImprovedStream<>(inferiorStream);
    }

    public ImprovedList<T> toList() {
        return delegate.collect(Collectors.toCollection(ImprovedList::new));
    }
    public ImprovedSet<T> toSet() {
        return delegate.collect(Collectors.toCollection(ImprovedSet::new));
    }
    public <R> ImprovedStream<R> map(Function<? super T, ? extends R> mapper) {
        return new ImprovedStream<>(delegate.map(mapper));
    }

    public <K> Map<K,T> toMap(Function<? super T, ? extends K> classifier) {
        return delegate.collect(Collectors.toMap(classifier,Function.identity()));
    }



    @SafeVarargs
    public static <T> ImprovedStream<T> of(T ... things) {
        return new ImprovedStream<>(Stream.of(things));
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return delegate.allMatch(predicate);
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return delegate.anyMatch(predicate);
    }

    public static <T1> Builder<T1> builder() {
        return Stream.builder();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return delegate.collect(collector);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return delegate.collect(supplier, accumulator, combiner);
    }

    public static <T1> Stream<T1> concat(Stream<? extends T1> a, Stream<? extends T1> b) {
        return Stream.concat(a, b);
    }

    @Override
    public long count() {
        return delegate.count();
    }

    @Override
    public Stream<T> distinct() {
        return delegate.distinct();
    }

    public static <T1> Stream<T1> empty() {
        return Stream.empty();
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return delegate.filter(predicate);
    }

    @Override
    public Optional<T> findAny() {
        return delegate.findAny();
    }

    @Override
    public Optional<T> findFirst() {
        return delegate.findFirst();
    }

    public <O> ImprovedCollection<O> transform(Function<? super T, ? extends O> f) {
        return delegate.map(f).collect(toCollection(ImprovedList::new));
    }

    @Override
    public <R> ImprovedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new ImprovedStream<>(delegate.flatMap(mapper));
    }

    public <K> Map<K, ImprovedList<T>> groupingBy(Function<? super T, ? extends K> classifier) {
        return delegate.collect(Collectors.groupingBy(classifier,toCollection(ImprovedList::new)));
    }

    public ImprovedList<T> collect() {
        return delegate.collect(toCollection(ImprovedList::new));
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return delegate.flatMapToDouble(mapper);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return delegate.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return delegate.flatMapToLong(mapper);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        delegate.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        delegate.forEachOrdered(action);
    }

    public static <T1> Stream<T1> generate(Supplier<T1> s) {
        return Stream.generate(s);
    }

    @Override
    public boolean isParallel() {
        return delegate.isParallel();
    }

    public static <T1> Stream<T1> iterate(T1 seed, UnaryOperator<T1> f) {
        return Stream.iterate(seed, f);
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return delegate.limit(maxSize);
    }


    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return delegate.mapToDouble(mapper);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return delegate.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return delegate.mapToLong(mapper);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return delegate.max(comparator);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return delegate.min(comparator);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return delegate.noneMatch(predicate);
    }

    public static <T1> Stream<T1> of(T1 t1) {
        return Stream.of(t1);
    }



    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return delegate.onClose(closeHandler);
    }

    @Override
    public Stream<T> parallel() {
        return delegate.parallel();
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return delegate.peek(action);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return delegate.reduce(accumulator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return delegate.reduce(identity, accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return delegate.reduce(identity, accumulator, combiner);
    }

    @Override
    public Stream<T> sequential() {
        return delegate.sequential();
    }

    @Override
    public Stream<T> skip(long n) {
        return delegate.skip(n);
    }

    @Override
    public Stream<T> sorted() {
        return delegate.sorted();
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return delegate.sorted(comparator);
    }

    @Override
    public Spliterator<T> spliterator() {
        return delegate.spliterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return delegate.toArray(generator);
    }

    @Override
    public Stream<T> unordered() {
        return delegate.unordered();
    }


}
