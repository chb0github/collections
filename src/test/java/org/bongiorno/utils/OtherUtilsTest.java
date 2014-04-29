package org.bongiorno.utils;

import org.bongiorno.misc.utils.OtherUtils;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class OtherUtilsTest {

    @Test
    public void testFieldEquals() throws Exception {
        TestObject t0 = new TestObject("Christian", "Bongiorno", 34);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(t0);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        TestObject t1 = (TestObject) ois.readObject();

        assertTrue(OtherUtils.fieldEquals(t0, t1));
        t1.firstName = "Jack";
        assertFalse(OtherUtils.fieldEquals(t0, t1));
    }

    @Test
    public void testHashObject() throws Exception {
        TestObject to1 = new TestObject("Christian", "Bongiorno", 34);
        int hash = OtherUtils.fieldHashCode(to1);
        int hash2 = OtherUtils.fieldHashCode(to1);
        assertEquals(hash, hash2); // no side effects
        hash2 = OtherUtils.fieldHashCode(new TestObject("Christian", "Bongiorno", 34));
        assertEquals(hash, hash2); // different instances, still equal hash

        hash = OtherUtils.fieldHashCode(new TestObject(null, null, 0));
        hash2 = OtherUtils.fieldHashCode(new TestObject(null, null, 0));
        assertEquals(hash, hash2);

        // if no error then it's the best you can test
    }

    @Test
    public void testHashSeries() throws Exception {

        int hash = OtherUtils.hashCodeSeries("Christian", "Peter", "Bongiorno");
        int hash2 = OtherUtils.hashCodeSeries("Christian", "Peter", "Bongiorno");
        assertEquals(hash, hash2);

    }


    private static class TestObject implements Serializable {
        private String firstName;
        private String lastName;
        private int age;

        private TestObject(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }
    }
}
