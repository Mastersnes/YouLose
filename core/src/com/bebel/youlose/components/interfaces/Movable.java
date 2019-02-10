package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.utils.Align.*;

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

        if ((alignment & right) != 0)
            x2 = getParent().getWidth() - getWidth() - x;
        if ((alignment & top) != 0)
            y2 = getParent().getHeight() - getHeight() - y;

        setPosition(x2, y2);
    }

    default void debugMove() {
        final Actor toMove = getParent();
        boolean move = false;
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            toMove.moveBy(0, 1);
            move = true;
            Gdx.app.debug("MOVE", toMove.getX() + ", " + toMove.getY());
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            toMove.moveBy(-1, 0);
            move = true;
            Gdx.app.debug("MOVE", toMove.getX() + ", " + toMove.getY());
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            toMove.moveBy(0, -1);
            move = true;
            Gdx.app.debug("MOVE", toMove.getX() + ", " + toMove.getY());
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            toMove.moveBy(1, 0);
            move = true;
            Gdx.app.debug("MOVE", toMove.getX() + ", " + toMove.getY());
        }

        if (move) {
            float y = getHeight() - toMove.getHeight() - toMove.getY();
            Gdx.app.debug("MOVE", toMove.getX() + ", " + y);
        }
    }

    float getHeight();
    float getWidth();
    Actor getParent();
    void setPosition(float x2, float y2);
}