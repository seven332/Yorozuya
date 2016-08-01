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

import java.util.concurrent.atomic.AtomicInteger;

public final class IntIdGenerator {

    public static final int INVALID_ID = -1;

    private final AtomicInteger mId = new AtomicInteger();

    public IntIdGenerator() {}

    public IntIdGenerator(int init) {
        setNextId(init);
    }

    /**
     * Return next id, can not be {@link #INVALID_ID}.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public int nextId() {
        int id;
        while ((id = mId.getAndIncrement()) == INVALID_ID);
        return id;
    }

    /**
     * Set next id. Throw IllegalStateException
     * if id is {@link #INVALID_ID}.
     */
    public void setNextId(int id) {
        checkInValidId(id);
        mId.set(id);
    }

    private void checkInValidId(int id) {
        if (INVALID_ID == id) {
            throw new IllegalStateException("Can't set INVALID_ID");
        }
    }
}
