package com.bebel.youlose.utils;

import com.badlogic.gdx.utils.Pools;
import com.bebel.youlose.components.runnable.OneTimeRunnable;

/**
 * Outils pour les callback
 */
public class RunnableUtils {
    public static synchronized OneTimeRunnable oneTime(final Runnable runnable) {
        final OneTimeRunnable oneTime = Pools.obtain(OneTimeRunnable.class);
        oneTime.setRunnable(runnable);
        return oneTime;
    }
}
