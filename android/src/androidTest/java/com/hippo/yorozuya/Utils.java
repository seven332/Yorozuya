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

public final class Utils {
  private Utils() {}

  private static String formatClassAndValue(Object value, String valueString) {
    String className = value == null ? "null" : value.getClass().getName();
    return className + "<" + valueString + ">";
  }

  private static String format(String message, Object expected, Object actual) {
    String formatted = "";
    if (message != null && !message.equals("")) {
      formatted = message + " ";
    }
    String expectedString = String.valueOf(expected);
    String actualString = String.valueOf(actual);
    if (expectedString.equals(actualString)) {
      return formatted + "expected: "
          + formatClassAndValue(expected, expectedString)
          + " but was: " + formatClassAndValue(actual, actualString);
    } else {
      return formatted + "expected:<" + expectedString + "> but was:<"
          + actualString + ">";
    }
  }

  public static void assertEqualsFloat(float expected, float actual) {
    if (expected != actual) {
      throw new AssertionError(format(null, Float.valueOf(expected), Float.valueOf(actual)));
    }
  }

  public static void assertEqualsDouble(double expected, double actual) {
    if (expected != actual) {
      throw new AssertionError(format(null, Double.valueOf(expected), Double.valueOf(actual)));
    }
  }
}
