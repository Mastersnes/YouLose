package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Image
 */
public class ImageActor extends Image implements Refreshable {
    private final AssetsManager manager;

    /**
     * Constructeur
     *
     * @param manager
     * @param image
     */
    public ImageActor(final AssetsManager manager, final String image) {
        super(manager.getTexture(image));
        this.manager = manager;
        setName(image);
        setTouchable(Touchable.enabled);
        setBounds(0, 0, getWidth(), getHeight());
    }

    /**
     * Permet de definir la transparence de l'image
     *
     * @param alpha
     */
    public ImageActor setAlpha(final int alpha) {
        getColor().a = alpha;
        return this;
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