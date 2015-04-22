package org.bongiorno.utils;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import org.bongiorno.misc.collections.SuperTypeSearchingLazyMap;
import org.junit.Test;

import java.io.Closeable;
import java.io.DataInput;
import java.io.FilterInputStream;
import java.io.PipedInputStream;
import java.util.*;
import java.util.zip.GZIPInputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.bongiorno.misc.collections.WSCollections.*;
import static org.junit.Assert.fail;


public class WSCollectionsTest {

    @Test
    public void testDelimiter() {
        Collection c = delimitedCollection(Arrays.asList(1, 2, 3, 4, 5), "#:");
        assertEquals(c.toString(), "1#:2#:3#:4#:5");
    }

    @Test
    public void testDelimitedCollectionEmptyCollection() throws Exception {
        Collection c = delimitedCollection(new LinkedList<String>(), "#:");
        assertEquals(c.toString(), "");
    }

    @Test
    public void testDelimitedMap() throws Exception {
        Map<Object, Object> m = delimitedMap(new LinkedHashMap<>(), "#:", "$$");
        m.put("Christian", "Bongiorno");
        m.put("Snookie", "Bongiorno");
        m.put("Pookie", "Bongiorno");
        m.put("Rookie", "Bongiorno");
        assertEquals(m.toString(), "Christian#:Bongiorno$$Snookie#:Bongiorno$$Pookie#:Bongiorno$$Rookie#:Bongiorno$$");

        m = delimitedMap(new LinkedHashMap<>(), "!!", "#:", "$$");
        m.put("Christian", "Bongiorno");
        m.put("Snookie", "Bongiorno");
        m.put("Pookie", "Bongiorno");
        m.put("Rookie", "Bongiorno");
        assertEquals(m.toString(), "!!Christian#:Bongiorno$$!!Snookie#:Bongiorno$$!!Pookie#:Bongiorno$$!!Rookie#:Bongiorno$$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnDupMap() {
        Map<String, Void> test = exceptionOnDuplicateMap(new HashMap<String, Void>());
        test.put("Christian", null);
        test.put("George", null);
        test.put("Christian", null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnDubSortedMap() {
        Map<String, Void> test = exceptionOnDuplicateMap(new TreeMap<String, Void>());
        test.put("Christian", null);
        test.put("George", null);
        test.put("Christian", null);
    }

    @Test
    public void testOnDupSet() {
        Set<String> test = exceptionOnDuplicateSet(new HashSet<String>());
        test.add("Christian");
        test.add("George");
        try {
            test.add("Christian");
            fail();
        } catch (IllegalArgumentException e) {
            // better throw an exception
        }
        try {
            test.addAll(Arrays.asList("George", "Christian"));
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testSupertypeSearchingLazyMap() throws Exception {
        Map<Class<?>, String> map = new SuperTypeSearchingLazyMap<>();
        map.put(AutoCloseable.class, "AutoCloseable");
        map.put(Closeable.class, "Closeable");
        map.put(DataInput.class, "DataInput");
        map.put(FilterInputStream.class, "FilterInputStream");

        assertEquals("AutoCloseable", map.get(AutoCloseable.class));
        assertEquals("DataInput", map.get(ByteSequence.class));
        assertEquals("FilterInputStream", map.get(GZIPInputStream.class));
        assertEquals("Closeable", map.get(PipedInputStream.class));
        assertNull(map.get(Set.class));
        assertNull(map.get(null));
    }
}
