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

import android.graphics.Color;

public final class ColorUtils {
    private ColorUtils() {}

    /**
     * Check is it the color opaque
     *
     * @param color the color
     * @return true if it is opaque
     */
    public static boolean isOpaque(int color) {
        return color >>> 24 == 0xFF;
    }

    /**
     * Get different depth color base on the color
     * <p>
     * if {@code absolute} is true, {@code dark} is in [0.0f, 1.0f]<br>
     * 0.0f    <----> 1.0f<br>
     * darkest <----> lightest<br>
     * if {@code absolute} is false, {@code dark} is in [-1.0f, 1.0f]<br>
     * -1.0f   <---->    0.0f     <----> 1.0f<br>
     * darkest <----> color depth <----> lightest
     */
    public static int depthColor(int color, float dark, boolean absolute) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        if (absolute) {
            hsv[2] = dark;
        } else if (dark >= 0.0f) {
            hsv[2] = MathUtils.lerp(hsv[2], 1.0f, dark);
        } else {
            hsv[2] = MathUtils.lerp(hsv[2], 0.0f, -dark);
        }
        hsv[2] = MathUtils.clamp(hsv[2], 0.0f, 1.0f);
        return Color.HSVToColor(hsv);
    }
}
