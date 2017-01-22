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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObjectUtilsTest {

  @Test
  public void testEquals() {
    assertTrue(ObjectUtils.equals(null, null));
    assertFalse(ObjectUtils.equals(null, "EhViewer"));
    assertTrue(ObjectUtils.equals(new String("EhViewer"), "EhViewer"));
  }

  @Test
  public void testHashCode() {
    assertEquals(0, ObjectUtils.hashCode(null));
    assertEquals("EhViewer".hashCode(), ObjectUtils.hashCode("EhViewer"));
  }

  @Test
  public void testToString() {
    assertEquals("null", ObjectUtils.toString(null));
    assertEquals("EhViewer", ObjectUtils.toString("EhViewer"));

    assertEquals("EhViewer", ObjectUtils.toString(null, "EhViewer"));
    assertEquals("EhViewer", ObjectUtils.toString("EhViewer", "EhViewer2"));
  }
}
