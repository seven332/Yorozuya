/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hippo.yorozuya;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests {@link StringUtils} - Substring methods
 */
public class StringUtilsSubstringTest {

  private static final String FOO = "foo";
  private static final String BAR = "bar";
  private static final String BAZ = "baz";
  private static final String FOOBAR = "foobar";
  private static final String SENTENCE = "foo bar baz";

  //-----------------------------------------------------------------------

  @Test
  public void testSubstring_StringInt() {
    assertEquals(null, StringUtils.substring(null, 0));
    assertEquals("", StringUtils.substring("", 0));
    assertEquals("", StringUtils.substring("", 2));

    assertEquals("", StringUtils.substring(SENTENCE, 80));
    assertEquals(BAZ, StringUtils.substring(SENTENCE, 8));
    assertEquals(BAZ, StringUtils.substring(SENTENCE, -3));
    assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0));
    assertEquals("abc", StringUtils.substring("abc", -4));
    assertEquals("abc", StringUtils.substring("abc", -3));
    assertEquals("bc", StringUtils.substring("abc", -2));
    assertEquals("c", StringUtils.substring("abc", -1));
    assertEquals("abc", StringUtils.substring("abc", 0));
    assertEquals("bc", StringUtils.substring("abc", 1));
    assertEquals("c", StringUtils.substring("abc", 2));
    assertEquals("", StringUtils.substring("abc", 3));
    assertEquals("", StringUtils.substring("abc", 4));
  }

  @Test
  public void testSubstring_StringIntInt() {
    assertEquals(null, StringUtils.substring(null, 0, 0));
    assertEquals(null, StringUtils.substring(null, 1, 2));
    assertEquals("", StringUtils.substring("", 0, 0));
    assertEquals("", StringUtils.substring("", 1, 2));
    assertEquals("", StringUtils.substring("", -2, -1));

    assertEquals("", StringUtils.substring(SENTENCE, 8, 6));
    assertEquals(FOO, StringUtils.substring(SENTENCE, 0, 3));
    assertEquals("o", StringUtils.substring(SENTENCE, -9, 3));
    assertEquals(FOO, StringUtils.substring(SENTENCE, 0, -8));
    assertEquals("o", StringUtils.substring(SENTENCE, -9, -8));
    assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0, 80));
    assertEquals("", StringUtils.substring(SENTENCE, 2, 2));
    assertEquals("b", StringUtils.substring("abc", -2, -1));
  }

  @Test
  public void testCountMatches_char() {
    assertEquals(0, StringUtils.countMatches(null, 'D'));
    assertEquals(5, StringUtils.countMatches("one long someone sentence of one", ' '));
    assertEquals(6, StringUtils.countMatches("one long someone sentence of one", 'o'));
  }
}
