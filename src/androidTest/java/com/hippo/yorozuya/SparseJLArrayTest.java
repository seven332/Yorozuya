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

import com.hippo.yorozuya.sparse.SparseJLArray;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SparseJLArrayTest extends TestCase {

    public void testOrder() {
        SparseJLArray<String> sparse = new SparseJLArray<>();
        sparse.append(124L, Long.toString(124L));
        sparse.append(14L, Long.toString(14L));
        sparse.append(144L, Long.toString(144L));

        Assert.assertEquals(14L, sparse.keyAt(0));
        Assert.assertEquals(124L, sparse.keyAt(1));
        Assert.assertEquals(144L, sparse.keyAt(2));
    }


}
