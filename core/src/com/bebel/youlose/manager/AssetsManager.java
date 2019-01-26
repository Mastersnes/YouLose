package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    private String context;

    /**
     * Permet de charger les ressources du menu
     */
    public void loadMenu() {
        context = "menu";
        loadTextures();
        finishLoading();
    }

    /**
     * Permet de charger les ressources du contexte
     */
    private void loadTextures() {
        final String path = context + "/textures/";
        FileHandle[] files = Gdx.files.internal(path).list();
        for(FileHandle file: files) {
            load(path + file.name(), Texture.class);
        }
    }

    /**
     * Permet de recuperer la texture du contexte
     *
     * @param name
     * @return
     */
    public Texture getTexture(final String name) {
        final String path = context + "/textures/" + name;
        return get(path, Texture.class);
    }

    //-----
    public String getContext() {
        return context;
    }
}
