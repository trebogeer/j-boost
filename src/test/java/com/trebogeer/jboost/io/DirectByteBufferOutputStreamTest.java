package com.trebogeer.jboost.io;

import com.trebogeer.jboost.util.StopWatch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * @author dimav
 *         Date: 5/22/13
 *         Time: 11:56 AM
 */
public class DirectByteBufferOutputStreamTest {


    static ThreadLocal<ByteArrayOutputStream> b = new ThreadLocal<ByteArrayOutputStream>(){
        @Override
        protected ByteArrayOutputStream initialValue() {
            return new ByteArrayOutputStream(DirectByteBufferOutputStream.buffer_size);
        }

        @Override
        public ByteArrayOutputStream get() {
            ByteArrayOutputStream byteArrayOutputStream = super.get();
            byteArrayOutputStream.reset();
            return byteArrayOutputStream;
        }
    };


    public static void main(String... args) {
        ByteArrayInputStream bis = new ByteArrayInputStream(TestStr.bytes);
        bis.mark(0);


        try {

            for (int i = 0; i < 5; i++) {
                DirectByteBufferOutputStream os = DirectByteBufferOutputStream.getDirectBufferStream();
                IOUtil.pipe(bis, os);
                bis.reset();
                assert Arrays.equals(os.toByteArray(), TestStr.bytes);
            }

            StopWatch sw = new StopWatch("byte test");
            sw.start("DirectByteBuffer");
            for (int i = 0; i < 100000; i++) {
                DirectByteBufferOutputStream os = DirectByteBufferOutputStream.getDirectBufferStream();
                IOUtil.pipe(bis, os);
                os.toByteArray();

            }
            sw.stop();


            for (int i = 0; i < 5; i++) {
                ByteArrayOutputStream os = new ByteArrayOutputStream(DirectByteBufferOutputStream.buffer_size);
                IOUtil.pipe(bis, os);
                bis.reset();
                assert Arrays.equals(os.toByteArray(), TestStr.bytes);
            }

            sw.start("ByteArrayOutputStream");
            for (int i = 0; i < 100000; i++) {
                ByteArrayOutputStream os = new ByteArrayOutputStream(DirectByteBufferOutputStream.buffer_size);
                IOUtil.pipe(bis, os);
                os.toByteArray();

            }
            sw.stop();


            for (int i = 0; i < 5; i++) {
                ByteArrayOutputStream os = b.get();
                IOUtil.pipe(bis, os);
                bis.reset();
                assert Arrays.equals(os.toByteArray(), TestStr.bytes);
            }

            sw.start("ByteArrayOutputStream - Thread Local");
            for (int i = 0; i < 100000; i++) {
                ByteArrayOutputStream os = b.get();
                IOUtil.pipe(bis, os);
                os.toByteArray();

            }
            sw.stop();
            System.out.println(sw.prettyPrint());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
