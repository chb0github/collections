package org.bongiorno.misc.utils.io;

import org.apache.commons.collections4.Transformer;
import org.bongiorno.misc.utils.functions.BufferTransformer;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chribong
 */
public class ScanningInputStream<T> extends FilterInputStream {

    private final byte[] convertBuffer;
    private int convertBuffPos = 0;

    private long current;
    private long lookingFor;
    private Transformer<byte[],T> converter;
    private int readCount = 0;

    public ScanningInputStream(InputStream in, byte[] lookingFor, BufferTransformer<T> converter) {
        super(in);
        if(lookingFor.length > 8)
            throw new IllegalArgumentException("only 8 bytes are currently supported");

        this.converter = converter;
        this.convertBuffer = new byte[converter.getNeededBufferSize()];
        for (byte bite : lookingFor) {
            this.lookingFor = (this.lookingFor << 8) | bite;
        }

    }

    @Override
    public int read() throws IOException {
        int read = super.read();
        if(convertBuffPos == 0 && lookingFor !=  (current & lookingFor)) {
            current = (current << 8) | (byte)read;
        }
        else {
            if(convertBuffPos < convertBuffer.length)
                convertBuffer[convertBuffPos++] = (byte) read;
        }
        return read;
    }


    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (available() > 0 && convertBuffPos < convertBuffer.length) {
            for(;  readCount < len; readCount++) {
                int bite = read();
                if(bite == -1)
                    break;

                b[off + readCount] = (byte) bite;
            }
        }
        else {
            readCount = super.read(b,off,len);
        }

        return readCount;
    }

    public boolean isFound() {
        return lookingFor ==  (current & lookingFor);
    }

    public T getValue() {
        return converter.transform(convertBuffer);
    }
}
