package com.bebel.youlose.components.abstrait.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/**
 * RunnableAction bloquante
 */
public class FinishRunnableAction extends Action {
    private FinishRunnable runnable;
    private boolean ran;

    public boolean act (final float delta) {
        if (!ran) {
            ran = true;
            run();
        }
        return runnable.isFinish();
    }

    /** Called to run the runnable. */
    public void run () {
        Pool pool = getPool();
        setPool(null); // Ensure this action can't be returned to the pool inside the runnable.
        try {
            runnable.run();
        } finally {
            setPool(pool);
        }
    }

    public void restart () {
        ran = false;
    }

    public void reset () {
        super.reset();
        runnable = null;
    }

    public void setRunnable (final FinishRunnable runnable) {
        this.runnable = runnable;
    }
}