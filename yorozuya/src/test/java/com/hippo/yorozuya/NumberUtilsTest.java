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
 * Created by Hippo on 1/22/2017.
 */

import static com.hippo.yorozuya.Utils.assertEqualsDouble;
import static com.hippo.yorozuya.Utils.assertEqualsFloat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class NumberUtilsTest {

  @Test
  public void testToBoolean() {
    assertEquals(true, NumberUtils.toBoolean(1));
    assertEquals(true, NumberUtils.toBoolean(-1));
    assertEquals(true, NumberUtils.toBoolean(325324));
    assertEquals(true, NumberUtils.toBoolean(Integer.MAX_VALUE));
    assertEquals(true, NumberUtils.toBoolean(Integer.MIN_VALUE));
    assertEquals(false, NumberUtils.toBoolean(0));
  }

  @Test
  public void testToInt() {
    assertEquals(1, NumberUtils.toInt(true));
    assertEquals(0, NumberUtils.toInt(false));
  }

  @Test
  public void testParseInt() {
    assertEquals(1231, NumberUtils.parseInt("1231", 0));
    assertEquals(-1231, NumberUtils.parseInt("-1231", 0));
    assertEquals(0, NumberUtils.parseInt("   1231", 0));
    assertEquals(0, NumberUtils.parseInt(null, 0));
  }

  @Test
  public void testParseLong() {
    assertEquals(122343256435631L, NumberUtils.parseLong("122343256435631", 0));
    assertEquals(-122343256435631L, NumberUtils.parseLong("-122343256435631", 0));
    assertEquals(0, NumberUtils.parseLong("   122343256435631", 0));
    assertEquals(0, NumberUtils.parseLong(null, 0));
  }

  @Test
  public void testParseFloat() {
    assertEqualsFloat(325.534623f, NumberUtils.parseFloat("325.534623", 0));
    assertEqualsFloat(-325.534623f, NumberUtils.parseFloat("-325.534623", 0));
    assertEqualsFloat(0, NumberUtils.parseFloat("das 325.534623", 0));
    assertEqualsFloat(0, NumberUtils.parseFloat(null, 0));
  }

  @Test
  public void testParseDouble() {
    assertEqualsDouble(325.5346374511719, NumberUtils.parseDouble("325.5346374511719", 0));
    assertEqualsDouble(-325.5346374511719, NumberUtils.parseDouble("-325.5346374511719", 0));
    assertEqualsDouble(0, NumberUtils.parseDouble("ewr  325.5346374511719", 0));
    assertEqualsDouble(0, NumberUtils.parseDouble(null, 0));
  }

  @Test
  public void testBinaryPrefix() {
    assertEquals("34", NumberUtils.binaryPrefix(34));
    assertEquals("-54", NumberUtils.binaryPrefix(-54));
    assertEquals("0", NumberUtils.binaryPrefix(0));
    assertEquals("1.0K", NumberUtils.binaryPrefix(1024));
    assertEquals("1.2K", NumberUtils.binaryPrefix(1234));
    assertEquals("-1.2K", NumberUtils.binaryPrefix(-1234));
    assertEquals("40.6M", NumberUtils.binaryPrefix(42523543));
    assertEquals("-71.2T", NumberUtils.binaryPrefix(-78324642523543L));

    assertEquals("34", NumberUtils.binaryPrefix(34, 5));
    assertEquals("-54", NumberUtils.binaryPrefix(-54, 5));
    assertEquals("0", NumberUtils.binaryPrefix(0, 5));
    assertEquals("1.00000K", NumberUtils.binaryPrefix(1024, 5));
    assertEquals("1.20508K", NumberUtils.binaryPrefix(1234, 5));
    assertEquals("-1.20508K", NumberUtils.binaryPrefix(-1234, 5));
    assertEquals("40.55361M", NumberUtils.binaryPrefix(42523543, 5));
    assertEquals("-71.2358T", NumberUtils.binaryPrefix(-78324642523543L, 4));
  }

  @Test
  public void testMetricPrefix() {
    assertEquals("34", NumberUtils.metricPrefix(34));
    assertEquals("-54", NumberUtils.metricPrefix(-54));
    assertEquals("0", NumberUtils.metricPrefix(0));
    assertEquals("1.1K", NumberUtils.metricPrefix(1094));
    assertEquals("-78.3T", NumberUtils.metricPrefix(-78324642523543L));

    assertEquals("34", NumberUtils.metricPrefix(34, 4));
    assertEquals("-54", NumberUtils.metricPrefix(-54, 3));
    assertEquals("0", NumberUtils.metricPrefix(0, 6));
    assertEquals("1.094K", NumberUtils.metricPrefix(1094, 3));
    assertEquals("-78.32464T", NumberUtils.metricPrefix(-78324642523543L, 5));

    assertEquals("34", NumberUtils.metricPrefix(34, 4));
    assertEquals("-54", NumberUtils.metricPrefix(-54, 3));
    assertEquals("0", NumberUtils.metricPrefix(0, 6));
    assertEquals("1.094K", NumberUtils.metricPrefix(1094, 3));
    assertEquals("-78.32464T", NumberUtils.metricPrefix(-78324642523543L, 5));

    assertEquals("1.4G", NumberUtils.metricPrefix(11, 1, 2));
    assertEquals("-1.4G", NumberUtils.metricPrefix(11, 1, -2));
    assertEquals("1G", NumberUtils.metricPrefix(11, 0, 2));
    assertEquals("1G", NumberUtils.metricPrefix(11, -1, 2));
    try {
      assertEquals("1G", NumberUtils.metricPrefix(11, 0, 0));
      fail();
    } catch (ArithmeticException e) {
    }
    try {
      assertEquals("1G", NumberUtils.metricPrefix(11, 0, 1));
      fail();
    } catch (ArithmeticException e) {
    }
    try {
      assertEquals("1G", NumberUtils.metricPrefix(11, 0, -1));
      fail();
    } catch (ArithmeticException e) {
    }
  }
}
