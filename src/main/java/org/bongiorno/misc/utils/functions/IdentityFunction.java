package org.bongiorno.misc.utils.functions;


import javax.annotation.Nullable;

public class IdentityFunction<T> implements ReversibleFunction<T, T> {
    @Override
    public T reverse(@Nullable T input) {
        return input;
    }

    @Override
    public T apply(@Nullable T input) {
        return input;
    }
}
