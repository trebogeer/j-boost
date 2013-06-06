package com.trebogeer.jboost.encodings;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author dimav
 *         Date: 4/24/13
 *         Time: 5:29 PM
 */
public class UTF8TestsDecode {

    public static void main(String... args) {
        try {
            final InputStream is = UTF8TestsDecode.class.getClassLoader().getResourceAsStream("UTF-8-test.txt");
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            while (true) {
                int r = is.read(buf);
                if (r == -1) {
                    break;
                }
                baos.write(buf, 0, r);
                baos.flush();
            }

            String text = new String(baos.toByteArray());
            String text1 = UTF8.decode(baos.toByteArray());
            System.out.println(text);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(text1);
            System.out.println(text1.equals(text));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
