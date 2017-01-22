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
   * The index value when an element is not found in a list or array: {@code -1}.
   * This value is returned by methods in this class and can also be used in comparisons with values returned by
   * various method from {@link java.util.List}.
   */
  public static final int INDEX_NOT_FOUND = -1;

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

  // IndexOf search
  // ----------------------------------------------------------------------

  // Object IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given object in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param objectToFind  the object to find, may be {@code null}
   * @return the index of the object within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final Object[] array, final Object objectToFind) {
    return indexOf(array, objectToFind, 0);
  }

  /**
   * <p>Finds the index of the given object in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param objectToFind  the object to find, may be {@code null}
   * @param startIndex  the index to start searching at
   * @return the index of the object within the array starting at the index,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    if (objectToFind == null) {
      for (int i = startIndex; i < array.length; i++) {
        if (array[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = startIndex; i < array.length; i++) {
        if (objectToFind.equals(array[i])) {
          return i;
        }
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given object within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param objectToFind  the object to find, may be {@code null}
   * @return the last index of the object within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final Object[] array, final Object objectToFind) {
    return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given object in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than
   * the array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param objectToFind  the object to find, may be {@code null}
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the object within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final Object[] array, final Object objectToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    if (objectToFind == null) {
      for (int i = startIndex; i >= 0; i--) {
        if (array[i] == null) {
          return i;
        }
      }
    } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
      for (int i = startIndex; i >= 0; i--) {
        if (objectToFind.equals(array[i])) {
          return i;
        }
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the object is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param objectToFind  the object to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final Object[] array, final Object objectToFind) {
    return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
  }

  // long IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final long[] array, final long valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final long[] array, final long valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final long[] array, final long valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final long[] array, final long valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final long[] array, final long valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // int IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final int[] array, final int valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final int[] array, final int valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final int[] array, final int valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final int[] array, final int valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // short IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final short[] array, final short valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final short[] array, final short valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final short[] array, final short valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final short[] array, final short valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final short[] array, final short valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // char IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   * @since 2.1
   */
  public static int indexOf(final char[] array, final char valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   * @since 2.1
   */
  public static int indexOf(final char[] array, final char valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   * @since 2.1
   */
  public static int lastIndexOf(final char[] array, final char valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   * @since 2.1
   */
  public static int lastIndexOf(final char[] array, final char valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   * @since 2.1
   */
  public static boolean contains(final char[] array, final char valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // byte IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final byte[] array, final byte valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final byte[] array, final byte valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final byte[] array, final byte valueToFind, int startIndex) {
    if (array == null) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final byte[] array, final byte valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // double IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final double[] array, final double valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value within a given tolerance in the array.
   * This method will return the index of the first value which falls between the region
   * defined by valueToFind - tolerance and valueToFind + tolerance.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param tolerance tolerance of the search
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final double[] array, final double valueToFind, final double tolerance) {
    return indexOf(array, valueToFind, 0, tolerance);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final double[] array, final double valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   * This method will return the index of the first value which falls between the region
   * defined by valueToFind - tolerance and valueToFind + tolerance.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @param tolerance tolerance of the search
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    final double min = valueToFind - tolerance;
    final double max = valueToFind + tolerance;
    for (int i = startIndex; i < array.length; i++) {
      if (array[i] >= min && array[i] <= max) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final double[] array, final double valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value within a given tolerance in the array.
   * This method will return the index of the last value which falls between the region
   * defined by valueToFind - tolerance and valueToFind + tolerance.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param tolerance tolerance of the search
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final double[] array, final double valueToFind, final double tolerance) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   * This method will return the index of the last value which falls between the region
   * defined by valueToFind - tolerance and valueToFind + tolerance.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @param tolerance  search for value within plus/minus this amount
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    final double min = valueToFind - tolerance;
    final double max = valueToFind + tolerance;
    for (int i = startIndex; i >= 0; i--) {
      if (array[i] >= min && array[i] <= max) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final double[] array, final double valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if a value falling within the given tolerance is in the
   * given array.  If the array contains a value within the inclusive range
   * defined by (value - tolerance) to (value + tolerance).
   *
   * <p>The method returns {@code false} if a {@code null} array
   * is passed in.
   *
   * @param array  the array to search
   * @param valueToFind  the value to find
   * @param tolerance  the array contains the tolerance of the search
   * @return true if value falling within tolerance is in array
   */
  public static boolean contains(final double[] array, final double valueToFind, final double tolerance) {
    return indexOf(array, valueToFind, 0, tolerance) != INDEX_NOT_FOUND;
  }

  // float IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final float[] array, final float valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final float[] array, final float valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final float[] array, final float valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than the
   * array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final float[] array, final float valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final float[] array, final float valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }

  // boolean IndexOf
  //-----------------------------------------------------------------------
  /**
   * <p>Finds the index of the given value in the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int indexOf(final boolean[] array, final boolean valueToFind) {
    return indexOf(array, valueToFind, 0);
  }

  /**
   * <p>Finds the index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex is treated as zero. A startIndex larger than the array
   * length will return {@link #INDEX_NOT_FOUND} ({@code -1}).
   *
   * @param array  the array to search through for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the index to start searching at
   * @return the index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null}
   *  array input
   */
  public static int indexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      startIndex = 0;
    }
    for (int i = startIndex; i < array.length; i++) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Finds the last index of the given value within the array.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) if
   * {@code null} array input.
   *
   * @param array  the array to travers backwords looking for the object, may be {@code null}
   * @param valueToFind  the object to find
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final boolean[] array, final boolean valueToFind) {
    return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
  }

  /**
   * <p>Finds the last index of the given value in the array starting at the given index.
   *
   * <p>This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
   *
   * <p>A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A startIndex larger than
   * the array length will search from the end of the array.
   *
   * @param array  the array to traverse for looking for the object, may be {@code null}
   * @param valueToFind  the value to find
   * @param startIndex  the start index to travers backwards from
   * @return the last index of the value within the array,
   *  {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null} array input
   */
  public static int lastIndexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
    if (ArrayUtils.isEmpty(array)) {
      return INDEX_NOT_FOUND;
    }
    if (startIndex < 0) {
      return INDEX_NOT_FOUND;
    } else if (startIndex >= array.length) {
      startIndex = array.length - 1;
    }
    for (int i = startIndex; i >= 0; i--) {
      if (valueToFind == array[i]) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  /**
   * <p>Checks if the value is in the given array.
   *
   * <p>The method returns {@code false} if a {@code null} array is passed in.
   *
   * @param array  the array to search through
   * @param valueToFind  the value to find
   * @return {@code true} if the array contains the object
   */
  public static boolean contains(final boolean[] array, final boolean valueToFind) {
    return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
  }
}
