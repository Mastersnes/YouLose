package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.utils.FontParameter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    private String context;
    private Locale language;
    private Map<String, BitmapFont> fonts = new HashMap<>();

    public AssetsManager() {
        super();
        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(getFileHandleResolver()));

        language = new Locale(Locale.getDefault().getLanguage());
        loadI18n();
    }

    /**
     * Permet de charger les ressources du context
     */
    public void load(final String context) {
        this.context = context;
        loadTextures();
        loadFonts();
        loadI18n();
        finishLoading();
    }

    /**
     * Permet de charger les ressources du contexte
     */
    private void loadTextures() {
        final String path = context + "/textures/";
        FileHandle[] files = Gdx.files.internal(path).list();
        for (FileHandle file : files) {
            load(path + file.name(), Texture.class);
        }
    }
    private void loadFonts() {
        final String path = context + "/fonts/";
        FileHandle[] files = Gdx.files.internal(path).list();
        for (FileHandle file : files) {
            load(path + file.name(), FreeTypeFontGenerator.class);
        }
    }
    private void loadI18n() {
        final String path = "i18n/i18n";
        load(path, I18NBundle.class, new I18NBundleLoader.I18NBundleParameter());
    }

    /**
     * Permet de recuperer les ressources du contexte
     *
     * @param name
     * @return
     */
    public Texture getTexture(final String name) {
        final String path = context + "/textures/" + name;
        return get(path, Texture.class);
    }
    public FreeTypeFontGenerator getFont(final String name) {
        final String path = context + "/fonts/" + name;
        return get(path, FreeTypeFontGenerator.class);
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
    public String getContext() {
        return context;
    }
    public void setLanguage(final String language) {
        this.language = new Locale(language);
        loadI18n();
    }
}
