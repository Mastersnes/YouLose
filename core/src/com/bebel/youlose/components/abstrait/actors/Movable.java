package com.bebel.youlose.components.abstrait.actors;

import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;
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
     * @param alignment
     */
    default void move(float x, float y, int alignment) {
        float x2 = x; float y2 = y;

        if ((alignment & right) != 0)
            x2 = WORLD_WIDTH - getWidth() - x;
        if ((alignment & top) != 0)
            y2 = WORLD_HEIGHT - getHeight() - y;


        setPosition(x2, y2);
    }

    float getHeight();
    float getWidth();
    void setPosition(float x2, float y2);
}