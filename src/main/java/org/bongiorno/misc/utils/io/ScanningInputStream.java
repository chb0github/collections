package org.bongiorno.misc.utils.io;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.bongiorno.misc.utils.functions.BufferTransformer;
import org.bongiorno.misc.utils.functions.predicates.ScanningPredicate;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author chribong
 */
public class ScanningInputStream extends FilterInputStream {

    private final Predicate<Integer> condition;
    private final Closure<InputStream> callback;

    public ScanningInputStream(Predicate<Integer> condition, Closure<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        this.condition = condition;
    }

    public ScanningInputStream(byte[] lookingFor, Closure<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        condition = new ScanningPredicate(lookingFor);
    }


    public ScanningInputStream(String lookingFor, Charset charset, Closure<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        condition = new ScanningPredicate(lookingFor.getBytes(charset));
    }

    public int read() throws IOException {
        int read = super.read();

        if(condition.evaluate(read))
            callback.execute(super.in);

        return read;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int result = super.read(b, off, len);

        for(int i = off; i < result; i++) {
            if(condition.evaluate((int) b[i]))
                callback.execute(super.in);
        }
        return result;
    }
}
