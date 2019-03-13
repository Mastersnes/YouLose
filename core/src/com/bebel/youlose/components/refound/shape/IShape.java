package com.bebel.youlose.components.refound.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Shape2D;

/**
 * Representation d'un shape pouvant etre dessiné
 */
public interface IShape extends Shape2D {

    void draw(final ShapeRenderer shapes, final float parentX, final float parentY);

    void add(final float x, final float y, final float r);

    float getX();

    float getY();

    float getR();
}
