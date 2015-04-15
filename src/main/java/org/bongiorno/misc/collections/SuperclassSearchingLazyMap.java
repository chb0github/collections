package org.bongiorno.misc.collections;

import java.util.Map;
import java.util.function.Function;

public class SuperclassSearchingLazyMap<V> extends QuickMap<Class<?>, V> {

    private Function<Class<?>, V> SEARCH_FUNCTION = clazz -> {
        V result = null;
        if (clazz != null){
            result = null;
            Class[] iFaces = clazz.getInterfaces();
            for(int i = 0; i < iFaces.length && result == null; ++i){
                result = this.get(iFaces[i]);
            }
            if(result == null){
                result = this.get(clazz.getSuperclass());
            }
        }
        return result;
    };

    public SuperclassSearchingLazyMap() {
    }

    public SuperclassSearchingLazyMap(Map<Class<?>, V> delegate) {
        super(delegate);
    }

    @Override
    public V get(Object key) {
        V result = super.get(key);
        if(result == null){
            result = SEARCH_FUNCTION.apply((Class<?>) key);
            if(result != null){
                this.put((Class<?>) key, result);
            }
        }
        return result;
    }
}
