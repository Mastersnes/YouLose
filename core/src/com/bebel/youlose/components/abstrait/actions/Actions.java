package com.bebel.youlose.components.abstrait.actions;

/**
 * Extention des actions badlogic
 */
public class Actions extends com.badlogic.gdx.scenes.scene2d.actions.Actions {
    public static FinishRunnableAction finishRun(FinishRunnable runnable) {
        FinishRunnableAction action = action(FinishRunnableAction.class);
        action.setRunnable(runnable);
        return action;
    }
}