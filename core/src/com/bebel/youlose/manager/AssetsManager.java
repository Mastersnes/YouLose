package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager de ressource
 */
public class AssetsManager {
    private static AssetsManager instance;
    private final Map<String, Texture> textures = new HashMap<>();

    private AssetsManager() {
        load("menu", "background.jpg");
        load("menu", "again.png");
        load("menu", "credits.png");
        load("menu", "play.png");
        load("menu", "quitter.png");
        load("menu", "title.png");
    }

    public static AssetsManager getInstance() {
        if (instance == null)
            instance = new AssetsManager();
        return instance;
    }

    private void load(final String path, final String name) {
        final String globalName = path + "/" + name;
        final Texture texture = new Texture(Gdx.files.internal(globalName));
        textures.put(globalName, texture);
    }

    public Texture getTexture(final String path, final String name) {
        final String globalName = path + "/" + name;
        return textures.get(globalName);
    }

    public void dispose() {
        for (final Texture texture : textures.values()) {
            texture.dispose();
        }
    }
}
