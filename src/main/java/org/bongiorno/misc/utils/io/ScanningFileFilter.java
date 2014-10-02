package org.bongiorno.misc.utils.io;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.bongiorno.misc.utils.functions.predicates.ScanningPredicate;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author chribong
 */
public class ScanningFileFilter implements FileFilter {


    private Predicate<Integer> predicate;

    public ScanningFileFilter(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public ScanningFileFilter(String lookingFor) {
        this.predicate = new ScanningPredicate(lookingFor);
    }
    public ScanningFileFilter(String lookingFor, Charset charset) {
        this.predicate = new ScanningPredicate(lookingFor,charset);
    }

    public ScanningFileFilter(byte[] lookingFor) {
        this.predicate = new ScanningPredicate(lookingFor);
    }

    @Override
    public boolean accept(File pathname) {
        final boolean[] accept = {true};
        try {
            ScanningInputStream input = new ScanningInputStream(predicate, new FoundClosure(accept), new FileInputStream(pathname));

            IOUtils.copy(input, new NullOutputStream());
            input.close();

        } catch (IOException e) {
            accept[0] = false;
        }
        return accept[0];
    }

    private static class FoundClosure implements Closure<InputStream> {
        private final boolean[] accept;

        public FoundClosure(boolean[] accept) {
            this.accept = accept;
        }

        @Override
        public void execute(InputStream input) {
            accept[0] = false;
            try {
                input.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

}