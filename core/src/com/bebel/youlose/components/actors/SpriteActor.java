package com.bebel.youlose.components.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Image
 */
public class SpriteActor extends Image implements Movable {

    public SpriteActor(final String image, final AssetsManager manager) {
        super(manager.getTexture(image));
        setTouchable(Touchable.enabled);
        setBounds(0, 0, getWidth(), getHeight());
    }

    public void setAlpha(final int alpha) {
        getColor().a = alpha;
    }
}