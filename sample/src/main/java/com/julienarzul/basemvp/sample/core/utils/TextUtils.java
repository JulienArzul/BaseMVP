package com.julienarzul.basemvp.sample.core.utils;

import android.support.annotation.Nullable;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TextUtils {

    private TextUtils() {
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     *
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
