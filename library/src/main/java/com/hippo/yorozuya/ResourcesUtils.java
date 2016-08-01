/*
 * Copyright (C) 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.yorozuya;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class ResourcesUtils {
    private ResourcesUtils() {}

    private static final Object mAccessLock = new Object();
    private static final TypedValue mTmpValue = new TypedValue();

    /**
     * Return an integer associated with a particular resource ID.
     */
    @CheckResult
    public static float getFloat(Resources resources, int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            resources.getValue(id, value, true);
            if (value.type == TypedValue.TYPE_FLOAT) {
                return value.getFloat();
            }
            throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    private static void getAttrValue(Context context, int attrId, TypedValue value) {
        context.getTheme().resolveAttribute(attrId, value, true);
    }

    /**
     * Returns a boolean associated with a particular attribute in the Theme.
     */
    @CheckResult
    public static boolean getAttrBoolean(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type >= TypedValue.TYPE_FIRST_INT
                    && value.type <= TypedValue.TYPE_LAST_INT) {
                return value.data != 0;
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Returns a integer associated with a particular attribute in the Theme.
     */
    @CheckResult
    public static int getAttrInteger(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type >= TypedValue.TYPE_FIRST_INT
                    && value.type <= TypedValue.TYPE_LAST_INT) {
                return value.data;
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Returns a float associated with a particular attribute in the Theme.
     */
    @CheckResult
    public static float getAttrFloat(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type == TypedValue.TYPE_FLOAT) {
                return value.getFloat();
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Retrieve a dimensional for a particular attribute in the Theme.  Unit
     * conversions are based on the current {@link DisplayMetrics} associated
     * with the resources.
     */
    @CheckResult
    public static float getAttrDimension(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type == TypedValue.TYPE_DIMENSION) {
                return TypedValue.complexToDimension(
                        value.data, context.getResources().getDisplayMetrics());
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Retrieve a dimensional for a particular attribute in the Theme for use
     * as an offset in raw pixels.  This is the same as
     * {@link #getAttrDimension}, except the returned value is converted to
     * integer pixels for you.  An offset conversion involves simply
     * truncating the base value to an integer.
     */
    @CheckResult
    public static int getAttrDimensionPixelOffset(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type == TypedValue.TYPE_DIMENSION) {
                return TypedValue.complexToDimensionPixelOffset(
                        value.data, context.getResources().getDisplayMetrics());
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Retrieve a dimensional for a particular attribute in the Theme for use
     * as a size in raw pixels.  This is the same as
     * {@link #getAttrDimension}, except the returned value is converted to
     * integer pixels for use as a size.  A size conversion involves
     * rounding the base value, and ensuring that a non-zero base value
     * is at least one pixel in size.
     */
    @CheckResult
    public static int getAttrDimensionPixelSize(Context context, @AttrRes int id)
            throws Resources.NotFoundException {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, id, value);
            if (value.type == TypedValue.TYPE_DIMENSION) {
                return TypedValue.complexToDimensionPixelSize(
                        value.data, context.getResources().getDisplayMetrics());
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(id)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }

    /**
     * Returns a color integer associated with a particular attribute in the Theme
     */
    @ColorInt
    @CheckResult
    public static int getAttrColor(Context context, @AttrRes int attrId) {
        TypedValue value = mTmpValue;
        synchronized (mAccessLock) {
            getAttrValue(context, attrId, value);
            if (value.type >= TypedValue.TYPE_FIRST_INT
                    && value.type <= TypedValue.TYPE_LAST_INT) {
                return value.data;
            }
            throw new Resources.NotFoundException("Attribute ID #0x" + Integer.toHexString(attrId)
                    + " type #0x" + Integer.toHexString(value.type) + " is not valid");
        }
    }
}
