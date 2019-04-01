package com.bebel.youlose.components.runnable;

/**
 * Runnable ne pouvant etre appelé qu'une fois
 */
public class OneTimeRunnable implements Runnable {
    protected boolean alreadyCall = false;
    protected Runnable runnable;

    @Override
    public void run() {
        if (runnable != null && !alreadyCall) {
            runnable.run();
        }
    }

    public void setRunnable(final Runnable runnable) {
        this.runnable = runnable;
        alreadyCall = false;
    }

    public void restart() {
        alreadyCall = false;
    }
}