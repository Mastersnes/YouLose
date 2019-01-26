package com.bebel.youlose.components.abstrait.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Image
 */
public class SpriteActor extends Image implements Movable {

    public SpriteActor(final String image, final AssetsManager manager) {
        super(manager.getTexture(image));
    }

    public void setAlpha(final int alpha) {
        getColor().a = alpha;
    }
}