package org.bongiorno.collections;

import java.util.Map;

/**
 * Simple class for Concatenating method calls to add things
 * @param <K>
 * @param <V>
 */
public class ChainAbleMap<K, V> extends QuickMap<K, V> {

    public ChainAbleMap(){
    }


    public ChainAbleMap(K key, V value, Map<K, V> delegate) {
        super(delegate);
        this.put(key,value);
    }

    public ChainAbleMap(K key, V value) {
        this.put(key,value);
    }

    public ChainAbleMap(Map<K, V> delegate) {
        super(delegate);
    }

    public ChainAbleMap<K, V> thenPut(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ChainAbleMap<K, V> thenPutAll(Map<K,V> otherMap) {
        super.putAll(otherMap);
        return this;
    }


}
