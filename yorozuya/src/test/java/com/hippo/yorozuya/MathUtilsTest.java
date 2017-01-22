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

import static com.hippo.yorozuya.Utils.assertEqualsDouble;
import static com.hippo.yorozuya.Utils.assertEqualsFloat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MathUtilsTest {

  @Test
  public void testMax() {
    // int
    assertEquals(23, MathUtils.max(23));
    assertEquals(4325, MathUtils.max(1, 0, 345, -78, -3434434, 4325, 1, 0));
    try {
      assertEquals(0, MathUtils.max(new int[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.max((int[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // long
    assertEquals(4325263L, MathUtils.max(4325263L));
    assertEquals(34343434L, MathUtils.max(342L, -3244L, 435L, 0L, 34343434L));
    try {
      assertEquals(0, MathUtils.max(new long[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.max((long[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // float
    assertEqualsFloat(354f, MathUtils.max(354f));
    assertEqualsFloat(32454f, MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
    assertTrue(Float.isNaN(MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN)));
    assertEqualsFloat(+0.0f, MathUtils.max(+0.0f, -0.0f, -0.0f, +0.0f));
    try {
      assertEqualsFloat(0.0f, MathUtils.max(new float[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEqualsFloat(0.0f, MathUtils.max((float[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // double
    assertEqualsDouble(354.0, MathUtils.max(354.0));
    assertEqualsDouble(32454.0, MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45));
    assertTrue(Double.isNaN(MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN)));
    assertEqualsDouble(+0.0, MathUtils.max(+0.0, -0.0, -0.0, +0.0));
    try {
      assertEqualsDouble(0.0f, MathUtils.max(new float[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEqualsDouble(0.0f, MathUtils.max((float[]) null));
      fail();
    } catch (NullPointerException e) {
    }
  }


  @Test
  public void testMin() {
    // int
    assertEquals(23, MathUtils.min(23));
    assertEquals(-3434434, MathUtils.min(1, 0, 345, -78, -3434434, 4325, 1, 0));
    try {
      assertEquals(0, MathUtils.min(new int[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.min((int[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // long
    assertEquals(4325263L, MathUtils.min(4325263L));
    assertEquals(-3244L, MathUtils.min(342L, -3244L, 435L, 0L, 34343434L));
    try {
      assertEquals(0, MathUtils.min(new long[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.min((long[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // float
    assertEqualsFloat(354f, MathUtils.min(354f));
    assertEqualsFloat(-4535.45f, MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
    assertTrue(Float.isNaN(MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN)));
    assertEqualsFloat(-0.0f, MathUtils.min(+0.0f, -0.0f, -0.0f, +0.0f));
    try {
      assertEqualsFloat(0.0f, MathUtils.min(new float[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEqualsFloat(0.0f, MathUtils.min((float[]) null));
      fail();
    } catch (NullPointerException e) {
    }

    // double
    assertEqualsDouble(354.0, MathUtils.min(354.0));
    assertEqualsDouble(-4535.45, MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45));
    assertTrue(Double.isNaN(MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN)));
    assertEqualsDouble(-0.0, MathUtils.min(+0.0, -0.0, -0.0, +0.0));
    try {
      assertEqualsDouble(0.0f, MathUtils.min(new float[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEqualsDouble(0.0f, MathUtils.min((float[]) null));
      fail();
    } catch (NullPointerException e) {
    }
  }

  @Test
  public void testAverageInt() {
    assertEquals(Integer.MAX_VALUE / 2 + 1,
        MathUtils.average(
            Integer.MAX_VALUE,
            2
        ));
    assertEquals(Integer.MAX_VALUE - 1,
        MathUtils.average(
            Integer.MAX_VALUE,
            Integer.MAX_VALUE,
            Integer.MAX_VALUE - 3
        ));
    assertEquals(2,
        MathUtils.average(
            1,
            2,
            3
        ));
    assertEquals(Integer.MIN_VALUE / 2 - 1,
        MathUtils.average(
            Integer.MIN_VALUE,
            -2
        ));
    assertEquals(Integer.MIN_VALUE + 1,
        MathUtils.average(
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE + 3
        ));
    assertEquals(-2,
        MathUtils.average(
            -1,
            -2,
            -3
        ));
    try {
      assertEquals(0, MathUtils.average(new int[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.average((int[]) null));
      fail();
    } catch (NullPointerException e) {
    }
  }

  @Test
  public void testAverageLong() {
    assertEquals(Long.MAX_VALUE / 2 + 1L,
        MathUtils.average(
            Long.MAX_VALUE,
            2L
        ));
    assertEquals(Long.MAX_VALUE - 1L,
        MathUtils.average(
            Long.MAX_VALUE,
            Long.MAX_VALUE,
            Long.MAX_VALUE - 3L
        ));
    assertEquals(2L,
        MathUtils.average(
            1L,
            2L,
            3L
        ));
    assertEquals(Long.MIN_VALUE / 2 - 1L,
        MathUtils.average(
            Long.MIN_VALUE,
            -2L
        ));
    assertEquals(Long.MIN_VALUE + 1L,
        MathUtils.average(
            Long.MIN_VALUE,
            Long.MIN_VALUE,
            Long.MIN_VALUE + 3L
        ));
    assertEquals(-2L,
        MathUtils.average(
            -1L,
            -2L,
            -3L
        ));
    try {
      assertEquals(0, MathUtils.average(new long[] {}));
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      assertEquals(0, MathUtils.average((long[]) null));
      fail();
    } catch (NullPointerException e) {
    }
  }

  @Test
  public void testDist() {
    assertEqualsFloat(0.0f, MathUtils.dist(1.0f, 2.0f, 1.0f, 2.0f));
    // 3, 4, 5
    assertEqualsFloat(5.0f, MathUtils.dist(1.0f, 2.0f, 4.0f, 6.0f));

    assertEqualsDouble(0.0, MathUtils.dist(1.0, 2.0, 1.0, 2.0));
    // 3, 4, 5
    assertEqualsDouble(5.0, MathUtils.dist(1.0, 2.0, 4.0, 6.0));

    assertEqualsFloat(0.0f, MathUtils.dist(1.0f, 2.0f, 3.0f, 1.0f, 2.0f, 3.0f));
    // 1, 4, 8, 9
    assertEqualsFloat(9.0f, MathUtils.dist(1.0f, 2.0f, 3.0f, 2.0f, 6.0f, 11.0f));

    assertEqualsDouble(0.0, MathUtils.dist(1.0, 2.0, 3.0, 1.0, 2.0, 3.0));
    // 1, 4, 8, 9
    assertEqualsDouble(9.0, MathUtils.dist(1.0, 2.0, 3.0, 2.0, 6.0, 11.0));
  }

  @Test
  public void testNear() {
    assertTrue(MathUtils.near(1.0f, 2.0f, 1.0f, 2.0f, 0.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 2.0f, 2.0f, 1.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 1.0f, 1.0f, 1.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 1.5f, 1.5f, 1.0f));
    assertFalse(MathUtils.near(1.0f, 2.0f, 1.5f, 1.5f, 0.5f));

    assertTrue(MathUtils.near(1.0, 2.0, 1.0, 2.0, 0.0));
    assertTrue(MathUtils.near(1.0, 2.0, 2.0, 2.0, 1.0));
    assertTrue(MathUtils.near(1.0, 2.0, 1.0, 1.0, 1.0));
    assertTrue(MathUtils.near(1.0, 2.0, 1.5, 1.5, 1.0));
    assertFalse(MathUtils.near(1.0, 2.0, 1.5, 1.5, 0.5));

    assertTrue(MathUtils.near(1.0f, 2.0f, 3.0f, 1.0f, 2.0f, 3.0f, 0.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 3.0f, 2.0f, 2.0f, 3.0f, 1.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 3.0f, 1.0f, 3.0f, 3.0f, 1.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 3.0f, 1.0f, 2.0f, 2.0f, 1.0f));
    assertTrue(MathUtils.near(1.0f, 2.0f, 3.0f, 2.0f, 1.0f, 2.0f, 2.0f));
    assertFalse(MathUtils.near(1.0f, 2.0f, 3.0f, 2.0f, 1.0f, 2.0f, 0.5f));

    assertTrue(MathUtils.near(1.0, 2.0, 3.0, 1.0, 2.0, 3.0, 0.0));
    assertTrue(MathUtils.near(1.0, 2.0, 3.0, 2.0, 2.0, 3.0, 1.0));
    assertTrue(MathUtils.near(1.0, 2.0, 3.0, 1.0, 3.0, 3.0, 1.0));
    assertTrue(MathUtils.near(1.0, 2.0, 3.0, 1.0, 2.0, 2.0, 1.0));
    assertTrue(MathUtils.near(1.0, 2.0, 3.0, 2.0, 1.0, 2.0, 2.0));
    assertFalse(MathUtils.near(1.0, 2.0, 3.0, 2.0, 1.0, 2.0, 0.5));
  }

  @Test
  public void testRadiansDegrees() {
    assertEqualsFloat(0.0f, MathUtils.radians(0.0f));
    assertEqualsFloat(0.0f, MathUtils.degrees(0.0f));
    assertEqualsFloat((float) Math.PI, MathUtils.radians(180.0f));
    assertEqualsFloat(180.0f, MathUtils.degrees((float) Math.PI));

    assertEqualsDouble(0.0, MathUtils.radians(0.0));
    assertEqualsDouble(0.0, MathUtils.degrees(0.0));
    assertEqualsDouble(Math.PI, MathUtils.radians(180.0));
    assertEqualsDouble(180.0, MathUtils.degrees(Math.PI));
  }

  @Test
  public void testLerp() {
    assertEquals(5, MathUtils.lerp(5, 12, 0));
    assertEquals(12, MathUtils.lerp(5, 12, 1));
    assertEquals(-2, MathUtils.lerp(5, 12, -1));
    assertEquals(8, MathUtils.lerp(5, 12, 0.5f));

    assertEquals(5L, MathUtils.lerp(5L, 12L, 0));
    assertEquals(12L, MathUtils.lerp(5L, 12L, 1));
    assertEquals(-2L, MathUtils.lerp(5L, 12L, -1));
    assertEquals(8L, MathUtils.lerp(5L, 12L, 0.5f));

    assertEqualsFloat(5.0f, MathUtils.lerp(5.0f, 12.0f, 0));
    assertEqualsFloat(12.0f, MathUtils.lerp(5.0f, 12.0f, 1));
    assertEqualsFloat(-2.0f, MathUtils.lerp(5.0f, 12.0f, -1));
    assertEqualsFloat(8.5f, MathUtils.lerp(5.0f, 12.0f, 0.5f));
  }

  @Test
  public void testNorm() {
    assertEqualsFloat(0.0f, MathUtils.norm(5, 12, 5));
    assertEqualsFloat(1.0f, MathUtils.norm(5, 12, 12));
    assertEqualsFloat(-1.0f, MathUtils.norm(5, 12, -2));
    assertEqualsFloat(0.0f, MathUtils.norm(5, 5, 5));
    assertTrue(Float.isNaN(MathUtils.norm(5, 5, 0)));

    assertEqualsFloat(0.0f, MathUtils.norm(5L, 12L, 5L));
    assertEqualsFloat(1.0f, MathUtils.norm(5L, 12L, 12L));
    assertEqualsFloat(-1.0f, MathUtils.norm(5L, 12L, -2L));
    assertEqualsFloat(0.0f, MathUtils.norm(5L, 5L, 5L));
    assertTrue(Float.isNaN(MathUtils.norm(5L, 5L, 0L)));

    assertEqualsFloat(0.0f, MathUtils.norm(5.0f, 12.0f, 5.0f));
    assertEqualsFloat(1.0f, MathUtils.norm(5.0f, 12.0f, 12.0f));
    assertEqualsFloat(-1.0f, MathUtils.norm(5.0f, 12.0f, -2.0f));
    assertEqualsFloat(0.0f, MathUtils.norm(5.0f, 5.0f, 5.0f));
    assertTrue(Float.isNaN(MathUtils.norm(5.0f, 5.0f, 0.0f)));
  }

  @Test
  public void testClamp() {
    assertEquals(7, MathUtils.clamp(7, 0, 8));
    assertEquals(0, MathUtils.clamp(-7, 0, 8));
    assertEquals(8, MathUtils.clamp(100, 0, 8));
    assertEquals(0, MathUtils.clamp(0, 0, 8));
    assertEquals(8, MathUtils.clamp(8, 0, 8));
    assertEquals(7, MathUtils.clamp(7, 8, 0));
    assertEquals(0, MathUtils.clamp(-7, 8, 0));
    assertEquals(8, MathUtils.clamp(100, 8, 0));
    assertEquals(0, MathUtils.clamp(0, 8, 0));
    assertEquals(8, MathUtils.clamp(8, 8, 0));

    assertEquals(7L, MathUtils.clamp(7L, 0L, 8L));
    assertEquals(0L, MathUtils.clamp(-7L, 0L, 8L));
    assertEquals(8L, MathUtils.clamp(100L, 0L, 8L));
    assertEquals(0L, MathUtils.clamp(0L, 0L, 8L));
    assertEquals(8L, MathUtils.clamp(8L, 0L, 8L));
    assertEquals(7L, MathUtils.clamp(7L, 8L, 0L));
    assertEquals(0L, MathUtils.clamp(-7L, 8L, 0L));
    assertEquals(8L, MathUtils.clamp(100L, 8L, 0L));
    assertEquals(0L, MathUtils.clamp(0L, 8L, 0L));
    assertEquals(8L, MathUtils.clamp(8L, 8L, 0L));

    assertEqualsFloat(7.0f, MathUtils.clamp(7.0f, 0.0f, 8.0f));
    assertEqualsFloat(0.0f, MathUtils.clamp(-7.0f, 0.0f, 8.0f));
    assertEqualsFloat(8.0f, MathUtils.clamp(100.0f, 0.0f, 8.0f));
    assertEqualsFloat(0.0f, MathUtils.clamp(0.0f, 0.0f, 8.0f));
    assertEqualsFloat(8.0f, MathUtils.clamp(8.0f, 0.0f, 8.0f));
    assertEqualsFloat(7.0f, MathUtils.clamp(7.0f, 8.0f, 0.0f));
    assertEqualsFloat(0.0f, MathUtils.clamp(-7.0f, 8.0f, 0.0f));
    assertEqualsFloat(8.0f, MathUtils.clamp(100.0f, 8.0f, 0.0f));
    assertEqualsFloat(0.0f, MathUtils.clamp(0.0f, 8.0f, 0.0f));
    assertEqualsFloat(8.0f, MathUtils.clamp(8.0f, 8.0f, 0.0f));

    assertEqualsDouble(7.0, MathUtils.clamp(7.0, 0.0, 8.0));
    assertEqualsDouble(0.0, MathUtils.clamp(-7.0, 0.0, 8.0));
    assertEqualsDouble(8.0, MathUtils.clamp(100.0, 0.0, 8.0));
    assertEqualsDouble(0.0, MathUtils.clamp(0.0, 0.0, 8.0));
    assertEqualsDouble(8.0, MathUtils.clamp(8.0, 0.0, 8.0));
    assertEqualsDouble(7.0, MathUtils.clamp(7.0, 8.0, 0.0));
    assertEqualsDouble(0.0, MathUtils.clamp(-7.0, 8.0, 0.0));
    assertEqualsDouble(8.0, MathUtils.clamp(100.0, 8.0, 0.0));
    assertEqualsDouble(0.0, MathUtils.clamp(0.0, 8.0, 0.0));
    assertEqualsDouble(8.0, MathUtils.clamp(8.0, 8.0, 0.0));
  }

  @Test
  public void testPow2() {
    assertFalse(MathUtils.pow2(0));
    assertTrue(MathUtils.pow2(1));
    assertTrue(MathUtils.pow2(2));
    assertFalse(MathUtils.pow2(3));
    assertTrue(MathUtils.pow2(4));
    assertTrue(MathUtils.pow2(1 << 6));
    assertFalse(MathUtils.pow2((1 << 8) + 1));
    assertTrue(MathUtils.pow2(Integer.MIN_VALUE));
  }

  @Test
  public void testNextPow2() {
    assertEquals(1, MathUtils.nextPow2(0));
    assertEquals(1, MathUtils.nextPow2(1));
    assertEquals(2, MathUtils.nextPow2(2));
    assertEquals(4, MathUtils.nextPow2(3));
    assertEquals(1 << 9, MathUtils.nextPow2((1 << 9)));
    assertEquals(1 << 9, MathUtils.nextPow2((1 << 9) - 7));
    assertEquals(1 << 31, MathUtils.nextPow2((1 << 31)));
    assertEquals(1 << 31, MathUtils.nextPow2((1 << 30) + 10));
    assertEquals(1 << 31, MathUtils.nextPow2((1 << 31) - 10));

    assertEquals(1L, MathUtils.nextPow2(0L));
    assertEquals(1L, MathUtils.nextPow2(1L));
    assertEquals(2L, MathUtils.nextPow2(2L));
    assertEquals(4L, MathUtils.nextPow2(3L));
    assertEquals(1L << 9, MathUtils.nextPow2((1L << 9)));
    assertEquals(1L << 9, MathUtils.nextPow2((1L << 9) - 7L));
    assertEquals(1L << 63, MathUtils.nextPow2(1L << 63));
    assertEquals(1L << 63, MathUtils.nextPow2((1L << 62) + 10L));
    assertEquals(1L << 63, MathUtils.nextPow2((1L << 63) - 10L));
  }

  @Test
  public void testPrevPow2() {
    assertEquals(0, MathUtils.prevPow2(0));
    assertEquals(1, MathUtils.prevPow2(1));
    assertEquals(2, MathUtils.prevPow2(2));
    assertEquals(2, MathUtils.prevPow2(3));
    assertEquals(4, MathUtils.prevPow2(4));
    assertEquals(4, MathUtils.prevPow2(5));
    assertEquals(1 << 31, MathUtils.prevPow2(1 << 31));
    assertEquals(1 << 31, MathUtils.prevPow2((1 << 31) + 45));

    assertEquals(0L, MathUtils.prevPow2(0L));
    assertEquals(1L, MathUtils.prevPow2(1L));
    assertEquals(2L, MathUtils.prevPow2(2L));
    assertEquals(2L, MathUtils.prevPow2(3L));
    assertEquals(4L, MathUtils.prevPow2(4L));
    assertEquals(4L, MathUtils.prevPow2(5L));
    assertEquals(1L << 63, MathUtils.prevPow2(1L << 63));
    assertEquals(1L << 63, MathUtils.prevPow2((1L << 63) + 45L));
  }

  @Test
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

    assertEquals(0L, MathUtils.floorDiv(6L, 7L));
    assertEquals(1L, MathUtils.floorDiv(6L, 6L));
    assertEquals(1L, MathUtils.floorDiv(6L, 5L));
    assertEquals(1L, MathUtils.floorDiv(6L, 4L));
    assertEquals(2L, MathUtils.floorDiv(6L, 3L));
    assertEquals(3L, MathUtils.floorDiv(6L, 2L));
    assertEquals(6L, MathUtils.floorDiv(6L, 1L));
    assertEquals(-6L, MathUtils.floorDiv(6L, -1L));
    assertEquals(-3L, MathUtils.floorDiv(6L, -2L));
    assertEquals(-2L, MathUtils.floorDiv(6L, -3L));
    assertEquals(-2L, MathUtils.floorDiv(6L, -4L));
    assertEquals(-2L, MathUtils.floorDiv(6L, -5L));
    assertEquals(-1L, MathUtils.floorDiv(6L, -6L));
    assertEquals(-1L, MathUtils.floorDiv(6L, -7L));

    assertEquals(-1L, MathUtils.floorDiv(-6L, 7L));
    assertEquals(-1L, MathUtils.floorDiv(-6L, 6L));
    assertEquals(-2L, MathUtils.floorDiv(-6L, 5L));
    assertEquals(-2L, MathUtils.floorDiv(-6L, 4L));
    assertEquals(-2L, MathUtils.floorDiv(-6L, 3L));
    assertEquals(-3L, MathUtils.floorDiv(-6L, 2L));
    assertEquals(-6L, MathUtils.floorDiv(-6L, 1L));
    assertEquals(6L, MathUtils.floorDiv(-6L, -1L));
    assertEquals(3L, MathUtils.floorDiv(-6L, -2L));
    assertEquals(2L, MathUtils.floorDiv(-6L, -3L));
    assertEquals(1L, MathUtils.floorDiv(-6L, -4L));
    assertEquals(1L, MathUtils.floorDiv(-6L, -5L));
    assertEquals(1L, MathUtils.floorDiv(-6L, -6L));
    assertEquals(0L, MathUtils.floorDiv(-6L, -7L));
  }

  @Test
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

    assertEquals(1L, MathUtils.ceilDiv(6L, 7L));
    assertEquals(1L, MathUtils.ceilDiv(6L, 6L));
    assertEquals(2L, MathUtils.ceilDiv(6L, 5L));
    assertEquals(2L, MathUtils.ceilDiv(6L, 4L));
    assertEquals(2L, MathUtils.ceilDiv(6L, 3L));
    assertEquals(3L, MathUtils.ceilDiv(6L, 2L));
    assertEquals(6L, MathUtils.ceilDiv(6L, 1L));
    assertEquals(-6L, MathUtils.ceilDiv(6L, -1L));
    assertEquals(-3L, MathUtils.ceilDiv(6L, -2L));
    assertEquals(-2L, MathUtils.ceilDiv(6L, -3L));
    assertEquals(-1L, MathUtils.ceilDiv(6L, -4L));
    assertEquals(-1L, MathUtils.ceilDiv(6L, -5L));
    assertEquals(-1L, MathUtils.ceilDiv(6L, -6L));
    assertEquals(0L, MathUtils.ceilDiv(6L, -7L));

    assertEquals(0L, MathUtils.ceilDiv(-6L, 7L));
    assertEquals(-1L, MathUtils.ceilDiv(-6L, 6L));
    assertEquals(-1L, MathUtils.ceilDiv(-6L, 5L));
    assertEquals(-1L, MathUtils.ceilDiv(-6L, 4L));
    assertEquals(-2L, MathUtils.ceilDiv(-6L, 3L));
    assertEquals(-3L, MathUtils.ceilDiv(-6L, 2L));
    assertEquals(-6L, MathUtils.ceilDiv(-6L, 1L));
    assertEquals(6L, MathUtils.ceilDiv(-6L, -1L));
    assertEquals(3L, MathUtils.ceilDiv(-6L, -2L));
    assertEquals(2L, MathUtils.ceilDiv(-6L, -3L));
    assertEquals(2L, MathUtils.ceilDiv(-6L, -4L));
    assertEquals(2L, MathUtils.ceilDiv(-6L, -5L));
    assertEquals(1L, MathUtils.ceilDiv(-6L, -6L));
    assertEquals(1L, MathUtils.ceilDiv(-6L, -7L));
  }

  @Test
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

    assertEquals(6L, MathUtils.floorMod(6, 7));
    assertEquals(0L, MathUtils.floorMod(6, 6));
    assertEquals(1L, MathUtils.floorMod(6, 5));
    assertEquals(2L, MathUtils.floorMod(6, 4));
    assertEquals(0L, MathUtils.floorMod(6, 3));
    assertEquals(0L, MathUtils.floorMod(6, 2));
    assertEquals(0L, MathUtils.floorMod(6, 1));
    assertEquals(0L, MathUtils.floorMod(6, -1));
    assertEquals(0L, MathUtils.floorMod(6, -2));
    assertEquals(0L, MathUtils.floorMod(6, -3));
    assertEquals(-2L, MathUtils.floorMod(6, -4));
    assertEquals(-4L, MathUtils.floorMod(6, -5));
    assertEquals(0L, MathUtils.floorMod(6, -6));
    assertEquals(-1L, MathUtils.floorMod(6, -7));

    assertEquals(1L, MathUtils.floorMod(-6L, 7L));
    assertEquals(0L, MathUtils.floorMod(-6L, 6L));
    assertEquals(4L, MathUtils.floorMod(-6L, 5L));
    assertEquals(2L, MathUtils.floorMod(-6L, 4L));
    assertEquals(0L, MathUtils.floorMod(-6L, 3L));
    assertEquals(0L, MathUtils.floorMod(-6L, 2L));
    assertEquals(0L, MathUtils.floorMod(-6L, 1L));
    assertEquals(0L, MathUtils.floorMod(-6L, -1L));
    assertEquals(0L, MathUtils.floorMod(-6L, -2L));
    assertEquals(0L, MathUtils.floorMod(-6L, -3L));
    assertEquals(-2L, MathUtils.floorMod(-6L, -4L));
    assertEquals(-1L, MathUtils.floorMod(-6L, -5L));
    assertEquals(0L, MathUtils.floorMod(-6L, -6L));
    assertEquals(-6L, MathUtils.floorMod(-6L, -7L));
  }

  @Test
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

    assertEquals(-1L, MathUtils.ceilMod(6L, 7L));
    assertEquals(0L, MathUtils.ceilMod(6L, 6L));
    assertEquals(-4L, MathUtils.ceilMod(6L, 5L));
    assertEquals(-2L, MathUtils.ceilMod(6L, 4L));
    assertEquals(0L, MathUtils.ceilMod(6L, 3L));
    assertEquals(0L, MathUtils.ceilMod(6L, 2L));
    assertEquals(0L, MathUtils.ceilMod(6L, 1L));
    assertEquals(0L, MathUtils.ceilMod(6L, -1L));
    assertEquals(0L, MathUtils.ceilMod(6L, -2L));
    assertEquals(0L, MathUtils.ceilMod(6L, -3L));
    assertEquals(2L, MathUtils.ceilMod(6L, -4L));
    assertEquals(1L, MathUtils.ceilMod(6L, -5L));
    assertEquals(0L, MathUtils.ceilMod(6L, -6L));
    assertEquals(6L, MathUtils.ceilMod(6L, -7L));

    assertEquals(-6L, MathUtils.ceilMod(-6L, 7L));
    assertEquals(0L, MathUtils.ceilMod(-6L, 6L));
    assertEquals(-1L, MathUtils.ceilMod(-6L, 5L));
    assertEquals(-2L, MathUtils.ceilMod(-6L, 4L));
    assertEquals(0L, MathUtils.ceilMod(-6L, 3L));
    assertEquals(0L, MathUtils.ceilMod(-6L, 2L));
    assertEquals(0L, MathUtils.ceilMod(-6L, 1L));
    assertEquals(0L, MathUtils.ceilMod(-6L, -1L));
    assertEquals(0L, MathUtils.ceilMod(-6L, -2L));
    assertEquals(0L, MathUtils.ceilMod(-6L, -3L));
    assertEquals(2L, MathUtils.ceilMod(-6L, -4L));
    assertEquals(4L, MathUtils.ceilMod(-6L, -5L));
    assertEquals(0L, MathUtils.ceilMod(-6L, -6L));
    assertEquals(1L, MathUtils.ceilMod(-6L, -7L));
  }

  @Test
  public void testCoverRadius() {
    assertEqualsFloat(5.0f, MathUtils.coverRadius(3.0f, 4.0f, 0.0f, 0.0f));
    assertEqualsFloat(5.0f, MathUtils.coverRadius(3.0f, 4.0f, 3.0f, 4.0f));
    assertEqualsFloat(2.5f, MathUtils.coverRadius(3.0f, 4.0f, 1.5f, 2.0f));
    assertEqualsFloat(13.0f, MathUtils.coverRadius(3.0f, 4.0f, 5.0f, 12.0f));
  }

  @Test
  public void testRandomInt() {
    testRandomIntInternal(1);
    testRandomIntInternal(0, 1);
    testRandomIntInternal(1, 2);
    testRandomIntInternal(-1, 0);
    testRandomIntInternal(-455, 213);
    testRandomIntInternal(Integer.MIN_VALUE, 435);
    testRandomIntInternal(-324, Integer.MAX_VALUE - 111);
    try {
      testRandomIntInternal(0);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      testRandomIntInternal(-100);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testRandomIntInternal(int howbig) {
    for (int i = 0; i < 10000; i++) {
      int random = MathUtils.random(howbig);
      assertTrue(random < howbig);
    }
  }

  public void testRandomIntInternal(int howsmall, int howbig) {
    for (int i = 0; i < 10000; i++) {
      int random = MathUtils.random(howsmall, howbig);
      assertTrue(random >= howsmall);
      assertTrue(random < howbig);
    }
  }

  @Test
  public void testRandomLong() {
    testRandomLongInternal(1);
    testRandomLongInternal(0, 1);
    testRandomLongInternal(1, 2);
    testRandomLongInternal(-1, 0);
    testRandomLongInternal(-455, 213);
    testRandomLongInternal(Long.MIN_VALUE, 435);
    testRandomLongInternal(-324, Long.MAX_VALUE - 111);
    try {
      testRandomLongInternal(0);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      testRandomLongInternal(-100);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testRandomLongInternal(long howbig) {
    for (int i = 0; i < 10000; i++) {
      long random = MathUtils.random(howbig);
      assertTrue(random < howbig);
    }
  }

  public void testRandomLongInternal(long howsmall, long howbig) {
    for (int i = 0; i < 10000; i++) {
      long random = MathUtils.random(howsmall, howbig);
      assertTrue(random >= howsmall);
      assertTrue(random < howbig);
    }
  }

  @Test
  public void testRandomFloat() {
    testRandomFloatInternal(1.0f);
    testRandomFloatInternal(1.0f, 1.001f);
    testRandomFloatInternal(323.999f, 324.0f);
    testRandomFloatInternal(-32342334.0f, 3243232334.0f);

    try {
      testRandomFloatInternal(0.0f);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      testRandomFloatInternal(-100.0f);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testRandomFloatInternal(float howbig) {
    for (int i = 0; i < 10000; i++) {
      float random = MathUtils.random(howbig);
      assertTrue(random < howbig);
    }
  }

  public void testRandomFloatInternal(float howsmall, float howbig) {
    for (int i = 0; i < 10000; i++) {
      float random = MathUtils.random(howsmall, howbig);
      assertTrue(random >= howsmall);
      assertTrue(random < howbig);
    }
  }

  @Test
  public void testRandomDouble() {
    testRandomDoubleInternal(1.0);
    testRandomDoubleInternal(1.0, 1.001);
    testRandomDoubleInternal(323.999, 324.0);
    testRandomDoubleInternal(-32342334.0, 3243232334.0);

    try {
      testRandomDoubleInternal(0.0);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      testRandomDoubleInternal(-100.0);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testRandomDoubleInternal(double howbig) {
    for (int i = 0; i < 10000; i++) {
      double random = MathUtils.random(howbig);
      assertTrue(random < howbig);
    }
  }

  public void testRandomDoubleInternal(double howsmall, double howbig) {
    for (int i = 0; i < 10000; i++) {
      double random = MathUtils.random(howsmall, howbig);
      assertTrue(random >= howsmall);
      assertTrue(random < howbig);
    }
  }
}
