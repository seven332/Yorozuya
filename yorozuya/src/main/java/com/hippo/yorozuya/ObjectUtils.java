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

public final class ObjectUtils {
  private ObjectUtils() {}

  /**
   * Returns {@code true} if the arguments are equal to each other
   * and {@code false} otherwise.
   * Consequently, if both arguments are {@code null}, {@code true}
   * is returned and if exactly one argument is {@code null}, {@code
   * false} is returned.  Otherwise, equality is determined by using
   * the {@link Object#equals equals} method of the first
   * argument.
   *
   * @param a an object
   * @param b an object to be compared with {@code a} for equality
   * @return {@code true} if the arguments are equal to each other
   * and {@code false} otherwise
   * @see Object#equals(Object)
   */
  public static boolean equals(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
  }

  /**
   * Returns the hash code of a non-{@code null} argument and 0 for
   * a {@code null} argument.
   *
   * @param o an object
   * @return the hash code of a non-{@code null} argument and 0 for
   * a {@code null} argument
   * @see Object#hashCode
   */
  public static int hashCode(Object o) {
    return o != null ? o.hashCode() : 0;
  }

  /**
   * Returns the result of calling {@code toString} for a non-{@code
   * null} argument and {@code "null"} for a {@code null} argument.
   *
   * @param o an object
   * @return the result of calling {@code toString} for a non-{@code
   * null} argument and {@code "null"} for a {@code null} argument
   * @see Object#toString
   * @see String#valueOf(Object)
   */
  public static String toString(Object o) {
    return String.valueOf(o);
  }

  /**
   * Returns the result of calling {@code toString} on the first
   * argument if the first argument is not {@code null} and returns
   * the second argument otherwise.
   *
   * @param o an object
   * @param nullDefault string to return if the first argument is
   *        {@code null}
   * @return the result of calling {@code toString} on the first
   * argument if it is not {@code null} and the second argument
   * otherwise.
   * @see #toString(Object)
   */
  public static String toString(Object o, String nullDefault) {
    return (o != null) ? o.toString() : nullDefault;
  }
}
