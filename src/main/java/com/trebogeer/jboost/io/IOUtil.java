package com.trebogeer.jboost.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author dimav
 *         Date: 5/22/13
 *         Time: 12:04 PM
 */
public class IOUtil {


    private static final int BUFFER = Integer.getInteger("jboost.io.util.buffer", 1024);

    public static void pipe(final InputStream source, final OutputStream target) throws IOException {
        byte[] buf = new byte[BUFFER];
        while (true) {
            int r = source.read(buf);
            if (r == -1) {
                break;
            }
            target.write(buf, 0, r);
            target.flush();
        }
    }

    public static void closeQuite(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            // nothing to do
        }
    }
}
