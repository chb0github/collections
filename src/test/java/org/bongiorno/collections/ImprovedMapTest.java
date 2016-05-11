package org.bongiorno.collections;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class ImprovedMapTest {



    @Test
    public void transform() throws Exception {
        ImprovedMap<String, String> input = new ImprovedMap<>();

        input.put("christian", "123");
        input.put("peter", "456");

        ImprovedMap<String, Integer> actual = input.transform(k -> k.replaceAll("r", "G"), Integer::new);
        ImprovedMap<String, Integer> expected = new ImprovedMap<>();
        expected.put("chGistian", 123);
        expected.put("peteG", 456);

        assertEquals(expected, actual);
    }
}