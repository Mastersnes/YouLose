package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager de texture
 */
public class TextureManager extends AbstractSubManager<TextureAtlas> {
    private List<String> atlasLoaded = new ArrayList<>();
    private Map<String, TextureAtlas.AtlasRegion> texturesLoaded = new HashMap<>();

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
    public void load() {
        atlasLoaded.clear();
        texturesLoaded.clear();
        super.load();
    }

    @Override
    protected void load(final String path) {
        if (path.contains(".atlas")) {
            super.load(path);
            atlasLoaded.add(path);
        }
    }

    public void indexTextures() {
        for (final String atlasPath : atlasLoaded) {
            final TextureAtlas atlas = parent.get(atlasPath, getType());
            for (final TextureAtlas.AtlasRegion region : atlas.getRegions()) {
                final String atlasName = atlasPath.replaceAll("\\.atlas", ":");
                texturesLoaded.put(atlasName + region.name, region);
            }
        }
    }

    /**
     * Renvoi la texture demandée
     * @param name
     * @return
     */
    public Drawable getDrawable(final String name) {
        String path = getPath(parent.conf.getLanguage());
        TextureAtlas.AtlasRegion region = texturesLoaded.get(path + name);
        if (region == null) {
            path = getPath("en");
            region = texturesLoaded.get(path + name);
        }
        return new TextureRegionDrawable(region);
    }

    @Override
    protected Class<TextureAtlas> getType() {
        return TextureAtlas.class;
    }

    @Override
    public void dispose() {
    }

}
