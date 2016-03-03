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

public final class LongIdGenerator {

    public static final long INVALID_ID = -1L;

    private long mId;

    public LongIdGenerator() {
        this(0L);
    }

    public LongIdGenerator(long init) {
        setNextId(init);
    }

    private void checkInValidId(long id) {
        if (INVALID_ID == id) {
            throw new IllegalStateException("Can't set INVALID_ID");
        }
    }

    public synchronized long nextId() {
        long id = mId++;
        if (id == INVALID_ID) {
            return nextId();
        } else {
            return id;
        }
    }

    public synchronized void setNextId(long id) {
        checkInValidId(id);
        mId = id;
    }
}