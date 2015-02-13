package org.bongiorno.misc.collections;

import org.bongiorno.misc.utils.QuickCollection;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toCollection;

/**
 * @author chribong
 */
public class ImprovedList<T> extends QuickCollection<T> implements List<T>{

    public ImprovedList() {
        super(new ArrayList<>());
    }

    public ImprovedList(List<T> interiorDelegate) {
        super(interiorDelegate);
    }

    public static <T> ImprovedList<T> improve(List<T> inferiorList) {
        return new ImprovedList<>(inferiorList);
    }

    @SafeVarargs
    public static <T> ImprovedList<T> of(T ... stuff) {
        return new ImprovedList<>(new ArrayList<>(Arrays.asList(stuff)));
    }
    public <O> ImprovedList<O> transform(Function<? super T, ? extends O> f) {
        return this.parallelStream().map(f).collect(toCollection(ImprovedList::new));
    }

    @Override
    public void add(int index, T element) {
        ((List<T>)super.delegate).add(index,element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return ((List<T>)super.delegate).addAll(index,c);
    }

    @Override
    public T get(int index) {
        return ((List<T>)super.delegate).get(index);
    }

    @Override
    public T set(int index, T element) {
        return ((List<T>)super.delegate).set(index,element);
    }

    @Override
    public T remove(int index) {
        return ((List<T>)super.delegate).remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return ((List<T>)super.delegate).indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((List<T>)super.delegate).lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return ((List<T>)super.delegate).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return ((List<T>)super.delegate).listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return ((List<T>)super.delegate).subList(fromIndex,toIndex);
    }
}
