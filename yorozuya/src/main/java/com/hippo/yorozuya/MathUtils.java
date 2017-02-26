/*
 * Copyright 2017 Hippo Seven
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

/*
 * Created by Hippo on 1/21/2017.
 */

import java.util.Random;

public final class MathUtils {
  private MathUtils() {}

  private static final float DEG_TO_RAD_FLOAT = (float) Math.PI / 180.0f;
  private static final float RAD_TO_DEG_FLOAT = 180.0f / (float) Math.PI;
  private static final double DEG_TO_RAD_DOUBLE = Math.PI / 180.0;
  private static final double RAD_TO_DEG_DOUBLE = 180.0 / Math.PI;

  private static final Random random = new Random();

  /**
   * Returns {@code true} if two floats are equal to within a positive delta.
   */
  public static boolean equals(float f1, float f2, float delta) {
    return Float.compare(f1, f2) == 0 || Math.abs(f1 - f2) <= delta;
  }

  /**
   * Returns {@code true} if two doubles are equal to within a positive delta.
   */
  public static boolean equals(double d1, double d2, double delta) {
    return Double.compare(d1, d2) == 0 || Math.abs(d1 - d2) <= delta;
  }

  /**
   * Returns the most positive (closest to positive infinity) of all the
   * arguments.
   *
   * @param args all the arguments
   * @return the largest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static int max(int... args) throws IllegalArgumentException, NullPointerException {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get max value from an empty array");
    }
    int max = args[0];
    for (int i = 1; i < length; i++) {
      max = Math.max(max, args[i]);
    }
    return max;
  }

  /**
   * Returns the most positive (closest to positive infinity) of all the
   * arguments.
   *
   * @param args all the arguments
   * @return the largest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static long max(long... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get max value from an empty array");
    }
    long max = args[0];
    for (int i = 1; i < length; i++) {
      max = Math.max(max, args[i]);
    }
    return max;
  }

  /**
   * Returns the most positive (closest to positive infinity) of all the
   * arguments.
   * <p>
   * Special cases are the same as {@link Math#max(float, float)}.
   *
   * @param args all the arguments
   * @return the largest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static float max(float... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get max value from an empty array");
    }
    float max = args[0];
    for (int i = 1; i < length; i++) {
      max = Math.max(max, args[i]);
    }
    return max;
  }

  /**
   * Returns the most positive (closest to positive infinity) of all the
   * arguments.
   * <p>
   * Special cases are the same as {@link Math#max(double, double)}.
   *
   * @param args all the arguments
   * @return the largest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static double max(double... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get max value from an empty array");
    }
    double max = args[0];
    for (int i = 1; i < length; i++) {
      max = Math.max(max, args[i]);
    }
    return max;
  }

  /**
   * Returns the most negative (closest to negative infinity) of all the
   * arguments.
   *
   * @param args all the arguments
   * @return the smallest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static int min(int... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get min value from an empty array");
    }
    int min = args[0];
    for (int i = 1; i < length; i++) {
      min = Math.min(min, args[i]);
    }
    return min;
  }

  /**
   * Returns the most negative (closest to negative infinity) of all the
   * arguments.
   *
   * @param args all the arguments
   * @return the smallest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static long min(long... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get min value from an empty array");
    }
    long min = args[0];
    for (int i = 1; i < length; i++) {
      min = Math.min(min, args[i]);
    }
    return min;
  }

  /**
   * Returns the most negative (closest to negative infinity) of all the
   * arguments.
   * <p>
   * Special cases are the same as {@link Math#max(float, float)}.
   *
   * @param args all the arguments
   * @return the smallest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static float min(float... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get min value from an empty array");
    }
    float min = args[0];
    for (int i = 1; i < length; i++) {
      min = Math.min(min, args[i]);
    }
    return min;
  }

  /**
   * Returns the most negative (closest to negative infinity) of all the
   * arguments.
   * <p>
   * Special cases are the same as {@link Math#max(double, double)}.
   *
   * @param args all the arguments
   * @return the smallest of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static double min(double... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get min value from an empty array");
    }
    double min = args[0];
    for (int i = 1; i < length; i++) {
      min = Math.min(min, args[i]);
    }
    return min;
  }

  /**
   * Calculates the average of the all arguments.
   *
   * @param args all the arguments
   * @return the average of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static int average(int... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get average from empty array");
    }

    int x = 0, y = 0;
    for (int n: args) {
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
   * Calculates the average of the all arguments.
   *
   * @param args all the arguments
   * @return the average of all the arguments
   * @throws IllegalArgumentException if the arguments is empty
   * @throws NullPointerException if the arguments is null
   */
  public static long average(long... args) {
    if (args == null) throw new NullPointerException("args == null");
    final int length = args.length;
    if (length <= 0) {
      throw new IllegalArgumentException("Can't get average from empty array");
    }

    long x = 0, y = 0;
    for (long n: args) {
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
   * Returns distance between two points.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @return the distance
   */
  public static float dist(float x1, float y1, float x2, float y2) {
    return (float) Math.hypot(x2 - x1, y2 - y1);
  }

  /**
   * Returns distance between two point.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @return the distance
   */
  public static double dist(double x1, double y1, double x2, double y2) {
    return Math.hypot(x2 - x1, y2 - y1);
  }

  /**
   * Returns distance between two points.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param z1 The z-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param z2 The z-coordinate of the second point
   * @return the distance
   */
  public static float dist(float x1, float y1, float z1, float x2, float y2, float z2) {
    final float x = (x2 - x1);
    final float y = (y2 - y1);
    final float z = (z2 - z1);
    return (float) Math.sqrt(x * x + y * y + z * z);
  }

  /**
   * Returns distance between two points.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param z1 The z-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param z2 The z-coordinate of the second point
   * @return the distance
   */
  public static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
    final double x = (x2 - x1);
    final double y = (y2 - y1);
    final double z = (z2 - z1);
    return Math.sqrt(x * x + y * y + z * z);
  }

  /**
   * Returns whether the two points are close.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param slop the max distance to judge near
   * @return {@code true} if the two points are close
   */
  public static boolean near(float x1, float y1, float x2, float y2, float slop) {
    return dist(x1, y1, x2, y2) <= slop;
  }

  /**
   * Returns whether the two points are close.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param slop the max distance to judge near
   * @return {@code true} if the two points are close
   */
  public static boolean near(double x1, double y1, double x2, double y2, double slop) {
    return dist(x1, y1, x2, y2) <= slop;
  }

  /**
   * Returns whether the two points are close.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param z1 The z-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param z2 The z-coordinate of the second point
   * @param slop the max distance to judge near
   * @return {@code true} if the two points are close
   */
  public static boolean near(float x1, float y1, float z1, float x2, float y2, float z2, float slop) {
    return dist(x1, y1, z1, x2, y2, z2) <= slop;
  }

  /**
   * Returns whether the two points are close.
   *
   * @param x1 The x-coordinate of the first point
   * @param y1 The y-coordinate of the first point
   * @param z1 The z-coordinate of the first point
   * @param x2 The x-coordinate of the second point
   * @param y2 The y-coordinate of the second point
   * @param z2 The z-coordinate of the second point
   * @param slop the max distance to judge near
   * @return {@code true} if the two points are close
   */
  public static boolean near(double x1, double y1, double z1, double x2, double y2, double z2, double slop) {
    return dist(x1, y1, z1, x2, y2, z2) <= slop;
  }

  /**
   * Convert degrees to radians.
   *
   * @param degrees the degrees to convert
   * @return the result radians
   */
  public static float radians(float degrees) {
    return degrees * DEG_TO_RAD_FLOAT;
  }

  /**
   * Convert degrees to radians.
   *
   * @param degrees the degrees to convert
   * @return the result radians
   */
  public static double radians(double degrees) {
    return degrees * DEG_TO_RAD_DOUBLE;
  }

  /**
   * Convert radians to degrees.
   *
   * @param radians the radians to convert
   * @return the result degrees
   */
  public static float degrees(float radians) {
    return radians * RAD_TO_DEG_FLOAT;
  }

  /**
   * Convert radians to degrees.
   *
   * @param radians the radians to convert
   * @return the result degrees
   */
  public static double degrees(double radians) {
    return radians * RAD_TO_DEG_DOUBLE;
  }

  /**
   * Calculates a linear interpolation between two inputs for a parameter.
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param amount the parameter
   * @return the result
   */
  public static int lerp(int start, int stop, float amount) {
    return (int) ((1 - amount) * start) + (int) (amount * stop);
  }

  /**
   * Calculates a linear interpolation between two inputs for a parameter.
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param amount the parameter
   * @return the result
   */
  public static long lerp(long start, long stop, float amount) {
    return (long) ((1 - amount) * start) + (long) (amount * stop);
  }

  /**
   * Calculates a linear interpolation between two inputs for a parameter.
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param amount the parameter
   * @return the result
   */
  public static float lerp(float start, float stop, float amount) {
    return ((1 - amount) * start) + (amount * stop);
  }

  /**
   * Inverse of linear interpolation.
   * <p>
   * Special cases:
   * <ul>
   * <li>{@code norm(5, 5, 5) = 0.0f}</li>
   * <li>{@code norm(5, 5, 2) = Float.NaN}</li>
   * </ul>
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param value the linear interpolation value
   * @return the amount
   */
  public static float norm(int start, int stop, int value) {
    if (start == stop) {
      if (stop == value) {
        return 0.0f;
      } else {
        return Float.NaN;
      }
    } else {
      return (float) (value - start) / (float) (stop - start);
    }
  }

  /**
   * Inverse of linear interpolation.
   * <p>
   * Special cases:
   * <ul>
   * <li>{@code norm(5, 5, 5) = 0.0f}</li>
   * <li>{@code norm(5, 5, 2) = Float.NaN}</li>
   * </ul>
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param value the linear interpolation value
   * @return the amount
   */
  public static float norm(long start, long stop, long value) {
    if (start == stop) {
      if (stop == value) {
        return 0.0f;
      } else {
        return Float.NaN;
      }
    } else {
      return (float) (value - start) / (float) (stop - start);
    }
  }

  /**
   * Inverse of linear interpolation.
   * <p>
   * Special cases:
   * <ul>
   * <li>{@code norm(5, 5, 5) = 0.0f}</li>
   * <li>{@code norm(5, 5, 2) = Float.NaN}</li>
   * </ul>
   *
   * @param start first of the inputs
   * @param stop second of the inputs
   * @param value the linear interpolation value
   * @return the amount
   */
  public static float norm(float start, float stop, float value) {
    if (stop == start) {
      if (stop == value) {
        return 0.0f;
      } else {
        return Float.NaN;
      }
    } else {
      return (value - start) / (stop - start);
    }
  }

  /**
   * Returns the input value x clamped to the range [bound1, bound2] if bound2 &gt;= bound1,
   * otherwise [bound2, bound1].
   *
   * @param x the input
   * @param bound1 the first bound
   * @param bound2 the second bound
   * @return the result which has been clamped
   */
  public static int clamp(int x, int bound1, int bound2) {
    if (bound2 >= bound1) {
      if (x > bound2) return bound2;
      if (x < bound1) return bound1;
    } else {
      if (x > bound1) return bound1;
      if (x < bound2) return bound2;
    }
    return x;
  }

  /**
   * Returns the input value x clamped to the range [bound1, bound2] if bound2 &gt;= bound1,
   * otherwise [bound2, bound1].
   *
   * @param x the input
   * @param bound1 the first bound
   * @param bound2 the second bound
   * @return the result which has been clamped
   */
  public static long clamp(long x, long bound1, long bound2) {
    if (bound2 >= bound1) {
      if (x > bound2) return bound2;
      if (x < bound1) return bound1;
    } else {
      if (x > bound1) return bound1;
      if (x < bound2) return bound2;
    }
    return x;
  }

  /**
   * Returns the input value x clamped to the range [bound1, bound2] if bound2 &gt;= bound1,
   * otherwise [bound2, bound1].
   *
   * @param x the input
   * @param bound1 the first bound
   * @param bound2 the second bound
   * @return the result which has been clamped
   */
  public static float clamp(float x, float bound1, float bound2) {
    if (bound2 >= bound1) {
      if (x > bound2) return bound2;
      if (x < bound1) return bound1;
    } else {
      if (x > bound1) return bound1;
      if (x < bound2) return bound2;
    }
    return x;
  }

  /**
   * Returns the input value x clamped to the range [bound1, bound2] if bound2 &gt;= bound1,
   * otherwise [bound2, bound1].
   *
   * @param x the input
   * @param bound1 the first bound
   * @param bound2 the second bound
   * @return the result which has been clamped
   */
  public static double clamp(double x, double bound1, double bound2) {
    if (bound2 >= bound1) {
      if (x > bound2) return bound2;
      if (x < bound1) return bound1;
    } else {
      if (x > bound1) return bound1;
      if (x < bound2) return bound2;
    }
    return x;
  }

  /**
   * Indicted whether {@code n} is power of 2. n is treated as unsigned int.
   *
   * @param n the input
   * @return {@code true} if {@code n} is power of 2.
   */
  public static boolean pow2(int n) {
    return (n != 0) && ((n & (n - 1)) == 0);
  }

  /**
   * Returns the result which is the smallest (closest to zero)
   * {@code int} value that is greater than or equal to the input
   * and is power of 2. n is treated as unsigned int.
   *
   * @param n the input
   * @return the result
   */
  public static int nextPow2(int n) {
    if (n == 0) return 1;
    n -= 1;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    return n + 1;
  }

  /**
   * Returns the result which is the smallest (closest to zero)
   * {@code long} value that is greater than or equal to the input
   * and is power of 2. n is treated as unsigned int.
   *
   * @param n the input
   * @return the result
   */
  public static long nextPow2(long n) {
    if (n == 0) return 1;
    n -= 1;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    n |= n >> 32;
    return n + 1;
  }

  /**
   * Returns the result which is the biggest (closest to positive infinity)
   * {@code long} value that is smaller than or equal to the input
   * and is power of 2. n is treated as unsigned int.
   * <p>
   * Return 0 if {@code n} is 0.
   *
   * @param n the input
   * @return the result
   */
  public static int prevPow2(int n) {
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    return n - (n >>> 1);
  }

  /**
   * Returns the result which is the biggest (closest to positive infinity)
   * {@code long} value that is smaller than or equal to the input
   * and is power of 2. n is treated as unsigned int.
   * <p>
   * Return 0 if {@code n} is 0.
   *
   * @param n the input
   * @return the result
   */
  public static long prevPow2(long n) {
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    n |= n >> 32;
    return n - (n >>> 1);
  }


  /**
   * Returns the largest (closest to positive infinity)
   * {@code int} value that is less than or equal to the algebraic quotient.
   *
   * @param x the dividend
   * @param y the divisor
   * @return the quotient
   */
  public static int floorDiv(int x, int y) {
    int r = x / y;
    // if the signs are different and modulo not zero, round down
    if ((x ^ y) < 0 && (r * y != x)) {
      --r;
    }
    return r;
  }

  /**
   * Returns the largest (closest to positive infinity)
   * {@code long} value that is less than or equal to the algebraic quotient.
   *
   * @param x the dividend
   * @param y the divisor
   * @return the quotient
   */
  public static long floorDiv(long x, long y) {
    long r = x / y;
    // if the signs are different and modulo not zero, round down
    if ((x ^ y) < 0 && (r * y != x)) {
      --r;
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
   *
   * @param x the dividend
   * @param y the divisor
   * @return the remainder
   */
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
   *
   * @param x the dividend
   * @param y the divisor
   * @return the remainder
   */
  public static long floorMod(long x, long y) {
    return x - floorDiv(x, y) * y;
  }

  /**
   * Returns the smallest (closest to positive infinity)
   * {@code int} value that is greater than or equal to the algebraic quotient.
   *
   * @param x the dividend
   * @param y the divisor
   * @return the quotient
   */
  public static int ceilDiv(int x, int y) {
    return -floorDiv(-x, y);
  }

  /**
   * Returns the smallest (closest to positive infinity)
   * {@code long} value that is greater than or equal to the algebraic quotient.
   *
   * @param x the dividend
   * @param y the divisor
   * @return the quotient
   */
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
   *
   * @param x the dividend
   * @param y the divisor
   * @return the remainder
   */
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
   *
   * @param x the dividend
   * @param y the divisor
   * @return the remainder
   */
  public static long ceilMod(long x, long y) {
    return x - ceilDiv(x, y) * y;
  }

  /**
   * Get coverage radius of a point for a area.
   *
   * @param w the width of the area
   * @param h the height of the area
   * @param x the x of point in area
   * @param y the y of point in area
   * @return the radius
   */
  public static float coverRadius(float w, float h, float x, float y) {
    final float x2 = (x > w / 2) ? 0 : w;
    final float y2 = (y > h / 2) ? 0 : h;
    return dist(x, y, x2, y2);
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code int}
   * in the half-open range [0, howbig).
   *
   * @param howbig the upper bound (exclusive), must be positive
   * @return a random {@code int}
   * @throws IllegalArgumentException if howbig &lt;= 0
   */
  public static int random(int howbig) throws IllegalArgumentException {
    return random.nextInt(howbig);
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code int}
   * in the half-open range [howsmall, howbig).
   *
   * @param howsmall the lower bound (inclusive)
   * @param howbig the upper bound (exclusive)
   * @return a random {@code int}
   * @throws IllegalArgumentException if howbig &lt;= howsmall
   */
  public static int random(int howsmall, int howbig) throws IllegalArgumentException {
    if (howsmall >= howbig) {
      throw new IllegalArgumentException("howsmall >= howbig: " + howsmall + " >= " + howbig);
    }
    int r = random.nextInt();
    int n = howbig - howsmall, m = n - 1;
    if ((n & m) == 0)
      r = (r & m) + howsmall;
    else if (n > 0) {
      for (int u = r >>> 1;
          u + m - (r = u % n) < 0;
          u = random.nextInt() >>> 1)
        ;
      r += howsmall;
    }
    else {
      while (r < howsmall || r >= howbig)
        r = random.nextInt();
    }
    return r;
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code long}
   * in the half-open range [0, howbig).
   *
   * @param howbig the upper bound (exclusive), must be positive
   * @return a random {@code long}
   * @throws IllegalArgumentException if howbig &lt;= 0
   */
  public static long random(long howbig) throws IllegalArgumentException {
    if (howbig <= 0) {
      throw new IllegalArgumentException("howbig <= 0: " + howbig);
    }
    long bits, val;
    do {
      bits = (random.nextLong() << 1) >>> 1;
      val = bits % howbig;
    } while (bits - val + (howbig - 1L) < 0L);
    return val;
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code long}
   * in the half-open range [howsmall, howbig).
   *
   * @param howsmall the lower bound (inclusive)
   * @param howbig the upper bound (exclusive)
   * @return a random {@code long}
   * @throws IllegalArgumentException if howbig &lt;= howsmall
   */
  public static long random(long howsmall, long howbig) throws IllegalArgumentException {
    if (howsmall >= howbig) {
      throw new IllegalArgumentException("howsmall >= howbig: " + howsmall + " >= " + howbig);
    }
    long r = random.nextLong();
    long n = howbig - howsmall, m = n - 1;
    if ((n & m) == 0L)  // power of two
      r = (r & m) + howsmall;
    else if (n > 0L) {  // reject over-represented candidates
      for (long u = r >>> 1;            // ensure nonnegative
          u + m - (r = u % n) < 0L;    // rejection check
          u = random.nextLong() >>> 1) // retry
        ;
      r += howsmall;
    }
    else {              // range not representable as long
      while (r < howsmall || r >= howbig)
        r = random.nextLong();
    }
    return r;
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code float}
   * in the half-open range [0, howbig).
   *
   * @param howbig the upper bound (exclusive), must be positive
   * @return a random {@code float}
   * @throws IllegalArgumentException if howbig &lt;= 0
   */
  public static float random(float howbig) throws IllegalArgumentException {
    return random(0.0f, howbig);
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code float}
   * in the half-open range [howsmall, howbig).
   *
   * @param howsmall the lower bound (inclusive)
   * @param howbig the upper bound (exclusive)
   * @return a random {@code float}
   * @throws IllegalArgumentException if howbig &lt;= howsmall
   */
  public static float random(float howsmall, float howbig) throws IllegalArgumentException {
    if (howsmall >= howbig) {
      throw new IllegalArgumentException("howsmall >= howbig: " + howsmall + " >= " + howbig);
    }
    float r = random.nextFloat();
    r = r * (howbig - howsmall) + howsmall;
    if (r >= howbig) // correct for rounding
      r = Float.intBitsToFloat(Float.floatToIntBits(howbig) - 1);
    return r;
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code double}
   * in the half-open range [0, howbig).
   *
   * @param howbig the upper bound (exclusive), must be positive
   * @return a random {@code double}
   * @throws IllegalArgumentException if howbig &lt;= 0
   */
  public static double random(double howbig) throws IllegalArgumentException {
    return random(0.0, howbig);
  }

  /**
   * Returns a pseudo-random uniformly distributed {@code double}
   * in the half-open range [howsmall, howbig).
   *
   * @param howsmall the lower bound (inclusive)
   * @param howbig the upper bound (exclusive)
   * @return a random {@code double}
   * @throws IllegalArgumentException if howbig &lt;= howsmall
   */
  public static double random(double howsmall, double howbig) throws IllegalArgumentException {
    if (howsmall >= howbig) {
      throw new IllegalArgumentException("howsmall >= howbig: " + howsmall + " >= " + howbig);
    }
    double r = random.nextDouble();
    r = r * (howbig - howsmall) + howsmall;
    if (r >= howbig) // correct for rounding
      r = Double.longBitsToDouble(Double.doubleToLongBits(howbig) - 1);
    return r;
  }

  /**
   * Sets the seed of this random number generator using a single
   * {@code long} seed.
   *
   * @param seed the initial seed
   * @see Random#setSeed(long)
   */
  public static void randomSeed(long seed) {
    random.setSeed(seed);
  }
}
