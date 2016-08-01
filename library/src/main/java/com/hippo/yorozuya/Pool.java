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

import android.support.annotation.CheckResult;

/**
 * Size-fixed pool
 */
public class Pool<T> {

    private final T[] mArray;
    private final int mMaxSize;
    private int mSize;

    /**
     * Throw IllegalArgumentException if size <= 0
     */
    @SuppressWarnings("unchecked")
    public Pool(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Pool size must > 0, it is " + size);
        }
        mArray = (T[]) new Object[size];
        mMaxSize = size;
        mSize = 0;
    }

    /**
     * Return max size of the pool
     */
    @CheckResult
    public int maxSize() {
        return mMaxSize;
    }

    /**
     * Return current size of the pool
     */
    @CheckResult
    public int size() {
        return mSize;
    }

    /**
     * Push the object in the Pool, {@code null} will be ignore.
     * If the pool is full, {@link #onOverflow(Object)} will be called.
     */
    public void push(T t) {
        if (t == null) {
            return;
        }
        if (mSize < mMaxSize) {
            mArray[mSize++] = t;
        } else {
            onOverflow(t);
        }
    }

    /**
     * Pop a object in the pool. If the pool is empty, return null.
     */
    public T pop() {
        if (mSize > 0) {
            T t = mArray[--mSize];
            mArray[mSize] = null;
            return t;
        } else {
            return null;
        }
    }

    /**
     * Called when the pool is full and still push.
     */
    public void onOverflow(T t) {}
}
