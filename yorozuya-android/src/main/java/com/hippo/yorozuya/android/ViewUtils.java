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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public final class ViewUtils {
  private ViewUtils() {}

  private static final String LOG_TAG = ViewUtils.class.getSimpleName();

  /**
   * Max size allowed for view.
   */
  public static final int MAX_SIZE = Integer.MAX_VALUE & ~(0x3 << 30);

  /** Lock object used to protect access to {@link #tmpMatrix}. */
  private static final Object tmpMatrixLock = new Object();
  /** Single-item pool used to minimize Matrix allocations. */
  private static Matrix tmpMatrix = new Matrix();

  /**
   * Finds a ancestor view with the given id recursively.
   *
   * @param view the view to start with
   * @param id the desired view identifier
   * @return the desired view or {@code null}
   */
  public static View getAncestor(View view, int id) {
    if (view == null) {
      Log.w(LOG_TAG, "getAncestor: view == null");
      return null;
    }

    if (view.getId() == id) {
      return view;
    }

    ViewParent viewParent = view.getParent();
    while (viewParent instanceof View) {
      view = (View) viewParent;
      if (view.getId() == id) {
        return view;
      }
      viewParent = view.getParent();
    }

    return null;
  }

  /**
   * Get view location in its ancestor.
   * If can't find the ancestor, get its location in root view.
   *
   * @param view the view to start with
   * @param ancestorId the ancestor id
   * @param location the container of result
   * @return {@code true} if find the ancestor
   * @throws IllegalArgumentException if location is not an array of two integers
   * @throws NullPointerException if {@code view == null}
   */
  public static boolean getLocationInAncestor(View view, int ancestorId, int[] location)
      throws IllegalArgumentException, NullPointerException {
    if (view == null) {
      throw new NullPointerException("view == null");
    }
    if (location == null || location.length < 2) {
      throw new IllegalArgumentException(
          "location must be an array of two integers");
    }

    boolean result = false;
    float[] position = new float[2];

    view.getMatrix().mapPoints(position);

    position[0] += view.getLeft();
    position[1] += view.getTop();

    ViewParent viewParent = view.getParent();
    while (viewParent instanceof View) {
      view = (View) viewParent;
      if (view.getId() == ancestorId) {
        result = true;
        break;
      }

      position[0] -= view.getScrollX();
      position[1] -= view.getScrollY();

      view.getMatrix().mapPoints(position);

      position[0] += view.getLeft();
      position[1] += view.getTop();

      viewParent = view.getParent();
    }

    location[0] = (int) (position[0] + 0.5f);
    location[1] = (int) (position[1] + 0.5f);

    return result;
  }

  /**
   * Get view location in its ancestor.
   * If can't find the ancestor, get its location in root view.
   *
   * @param view the view to start with
   * @param ancestor the ancestor
   * @param location the container of result
   * @return {@code true} if find the ancestor
   * @throws IllegalArgumentException if location is not an array of two integers
   * @throws NullPointerException if {@code view == null} or {@code ancestor == null}
   */
  public static boolean getLocationInAncestor(View view, View ancestor, int[] location)
      throws IllegalArgumentException, NullPointerException {
    if (view == null) {
      throw new NullPointerException("view == null");
    }
    if (location == null || location.length < 2) {
      throw new IllegalArgumentException(
          "location must be an array of two integers");
    }

    boolean result = false;
    float[] position = new float[2];

    view.getMatrix().mapPoints(position);

    position[0] += view.getLeft();
    position[1] += view.getTop();

    ViewParent viewParent = view.getParent();
    while (viewParent instanceof View) {
      view = (View) viewParent;
      if (view == ancestor) {
        result = true;
        break;
      }

      position[0] -= view.getScrollX();
      position[1] -= view.getScrollY();

      view.getMatrix().mapPoints(position);

      position[0] += view.getLeft();
      position[1] += view.getTop();

      viewParent = view.getParent();
    }

    location[0] = (int) (position[0] + 0.5f);
    location[1] = (int) (position[1] + 0.5f);

    return result;
  }

  /**
   * Measures a view.
   * <p>
   * It is useful to get its size before layout.
   * {@link View#getMeasuredWidth()} and {@link View#getMeasuredHeight()}
   * will return valid values.
   *
   * @param v the view to measure
   * @param width the width size, {@link ViewGroup.LayoutParams#WRAP_CONTENT} or a positive integer
   * @param height the height size, {@link ViewGroup.LayoutParams#WRAP_CONTENT} or a positive integer
   */
  public static void measure(View v, int width, int height) {
    if (v == null) {
      Log.w(LOG_TAG, "measure: v == null");
      return;
    }
    int widthMeasureSpec;
    int heightMeasureSpec;
    if (width == ViewGroup.LayoutParams.WRAP_CONTENT) {
      widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    } else {
      widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(width, 0), View.MeasureSpec.EXACTLY);
    }
    if (height == ViewGroup.LayoutParams.WRAP_CONTENT) {
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    } else {
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(height, 0), View.MeasureSpec.EXACTLY);
    }
    v.measure(widthMeasureSpec, heightMeasureSpec);
  }

  /**
   * Measures and layouts a view.
   * <p>
   * {@link View#getWidth()} and {@link View#getHandler()}
   * will return valid values.
   *
   * @param v the view to layout
   * @param width the width size, {@link ViewGroup.LayoutParams#WRAP_CONTENT} or a positive integer
   * @param height the height size, {@link ViewGroup.LayoutParams#WRAP_CONTENT} or a positive integer
   */
  public static void layout(View v, int width, int height) {
    if (v == null) {
      Log.w(LOG_TAG, "layout: v == null");
      return;
    }
    measure(v, width, height);
    v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
  }

  /**
   * Utility to return a default size. Uses the supplied size if the
   * MeasureSpec imposed no constraints. Will get suitable if allowed
   * by the MeasureSpec.
   *
   * @param size Default size for this view
   * @param spec Constraints imposed by the parent
   * @return The size this view should be.
   */
  public static int getSuitableSize(int size, int spec) {
    int result = size;
    int specMode = View.MeasureSpec.getMode(spec);
    int specSize = View.MeasureSpec.getSize(spec);

    switch (specMode) {
      case View.MeasureSpec.UNSPECIFIED:
        result = size;
        break;
      case View.MeasureSpec.EXACTLY:
        result = specSize;
        break;
      case View.MeasureSpec.AT_MOST:
        return size == 0 ? specSize : Math.min(size, specSize);
      default:
        Log.d(LOG_TAG, "getSuitableSize: bad spec mode: " + specMode);
        break;
    }
    return result;
  }

  /**
   * Remove view from its parent.
   *
   * @param v the view to remove
   */
  public static void removeFromParent(View v) {
    if (v == null) {
      Log.w(LOG_TAG, "removeFromParent: v == null");
      return;
    }
    ViewParent vp = v.getParent();
    if (vp instanceof ViewGroup) {
      ((ViewGroup) vp).removeView(v);
    }
  }

  /**
   * Get index of the view in its parent.
   *
   * @param v the view
   * @return the index in its parent, or {@code -1} if not found
   */
  public static int indexInParent(View v) {
    if (v == null) {
      Log.w(LOG_TAG, "indexInParent: v == null");
      return -1;
    }

    ViewParent vp = v.getParent();
    if (vp == null) {
      Log.w(LOG_TAG, "indexInParent: orphan");
      return -1;
    }
    if (! (vp instanceof ViewGroup)) {
      Log.w(LOG_TAG, "indexInParent: parent isn't a ViewGroup");
      return -1;
    }

    ViewGroup vg = (ViewGroup) vp;
    return vg.indexOfChild(v);
  }

  /**
   * Returns a bitmap showing a screenshot of the view passed in.
   * The view must be measured and layout.
   *
   * @param v The view to get screenshot
   * @return The screenshot, or {@code null} if can't get it
   * @throws OutOfMemoryError if the view is too large
   * @throws IllegalArgumentException if the view is too large
   */
  public static Bitmap screenshot(View v)
      throws NullPointerException, OutOfMemoryError {
    if (v == null) {
      Log.w(LOG_TAG, "screenshot: v == null");
      return null;
    }
    int width = v.getWidth();
    int height = v.getHeight();
    if (width <= 0 || height <= 0) {
      Log.w(LOG_TAG, "screenshot: width <= 0 || height <= 0");
      return null;
    }

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.translate(-v.getScrollX(), -v.getScrollY());
    v.draw(canvas);
    return bitmap;
  }

  /**
   * Returns a Matrix suitable for temporary use. The obtained Matrix
   * should be released using {@link #releaseTempMatrix(Matrix)}.
   *
   * @return a matrix suitable for temporary use
   */
  private static Matrix obtainTempMatrix() {
    Matrix tmpMatrix = null;
    synchronized (tmpMatrixLock) {
      if (ViewUtils.tmpMatrix != null) {
        tmpMatrix = ViewUtils.tmpMatrix;
        ViewUtils.tmpMatrix = null;
      }
    }
    if (tmpMatrix == null) {
      return new Matrix();
    }
    return tmpMatrix;
  }

  /**
   * Returns a Matrix to the pool. After calling this method, the
   * specified Matrix should no longer be accessed.
   *
   * @param value the typed value to return to the pool
   */
  private static void releaseTempMatrix(Matrix value) {
    synchronized (tmpMatrixLock) {
      if (tmpMatrix == null) {
        tmpMatrix = value;
      }
    }
  }

  /**
   * Transforms the specified point to the coordinate space of a child view.
   *
   * @param point the point to transform
   * @param parent the parent view
   * @param child the child view
   * @throws IllegalArgumentException if point is not an array of two integers
   * @throws NullPointerException if {@code parent == null} or {@code child == null}
   */
  public static void transformPointToViewLocal(float[] point, View parent, View child)
      throws IllegalArgumentException, NullPointerException {
    if (point == null || point.length < 2) {
      throw new IllegalArgumentException("point must be an array of two integers");
    }
    if (parent == null || child == null) {
      throw new NullPointerException("parent == null || child == null");
    }
    point[0] += parent.getScrollX() - child.getLeft();
    point[1] += parent.getScrollY() - child.getTop();

    final Matrix value = obtainTempMatrix();
    try {
      if (child.getMatrix().invert(value)) {
        value.mapPoints(point);
      }
    } finally {
      releaseTempMatrix(value);
    }
  }

  /**
   * Determines whether the given point, in local coordinates is inside the view.
   *
   * @param view the view
   * @param localX x of the point
   * @param localY x of the point
   * @return {@code true} if the point is in the view and view is not {@code null}
   */
  public static boolean pointInView(View view, float localX, float localY) {
    return pointInView(view, localX, localY, 0);
  }

  /**
   * Utility method to determine whether the given point, in local coordinates,
   * is inside the view, where the area of the view is expanded by the slop factor.
   * This method is called while processing touch-move events to determine if the event
   * is still within the view.
   *
   * @param view the view
   * @param localX x of the point
   * @param localY x of the point
   * @param slop the slop factor
   * @return {@code true} if the point is in the view and view is not {@code null}
   */
  public static boolean pointInView(View view, float localX, float localY, float slop) {
    if (view == null) {
      Log.w(LOG_TAG, "pointInView: view == null");
      return false;
    }
    return localX >= -slop
        && localY >= -slop
        && localX < (view.getWidth() + slop)
        && localY < (view.getHeight() + slop);
  }

  /**
   * Offset this view translationX.
   *
   * @param view the view
   * @param offset the translationX offset
   */
  public static void translationXBy(View view, float offset) {
    if (view == null) {
      Log.w(LOG_TAG, "translationXBy: view == null");
      return;
    }
    view.setTranslationX(view.getTranslationX() + offset);
  }

  /**
   * Offset this view translationY.
   *
   * @param view the view
   * @param offset the translationY offset
   */
  public static void translationYBy(View view, float offset) {
    if (view == null) {
      Log.w(LOG_TAG, "translationYBy: view == null");
      return;
    }
    view.setTranslationY(view.getTranslationY() + offset);
  }
}
