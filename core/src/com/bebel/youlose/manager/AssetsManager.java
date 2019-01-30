package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.utils.FontParameter;

import java.util.*;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    private String context;
    private Locale language;
    private int music;
    private int sound;
    private Map<Class, String> typeMap = new HashMap<>();
    private Map<String, BitmapFont> fonts = new HashMap<>();
    private List<String> loaded = new ArrayList<>();

    public AssetsManager() {
        super();
        typeMap.put(Texture.class, "textures");
        typeMap.put(FreeTypeFontGenerator.class, "fonts");

        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(getFileHandleResolver()));
        setLanguage(Locale.getDefault().getLanguage(), true);
    }

    /**
     * Permet de charger les ressources du context
     */
    public void load(final String context) {
        if (context == null) return;
        this.context = context;
        loadI18n();
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
        final String path = getPath(context, type, language.getLanguage());
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

    private void loadI18n() {
        load("i18n/i18n", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(this.language, "UTF-8"));
        finishLoading();
        // On remet le langage finalement trouvé
        setLanguage(getI18n().getLocale().getLanguage(), false);
    }

    /**
     * Permet de recuperer les ressources du contexte
     *
     * @param name
     * @return
     */

    public <T> T getAsset(final String name, final Class<T> type) {
        final String path = getPath(context, type, language.getLanguage()) + name;
        final String defaultPath = getPath(context, type, "en") + name;

        if (isLoaded(path)) return get(path, type);
        else return get(defaultPath, type);
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
    public I18NBundle getI18n() {
        return get("i18n/i18n", I18NBundle.class);
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

    public void setMusic(int music) {
        this.music = music;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public void setLanguage(final String language, boolean refresh) {
        if (refresh && isLoaded("i18n/i18n")) unload("i18n/i18n");
        this.language = new Locale(language);
        if (refresh) load(context);
    }

    @Override
    public synchronized <T> void load (String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
        super.load(fileName, type, parameter);
        loaded.add(fileName);
    }

    /**
     * Renvoi le chemin formaté
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
