package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Manager de texture
 */
public class ImageManager extends AbstractSubManager<Texture> {
    public ImageManager(final AssetsManager manager) {
        super(manager);
    }

    @Override
    protected String getPath(final String language) {
        final StringBuilder path = new StringBuilder("images/");
        path.append(parent.context).append("/");
        return path.toString();
    }

    /**
     * Renvoi la texture demandée
     *
     * @param name
     * @return
     */
    public Drawable getDrawable(final String name) {
        return new TextureRegionDrawable(get(name));
    }

    @Override
    protected Class<Texture> getType() {
        return Texture.class;
    }

    @Override
    public void dispose() {
    }

}
