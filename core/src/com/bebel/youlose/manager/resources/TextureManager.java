package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.graphics.Texture;

/**
 * Manager de texture
 */
public class TextureManager extends AbstractSubManager<Texture> {
    public TextureManager(final AssetsManager manager) {
        super(manager);
    }

    @Override
    protected String getPath(final String language) {
        final StringBuilder path = new StringBuilder("textures/");
        path.append(language).append("/");
        path.append(parent.context).append("/");
        return path.toString();
    }

    @Override
    protected Class<Texture> getType() {
        return Texture.class;
    }

    @Override
    public void dispose() {
    }

}
