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

import com.hippo.yorozuya.NumberUtils;
import org.junit.Test;

public class OSUtilsTest {

  @Test
  public void testGetAllocatedMemory() {
    System.out.println("OSUtils.getAllocatedMemory() = " + NumberUtils.binaryPrefix(OSUtils.getAllocatedMemory()) + "B");
    System.out.println("OSUtils.getMaxMemory() = " + NumberUtils.binaryPrefix(OSUtils.getMaxMemory()) + "B");
    System.out.println("OSUtils.getTotalMemory() = " + NumberUtils.binaryPrefix(OSUtils.getTotalMemory()) + "B");
  }
}
