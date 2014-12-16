package org.bongiorno.misc.utils.functions;

import javax.annotation.Nullable;

public class ToStringFunction implements Function<Object, String> {

    private static final ToStringFunction INSTANCE = new ToStringFunction();

    public static ToStringFunction getInstance() {
        return INSTANCE;
    }

    @Override
    public String apply(@Nullable Object input) {
        return "" + input;
    }
}
