package com.bebel.youlose.components.refound.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Refound des sprites
 */
public class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {
    public Sprite(final Texture texture) {
        super(texture);
    }

    public Sprite(final TextureRegion texture) {
        super(texture);
    }

    public Color mul(final Color color) {
        final Color newColor = getColor().mul(color);
        setColor(newColor);
        return newColor;
    }
}
