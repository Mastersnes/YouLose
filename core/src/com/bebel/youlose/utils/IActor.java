package com.bebel.youlose.utils;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Interface d'acteur
 */
public interface IActor {
    /**
     * Permet de deplacer l'acteur
     *
     * @param x
     * @param y
     * @param <T>
     * @return
     */
    <T extends Actor> T move(final float x, final float y);
    <T extends Actor> T move(final float x, final float y, final int align);

    /**
     * Permet de definir la transparence de l'element
     *
     * @param alpha
     */
    <T extends Actor> T setAlpha(final int alpha);
    <T extends Actor> float getAlpha();

    /**
     * Ajoute une suite d'actions
     *
     * @param actions
     */
    boolean addActions(final Action... actions);

    /**
     * Ajoute une suite d'actions durant laquel l'interaction est interdite
     */
    boolean addBlockedActions(final Action... actions);

    /**
     * Stop les actions de l'acteur
     */
    void stop();

    /**
     * Renvoi la position centrée
     *
     * @return
     */
    float centerX();
    float centerY();
}
