package org.bongiorno.misc.utils.functions;

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
