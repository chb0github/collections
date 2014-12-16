package org.bongiorno.misc.utils.functions;

import org.apache.commons.collections4.Transformer;

/**
 * @author chribong
 */
public abstract class AbstractFunction<I,O> implements Function<I,O>, Transformer<I,O> {


    @Override
    public O transform(I input) {
        return apply(input);
    }
}
