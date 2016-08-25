/*
 * Copyright 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.yorozuya;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.util.Random;

// Get most code from android.util.MathUtils
public final class MathUtils {
    private MathUtils() {}

    private static final Random sRandom = new Random();
    private static final float DEG_TO_RAD = 3.1415926f / 180.0f;
    private static final float RAD_TO_DEG = 180.0f / 3.1415926f;

    /**
     * Returns the most positive (closest to positive infinity) of all the
     * arguments.
     */
    @CheckResult
    public static int max(@NonNull int... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get max value from empty array");
        }
        int max = arg[0];
        for (int i = 1; i < length; i++) {
            max = Math.max(max, arg[i]);
        }
        return max;
    }

    /**
     * Returns the most positive (closest to positive infinity) of all the
     * arguments.
     */
    @CheckResult
    public static long max(@NonNull long... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get max value from empty array");
        }
        long max = arg[0];
        for (int i = 1; i < length; i++) {
            max = Math.max(max, arg[i]);
        }
        return max;
    }

    /**
     * Returns the most positive (closest to positive infinity) of all the
     * arguments.
     * <p>
     * Special cases are the same as {@link Math#max(float, float)}
     */
    @CheckResult
    public static float max(@NonNull float... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get max value from empty array");
        }
        float max = arg[0];
        for (int i = 1; i < length; i++) {
            max = Math.max(max, arg[i]);
        }
        return max;
    }

    /**
     * Returns the most positive (closest to positive infinity) of all the
     * arguments.
     * <p>
     * Special cases are the same as {@link Math#max(double, double)}
     */
    @CheckResult
    public static double max(@NonNull double... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get max value from empty array");
        }
        double max = arg[0];
        for (int i = 1; i < length; i++) {
            max = Math.max(max, arg[i]);
        }
        return max;
    }

    /**
     * Returns the most negative (closest to negative infinity) of all the
     * arguments.
     */
    @CheckResult
    public static int min(@NonNull int... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get min value from empty array");
        }
        int min = arg[0];
        for (int i = 1; i < length; i++) {
            min = Math.min(min, arg[i]);
        }
        return min;
    }

    /**
     * Returns the most negative (closest to negative infinity) of all the
     * arguments.
     */
    @CheckResult
    public static long min(@NonNull long... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get min value from empty array");
        }
        long min = arg[0];
        for (int i = 1; i < length; i++) {
            min = Math.min(min, arg[i]);
        }
        return min;
    }

    /**
     * Returns the most negative (closest to negative infinity) of all the
     * arguments.
     * <p>
     * Special cases are the same as {@link Math#max(float, float)}
     */
    @CheckResult
    public static float min(@NonNull float... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get min value from empty array");
        }
        float min = arg[0];
        for (int i = 1; i < length; i++) {
            min = Math.min(min, arg[i]);
        }
        return min;
    }

    /**
     * Returns the most negative (closest to negative infinity) of all the
     * arguments.
     * <p>
     * Special cases are the same as {@link Math#max(double, double)}
     */
    @CheckResult
    public static double min(@NonNull double... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get min value from empty array");
        }
        double min = arg[0];
        for (int i = 1; i < length; i++) {
            min = Math.min(min, arg[i]);
        }
        return min;
    }

    /**
     * Return average of the array without overflow.
     * Both positive and negative are supported.
     * Throw IllegalArgumentException if arg is empty.
     */
    public static int average(@NonNull int... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get average from empty array");
        }

        int x = 0, y = 0;
        for (int n : arg) {
            x += n / length;
            y += n % length;
            if (y >= length) {
                ++x;
                y -= length;
            } else if (y <= -length) {
                --x;
                y += length;
            }
        }

        if (x > 0 && y < 0) {
            --x;
        } else if (x < 0 && y > 0) {
            ++x;
        }

        return x;
    }

    /**
     * Return average of the array without overflow.
     * Both positive and negative are supported.
     * Throw IllegalArgumentException if arg is empty.
     */
    public static long average(@NonNull long... arg) {
        int length = arg.length;
        if (length <= 0) {
            throw new IllegalArgumentException("Can't get average from empty array");
        }

        long x = 0, y = 0;
        for (long n : arg) {
            x += n / length;
            y += n % length;
            if (y >= length) {
                ++x;
                y -= length;
            } else if (y <= -length) {
                --x;
                y += length;
            }
        }

        if (x > 0 && y < 0) {
            --x;
        } else if (x < 0 && y > 0) {
            ++x;
        }

        return x;
    }

    /**
     * Returns distance between two point
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @return the distance
     */
    @CheckResult
    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.hypot(x2 - x1, y2 - y1);
    }

    /**
     * Returns distance between two point
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @return the distance
     */
    @CheckResult
    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    /**
     * Returns distance between two point
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param z1 The z-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param z2 The z-coordinate of the second point
     * @return the distance
     */
    @CheckResult
    public static float dist(float x1, float y1, float z1, float x2, float y2, float z2) {
        final float x = (x2 - x1);
        final float y = (y2 - y1);
        final float z = (z2 - z1);
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns distance between two point
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param z1 The z-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param z2 The z-coordinate of the second point
     * @return the distance
     */
    @CheckResult
    public static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
        final double x = (x2 - x1);
        final double y = (y2 - y1);
        final double z = (z2 - z1);
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns whether the two point are close
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param slop the max distance to judge near
     */
    @CheckResult
    public static boolean near(float x1, float y1, float x2, float y2, float slop) {
        return dist(x1, y1, x2, y2) < slop;
    }

    /**
     * Returns whether the two point are close
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param slop the max distance to judge near
     */
    @CheckResult
    public static boolean near(double x1, double y1, double x2, double y2, double slop) {
        return dist(x1, y1, x2, y2) < slop;
    }

    /**
     * Returns whether the two point are close
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param z1 The z-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param z2 The z-coordinate of the second point
     * @param slop the max distance to judge near
     */
    @CheckResult
    public static boolean near(float x1, float y1, float z1, float x2, float y2, float z2, float slop) {
        return dist(x1, y1, z1, x2, y2, z2) < slop;
    }

    /**
     * Returns whether the two point are close
     *
     * @param x1 The x-coordinate of the first point
     * @param y1 The y-coordinate of the first point
     * @param z1 The z-coordinate of the first point
     * @param x2 The x-coordinate of the second point
     * @param y2 The y-coordinate of the second point
     * @param z2 The z-coordinate of the second point
     * @param slop the max distance to judge near
     */
    @CheckResult
    public static boolean near(double x1, double y1, double z1, double x2, double y2, double z2, double slop) {
        return dist(x1, y1, z1, x2, y2, z2) < slop;
    }

    /**
     * Convert degrees to radians
     */
    @CheckResult
    public static float radians(float degrees) {
        return degrees * DEG_TO_RAD;
    }

    /**
     * Convert radians to degrees
     */
    @CheckResult
    public static float degrees(float radians) {
        return radians * RAD_TO_DEG;
    }

    /**
     * Linear interpolation
     */
    @CheckResult
    public static int lerp(int start, int stop, float amount) {
        return start + (int) ((stop - start) * amount);
    }

    /**
     * Linear interpolation
     */
    @CheckResult
    public static long lerp(long start, long stop, float amount) {
        return start + (long) ((stop - start) * amount);
    }

    /**
     * Linear interpolation
     */
    @CheckResult
    public static float lerp(float start, float stop, float amount) {
        return start + (stop - start) * amount;
    }

    /**
     * Inverse of lerp
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code norm(5, 5, 5) = 0}</li>
     * <li>{@code norm(5, 5, 2) = Float.NaN}</li>
     * </ul>
     */
    @CheckResult
    public static float norm(int start, int stop, int value) {
        if (start == stop) {
            if (stop == value) {
                return 1.0f;
            } else {
                return Float.NaN;
            }
        } else {
            return (float) (value - start) / (float) (stop - start);
        }
    }

    /**
     * Inverse of lerp
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code norm(5, 5, 5) = 0}</li>
     * <li>{@code norm(5, 5, 2) = Float.NaN}</li>
     * </ul>
     */
    @CheckResult
    public static float norm(long start, long stop, long value) {
        if (start == stop) {
            if (stop == value) {
                return 1.0f;
            } else {
                return Float.NaN;
            }
        } else {
            return (float) (value - start) / (float) (stop - start);
        }
    }

    /**
     * Inverse of lerp
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code norm(5.0f, 5.0f, 5.0f) = 0.0f}</li>
     * <li>{@code norm(5.0f, 5.0f, 2.0f) = Float.NaN}</li>
     * </ul>
     */
    @CheckResult
    public static float norm(float start, float stop, float value) {
        if (stop == start) {
            if (stop == value) {
                return 1.0f;
            } else {
                return Float.NaN;
            }
        } else {
            return (value - start) / (stop - start);
        }
    }

    /**
     * Returns the input value x clamped to the range [min, max].
     */
    @CheckResult
    public static int clamp(int x, int min, int max) {
        if (x > max) return max;
        if (x < min) return min;
        return x;
    }

    /**
     * Returns the input value x clamped to the range [min, max].
     */
    @CheckResult
    public static long clamp(long x, long min, long max) {
        if (x > max) return max;
        if (x < min) return min;
        return x;
    }

    /**
     * Returns the input value x clamped to the range [min, max].
     */
    @CheckResult
    public static float clamp(float x, float min, float max) {
        if (x > max) return max;
        if (x < min) return min;
        return x;
    }

    /**
     * Returns the input value x clamped to the range [min, max].
     */
    @CheckResult
    public static double clamp(double x, double min, double max) {
        if (x > max) return max;
        if (x < min) return min;
        return x;
    }

    /**
     * Return true if n is pow of 2.
     */
    public static boolean pow2(int n) {
        return (n != 0) && ((n & (n - 1)) == 0);
    }

    /**
     * Returns the next power of two.
     * Returns the input if it is already power of 2.
     * Throws IllegalArgumentException if the input is <= 0 or
     * the answer overflows.
     */
    @CheckResult
    public static int nextPow2(int n) {
        if (n <= 0 || n > (1 << 30)) throw new IllegalArgumentException("n is invalid: " + n);
        n -= 1;
        n |= n >> 16;
        n |= n >> 8;
        n |= n >> 4;
        n |= n >> 2;
        n |= n >> 1;
        return n + 1;
    }

    /**
     * Returns the next power of two.
     * Returns the input if it is already power of 2.
     * Throws IllegalArgumentException if the input is <= 0 or
     * the answer overflows.
     */
    @CheckResult
    public static long nextPow2(long n) {
        if (n <= 0L || n > (1L << 62)) throw new IllegalArgumentException("n is invalid: " + n);
        n -= 1L;
        n |= n >> 32;
        n |= n >> 16;
        n |= n >> 8;
        n |= n >> 4;
        n |= n >> 2;
        n |= n >> 1;
        return n + 1L;
    }

    /**
     * Returns the previous power of two.
     * Returns the input if it is already power of 2.
     * Throws IllegalArgumentException if the input is <= 0.
     */
    @CheckResult
    public static int prevPow2(int n) {
        if (n <= 0) throw new IllegalArgumentException("n is invalid: " + n);
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return n - (n >> 1);
    }

    /**
     * Returns the previous power of two.
     * Returns the input if it is already power of 2.
     * Throws IllegalArgumentException if the input is <= 0.
     */
    @CheckResult
    public static long prevPow2(long n) {
        if (n <= 0L) throw new IllegalArgumentException("n is invalid: " + n);
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n |= n >> 32;
        return n - (n >> 1);
    }

    /**
     * Returns the largest (closest to positive infinity)
     * {@code int} value that is less than or equal to the algebraic quotient.
     */
    @CheckResult
    public static int floorDiv(int x, int y) {
        int r = x / y;
        // if the signs are different and modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }

    /**
     * Returns the largest (closest to positive infinity)
     * {@code long} value that is less than or equal to the algebraic quotient.
     */
    @CheckResult
    public static long floorDiv(long x, long y) {
        long r = x / y;
        // if the signs are different and modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }

    /**
     * Returns the floor modulus of the {@code int} arguments.
     * <p>
     * The relationship between {@code floorDiv} and {@code floorMod} is such that:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     */
    @CheckResult
    public static int floorMod(int x, int y) {
        return x - floorDiv(x, y) * y;
    }

    /**
     * Returns the floor modulus of the {@code long} arguments.
     * <p>
     * The relationship between {@code floorDiv} and {@code floorMod} is such that:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     */
    @CheckResult
    public static long floorMod(long x, long y) {
        return x - floorDiv(x, y) * y;
    }

    /**
     * Returns the smallest (closest to positive infinity)
     * {@code int} value that is greater than or equal to the algebraic quotient.
     */
    @CheckResult
    public static int ceilDiv(int x, int y) {
        return -floorDiv(-x, y);
    }

    /**
     * Returns the smallest (closest to positive infinity)
     * {@code long} value that is greater than or equal to the algebraic quotient.
     */
    @CheckResult
    public static long ceilDiv(long x, long y) {
        return -floorDiv(-x, y);
    }

    /**
     * Returns the ceil modulus of the {@code int} arguments.
     * <p>
     * The relationship between {@code ceilDiv} and {@code ceilMod} is such that:
     * <ul>
     *   <li>{@code ceilDiv(x, y) * y + ceilMod(x, y) == x}
     * </ul>
     */
    @CheckResult
    public static int ceilMod(int x, int y) {
        return x - ceilDiv(x, y) * y;
    }

    /**
     * Returns the ceil modulus of the {@code long} arguments.
     * <p>
     * The relationship between {@code ceilDiv} and {@code ceilMod} is such that:
     * <ul>
     *   <li>{@code ceilDiv(x, y) * y + ceilMod(x, y) == x}
     * </ul>
     */
    @CheckResult
    public static long ceilMod(long x, long y) {
        return x - ceilDiv(x, y) * y;
    }

    /**
     * Get coverage radius of a area
     *
     * @param w the width of the area
     * @param h the height of the area
     * @param x the x of point in area
     * @param y the y of point in area
     * @return the radius
     */
    @CheckResult
    public static float coverRadius(float w, float h, float x, float y) {
        float x2 = (x > w / 2) ? 0 : w;
        float y2 = (y > h / 2) ? 0 : h;
        return dist(x, y, x2, y2);
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [0, howbig).
     * Throws IllegalArgumentException if howbig <= 0.
     */
    @CheckResult
    public static int random(int howbig) {
        return sRandom.nextInt(howbig);
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [howsmall, howbig).
     * Throws IllegalArgumentException if howbig <= howsmall.
     */
    @CheckResult
    public static int random(int howsmall, int howbig) {
        final int dist = howbig - howsmall;
        if (dist <= 0) {
            throw new IllegalArgumentException("howbig <= howsmall: " + howbig + " <= " + howsmall);
        }
        return howsmall + sRandom.nextInt(dist);
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [0, howbig).
     * Throws IllegalArgumentException if howbig <= 0.
     */
    @CheckResult
    public static long random(long howbig) {
        if (howbig <= 0) {
            throw new IllegalArgumentException("howbig <= 0: " + howbig);
        }
        long bits, val;
        do {
            bits = (sRandom.nextLong() << 1) >>> 1;
            val = bits % howbig;
        } while (bits - val + (howbig - 1L) < 0L);
        return val;
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [howsmall, howbig).
     * Throws IllegalArgumentException if howbig <= howsmall.
     */
    @CheckResult
    public static long random(long howsmall, long howbig) {
        final long dist = howbig - howsmall;
        if (dist <= 0) {
            throw new IllegalArgumentException("howbig <= howsmall: " + howbig + " <= " + howsmall);
        }
        return howsmall + random(dist);
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [0, howbig).
     * Throws IllegalArgumentException if howbig <= 0.
     */
    @CheckResult
    public static float random(float howbig) {
        if (howbig <= 0.0f) {
            throw new IllegalArgumentException("howbig <= 0.0f: " + howbig);
        }
        return sRandom.nextFloat() * howbig;
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [howsmall, howbig).
     * Throws IllegalArgumentException if howbig <= howsmall.
     */
    @CheckResult
    public static float random(float howsmall, float howbig) {
        final float dist = howbig - howsmall;
        if (dist <= 0.0f) {
            throw new IllegalArgumentException("howbig <= howsmall: " + howbig + " <= " + howsmall);
        }
        return howsmall + random(dist);
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [0, howbig).
     * Throws IllegalArgumentException if howbig <= 0.
     */
    @CheckResult
    public static double random(double howbig) {
        if (howbig <= 0.0) {
            throw new IllegalArgumentException("howbig <= 0.0: " + howbig);
        }
        return sRandom.nextDouble() * howbig;
    }

    /**
     * Returns a pseudo-random uniformly distributed {@code int}
     * in the half-open range [howsmall, howbig).
     * Throws IllegalArgumentException if howbig <= howsmall.
     */
    @CheckResult
    public static double random(double howsmall, double howbig) {
        final double dist = howbig - howsmall;
        if (dist <= 0.0) {
            throw new IllegalArgumentException("howbig <= howsmall: " + howbig + " <= " + howsmall);
        }
        return howsmall + random(dist);
    }

    /**
     * Just {@link Random#setSeed(long)}.
     */
    public static void randomSeed(long seed) {
        sRandom.setSeed(seed);
    }
}
