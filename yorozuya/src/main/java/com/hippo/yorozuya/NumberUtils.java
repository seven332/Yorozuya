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

import java.util.Locale;

public final class NumberUtils {
  private NumberUtils() {}

  private static final String METRIC_PREFIX = "KMGTPEZY";

  /**
   * Converts a {@code int} to a {@code boolean}.
   *
   * @param integer the input
   * @return {@code false} if {@code integer} is zero, otherwise {@code true}
   */
  public static boolean toBoolean(int integer) {
    return integer != 0;
  }

  /**
   * Converts a {@code int} to a {@code boolean}.
   *
   * @param bool the input
   * @return {@code 1} if {@code bool} is {@code true}, otherwise {@code 0}
   */
  public static int toInt(boolean bool) {
    return bool ? 1 : 0;
  }

  /**
   * Parses a string to a integer without NumberFormatException.
   * Returns {@code defaultValue} if if the string does not contain a
   * parsable integer.
   *
   * @param str the string to parse
   * @param defaultValue the value to return when get error
   * @return the value of the string
   */
  public static int parseInt(String str, int defaultValue) {
    if (str == null) return defaultValue;
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  /**
   * Parses a string to a long without NumberFormatException.
   * Returns {@code defaultValue} if if the string does not contain a
   * parsable long.
   *
   * @param str the string to parse
   * @param defaultValue the value to return when get error
   * @return the value of the string
   */
  public static long parseLong(String str, long defaultValue) {
    if (str == null) return defaultValue;
    try {
      return Long.parseLong(str);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  /**
   * Parses a string to a float without NumberFormatException.
   * Returns {@code defaultValue} if if the string does not contain a
   * parsable float.
   *
   * @param str the string to parse
   * @param defaultValue the value to return when get error
   * @return the value of the string
   */
  public static float parseFloat(String str, float defaultValue) {
    if (str == null) return defaultValue;
    try {
      return Float.parseFloat(str);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  /**
   * Parses a string to a double without NumberFormatException.
   * Returns {@code defaultValue} if if the string does not contain a
   * parsable double.
   *
   * @param str the string to parse
   * @param defaultValue the value to return when get error
   * @return the value of the string
   */
  public static double parseDouble(String str, double defaultValue) {
    if (str == null) return defaultValue;
    try {
      return Double.parseDouble(str);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  /**
   * Convert the number to a string with binary prefix based on 1024 keeping one decimal.
   * <p>
   * For example:
   * <ul>
   * <li>{@code binaryPrefix(23) = "23"}</li>
   * <li>{@code binaryPrefix(1024) = "1.0K"}</li>
   * <li>{@code binaryPrefix(-1234) = "-1.2K"}</li>
   * </ul>
   *
   * @param n the input
   * @return the requested string
   */
  public static String binaryPrefix(long n) {
    return binaryPrefix(n, 1);
  }

  /**
   * Convert the number to a string with binary prefix based on 1024.
   * <p>
   * For example:
   * <ul>
   * <li>{@code binaryPrefix(23, 4) = "23"}</li>
   * <li>{@code binaryPrefix(1234, 5) = "1.20508K"}</li>
   * <li>{@code binaryPrefix(-1234, 5) = "-1.20508K"}</li>
   * </ul>
   *
   * @param n the input
   * @param place the number of decimal to keep, set to {@code 0} if it smaller than {@code 0}
   * @return the requested string
   */
  public static String binaryPrefix(long n, int place) {
    return metricPrefix(n, place, 1024);
  }

  /**
   * Convert the number to a string with metric prefix based on 1000 keeping one decimal.
   * <p>
   * For example:
   * <ul>
   * <li>{@code metricPrefix(23) = "23"}</li>
   * <li>{@code metricPrefix(1234) = "1.2K"}</li>
   * <li>{@code metricPrefix(-1234) = "-1.2K"}</li>
   * </ul>
   *
   * @param n the input
   * @return the requested string
   */
  public static String metricPrefix(long n) {
    return metricPrefix(n, 1);
  }

  /**
   * Convert the number to a string with metric prefix based on 1000.
   * <p>
   * For example:
   * <ul>
   * <li>{@code metricPrefix(23, 4) = "23"}</li>
   * <li>{@code metricPrefix(1234, 3) = "1.234K"}</li>
   * <li>{@code metricPrefix(-1234, 3) = "-1.234K"}</li>
   * </ul>
   *
   * @param n the input
   * @param place the number of decimal to keep, set to {@code 0} if it smaller than {@code 0}
   * @return the requested string
   */
  public static String metricPrefix(long n, int place) {
    return metricPrefix(n, place, 1000);
  }

  /**
   * Convert the number to a string with metric prefix.
   * <p>
   * For example:
   * <ul>
   * <li>{@code metricPrefix(11, 1, 2) = "1.4G"}</li>
   * </ul>
   *
   * @param n the input
   * @param place the number of decimal to keep, set to {@code 0} if it smaller than {@code 0}
   * @param base the base
   * @return the requested string
   * @throws ArithmeticException if {@code base} is 0 or 1 or -1
   */
  public static String metricPrefix(long n, int place, int base) {
    if (base == 0) throw new ArithmeticException("base == 0");
    if (base == 1) throw new ArithmeticException("base == 1");
    if (base == -1) throw new ArithmeticException("base == -1");
    if (place < 0) place = 0;

    int signum = Long.signum(n * base);
    n = Math.abs(n);
    base = Math.abs(base);

    if (n < base) {
      return Long.toString(signum * n);
    }

    int exponent = Math.min(METRIC_PREFIX.length(), (int) (Math.log(n) / Math.log(base)));
    char prefix = METRIC_PREFIX.charAt(exponent - 1);
    String formatter = "%." + place + "f%c";
    return String.format(Locale.US, formatter, signum * n / Math.pow(base, exponent), prefix);
  }
}
