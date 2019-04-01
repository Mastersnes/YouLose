package com.bebel.youlose.utils;

import com.bebel.youlose.components.runnable.OneTimeRunnable;

/**
 * Outils pour les callback
 */
public class RunnableUtils {
    public static synchronized OneTimeRunnable oneTime(final Runnable runnable) {
        final OneTimeRunnable oneTime = new OneTimeRunnable();
        oneTime.setRunnable(runnable);
        return oneTime;
    }
}
