/*
 * Copyright 2016 Hippo Seven
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

import com.hippo.yorozuya.sparse.ContainerHelpers;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ContainerHelpersTest extends TestCase {

    public void testBinarySearch() {
        int[] array;

        array = new int[]{};
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 6));
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 10));

        array = new int[]{8};
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 6));
        Assert.assertEquals(~1, ContainerHelpers.binarySearch(array, array.length, 10));

        array = new int[]{8, 12};
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 6));
        Assert.assertEquals(~1, ContainerHelpers.binarySearch(array, array.length, 10));
        Assert.assertEquals(~2, ContainerHelpers.binarySearch(array, array.length, 14));

        array = new int[]{8, 12};
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 6));
        Assert.assertEquals(~1, ContainerHelpers.binarySearch(array, array.length, 10));
        Assert.assertEquals(~2, ContainerHelpers.binarySearch(array, array.length, 14));

        array = new int[]{8, 12, 16};
        Assert.assertEquals(~0, ContainerHelpers.binarySearch(array, array.length, 6));
        Assert.assertEquals(~1, ContainerHelpers.binarySearch(array, array.length, 10));
        Assert.assertEquals(~2, ContainerHelpers.binarySearch(array, array.length, 14));
        Assert.assertEquals(~3, ContainerHelpers.binarySearch(array, array.length, 18));
    }
}
