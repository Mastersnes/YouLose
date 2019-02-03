package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.bebel.youlose.components.refound.ButtonActor;

/**
 * Represente une entitée pouvant etre actionnée
 */
public interface Actionnable {
    default boolean addActions(final Action... actions) {
        addAction(Actions.sequence(actions));
        return true;
    }

    /**
     * Ajoute une action durant laquel l'interaction est interdite
     * @param action
     */
    default void addActionBloc(final Action action) {
        setTouchable(Touchable.disabled);
        final SequenceAction sequence = Actions.sequence(action);
        sequence.addAction(Actions.run(() -> setTouchable(Touchable.enabled)));
        addAction(sequence);
    }

    void setTouchable(final Touchable disabled);
    void addAction (final Action action);
}