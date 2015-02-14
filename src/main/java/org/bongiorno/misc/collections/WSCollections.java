package org.bongiorno.misc.collections;


import org.bongiorno.misc.collections.QuickCollection;
import org.bongiorno.misc.collections.QuickMap;

import java.util.*;
import java.util.function.Function;

public final class WSCollections {


    public static <K, V> Map<K, V> exceptionOnDuplicateMap(Map<K, V> m) {
        return new ExceptionOnDuplicateKeyMap<K, V>(m);
    }

    public static <K, V> SortedMap<K, V> exceptionOnDuplicateSortedMap(SortedMap<K, V> m) {
        return new ExceptionOnDuplicateKeySortedMap<K, V>(m);
    }

    public static <T> Collection<T> delimitedCollection(Collection<T> c, String delimiter) {
        return new DelimitedCollection<T>(c, delimiter);
    }

    /**
     * Delimits with the system line separator. Handy for debugging
     *
     * @param c   your collection
     * @param <T> the stuff in the collection
     * @return a system dependent newline delimited collection
     */
    public static <T> Collection<T> delimitNewLine(Collection<T> c) {
        return new DelimitedCollection<T>(c, System.lineSeparator());
    }

    /**
     * @param m              the map to delegate to
     * @param postKeyDelim   what you want to come after every key
     * @param postValueDelim what you want to come after every value
     * @param <K>            key
     * @param <V>            value
     * @return a map that behaves as 'm' except has a very nice toString()
     */
    public static <K, V> Map<K, V> delimitedMap(Map<K, V> m, CharSequence postKeyDelim, CharSequence postValueDelim) {
        return new DelimitedMap<K, V>(m, postKeyDelim, postValueDelim);
    }

    /**
     * @param m         the map to delegate to
     * @param preKey    what you want to come before every key
     * @param postKey   what you want to come after every key
     * @param postValue what you want to come after every value
     * @param <K>       key
     * @param <V>       value
     * @return a map that behaves as 'm' except has a very nice toString()
     */
    public static <K, V> Map<K, V> delimitedMap(Map<K, V> m, CharSequence preKey, CharSequence postKey, CharSequence postValue) {
        return new DelimitedMap<K, V>(m, preKey, postKey, postValue);
    }

    /**
     * @param m         the map to delegate to
     * @param preKey    what you want to come before every key
     * @param postKey   what you want to come after every key
     * @param postValue what you want to come after every value
     * @param trim      if, when output is complete, you want the last postValue to be trimmed off
     * @param <K>       key
     * @param <V>       value
     * @return a map that behaves as 'm' except has a very nice toString()
     */
    public static <K, V> Map<K, V> delimitedMap(Map<K, V> m, CharSequence preKey, CharSequence postKey, CharSequence postValue, boolean trim) {
        return new DelimitedMap<K, V>(m, preKey, postKey, postValue, trim);
    }

    public static <T> Set<T> exceptionOnDuplicateSet(Set<T> s) {
        return new ExceptionOnDuplicateSet<T>(s);
    }


    /**
     * Map equivalent of Arrays.asList.  This works just like the way you would declare a hash (map) in Perl
     *
     * @param pairs Keys and values, alternately
     * @param <T>   the element to make pairs from
     * @throws ArrayIndexOutOfBoundsException If an odd number of Objects are passed in
     * @return a new Map instance based on your input.
     */
    public static <T> Map<T, T> asMap(T... pairs) throws ArrayIndexOutOfBoundsException {
        Map<T, T> retVal = new HashMap<T, T>();
        int i = 0;
        while (i < pairs.length) {
            retVal.put(pairs[i++], pairs[i++]);
        }
        return retVal;
    }

    public static <K, V> Map<K, V> putInMap(Iterable<K> things, Function<? super K, V> valueFunction) {
        return putInMap(things, new HashMap<K,V>(), valueFunction);
    }

    public static <K, V> Map<K, V> putInMap(Iterable<K> things, V value) {
        return putInMap(things, new HashMap<>(), value);
    }

    public static <C extends Map<K, V>, K, V> C putInMap(Iterable<K> things, C destinationMap, Function<? super K, V> valueFunction) {
        for (K thing : things) {
            destinationMap.put(thing, valueFunction.apply(thing));
        }
        return destinationMap;
    }

    public static <C extends Map<K, V>, K, V> C putInMap(Iterable<K> things, C destinationMap, V value) {
        return putInMap(things, destinationMap, (Function<K, V>) k -> value);
    }

    /**



     */
    private static class DelimitedCollection<T> extends QuickCollection<T> {
        private String delimiter = ",";

        /**
         * Default delimiter is used: ','
         *
         * @param delegate the collection to delegate calls to
         */
        private DelimitedCollection(Collection<T> delegate) {
            super.delegate = delegate;
        }


