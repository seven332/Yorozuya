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
 * Created by Hippo on 1/20/2017.
 */

import java.lang.reflect.Array;

public final class ArrayUtils {
  private ArrayUtils() {}

  /**
   * An empty immutable {@code Object} array.
   */
  public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
  /**
   * An empty immutable {@code Class} array.
   */
  public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
  /**
   * An empty immutable {@code String} array.
   */
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  /**
   * An empty immutable {@code long} array.
   */
  public static final long[] EMPTY_LONG_ARRAY = new long[0];
  /**
   * An empty immutable {@code Long} array.
   */
  public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
  /**
   * An empty immutable {@code int} array.
   */
  public static final int[] EMPTY_INT_ARRAY = new int[0];
  /**
   * An empty immutable {@code Integer} array.
   */
  public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
  /**
   * An empty immutable {@code short} array.
   */
  public static final short[] EMPTY_SHORT_ARRAY = new short[0];
  /**
   * An empty immutable {@code Short} array.
   */
  public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
  /**
   * An empty immutable {@code byte} array.
   */
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  /**
   * An empty immutable {@code Byte} array.
   */
  public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
  /**
   * An empty immutable {@code double} array.
   */
  public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
  /**
   * An empty immutable {@code Double} array.
   */
  public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
  /**
   * An empty immutable {@code float} array.
   */
  public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
  /**
   * An empty immutable {@code Float} array.
   */
  public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
  /**
   * An empty immutable {@code boolean} array.
   */
  public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
  /**
   * An empty immutable {@code Boolean} array.
   */
  public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
  /**
   * An empty immutable {@code char} array.
   */
  public static final char[] EMPTY_CHAR_ARRAY = new char[0];
  /**
   * An empty immutable {@code Character} array.
   */
  public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

  // Length
  //-----------------------------------------------------------------------
  /**
   * <p>Returns the length of the specified array.
   * This method can deal with {@code Object} arrays and with primitive arrays.
   *
   * <p>If the input array is {@code null}, {@code 0} is returned.
   *
   * <pre>
   * ArrayUtils.getLength(null)            = 0
   * ArrayUtils.getLength([])              = 0
   * ArrayUtils.getLength([null])          = 1
   * ArrayUtils.getLength([true, false])   = 2
   * ArrayUtils.getLength([1, 2, 3])       = 3
   * ArrayUtils.getLength(["a", "b", "c"]) = 3
   * </pre>
   *
   * @param array  the array to retrieve the length from, may be null
   * @return The length of the array, or {@code 0} if the array is {@code null}
   * @throws IllegalArgumentException if the object argument is not an array.
   */
  public static int getLength(final Object array) {
    if (array == null) {
      return 0;
    }
    return Array.getLength(array);
  }

  // Empty
  // ----------------------------------------------------------------------
  /**
   * <p>Checks if an array of Objects is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final Object[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive longs is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final long[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive ints is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final int[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive shorts is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final short[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive chars is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final char[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive bytes is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final byte[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive doubles is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final double[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive floats is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final float[] array) {
    return getLength(array) == 0;
  }

  /**
   * <p>Checks if an array of primitive booleans is empty or {@code null}.
   *
   * @param array  the array to test
   * @return {@code true} if the array is empty or {@code null}
   */
  public static boolean isEmpty(final boolean[] array) {
    return getLength(array) == 0;
  }
}
