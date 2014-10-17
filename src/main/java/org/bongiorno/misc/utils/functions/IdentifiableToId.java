package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.Identifiable;
import org.bongiorno.misc.utils.Function;

import javax.annotation.Nullable;

public class IdentifiableToId<T> implements Function<Identifiable<T>, T> {

    private static IdentifiableToId<?> INSTANCE = new IdentifiableToId<>();

    private IdentifiableToId() {
    }

    public static IdentifiableToId<?> getInstance() {
        return INSTANCE;
    }


    @Override
    public T apply(@Nullable Identifiable<T> input) {
        return input == null ? null : input.getId();
    }

}
