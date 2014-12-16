package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.Transformable;

import javax.annotation.Nullable;

public class TransformFunction<T> implements Function<Transformable<T>, T> {

    private static final TransformFunction instance = new TransformFunction();

    private TransformFunction(){

    }

    @SuppressWarnings("unchecked")
    public static <X> TransformFunction<X> getInstance(){
        return instance;
    }

    @Override
    public T apply(@Nullable Transformable<T> input) {
        return input == null ? null : input.transform();
    }
}
