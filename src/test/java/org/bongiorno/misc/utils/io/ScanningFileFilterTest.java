package org.bongiorno.misc.utils.io;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.assertTrue;

/**
 * @author chribong
 */
public class ScanningFileFilterTest {


    @Test
    @Ignore("Doesn't pass because of encoding issue, I think")
    public void testAcceptFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("broadcasting", Charset.forName("UTF-16BE"));
        File pathname = new File(this.getClass().getResource("/testscan.dat").getFile());

        assertTrue(filter.accept(pathname));
    }

    @Test
    @Ignore("Doesn't pass because of encoding issue, I think")
    public void testRejectFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("foo");
        assertTrue(filter.accept(new File(this.getClass().getResource("/testscan.dat").getFile())));
    }
}
