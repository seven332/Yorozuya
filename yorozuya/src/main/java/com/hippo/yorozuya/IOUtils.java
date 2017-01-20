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

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IOUtils {
  private IOUtils() {}

  private static final int EOF = -1;
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

  public static final Charset UTF_8 = Charset.forName("UTF-8");

  /**
   * Closes the {@link Closeable}. Have no worries.
   */
  public static void closeQuietly(@Nullable Closeable is) {
    try {
      if (is != null) is.close();
    } catch (IOException e) {
      // Ignore
    }
  }

  /**
   * Copies all bytes from {@code is} to {@code os}.
   * Returns how many bytes copied.
   */
  public static long copy(@Nonnull InputStream is, @Nonnull OutputStream os) throws IOException {
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n;
    while ((n = is.read(buffer)) != EOF) {
      os.write(buffer, 0, n);
      count += n;
    }
    return count;
  }

  /**
   * Reads all bytes from {@code is} as a UTF-8 {@link String}.
   */
  public static String toString(@Nonnull InputStream is) throws IOException {
    return toString(is, UTF_8);
  }

  /**
   * Reads all bytes from {@code is} as a {@link String}.
   */
  public static String toString(@Nonnull InputStream is, @Nonnull Charset charset) throws IOException {
    return toString(is, charset.name());
  }

  /**
   * Reads all bytes from {@code is} as a {@link String}.
   */
  public static String toString(@Nonnull InputStream is, @Nonnull String charsetName) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    copy(is, os);
    return os.toString(charsetName);
  }

  /**
   * Reads all bytes from {@code is}.
   */
  public static byte[] toByteArray(@Nonnull InputStream is) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    copy(is, os);
    return os.toByteArray();
  }
}
