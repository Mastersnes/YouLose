package com.bebel.youlose.components.interfaces.event;

/**
 * Action à effectuer lors d'un evenement
 */
public interface ClickEvent {
    void run(final float x, final float y, final int pointer, final int button);
}