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
 * Created by Hippo on 2/13/2017.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsJoinTest {

  @Test
  public void testJoin() {
    assertEquals(null, StringUtils.join(null, null, '/'));
    assertEquals("base", StringUtils.join("base", null, '/'));
    assertEquals("relative", StringUtils.join(null, "relative", '/'));
    assertEquals("base/relative", StringUtils.join("base", "relative", '/'));
    assertEquals("base/relative", StringUtils.join("base/", "relative", '/'));
    assertEquals("base/relative", StringUtils.join("base", "/relative", '/'));
    assertEquals("base/relative", StringUtils.join("base/", "/relative", '/'));
  }
}
