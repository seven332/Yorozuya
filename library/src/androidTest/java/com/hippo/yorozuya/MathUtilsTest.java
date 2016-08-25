package com.hippo.yorozuya;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Arrays;

public class MathUtilsTest extends TestCase {

    public void testMax() {
        assertEquals(4325, MathUtils.max(1, 0, 345, -78, -3434_434, 4325, 1, 0));

        assertEquals(34343434L, MathUtils.max(342L, -3244L, 435L, 0L, 34343434L));

        assertEquals(32454f, MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
        assertEquals(Float.NaN, MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN));
        assertEquals(+0.0f, MathUtils.max(+0.0f, -0.0f, -0.0f, +0.0f));

        assertEquals(32454.0, MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45));
        assertEquals(Double.NaN, MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN));
        assertEquals(+0.0, MathUtils.max(+0.0, -0.0, -0.0, +0.0));

        try {
            assertEquals(1212, MathUtils.max());
        } catch (IllegalArgumentException e) {
            assertEquals(true, "Can't get max value from empty array".equals(e.getMessage()));
        }
    }

    public void testMin() {
        assertEquals(-3434434, MathUtils.min(1, 0, 345, -78, -3434_434, 4325, 1, 0));

        assertEquals(-3244L, MathUtils.min(342L, -3244L, 435L, 0L, 34343434L));

        assertEquals(-4535.45f, MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
        assertEquals(Float.NaN, MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN));
        assertEquals(-0.0f, MathUtils.min(+0.0f, -0.0f, -0.0f, +0.0f));

        assertEquals(-4535.45, MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45));
        assertEquals(Double.NaN, MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN));
        assertEquals(-0.0, MathUtils.min(+0.0, -0.0, -0.0, +0.0));

        try {
            assertEquals(1212, MathUtils.min());
        } catch (IllegalArgumentException e) {
            assertEquals(true, "Can't get min value from empty array".equals(e.getMessage()));
        }
    }

    public int nextPow2(int n) {
        int r = Integer.highestOneBit(n);
        if (Integer.bitCount(n) != 1)  {
            r <<= 1;
        }
        return r;
    }

    public long nextPow2(long n) {
        long r = Long.highestOneBit(n);
        if (Long.bitCount(n) != 1)  {
            r <<= 1;
        }
        return r;
    }

    public void testPow2() {
        int n;
        n = 32432;
        assertEquals(Integer.highestOneBit(n), MathUtils.prevPow2(n));
        assertEquals(nextPow2(n), MathUtils.nextPow2(n));
        n = 98078;
        assertEquals(Integer.highestOneBit(n), MathUtils.prevPow2(n));
        assertEquals(nextPow2(n), MathUtils.nextPow2(n));
        n = 1;
        assertEquals(Integer.highestOneBit(n), MathUtils.prevPow2(n));
        assertEquals(nextPow2(n), MathUtils.nextPow2(n));
        n = 1 << 15;
        assertEquals(Integer.highestOneBit(n), MathUtils.prevPow2(n));
        assertEquals(nextPow2(n), MathUtils.nextPow2(n));

        long m;
        m = 34252365432432434L;
        assertEquals(Long.highestOneBit(m), MathUtils.prevPow2(m));
        assertEquals(nextPow2(m), MathUtils.nextPow2(m));
        m = 5436548535324L;
        assertEquals(Long.highestOneBit(m), MathUtils.prevPow2(m));
        assertEquals(nextPow2(m), MathUtils.nextPow2(m));
        m = 1L;
        assertEquals(Long.highestOneBit(m), MathUtils.prevPow2(m));
        assertEquals(nextPow2(m), MathUtils.nextPow2(m));
        m = 1L << 54;
        assertEquals(Long.highestOneBit(m), MathUtils.prevPow2(m));
        assertEquals(nextPow2(m), MathUtils.nextPow2(m));
    }

    public void testFloorDiv() {
        assertEquals(0, MathUtils.floorDiv(6, 7));
        assertEquals(1, MathUtils.floorDiv(6, 6));
        assertEquals(1, MathUtils.floorDiv(6, 5));
        assertEquals(1, MathUtils.floorDiv(6, 4));
        assertEquals(2, MathUtils.floorDiv(6, 3));
        assertEquals(3, MathUtils.floorDiv(6, 2));
        assertEquals(6, MathUtils.floorDiv(6, 1));
        assertEquals(-6, MathUtils.floorDiv(6, -1));
        assertEquals(-3, MathUtils.floorDiv(6, -2));
        assertEquals(-2, MathUtils.floorDiv(6, -3));
        assertEquals(-2, MathUtils.floorDiv(6, -4));
        assertEquals(-2, MathUtils.floorDiv(6, -5));
        assertEquals(-1, MathUtils.floorDiv(6, -6));
        assertEquals(-1, MathUtils.floorDiv(6, -7));

        assertEquals(-1, MathUtils.floorDiv(-6, 7));
        assertEquals(-1, MathUtils.floorDiv(-6, 6));
        assertEquals(-2, MathUtils.floorDiv(-6, 5));
        assertEquals(-2, MathUtils.floorDiv(-6, 4));
        assertEquals(-2, MathUtils.floorDiv(-6, 3));
        assertEquals(-3, MathUtils.floorDiv(-6, 2));
        assertEquals(-6, MathUtils.floorDiv(-6, 1));
        assertEquals(6, MathUtils.floorDiv(-6, -1));
        assertEquals(3, MathUtils.floorDiv(-6, -2));
        assertEquals(2, MathUtils.floorDiv(-6, -3));
        assertEquals(1, MathUtils.floorDiv(-6, -4));
        assertEquals(1, MathUtils.floorDiv(-6, -5));
        assertEquals(1, MathUtils.floorDiv(-6, -6));
        assertEquals(0, MathUtils.floorDiv(-6, -7));
    }

    public void testCeilDiv() {
        assertEquals(1, MathUtils.ceilDiv(6, 7));
        assertEquals(1, MathUtils.ceilDiv(6, 6));
        assertEquals(2, MathUtils.ceilDiv(6, 5));
        assertEquals(2, MathUtils.ceilDiv(6, 4));
        assertEquals(2, MathUtils.ceilDiv(6, 3));
        assertEquals(3, MathUtils.ceilDiv(6, 2));
        assertEquals(6, MathUtils.ceilDiv(6, 1));
        assertEquals(-6, MathUtils.ceilDiv(6, -1));
        assertEquals(-3, MathUtils.ceilDiv(6, -2));
        assertEquals(-2, MathUtils.ceilDiv(6, -3));
        assertEquals(-1, MathUtils.ceilDiv(6, -4));
        assertEquals(-1, MathUtils.ceilDiv(6, -5));
        assertEquals(-1, MathUtils.ceilDiv(6, -6));
        assertEquals(0, MathUtils.ceilDiv(6, -7));

        assertEquals(0, MathUtils.ceilDiv(-6, 7));
        assertEquals(-1, MathUtils.ceilDiv(-6, 6));
        assertEquals(-1, MathUtils.ceilDiv(-6, 5));
        assertEquals(-1, MathUtils.ceilDiv(-6, 4));
        assertEquals(-2, MathUtils.ceilDiv(-6, 3));
        assertEquals(-3, MathUtils.ceilDiv(-6, 2));
        assertEquals(-6, MathUtils.ceilDiv(-6, 1));
        assertEquals(6, MathUtils.ceilDiv(-6, -1));
        assertEquals(3, MathUtils.ceilDiv(-6, -2));
        assertEquals(2, MathUtils.ceilDiv(-6, -3));
        assertEquals(2, MathUtils.ceilDiv(-6, -4));
        assertEquals(2, MathUtils.ceilDiv(-6, -5));
        assertEquals(1, MathUtils.ceilDiv(-6, -6));
        assertEquals(1, MathUtils.ceilDiv(-6, -7));
    }

    public void testFloorMod() {
        assertEquals(6, MathUtils.floorMod(6, 7));
        assertEquals(0, MathUtils.floorMod(6, 6));
        assertEquals(1, MathUtils.floorMod(6, 5));
        assertEquals(2, MathUtils.floorMod(6, 4));
        assertEquals(0, MathUtils.floorMod(6, 3));
        assertEquals(0, MathUtils.floorMod(6, 2));
        assertEquals(0, MathUtils.floorMod(6, 1));
        assertEquals(0, MathUtils.floorMod(6, -1));
        assertEquals(0, MathUtils.floorMod(6, -2));
        assertEquals(0, MathUtils.floorMod(6, -3));
        assertEquals(-2, MathUtils.floorMod(6, -4));
        assertEquals(-4, MathUtils.floorMod(6, -5));
        assertEquals(0, MathUtils.floorMod(6, -6));
        assertEquals(-1, MathUtils.floorMod(6, -7));

        assertEquals(1, MathUtils.floorMod(-6, 7));
        assertEquals(0, MathUtils.floorMod(-6, 6));
        assertEquals(4, MathUtils.floorMod(-6, 5));
        assertEquals(2, MathUtils.floorMod(-6, 4));
        assertEquals(0, MathUtils.floorMod(-6, 3));
        assertEquals(0, MathUtils.floorMod(-6, 2));
        assertEquals(0, MathUtils.floorMod(-6, 1));
        assertEquals(0, MathUtils.floorMod(-6, -1));
        assertEquals(0, MathUtils.floorMod(-6, -2));
        assertEquals(0, MathUtils.floorMod(-6, -3));
        assertEquals(-2, MathUtils.floorMod(-6, -4));
        assertEquals(-1, MathUtils.floorMod(-6, -5));
        assertEquals(0, MathUtils.floorMod(-6, -6));
        assertEquals(-6, MathUtils.floorMod(-6, -7));
    }

    public void testCeilMod() {
        assertEquals(-1, MathUtils.ceilMod(6, 7));
        assertEquals(0, MathUtils.ceilMod(6, 6));
        assertEquals(-4, MathUtils.ceilMod(6, 5));
        assertEquals(-2, MathUtils.ceilMod(6, 4));
        assertEquals(0, MathUtils.ceilMod(6, 3));
        assertEquals(0, MathUtils.ceilMod(6, 2));
        assertEquals(0, MathUtils.ceilMod(6, 1));
        assertEquals(0, MathUtils.ceilMod(6, -1));
        assertEquals(0, MathUtils.ceilMod(6, -2));
        assertEquals(0, MathUtils.ceilMod(6, -3));
        assertEquals(2, MathUtils.ceilMod(6, -4));
        assertEquals(1, MathUtils.ceilMod(6, -5));
        assertEquals(0, MathUtils.ceilMod(6, -6));
        assertEquals(6, MathUtils.ceilMod(6, -7));

        assertEquals(-6, MathUtils.ceilMod(-6, 7));
        assertEquals(0, MathUtils.ceilMod(-6, 6));
        assertEquals(-1, MathUtils.ceilMod(-6, 5));
        assertEquals(-2, MathUtils.ceilMod(-6, 4));
        assertEquals(0, MathUtils.ceilMod(-6, 3));
        assertEquals(0, MathUtils.ceilMod(-6, 2));
        assertEquals(0, MathUtils.ceilMod(-6, 1));
        assertEquals(0, MathUtils.ceilMod(-6, -1));
        assertEquals(0, MathUtils.ceilMod(-6, -2));
        assertEquals(0, MathUtils.ceilMod(-6, -3));
        assertEquals(2, MathUtils.ceilMod(-6, -4));
        assertEquals(4, MathUtils.ceilMod(-6, -5));
        assertEquals(0, MathUtils.ceilMod(-6, -6));
        assertEquals(1, MathUtils.ceilMod(-6, -7));
    }

    public void testRandom() {
        int[] siArray = {0,    0,        0, 233,  34242341, -34242342, -1};
        int[] biArray = {4325, 34242342, 1, 4325, 34242342, -34242341,  0};
        for (int i = 0; i < siArray.length; i++) {
            int si = siArray[i];
            int bi = biArray[i];
            int ri;
            if (si == 0) {
                ri = MathUtils.random(bi);
            } else {
                ri = MathUtils.random(si, bi);
            }
            assertEquals("ri = " + ri, true, ri >= si);
            assertEquals("ri = " + ri, true, ri < bi);
        }

        long[] slArray = {0L,    0L,        0L, 233L,  34242341L, -34242342L, -1L};
        long[] blArray = {4325L, 34242342L, 1L, 4325L, 34242342L, -34242341L,  0L};
        for (int i = 0; i < siArray.length; i++) {
            long sl = slArray[i];
            long bl = blArray[i];
            long rl;
            if (sl == 0L) {
                rl = MathUtils.random(bl);
            } else {
                rl = MathUtils.random(sl, bl);
            }
            assertEquals("rl = " + rl, true, rl >= sl);
            assertEquals("rl = " + rl, true, rl < bl);
        }


        float[] sfArray = {0f,    0f,        0.99f, 233f,  34341.99f, -34342f,    -0.99f};
        float[] bfArray = {4325f, 34242342f, 1f,    4325f, 34342f,    -34341.99f,  0f};
        for (int i = 0; i < siArray.length; i++) {
            float sf = sfArray[i];
            float bf = bfArray[i];
            float rf;
            if (sf == 0f) {
                rf = MathUtils.random(bf);
            } else {
                rf = MathUtils.random(sf, bf);
            }
            assertEquals("rf = " + rf + ", i = " + i, true, rf >= sf);
            assertEquals("rf = " + rf + ", i = " + i, true, rf < bf);
        }

        double[] sdArray = {0,    0,        0.99, 233,  34242341.99, -34242342,    -0.99};
        double[] bdArray = {4325, 34242342, 1,    4325, 34242342,    -34242341.99,  0};
        for (int i = 0; i < siArray.length; i++) {
            double sd = sdArray[i];
            double bd = bdArray[i];
            double rd;
            if (sd == 0f) {
                rd = MathUtils.random(bd);
            } else {
                rd = MathUtils.random(sd, bd);
            }
            assertEquals("rf = " + rd + ", i = " + i, true, rd >= sd);
            assertEquals("rf = " + rd + ", i = " + i, true, rd < bd);
        }
    }

    private int average(int... arg) {
        long max = 0;
        for (int n : arg) {
            max += n;
        }
        return (int) (max / arg.length);
    }

    public void testAverage() {
        for (int j = 0; j < 1000; j++) {
            int length = 2;
            int[] a = new int[length];
            for (int i = 0; i < length; i++) {
                a[i] = MathUtils.random(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            Assert.assertEquals(Arrays.toString(a), average(a), MathUtils.average(a));
        }
    }
}
