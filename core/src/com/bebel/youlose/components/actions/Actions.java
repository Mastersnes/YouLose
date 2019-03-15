package com.bebel.youlose.components.actions;

import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.components.refound.actors.AnimatedActor;

/**
 * Extention des actions badlogic
 */
public class Actions extends com.badlogic.gdx.scenes.scene2d.actions.Actions {
    public static FinishRunnableAction emptyRun() {
        FinishRunnableAction action = action(FinishRunnableAction.class);
        action.setRunnable(new FinishRunnable() {
            @Override
            public void run() {
                finish = true;
            }
        });
        return action;
    }

    public static FinishRunnableAction finishRun(final FinishRunnable runnable) {
        FinishRunnableAction action = action(FinishRunnableAction.class);
        action.setRunnable(runnable);
        return action;
    }

    public static FinishAnimAction emptyAnim() {
        FinishAnimAction action = action(FinishAnimAction.class);
        return action;
    }
    public static FinishAnimAction finishAnim(final AnimatedActor animation, final boolean flipH, final boolean flipV) {
        FinishAnimAction action = action(FinishAnimAction.class);
        action.init(animation, flipH, flipV);
        return action;
    }
}