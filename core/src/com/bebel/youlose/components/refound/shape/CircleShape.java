package com.bebel.youlose.components.refound.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 * Representation d'un shape circle
 */
public class CircleShape extends Circle implements IShape {

    public CircleShape(final float x, final float y, final float radius) {
        super(x, y, radius);
    }

    @Override
    public void draw(final ShapeRenderer shapes, final float parentX, final float parentY) {
        shapes.circle(parentX + x, parentY + y, radius);
    }

    @Override
    public void add(float x, float y, float r) {
        set(this.x + x, this.y + y, this.radius + r);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getR() {
        return radius;
    }
}
