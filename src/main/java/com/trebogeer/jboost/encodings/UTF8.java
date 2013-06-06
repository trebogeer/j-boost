package com.trebogeer.jboost.encodings;

import java.lang.ref.SoftReference;
import java.nio.Buffer;
import java.nio.CharBuffer;

/**
 * @author dimav
 *         Date: 4/4/13
 *         Time: 3:35 PM
 */
public final class UTF8 {


    private final static ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal<SoftReference<char[]>>();


    private static final char CharError = '\uFFFD';   // the "error" char or "Unicode replacement character"
    private static final int CharSelf = 0x80;         // characters below CharSelf are represented as themselves in a single byte.
    private static final byte UTFMax = 4;            // maximum number of bytes of a UTF-8 encoded Unicode character.
    public static final String ErrorString = new String(new char[]{CharError});

//    private static final int t1 = 0x00; // 0000 0000
//    private static final int tx = 0x80; // 1000 0000
//    private static final int t2 = 0xC0; // 1100 0000
//    private static final int t3 = 0xE0; // 1110 0000
//    private static final int t4 = 0xF0; // 1111 0000
//    private static final int t5 = 0xF8; // 1111 1000

    private static final int maskx = 0x3F; // 0011 1111
    private static final int mask2 = 0x1F; // 0001 1111
    private static final int mask3 = 0x0F; // 0000 1111
    private static final int mask4 = 0x07; // 0000 0111

    private static final int char1Max = 1 << 7 - 1;
    private static final int char2Max = 1 << 11 - 1;
    private static final int char3Max = 1 << 16 - 1;
    private static final int char4Max = 1 << 21 - 1;


    private static char[] getChars(int length) {
        SoftReference<char[]> ref = charsBufLocal.get();

        if (ref == null) {
            return allocate(length);
        }

        char[] chars = ref.get();

        if (chars == null) {
            return allocate(length);
        }

        if (chars.length < length) {
            chars = allocate(length);
        }

        return chars;
    }

    private static char[] allocate(int length) {
        char[] chars = new char[length];
        charsBufLocal.set(new SoftReference<char[]>(chars));
        return chars;

    }


    public static String decode(final byte[] bytes) {
        return decode(bytes, 0, bytes.length, new char[bytes.length]);
    }

    public static String decode_tl(final byte[] bytes) {
        return decode(bytes, 0, bytes.length, getChars(bytes.length));
    }

    public static String decode(final byte[] bytes, final int offset, final int length) {

        return decode(bytes, offset, length, getChars(length));
    }

    public static String decode(final byte[] bytes, final int offset, final int length, char[] chars) {
        int last = offset + length;
        if (last > bytes.length) return ErrorString;
        // chars = new char[length];
        int charCnt = 0;
        for (int i = offset; i < last; i++) {
            byte b0 = bytes[i];
            if (/*b0 < tx && */b0 >= 0) {
                // 1-byte, 7-bit sequence
                chars[charCnt] = (char) (b0 & 0xff);
                charCnt++;
                continue;
            }

            // unexpected continuation byte or no more bytes
            if (/*b0 < t2 */b0 < -64 || (length - i) < 2) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }

            byte b1 = bytes[++i];

            if (/*b1 < tx || t2 <= b1*/ b1 >= -64) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }

            // 2 byte, 5+6 = 11 bit sequence
            if (/*b0 < t3*/b0 < -32) {
                int t = ((b0 & mask2) << 6) | (b1 & maskx);
                if (t <= char1Max) {
                    chars[charCnt] = CharError;
                } else {
                    chars[charCnt] = (char) t;
                }
                charCnt++;
                continue;
            }
            // need second continuation byte
            if (length - i < 2) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }

            byte b2 = bytes[++i];
            if (/*b2 < tx || t2 <= b2*/b2 >= -64) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }
            // 3-byte, 16-bit sequence?
            if (/*b0 < t4*/b0 < -16) {
                int t = (b0 & mask3) << 12 | (b1 & maskx) << 6 | b2 & maskx;
                if (t <= char2Max) {
                    chars[charCnt] = CharError;
                } else {
                    chars[charCnt] = (char) t;
                }
                charCnt++;
                continue;
            }

            // need third continuation byte
            if (length - i < 2) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }

            byte b3 = bytes[++i];
            if (/*b3 < tx || t2 <= b3*/b3 >= -64) {
                chars[charCnt] = CharError;
                charCnt++;
                continue;
            }

            // 4-byte, 21-bit sequence?
            if (/*b0 < t5*/b0 < -8) {
                int t = (b0 & mask4) << 18 | (b1 & maskx) << 12 | (b2 & maskx) << 6 | b3 & maskx;
                if (t <= char3Max) {
                    chars[charCnt] = CharError;
                } else {
                    chars[charCnt] = (char) t;
                }
                charCnt++;
                continue;

            }
            chars[charCnt] = CharError;
           // return ErrorString;

        }
        return new String(chars, 0, charCnt);
    }

}
