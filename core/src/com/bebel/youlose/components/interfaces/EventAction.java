package com.bebel.youlose.components.interfaces;

/**
 * Action à effectuer lors d'un evenement
 */
public interface EventAction {
    void run(final float x, final float y, final int pointer, final int button);
}