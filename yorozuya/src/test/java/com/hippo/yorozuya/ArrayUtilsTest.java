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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ArrayUtilsTest {

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOf() {
    final Object[] array = new Object[]{"0", "1", "2", "3", null, "0"};
    assertEquals(-1, ArrayUtils.indexOf(null, null));
    assertEquals(-1, ArrayUtils.indexOf(null, "0"));
    assertEquals(-1, ArrayUtils.indexOf(new Object[0], "0"));
    assertEquals(0, ArrayUtils.indexOf(array, "0"));
    assertEquals(1, ArrayUtils.indexOf(array, "1"));
    assertEquals(2, ArrayUtils.indexOf(array, "2"));
    assertEquals(3, ArrayUtils.indexOf(array, "3"));
    assertEquals(4, ArrayUtils.indexOf(array, null));
    assertEquals(-1, ArrayUtils.indexOf(array, "notInArray"));
  }

  @Test
  public void testIndexOfWithStartIndex() {
    final Object[] array = new Object[]{"0", "1", "2", "3", null, "0"};
    assertEquals(-1, ArrayUtils.indexOf(null, null, 2));
    assertEquals(-1, ArrayUtils.indexOf(new Object[0], "0", 0));
    assertEquals(-1, ArrayUtils.indexOf(null, "0", 2));
    assertEquals(5, ArrayUtils.indexOf(array, "0", 2));
    assertEquals(-1, ArrayUtils.indexOf(array, "1", 2));
    assertEquals(2, ArrayUtils.indexOf(array, "2", 2));
    assertEquals(3, ArrayUtils.indexOf(array, "3", 2));
    assertEquals(4, ArrayUtils.indexOf(array, null, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, "notInArray", 2));

    assertEquals(4, ArrayUtils.indexOf(array, null, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, null, 8));
    assertEquals(-1, ArrayUtils.indexOf(array, "0", 8));
  }

  @Test
  public void testLastIndexOf() {
    final Object[] array = new Object[]{"0", "1", "2", "3", null, "0"};
    assertEquals(-1, ArrayUtils.lastIndexOf(null, null));
    assertEquals(-1, ArrayUtils.lastIndexOf(null, "0"));
    assertEquals(5, ArrayUtils.lastIndexOf(array, "0"));
    assertEquals(1, ArrayUtils.lastIndexOf(array, "1"));
    assertEquals(2, ArrayUtils.lastIndexOf(array, "2"));
    assertEquals(3, ArrayUtils.lastIndexOf(array, "3"));
    assertEquals(4, ArrayUtils.lastIndexOf(array, null));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, "notInArray"));
  }

  @Test
  public void testLastIndexOfWithStartIndex() {
    final Object[] array = new Object[]{"0", "1", "2", "3", null, "0"};
    assertEquals(-1, ArrayUtils.lastIndexOf(null, null, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(null, "0", 2));
    assertEquals(0, ArrayUtils.lastIndexOf(array, "0", 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, "1", 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, "2", 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, "3", 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, "3", -1));
    assertEquals(4, ArrayUtils.lastIndexOf(array, null, 5));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, null, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, "notInArray", 5));

    assertEquals(-1, ArrayUtils.lastIndexOf(array, null, -1));
    assertEquals(5, ArrayUtils.lastIndexOf(array, "0", 88));
  }

  @Test
  public void testContains() {
    final Object[] array = new Object[]{"0", "1", "2", "3", null, "0"};
    assertFalse(ArrayUtils.contains(null, null));
    assertFalse(ArrayUtils.contains(null, "1"));
    assertTrue(ArrayUtils.contains(array, "0"));
    assertTrue(ArrayUtils.contains(array, "1"));
    assertTrue(ArrayUtils.contains(array, "2"));
    assertTrue(ArrayUtils.contains(array, "3"));
    assertTrue(ArrayUtils.contains(array, null));
    assertFalse(ArrayUtils.contains(array, "notInArray"));
  }

  @Test
  public void testContains_LANG_1261() {
    class LANG1261ParentObject {

      @Override
      public boolean equals(Object o) {
        return true;
      }
    }
    class LANG1261ChildObject extends LANG1261ParentObject {

    }

    Object[] array = new LANG1261ChildObject[]{new LANG1261ChildObject()};

    assertTrue(ArrayUtils.contains(array, new LANG1261ParentObject()));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfLong() {
    long[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 0));
    array = new long[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, 0));
    assertEquals(1, ArrayUtils.indexOf(array, 1));
    assertEquals(2, ArrayUtils.indexOf(array, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3));
    assertEquals(-1, ArrayUtils.indexOf(array, 99));
  }

  @Test
  public void testIndexOfLongWithStartIndex() {
    long[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 0, 2));
    array = new long[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, 0, 6));
  }

  @Test
  public void testLastIndexOfLong() {
    long[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 0));
    array = new long[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
  }

  @Test
  public void testLastIndexOfLongWithStartIndex() {
    long[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 0, 2));
    array = new long[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 99, 4));
    assertEquals(4, ArrayUtils.lastIndexOf(array, 0, 88));
  }

  @Test
  public void testContainsLong() {
    long[] array = null;
    assertFalse(ArrayUtils.contains(array, 1));
    array = new long[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, 0));
    assertTrue(ArrayUtils.contains(array, 1));
    assertTrue(ArrayUtils.contains(array, 2));
    assertTrue(ArrayUtils.contains(array, 3));
    assertFalse(ArrayUtils.contains(array, 99));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfInt() {
    int[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 0));
    array = new int[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, 0));
    assertEquals(1, ArrayUtils.indexOf(array, 1));
    assertEquals(2, ArrayUtils.indexOf(array, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3));
    assertEquals(-1, ArrayUtils.indexOf(array, 99));
  }

  @Test
  public void testIndexOfIntWithStartIndex() {
    int[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 0, 2));
    array = new int[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3, 2));
    assertEquals(3, ArrayUtils.indexOf(array, 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, 0, 6));
  }

  @Test
  public void testLastIndexOfInt() {
    int[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 0));
    array = new int[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
  }

  @Test
  public void testLastIndexOfIntWithStartIndex() {
    int[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 0, 2));
    array = new int[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
    assertEquals(4, ArrayUtils.lastIndexOf(array, 0, 88));
  }

  @Test
  public void testContainsInt() {
    int[] array = null;
    assertFalse(ArrayUtils.contains(array, 1));
    array = new int[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, 0));
    assertTrue(ArrayUtils.contains(array, 1));
    assertTrue(ArrayUtils.contains(array, 2));
    assertTrue(ArrayUtils.contains(array, 3));
    assertFalse(ArrayUtils.contains(array, 99));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfShort() {
    short[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 0));
    array = new short[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, (short) 0));
    assertEquals(1, ArrayUtils.indexOf(array, (short) 1));
    assertEquals(2, ArrayUtils.indexOf(array, (short) 2));
    assertEquals(3, ArrayUtils.indexOf(array, (short) 3));
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 99));
  }

  @Test
  public void testIndexOfShortWithStartIndex() {
    short[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 0, 2));
    array = new short[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, (short) 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, (short) 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (short) 3, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (short) 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, (short) 0, 6));
  }

  @Test
  public void testLastIndexOfShort() {
    short[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 0));
    array = new short[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (short) 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (short) 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (short) 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (short) 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 99));
  }

  @Test
  public void testLastIndexOfShortWithStartIndex() {
    short[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 0, 2));
    array = new short[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, (short) 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (short) 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (short) 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 99));
    assertEquals(4, ArrayUtils.lastIndexOf(array, (short) 0, 88));
  }

  @Test
  public void testContainsShort() {
    short[] array = null;
    assertFalse(ArrayUtils.contains(array, (short) 1));
    array = new short[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, (short) 0));
    assertTrue(ArrayUtils.contains(array, (short) 1));
    assertTrue(ArrayUtils.contains(array, (short) 2));
    assertTrue(ArrayUtils.contains(array, (short) 3));
    assertFalse(ArrayUtils.contains(array, (short) 99));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfChar() {
    char[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 'a'));
    array = new char[]{'a', 'b', 'c', 'd', 'a'};
    assertEquals(0, ArrayUtils.indexOf(array, 'a'));
    assertEquals(1, ArrayUtils.indexOf(array, 'b'));
    assertEquals(2, ArrayUtils.indexOf(array, 'c'));
    assertEquals(3, ArrayUtils.indexOf(array, 'd'));
    assertEquals(-1, ArrayUtils.indexOf(array, 'e'));
  }

  @Test
  public void testIndexOfCharWithStartIndex() {
    char[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, 'a', 2));
    array = new char[]{'a', 'b', 'c', 'd', 'a'};
    assertEquals(4, ArrayUtils.indexOf(array, 'a', 2));
    assertEquals(-1, ArrayUtils.indexOf(array, 'b', 2));
    assertEquals(2, ArrayUtils.indexOf(array, 'c', 2));
    assertEquals(3, ArrayUtils.indexOf(array, 'd', 2));
    assertEquals(3, ArrayUtils.indexOf(array, 'd', -1));
    assertEquals(-1, ArrayUtils.indexOf(array, 'e', 0));
    assertEquals(-1, ArrayUtils.indexOf(array, 'a', 6));
  }

  @Test
  public void testLastIndexOfChar() {
    char[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'a'));
    array = new char[]{'a', 'b', 'c', 'd', 'a'};
    assertEquals(4, ArrayUtils.lastIndexOf(array, 'a'));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 'b'));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 'c'));
    assertEquals(3, ArrayUtils.lastIndexOf(array, 'd'));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'e'));
  }

  @Test
  public void testLastIndexOfCharWithStartIndex() {
    char[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'a', 2));
    array = new char[]{'a', 'b', 'c', 'd', 'a'};
    assertEquals(0, ArrayUtils.lastIndexOf(array, 'a', 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, 'b', 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, 'c', 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'd', 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'd', -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, 'e'));
    assertEquals(4, ArrayUtils.lastIndexOf(array, 'a', 88));
  }

  @Test
  public void testContainsChar() {
    char[] array = null;
    assertFalse(ArrayUtils.contains(array, 'b'));
    array = new char[]{'a', 'b', 'c', 'd', 'a'};
    assertTrue(ArrayUtils.contains(array, 'a'));
    assertTrue(ArrayUtils.contains(array, 'b'));
    assertTrue(ArrayUtils.contains(array, 'c'));
    assertTrue(ArrayUtils.contains(array, 'd'));
    assertFalse(ArrayUtils.contains(array, 'e'));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfByte() {
    byte[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0));
    array = new byte[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, (byte) 0));
    assertEquals(1, ArrayUtils.indexOf(array, (byte) 1));
    assertEquals(2, ArrayUtils.indexOf(array, (byte) 2));
    assertEquals(3, ArrayUtils.indexOf(array, (byte) 3));
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 99));
  }

  @Test
  public void testIndexOfByteWithStartIndex() {
    byte[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0, 2));
    array = new byte[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, (byte) 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, (byte) 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (byte) 3, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (byte) 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0, 6));
  }

  @Test
  public void testLastIndexOfByte() {
    byte[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 0));
    array = new byte[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (byte) 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (byte) 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (byte) 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (byte) 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 99));
  }

  @Test
  public void testLastIndexOfByteWithStartIndex() {
    byte[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 0, 2));
    array = new byte[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, (byte) 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (byte) 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (byte) 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 99));
    assertEquals(4, ArrayUtils.lastIndexOf(array, (byte) 0, 88));
  }

  @Test
  public void testContainsByte() {
    byte[] array = null;
    assertFalse(ArrayUtils.contains(array, (byte) 1));
    array = new byte[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, (byte) 0));
    assertTrue(ArrayUtils.contains(array, (byte) 1));
    assertTrue(ArrayUtils.contains(array, (byte) 2));
    assertTrue(ArrayUtils.contains(array, (byte) 3));
    assertFalse(ArrayUtils.contains(array, (byte) 99));
  }

  //-----------------------------------------------------------------------
  @SuppressWarnings("cast")
  @Test
  public void testIndexOfDouble() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, (double) 0));
    assertEquals(1, ArrayUtils.indexOf(array, (double) 1));
    assertEquals(2, ArrayUtils.indexOf(array, (double) 2));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 3));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 99));
  }

  @SuppressWarnings("cast")
  @Test
  public void testIndexOfDoubleTolerance() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, (double) 0, (double) 0.3));
    assertEquals(2, ArrayUtils.indexOf(array, (double) 2.2, (double) 0.35));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, (double) 2.0));
    assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, (double) 0.0001));
  }

  @SuppressWarnings("cast")
  @Test
  public void testIndexOfDoubleWithStartIndex() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2));
    array = new double[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, (double) 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, (double) 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 3, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 6));
  }

  @SuppressWarnings("cast")
  @Test
  public void testIndexOfDoubleWithStartIndexTolerance() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 99, (double) 0.3));
    assertEquals(0, ArrayUtils.indexOf(array, (double) 0, 0, (double) 0.3));
    assertEquals(4, ArrayUtils.indexOf(array, (double) 0, 3, (double) 0.3));
    assertEquals(2, ArrayUtils.indexOf(array, (double) 2.2, 0, (double) 0.35));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, 0, (double) 2.0));
    assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, 0, (double) 0.0001));
    assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, -1, (double) 2.0));
    assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, -300, (double) 0.0001));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfDouble() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 99));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfDoubleTolerance() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0.3));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2.2, (double) 0.35));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 4.15, (double) 2.0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1.00001324, (double) 0.0001));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfDoubleWithStartIndex() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2));
    array = new double[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, (double) 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 99));
    assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, 88));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfDoubleWithStartIndexTolerance() {
    double[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2, (double) 0));
    array = new double[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2, (double) 0));
    array = new double[]{(double) 3};
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 1, 0, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, 99, (double) 0.3));
    assertEquals(0, ArrayUtils.lastIndexOf(array, (double) 0, 3, (double) 0.3));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2.2, 3, (double) 0.35));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 4.15, array.length, (double) 2.0));
    assertEquals(1,
        ArrayUtils.lastIndexOf(array, (double) 1.00001324, array.length, (double) 0.0001));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 4.15, -200, (double) 2.0));
  }

  @SuppressWarnings("cast")
  @Test
  public void testContainsDouble() {
    double[] array = null;
    assertFalse(ArrayUtils.contains(array, (double) 1));
    array = new double[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, (double) 0));
    assertTrue(ArrayUtils.contains(array, (double) 1));
    assertTrue(ArrayUtils.contains(array, (double) 2));
    assertTrue(ArrayUtils.contains(array, (double) 3));
    assertFalse(ArrayUtils.contains(array, (double) 99));
  }

  @SuppressWarnings("cast")
  @Test
  public void testContainsDoubleTolerance() {
    double[] array = null;
    assertFalse(ArrayUtils.contains(array, (double) 1, (double) 0));
    array = new double[]{0, 1, 2, 3, 0};
    assertFalse(ArrayUtils.contains(array, (double) 4.0, (double) 0.33));
    assertFalse(ArrayUtils.contains(array, (double) 2.5, (double) 0.49));
    assertTrue(ArrayUtils.contains(array, (double) 2.5, (double) 0.50));
    assertTrue(ArrayUtils.contains(array, (double) 2.5, (double) 0.51));
  }

  //-----------------------------------------------------------------------
  @SuppressWarnings("cast")
  @Test
  public void testIndexOfFloat() {
    float[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 0));
    array = new float[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 0));
    array = new float[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.indexOf(array, (float) 0));
    assertEquals(1, ArrayUtils.indexOf(array, (float) 1));
    assertEquals(2, ArrayUtils.indexOf(array, (float) 2));
    assertEquals(3, ArrayUtils.indexOf(array, (float) 3));
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 99));
  }

  @SuppressWarnings("cast")
  @Test
  public void testIndexOfFloatWithStartIndex() {
    float[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 2));
    array = new float[0];
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 2));
    array = new float[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.indexOf(array, (float) 0, 2));
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 1, 2));
    assertEquals(2, ArrayUtils.indexOf(array, (float) 2, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (float) 3, 2));
    assertEquals(3, ArrayUtils.indexOf(array, (float) 3, -1));
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 99, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 6));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfFloat() {
    float[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0));
    array = new float[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0));
    array = new float[]{0, 1, 2, 3, 0};
    assertEquals(4, ArrayUtils.lastIndexOf(array, (float) 0));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (float) 1));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (float) 2));
    assertEquals(3, ArrayUtils.lastIndexOf(array, (float) 3));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 99));
  }

  @SuppressWarnings("cast")
  @Test
  public void testLastIndexOfFloatWithStartIndex() {
    float[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0, 2));
    array = new float[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0, 2));
    array = new float[]{0, 1, 2, 3, 0};
    assertEquals(0, ArrayUtils.lastIndexOf(array, (float) 0, 2));
    assertEquals(1, ArrayUtils.lastIndexOf(array, (float) 1, 2));
    assertEquals(2, ArrayUtils.lastIndexOf(array, (float) 2, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 3, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 3, -1));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 99));
    assertEquals(4, ArrayUtils.lastIndexOf(array, (float) 0, 88));
  }

  @SuppressWarnings("cast")
  @Test
  public void testContainsFloat() {
    float[] array = null;
    assertFalse(ArrayUtils.contains(array, (float) 1));
    array = new float[]{0, 1, 2, 3, 0};
    assertTrue(ArrayUtils.contains(array, (float) 0));
    assertTrue(ArrayUtils.contains(array, (float) 1));
    assertTrue(ArrayUtils.contains(array, (float) 2));
    assertTrue(ArrayUtils.contains(array, (float) 3));
    assertFalse(ArrayUtils.contains(array, (float) 99));
  }

  //-----------------------------------------------------------------------
  @Test
  public void testIndexOfBoolean() {
    boolean[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, true));
    array = new boolean[0];
    assertEquals(-1, ArrayUtils.indexOf(array, true));
    array = new boolean[]{true, false, true};
    assertEquals(0, ArrayUtils.indexOf(array, true));
    assertEquals(1, ArrayUtils.indexOf(array, false));
    array = new boolean[]{true, true};
    assertEquals(-1, ArrayUtils.indexOf(array, false));
  }

  @Test
  public void testIndexOfBooleanWithStartIndex() {
    boolean[] array = null;
    assertEquals(-1, ArrayUtils.indexOf(array, true, 2));
    array = new boolean[0];
    assertEquals(-1, ArrayUtils.indexOf(array, true, 2));
    array = new boolean[]{true, false, true};
    assertEquals(2, ArrayUtils.indexOf(array, true, 1));
    assertEquals(-1, ArrayUtils.indexOf(array, false, 2));
    assertEquals(1, ArrayUtils.indexOf(array, false, 0));
    assertEquals(1, ArrayUtils.indexOf(array, false, -1));
    array = new boolean[]{true, true};
    assertEquals(-1, ArrayUtils.indexOf(array, false, 0));
    assertEquals(-1, ArrayUtils.indexOf(array, false, -1));
  }

  @Test
  public void testLastIndexOfBoolean() {
    boolean[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true));
    array = new boolean[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true));
    array = new boolean[]{true, false, true};
    assertEquals(2, ArrayUtils.lastIndexOf(array, true));
    assertEquals(1, ArrayUtils.lastIndexOf(array, false));
    array = new boolean[]{true, true};
    assertEquals(-1, ArrayUtils.lastIndexOf(array, false));
  }

  @Test
  public void testLastIndexOfBooleanWithStartIndex() {
    boolean[] array = null;
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true, 2));
    array = new boolean[0];
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true, 2));
    array = new boolean[]{true, false, true};
    assertEquals(2, ArrayUtils.lastIndexOf(array, true, 2));
    assertEquals(0, ArrayUtils.lastIndexOf(array, true, 1));
    assertEquals(1, ArrayUtils.lastIndexOf(array, false, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true, -1));
    array = new boolean[]{true, true};
    assertEquals(-1, ArrayUtils.lastIndexOf(array, false, 2));
    assertEquals(-1, ArrayUtils.lastIndexOf(array, true, -1));
  }

  @Test
  public void testContainsBoolean() {
    boolean[] array = null;
    assertFalse(ArrayUtils.contains(array, true));
    array = new boolean[]{true, false, true};
    assertTrue(ArrayUtils.contains(array, true));
    assertTrue(ArrayUtils.contains(array, false));
    array = new boolean[]{true, true};
    assertTrue(ArrayUtils.contains(array, true));
    assertFalse(ArrayUtils.contains(array, false));
  }

  //-----------------------------------------------------------------------

  /**
   * Test for {@link ArrayUtils#isEmpty(java.lang.Object[])}.
   */
  @Test
  public void testIsEmptyObject() {
    final Object[] emptyArray = new Object[]{};
    final Object[] notEmptyArray = new Object[]{new String("Value")};
    assertTrue(ArrayUtils.isEmpty((Object[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyArray));
  }

  /**
   * Tests for {@link ArrayUtils#isEmpty(long[])},
   * {@link ArrayUtils#isEmpty(int[])},
   * {@link ArrayUtils#isEmpty(short[])},
   * {@link ArrayUtils#isEmpty(char[])},
   * {@link ArrayUtils#isEmpty(byte[])},
   * {@link ArrayUtils#isEmpty(double[])},
   * {@link ArrayUtils#isEmpty(float[])} and
   * {@link ArrayUtils#isEmpty(boolean[])}.
   */
  @Test
  public void testIsEmptyPrimitives() {
    final long[] emptyLongArray = new long[]{};
    final long[] notEmptyLongArray = new long[]{1L};
    assertTrue(ArrayUtils.isEmpty((long[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyLongArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyLongArray));

    final int[] emptyIntArray = new int[]{};
    final int[] notEmptyIntArray = new int[]{1};
    assertTrue(ArrayUtils.isEmpty((int[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyIntArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyIntArray));

    final short[] emptyShortArray = new short[]{};
    final short[] notEmptyShortArray = new short[]{1};
    assertTrue(ArrayUtils.isEmpty((short[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyShortArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyShortArray));

    final char[] emptyCharArray = new char[]{};
    final char[] notEmptyCharArray = new char[]{1};
    assertTrue(ArrayUtils.isEmpty((char[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyCharArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyCharArray));

    final byte[] emptyByteArray = new byte[]{};
    final byte[] notEmptyByteArray = new byte[]{1};
    assertTrue(ArrayUtils.isEmpty((byte[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyByteArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyByteArray));

    final double[] emptyDoubleArray = new double[]{};
    final double[] notEmptyDoubleArray = new double[]{1.0};
    assertTrue(ArrayUtils.isEmpty((double[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyDoubleArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyDoubleArray));

    final float[] emptyFloatArray = new float[]{};
    final float[] notEmptyFloatArray = new float[]{1.0F};
    assertTrue(ArrayUtils.isEmpty((float[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyFloatArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyFloatArray));

    final boolean[] emptyBooleanArray = new boolean[]{};
    final boolean[] notEmptyBooleanArray = new boolean[]{true};
    assertTrue(ArrayUtils.isEmpty((boolean[]) null));
    assertTrue(ArrayUtils.isEmpty(emptyBooleanArray));
    assertFalse(ArrayUtils.isEmpty(notEmptyBooleanArray));
  }

  // ------------------------------------------------------------------------
  @Test
  public void testGetLength() {
    assertEquals(0, ArrayUtils.getLength(null));

    final Object[] emptyObjectArray = new Object[0];
    final Object[] notEmptyObjectArray = new Object[]{"aValue"};
    assertEquals(0, ArrayUtils.getLength((Object[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyObjectArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyObjectArray));

    final int[] emptyIntArray = new int[]{};
    final int[] notEmptyIntArray = new int[]{1};
    assertEquals(0, ArrayUtils.getLength((int[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyIntArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyIntArray));

    final short[] emptyShortArray = new short[]{};
    final short[] notEmptyShortArray = new short[]{1};
    assertEquals(0, ArrayUtils.getLength((short[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyShortArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyShortArray));

    final char[] emptyCharArray = new char[]{};
    final char[] notEmptyCharArray = new char[]{1};
    assertEquals(0, ArrayUtils.getLength((char[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyCharArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyCharArray));

    final byte[] emptyByteArray = new byte[]{};
    final byte[] notEmptyByteArray = new byte[]{1};
    assertEquals(0, ArrayUtils.getLength((byte[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyByteArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyByteArray));

    final double[] emptyDoubleArray = new double[]{};
    final double[] notEmptyDoubleArray = new double[]{1.0};
    assertEquals(0, ArrayUtils.getLength((double[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyDoubleArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyDoubleArray));

    final float[] emptyFloatArray = new float[]{};
    final float[] notEmptyFloatArray = new float[]{1.0F};
    assertEquals(0, ArrayUtils.getLength((float[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyFloatArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyFloatArray));

    final boolean[] emptyBooleanArray = new boolean[]{};
    final boolean[] notEmptyBooleanArray = new boolean[]{true};
    assertEquals(0, ArrayUtils.getLength((boolean[]) null));
    assertEquals(0, ArrayUtils.getLength(emptyBooleanArray));
    assertEquals(1, ArrayUtils.getLength(notEmptyBooleanArray));

    try {
      ArrayUtils.getLength("notAnArray");
      fail("IllegalArgumentException should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }
}
