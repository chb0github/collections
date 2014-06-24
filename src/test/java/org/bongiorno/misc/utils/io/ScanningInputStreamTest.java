package org.bongiorno.misc.utils.io;

import org.bongiorno.misc.utils.functions.BufferTransformer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author chribong
 */
public class ScanningInputStreamTest {


    private static final byte[] color = new byte[]{'b', 'l', 'u', 'e', 'f', 'o', 'o', 'o'};
    private static final byte[] myName = new byte[]{'c', 'h', 'r', 'i', 'b', 'o', 'n', 'g'};
    private final Random rng = new Random(0);
    private final byte[] someNoise = new byte[256];

    @Test
    public void testIsFound() throws Exception {

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(myName);

        ScanningInputStream inputStream = new ScanningInputStream(bytesIn, myName, NAME_CONVERTER);

        inputStream.read(new byte[256]);
        assertTrue(inputStream.isFound());
    }

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < someNoise.length; i++) {
            someNoise[i] = '~';
        }
    }

    @Test
    public void testFindPatternComplex() throws Exception {


        int destPos = rng.nextInt(someNoise.length - myName.length);
        System.arraycopy(myName, 0, someNoise, destPos, myName.length);

        // stuff my color in there
        System.arraycopy(color, 0, someNoise, destPos + myName.length, color.length);

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(someNoise);

        ScanningInputStream inputStream = new ScanningInputStream(bytesIn, myName, NAME_CONVERTER);

        inputStream.read(new byte[256]);
        assertTrue(inputStream.isFound());
        assertEquals(new String(color), inputStream.getValue());

    }

    @Test
    public void testLessThanFull() throws Exception {
        byte[] someNoise = new byte[256];
        rng.nextBytes(someNoise);

        byte[] altName = new byte[] {'c','h','b','o'};

        int destPos = rng.nextInt(someNoise.length - altName.length);
        System.arraycopy(altName, 0, someNoise, destPos, altName.length);

        // stuff my color in there
        System.arraycopy(color, 0, someNoise, destPos + altName.length, color.length);

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(someNoise);

        ScanningInputStream inputStream = new ScanningInputStream(bytesIn, altName, NAME_CONVERTER);

        inputStream.read(new byte[256]);
        assertTrue(inputStream.isFound());
        assertEquals(new String(color), inputStream.getValue());

    }

    @Test
    public void testNotInStream() throws Exception {


        ScanningInputStream<Object> inputStream = new ScanningInputStream( new ByteArrayInputStream(someNoise), "foo".getBytes(), NO_OP);

        inputStream.read(new byte[256]);
        assertFalse(inputStream.isFound());

    }


    private static BufferTransformer<Object> NO_OP = new BufferTransformer<Object>() {
        @Override
        public int getNeededBufferSize() {
            return 0;
        }

        @Override
        public Object transform(byte[] input) {
            return null;
        }
    };

    private static BufferTransformer<String> NAME_CONVERTER = new BufferTransformer<String>() {
        @Override
        public int getNeededBufferSize() {
            return color.length;
        }

        @Override
        public String transform(byte[] input) {
            return new String(input);
        }
    };

}
