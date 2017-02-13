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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Most methods are just copied from Apache Commons Lang's StringUtils.java.
 */
// https://github.com/apache/commons-lang/blob/LANG_3_5/src/main/java/org/apache/commons/lang3/StringUtils.java
public final class StringUtils {
  private StringUtils() {}

  /**
   * The empty String {@code ""}.
   */
  public static final String EMPTY = "";

  /**
   * Represents a failed index search.
   */
  public static final int INDEX_NOT_FOUND = -1;

  // Empty checks
  //-----------------------------------------------------------------------
  /**
   * <p>Checks if a CharSequence is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>NOTE: This method changed in Lang version 2.0.
   * It no longer trims the CharSequence.
   * That functionality is available in isBlank().</p>
   *
   * @param cs  the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   */
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  // Trim
  //-----------------------------------------------------------------------
  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String, handling {@code null} by returning
   * {@code null}.</p>
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #strip(String)}.</p>
   *
   * <p>To trim your choice of characters, use the
   * {@link #strip(String, String)} methods.</p>
   *
   * <pre>
   * StringUtils.trim(null)          = null
   * StringUtils.trim("")            = ""
   * StringUtils.trim("     ")       = ""
   * StringUtils.trim("abc")         = "abc"
   * StringUtils.trim("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed string, {@code null} if null String input
   */
  public static String trim(final String str) {
    return str == null ? null : str.trim();
  }

  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String returning {@code null} if the String is
   * empty ("") after the trim or if it is {@code null}.
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #stripToNull(String)}.</p>
   *
   * <pre>
   * StringUtils.trimToNull(null)          = null
   * StringUtils.trimToNull("")            = null
   * StringUtils.trimToNull("     ")       = null
   * StringUtils.trimToNull("abc")         = "abc"
   * StringUtils.trimToNull("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed String,
   *  {@code null} if only chars &lt;= 32, empty or null String input
   */
  public static String trimToNull(final String str) {
    final String ts = trim(str);
    return isEmpty(ts) ? null : ts;
  }

  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String returning an empty String ("") if the String
   * is empty ("") after the trim or if it is {@code null}.
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #stripToEmpty(String)}.</p>
   *
   * <pre>
   * StringUtils.trimToEmpty(null)          = ""
   * StringUtils.trimToEmpty("")            = ""
   * StringUtils.trimToEmpty("     ")       = ""
   * StringUtils.trimToEmpty("abc")         = "abc"
   * StringUtils.trimToEmpty("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed String, or an empty String if {@code null} input
   */
  public static String trimToEmpty(final String str) {
    return str == null ? EMPTY : str.trim();
  }

  // Truncate
  //-----------------------------------------------------------------------
  /**
   * <p>Truncates a String. This will turn
   * "Now is the time for all good men" into "Now is the time for".</p>
   *
   * <p>Specifically:</p>
   * <ul>
   *   <li>If {@code str} is less than {@code maxWidth} characters
   *       long, return it.</li>
   *   <li>Else truncate it to {@code substring(str, 0, maxWidth)}.</li>
   *   <li>If {@code maxWidth} is less than {@code 0}, throw an
   *       {@code IllegalArgumentException}.</li>
   *   <li>In no case will it return a String of length greater than
   *       {@code maxWidth}.</li>
   * </ul>
   *
   * <pre>
   * StringUtils.truncate(null, 0)       = null
   * StringUtils.truncate(null, 2)       = null
   * StringUtils.truncate("", 4)         = ""
   * StringUtils.truncate("abcdefg", 4)  = "abcd"
   * StringUtils.truncate("abcdefg", 6)  = "abcdef"
   * StringUtils.truncate("abcdefg", 7)  = "abcdefg"
   * StringUtils.truncate("abcdefg", 8)  = "abcdefg"
   * StringUtils.truncate("abcdefg", -1) = throws an IllegalArgumentException
   * </pre>
   *
   * @param str  the String to truncate, may be null
   * @param maxWidth  maximum length of result String, must be positive
   * @return truncated String, {@code null} if null String input
   */
  public static String truncate(final String str, int maxWidth) {
    return truncate(str, 0, maxWidth);
  }

  /**
   * <p>Truncates a String. This will turn
   * "Now is the time for all good men" into "is the time for all".</p>
   *
   * <p>Works like {@code truncate(String, int)}, but allows you to specify
   * a "left edge" offset.
   *
   * <p>Specifically:</p>
   * <ul>
   *   <li>If {@code str} is less than {@code maxWidth} characters
   *       long, return it.</li>
   *   <li>Else truncate it to {@code substring(str, offset, maxWidth)}.</li>
   *   <li>If {@code maxWidth} is less than {@code 0}, throw an
   *       {@code IllegalArgumentException}.</li>
   *   <li>If {@code offset} is less than {@code 0}, throw an
   *       {@code IllegalArgumentException}.</li>
   *   <li>In no case will it return a String of length greater than
   *       {@code maxWidth}.</li>
   * </ul>
   *
   * <pre>
   * StringUtils.truncate(null, 0, 0) = null
   * StringUtils.truncate(null, 2, 4) = null
   * StringUtils.truncate("", 0, 10) = ""
   * StringUtils.truncate("", 2, 10) = ""
   * StringUtils.truncate("abcdefghij", 0, 3) = "abc"
   * StringUtils.truncate("abcdefghij", 5, 6) = "fghij"
   * StringUtils.truncate("raspberry peach", 10, 15) = "peach"
   * StringUtils.truncate("abcdefghijklmno", 0, 10) = "abcdefghij"
   * StringUtils.truncate("abcdefghijklmno", -1, 10) = throws an IllegalArgumentException
   * StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, 10) = "abcdefghij"
   * StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, Integer.MAX_VALUE) = "abcdefghijklmno"
   * StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE) = "abcdefghijklmno"
   * StringUtils.truncate("abcdefghijklmno", 1, 10) = "bcdefghijk"
   * StringUtils.truncate("abcdefghijklmno", 2, 10) = "cdefghijkl"
   * StringUtils.truncate("abcdefghijklmno", 3, 10) = "defghijklm"
   * StringUtils.truncate("abcdefghijklmno", 4, 10) = "efghijklmn"
   * StringUtils.truncate("abcdefghijklmno", 5, 10) = "fghijklmno"
   * StringUtils.truncate("abcdefghijklmno", 5, 5) = "fghij"
   * StringUtils.truncate("abcdefghijklmno", 5, 3) = "fgh"
   * StringUtils.truncate("abcdefghijklmno", 10, 3) = "klm"
   * StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE) = "klmno"
   * StringUtils.truncate("abcdefghijklmno", 13, 1) = "n"
   * StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE) = "no"
   * StringUtils.truncate("abcdefghijklmno", 14, 1) = "o"
   * StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE) = "o"
   * StringUtils.truncate("abcdefghijklmno", 15, 1) = ""
   * StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE) = ""
   * StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE) = ""
   * StringUtils.truncate("abcdefghij", 3, -1) = throws an IllegalArgumentException
   * StringUtils.truncate("abcdefghij", -2, 4) = throws an IllegalArgumentException
   * </pre>
   *
   * @param str  the String to check, may be null
   * @param offset  left edge of source String
   * @param maxWidth  maximum length of result String, must be positive
   * @return truncated String, {@code null} if null String input
   */
  public static String truncate(final String str, int offset, int maxWidth) {
    if (offset < 0) {
      throw new IllegalArgumentException("offset cannot be negative");
    }
    if (maxWidth < 0) {
      throw new IllegalArgumentException("maxWith cannot be negative");
    }
    if (str == null) {
      return null;
    }
    if (offset > str.length()) {
      return EMPTY;
    }
    if (str.length() > maxWidth) {
      int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
      return str.substring(offset, ix);
    }
    return str.substring(offset);
  }

  // Stripping
  //-----------------------------------------------------------------------
  /**
   * <p>Strips whitespace from the start and end of a String.</p>
   *
   * <p>This is similar to {@link #trim(String)} but removes whitespace.
   * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <p>A {@code null} input String returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.strip(null)     = null
   * StringUtils.strip("")       = ""
   * StringUtils.strip("   ")    = ""
   * StringUtils.strip("abc")    = "abc"
   * StringUtils.strip("  abc")  = "abc"
   * StringUtils.strip("abc  ")  = "abc"
   * StringUtils.strip(" abc ")  = "abc"
   * StringUtils.strip(" ab c ") = "ab c"
   * </pre>
   *
   * @param str  the String to remove whitespace from, may be null
   * @return the stripped String, {@code null} if null String input
   */
  public static String strip(final String str) {
    return strip(str, null);
  }

  /**
   * <p>Strips whitespace from the start and end of a String  returning
   * {@code null} if the String is empty ("") after the strip.</p>
   *
   * <p>This is similar to {@link #trimToNull(String)} but removes whitespace.
   * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.stripToNull(null)     = null
   * StringUtils.stripToNull("")       = null
   * StringUtils.stripToNull("   ")    = null
   * StringUtils.stripToNull("abc")    = "abc"
   * StringUtils.stripToNull("  abc")  = "abc"
   * StringUtils.stripToNull("abc  ")  = "abc"
   * StringUtils.stripToNull(" abc ")  = "abc"
   * StringUtils.stripToNull(" ab c ") = "ab c"
   * </pre>
   *
   * @param str  the String to be stripped, may be null
   * @return the stripped String,
   *  {@code null} if whitespace, empty or null String input
   */
  public static String stripToNull(String str) {
    if (str == null) {
      return null;
    }
    str = strip(str, null);
    return str.isEmpty() ? null : str;
  }

  /**
   * <p>Strips whitespace from the start and end of a String  returning
   * an empty String if {@code null} input.</p>
   *
   * <p>This is similar to {@link #trimToEmpty(String)} but removes whitespace.
   * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.stripToEmpty(null)     = ""
   * StringUtils.stripToEmpty("")       = ""
   * StringUtils.stripToEmpty("   ")    = ""
   * StringUtils.stripToEmpty("abc")    = "abc"
   * StringUtils.stripToEmpty("  abc")  = "abc"
   * StringUtils.stripToEmpty("abc  ")  = "abc"
   * StringUtils.stripToEmpty(" abc ")  = "abc"
   * StringUtils.stripToEmpty(" ab c ") = "ab c"
   * </pre>
   *
   * @param str  the String to be stripped, may be null
   * @return the trimmed String, or an empty String if {@code null} input
   */
  public static String stripToEmpty(final String str) {
    return str == null ? EMPTY : strip(str, null);
  }

  /**
   * <p>Strips any of a set of characters from the start and end of a String.
   * This is similar to {@link String#trim()} but allows the characters
   * to be stripped to be controlled.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * An empty string ("") input returns the empty string.</p>
   *
   * <p>If the stripChars String is {@code null}, whitespace is
   * stripped as defined by {@link Character#isWhitespace(char)}.
   * Alternatively use {@link #strip(String)}.</p>
   *
   * <pre>
   * StringUtils.strip(null, *)          = null
   * StringUtils.strip("", *)            = ""
   * StringUtils.strip("abc", null)      = "abc"
   * StringUtils.strip("  abc", null)    = "abc"
   * StringUtils.strip("abc  ", null)    = "abc"
   * StringUtils.strip(" abc ", null)    = "abc"
   * StringUtils.strip("  abcyx", "xyz") = "  abc"
   * </pre>
   *
   * @param str  the String to remove characters from, may be null
   * @param stripChars  the characters to remove, null treated as whitespace
   * @return the stripped String, {@code null} if null String input
   */
  public static String strip(String str, final String stripChars) {
    if (isEmpty(str)) {
      return str;
    }
    str = stripStart(str, stripChars);
    return stripEnd(str, stripChars);
  }

  /**
   * <p>Strips any of a set of characters from the start of a String.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * An empty string ("") input returns the empty string.</p>
   *
   * <p>If the stripChars String is {@code null}, whitespace is
   * stripped as defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.stripStart(null, *)          = null
   * StringUtils.stripStart("", *)            = ""
   * StringUtils.stripStart("abc", "")        = "abc"
   * StringUtils.stripStart("abc", null)      = "abc"
   * StringUtils.stripStart("  abc", null)    = "abc"
   * StringUtils.stripStart("abc  ", null)    = "abc  "
   * StringUtils.stripStart(" abc ", null)    = "abc "
   * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
   * </pre>
   *
   * @param str  the String to remove characters from, may be null
   * @param stripChars  the characters to remove, null treated as whitespace
   * @return the stripped String, {@code null} if null String input
   */
  public static String stripStart(final String str, final String stripChars) {
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
      return str;
    }
    int start = 0;
    if (stripChars == null) {
      while (start != strLen && Character.isWhitespace(str.charAt(start))) {
        start++;
      }
    } else if (stripChars.isEmpty()) {
      return str;
    } else {
      while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
        start++;
      }
    }
    return str.substring(start);
  }

  /**
   * <p>Strips any of a set of characters from the end of a String.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * An empty string ("") input returns the empty string.</p>
   *
   * <p>If the stripChars String is {@code null}, whitespace is
   * stripped as defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.stripEnd(null, *)          = null
   * StringUtils.stripEnd("", *)            = ""
   * StringUtils.stripEnd("abc", "")        = "abc"
   * StringUtils.stripEnd("abc", null)      = "abc"
   * StringUtils.stripEnd("  abc", null)    = "  abc"
   * StringUtils.stripEnd("abc  ", null)    = "abc"
   * StringUtils.stripEnd(" abc ", null)    = " abc"
   * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
   * StringUtils.stripEnd("120.00", ".0")   = "12"
   * </pre>
   *
   * @param str  the String to remove characters from, may be null
   * @param stripChars  the set of characters to remove, null treated as whitespace
   * @return the stripped String, {@code null} if null String input
   */
  public static String stripEnd(final String str, final String stripChars) {
    int end;
    if (str == null || (end = str.length()) == 0) {
      return str;
    }

    if (stripChars == null) {
      while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
        end--;
      }
    } else if (stripChars.isEmpty()) {
      return str;
    } else {
      while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
        end--;
      }
    }
    return str.substring(0, end);
  }

