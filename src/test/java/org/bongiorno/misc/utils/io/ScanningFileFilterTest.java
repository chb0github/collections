package org.bongiorno.misc.utils.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertTrue;

/**
 * @author chribong
 */
@Ignore("WIP")
public class ScanningFileFilterTest {

    private File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = new File(this.getClass().getResource("/testscan.dat").getFile());
    }

    @Test
    public void testAcceptFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("broadcasting");
        assertTrue(filter.accept(testFile));
    }

    @Test
    public void testRejectFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("foo");
        assertTrue(filter.accept(testFile));
    }
}
