/*
 * Copyright 2015 Hippo Seven
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

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {

    private static final int EOF = -1;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * Colse the closeable stuff. Don't worry about anything.
     *
     * @param is the closeable stuff
     */
    public static void closeQuietly(Closeable is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * Copy bytes from an <code>InputStream</code> to an
     * <code>OutputStream</code>.
     *
     * @param input the InputStream
     * @param output the OutputStream
     * @return the number of bytes copied
     * @throws IOException
     */
    public static long copy(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