  // Substring
  //-----------------------------------------------------------------------
  /**
   * <p>Gets a substring from the specified String avoiding exceptions.</p>
   *
   * <p>A negative start position can be used to start {@code n}
   * characters from the end of the String.</p>
   *
   * <p>A {@code null} String will return {@code null}.
   * An empty ("") String will return "".</p>
   *
   * <pre>
   * StringUtils.substring(null, *)   = null
   * StringUtils.substring("", *)     = ""
   * StringUtils.substring("abc", 0)  = "abc"
   * StringUtils.substring("abc", 2)  = "c"
   * StringUtils.substring("abc", 4)  = ""
   * StringUtils.substring("abc", -2) = "bc"
   * StringUtils.substring("abc", -4) = "abc"
   * </pre>
   *
   * @param str  the String to get the substring from, may be null
   * @param start  the position to start from, negative means
   *  count back from the end of the String by this many characters
   * @return substring from start position, {@code null} if null String input
   */
  public static String substring(final String str, int start) {
    if (str == null) {
      return null;
    }

    // handle negatives, which means last n characters
    if (start < 0) {
      start = str.length() + start; // remember start is negative
    }

    if (start < 0) {
      start = 0;
    }
    if (start > str.length()) {
      return EMPTY;
    }

    return str.substring(start);
  }

  /**
   * <p>Gets a substring from the specified String avoiding exceptions.</p>
   *
   * <p>A negative start position can be used to start/end {@code n}
   * characters from the end of the String.</p>
   *
   * <p>The returned substring starts with the character in the {@code start}
   * position and ends before the {@code end} position. All position counting is
   * zero-based -- i.e., to start at the beginning of the string use
   * {@code start = 0}. Negative start and end positions can be used to
   * specify offsets relative to the end of the String.</p>
   *
   * <p>If {@code start} is not strictly to the left of {@code end}, ""
   * is returned.</p>
   *
   * <pre>
   * StringUtils.substring(null, *, *)    = null
   * StringUtils.substring("", * ,  *)    = "";
   * StringUtils.substring("abc", 0, 2)   = "ab"
   * StringUtils.substring("abc", 2, 0)   = ""
   * StringUtils.substring("abc", 2, 4)   = "c"
   * StringUtils.substring("abc", 4, 6)   = ""
   * StringUtils.substring("abc", 2, 2)   = ""
   * StringUtils.substring("abc", -2, -1) = "b"
   * StringUtils.substring("abc", -4, 2)  = "ab"
   * </pre>
   *
   * @param str  the String to get the substring from, may be null
   * @param start  the position to start from, negative means
   *  count back from the end of the String by this many characters
   * @param end  the position to end at (exclusive), negative means
   *  count back from the end of the String by this many characters
   * @return substring from start position to end position,
   *  {@code null} if null String input
   */
  public static String substring(final String str, int start, int end) {
    if (str == null) {
      return null;
    }

    // handle negatives
    if (end < 0) {
      end = str.length() + end; // remember end is negative
    }
    if (start < 0) {
      start = str.length() + start; // remember start is negative
    }

    // check length next
    if (end > str.length()) {
      end = str.length();
    }

    // if start is greater than end, return ""
    if (start > end) {
      return EMPTY;
    }

    if (start < 0) {
      start = 0;
    }
    if (end < 0) {
      end = 0;
    }

    return str.substring(start, end);
  }

