package com.bebel.youlose.components.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;
import com.bebel.youlose.components.refound.actors.AnimatedActor;

/**
 * Action lancant une animation et attendant sa fin
 */
public class FinishAnimAction extends Action {
    private AnimatedActor animation;
    private boolean flipH;
    private boolean flipV;
    private boolean ran;

    public boolean act (final float delta) {
        if (animation == null) return true;
        if (!ran) {
            ran = true;
            run();
        }
        return animation.isFinish();
    }

    /** Called to run the runnable. */
    public void run () {
        Pool pool = getPool();
        setPool(null); // Ensure this action can't be returned to the pool inside the runnable.
        try {
            animation.play(flipH, flipV);
        } finally {
            setPool(pool);
        }
    }

    public void restart () {
        if (animation == null) return;
        ran = false;
        flipH = false; flipV = false;
        animation.stop();
    }

    public void reset () {
        super.reset();
        flipH = false; flipV = false;
        animation = null;
    }

    public void init (final AnimatedActor animation, final boolean flipH, final boolean flipV) {
        this.animation = animation;
        this.flipH = flipH;
        this.flipV = flipV;
    }
}