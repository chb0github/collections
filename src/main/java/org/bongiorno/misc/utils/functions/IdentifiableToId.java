package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.Identifiable;
import org.bongiorno.misc.utils.Function;

import javax.annotation.Nullable;

public class IdentifiableToId implements Function<Identifiable, Long> {

    private static IdentifiableToId INSTANCE = new IdentifiableToId();

    private IdentifiableToId() {
    }

    public static IdentifiableToId getInstance() {
        return INSTANCE;
    }

    @Override
    public Long apply(@Nullable Identifiable input) {
        return input == null ? null : input.getId();
    }

}
