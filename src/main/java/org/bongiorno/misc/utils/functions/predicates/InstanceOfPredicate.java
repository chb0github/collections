package org.bongiorno.misc.utils.functions.predicates;

import org.bongiorno.misc.utils.Function;

import javax.annotation.Nullable;

public class InstanceOfPredicate<T> implements Function<Object, Boolean> {

    private Class<T> clazz;

    public InstanceOfPredicate(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> InstanceOfPredicate<T> forClass(Class<T> clazz) {
        return new InstanceOfPredicate<T>(clazz);
    }

    @Override
    public Boolean apply(@Nullable Object input) {
        return input != null && clazz.isAssignableFrom(input.getClass());
    }
}
