package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bebel.youlose.utils.FontParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    public String context;
    private Map<Class, String> typeMap = new HashMap<>();
    private Map<String, BitmapFont> fonts = new HashMap<>();
    private List<String> loaded = new ArrayList<>();

    public SoundManager sound;
    public LanguageManager langue;

    public AssetsManager() {
        super();
        typeMap.put(Texture.class, "textures");
        typeMap.put(FreeTypeFontGenerator.class, "fonts");
        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(getFileHandleResolver()));

        sound = new SoundManager(this);
        langue = new LanguageManager(this);
    }

    /**
     * Permet de charger les ressources du context
     */
    public void load(final String context) {
        if (context == null) return;
        this.context = context;
        langue.load();
        loadAssets(Texture.class);
        loadAssets(FreeTypeFontGenerator.class);
        finishLoading();
    }

    public void unloadAll() {
        for (final String loadedPath : loaded) {
            unload(loadedPath);
        }
        loaded.clear();
    }

    /**
     * Permet de charger les ressources du contexte
     */
    private <T> void loadAssets(final Class<T> type) {
        final String path = getPath(context, type, langue.getLanguage());
        final String defaultPath = getPath(context, type, "en");
        FileHandle[] files = Gdx.files.internal(path).list();
        FileHandle[] defaultFiles = Gdx.files.internal(defaultPath).list();
        for (FileHandle defaultFile : defaultFiles) {
            final FileHandle file = findIn(files, defaultFile.name());
            if (file != null)
                load(path + file.name(), type);
            else load(defaultPath + defaultFile.name(), type);
        }
    }

    /**
     * Permet de recuperer les ressources du contexte
     *
     * @param name
     * @return
     */

    public <T> T getAsset(final String name, final Class<T> type) {
        final String path = getPath(context, type, langue.getLanguage()) + name;
        final String defaultPath = getPath(context, type, "en") + name;

        if (isLoaded(path)) return get(path, type);
        else return get(defaultPath, type);
    }

    public TextureRegionDrawable getDrawable(final String name) {
        return new TextureRegionDrawable(getTexture(name));
    }

    public Texture getTexture(final String name) {
        return getAsset(name, Texture.class);
    }

    public FreeTypeFontGenerator getFont(final String name) {
        return getAsset(name, FreeTypeFontGenerator.class);
    }

    public BitmapFont getFont(final String name, final FontParameter parameter) {
        final String key = name + parameter.getCode();
        BitmapFont font = fonts.get(key);
        if (font == null) {
            font = getFont(name).generateFont(parameter);
            fonts.put(key, font);
        }
        return font;
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
        for (final BitmapFont font : fonts.values()) {
            font.dispose();
        }
    }

    //-----
    public void setContext(final String context) {
        this.context = context;
    }

    @Override
    public synchronized <T> void load(String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
        super.load(fileName, type, parameter);
        loaded.add(fileName);
    }

    /**
     * Renvoi le chemin formaté
     *
     * @param context
     * @param type
     * @param language
     * @return
     */
    private <T> String getPath(String context, Class<T> type, final String language) {
        final StringBuilder path = new StringBuilder();
        path.append(context).append("/");
        path.append(typeMap.get(type)).append("/");
        path.append(language).append("/");
        return path.toString();
    }

    /**
     * Trouve un fichier dans une liste
     *
     * @param files
     * @param name
     * @return
     */
    private FileHandle findIn(final FileHandle[] files, final String name) {
        for (final FileHandle file : files) {
            if (file.name().equals(name)) return file;
        }
        return null;
    }
}
