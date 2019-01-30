package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Represente une entitée pouvant etre actionnée
 */
public interface Actionnable {
    default boolean addActions(Action... actions) {
        addAction(Actions.sequence(actions));
        return true;
    }

    void addAction (final Action action);
}