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

import java.io.File;

public class FileUtils {
  private FileUtils() {}

  /**
   * Ensure {@code file} is a file or can be created as a file.
   *
   * @param file the file to check, may be null
   * @return {@code true} if {@code file} is a file or can be created as a file,
   * otherwise {@code false} or {@code file} is null
   */
  public static boolean ensureFile(File file) {
    return file != null && (!file.exists() || file.isFile());
  }

  /**
   * Ensure {@code file} is a directory or create it as a directory.
   *
   * @param file the file to check, may be null
   * @return {@code true} if {@code file} is a directory or mkdirs have succeeded,
   * otherwise {@code false} or {@code file} is null
   */
  public static boolean ensureDir(File file) {
    return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
  }

  /**
   * Deletes {@code file} and its children if it is a directory.
   *
   * @param file the file to delete, may be null
   * @return {@code true} if {@code file} doesn't exists anymore, or {@code file} is null
   */
  public static boolean delete(File file) {
    if (file == null) return true;
    deleteContent(file);
    file.delete();
    return !file.exists();
  }

  /**
   * Deletes all children in the {@code file}.
   *
   * @param file the file to delete all children, may be null
   * @return {@code true} if {@code file} has no child anymore, or {@code file} is a file or null
   */
  public static boolean deleteContent(File file) {
    if (file == null) return true;
    File[] files = file.listFiles();
    if (files != null) {
      for (File f: files) {
        delete(f);
      }
    }
    return ArrayUtils.isEmpty(file.listFiles());
  }
}
