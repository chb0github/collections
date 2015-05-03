package org.bongiorno.misc.collections;

import java.util.Map;

/**
 * Simple class for Concatenating method calls to add things
 * @param <K>
 * @param <V>
 */
public class ChainedMap<K, V> extends QuickMap<K, V> {

    public ChainedMap(Map<K, V> copy) {
        super(copy);
    }

    public ChainedMap<K, V> thenPut(K key, V value) {
        super.put(key, value);
        return this;
    }

}
