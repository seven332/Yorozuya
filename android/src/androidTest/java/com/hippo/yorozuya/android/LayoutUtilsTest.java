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
 * Created by Hippo on 1/23/2017.
 */

import static com.hippo.yorozuya.android.Utils.assertEqualsFloat;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LayoutUtilsTest {

  @Test
  public void testDpPix() {
    Context app = InstrumentationRegistry.getTargetContext();
    float density = app.getResources().getDisplayMetrics().density;
    assertEquals((int) (10 * density + 0.5f), LayoutUtils.dp2pix(app, 10));
    assertEquals(0, LayoutUtils.dp2pix(app, 0));

    assertEqualsFloat(10 / density, LayoutUtils.pix2dp(app, 10));
    assertEqualsFloat(0, LayoutUtils.pix2dp(app, 0));
  }

  @Test
  public void testSpPix() {
    Context app = InstrumentationRegistry.getTargetContext();
    float scaledDensity = app.getResources().getDisplayMetrics().scaledDensity;
    assertEquals((int) (10 * scaledDensity + 0.5f), LayoutUtils.sp2pix(app, 10));
    assertEquals(0, LayoutUtils.sp2pix(app, 0));

    assertEqualsFloat(10 / scaledDensity, LayoutUtils.pix2sp(app, 10));
    assertEqualsFloat(0, LayoutUtils.pix2sp(app, 0));
  }
}
