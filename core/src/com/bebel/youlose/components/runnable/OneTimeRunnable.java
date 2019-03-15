package com.bebel.youlose.components.runnable;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Runnable ne pouvqnt etre qppelé qu'une fois
 */
public class OneTimeRunnable implements Runnable, Pool.Poolable {
    protected boolean alreadyCall = false;
    protected Runnable runnable;

    @Override
    public void run() {
        if (runnable != null && !alreadyCall) {
            runnable.run();
            Pools.free(this);
        }
    }

    public void setRunnable(final Runnable runnable) {
        this.runnable = runnable;
        alreadyCall = false;
    }

    @Override
    public void reset() {
        runnable = null;
        alreadyCall = false;
    }
}