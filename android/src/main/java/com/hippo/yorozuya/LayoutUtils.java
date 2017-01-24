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
 * Created by Hippo on 1/23/2017.
 */

import android.content.Context;

public final class LayoutUtils {
  private LayoutUtils() {}

  /**
   * Converts dp value to pix value.
   *
   * @param context the context
   * @param dp the dp value to convert
   * @return the pix value
   * @throws NullPointerException if {@code context == null}
   */
  public static int dp2pix(Context context, float dp) throws NullPointerException {
    if (context == null) throw new NullPointerException("context == null");
    return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
  }

  /**
   * Converts pix value to dp value.
   *
   * @param context the context
   * @param pix the pix value to convert
   * @return the dp value
   * @throws NullPointerException if {@code context == null}
   */
  public static float pix2dp(Context context, int pix) throws NullPointerException {
    if (context == null) throw new NullPointerException("context == null");
    return pix / context.getResources().getDisplayMetrics().density;
  }

  /**
   * Converts sp value to pix value.
   *
   * @param context the context
   * @param sp the sp value to convert
   * @return the pix value
   * @throws NullPointerException if {@code context == null}
   */
  public static int sp2pix(Context context, float sp) throws NullPointerException {
    if (context == null) throw new NullPointerException("context == null");
    return (int) (sp * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
  }

  /**
   * Converts pix value to sp value.
   *
   * @param context the context
   * @param pix the pix value to convert
   * @return the sp value
   * @throws NullPointerException if {@code context == null}
   */
  public static float pix2sp(Context context, float pix) throws NullPointerException {
    if (context == null) throw new NullPointerException("context == null");
    return pix / context.getResources().getDisplayMetrics().scaledDensity;
  }
}
