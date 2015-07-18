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
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class Say {

    private static boolean sInitSayFile;
    private static File sSayFile;
    private static Executor sSayFileExecutor;
    private static DateFormat sDataFormat;

    private static class SayFileTask implements Runnable {

        private long mTime;
        private String mTag;
        private String mMsg;
        private Throwable mTr;

        public SayFileTask(long time, String tag, String msg, Throwable tr) {
            mTime = time;
            mTag = tag;
            mMsg = msg;
            mTr = tr;
        }

        @Override
        public void run() {
            FileWriter fw = null;
            try {
                fw = new FileWriter(sSayFile, true);
                fw.append(sDataFormat.format(new Date(mTime))).append("  ")
                        .append(mTag).append("\n").append(mMsg).append("\n");
                if (mTr != null) {
                    fw.append(Log.getStackTraceString(mTr)).append("\n");
                }
                fw.flush();
            } catch (IOException e) {
                IOUtils.closeQuietly(fw);
            }
        }
    }

    public static void initSayFile(@NonNull File sayFile) {
        if (!sInitSayFile) {
            sInitSayFile = true;
            sSayFile = sayFile;
            sSayFileExecutor = new SerialThreadExecutor(10000,
                    new LinkedBlockingQueue<Runnable>(),
                    new PriorityThreadFactory("Say", Process.THREAD_PRIORITY_BACKGROUND));
            sDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        }
    }

    private static void addSayFileTask(long time, String tag, String msg, Throwable tr) {
        if (sInitSayFile) {
            sSayFileExecutor.execute(new SayFileTask(time, tag, msg, tr));
        }
    }

    /**
     * Save msg to file.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void f(String tag, String msg) {
        addSayFileTask(System.currentTimeMillis(), tag, msg, null);
    }

    /**
     * Save msg to file.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static void f(String tag, String msg, Throwable tr) {
        addSayFileTask(System.currentTimeMillis(), tag, msg, tr);
    }

    private static String noNull(String str) {
        return str == null ? "null" : str;
    }

    /**
     * Send a {@link Log#VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String msg) {
        return Log.v(tag, noNull(msg));
    }

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
        return Log.v(tag, noNull(msg), tr);
    }

    /**
     * Send a {@link Log#DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
        return Log.d(tag, noNull(msg));
    }

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
        return Log.d(tag, noNull(msg), tr);
    }

    /**
     * Send an {@link Log#INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
        return Log.i(tag, noNull(msg));
    }

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
        return Log.i(tag, noNull(msg), tr);
    }

    /**
     * Send a {@link Log#WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
        return Log.w(tag, noNull(msg));
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
        return Log.w(tag, noNull(msg), tr);
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
        return Log.w(tag, tr);
    }

    /**
     * Send an {@link Log#ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
        return Log.e(tag, noNull(msg));
    }

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
        return Log.e(tag, noNull(msg), tr);
    }
}
