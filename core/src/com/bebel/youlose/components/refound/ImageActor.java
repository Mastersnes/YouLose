package com.bebel.youlose.components.refound;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Image
 */
public class ImageActor extends Image implements Movable, Refreshable {
    private final AssetsManager manager;

    public ImageActor(final AssetsManager manager, final String image) {
        super(manager.getTexture(image));
        this.manager = manager;
        setName(image);
        setTouchable(Touchable.enabled);
        setBounds(0, 0, getWidth(), getHeight());
    }

    public void setAlpha(final int alpha) {
        getColor().a = alpha;
    }

    @Override
    public boolean refresh() {
        setDrawable(manager.getDrawable(getName()));
        return true;
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}