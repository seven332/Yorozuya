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
 * Created by Hippo on 1/21/2017.
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileUtilsTest {

  @Test
  public void testEnsureFile() throws IOException {
    TemporaryFolder folder = new TemporaryFolder();
    folder.create();
    File dir = folder.getRoot();

    File file1 = new File(dir, "file1");
    assertFalse(file1.exists());
    assertFalse(file1.isFile());
    assertTrue(FileUtils.ensureFile(file1));
    assertFalse(file1.exists());
    assertFalse(file1.isFile());
    assertTrue(FileUtils.ensureFile(file1));

    File file2 = folder.newFile();
    assertTrue(file2.exists());
    assertTrue(file2.isFile());
    assertTrue(FileUtils.ensureFile(file2));

    File file3 = folder.newFolder();
    assertTrue(file3.exists());
    assertFalse(file3.isFile());
    assertFalse(FileUtils.ensureFile(file3));

    assertFalse(FileUtils.ensureFile(null));
  }

  @Test
  public void testEnsureDir() throws IOException {
    TemporaryFolder folder = new TemporaryFolder();
    folder.create();
    File dir = folder.getRoot();

    File file1 = new File(dir, "file1");
    assertFalse(file1.exists());
    assertFalse(file1.isDirectory());
    assertTrue(FileUtils.ensureDir(file1));
    assertTrue(file1.exists());
    assertTrue(file1.isDirectory());
    assertTrue(FileUtils.ensureDir(file1));

    File file2 = folder.newFolder();
    assertTrue(file2.exists());
    assertTrue(file2.isDirectory());
    assertTrue(FileUtils.ensureDir(file2));

    File file3 = folder.newFile();
    assertTrue(file3.exists());
    assertFalse(file3.isDirectory());
    assertFalse(FileUtils.ensureDir(file3));

    assertFalse(FileUtils.ensureDir(null));
  }

  @Test
  public void testDelete() throws IOException {
    TemporaryFolder folder = new TemporaryFolder();
    folder.create();
    File dir = folder.getRoot();

    File file1 = new File(dir, "file1");
    assertTrue(FileUtils.delete(file1));

    File file2 = folder.newFile();
    assertTrue(FileUtils.delete(file2));

    File file3 = folder.newFolder();
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    assertTrue(!ArrayUtils.isEmpty(file3.listFiles()));
    assertTrue(FileUtils.delete(file3));

    assertTrue(FileUtils.delete(null));
  }

  @Test
  public void testDeleteContent() throws IOException {
    TemporaryFolder folder = new TemporaryFolder();
    folder.create();
    File dir = folder.getRoot();

    File file1 = new File(dir, "file1");
    assertTrue(FileUtils.deleteContent(file1));

    File file2 = folder.newFile();
    assertTrue(file2.exists());
    assertTrue(FileUtils.deleteContent(file2));
    assertTrue(file2.exists());

    File file3 = folder.newFolder();
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    File.createTempFile("file3", null, file3);
    assertTrue(!ArrayUtils.isEmpty(file3.listFiles()));
    assertTrue(FileUtils.delete(file3));
    assertTrue(ArrayUtils.isEmpty(file3.listFiles()));
  }


}
