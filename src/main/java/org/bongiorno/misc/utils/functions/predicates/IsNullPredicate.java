package org.bongiorno.misc.utils.functions.predicates;

import org.bongiorno.misc.utils.functions.Function;

import javax.annotation.Nullable;

public class IsNullPredicate implements Function<Object, Boolean> {

    private static final IsNullPredicate INSTANCE = new IsNullPredicate();

    public static IsNullPredicate getInstance() {
        return INSTANCE;
    }

    private IsNullPredicate() {
    }

    @Override
    public Boolean apply(@Nullable Object input) {
        return input == null;
    }
}
