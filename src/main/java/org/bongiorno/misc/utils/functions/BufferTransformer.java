package org.bongiorno.misc.utils.functions;

import org.apache.commons.collections4.Transformer;

/**
 * @author chribong
 */
public interface BufferTransformer<O> extends Transformer<byte[],O> {

    public int getNeededBufferSize();
}
