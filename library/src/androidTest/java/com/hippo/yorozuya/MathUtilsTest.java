package com.hippo.yorozuya;

import junit.framework.TestCase;

public class MathUtilsTest extends TestCase {

    public void testMax() {
        assertEquals(4325, MathUtils.max(1, 0, 345, -78, -3434_434, 4325, 1, 0));

        assertEquals(34343434L, MathUtils.max(342L, -3244L, 435L, 0L, 34343434L));

        assertEquals(32454f, MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
        assertEquals(Float.NaN, MathUtils.max(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN));
        assertEquals(+0.0f, MathUtils.max(+0.0f, -0.0f, -0.0f, +0.0f));

        assertEquals(32454.0, MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45));
        assertEquals(Double.NaN, MathUtils.max(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN));
        assertEquals(+0.0, MathUtils.max(+0.0, -0.0, -0.0, +0.0));

        try {
            assertEquals(1212, MathUtils.max());
        } catch (IllegalArgumentException e) {
            assertEquals(true, "Can't get max value from empty array".equals(e.getMessage()));
        }
    }

    public void testMin() {
        assertEquals(-3434434, MathUtils.min(1, 0, 345, -78, -3434_434, 4325, 1, 0));

        assertEquals(-3244L, MathUtils.min(342L, -3244L, 435L, 0L, 34343434L));

        assertEquals(-4535.45f, MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f));
        assertEquals(Float.NaN, MathUtils.min(32454f, 34.5435f, +0.0f, -0.0f, -4535.45f, Float.NaN));
        assertEquals(-0.0f, MathUtils.min(+0.0f, -0.0f, -0.0f, +0.0f));

        assertEquals(-4535.45, MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45));
        assertEquals(Double.NaN, MathUtils.min(32454.0, 34.5435, +0.0, -0.0, -4535.45, Double.NaN));
        assertEquals(-0.0, MathUtils.min(+0.0, -0.0, -0.0, +0.0));

        try {
            assertEquals(1212, MathUtils.min());
        } catch (IllegalArgumentException e) {
            assertEquals(true, "Can't get min value from empty array".equals(e.getMessage()));
        }
    }
}
