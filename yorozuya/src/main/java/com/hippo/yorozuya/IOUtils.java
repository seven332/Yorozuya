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

public final class IOUtils {
  private IOUtils() {}

  private static final int EOF = -1;
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

  /**
   * UTF_8 Charset.
   */
  public static final Charset UTF_8 = Charset.forName("UTF-8");

  /**
   * Closes the {@link Closeable}. Have no worries.
   *
   * @param closeable the objects to close, may be null or already closed
   */
  public static void closeQuietly(Closeable closeable) {
    try {
      if (closeable != null) closeable.close();
    } catch (IOException e) {
      // Ignore
    }
  }

  /**
   * Copies all bytes from {@code is} to {@code os}.
   *
   * @param is the {@code InputStream} to read from, may be null
   * @param os the {@code OutputStream} to write to, may be null
   * @return the number of bytes copied, or 0 if {@code is} or {@code os} is null
   * @throws IOException if an I/O error occurs
   */
  public static long copy(InputStream is, OutputStream os) throws IOException {
    if (is == null || os == null) return 0;
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
   *
   * @param is the {@code InputStream} to read from, may be null
   * @return the requested String, or {@code null} if {@code is} is null
   * @throws IOException if an I/O error occurs
   */
  public static String toString(InputStream is) throws IOException {
    return toString(is, UTF_8);
  }

  /**
   * Reads all bytes from {@code is} as a {@link String}.
   *
   * @param is the {@code InputStream} to read from, may be null
   * @param charset the charset of the requested String, may be null
   * @return the requested String, or {@code null} if {@code is} or {@code charset} is null
   * @throws IOException if an I/O error occurs
   */
  public static String toString(InputStream is, Charset charset) throws IOException {
    if (is == null || charset == null) return null;
    return toString(is, charset.name());
  }

  /**
   * Reads all bytes from {@code is} as a {@link String}.
   *
   * @param is the {@code InputStream} to read from, may be null
   * @param charsetName the charset name of the requested String, may be null
   * @return the requested String, or {@code null} if {@code is} or {@code charsetName} is null
   * @throws IOException if an I/O error occurs
   */
  public static String toString(InputStream is, String charsetName) throws IOException {
    if (is == null || charsetName == null) return null;
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    copy(is, os);
    return os.toString(charsetName);
  }

  /**
   * Reads all bytes from {@code is}.
   *
   * @param is the {@code InputStream} to read from, may be null
   * @return the requested byte array, or {@code null} if {@code is} is null
   * @throws IOException if an I/O error occurs
   */
  public static byte[] toByteArray(InputStream is) throws IOException {
    if (is == null) return null;
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    copy(is, os);
    return os.toByteArray();
  }
}
