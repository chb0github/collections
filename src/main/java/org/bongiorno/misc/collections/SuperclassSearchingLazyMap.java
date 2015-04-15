package org.bongiorno.misc.collections;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.LazyMap;

import java.util.Map;

public class SuperclassSearchingLazyMap<V> extends QuickMap<Class<?>, V> {

    private Transformer<Class<?>, V> SEARCH_FUNCTION = clazz -> {
        V result = null;
        if (clazz != null){
            result = null;
            Class[] iFaces = clazz.getInterfaces();
            for(int i = 0; i < iFaces.length && result == null; ++i){
                result = delegate.get(iFaces[i]);
            }
            if(result == null){
                result = delegate.get(clazz.getSuperclass());
            }
        }
        return result;
    };

    public SuperclassSearchingLazyMap() {
        this.delegate = LazyMap.lazyMap(this.delegate, SEARCH_FUNCTION);
    }

    public SuperclassSearchingLazyMap(Map<Class<?>, V> delegate) {
        this.delegate = LazyMap.lazyMap(delegate, SEARCH_FUNCTION);
    }
}
