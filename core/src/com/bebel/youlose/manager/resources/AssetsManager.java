package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.FontParameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    private static AssetsManager instance;

    public LaunchGame game;

    public String context;
    private List<String> loaded = new ArrayList<>();

    public TextureManager textures;
    public FontsManager fonts;
    public SoundManager sounds;
    public MusiqueManager musiques;
    public LanguageManager langue;
    public ConfigManager conf;

    /**
     * Construction et premiere resolution de l'I18N
     */
    public AssetsManager() {
        super();
        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(getFileHandleResolver()));
        conf = new ConfigManager(this);
        langue = new LanguageManager(this);
        textures = new TextureManager(this);
        sounds = new SoundManager(this);
        musiques = new MusiqueManager(this);
        fonts = new FontsManager(this);
    }

    public static synchronized AssetsManager getInstance() {
        if (instance == null) {
            instance = new AssetsManager();
        }
        return instance;
    }

    /**
     * Rechargement du context en cours avec un nouveau language
     *
     * @param newLangage
     */
    public void reload(final String newLangage) {
        langue.load(newLangage);
        loadContext(context);
    }

    /**
     * Chargement du context indiqué
     *
     * @param context
     */
    public void loadContext(final String context) {
        this.context = context;
        textures.load();
        sounds.load();
        musiques.load();
        fonts.load();
        finishLoading();

        textures.indexTextures();
    }

    @Override
    public synchronized <T> void load(final String fileName, final Class<T> type, final AssetLoaderParameters<T> parameter) {
        if (isLoaded(fileName)) return;
        else if (langue != null) {
            // On efface d'abord les elements des autres langues avant de charger le nouvel element
            final String languagePath = "/" + conf.getLanguage() + "/";
            unload(fileName.replace(languagePath, "/en/"));
            unload(fileName.replace(languagePath, "/fr/"));
            unload(fileName.replace(languagePath, "/eo/"));
        }
        super.load(fileName, type, parameter);
        if (!"i18n/i18n".equals(fileName))
            loaded.add(fileName);
    }

    @Override
    public synchronized void unload(String fileName) {
        if (isLoaded(fileName)) {
            super.unload(fileName);
            loaded.remove(fileName);
        }
    }

    /**
     * Permet de tout decharger
     */
    public synchronized void unloadAll() {
        String fileName;
        for (final Iterator<String> it = loaded.iterator(); it.hasNext(); ) {
            fileName = it.next();
            if (isLoaded(fileName)) {
                super.unload(fileName);
                it.remove();
            }
        }
    }

    //--Utils
    public TextureAtlas getAtlas(final String name) {
        return textures.get(name);
    }
    public Drawable getDrawable(final String name) {
        return textures.getDrawable(name);
    }

    public BitmapFont getFont(final String name, final FontParameter parameter) {
        return fonts.get(name, parameter);
    }


    @Override
    public synchronized void dispose() {
        super.dispose();
        textures.dispose();
        sounds.dispose();
        fonts.dispose();
    }

    //--- Getter setter
    public void setContext(final String context) {
        this.context = context;
    }
}
