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
import static org.junit.Assert.fail;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.hippo.yorozuya.android.test.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ResourcesUtilsTest {

  @Before
  public void before() {
    Context app = InstrumentationRegistry.getTargetContext();
    app.setTheme(R.style.AppTheme);
  }

  @Test
  public void testGetFloat() {
    Context app = InstrumentationRegistry.getTargetContext();
    Resources resources = app.getResources();

    assertEqualsFloat(5.6f, ResourcesUtils.getFloat(resources, R.integer.float1));
    try {
      assertEqualsFloat(5.6f, ResourcesUtils.getFloat(resources, R.integer.integer1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }
}
