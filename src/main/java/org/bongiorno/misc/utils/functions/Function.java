package org.bongiorno.misc.utils.functions;

import javax.annotation.Nullable;

public interface Function<I, O>  {

    /**
     * Applies 'this' to I and returns O
     */
    O apply(@Nullable I input);
}