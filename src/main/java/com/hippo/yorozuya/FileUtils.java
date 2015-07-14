/*
 * Copyright (C) 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import java.io.File;

public final class FileUtils {

    public static boolean ensureFile(File file) {
        return file != null && (!file.exists() || file.isFile());
    }

    public static boolean ensureDirectory(File file) {
        if (file != null) {
            if (file.exists()) {
                return file.isDirectory();
            } else {
                return file.mkdirs();
            }
        } else {
            return false;
        }
    }

    /**
     * Convert byte to human readable string.<br/>
     * http://stackoverflow.com/questions/3758606/
     *
     * @param bytes the bytes to convert
     * @param si si units
     * @return the human readable string
     */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
