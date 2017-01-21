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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests {@link StringUtils} - StartsWith/EndsWith methods
 */
public class StringUtilsStartsEndsWithTest {

  private static final String foo = "foo";
  private static final String bar = "bar";
  private static final String foobar = "foobar";
  private static final String FOO = "FOO";
  private static final String BAR = "BAR";
  private static final String FOOBAR = "FOOBAR";

  //-----------------------------------------------------------------------

  /**
   * Test StringUtils.startsWith()
   */
  @Test
  public void testStartsWith() {
    assertTrue("startsWith(null, null)", StringUtils.startsWith(null, (String) null));
    assertFalse("startsWith(FOOBAR, null)", StringUtils.startsWith(FOOBAR, (String) null));
    assertFalse("startsWith(null, FOO)", StringUtils.startsWith(null, FOO));
    assertTrue("startsWith(FOOBAR, \"\")", StringUtils.startsWith(FOOBAR, ""));

    assertTrue("startsWith(foobar, foo)", StringUtils.startsWith(foobar, foo));
    assertTrue("startsWith(FOOBAR, FOO)", StringUtils.startsWith(FOOBAR, FOO));
    assertFalse("startsWith(foobar, FOO)", StringUtils.startsWith(foobar, FOO));
    assertFalse("startsWith(FOOBAR, foo)", StringUtils.startsWith(FOOBAR, foo));

    assertFalse("startsWith(foo, foobar)", StringUtils.startsWith(foo, foobar));
    assertFalse("startsWith(foo, foobar)", StringUtils.startsWith(bar, foobar));

    assertFalse("startsWith(foobar, bar)", StringUtils.startsWith(foobar, bar));
    assertFalse("startsWith(FOOBAR, BAR)", StringUtils.startsWith(FOOBAR, BAR));
    assertFalse("startsWith(foobar, BAR)", StringUtils.startsWith(foobar, BAR));
    assertFalse("startsWith(FOOBAR, bar)", StringUtils.startsWith(FOOBAR, bar));
  }

  @Test
  public void testStartsWithAny() {
    assertFalse(StringUtils.startsWithAny(null, (String[]) null));
    assertFalse(StringUtils.startsWithAny(null, "abc"));
    assertFalse(StringUtils.startsWithAny("abcxyz", (String[]) null));
    assertFalse(StringUtils.startsWithAny("abcxyz"));
    assertTrue(StringUtils.startsWithAny("abcxyz", "abc"));
    assertTrue(StringUtils.startsWithAny("abcxyz", null, "xyz", "abc"));
    assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "abcd"));
    assertTrue(StringUtils.startsWithAny("abcxyz", new String[]{""}));
    assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX"));
    assertFalse(StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc"));
  }


  /**
   * Test StringUtils.endsWith()
   */
  @Test
  public void testEndsWith() {
    assertTrue("endsWith(null, null)", StringUtils.endsWith(null, (String) null));
    assertFalse("endsWith(FOOBAR, null)", StringUtils.endsWith(FOOBAR, (String) null));
    assertFalse("endsWith(null, FOO)", StringUtils.endsWith(null, FOO));
    assertTrue("endsWith(FOOBAR, \"\")", StringUtils.endsWith(FOOBAR, ""));

    assertFalse("endsWith(foobar, foo)", StringUtils.endsWith(foobar, foo));
    assertFalse("endsWith(FOOBAR, FOO)", StringUtils.endsWith(FOOBAR, FOO));
    assertFalse("endsWith(foobar, FOO)", StringUtils.endsWith(foobar, FOO));
    assertFalse("endsWith(FOOBAR, foo)", StringUtils.endsWith(FOOBAR, foo));

    assertFalse("endsWith(foo, foobar)", StringUtils.endsWith(foo, foobar));
    assertFalse("endsWith(foo, foobar)", StringUtils.endsWith(bar, foobar));

    assertTrue("endsWith(foobar, bar)", StringUtils.endsWith(foobar, bar));
    assertTrue("endsWith(FOOBAR, BAR)", StringUtils.endsWith(FOOBAR, BAR));
    assertFalse("endsWith(foobar, BAR)", StringUtils.endsWith(foobar, BAR));
    assertFalse("endsWith(FOOBAR, bar)", StringUtils.endsWith(FOOBAR, bar));

    // "alpha,beta,gamma,delta".endsWith("delta")
    assertTrue("endsWith(\u03B1\u03B2\u03B3\u03B4, \u03B4)",
        StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B4"));
    // "alpha,beta,gamma,delta".endsWith("gamma,DELTA")
    assertFalse("endsWith(\u03B1\u03B2\u03B3\u03B4, \u03B3\u0394)",
        StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B3\u0394"));
  }

  @Test
  public void testEndsWithAny() {
    assertFalse("StringUtils.endsWithAny(null, null)",
        StringUtils.endsWithAny(null, (String) null));
    assertFalse("StringUtils.endsWithAny(null, new String[] {abc})",
        StringUtils.endsWithAny(null, new String[]{"abc"}));
    assertFalse("StringUtils.endsWithAny(abcxyz, null)",
        StringUtils.endsWithAny("abcxyz", (String) null));
    assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {\"\"})",
        StringUtils.endsWithAny("abcxyz", new String[]{""}));
    assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {xyz})",
        StringUtils.endsWithAny("abcxyz", new String[]{"xyz"}));
    assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {null, xyz, abc})",
        StringUtils.endsWithAny("abcxyz", new String[]{null, "xyz", "abc"}));
    assertFalse("StringUtils.endsWithAny(defg, new String[] {null, xyz, abc})",
        StringUtils.endsWithAny("defg", new String[]{null, "xyz", "abc"}));
    assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "XYZ"));
    assertFalse(StringUtils.endsWithAny("abcXYZ", "def", "xyz"));
    assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "YZ"));

    /*
     * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
     * doesn't exactly match the vararg parameter type.
     * Cast to CharSequence[] to confirm the non-varargs invocation,
     * or pass individual arguments of type CharSequence for a varargs invocation.
     *
     * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
     */
    assertFalse(StringUtils.endsWithAny("abcXYZ", (String) null));
    assertFalse(StringUtils.endsWithAny("abcXYZ", (String[]) null));
    assertTrue(StringUtils.endsWithAny("abcXYZ", ""));
  }
}