        private DelimitedCollection(Collection<T> delegate, String delimiter) {
            super.delegate = delegate;
            this.delimiter = delimiter;
        }


        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (T t : delegate)
                b.append(t).append(delimiter);
            b.setLength(Math.max(0, b.length() - delimiter.length())); // remove the extra delimiter
            return b.toString();
        }
    }


    private static class ExceptionOnDuplicateSet<T> implements Set<T> {

        private Set<T> delegate;

        private ExceptionOnDuplicateSet(Set<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public boolean isEmpty() {
            return delegate.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return delegate.contains(o);
        }

        @Override
        public Iterator<T> iterator() {
            return delegate.iterator();
        }

        @Override
        public Object[] toArray() {
            return delegate.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return delegate.toArray(a);
        }

        @Override
        public boolean add(T t) {
            if (delegate.contains(t))
                throw new IllegalArgumentException("Duplicate entry attempt. " + t + " already contained");
            return delegate.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return delegate.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return delegate.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            boolean retVal = false;

            for (T t : c)
                retVal |= this.add(t);

            return retVal;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return delegate.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return delegate.removeAll(c);
        }

        @Override
        public void clear() {
            delegate.clear();
        }

        @Override
        public String toString() {
            return delegate.toString();
        }

        @Override
        public boolean equals(Object o) {
            return (delegate == o || (delegate != null && delegate.equals(o)));

        }

        @Override
        public int hashCode() {
            return delegate != null ? delegate.hashCode() : 0;
        }
    }

    private static class ExceptionOnDuplicateKeyMap<K, V> extends QuickMap<K, V> {
        private ExceptionOnDuplicateKeyMap(Map<K, V> delegate) {
            super(delegate);
        }

        /**
         * Put a key/value pair into the map, throwing an IllegalArgumentException if the key is already present
         *
         * @param key   the key to attempt to put
         * @param value the value to attempt to put
         * @return always null since no prior key would have existed and thus no value
         */
        public V put(K key, V value) {
            if (delegate.containsKey(key))
                errorMsg(key, value);

            V retVal = delegate.put(key, value);
            if (retVal != null)
                errorMsg(key, value);

            return retVal;
        }

        private V errorMsg(K key, V value) {
            String msg = "Duplicate key " + key + " found.";
            msg += " new val is " + (value == null ? null : value.getClass()) + "(" + value + ")";
            throw new IllegalArgumentException(msg);
        }

        /**
         * @inheritDoc
         */
        public void putAll(Map<? extends K, ? extends V> m) {
            for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
                put(entry.getKey(), entry.getValue());
        }
    }

    private static class DelimitedMap<K, V> extends QuickMap<K, V> {
        private CharSequence preKeyDelim = "";
        private CharSequence postKeyDelim = "";
        private CharSequence postValueDelim = "";
        private boolean trim = false;

        private DelimitedMap(Map<K, V> delegate, CharSequence preKeyDelim, CharSequence postKeyDelim, CharSequence postValueDelim, boolean trim) {
            super(delegate);
            this.delegate = delegate;
            this.preKeyDelim = preKeyDelim;
            this.postKeyDelim = postKeyDelim;
            this.postValueDelim = postValueDelim;
            this.trim = trim;
        }

        private DelimitedMap(Map<K, V> delegate, CharSequence preKeyDelim, CharSequence postKeyDelim, CharSequence postValueDelim) {
            super(delegate);
            this.delegate = delegate;
            this.preKeyDelim = preKeyDelim;
            this.postKeyDelim = postKeyDelim;
            this.postValueDelim = postValueDelim;
        }

        private DelimitedMap(Map<K, V> delegate, CharSequence postKey, CharSequence postValue) {
            super(delegate);
            this.delegate = delegate;
            this.postKeyDelim = postKey;
            this.postValueDelim = postValue;
        }

        public void setTrimming(boolean onOrOff) {
            this.trim = onOrOff;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<K, V> item : entrySet()) {
                sb.append(preKeyDelim);
                sb.append(item.getKey());
                sb.append(postKeyDelim);
                sb.append(item.getValue());
                sb.append(postValueDelim);
            }
            if (trim)
                sb.setLength(Math.max(0, sb.length() - postValueDelim.length())); // remove the extra delimiter

            return sb.toString();
        }
    }

    private static class ExceptionOnDuplicateKeySortedMap<K, V> extends ExceptionOnDuplicateKeyMap<K, V> implements SortedMap<K, V> {
        private SortedMap<K, V> delegate = null;

        private ExceptionOnDuplicateKeySortedMap(SortedMap<K, V> delegate) {
            super(delegate);
            this.delegate = delegate;
        }

        public Comparator<? super K> comparator() {
            return delegate.comparator();
        }

        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return delegate.subMap(fromKey, toKey);
        }

        public SortedMap<K, V> headMap(K toKey) {
            return delegate.headMap(toKey);
        }

        public SortedMap<K, V> tailMap(K fromKey) {
            return delegate.tailMap(fromKey);
        }

        public K firstKey() {
            return delegate.firstKey();
        }

        public K lastKey() {
            return delegate.lastKey();
        }
    }

}
