package org.bongiorno.collections;

import org.junit.Test;

import static java.util.stream.Stream.generate;
import static org.junit.Assert.assertEquals;

/**
 * @author cbongiorno
 */
public class ChainAbleMapTest {


    @Test
    public void thenPut() throws Exception {
        java.util.Random r = new java.util.Random(10L);
        ChainAbleMap<Integer, Integer> actual = generate(() -> new Integer[]{r.nextInt(), r.nextInt()}).limit(10).map(ints -> new ChainAbleMap<>(ints[0], ints[1])).reduce(ChainAbleMap::thenPutAll).get();
        ChainAbleMap<Integer, Integer> expected = new ChainAbleMap<>();
        expected.put(-1224131505, 1762380960);
        expected.put(-1157793070, 1913984760);
        expected.put(254270492, -1408064384);
        expected.put(1107254586, 1773446580);
        expected.put(1591762646, -1492345098);
        expected.put(-778209333, 1532292428);
        expected.put(1048475594, 1581279777);
        expected.put(1233758972, 1504499906);
        expected.put(-359716550, 183138476);
        expected.put(-617260113, -462612879);
        assertEquals(expected, actual);
    }

}