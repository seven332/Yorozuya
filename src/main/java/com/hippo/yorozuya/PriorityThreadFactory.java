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

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * A thread factory that creates threads with a given thread priority.
 */
public class PriorityThreadFactory implements ThreadFactory {

    private final int mPriority;
    private final IdGenerator mIdGenerator = new IdGenerator();
    private final String mName;

    public PriorityThreadFactory(String name, int priority) {
        mName = name;
        mPriority = priority;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        return new Thread(r, mName + '-' + mIdGenerator.nextId()) {
            @Override
            public void run() {
                Process.setThreadPriority(mPriority);
                super.run();
            }
        };
    }
}
