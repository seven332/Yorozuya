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

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MathUtilsTest extends TestCase {

    private Map<Integer, Map<Integer, List<Integer>>> mListIntegerMapMap = new HashMap<>();

    private void putListInteger(int count, int maxBit, List<Integer> list) {
        Map<Integer, List<Integer>> map = mListIntegerMapMap.get(count);
        if (map == null) {
            map = new HashMap<>();
            mListIntegerMapMap.put(count, map);
        }
        map.put(maxBit, list);
        for (int i = 0; i < maxBit; i++) {
            map.remove(i);
        }
    }

    private List<Integer> getListInteger(int count, int maxBit) {
        Map<Integer, List<Integer>> map = mListIntegerMapMap.get(count);
        if (map == null) {
            return null;
        }
        return map.get(maxBit);
    }

    private List<Integer> intWithHammingWeight(int count) {
        return intWithHammingWeight(count, Integer.SIZE);
    }

    private List<Integer> intWithHammingWeight(int count, int maxBit) {
        if (count < 0 || count > maxBit) {
            return new LinkedList<>();
        }

        List<Integer> result = getListInteger(count, maxBit);
        if (result != null) {
            return result;
        }

        result = new LinkedList<>();
        if (count == 0) {
            result.add(0);
        } else {
            for (int i = maxBit; i >= count; i--) {
                int n = 1 << (i - 1);
                List<Integer> sub = intWithHammingWeight(count - 1, i - 1);
                for (int j : sub) {
                    result.add(n | j);
                }
            }
        }

        putListInteger(count, maxBit, result);

        return result;
    }

    public void testHammingWeight1() {
        for (int i = 0; i <= Integer.SIZE; i++) {
            System.out.println("i = " + i);
            List<Integer> integers = intWithHammingWeight(i);
            for (int j : integers) {
                System.out.println(String.format("%32s", Integer.toBinaryString(j)).replace(" ", "0"));
                Assert.assertEquals(i, MathUtils.hammingWeight(j));
            }
        }
    }
}
