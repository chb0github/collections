package org.bongiorno.misc.utils.functions;


import javax.annotation.Nullable;

public interface ReversibleFunction<F, T> extends Function<F, T> {
    public F reverse(@Nullable T input);
}