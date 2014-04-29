package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.utils.Function;

public class ConstantFunction<F,T> implements Function<F, T> {

    private T value;

    public ConstantFunction(T value){
        this.value = value;
    }

    @Override
    public T apply(F ignored) {
        return value;
    }
}
