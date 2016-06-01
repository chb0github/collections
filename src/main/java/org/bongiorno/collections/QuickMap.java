package org.bongiorno.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * A delegating wrapper for a Map.  You can extend this class to quickly implement Map wrappers.
 */
public class QuickMap<K,V> implements Map<K,V> {

    protected Map<K,V> delegate = new HashMap<K, V>();

    public QuickMap() {
    }

    public QuickMap(Map<K, V> delegate) {
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
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return delegate.get(key);
    }

    public V put(K key, V value) {
        return delegate.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return delegate.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        delegate.putAll(m);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public ImprovedSet<K> keySet() {
        return ImprovedSet.of(delegate.keySet());
    }

    @Override
    public ImprovedCollection<V> values() {
        return ImprovedCollection.of(delegate.values());
    }

    @Override
    public ImprovedSet<Entry<K, V>> entrySet() {
        return ImprovedSet.of(delegate.entrySet());
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return String.join("\r\n",this.entrySet().transform(Map.Entry::toString));
    }
}
