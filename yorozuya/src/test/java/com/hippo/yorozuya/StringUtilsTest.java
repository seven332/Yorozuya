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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.regex.PatternSyntaxException;
import org.junit.Test;

/**
 * Unit tests for methods of {@link StringUtils}
 * which been moved to their own test classes.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class StringUtilsTest {

  static final String WHITESPACE;
  static final String NON_WHITESPACE;
  static final String HARD_SPACE;
  static final String TRIMMABLE;
  static final String NON_TRIMMABLE;

  static {
    String ws = "";
    String nws = "";
    final String hs = String.valueOf(((char) 160));
    String tr = "";
    String ntr = "";
    for (int i = 0; i < Character.MAX_VALUE; i++) {
      if (Character.isWhitespace((char) i)) {
        ws += String.valueOf((char) i);
        if (i > 32) {
          ntr += String.valueOf((char) i);
        }
      } else if (i < 40) {
        nws += String.valueOf((char) i);
      }
    }
    for (int i = 0; i <= 32; i++) {
      tr += String.valueOf((char) i);
    }
    WHITESPACE = ws;
    NON_WHITESPACE = nws;
    HARD_SPACE = hs;
    TRIMMABLE = tr;
    NON_TRIMMABLE = ntr;
  }

  @Test
  public void testSplit_String() {
    assertNull(StringUtils.split(null));
    assertEquals(0, StringUtils.split("").length);

    String str = "a b  .c";
    String[] res = StringUtils.split(str);
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals(".c", res[2]);

    str = " a ";
    res = StringUtils.split(str);
    assertEquals(1, res.length);
    assertEquals("a", res[0]);

    str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
    res = StringUtils.split(str);
    assertEquals(2, res.length);
    assertEquals("a", res[0]);
    assertEquals("b" + NON_WHITESPACE + "c", res[1]);
  }

  @Test
  public void testSplit_StringChar() {
    assertNull(StringUtils.split(null, '.'));
    assertEquals(0, StringUtils.split("", '.').length);

    String str = "a.b.. c";
    String[] res = StringUtils.split(str, '.');
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals(" c", res[2]);

    str = ".a.";
    res = StringUtils.split(str, '.');
    assertEquals(1, res.length);
    assertEquals("a", res[0]);

    str = "a b c";
    res = StringUtils.split(str, ' ');
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals("c", res[2]);
  }

  @Test
  public void testSplit_StringString_StringStringInt() {
    assertNull(StringUtils.split(null, "."));
    assertNull(StringUtils.split(null, ".", 3));

    assertEquals(0, StringUtils.split("", ".").length);
    assertEquals(0, StringUtils.split("", ".", 3).length);

    innerTestSplit('.', ".", ' ');
    innerTestSplit('.', ".", ',');
    innerTestSplit('.', ".,", 'x');
    for (int i = 0; i < WHITESPACE.length(); i++) {
      for (int j = 0; j < NON_WHITESPACE.length(); j++) {
        innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
        innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)),
            NON_WHITESPACE.charAt(j));
      }
    }

    String[] results;
    final String[] expectedResults = {"ab", "de fg"};
    results = StringUtils.split("ab   de fg", null, 2);
    assertEquals(expectedResults.length, results.length);
    for (int i = 0; i < expectedResults.length; i++) {
      assertEquals(expectedResults[i], results[i]);
    }

    final String[] expectedResults2 = {"ab", "cd:ef"};
    results = StringUtils.split("ab:cd:ef", ":", 2);
    assertEquals(expectedResults2.length, results.length);
    for (int i = 0; i < expectedResults2.length; i++) {
      assertEquals(expectedResults2[i], results[i]);
    }
  }

  private void innerTestSplit(final char separator, final String sepStr, final char noMatch) {
    final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
        "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

    final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
    String[] res;
    // (str, sepStr)
    res = StringUtils.split(str, sepStr);
    assertEquals(msg, 3, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, noMatch + "c", res[2]);

    final String str2 = separator + "a" + separator;
    res = StringUtils.split(str2, sepStr);
    assertEquals(msg, 1, res.length);
    assertEquals(msg, "a", res[0]);

    res = StringUtils.split(str, sepStr, -1);
    assertEquals(msg, 3, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, noMatch + "c", res[2]);

    res = StringUtils.split(str, sepStr, 0);
    assertEquals(msg, 3, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, noMatch + "c", res[2]);

    res = StringUtils.split(str, sepStr, 1);
    assertEquals(msg, 1, res.length);
    assertEquals(msg, str, res[0]);

    res = StringUtils.split(str, sepStr, 2);
    assertEquals(msg, 2, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, str.substring(2), res[1]);
  }

  @Test
  public void testSplitPreserveAllTokens_String() {
    assertNull(StringUtils.splitPreserveAllTokens(null));
    assertEquals(0, StringUtils.splitPreserveAllTokens("").length);

    String str = "abc def";
    String[] res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(2, res.length);
    assertEquals("abc", res[0]);
    assertEquals("def", res[1]);

    str = "abc  def";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(3, res.length);
    assertEquals("abc", res[0]);
    assertEquals("", res[1]);
    assertEquals("def", res[2]);

    str = " abc ";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(3, res.length);
    assertEquals("", res[0]);
    assertEquals("abc", res[1]);
    assertEquals("", res[2]);

    str = "a b .c";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals(".c", res[2]);

    str = " a b .c";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("b", res[2]);
    assertEquals(".c", res[3]);

    str = "a  b  .c";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(5, res.length);
    assertEquals("a", res[0]);
    assertEquals("", res[1]);
    assertEquals("b", res[2]);
    assertEquals("", res[3]);
    assertEquals(".c", res[4]);

    str = " a  ";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("", res[2]);
    assertEquals("", res[3]);

    str = " a  b";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("", res[2]);
    assertEquals("b", res[3]);

    str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
    res = StringUtils.splitPreserveAllTokens(str);
    assertEquals(WHITESPACE.length() + 1, res.length);
    assertEquals("a", res[0]);
    for (int i = 1; i < WHITESPACE.length() - 1; i++) {
      assertEquals("", res[i]);
    }
    assertEquals("b" + NON_WHITESPACE + "c", res[WHITESPACE.length()]);
  }

  @Test
  public void testSplitPreserveAllTokens_StringChar() {
    assertNull(StringUtils.splitPreserveAllTokens(null, '.'));
    assertEquals(0, StringUtils.splitPreserveAllTokens("", '.').length);

    String str = "a.b. c";
    String[] res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals(" c", res[2]);

    str = "a.b.. c";
    res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(4, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals("", res[2]);
    assertEquals(" c", res[3]);

    str = ".a.";
    res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(3, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("", res[2]);

    str = ".a..";
    res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("", res[2]);
    assertEquals("", res[3]);

    str = "..a.";
    res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("", res[1]);
    assertEquals("a", res[2]);
    assertEquals("", res[3]);

    str = "..a";
    res = StringUtils.splitPreserveAllTokens(str, '.');
    assertEquals(3, res.length);
    assertEquals("", res[0]);
    assertEquals("", res[1]);
    assertEquals("a", res[2]);

    str = "a b c";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(3, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals("c", res[2]);

    str = "a  b  c";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(5, res.length);
    assertEquals("a", res[0]);
    assertEquals("", res[1]);
    assertEquals("b", res[2]);
    assertEquals("", res[3]);
    assertEquals("c", res[4]);

    str = " a b c";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(4, res.length);
    assertEquals("", res[0]);
    assertEquals("a", res[1]);
    assertEquals("b", res[2]);
    assertEquals("c", res[3]);

    str = "  a b c";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(5, res.length);
    assertEquals("", res[0]);
    assertEquals("", res[1]);
    assertEquals("a", res[2]);
    assertEquals("b", res[3]);
    assertEquals("c", res[4]);

    str = "a b c ";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(4, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals("c", res[2]);
    assertEquals("", res[3]);

    str = "a b c  ";
    res = StringUtils.splitPreserveAllTokens(str, ' ');
    assertEquals(5, res.length);
    assertEquals("a", res[0]);
    assertEquals("b", res[1]);
    assertEquals("c", res[2]);
    assertEquals("", res[3]);
    assertEquals("", res[3]);

    // Match example in javadoc
    {
      String[] results;
      final String[] expectedResults = {"a", "", "b", "c"};
      results = StringUtils.splitPreserveAllTokens("a..b.c", '.');
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }
  }

  @Test
  public void testSplitPreserveAllTokens_StringString_StringStringInt() {
    assertNull(StringUtils.splitPreserveAllTokens(null, "."));
    assertNull(StringUtils.splitPreserveAllTokens(null, ".", 3));

    assertEquals(0, StringUtils.splitPreserveAllTokens("", ".").length);
    assertEquals(0, StringUtils.splitPreserveAllTokens("", ".", 3).length);

    innerTestSplitPreserveAllTokens('.', ".", ' ');
    innerTestSplitPreserveAllTokens('.', ".", ',');
    innerTestSplitPreserveAllTokens('.', ".,", 'x');
    for (int i = 0; i < WHITESPACE.length(); i++) {
      for (int j = 0; j < NON_WHITESPACE.length(); j++) {
        innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
        innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)),
            NON_WHITESPACE.charAt(j));
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "de fg"};
      results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "  de fg"};
      results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "::de:fg"};
      results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "", " de fg"};
      results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "", "", "de fg"};
      results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      final String[] expectedResults = {"ab", "cd:ef"};
      String[] results;
      results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", ":cd:ef"};
      results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "", ":cd:ef"};
      results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"ab", "", "", "cd:ef"};
      results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
      results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

    {
      String[] results;
      final String[] expectedResults = {"", "", "ab", "", "", "cd:ef"};
      results = StringUtils.splitPreserveAllTokens("::ab:::cd:ef", ":", 6);
      assertEquals(expectedResults.length, results.length);
      for (int i = 0; i < expectedResults.length; i++) {
        assertEquals(expectedResults[i], results[i]);
      }
    }

  }

  private void innerTestSplitPreserveAllTokens(final char separator, final String sepStr,
      final char noMatch) {
    final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
        "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

    final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
    String[] res;
    // (str, sepStr)
    res = StringUtils.splitPreserveAllTokens(str, sepStr);
    assertEquals(msg, 4, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, "", res[2]);
    assertEquals(msg, noMatch + "c", res[3]);

    final String str2 = separator + "a" + separator;
    res = StringUtils.splitPreserveAllTokens(str2, sepStr);
    assertEquals(msg, 3, res.length);
    assertEquals(msg, "", res[0]);
    assertEquals(msg, "a", res[1]);
    assertEquals(msg, "", res[2]);

    res = StringUtils.splitPreserveAllTokens(str, sepStr, -1);
    assertEquals(msg, 4, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, "", res[2]);
    assertEquals(msg, noMatch + "c", res[3]);

    res = StringUtils.splitPreserveAllTokens(str, sepStr, 0);
    assertEquals(msg, 4, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, "b", res[1]);
    assertEquals(msg, "", res[2]);
    assertEquals(msg, noMatch + "c", res[3]);

    res = StringUtils.splitPreserveAllTokens(str, sepStr, 1);
    assertEquals(msg, 1, res.length);
    assertEquals(msg, str, res[0]);

    res = StringUtils.splitPreserveAllTokens(str, sepStr, 2);
    assertEquals(msg, 2, res.length);
    assertEquals(msg, "a", res[0]);
    assertEquals(msg, str.substring(2), res[1]);
  }

  @Test
  public void testLang623() {
    assertEquals("t", StringUtils.replaceChars("\u00DE", '\u00DE', 't'));
    assertEquals("t", StringUtils.replaceChars("\u00FE", '\u00FE', 't'));
  }

  @Test
  public void testReplace_StringStringString() {
    assertNull(StringUtils.replace(null, null, null));
    assertNull(StringUtils.replace(null, null, "any"));
    assertNull(StringUtils.replace(null, "any", null));
    assertNull(StringUtils.replace(null, "any", "any"));

    assertEquals("", StringUtils.replace("", null, null));
    assertEquals("", StringUtils.replace("", null, "any"));
    assertEquals("", StringUtils.replace("", "any", null));
    assertEquals("", StringUtils.replace("", "any", "any"));

    assertEquals("FOO", StringUtils.replace("FOO", "", "any"));
    assertEquals("FOO", StringUtils.replace("FOO", null, "any"));
    assertEquals("FOO", StringUtils.replace("FOO", "F", null));
    assertEquals("FOO", StringUtils.replace("FOO", null, null));

    assertEquals("", StringUtils.replace("foofoofoo", "foo", ""));
    assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar"));
    assertEquals("farfarfar", StringUtils.replace("foofoofoo", "oo", "ar"));
  }

  @Test
  public void testReplaceIgnoreCase_StringStringString() {
    assertEquals(null, StringUtils.replaceIgnoreCase(null, null, null));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, null, "any"));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", null));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", "any"));

    assertEquals("", StringUtils.replaceIgnoreCase("", null, null));
    assertEquals("", StringUtils.replaceIgnoreCase("", null, "any"));
    assertEquals("", StringUtils.replaceIgnoreCase("", "any", null));
    assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any"));

    assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "", "any"));
    assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, "any"));
    assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "F", null));
    assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, null));

    assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "foo", ""));
    assertEquals("barbarbar", StringUtils.replaceIgnoreCase("foofoofoo", "foo", "bar"));
    assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofoofoo", "oo", "ar"));

    // IgnoreCase
    assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "FOO", ""));
    assertEquals("barbarbar", StringUtils.replaceIgnoreCase("fooFOOfoo", "foo", "bar"));
    assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofOOfoo", "OO", "ar"));
  }

  @Test
  public void testReplacePattern() {
    assertNull(StringUtils.replacePattern(null, "", ""));
    assertEquals("any", StringUtils.replacePattern("any", null, ""));
    assertEquals("any", StringUtils.replacePattern("any", "", null));

    assertEquals("zzz", StringUtils.replacePattern("", "", "zzz"));
    assertEquals("zzz", StringUtils.replacePattern("", ".*", "zzz"));
    assertEquals("", StringUtils.replacePattern("", ".+", "zzz"));

    assertEquals("z", StringUtils.replacePattern("<__>\n<__>", "<.*>", "z"));
    assertEquals("z", StringUtils.replacePattern("<__>\\n<__>", "<.*>", "z"));
    assertEquals("X", StringUtils.replacePattern("<A>\nxy\n</A>", "<A>.*</A>", "X"));

    assertEquals("ABC___123", StringUtils.replacePattern("ABCabc123", "[a-z]", "_"));
    assertEquals("ABC_123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_"));
    assertEquals("ABC123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", ""));
    assertEquals("Lorem_ipsum_dolor_sit",
        StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));
  }

  @Test
  public void testRemovePattern() {
    assertNull(StringUtils.removePattern(null, ""));
    assertEquals("any", StringUtils.removePattern("any", null));

    assertEquals("", StringUtils.removePattern("", ""));
    assertEquals("", StringUtils.removePattern("", ".*"));
    assertEquals("", StringUtils.removePattern("", ".+"));

    assertEquals("AB", StringUtils.removePattern("A<__>\n<__>B", "<.*>"));
    assertEquals("AB", StringUtils.removePattern("A<__>\\n<__>B", "<.*>"));
    assertEquals("", StringUtils.removePattern("<A>x\\ny</A>", "<A>.*</A>"));
    assertEquals("", StringUtils.removePattern("<A>\nxy\n</A>", "<A>.*</A>"));

    assertEquals("ABC123", StringUtils.removePattern("ABCabc123", "[a-z]"));
  }

  @Test
  public void testReplaceAll() {
    assertNull(StringUtils.replaceAll(null, "", ""));

    assertEquals("any", StringUtils.replaceAll("any", null, ""));
    assertEquals("any", StringUtils.replaceAll("any", "", null));

    assertEquals("zzz", StringUtils.replaceAll("", "", "zzz"));
    assertEquals("zzz", StringUtils.replaceAll("", ".*", "zzz"));
    assertEquals("", StringUtils.replaceAll("", ".+", "zzz"));
    assertEquals("ZZaZZbZZcZZ", StringUtils.replaceAll("abc", "", "ZZ"));

    assertEquals("z\nz", StringUtils.replaceAll("<__>\n<__>", "<.*>", "z"));
    assertEquals("z", StringUtils.replaceAll("<__>\n<__>", "(?s)<.*>", "z"));

    assertEquals("ABC___123", StringUtils.replaceAll("ABCabc123", "[a-z]", "_"));
    assertEquals("ABC_123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_"));
    assertEquals("ABC123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", ""));
    assertEquals("Lorem_ipsum_dolor_sit",
        StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));

    try {
      StringUtils.replaceAll("any", "{badRegexSyntax}", "");
      fail("StringUtils.replaceAll expecting PatternSyntaxException");
    } catch (final PatternSyntaxException ex) {
      // empty
    }
  }

  @Test
  public void testReplaceFirst() {
    assertNull(StringUtils.replaceFirst(null, "", ""));

    assertEquals("any", StringUtils.replaceFirst("any", null, ""));
    assertEquals("any", StringUtils.replaceFirst("any", "", null));

    assertEquals("zzz", StringUtils.replaceFirst("", "", "zzz"));
    assertEquals("zzz", StringUtils.replaceFirst("", ".*", "zzz"));
    assertEquals("", StringUtils.replaceFirst("", ".+", "zzz"));
    assertEquals("ZZabc", StringUtils.replaceFirst("abc", "", "ZZ"));

    assertEquals("z\n<__>", StringUtils.replaceFirst("<__>\n<__>", "<.*>", "z"));
    assertEquals("z", StringUtils.replaceFirst("<__>\n<__>", "(?s)<.*>", "z"));

    assertEquals("ABC_bc123", StringUtils.replaceFirst("ABCabc123", "[a-z]", "_"));
    assertEquals("ABC_123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_"));
    assertEquals("ABC123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", ""));
    assertEquals("Lorem_ipsum  dolor   sit",
        StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));

    try {
      StringUtils.replaceFirst("any", "{badRegexSyntax}", "");
      fail("StringUtils.replaceFirst expecting PatternSyntaxException");
    } catch (final PatternSyntaxException ex) {
      // empty
    }
  }

  @Test
  public void testReplace_StringStringStringInt() {
    assertNull(StringUtils.replace(null, null, null, 2));
    assertNull(StringUtils.replace(null, null, "any", 2));
    assertNull(StringUtils.replace(null, "any", null, 2));
    assertNull(StringUtils.replace(null, "any", "any", 2));

    assertEquals("", StringUtils.replace("", null, null, 2));
    assertEquals("", StringUtils.replace("", null, "any", 2));
    assertEquals("", StringUtils.replace("", "any", null, 2));
    assertEquals("", StringUtils.replace("", "any", "any", 2));

    final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});
    assertSame(str, StringUtils.replace(str, "x", "", -1));

    assertEquals("f", StringUtils.replace("oofoo", "o", "", -1));
    assertEquals("oofoo", StringUtils.replace("oofoo", "o", "", 0));
    assertEquals("ofoo", StringUtils.replace("oofoo", "o", "", 1));
    assertEquals("foo", StringUtils.replace("oofoo", "o", "", 2));
    assertEquals("fo", StringUtils.replace("oofoo", "o", "", 3));
    assertEquals("f", StringUtils.replace("oofoo", "o", "", 4));

    assertEquals("f", StringUtils.replace("oofoo", "o", "", -5));
    assertEquals("f", StringUtils.replace("oofoo", "o", "", 1000));
  }

  @Test
  public void testReplaceIgnoreCase_StringStringStringInt() {
    assertEquals(null, StringUtils.replaceIgnoreCase(null, null, null, 2));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, null, "any", 2));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", null, 2));
    assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", "any", 2));

    assertEquals("", StringUtils.replaceIgnoreCase("", null, null, 2));
    assertEquals("", StringUtils.replaceIgnoreCase("", null, "any", 2));
    assertEquals("", StringUtils.replaceIgnoreCase("", "any", null, 2));
    assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any", 2));

    String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});
    assertSame(str, StringUtils.replaceIgnoreCase(str, "x", "", -1));

    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -1));
    assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 0));
    assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1));
    assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 2));
    assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 3));
    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 4));

    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -5));
    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1000));

    // IgnoreCase
    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -1));
    assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 0));
    assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1));
    assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 2));
    assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 3));
    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 4));

    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -5));
    assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1000));
  }

  @Test
  public void testReplaceOnce_StringStringString() {
    assertNull(StringUtils.replaceOnce(null, null, null));
    assertNull(StringUtils.replaceOnce(null, null, "any"));
    assertNull(StringUtils.replaceOnce(null, "any", null));
    assertNull(StringUtils.replaceOnce(null, "any", "any"));

    assertEquals("", StringUtils.replaceOnce("", null, null));
    assertEquals("", StringUtils.replaceOnce("", null, "any"));
    assertEquals("", StringUtils.replaceOnce("", "any", null));
    assertEquals("", StringUtils.replaceOnce("", "any", "any"));

    assertEquals("FOO", StringUtils.replaceOnce("FOO", "", "any"));
    assertEquals("FOO", StringUtils.replaceOnce("FOO", null, "any"));
    assertEquals("FOO", StringUtils.replaceOnce("FOO", "F", null));
    assertEquals("FOO", StringUtils.replaceOnce("FOO", null, null));

    assertEquals("foofoo", StringUtils.replaceOnce("foofoofoo", "foo", ""));
  }

  @Test
  public void testReplaceOnceIgnoreCase_StringStringString() {
    assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, null, null));
    assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, null, "any"));
    assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, "any", null));
    assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, "any", "any"));

    assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, null));
    assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, "any"));
    assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", null));
    assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", "any"));

    assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "", "any"));
    assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, "any"));
    assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "F", null));
    assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, null));

    assertEquals("foofoo", StringUtils.replaceOnceIgnoreCase("foofoofoo", "foo", ""));

    // Ignore Case
    assertEquals("Foofoo", StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", ""));
  }

  /**
   * Test method for 'StringUtils.replaceEach(String, String[], String[])'
   */
  @Test
  public void testReplace_StringStringArrayStringArray() {
    //JAVADOC TESTS START
    assertNull(StringUtils.replaceEach(null, new String[]{"a"}, new String[]{"b"}));
    assertEquals(StringUtils.replaceEach("", new String[]{"a"}, new String[]{"b"}), "");
    assertEquals(StringUtils.replaceEach("aba", null, null), "aba");
    assertEquals(StringUtils.replaceEach("aba", new String[0], null), "aba");
    assertEquals(StringUtils.replaceEach("aba", null, new String[0]), "aba");
    assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, null), "aba");

    assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""}), "b");
    assertEquals(StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"}), "aba");
    assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}),
        "wcte");
    assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}),
        "dcte");
    //JAVADOC TESTS END

    assertEquals("bcc",
        StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}));
    assertEquals("q651.506bera", StringUtils.replaceEach("d216.102oren",
        new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
        new String[]{"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a",
            "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "5", "6", "7", "8", "9", "1", "2", "3", "4"}));

    // Test null safety inside arrays - LANG-552
    assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{null}), "aba");
    assertEquals(StringUtils.replaceEach("aba", new String[]{"a", "b"}, new String[]{"c", null}),
        "cbc");

    try {
      StringUtils.replaceEach("abba", new String[]{"a"}, new String[]{"b", "a"});
      fail(
          "StringUtils.replaceEach(String, String[], String[]) expecting IllegalArgumentException");
    } catch (final IllegalArgumentException ex) {
      // expected
    }
  }

  /**
   * Test method for 'StringUtils.replaceEachRepeatedly(String, String[], String[])'
   */
  @Test
  public void testReplace_StringStringArrayStringArrayBoolean() {
    //JAVADOC TESTS START
    assertNull(StringUtils.replaceEachRepeatedly(null, new String[]{"a"}, new String[]{"b"}));
    assertEquals(StringUtils.replaceEachRepeatedly("", new String[]{"a"}, new String[]{"b"}), "");
    assertEquals(StringUtils.replaceEachRepeatedly("aba", null, null), "aba");
    assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[0], null), "aba");
    assertEquals(StringUtils.replaceEachRepeatedly("aba", null, new String[0]), "aba");
    assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[0], null), "aba");

    assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}),
        "b");
    assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}),
        "aba");
    assertEquals(
        StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}),
        "wcte");
    assertEquals(
        StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}),
        "tcte");

    try {
      StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"});
      fail("Should be a circular reference");
    } catch (final IllegalStateException e) {
    }

    //JAVADOC TESTS END
  }

  @Test
  public void testReplaceChars_StringCharChar() {
    assertNull(StringUtils.replaceChars(null, 'b', 'z'));
    assertEquals("", StringUtils.replaceChars("", 'b', 'z'));
    assertEquals("azcza", StringUtils.replaceChars("abcba", 'b', 'z'));
    assertEquals("abcba", StringUtils.replaceChars("abcba", 'x', 'z'));
  }

  @Test
  public void testReplaceChars_StringStringString() {
    assertNull(StringUtils.replaceChars(null, null, null));
    assertNull(StringUtils.replaceChars(null, "", null));
    assertNull(StringUtils.replaceChars(null, "a", null));
    assertNull(StringUtils.replaceChars(null, null, ""));
    assertNull(StringUtils.replaceChars(null, null, "x"));

    assertEquals("", StringUtils.replaceChars("", null, null));
    assertEquals("", StringUtils.replaceChars("", "", null));
    assertEquals("", StringUtils.replaceChars("", "a", null));
    assertEquals("", StringUtils.replaceChars("", null, ""));
    assertEquals("", StringUtils.replaceChars("", null, "x"));

    assertEquals("abc", StringUtils.replaceChars("abc", null, null));
    assertEquals("abc", StringUtils.replaceChars("abc", null, ""));
    assertEquals("abc", StringUtils.replaceChars("abc", null, "x"));

    assertEquals("abc", StringUtils.replaceChars("abc", "", null));
    assertEquals("abc", StringUtils.replaceChars("abc", "", ""));
    assertEquals("abc", StringUtils.replaceChars("abc", "", "x"));

    assertEquals("ac", StringUtils.replaceChars("abc", "b", null));
    assertEquals("ac", StringUtils.replaceChars("abc", "b", ""));
    assertEquals("axc", StringUtils.replaceChars("abc", "b", "x"));

    assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
    assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
    assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));

    assertEquals("abcba", StringUtils.replaceChars("abcba", "z", "w"));
    assertSame("abcba", StringUtils.replaceChars("abcba", "z", "w"));

    // Javadoc examples:
    assertEquals("jelly", StringUtils.replaceChars("hello", "ho", "jy"));
    assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
    assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
    assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));

    // From http://issues.apache.org/bugzilla/show_bug.cgi?id=25454
    assertEquals("bcc", StringUtils.replaceChars("abc", "ab", "bc"));
    assertEquals("q651.506bera", StringUtils.replaceChars("d216.102oren",
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",
        "nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM567891234"));
  }

  @Test
  public void testTruncate_StringInt() {
    assertNull(StringUtils.truncate(null, 12));
    try {
      StringUtils.truncate(null, -1);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate(null, -10);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate(null, Integer.MIN_VALUE);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    assertEquals("", StringUtils.truncate("", 10));
    assertEquals("", StringUtils.truncate("", 10));
    assertEquals("abc", StringUtils.truncate("abcdefghij", 3));
    assertEquals("abcdef", StringUtils.truncate("abcdefghij", 6));
    assertEquals("", StringUtils.truncate("abcdefghij", 0));
    try {
      StringUtils.truncate("abcdefghij", -1);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -100);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", Integer.MIN_VALUE);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 10));
    assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE));
    assertEquals("abcde", StringUtils.truncate("abcdefghijklmno", 5));
    assertEquals("abc", StringUtils.truncate("abcdefghijklmno", 3));
  }

  @Test
  public void testTruncate_StringIntInt() {
    assertNull(StringUtils.truncate(null, 0, 12));
    try {
      StringUtils.truncate(null, -1, 0);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate(null, -10, -4);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate(null, Integer.MIN_VALUE, Integer.MIN_VALUE);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    assertNull(StringUtils.truncate(null, 10, 12));
    assertEquals("", StringUtils.truncate("", 0, 10));
    assertEquals("", StringUtils.truncate("", 2, 10));
    assertEquals("abc", StringUtils.truncate("abcdefghij", 0, 3));
    assertEquals("fghij", StringUtils.truncate("abcdefghij", 5, 6));
    assertEquals("", StringUtils.truncate("abcdefghij", 0, 0));
    try {
      StringUtils.truncate("abcdefghij", 0, -1);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", 0, -10);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", 0, -100);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", 1, -100);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", 0, Integer.MIN_VALUE);
      fail("maxWith cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -1, 0);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -10, 0);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -100, 1);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, 0);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -1, -1);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -10, -10);
      fail("offset cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", -100, -100);
      fail("offset  cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    try {
      StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, Integer.MIN_VALUE);
      fail("offset  cannot be negative");
    } catch (Exception e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
    final String raspberry = "raspberry peach";
    assertEquals("peach", StringUtils.truncate(raspberry, 10, 15));
    assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 0, 10));
    assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE));
    assertEquals("bcdefghijk", StringUtils.truncate("abcdefghijklmno", 1, 10));
    assertEquals("cdefghijkl", StringUtils.truncate("abcdefghijklmno", 2, 10));
    assertEquals("defghijklm", StringUtils.truncate("abcdefghijklmno", 3, 10));
    assertEquals("efghijklmn", StringUtils.truncate("abcdefghijklmno", 4, 10));
    assertEquals("fghijklmno", StringUtils.truncate("abcdefghijklmno", 5, 10));
    assertEquals("fghij", StringUtils.truncate("abcdefghijklmno", 5, 5));
    assertEquals("fgh", StringUtils.truncate("abcdefghijklmno", 5, 3));
    assertEquals("klm", StringUtils.truncate("abcdefghijklmno", 10, 3));
    assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE));
    assertEquals("n", StringUtils.truncate("abcdefghijklmno", 13, 1));
    assertEquals("no", StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE));
    assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, 1));
    assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE));
    assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, 1));
    assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE));
    assertEquals("", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE));
  }

  /**
   * A sanity check for {@link StringUtils#EMPTY}.
   */
  @Test
  public void testEMPTY() {
    assertNotNull(StringUtils.EMPTY);
    assertEquals("", StringUtils.EMPTY);
    assertEquals(0, StringUtils.EMPTY.length());
  }

  @Test
  public void testRemoveStart() {
    // StringUtils.removeStart("", *)        = ""
    assertNull(StringUtils.removeStart(null, null));
    assertNull(StringUtils.removeStart(null, ""));
    assertNull(StringUtils.removeStart(null, "a"));

    // StringUtils.removeStart(*, null)      = *
    assertEquals(StringUtils.removeStart("", null), "");
    assertEquals(StringUtils.removeStart("", ""), "");
    assertEquals(StringUtils.removeStart("", "a"), "");

    // All others:
    assertEquals(StringUtils.removeStart("www.domain.com", "www."), "domain.com");
    assertEquals(StringUtils.removeStart("domain.com", "www."), "domain.com");
    assertEquals(StringUtils.removeStart("domain.com", ""), "domain.com");
    assertEquals(StringUtils.removeStart("domain.com", null), "domain.com");
  }

  @Test
  public void testRemoveEnd() {
    // StringUtils.removeEnd("", *)        = ""
    assertNull(StringUtils.removeEnd(null, null));
    assertNull(StringUtils.removeEnd(null, ""));
    assertNull(StringUtils.removeEnd(null, "a"));

    // StringUtils.removeEnd(*, null)      = *
    assertEquals(StringUtils.removeEnd("", null), "");
    assertEquals(StringUtils.removeEnd("", ""), "");
    assertEquals(StringUtils.removeEnd("", "a"), "");

    // All others:
    assertEquals(StringUtils.removeEnd("www.domain.com.", ".com"), "www.domain.com.");
    assertEquals(StringUtils.removeEnd("www.domain.com", ".com"), "www.domain");
    assertEquals(StringUtils.removeEnd("www.domain", ".com"), "www.domain");
    assertEquals(StringUtils.removeEnd("domain.com", ""), "domain.com");
    assertEquals(StringUtils.removeEnd("domain.com", null), "domain.com");
  }

  @Test
  public void testRemove_String() {
    // StringUtils.remove(null, *)        = null
    assertNull(StringUtils.remove(null, null));
    assertNull(StringUtils.remove(null, ""));
    assertNull(StringUtils.remove(null, "a"));

    // StringUtils.remove("", *)          = ""
    assertEquals("", StringUtils.remove("", null));
    assertEquals("", StringUtils.remove("", ""));
    assertEquals("", StringUtils.remove("", "a"));

    // StringUtils.remove(*, null)        = *
    assertNull(StringUtils.remove(null, null));
    assertEquals("", StringUtils.remove("", null));
    assertEquals("a", StringUtils.remove("a", null));

    // StringUtils.remove(*, "")          = *
    assertNull(StringUtils.remove(null, ""));
    assertEquals("", StringUtils.remove("", ""));
    assertEquals("a", StringUtils.remove("a", ""));

    // StringUtils.remove("queued", "ue") = "qd"
    assertEquals("qd", StringUtils.remove("queued", "ue"));

    // StringUtils.remove("queued", "zz") = "queued"
    assertEquals("queued", StringUtils.remove("queued", "zz"));
  }

  @Test
  public void testRemoveIgnoreCase_String() {
    // StringUtils.removeIgnoreCase(null, *) = null
    assertEquals(null, StringUtils.removeIgnoreCase(null, null));
    assertEquals(null, StringUtils.removeIgnoreCase(null, ""));
    assertEquals(null, StringUtils.removeIgnoreCase(null, "a"));

    // StringUtils.removeIgnoreCase("", *) = ""
    assertEquals("", StringUtils.removeIgnoreCase("", null));
    assertEquals("", StringUtils.removeIgnoreCase("", ""));
    assertEquals("", StringUtils.removeIgnoreCase("", "a"));

    // StringUtils.removeIgnoreCase(*, null) = *
    assertEquals(null, StringUtils.removeIgnoreCase(null, null));
    assertEquals("", StringUtils.removeIgnoreCase("", null));
    assertEquals("a", StringUtils.removeIgnoreCase("a", null));

    // StringUtils.removeIgnoreCase(*, "") = *
    assertEquals(null, StringUtils.removeIgnoreCase(null, ""));
    assertEquals("", StringUtils.removeIgnoreCase("", ""));
    assertEquals("a", StringUtils.removeIgnoreCase("a", ""));

    // StringUtils.removeIgnoreCase("queued", "ue") = "qd"
    assertEquals("qd", StringUtils.removeIgnoreCase("queued", "ue"));

    // StringUtils.removeIgnoreCase("queued", "zz") = "queued"
    assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zz"));

    // IgnoreCase
    // StringUtils.removeIgnoreCase("quEUed", "UE") = "qd"
    assertEquals("qd", StringUtils.removeIgnoreCase("quEUed", "UE"));

    // StringUtils.removeIgnoreCase("queued", "zZ") = "queued"
    assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zZ"));
  }

  @Test
  public void testRemove_char() {
    // StringUtils.remove(null, *)       = null
    assertNull(StringUtils.remove(null, 'a'));
    assertNull(StringUtils.remove(null, 'a'));
    assertNull(StringUtils.remove(null, 'a'));

    // StringUtils.remove("", *)          = ""
    assertEquals("", StringUtils.remove("", 'a'));
    assertEquals("", StringUtils.remove("", 'a'));
    assertEquals("", StringUtils.remove("", 'a'));

    // StringUtils.remove("queued", 'u') = "qeed"
    assertEquals("qeed", StringUtils.remove("queued", 'u'));

    // StringUtils.remove("queued", 'z') = "queued"
    assertEquals("queued", StringUtils.remove("queued", 'z'));
  }

  @Test
  public void testRemoveAll() {
    assertNull(StringUtils.removeAll(null, ""));
    assertEquals("any", StringUtils.removeAll("any", null));

    assertEquals("any", StringUtils.removeAll("any", ""));
    assertEquals("", StringUtils.removeAll("any", ".*"));
    assertEquals("", StringUtils.removeAll("any", ".+"));
    assertEquals("", StringUtils.removeAll("any", ".?"));

    assertEquals("A\nB", StringUtils.removeAll("A<__>\n<__>B", "<.*>"));
    assertEquals("AB", StringUtils.removeAll("A<__>\n<__>B", "(?s)<.*>"));
    assertEquals("ABC123", StringUtils.removeAll("ABCabc123abc", "[a-z]"));

    try {
      StringUtils.removeAll("any", "{badRegexSyntax}");
      fail("StringUtils.removeAll expecting PatternSyntaxException");
    } catch (final PatternSyntaxException ex) {
      // empty
    }
  }

  @Test
  public void testRemoveFirst() {
    assertNull(StringUtils.removeFirst(null, ""));
    assertEquals("any", StringUtils.removeFirst("any", null));

    assertEquals("any", StringUtils.removeFirst("any", ""));
    assertEquals("", StringUtils.removeFirst("any", ".*"));
    assertEquals("", StringUtils.removeFirst("any", ".+"));
    assertEquals("bc", StringUtils.removeFirst("abc", ".?"));

    assertEquals("A\n<__>B", StringUtils.removeFirst("A<__>\n<__>B", "<.*>"));
    assertEquals("AB", StringUtils.removeFirst("A<__>\n<__>B", "(?s)<.*>"));
    assertEquals("ABCbc123", StringUtils.removeFirst("ABCabc123", "[a-z]"));
    assertEquals("ABC123abc", StringUtils.removeFirst("ABCabc123abc", "[a-z]+"));

    try {
      StringUtils.removeFirst("any", "{badRegexSyntax}");
      fail("StringUtils.removeFirst expecting PatternSyntaxException");
    } catch (final PatternSyntaxException ex) {
      // empty
    }
  }

  @Test
  public void testLANG666() {
    assertEquals("12", StringUtils.stripEnd("120.00", ".0"));
    assertEquals("121", StringUtils.stripEnd("121.00", ".0"));
  }
}
