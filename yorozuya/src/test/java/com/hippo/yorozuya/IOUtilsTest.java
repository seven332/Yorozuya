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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class IOUtilsTest {

  @Test
  public void testCloseQuietly() throws IOException {
    InputStream in1 = null;
    IOUtils.closeQuietly(in1);

    TemporaryFolder folder = new TemporaryFolder();
    folder.create();
    File file = folder.newFile();
    InputStream in2 = new FileInputStream(file);
    IOUtils.closeQuietly(in2);
    try {
      in2.read();
      fail();
    } catch (IOException e) {
    }
  }

  @Test
  public void testCopy() throws IOException {
    final String sample = "XiXiHaHaHaoKaiXin";
    final int repeat = 1024 * 8;

    TemporaryFolder folder = new TemporaryFolder();
    folder.create();

    File inFile = folder.newFile();
    File outFile = folder.newFile();

    BufferedSink sink = Okio.buffer(Okio.sink(inFile));
    for (int i = 0; i < repeat; i++) {
      sink.writeUtf8(sample);
    }
    sink.close();

    InputStream is = new FileInputStream(inFile);
    OutputStream os = new FileOutputStream(outFile);
    IOUtils.copy(is, os);
    is.close();
    os.close();

    BufferedSource source = Okio.buffer(Okio.store(inFile));
    for (int i = 0; i < repeat; i++) {
      assertEquals(sample, source.readUtf8(sample.length()));
    }
    assertTrue(source.exhausted());
    source.close();
  }

  @Test
  public void testToString() throws IOException {
    final byte[] sample = {
        (byte) 0xc4, (byte) 0xe3, // GBK 你
        (byte) 0xba, (byte) 0xc3, // GBK 好
    };
    final String nihao = "你好";
    final int repeat = 1024 * 8;

    TemporaryFolder folder = new TemporaryFolder();
    folder.create();

    File file = folder.newFile();

    BufferedSink sink = Okio.buffer(Okio.sink(file));
    for (int i = 0; i < repeat; i++) {
      sink.write(sample);
    }
    sink.close();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < repeat; i++) {
      sb.append(nihao);
    }
    String string = sb.toString();

    InputStream is = new FileInputStream(file);
    assertEquals(string, IOUtils.toString(is, "GBK"));
    is.close();
  }

  @Test
  public void testToByteArray() throws IOException {
    final byte[] sample = {
        (byte) 0x33, (byte) 0xf7, (byte) 0x42, (byte) 0xcf,
    };
    final int repeat = 1024 * 8;

    TemporaryFolder folder = new TemporaryFolder();
    folder.create();

    File file = folder.newFile();

    BufferedSink sink = Okio.buffer(Okio.sink(file));
    for (int i = 0; i < repeat; i++) {
      sink.write(sample);
    }
    sink.close();

    InputStream is = new FileInputStream(file);
    byte[] result = IOUtils.toByteArray(is);
    is.close();

    int start = 0;
    for (int i = 0; i < repeat; i++) {
      for (int j = 0; j < sample.length; j++) {
        assertEquals(sample[j], result[start + j]);
      }
      start += sample.length;
    }
  }
}
