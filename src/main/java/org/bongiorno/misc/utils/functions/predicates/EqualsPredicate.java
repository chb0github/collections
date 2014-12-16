package org.bongiorno.misc.utils.functions.predicates;

import org.bongiorno.misc.utils.functions.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EqualsPredicate<T> implements Function<T, Boolean> {

    private T target;

    public EqualsPredicate(@Nonnull T target) {
        this.target = target;
    }

    @Override
    public Boolean apply(@Nullable T input) {
        return target.equals(input);
    }
}