  // Splitting
  //-----------------------------------------------------------------------
  /**
   * <p>Splits the provided text into an array, using whitespace as the
   * separator.
   * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as one separator.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.split(null)       = null
   * StringUtils.split("")         = []
   * StringUtils.split("abc def")  = ["abc", "def"]
   * StringUtils.split("abc  def") = ["abc", "def"]
   * StringUtils.split(" abc ")    = ["abc"]
   * </pre>
   *
   * @param str  the String to parse, may be null
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] split(final String str) {
    return split(str, null, -1);
  }

  /**
   * <p>Splits the provided text into an array, separator specified.
   * This is an alternative to using StringTokenizer.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as one separator.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.split(null, *)         = null
   * StringUtils.split("", *)           = []
   * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
   * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
   * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
   * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
   * </pre>
   *
   * @param str  the String to parse, may be null
   * @param separatorChar  the character used as the delimiter
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] split(final String str, final char separatorChar) {
    return splitWorker(str, separatorChar, false);
  }

  /**
   * <p>Splits the provided text into an array, separators specified.
   * This is an alternative to using StringTokenizer.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as one separator.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} separatorChars splits on whitespace.</p>
   *
   * <pre>
   * StringUtils.split(null, *)         = null
   * StringUtils.split("", *)           = []
   * StringUtils.split("abc def", null) = ["abc", "def"]
   * StringUtils.split("abc def", " ")  = ["abc", "def"]
   * StringUtils.split("abc  def", " ") = ["abc", "def"]
   * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
   * </pre>
   *
   * @param str  the String to parse, may be null
   * @param separatorChars  the characters used as the delimiters,
   *  {@code null} splits on whitespace
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] split(final String str, final String separatorChars) {
    return splitWorker(str, separatorChars, -1, false);
  }

  /**
   * <p>Splits the provided text into an array with a maximum length,
   * separators specified.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as one separator.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} separatorChars splits on whitespace.</p>
   *
   * <p>If more than {@code max} delimited substrings are found, the last
   * returned string includes all characters after the first {@code max - 1}
   * returned strings (including separator characters).</p>
   *
   * <pre>
   * StringUtils.split(null, *, *)            = null
   * StringUtils.split("", *, *)              = []
   * StringUtils.split("ab cd ef", null, 0)   = ["ab", "cd", "ef"]
   * StringUtils.split("ab   cd ef", null, 0) = ["ab", "cd", "ef"]
   * StringUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
   * StringUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
   * </pre>
   *
   * @param str  the String to parse, may be null
   * @param separatorChars  the characters used as the delimiters,
   *  {@code null} splits on whitespace
   * @param max  the maximum number of elements to include in the
   *  array. A zero or negative value implies no limit
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] split(final String str, final String separatorChars, final int max) {
    return splitWorker(str, separatorChars, max, false);
  }

  /**
   * <p>Splits the provided text into an array, using whitespace as the
   * separator, preserving all tokens, including empty tokens created by
   * adjacent separators. This is an alternative to using StringTokenizer.
   * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as separators for empty tokens.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.splitPreserveAllTokens(null)       = null
   * StringUtils.splitPreserveAllTokens("")         = []
   * StringUtils.splitPreserveAllTokens("abc def")  = ["abc", "def"]
   * StringUtils.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
   * StringUtils.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
   * </pre>
   *
   * @param str  the String to parse, may be {@code null}
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] splitPreserveAllTokens(final String str) {
    return splitWorker(str, null, -1, true);
  }

  /**
   * <p>Splits the provided text into an array, separator specified,
   * preserving all tokens, including empty tokens created by adjacent
   * separators. This is an alternative to using StringTokenizer.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as separators for empty tokens.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.splitPreserveAllTokens(null, *)         = null
   * StringUtils.splitPreserveAllTokens("", *)           = []
   * StringUtils.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
   * StringUtils.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
   * StringUtils.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
   * StringUtils.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
   * StringUtils.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
   * StringUtils.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
   * StringUtils.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
   * StringUtils.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
   * StringUtils.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
   * StringUtils.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
   * </pre>
   *
   * @param str  the String to parse, may be {@code null}
   * @param separatorChar  the character used as the delimiter,
   *  {@code null} splits on whitespace
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] splitPreserveAllTokens(final String str, final char separatorChar) {
    return splitWorker(str, separatorChar, true);
  }

  /**
   * <p>Splits the provided text into an array, separators specified,
   * preserving all tokens, including empty tokens created by adjacent
   * separators. This is an alternative to using StringTokenizer.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as separators for empty tokens.
   * For more control over the split use the StrTokenizer class.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} separatorChars splits on whitespace.</p>
   *
   * <pre>
   * StringUtils.splitPreserveAllTokens(null, *)           = null
   * StringUtils.splitPreserveAllTokens("", *)             = []
   * StringUtils.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
   * StringUtils.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
   * StringUtils.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
   * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
   * StringUtils.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
   * StringUtils.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
   * StringUtils.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
   * StringUtils.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
   * StringUtils.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
   * StringUtils.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
   * </pre>
   *
   * @param str  the String to parse, may be {@code null}
   * @param separatorChars  the characters used as the delimiters,
   *  {@code null} splits on whitespace
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] splitPreserveAllTokens(final String str, final String separatorChars) {
    return splitWorker(str, separatorChars, -1, true);
  }

  /**
   * <p>Splits the provided text into an array with a maximum length,
   * separators specified, preserving all tokens, including empty tokens
   * created by adjacent separators.</p>
   *
   * <p>The separator is not included in the returned String array.
   * Adjacent separators are treated as separators for empty tokens.
   * Adjacent separators are treated as one separator.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} separatorChars splits on whitespace.</p>
   *
   * <p>If more than {@code max} delimited substrings are found, the last
   * returned string includes all characters after the first {@code max - 1}
   * returned strings (including separator characters).</p>
   *
   * <pre>
   * StringUtils.splitPreserveAllTokens(null, *, *)            = null
   * StringUtils.splitPreserveAllTokens("", *, *)              = []
   * StringUtils.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "cd", "ef"]
   * StringUtils.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "cd", "ef"]
   * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
   * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
   * StringUtils.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
   * StringUtils.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
   * StringUtils.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
   * </pre>
   *
   * @param str  the String to parse, may be {@code null}
   * @param separatorChars  the characters used as the delimiters,
   *  {@code null} splits on whitespace
   * @param max  the maximum number of elements to include in the
   *  array. A zero or negative value implies no limit
   * @return an array of parsed Strings, {@code null} if null String input
   */
  public static String[] splitPreserveAllTokens(final String str, final String separatorChars, final int max) {
    return splitWorker(str, separatorChars, max, true);
  }

  /**
   * Performs the logic for the {@code split} and
   * {@code splitPreserveAllTokens} methods that do not return a
   * maximum array length.
   *
   * @param str  the String to parse, may be {@code null}
   * @param separatorChar the separate character
   * @param preserveAllTokens if {@code true}, adjacent separators are
   * treated as empty token separators; if {@code false}, adjacent
   * separators are treated as one separator.
   * @return an array of parsed Strings, {@code null} if null String input
   */
  private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
    // Performance tuned for 2.0 (JDK1.4)

