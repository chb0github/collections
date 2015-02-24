package org.bongiorno.misc.utils;

import org.bongiorno.misc.collections.WSCollections;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class OtherUtils {


    private static final byte[] PNG_SIGNATURE = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};

    /**
     * This utility method will compare equality on all private non-static fields.
     * Note: If the security manager is active then this method will throw exceptions.
     * Sadly the only TRUE way to make sure this runs is to C and P it into your class. Best used on
     * classes with large fields that you don't want to have an ugly generated method for
     * <p>
     * caveat emptor
     *
     * @param a 'this' usually
     * @param b the other object to test equality on
     * @return true if all members as described above are equal
     * @throws IllegalAccessException if the caller of this function is unable to access the members of 'a' or 'b'
     */
    public static boolean fieldEquals(Object a, Object b) throws IllegalAccessException {
        boolean eq = true;
        Field[] fields = a.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length && eq; i++) {
            Field field = fields[i];
            int mods = field.getModifiers();
            if (Modifier.isPrivate(mods) && !Modifier.isStatic(mods)) {
                field.setAccessible(true);
                Object me = field.get(a);
                Object he = field.get(b);
                field.setAccessible(false);
                // both same instance or null or both not null and equal
                eq = me == he || (me != null && he != null && me.equals(he));

            }
        }
        return eq;
    }

    public static int fieldHashCode(Object a) throws IllegalAccessException {
        int hash = 1;
        Field[] fields = a.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mods = field.getModifiers();
            if (Modifier.isPrivate(mods) && !Modifier.isStatic(mods)) {
                field.setAccessible(true);
                Object me = field.get(a);
                field.setAccessible(false);
                if (me != null)
                    hash *= 31 * me.hashCode() >> 7;
            }
        }
        return hash;
    }

    public static int hashCodeSeries(Object... cherrios) {
        int hash = 1;
        for (Object field : cherrios) {
            hash *= 31 * field.hashCode() >> 7;
        }
        return hash;
    }


    /**
     * Get a set of all the fields in an object that have not been initialized.  Useful when testing transformers, to
     * make sure you're not missing anything (and let you know what you are missing).
     *
     * @param o The object to check for null fields
     * @return All the fields that are null
     * @throws IllegalAccessException If the security manager doesn't like you looking at private fields.
     */
    public static Collection<Field> nullFields(Object o) throws IllegalAccessException {
        //Primary use is to show me everything I forgot in a transformer test, so I want this to be easy to read.
        Collection<Field> result = WSCollections.delimitedCollection(new LinkedList<Field>(), "\n");
        for (Field field : o.getClass().getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                if (field.get(o) == null) {
                    result.add(field);
                }
            }
        }
        return result;
    }
     public static String hexFormat(byte[] data) {
         Formatter f = new Formatter();

         for (byte b : data)
             f.format("%02x", b);

         return f.toString();
     }
    public static boolean zeroOrAllNull(Object... things) {
        return zeroOrAllNull(Arrays.asList(things));
    }

    public static boolean zeroOrAllNull(Collection<?> things) {
        boolean result = things == null;
        if (!result) {
            long count = things.stream().filter(Objects::nonNull).count();
            result = count == 0 || count == things.size();
        }
        return result;
    }


    public static String getMyHostname() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Unknown *local* host?  Really?");
            e.printStackTrace(System.err);
        }
        return hostName;
    }

    public static boolean isImgPng(byte[] imgData) {
        boolean match = imgData.length >= PNG_SIGNATURE.length;
        for (int i = 0; match && i < PNG_SIGNATURE.length; ++i) {
            match = imgData[i] == PNG_SIGNATURE[i];
        }

        return match;
    }
}
