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

package com.hippo.yorozuya.android;

/*
 * Created by Hippo on 1/24/2017.
 */

import com.hippo.yorozuya.IOUtils;
import com.hippo.yorozuya.NumberUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSUtils {

  private static final String PROCFS_MEMFILE = "/proc/meminfo";
  private static final Pattern PROCFS_MEMFILE_FORMAT =
      Pattern.compile("^([a-zA-Z]*):[ \t]*([0-9]*)[ \t]kB");
  private static final String MEMTOTAL_STRING = "MemTotal";

  private static long sTotalMem = Long.MIN_VALUE;

  /**
   * Get application allocated memory size in byte.
   *
   * @return application max memory size in byte
   */
  public static long getAllocatedMemory() {
    return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
  }

  /**
   * Get application max memory size in byte.
   *
   * @return application max memory size in byte
   */
  public static long getMaxMemory() {
    return Runtime.getRuntime().maxMemory();
  }

  /**
   * Get device RAM size in byte.
   *
   * @return device RAM size in byte, or {@code -1} if can't get it
   */
  public static long getTotalMemory() {
    if (sTotalMem == Long.MIN_VALUE) {
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(PROCFS_MEMFILE), 64);
        String line;
        while ((line = reader.readLine()) != null) {
          Matcher matcher = PROCFS_MEMFILE_FORMAT.matcher(line);
          if (matcher.find() && MEMTOTAL_STRING.equals(matcher.group(1))) {
            long mem = NumberUtils.parseLong(matcher.group(2), -1L);
            if (mem != -1L) {
              mem *= 1024;
            }
            sTotalMem = mem;
            break;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(reader);
      }
      if (sTotalMem == Long.MIN_VALUE) {
        sTotalMem = -1L;
      }
    }
    return sTotalMem;
  }
}