    if (str == null) {
      return null;
    }
    final int len = str.length();
    if (len == 0) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    final List<String> list = new ArrayList<String>();
    int i = 0, start = 0;
    boolean match = false;
    boolean lastMatch = false;
    while (i < len) {
      if (str.charAt(i) == separatorChar) {
        if (match || preserveAllTokens) {
          list.add(str.substring(start, i));
          match = false;
          lastMatch = true;
        }
        start = ++i;
        continue;
      }
      lastMatch = false;
      match = true;
      i++;
    }
    if (match || preserveAllTokens && lastMatch) {
      list.add(str.substring(start, i));
    }
    return list.toArray(new String[list.size()]);
  }

  /**
   * Performs the logic for the {@code split} and
   * {@code splitPreserveAllTokens} methods that return a maximum array
   * length.
   *
   * @param str  the String to parse, may be {@code null}
   * @param separatorChars the separate character
   * @param max  the maximum number of elements to include in the
   *  array. A zero or negative value implies no limit.
   * @param preserveAllTokens if {@code true}, adjacent separators are
   * treated as empty token separators; if {@code false}, adjacent
   * separators are treated as one separator.
   * @return an array of parsed Strings, {@code null} if null String input
   */
  private static String[] splitWorker(final String str, final String separatorChars, final int max, final boolean preserveAllTokens) {
    // Performance tuned for 2.0 (JDK1.4)
    // Direct code is quicker than StringTokenizer.
    // Also, StringTokenizer uses isSpace() not isWhitespace()

    if (str == null) {
      return null;
    }
    final int len = str.length();
    if (len == 0) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    final List<String> list = new ArrayList<String>();
    int sizePlus1 = 1;
    int i = 0, start = 0;
    boolean match = false;
    boolean lastMatch = false;
    if (separatorChars == null) {
      // Null separator means use whitespace
      while (i < len) {
        if (Character.isWhitespace(str.charAt(i))) {
          if (match || preserveAllTokens) {
            lastMatch = true;
            if (sizePlus1++ == max) {
              i = len;
              lastMatch = false;
            }
            list.add(str.substring(start, i));
            match = false;
          }
          start = ++i;
          continue;
        }
        lastMatch = false;
        match = true;
        i++;
      }
    } else if (separatorChars.length() == 1) {
      // Optimise 1 character case
      final char sep = separatorChars.charAt(0);
      while (i < len) {
        if (str.charAt(i) == sep) {
          if (match || preserveAllTokens) {
            lastMatch = true;
            if (sizePlus1++ == max) {
              i = len;
              lastMatch = false;
            }
            list.add(str.substring(start, i));
            match = false;
          }
          start = ++i;
          continue;
        }
        lastMatch = false;
        match = true;
        i++;
      }
    } else {
      // standard case
      while (i < len) {
        if (separatorChars.indexOf(str.charAt(i)) >= 0) {
          if (match || preserveAllTokens) {
            lastMatch = true;
            if (sizePlus1++ == max) {
              i = len;
              lastMatch = false;
            }
            list.add(str.substring(start, i));
            match = false;
          }
          start = ++i;
          continue;
        }
        lastMatch = false;
        match = true;
        i++;
      }
    }
    if (match || preserveAllTokens && lastMatch) {
      list.add(str.substring(start, i));
    }
    return list.toArray(new String[list.size()]);
  }

  // Remove
  //-----------------------------------------------------------------------
  /**
   * <p>Removes a substring only if it is at the beginning of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.
   * A {@code null} search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeStart(null, *)      = null
   * StringUtils.removeStart("", *)        = ""
   * StringUtils.removeStart(*, null)      = *
   * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
   * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
   * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeStart("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  {@code null} if null String input
   */
  public static String removeStart(final String str, final String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    if (str.startsWith(remove)){
      return str.substring(remove.length());
    }
    return str;
  }

  /**
   * <p>Removes a substring only if it is at the end of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.
   * A {@code null} search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeEnd(null, *)      = null
   * StringUtils.removeEnd("", *)        = ""
   * StringUtils.removeEnd(*, null)      = *
   * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
   * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
   * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeEnd("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  {@code null} if null String input
   */
  public static String removeEnd(final String str, final String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    if (str.endsWith(remove)) {
      return str.substring(0, str.length() - remove.length());
    }
    return str;
  }

  /**
   * <p>Removes all occurrences of a substring from within the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.
   * A {@code null} remove string will return the source string.
   * An empty ("") remove string will return the source string.</p>
   *
   * <pre>
   * StringUtils.remove(null, *)        = null
   * StringUtils.remove("", *)          = ""
   * StringUtils.remove(*, null)        = *
   * StringUtils.remove(*, "")          = *
   * StringUtils.remove("queued", "ue") = "qd"
   * StringUtils.remove("queued", "zz") = "queued"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  {@code null} if null String input
   */
  public static String remove(final String str, final String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    return replace(str, remove, EMPTY, -1);
  }

  /**
   * <p>
   * Case insensitive removal of all occurrences of a substring from within
   * the source string.
   * </p>
   *
   * <p>
   * A {@code null} source string will return {@code null}. An empty ("")
   * source string will return the empty string. A {@code null} remove string
   * will return the source string. An empty ("") remove string will return
   * the source string.
   * </p>
   *
   * <pre>
   * StringUtils.removeIgnoreCase(null, *)        = null
   * StringUtils.removeIgnoreCase("", *)          = ""
   * StringUtils.removeIgnoreCase(*, null)        = *
   * StringUtils.removeIgnoreCase(*, "")          = *
   * StringUtils.removeIgnoreCase("queued", "ue") = "qd"
   * StringUtils.removeIgnoreCase("queued", "zz") = "queued"
   * StringUtils.removeIgnoreCase("quEUed", "UE") = "qd"
   * StringUtils.removeIgnoreCase("queued", "zZ") = "queued"
   * </pre>
   *
   * @param str
   *            the source String to search, may be null
   * @param remove
   *            the String to search for (case insensitive) and remove, may be
   *            null
   * @return the substring with the string removed if found, {@code null} if
   *         null String input
   */
  public static String removeIgnoreCase(String str, String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    return replaceIgnoreCase(str, remove, EMPTY, -1);
  }

  /**
   * <p>Removes all occurrences of a character from within the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.</p>
   *
   * <pre>
   * StringUtils.remove(null, *)       = null
   * StringUtils.remove("", *)         = ""
   * StringUtils.remove("queued", 'u') = "qeed"
   * StringUtils.remove("queued", 'z') = "queued"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the char to search for and remove, may be null
   * @return the substring with the char removed if found,
   *  {@code null} if null String input
   */
  public static String remove(final String str, final char remove) {
    if (isEmpty(str) || str.indexOf(remove) == INDEX_NOT_FOUND) {
      return str;
    }
    final char[] chars = str.toCharArray();
    int pos = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] != remove) {
        chars[pos++] = chars[i];
      }
    }
    return new String(chars, 0, pos);
  }

  /**
   * <p>Removes each substring of the text String that matches the given regular expression.</p>
   *
   * This method is a {@code null} safe equivalent to:
   * <ul>
   *  <li>{@code text.replaceAll(regex, StringUtils.EMPTY)}</li>
   *  <li>{@code Pattern.compile(regex).matcher(text).replaceAll(StringUtils.EMPTY)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <p>Unlike in the {@link #removePattern(String, String)} method, the {@link Pattern#DOTALL} option
   * is NOT automatically added.
   * To use the DOTALL option prepend <code>"(?s)"</code> to the regex.
   * DOTALL is also know as single-line mode in Perl.</p>
   *
   * <pre>
   * StringUtils.removeAll(null, *)      = null
   * StringUtils.removeAll("any", null)  = "any"
   * StringUtils.removeAll("any", "")    = "any"
   * StringUtils.removeAll("any", ".*")  = ""
   * StringUtils.removeAll("any", ".+")  = ""
   * StringUtils.removeAll("abc", ".?")  = ""
   * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\nB"
   * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
   * StringUtils.removeAll("ABCabc123abc", "[a-z]")     = "ABC123"
   * </pre>
   *
   * @param text  text to remove from, may be null
   * @param regex  the regular expression to which this string is to be matched
   * @return  the text with any removes processed,
   *              {@code null} if null String input
   *
   * @throws  java.util.regex.PatternSyntaxException
   *              if the regular expression's syntax is invalid
   *
   * @see #replaceAll(String, String, String)
   * @see #removePattern(String, String)
   * @see String#replaceAll(String, String)
   * @see java.util.regex.Pattern
   * @see java.util.regex.Pattern#DOTALL
   */
  public static String removeAll(final String text, final String regex) {
    return replaceAll(text, regex, StringUtils.EMPTY);
  }

  /**
   * <p>Removes the first substring of the text string that matches the given regular expression.</p>
   *
   * This method is a {@code null} safe equivalent to:
   * <ul>
   *  <li>{@code text.replaceFirst(regex, StringUtils.EMPTY)}</li>
   *  <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(StringUtils.EMPTY)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
   * To use the DOTALL option prepend <code>"(?s)"</code> to the regex.
   * DOTALL is also know as single-line mode in Perl.</p>
   *
   * <pre>
   * StringUtils.removeFirst(null, *)      = null
   * StringUtils.removeFirst("any", null)  = "any"
   * StringUtils.removeFirst("any", "")    = "any"
   * StringUtils.removeFirst("any", ".*")  = ""
   * StringUtils.removeFirst("any", ".+")  = ""
   * StringUtils.removeFirst("abc", ".?")  = "bc"
   * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\n&lt;__&gt;B"
   * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
   * StringUtils.removeFirst("ABCabc123", "[a-z]")          = "ABCbc123"
   * StringUtils.removeFirst("ABCabc123abc", "[a-z]+")      = "ABC123abc"
   * </pre>
   *
   * @param text  text to remove from, may be null
   * @param regex  the regular expression to which this string is to be matched
   * @return  the text with the first replacement processed,
   *              {@code null} if null String input
   *
   * @throws  java.util.regex.PatternSyntaxException
   *              if the regular expression's syntax is invalid
   *
   * @see #replaceFirst(String, String, String)
   * @see String#replaceFirst(String, String)
   * @see java.util.regex.Pattern
   * @see java.util.regex.Pattern#DOTALL
   */
  public static String removeFirst(final String text, final String regex) {
    return replaceFirst(text, regex, StringUtils.EMPTY);
  }

  // Replacing
  //-----------------------------------------------------------------------
  /**
   * <p>Replaces a String with another String inside a larger String, once.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replaceOnce(null, *, *)        = null
   * StringUtils.replaceOnce("", *, *)          = ""
   * StringUtils.replaceOnce("any", null, *)    = "any"
   * StringUtils.replaceOnce("any", *, null)    = "any"
   * StringUtils.replaceOnce("any", "", *)      = "any"
   * StringUtils.replaceOnce("aba", "a", null)  = "aba"
   * StringUtils.replaceOnce("aba", "a", "")    = "ba"
   * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
   * </pre>
   *
   * @see #replace(String text, String searchString, String replacement, int max)
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for, may be null
   * @param replacement  the String to replace with, may be null
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replaceOnce(final String text, final String searchString, final String replacement) {
    return replace(text, searchString, replacement, 1);
  }

  /**
   * <p>Case insensitively replaces a String with another String inside a larger String, once.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replaceOnceIgnoreCase(null, *, *)        = null
   * StringUtils.replaceOnceIgnoreCase("", *, *)          = ""
   * StringUtils.replaceOnceIgnoreCase("any", null, *)    = "any"
   * StringUtils.replaceOnceIgnoreCase("any", *, null)    = "any"
   * StringUtils.replaceOnceIgnoreCase("any", "", *)      = "any"
   * StringUtils.replaceOnceIgnoreCase("aba", "a", null)  = "aba"
   * StringUtils.replaceOnceIgnoreCase("aba", "a", "")    = "ba"
   * StringUtils.replaceOnceIgnoreCase("aba", "a", "z")   = "zba"
   * StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "") = "Foofoo"
   * </pre>
   *
   * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for (case insensitive), may be null
   * @param replacement  the String to replace with, may be null
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replaceOnceIgnoreCase(String text, String searchString, String replacement) {
    return replaceIgnoreCase(text, searchString, replacement, 1);
  }

  /**
   * <p>Replaces each substring of the source String that matches the given regular expression with the given
   * replacement using the {@link Pattern#DOTALL} option. DOTALL is also know as single-line mode in Perl.</p>
   *
   * This call is a {@code null} safe equivalent to:
   * <ul>
   * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, replacement)}</li>
   * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(replacement)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replacePattern(null, *, *)       = null
   * StringUtils.replacePattern("any", null, *)   = "any"
   * StringUtils.replacePattern("any", *, null)   = "any"
   * StringUtils.replacePattern("", "", "zzz")    = "zzz"
   * StringUtils.replacePattern("", ".*", "zzz")  = "zzz"
   * StringUtils.replacePattern("", ".+", "zzz")  = ""
   * StringUtils.replacePattern("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")       = "z"
   * StringUtils.replacePattern("ABCabc123", "[a-z]", "_")       = "ABC___123"
   * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
   * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
   * StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
   * </pre>
   *
   * @param source
   *            the source string
   * @param regex
   *            the regular expression to which this string is to be matched
   * @param replacement
   *            the string to be substituted for each match
   * @return The resulting {@code String}
   * @see #replaceAll(String, String, String)
   * @see String#replaceAll(String, String)
   * @see Pattern#DOTALL
   */
  public static String replacePattern(final String source, final String regex, final String replacement) {
    if (source == null || regex == null|| replacement == null ) {
      return source;
    }
    return Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(replacement);
  }

  /**
   * <p>Removes each substring of the source String that matches the given regular expression using the DOTALL option.
   * </p>
   *
   * This call is a {@code null} safe equivalent to:
   * <ul>
   * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, StringUtils.EMPTY)}</li>
   * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(StringUtils.EMPTY)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.removePattern(null, *)       = null
   * StringUtils.removePattern("any", null)   = "any"
   * StringUtils.removePattern("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")  = "AB"
   * StringUtils.removePattern("ABCabc123", "[a-z]")    = "ABC123"
   * </pre>
   *
   * @param source
   *            the source string
   * @param regex
   *            the regular expression to which this string is to be matched
   * @return The resulting {@code String}
   * @see #replacePattern(String, String, String)
   * @see String#replaceAll(String, String)
   * @see Pattern#DOTALL
   */
  public static String removePattern(final String source, final String regex) {
    return replacePattern(source, regex, StringUtils.EMPTY);
  }

  /**
   * <p>Replaces each substring of the text String that matches the given regular expression
   * with the given replacement.</p>
   *
   * This method is a {@code null} safe equivalent to:
   * <ul>
   *  <li>{@code text.replaceAll(regex, replacement)}</li>
   *  <li>{@code Pattern.compile(regex).matcher(text).replaceAll(replacement)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <p>Unlike in the {@link #replacePattern(String, String, String)} method, the {@link Pattern#DOTALL} option
   * is NOT automatically added.
   * To use the DOTALL option prepend <code>"(?s)"</code> to the regex.
   * DOTALL is also know as single-line mode in Perl.</p>
   *
   * <pre>
   * StringUtils.replaceAll(null, *, *)       = null
   * StringUtils.replaceAll("any", null, *)   = "any"
   * StringUtils.replaceAll("any", *, null)   = "any"
   * StringUtils.replaceAll("", "", "zzz")    = "zzz"
   * StringUtils.replaceAll("", ".*", "zzz")  = "zzz"
   * StringUtils.replaceAll("", ".+", "zzz")  = ""
   * StringUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
   * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
   * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
   * StringUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
   * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
   * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
   * StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param regex  the regular expression to which this string is to be matched
   * @param replacement  the string to be substituted for each match
   * @return  the text with any replacements processed,
   *              {@code null} if null String input
   *
   * @throws  java.util.regex.PatternSyntaxException
   *              if the regular expression's syntax is invalid
   *
   * @see #replacePattern(String, String, String)
   * @see String#replaceAll(String, String)
   * @see java.util.regex.Pattern
   * @see java.util.regex.Pattern#DOTALL
   */
  public static String replaceAll(final String text, final String regex, final String replacement) {
    if (text == null || regex == null|| replacement == null ) {
      return text;
    }
    return text.replaceAll(regex, replacement);
  }

  /**
   * <p>Replaces the first substring of the text string that matches the given regular expression
   * with the given replacement.</p>
   *
   * This method is a {@code null} safe equivalent to:
   * <ul>
   *  <li>{@code text.replaceFirst(regex, replacement)}</li>
   *  <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(replacement)}</li>
   * </ul>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
   * To use the DOTALL option prepend <code>"(?s)"</code> to the regex.
   * DOTALL is also know as single-line mode in Perl.</p>
   *
   * <pre>
   * StringUtils.replaceFirst(null, *, *)       = null
   * StringUtils.replaceFirst("any", null, *)   = "any"
   * StringUtils.replaceFirst("any", *, null)   = "any"
   * StringUtils.replaceFirst("", "", "zzz")    = "zzz"
   * StringUtils.replaceFirst("", ".*", "zzz")  = "zzz"
   * StringUtils.replaceFirst("", ".+", "zzz")  = ""
   * StringUtils.replaceFirst("abc", "", "ZZ")  = "ZZabc"
   * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\n&lt;__&gt;"
   * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
   * StringUtils.replaceFirst("ABCabc123", "[a-z]", "_")          = "ABC_bc123"
   * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_")  = "ABC_123abc"
   * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "")   = "ABC123abc"
   * StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum  dolor   sit"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param regex  the regular expression to which this string is to be matched
   * @param replacement  the string to be substituted for the first match
   * @return  the text with the first replacement processed,
   *              {@code null} if null String input
   *
   * @throws  java.util.regex.PatternSyntaxException
   *              if the regular expression's syntax is invalid
   *
   * @see String#replaceFirst(String, String)
   * @see java.util.regex.Pattern
   * @see java.util.regex.Pattern#DOTALL
   */
  public static String replaceFirst(final String text, final String regex, final String replacement) {
    if (text == null || regex == null|| replacement == null ) {
      return text;
    }
    return text.replaceFirst(regex, replacement);
  }

  /**
   * <p>Replaces all occurrences of a String within another String.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replace(null, *, *)        = null
   * StringUtils.replace("", *, *)          = ""
   * StringUtils.replace("any", null, *)    = "any"
   * StringUtils.replace("any", *, null)    = "any"
   * StringUtils.replace("any", "", *)      = "any"
   * StringUtils.replace("aba", "a", null)  = "aba"
   * StringUtils.replace("aba", "a", "")    = "b"
   * StringUtils.replace("aba", "a", "z")   = "zbz"
   * </pre>
   *
   * @see #replace(String text, String searchString, String replacement, int max)
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for, may be null
   * @param replacement  the String to replace it with, may be null
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replace(final String text, final String searchString, final String replacement) {
    return replace(text, searchString, replacement, -1);
  }

  /**
   * <p>Case insensitively replaces all occurrences of a String within another String.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replaceIgnoreCase(null, *, *)        = null
   * StringUtils.replaceIgnoreCase("", *, *)          = ""
   * StringUtils.replaceIgnoreCase("any", null, *)    = "any"
   * StringUtils.replaceIgnoreCase("any", *, null)    = "any"
   * StringUtils.replaceIgnoreCase("any", "", *)      = "any"
   * StringUtils.replaceIgnoreCase("aba", "a", null)  = "aba"
   * StringUtils.replaceIgnoreCase("abA", "A", "")    = "b"
   * StringUtils.replaceIgnoreCase("aba", "A", "z")   = "zbz"
   * </pre>
   *
   * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for (case insensitive), may be null
   * @param replacement  the String to replace it with, may be null
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replaceIgnoreCase(String text, String searchString, String replacement) {
    return replaceIgnoreCase(text, searchString, replacement, -1);
  }

  /**
   * <p>Replaces a String with another String inside a larger String,
   * for the first {@code max} values of the search String.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replace(null, *, *, *)         = null
   * StringUtils.replace("", *, *, *)           = ""
   * StringUtils.replace("any", null, *, *)     = "any"
   * StringUtils.replace("any", *, null, *)     = "any"
   * StringUtils.replace("any", "", *, *)       = "any"
   * StringUtils.replace("any", *, *, 0)        = "any"
   * StringUtils.replace("abaa", "a", null, -1) = "abaa"
   * StringUtils.replace("abaa", "a", "", -1)   = "b"
   * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
   * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
   * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
   * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for, may be null
   * @param replacement  the String to replace it with, may be null
   * @param max  maximum number of values to replace, or {@code -1} if no maximum
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replace(final String text, final String searchString, final String replacement, int max) {
    return replace(text, searchString, replacement, max, false);
  }

  /**
   * <p>Replaces a String with another String inside a larger String,
   * for the first {@code max} values of the search String,
   * case sensitively/insensisitively based on {@code ignoreCase} value.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replace(null, *, *, *, false)         = null
   * StringUtils.replace("", *, *, *, false)           = ""
   * StringUtils.replace("any", null, *, *, false)     = "any"
   * StringUtils.replace("any", *, null, *, false)     = "any"
   * StringUtils.replace("any", "", *, *, false)       = "any"
   * StringUtils.replace("any", *, *, 0, false)        = "any"
   * StringUtils.replace("abaa", "a", null, -1, false) = "abaa"
   * StringUtils.replace("abaa", "a", "", -1, false)   = "b"
   * StringUtils.replace("abaa", "a", "z", 0, false)   = "abaa"
   * StringUtils.replace("abaa", "A", "z", 1, false)   = "abaa"
   * StringUtils.replace("abaa", "A", "z", 1, true)   = "zbaa"
   * StringUtils.replace("abAa", "a", "z", 2, true)   = "zbza"
   * StringUtils.replace("abAa", "a", "z", -1, true)  = "zbzz"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for (case insensitive), may be null
   * @param replacement  the String to replace it with, may be null
   * @param max  maximum number of values to replace, or {@code -1} if no maximum
   * @param ignoreCase if true replace is case insensitive, otherwise case sensitive
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  private static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase) {
    if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
      return text;
    }
    String searchText = text;
    if (ignoreCase) {
      searchText = text.toLowerCase();
      searchString = searchString.toLowerCase();
    }
    int start = 0;
    int end = searchText.indexOf(searchString, start);
    if (end == INDEX_NOT_FOUND) {
      return text;
    }
    final int replLength = searchString.length();
    int increase = replacement.length() - replLength;
    increase = increase < 0 ? 0 : increase;
    increase *= max < 0 ? 16 : max > 64 ? 64 : max;
    final StringBuilder buf = new StringBuilder(text.length() + increase);
    while (end != INDEX_NOT_FOUND) {
      buf.append(text.substring(start, end)).append(replacement);
      start = end + replLength;
      if (--max == 0) {
        break;
      }
      end = searchText.indexOf(searchString, start);
    }
    buf.append(text.substring(start));
    return buf.toString();
  }

  /**
   * <p>Case insensitively replaces a String with another String inside a larger String,
   * for the first {@code max} values of the search String.</p>
   *
   * <p>A {@code null} reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replaceIgnoreCase(null, *, *, *)         = null
   * StringUtils.replaceIgnoreCase("", *, *, *)           = ""
   * StringUtils.replaceIgnoreCase("any", null, *, *)     = "any"
   * StringUtils.replaceIgnoreCase("any", *, null, *)     = "any"
   * StringUtils.replaceIgnoreCase("any", "", *, *)       = "any"
   * StringUtils.replaceIgnoreCase("any", *, *, 0)        = "any"
   * StringUtils.replaceIgnoreCase("abaa", "a", null, -1) = "abaa"
   * StringUtils.replaceIgnoreCase("abaa", "a", "", -1)   = "b"
   * StringUtils.replaceIgnoreCase("abaa", "a", "z", 0)   = "abaa"
   * StringUtils.replaceIgnoreCase("abaa", "A", "z", 1)   = "zbaa"
   * StringUtils.replaceIgnoreCase("abAa", "a", "z", 2)   = "zbza"
   * StringUtils.replaceIgnoreCase("abAa", "a", "z", -1)  = "zbzz"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param searchString  the String to search for (case insensitive), may be null
   * @param replacement  the String to replace it with, may be null
   * @param max  maximum number of values to replace, or {@code -1} if no maximum
   * @return the text with any replacements processed,
   *  {@code null} if null String input
   */
  public static String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
    return replace(text, searchString, replacement, max, true);
  }

  /**
   * <p>
   * Replaces all occurrences of Strings within another String.
   * </p>
   *
   * <p>
   * A {@code null} reference passed to this method is a no-op, or if
   * any "search string" or "string to replace" is null, that replace will be
   * ignored. This will not repeat. For repeating replaces, call the
   * overloaded method.
   * </p>
   *
   * <pre>
   *  StringUtils.replaceEach(null, *, *)        = null
   *  StringUtils.replaceEach("", *, *)          = ""
   *  StringUtils.replaceEach("aba", null, null) = "aba"
   *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
   *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
   *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
   *  (example of how it does not repeat)
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
   * </pre>
   *
   * @param text
   *            text to search and replace in, no-op if null
   * @param searchList
   *            the Strings to search for, no-op if null
   * @param replacementList
   *            the Strings to replace them with, no-op if null
   * @return the text with any replacements processed, {@code null} if
   *         null String input
   * @throws IllegalArgumentException
   *             if the lengths of the arrays are not the same (null is ok,
   *             and/or size 0)
   */
  public static String replaceEach(final String text, final String[] searchList, final String[] replacementList) {
    return replaceEach(text, searchList, replacementList, false, 0);
  }

  /**
   * <p>
   * Replaces all occurrences of Strings within another String.
   * </p>
   *
   * <p>
   * A {@code null} reference passed to this method is a no-op, or if
   * any "search string" or "string to replace" is null, that replace will be
   * ignored.
   * </p>
   *
   * <pre>
   *  StringUtils.replaceEachRepeatedly(null, *, *) = null
   *  StringUtils.replaceEachRepeatedly("", *, *) = ""
   *  StringUtils.replaceEachRepeatedly("aba", null, null) = "aba"
   *  StringUtils.replaceEachRepeatedly("aba", new String[0], null) = "aba"
   *  StringUtils.replaceEachRepeatedly("aba", null, new String[0]) = "aba"
   *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, null) = "aba"
   *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}) = "b"
   *  StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}) = "aba"
   *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}) = "wcte"
   *  (example of how it repeats)
   *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}) = "tcte"
   *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}) = IllegalStateException
   * </pre>
   *
   * @param text
   *            text to search and replace in, no-op if null
   * @param searchList
   *            the Strings to search for, no-op if null
   * @param replacementList
   *            the Strings to replace them with, no-op if null
   * @return the text with any replacements processed, {@code null} if
   *         null String input
   * @throws IllegalStateException
   *             if the search is repeating and there is an endless loop due
   *             to outputs of one being inputs to another
   * @throws IllegalArgumentException
   *             if the lengths of the arrays are not the same (null is ok,
   *             and/or size 0)
   */
  public static String replaceEachRepeatedly(final String text, final String[] searchList, final String[] replacementList) {
    // timeToLive should be 0 if not used or nothing to replace, else it's
    // the length of the replace array
    final int timeToLive = searchList == null ? 0 : searchList.length;
    return replaceEach(text, searchList, replacementList, true, timeToLive);
  }

  /**
   * <p>
   * Replace all occurrences of Strings within another String.
   * This is a private recursive helper method for {@link #replaceEachRepeatedly(String, String[], String[])} and
   * {@link #replaceEach(String, String[], String[])}
   * </p>
   *
   * <p>
   * A {@code null} reference passed to this method is a no-op, or if
   * any "search string" or "string to replace" is null, that replace will be
   * ignored.
   * </p>
   *
   * <pre>
   *  StringUtils.replaceEach(null, *, *, *, *) = null
   *  StringUtils.replaceEach("", *, *, *, *) = ""
   *  StringUtils.replaceEach("aba", null, null, *, *) = "aba"
   *  StringUtils.replaceEach("aba", new String[0], null, *, *) = "aba"
   *  StringUtils.replaceEach("aba", null, new String[0], *, *) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, null, *, *) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""}, *, >=0) = "b"
   *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"}, *, >=0) = "aba"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}, *, >=0) = "wcte"
   *  (example of how it repeats)
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, false, >=0) = "dcte"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, true, >=2) = "tcte"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}, *, *) = IllegalStateException
   * </pre>
   *
   * @param text
   *            text to search and replace in, no-op if null
   * @param searchList
   *            the Strings to search for, no-op if null
   * @param replacementList
   *            the Strings to replace them with, no-op if null
   * @param repeat if true, then replace repeatedly
   *       until there are no more possible replacements or timeToLive < 0
   * @param timeToLive
   *            if less than 0 then there is a circular reference and endless
   *            loop
   * @return the text with any replacements processed, {@code null} if
   *         null String input
   * @throws IllegalStateException
   *             if the search is repeating and there is an endless loop due
   *             to outputs of one being inputs to another
   * @throws IllegalArgumentException
   *             if the lengths of the arrays are not the same (null is ok,
   *             and/or size 0)
   */
  private static String replaceEach(
      final String text, final String[] searchList, final String[] replacementList, final boolean repeat, final int timeToLive) {

    // mchyzer Performance note: This creates very few new objects (one major goal)
    // let me know if there are performance requests, we can create a harness to measure

    if (text == null || text.isEmpty() || searchList == null ||
        searchList.length == 0 || replacementList == null || replacementList.length == 0) {
      return text;
    }

    // if recursing, this shouldn't be less than 0
    if (timeToLive < 0) {
      throw new IllegalStateException("Aborting to protect against StackOverflowError - " +
          "output of one loop is the input of another");
    }

    final int searchLength = searchList.length;
    final int replacementLength = replacementList.length;

    // make sure lengths are ok, these need to be equal
    if (searchLength != replacementLength) {
      throw new IllegalArgumentException("Search and Replace array lengths don't match: "
          + searchLength
          + " vs "
          + replacementLength);
    }

    // keep track of which still have matches
    final boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

    // index on index that the match was found
    int textIndex = -1;
    int replaceIndex = -1;
    int tempIndex = -1;

    // index of replace array that will replace the search string found
    // NOTE: logic duplicated below START
    for (int i = 0; i < searchLength; i++) {
      if (noMoreMatchesForReplIndex[i] || searchList[i] == null ||
          searchList[i].isEmpty() || replacementList[i] == null) {
        continue;
      }
      tempIndex = text.indexOf(searchList[i]);

      // see if we need to keep searching for this
      if (tempIndex == -1) {
        noMoreMatchesForReplIndex[i] = true;
      } else {
        if (textIndex == -1 || tempIndex < textIndex) {
          textIndex = tempIndex;
          replaceIndex = i;
        }
      }
    }
    // NOTE: logic mostly below END

    // no search strings found, we are done
    if (textIndex == -1) {
      return text;
    }

    int start = 0;

    // get a good guess on the size of the result buffer so it doesn't have to double if it goes over a bit
    int increase = 0;

    // count the replacement text elements that are larger than their corresponding text being replaced
    for (int i = 0; i < searchList.length; i++) {
      if (searchList[i] == null || replacementList[i] == null) {
        continue;
      }
      final int greater = replacementList[i].length() - searchList[i].length();
      if (greater > 0) {
        increase += 3 * greater; // assume 3 matches
      }
    }
    // have upper-bound at 20% increase, then let Java take over
    increase = Math.min(increase, text.length() / 5);

    final StringBuilder buf = new StringBuilder(text.length() + increase);

    while (textIndex != -1) {

      for (int i = start; i < textIndex; i++) {
        buf.append(text.charAt(i));
      }
      buf.append(replacementList[replaceIndex]);

      start = textIndex + searchList[replaceIndex].length();

      textIndex = -1;
      replaceIndex = -1;
      tempIndex = -1;
      // find the next earliest match
      // NOTE: logic mostly duplicated above START
      for (int i = 0; i < searchLength; i++) {
        if (noMoreMatchesForReplIndex[i] || searchList[i] == null ||
            searchList[i].isEmpty() || replacementList[i] == null) {
          continue;
        }
        tempIndex = text.indexOf(searchList[i], start);

        // see if we need to keep searching for this
        if (tempIndex == -1) {
          noMoreMatchesForReplIndex[i] = true;
        } else {
          if (textIndex == -1 || tempIndex < textIndex) {
            textIndex = tempIndex;
            replaceIndex = i;
          }
        }
      }
      // NOTE: logic duplicated above END

    }
    final int textLength = text.length();
    for (int i = start; i < textLength; i++) {
      buf.append(text.charAt(i));
    }
    final String result = buf.toString();
    if (!repeat) {
      return result;
    }

    return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
  }

  // Replace, character based
  //-----------------------------------------------------------------------
  /**
   * <p>Replaces all occurrences of a character in a String with another.
   * This is a null-safe version of {@link String#replace(char, char)}.</p>
   *
   * <p>A {@code null} string input returns {@code null}.
   * An empty ("") string input returns an empty string.</p>
   *
   * <pre>
   * StringUtils.replaceChars(null, *, *)        = null
   * StringUtils.replaceChars("", *, *)          = ""
   * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
   * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
   * </pre>
   *
   * @param str  String to replace characters in, may be null
   * @param searchChar  the character to search for, may be null
   * @param replaceChar  the character to replace, may be null
   * @return modified String, {@code null} if null string input
   */
  public static String replaceChars(final String str, final char searchChar, final char replaceChar) {
    if (str == null) {
      return null;
    }
    return str.replace(searchChar, replaceChar);
  }

  /**
   * <p>Replaces multiple characters in a String in one go.
   * This method can also be used to delete characters.</p>
   *
   * <p>For example:<br>
   * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>.</p>
   *
   * <p>A {@code null} string input returns {@code null}.
   * An empty ("") string input returns an empty string.
   * A null or empty set of search characters returns the input string.</p>
   *
   * <p>The length of the search characters should normally equal the length
   * of the replace characters.
   * If the search characters is longer, then the extra search characters
   * are deleted.
   * If the search characters is shorter, then the extra replace characters
   * are ignored.</p>
   *
   * <pre>
   * StringUtils.replaceChars(null, *, *)           = null
   * StringUtils.replaceChars("", *, *)             = ""
   * StringUtils.replaceChars("abc", null, *)       = "abc"
   * StringUtils.replaceChars("abc", "", *)         = "abc"
   * StringUtils.replaceChars("abc", "b", null)     = "ac"
   * StringUtils.replaceChars("abc", "b", "")       = "ac"
   * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
   * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
   * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
   * </pre>
   *
   * @param str  String to replace characters in, may be null
   * @param searchChars  a set of characters to search for, may be null
   * @param replaceChars  a set of characters to replace, may be null
   * @return modified String, {@code null} if null string input
   */
  public static String replaceChars(final String str, final String searchChars, String replaceChars) {
    if (isEmpty(str) || isEmpty(searchChars)) {
      return str;
    }
    if (replaceChars == null) {
      replaceChars = EMPTY;
    }
    boolean modified = false;
    final int replaceCharsLength = replaceChars.length();
    final int strLength = str.length();
    final StringBuilder buf = new StringBuilder(strLength);
    for (int i = 0; i < strLength; i++) {
      final char ch = str.charAt(i);
      final int index = searchChars.indexOf(ch);
      if (index >= 0) {
        modified = true;
        if (index < replaceCharsLength) {
          buf.append(replaceChars.charAt(index));
        }
      } else {
        buf.append(ch);
      }
    }
    if (modified) {
      return buf.toString();
    }
    return str;
  }

  // startsWith
  //-----------------------------------------------------------------------
  /**
   * <p>Check if a String starts with a specified prefix.</p>
   *
   * <pre>
   * StringUtils.startsWith(null, 'a')       = false
   * StringUtils.startsWith("abcdef", 'a')   = true
   * StringUtils.startsWith("ABCDEF", 'a')   = false
   * </pre>
   *
   * @param str  the String to check, may be null
   * @param prefix the prefix to find
   * @return {@code true} if the String starts with the prefix, case sensitive.
   */
  public static boolean startsWith(final String str, final char prefix) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    return str.charAt(0) == prefix;
  }

  /**
   * <p>Check if a String starts with a specified prefix.</p>
   *
   * <p>{@code null}s are handled without exceptions. Two {@code null}
   * references are considered to be equal. The comparison is case sensitive.</p>
   *
   * <pre>
   * StringUtils.startsWith(null, null)      = true
   * StringUtils.startsWith(null, "abc")     = false
   * StringUtils.startsWith("abcdef", null)  = false
   * StringUtils.startsWith("abcdef", "abc") = true
   * StringUtils.startsWith("ABCDEF", "abc") = false
   * </pre>
   *
   * @see java.lang.String#startsWith(String)
   * @param str  the String to check, may be null
   * @param prefix the prefix to find, may be null
   * @return {@code true} if the String starts with the prefix, case sensitive, or
   *  both {@code null}
   */
  public static boolean startsWith(final String str, final String prefix) {
    if (str == null || prefix == null) {
      return str == null && prefix == null;
    }
    if (prefix.length() > str.length()) {
      return false;
    }
    return str.startsWith(prefix);
  }

  /**
   * <p>Check if a String starts with any of the provided case-sensitive prefixes.</p>
   *
   * <pre>
   * StringUtils.startsWithAny(null, null)      = false
   * StringUtils.startsWithAny(null, new String[] {"abc"})  = false
   * StringUtils.startsWithAny("abcxyz", null)     = false
   * StringUtils.startsWithAny("abcxyz", new String[] {""}) = true
   * StringUtils.startsWithAny("abcxyz", new String[] {"abc"}) = true
   * StringUtils.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
   * StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX") = false
   * StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc") = false
   * </pre>
   *
   * @param sequence the String to check, may be null
   * @param searchStrings the case-sensitive String prefixes, may be empty or contain {@code null}
   * @see StringUtils#startsWith(String, String)
   * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
   *   the input {@code sequence} begins with any of the provided case-sensitive {@code searchStrings}.
   */
  public static boolean startsWithAny(final String sequence, final String... searchStrings) {
    if (isEmpty(sequence) || searchStrings == null || searchStrings.length == 0) {
      return false;
    }
    for (final String searchString : searchStrings) {
      if (startsWith(sequence, searchString)) {
        return true;
      }
    }
    return false;
  }

  // endsWith
  //-----------------------------------------------------------------------
  /**
   * <p>Check if a String ends with a specified suffix.</p>
   *
   * <pre>
   * StringUtils.endsWith(null, 'f')       = false
   * StringUtils.endsWith("abcdef", 'f')   = true
   * StringUtils.endsWith("ABCDEF", 'f')   = false
   * </pre>
   *
   * @param str the String to check, may be null
   * @param suffix the suffix to find
   * @return {@code true} if the String ends with the suffix, case sensitive.
   */
  public static boolean endsWith(final String str, final char suffix) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    return str.charAt(str.length() - 1) == suffix;
  }

  /**
   * <p>Check if a String ends with a specified suffix.</p>
   *
   * <p>{@code null}s are handled without exceptions. Two {@code null}
   * references are considered to be equal. The comparison is case sensitive.</p>
   *
   * <pre>
   * StringUtils.endsWith(null, null)      = true
   * StringUtils.endsWith(null, "def")     = false
   * StringUtils.endsWith("abcdef", null)  = false
   * StringUtils.endsWith("abcdef", "def") = true
   * StringUtils.endsWith("ABCDEF", "def") = false
   * StringUtils.endsWith("ABCDEF", "cde") = false
   * StringUtils.endsWith("ABCDEF", "")    = true
   * </pre>
   *
   * @see java.lang.String#endsWith(String)
   * @param str  the String to check, may be null
   * @param suffix the suffix to find, may be null
   * @return {@code true} if the String ends with the suffix, case sensitive, or
   *  both {@code null}
   */
  public static boolean endsWith(final String str, final String suffix) {
    if (str == null || suffix == null) {
      return str == null && suffix == null;
    }
    if (suffix.length() > str.length()) {
      return false;
    }
    return str.endsWith(suffix);
  }

  /**
   * <p>Check if a String ends with any of the provided case-sensitive suffixes.</p>
   *
   * <pre>
   * StringUtils.endsWithAny(null, null)      = false
   * StringUtils.endsWithAny(null, new String[] {"abc"})  = false
   * StringUtils.endsWithAny("abcxyz", null)     = false
   * StringUtils.endsWithAny("abcxyz", new String[] {""}) = true
   * StringUtils.endsWithAny("abcxyz", new String[] {"xyz"}) = true
   * StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
   * StringUtils.endsWithAny("abcXYZ", "def", "XYZ") = true
   * StringUtils.endsWithAny("abcXYZ", "def", "xyz") = false
   * </pre>
   *
   * @param sequence  the String to check, may be null
   * @param searchStrings the case-sensitive String to find, may be empty or contain {@code null}
   * @see StringUtils#endsWith(String, String)
   * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
   *   the input {@code sequence} ends in any of the provided case-sensitive {@code searchStrings}.
   */
  public static boolean endsWithAny(final String sequence, final String... searchStrings) {
    if (isEmpty(sequence) || searchStrings == null || searchStrings.length == 0) {
      return false;
    }
    for (final String searchString : searchStrings) {
      if (endsWith(sequence, searchString)) {
        return true;
      }
    }
    return false;
  }

  // Count matches
  //-----------------------------------------------------------------------
  /**
   * <p>Counts how many times the char appears in the given string.</p>
   *
   * <p>A {@code null} or empty ("") String input returns {@code 0}.</p>
   *
   * <pre>
   * StringUtils.countMatches(null, *)       = 0
   * StringUtils.countMatches("", *)         = 0
   * StringUtils.countMatches("abba", 0)  = 0
   * StringUtils.countMatches("abba", 'a')   = 2
   * StringUtils.countMatches("abba", 'b')  = 2
   * StringUtils.countMatches("abba", 'x') = 0
   * </pre>
   *
   * @param str  the CharSequence to check, may be null
   * @param ch  the char to count
   * @return the number of occurrences, 0 if the CharSequence is {@code null}
   */
  public static int countMatches(final CharSequence str, final char ch) {
    if (isEmpty(str)) {
      return 0;
    }
    int count = 0;
    // We could also call str.toCharArray() for faster look ups but that would generate more garbage.
    for (int i = 0; i < str.length(); i++) {
      if (ch == str.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  // Length
  //-----------------------------------------------------------------------
  /**
   * Gets a CharSequence length or {@code 0} if the CharSequence is
   * {@code null}.
   *
   * @param cs
   *            a CharSequence or {@code null}
   * @return CharSequence length or {@code 0} if the CharSequence is
   *         {@code null}.
   * @since 2.4
   * @since 3.0 Changed signature from length(String) to length(CharSequence)
   */
  public static int length(final CharSequence cs) {
    return cs == null ? 0 : cs.length();
  }

  // Join
  //-----------------------------------------------------------------------
  /**
   * Joins two parts to a whole one, avoids one separator after another separator.
   *
   * <pre>
   * StringUtils.join(null, null, '/')           = null
   * StringUtils.join("base", null, '/')         = "base"
   * StringUtils.join(null, "relative", '/')     = "relative"
   * StringUtils.join("base", "relative", '/')   = "base/relative"
   * StringUtils.join("base/", "relative", '/')  = "base/relative"
   * StringUtils.join("base", "/relative", '/')  = "base/relative"
   * StringUtils.join("base/", "/relative", '/') = "base/relative"
   * </pre>
   *
   * @param base the first part, may be null
   * @param relative the second part, may be null
   * @param separator the separator character
   * @return the whole
   */
  public static String join(String base, String relative, char separator) {
    if (base == null && relative == null) {
      return null;
    }
    if (base == null) {
      return relative;
    }
    if (relative == null) {
      return base;
    }

    if (StringUtils.endsWith(base, separator)) {
      if (StringUtils.startsWith(relative, separator)) {
        return base + relative.substring(1);
      } else {
        return base + relative;
      }
    } else {
      if (StringUtils.startsWith(relative, separator)) {
        return base + relative;
      } else {
        return base + separator + relative;
      }
    }
  }
}
