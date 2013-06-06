package com.trebogeer.jboost.hash;

import com.trebogeer.jboost.util.StopWatch;

import java.security.MessageDigest;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

/**
 * @author dimav
 *         Date: 5/2/13
 *         Time: 5:26 PM
 */
public class HashTest {

    public static void main(String... args) {
        try {
            byte[] input = "asjdfhksjdhfJKHK".getBytes();
            StopWatch sw = new StopWatch("hashes");


            MessageDigest md = MessageDigest.getInstance("MD5");
            sw.start("md5");
            for (int i = 0; i < 1000000; i++) {
                md.reset();
                md.digest(input);
            }
            sw.stop();

            MessageDigest mdcrc = MessageDigest.getInstance("SHA-1");
            sw.start("sha-1");
            for (int i = 0; i < 1000000; i++) {
                mdcrc.reset();
                mdcrc.digest(input);
            }
            sw.stop();


            CRC32 crc = new CRC32();

            sw.start("crc-32");
            for (int i = 0; i < 1000000; i++) {

                crc.update(input);
                crc.getValue();
                crc.reset();
            }
            sw.stop();


            Adler32 crc16 = new Adler32();

            sw.start("adler-32");
            for (int i = 0; i < 1000000; i++) {

                crc16.update(input);
                crc16.getValue();
                crc16.reset();
            }
            sw.stop();


            sw.start("murmur2");
            for (int i = 0; i < 1000000; i++) {
                Hashes.murmur2hash32(input, input.length);
            }
            sw.stop();

            sw.start("jenkins");
            for (int i = 0; i < 1000000; i++) {
                Hashes.jenkinsHash32(input);
            }
            sw.stop();

            sw.start("cwow");
            for (int i = 0; i < 1000000; i++) {
                Hashes.cwow32Hash(input, 0);
            }
            sw.stop();


            System.out.println(sw.prettyPrint());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
