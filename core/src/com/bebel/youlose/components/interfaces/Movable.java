package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Represente une entitée pouvant etre bougée
 */
public interface Movable {
    /**
     * Permet de placer un element relativement à l'espace
     * @param x
     * @param y
     */
    default void move(float x, float y) {
        move(x, y, top | left);
    }
    default void move(float x, float y, int alignment) {
        float x2 = x; float y2 = y;
        float parentWidth = getParent() != null ? getParent().getWidth() : WORLD_WIDTH;
        float parentHeight = getParent() != null ? getParent().getHeight() : WORLD_HEIGHT;


        if ((alignment & right) != 0)
            x2 = parentWidth - getWidth() - x;
        if ((alignment & top) != 0)
            y2 = parentHeight - getHeight() - y;

        setPosition(x2, y2);
    }

    float getHeight();
    float getWidth();
    Actor getParent();
    void setPosition(float x2, float y2);
}