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

import android.support.annotation.NonNull;
import android.util.SparseArray;

public class SafeSparseArray<E> extends SparseArray<E> {

    /**
     * {@inheritDoc}
     */
    public SafeSparseArray() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public SafeSparseArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized E get(int key) {
        return super.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized E get(int key, E valueIfKeyNotFound) {
        return super.get(key, valueIfKeyNotFound);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void delete(int key) {
        super.delete(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void remove(int key) {
        super.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void removeAt(int index) {
        super.removeAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void removeAtRange(int index, int size) {
        super.removeAtRange(index, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void put(int key, E value) {
        super.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int size() {
        return super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int keyAt(int index) {
        return super.keyAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public synchronized E valueAt(int index) {
        return super.valueAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setValueAt(int index, E value) {
        super.setValueAt(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int indexOfKey(int key) {
        return super.indexOfKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int indexOfValue(E value) {
        return super.indexOfValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void clear() {
        super.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void append(int key, E value) {
        super.append(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String toString() {
        return super.toString();
    }
}
