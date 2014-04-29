package org.bongiorno.misc.validators;

public interface Validator<T> {

    public boolean validate(T object);
}
