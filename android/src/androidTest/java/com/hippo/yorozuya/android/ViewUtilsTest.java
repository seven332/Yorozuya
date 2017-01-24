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

import static com.hippo.yorozuya.android.Utils.assertEqualsFloat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hippo.yorozuya.android.test.R;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewUtilsTest {

  private Context app;

  @Before
  public void before() {
    app = InstrumentationRegistry.getTargetContext();
  }

  @Test
  public void testMaxSize() {
    assertEquals(0x3fffffff, ViewUtils.MAX_SIZE);
  }

  @Test
  public void testGetAncestor() {
    LayoutInflater inflater = LayoutInflater.from(app);
    View view_1_1 = inflater.inflate(R.layout.test, null);

    assertEquals(view_1_1, ViewUtils.getAncestor(view_1_1, R.id.view_1_1));

    View view_3_3 = view_1_1.findViewById(R.id.view_3_3);
    assertEquals(view_1_1, ViewUtils.getAncestor(view_3_3, R.id.view_1_1));

    View view_2_1 = view_1_1.findViewById(R.id.view_2_1);
    assertEquals(view_1_1, ViewUtils.getAncestor(view_2_1, R.id.view_1_1));
    assertEquals(null, ViewUtils.getAncestor(view_2_1, R.id.view_3_3));

    assertEquals(null, ViewUtils.getAncestor(null, R.id.view_1_1));
  }

  @Test
  public void testGetLocationInAncestor() {
    int[] location = new int[2];
    LayoutInflater inflater = LayoutInflater.from(app);
    View view_1_1 = inflater.inflate(R.layout.test, null);
    view_1_1.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    view_1_1.layout(0, 0, view_1_1.getMeasuredWidth(), view_1_1.getMeasuredHeight());

    View view_3_4 = view_1_1.findViewById(R.id.view_3_4);
    Arrays.fill(location, 0);
    ViewUtils.getLocationInAncestor(view_3_4, R.id.view_1_1, location);
    assertEquals(50, location[0]);
    assertEquals(50, location[1]);
    Arrays.fill(location, 0);
    ViewUtils.getLocationInAncestor(view_3_4, view_1_1, location);
    assertEquals(50, location[0]);
    assertEquals(50, location[1]);

    View view_2_2 = view_1_1.findViewById(R.id.view_2_2);
    Arrays.fill(location, 0);
    ViewUtils.getLocationInAncestor(view_3_4, R.id.view_2_2, location);
    assertEquals(50, location[0]);
    assertEquals(0, location[1]);
    Arrays.fill(location, 0);
    ViewUtils.getLocationInAncestor(view_3_4, view_2_2, location);
    assertEquals(50, location[0]);
    assertEquals(0, location[1]);

    view_3_4.setTranslationX(100);
    view_3_4.setTranslationY(-50);
    Arrays.fill(location, 0);
    ViewUtils.getLocationInAncestor(view_3_4, R.id.view_1_1, location);
    assertEquals(150, location[0]);
    assertEquals(0, location[1]);
    view_3_4.setTranslationX(0);
    view_3_4.setTranslationY(0);

    try {
      ViewUtils.getLocationInAncestor(null, R.id.view_1_1, location);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      ViewUtils.getLocationInAncestor(view_3_4, R.id.view_1_1, null);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      ViewUtils.getLocationInAncestor(view_3_4, R.id.view_1_1, new int[1]);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  @Test
  public void testMeasure() {
    View view1 = new View(app);

    ViewUtils.measure(view1, 100, 200);
    assertEquals(100, view1.getMeasuredWidth());
    assertEquals(200, view1.getMeasuredHeight());

    ViewUtils.measure(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    assertEquals(0, view1.getMeasuredWidth());
    assertEquals(0, view1.getMeasuredHeight());

    ViewUtils.measure(null, 100, 200);
  }

  @Test
  public void testLayout() {
    View view1 = new View(app);

    ViewUtils.layout(view1, 100, 200);
    assertEquals(100, view1.getWidth());
    assertEquals(200, view1.getHeight());

    ViewUtils.layout(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    assertEquals(0, view1.getWidth());
    assertEquals(0, view1.getHeight());

    ViewUtils.layout(null, 100, 200);
  }

  @Test
  public void testGetSuitableSize() {
    int size;
    int spec;

    size = 100;
    spec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY);
    assertEquals(200, ViewUtils.getSuitableSize(size, spec));

    size = 100;
    spec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.UNSPECIFIED);
    assertEquals(100, ViewUtils.getSuitableSize(size, spec));

    size = 100;
    spec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.AT_MOST);
    assertEquals(100, ViewUtils.getSuitableSize(size, spec));

    size = 0;
    spec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.AT_MOST);
    assertEquals(200, ViewUtils.getSuitableSize(size, spec));

    size = 300;
    spec = View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.AT_MOST);
    assertEquals(200, ViewUtils.getSuitableSize(size, spec));

    size = 35124;
    spec = View.MeasureSpec.makeMeasureSpec(200, 213);
    assertEquals(35124, ViewUtils.getSuitableSize(size, spec));
  }

  @Test
  public void testRemoveFromParent() {
    LayoutInflater inflater = LayoutInflater.from(app);
    ViewGroup view_1_1 = (ViewGroup) inflater.inflate(R.layout.test, null);
    View view_2_1 = view_1_1.findViewById(R.id.view_2_1);

    assertEquals(2, view_1_1.getChildCount());
    ViewUtils.removeFromParent(view_2_1);
    assertEquals(1, view_1_1.getChildCount());

    ViewUtils.removeFromParent(null);
    ViewUtils.removeFromParent(view_1_1);
  }

  @Test
  public void testIndexInParent() {
    LayoutInflater inflater = LayoutInflater.from(app);
    ViewGroup view_1_1 = (ViewGroup) inflater.inflate(R.layout.test, null);
    View view_2_2 = view_1_1.findViewById(R.id.view_2_2);

    assertEquals(-1, ViewUtils.indexInParent(view_1_1));
    assertEquals(1, ViewUtils.indexInParent(view_2_2));
    assertEquals(-1, ViewUtils.indexInParent(null));
  }

  @Test
  public void testScreenshot() {
    TextView view1 = new TextView(app);
    view1.setText("12345");

    ViewUtils.layout(view1, 100, 200);
    Bitmap bitmap = ViewUtils.screenshot(view1);
    assertNotNull(bitmap);
    assertEquals(100, bitmap.getWidth());
    assertEquals(200, bitmap.getHeight());
    bitmap.recycle();

    ViewUtils.layout(view1, 1000000, 1000000);
    try {
      ViewUtils.screenshot(view1);
      fail();
    } catch (OutOfMemoryError e) {
    } catch (IllegalArgumentException e) {
    }

    assertNull(ViewUtils.screenshot(null));
  }

  @Test
  public void testTransformPointToViewLocal() {
    LayoutInflater inflater = LayoutInflater.from(app);
    View view_1_1 = inflater.inflate(R.layout.test, null);
    View view_2_2 = view_1_1.findViewById(R.id.view_2_2);
    ViewUtils.layout(view_1_1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    float[] point = new float[2];

    point[0] = 10.0f;
    point[1] = 20.0f;
    ViewUtils.transformPointToViewLocal(point, view_1_1, view_2_2);
    assertEqualsFloat(10.0f, point[0]);
    assertEqualsFloat(-30.0f, point[1]);

    point[0] = 10.0f;
    point[1] = 20.0f;
    view_2_2.setTranslationX(10.0f);
    view_2_2.setTranslationY(-20.0f);
    ViewUtils.transformPointToViewLocal(point, view_1_1, view_2_2);
    assertEqualsFloat(0.0f, point[0]);
    assertEqualsFloat(-10.0f, point[1]);

    try {
      ViewUtils.transformPointToViewLocal(null, view_1_1, view_2_2);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      ViewUtils.transformPointToViewLocal(new float[1], view_1_1, view_2_2);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      ViewUtils.transformPointToViewLocal(point, null, view_2_2);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      ViewUtils.transformPointToViewLocal(point, view_1_1, null);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      ViewUtils.transformPointToViewLocal(point, null, null);
      fail();
    } catch (NullPointerException e) {
    }
  }

  @Test
  public void testPointInView() {
    LayoutInflater inflater = LayoutInflater.from(app);
    View view_1_1 = inflater.inflate(R.layout.test, null);
    View view_2_2 = view_1_1.findViewById(R.id.view_2_2);
    ViewUtils.layout(view_1_1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    assertTrue(ViewUtils.pointInView(view_2_2, 0.0f, 0.0f));
    assertFalse(ViewUtils.pointInView(view_2_2, 0.0f, 50.0f));
    assertTrue(ViewUtils.pointInView(view_2_2, 0.0f, 50.0f, 1.0f));
    assertFalse(ViewUtils.pointInView(null, 0.0f, 0.0f));
  }

  @Test
  public void testTranslationXYBy() {
    LayoutInflater inflater = LayoutInflater.from(app);
    View view_1_1 = inflater.inflate(R.layout.test, null);
    View view_2_2 = view_1_1.findViewById(R.id.view_2_2);
    ViewUtils.layout(view_1_1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    assertEqualsFloat(0.0f, view_2_2.getX());
    assertEqualsFloat(50.0f, view_2_2.getY());
    ViewUtils.translationXBy(view_2_2, 10.0f);
    assertEqualsFloat(10.0f, view_2_2.getX());
    ViewUtils.translationXBy(view_2_2, -30.0f);
    assertEqualsFloat(-20.0f, view_2_2.getX());
    ViewUtils.translationYBy(view_2_2, 10.0f);
    assertEqualsFloat(60.0f, view_2_2.getY());
    ViewUtils.translationYBy(view_2_2, -30.0f);
    assertEqualsFloat(30.0f, view_2_2.getY());
  }
}
