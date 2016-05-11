package org.bongiorno.collections;

import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author chribong
 */
public class ImprovedMap<K,V> extends QuickMap<K,V> {
    public ImprovedMap() {
        super(new HashMap<>());
    }

    public <A, B> ImprovedMap<A,B> transform(Function<? super K, ? extends A> fOfK, Function<? super V, ? extends B> fOfV) {
        ImprovedMap<A,B> retVal = new ImprovedMap<>();
        this.entrySet().stream().forEach(e -> retVal.put(fOfK.apply(e.getKey()), fOfV.apply(e.getValue())));
        return retVal;
    }

    public static <T> BinaryOperator<T> noDupKeys() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }
}
