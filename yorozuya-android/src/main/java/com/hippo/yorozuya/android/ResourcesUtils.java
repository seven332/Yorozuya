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

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public final class ResourcesUtils {
  private ResourcesUtils() {}

  /** Lock object used to protect access to {@link #tmpValue}. */
  private static final Object tmpValueLock = new Object();
  /** Single-item pool used to minimize TypedValue allocations. */
  private static TypedValue tmpValue = new TypedValue();

  /**
   * Returns a TypedValue suitable for temporary use. The obtained TypedValue
   * should be released using {@link #releaseTempTypedValue(TypedValue)}.
   *
   * @return a typed value suitable for temporary use
   */
  private static TypedValue obtainTempTypedValue() {
    TypedValue tmpValue = null;
    synchronized (tmpValueLock) {
      if (ResourcesUtils.tmpValue != null) {
        tmpValue = ResourcesUtils.tmpValue;
        ResourcesUtils.tmpValue = null;
      }
    }
    if (tmpValue == null) {
      return new TypedValue();
    }
    return tmpValue;
  }

  /**
   * Returns a TypedValue to the pool. After calling this method, the
   * specified TypedValue should no longer be accessed.
   *
   * @param value the typed value to return to the pool
   */
  private static void releaseTempTypedValue(TypedValue value) {
    synchronized (tmpValueLock) {
      if (tmpValue == null) {
        tmpValue = value;
      }
    }
  }

  /**
   * Retrieve a floating-point value for a particular resource ID.
   *
   * @param resources the resources to retrieve from
   * @param id the desired resource identifier, as generated by the aapt tool
   * @return the floating-point value contained in the resource
   * @throws Resources.NotFoundException if the given ID does
   *         not exist or is not a floating-point value
   */
  public static float getFloat(@NonNull Resources resources, int id)
      throws Resources.NotFoundException {
    final TypedValue value = obtainTempTypedValue();
    try {
      resources.getValue(id, value, true);
      if (value.type == TypedValue.TYPE_FLOAT) {
        return value.getFloat();
      }
      throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id)
          + " type #0x" + Integer.toHexString(value.type) + " is not valid");
    } finally {
      releaseTempTypedValue(value);
    }
  }
}
