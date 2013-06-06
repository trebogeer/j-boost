package com.trebogeer.jboost.encodings;

import com.trebogeer.jboost.util.StopWatch;

import java.io.UnsupportedEncodingException;

/**
 * @author dimav
 *         Date: 4/4/13
 *         Time: 4:24 PM
 */
public class UTF8Test {


  //  private static final String a1_short = "фshagf ü ö ä Ä Ü Ö ß 759بي/عربى8345392874 *%&^$&%$*&*^(*^&*&^*/漢語和英";
  //  private static final String a1_short = "英";
    private static final String a1_short = "asdfghjkl;'ASDYUIO%$*&12345";
    private static final String a1_long = "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364" +
            "фshagf ЛРПОРЛ 7598345392874 *%&^$&%$*&*^(*^&*&^*(& ?><><MBMNBVBXCXZSDADDJGGKHGkjhyt7364";
    private static final String a2_ = "a";
    private static final byte[] a1_s = a1_short.getBytes();
    private static final byte[] a1_l = a1_long.getBytes();

    public static void main(String... args) {

        for (int s = 0; s < 20; s++) {
            StopWatch sw = new StopWatch("utf-8");

            sw.start("warmup");
            for (int i = 0; i < 10; i++) {
                System.out.println(new String(a1_s));
                System.out.println(UTF8.decode(a1_s));
            }
            sw.stop();
            sw.start("utf8-java-native - short");
            for (int i = 0; i < 10000000; i++) {
                new String(a1_s);
            }
            sw.stop();
            sw.start("utf8-custom-go - short");
            for (int i = 0; i < 10000000; i++) {
                UTF8.decode(a1_s);
            }
            sw.stop();
            sw.start("utf8-custom-go - short - TL");
            for (int i = 0; i < 10000000; i++) {
                UTF8.decode_tl(a1_s);
            }
            sw.stop();

            sw.start("utf8-java-native - long");
            for (int i = 0; i < 1000000; i++) {
                new String(a1_l);
            }
            sw.stop();
            sw.start("utf8-custom-go - long");
            for (int i = 0; i < 1000000; i++) {
                UTF8.decode(a1_l);
            }
            sw.stop();
            sw.start("utf8-custom-go - long - TL");
            for (int i = 0; i < 1000000; i++) {
                UTF8.decode_tl(a1_l);
            }
            sw.stop();

            System.out.println(sw.prettyPrint());
        }
    }
}
