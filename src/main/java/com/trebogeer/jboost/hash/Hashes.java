package com.trebogeer.jboost.hash;

/**
 * @author dimav
 *         Date: 5/2/13
 *         Time: 3:48 PM
 */
public class Hashes {

    private final static int CWOW_32_M = 0x57559429;
    private final static int CWOW_32_N = 0x5052acdb;

    private static final long LONG_LO_MASK = 0x00000000FFFFFFFFL;

    private static final int MURMUR2_M = 0x5bd1e995;
    private static final int MURMUR2_R = 24;

    private Hashes() {
    }

    public static int murmur2hash32(final byte[] data, final int length, final int seed) {

        int h = seed ^ length;

        int len = length;
        int len_4 = len >> 2;

        for (int i = 0; i < len_4; i++) {
            int i_4 = i << 2;
            int k = data[i_4 + 3];
            k = k << 8;
            k = k | (data[i_4 + 2] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 1] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 0] & 0xff);
            k *= MURMUR2_M;
            k ^= k >>> MURMUR2_R;
            k *= MURMUR2_M;
            h *= MURMUR2_M;
            h ^= k;
        }

        int len_m = len_4 << 2;
        int left = len - len_m;

        if (left != 0) {
            if (left >= 3) {
                h ^= (int) data[len - 3] << 16;
            }
            if (left >= 2) {
                h ^= (int) data[len - 2] << 8;
            }
            if (left >= 1) {
                h ^= (int) data[len - 1];
            }

            h *= MURMUR2_M;
        }

        h ^= h >>> 13;
        h *= MURMUR2_M;
        h ^= h >>> 15;

        return h;
    }

    public static int murmur2hash32(final byte[] data, int length) {
        return murmur2hash32(data, length, 0x9747b28c);
    }

    public static int murmur2hash32(final String text) {
        final byte[] bytes = text.getBytes();
        return murmur2hash32(bytes, bytes.length);
    }


    public static int cwow32Hash(byte[] data, int seed) {
        final int length = data.length;

        /* cwfold( a, b, lo, hi ): */
        /* p = (u32)(a) * (u64)(b); lo ^=(u32)p; hi ^= (u32)(p >> 32) */
        /* cwmixa( in ): cwfold( in, m, k, h ) */
        /* cwmixb( in ): cwfold( in, n, h, k ) */

        int hVal = seed;
        int k = length + seed + CWOW_32_N;
        long p = 0;

        int pos = 0;
        int len = length;

        while (len >= 8) {
            int i1 = gatherIntLE(data, pos);
            int i2 = gatherIntLE(data, pos + 4);

            /* cwmixb(i1) = cwfold( i1, N, hVal, k ) */
            p = i1 * (long) CWOW_32_N;
            k ^= p & LONG_LO_MASK;
            hVal ^= (p >> 32);
            /* cwmixa(i2) = cwfold( i2, M, k, hVal ) */
            p = i2 * (long) CWOW_32_M;
            hVal ^= p & LONG_LO_MASK;
            k ^= (p >> 32);

            pos += 8;
            len -= 8;
        }

        if (len >= 4) {
            int i1 = gatherIntLE(data, pos);

            /* cwmixb(i1) = cwfold( i1, N, hVal, k ) */
            p = i1 * (long) CWOW_32_N;
            k ^= p & LONG_LO_MASK;
            hVal ^= (p >> 32);

            pos += 4;
            len -= 4;
        }

        if (len > 0) {
            int i1 = gatherPartialIntLE(data, pos, len);

            /* cwmixb(i1) = cwfold( i1, N, hVal, k ) */
            p = (i1 & ((1 << (len * 8)) - 1)) * (long) CWOW_32_M;
            hVal ^= p & LONG_LO_MASK;
            k ^= (p >> 32);
        }

        p = (hVal ^ (k + CWOW_32_N)) * (long) CWOW_32_N;
        k ^= p & LONG_LO_MASK;
        hVal ^= (p >> 32);
        hVal ^= k;

        return hVal;
    }

    /**
     * gather an int from the specified index into the byte array
     */
    private static int gatherIntLE(byte[] data, int index) {
        int i = data[index] & 0xFF;

        i |= (data[++index] & 0xFF) << 8;
        i |= (data[++index] & 0xFF) << 16;
        i |= (data[++index] << 24);

        return i;
    }

    /**
     * gather a partial int from the specified index using the specified number
     * of bytes into the byte array
     */
    private static int gatherPartialIntLE(byte[] data, int index,
                                          int available) {
        int i = data[index] & 0xFF;

        if (available > 1) {
            i |= (data[++index] & 0xFF) << 8;
            if (available > 2) {
                i |= (data[++index] & 0xFF) << 16;
            }
        }

        return i;
    }


    public static int jenkinsHash32(byte[] k) {
        return jenkinsHash32(k, k.length, 0, 0);
    }


    public static int jenkinsHash32(byte[] k, int length, int pc, int pb) {
        int a, b, c;

        a = b = c = 0xdeadbeef + length + pc;
        c += pb;

        int offset = 0;
        while (length > 12) {
            a += k[offset + 0];
            a += k[offset + 1] << 8;
            a += k[offset + 2] << 16;
            a += k[offset + 3] << 24;
            b += k[offset + 4];
            b += k[offset + 5] << 8;
            b += k[offset + 6] << 16;
            b += k[offset + 7] << 24;
            c += k[offset + 8];
            c += k[offset + 9] << 8;
            c += k[offset + 10] << 16;
            c += k[offset + 11] << 24;

            // mix(a, b, c);
            a -= c;
            a ^= rot(c, 4);
            c += b;
            b -= a;
            b ^= rot(a, 6);
            a += c;
            c -= b;
            c ^= rot(b, 8);
            b += a;
            a -= c;
            a ^= rot(c, 16);
            c += b;
            b -= a;
            b ^= rot(a, 19);
            a += c;
            c -= b;
            c ^= rot(b, 4);
            b += a;

            length -= 12;
            offset += 12;
        }

        switch (length) {
            case 12:
                c += k[offset + 11] << 24;
            case 11:
                c += k[offset + 10] << 16;
            case 10:
                c += k[offset + 9] << 8;
            case 9:
                c += k[offset + 8];
            case 8:
                b += k[offset + 7] << 24;
            case 7:
                b += k[offset + 6] << 16;
            case 6:
                b += k[offset + 5] << 8;
            case 5:
                b += k[offset + 4];
            case 4:
                a += k[offset + 3] << 24;
            case 3:
                a += k[offset + 2] << 16;
            case 2:
                a += k[offset + 1] << 8;
            case 1:
                a += k[offset + 0];
                break;
            case 0:
                return c;
        }

        // Final mixing of thrree 32-bit values in to c
        c ^= b;
        c -= rot(b, 14);
        a ^= c;
        a -= rot(c, 11);
        b ^= a;
        b -= rot(a, 25);
        c ^= b;
        c -= rot(b, 16);
        a ^= c;
        a -= rot(c, 4);
        b ^= a;
        b -= rot(a, 14);
        c ^= b;
        c -= rot(b, 24);

        return c;
    }

    private static int rot(int x, int distance) {
        return (x << distance) | (x >> (32 - distance));
        // return (x << distance) | (x >>> -distance);
    }

}
