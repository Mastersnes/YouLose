package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.bebel.youlose.components.refound.FontParameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manager de ressource
 */
public class AssetsManager extends AssetManager {
    public static final String GENERAL_CONTEXT = "general";
    public static final String GENERAL_PATH = GENERAL_CONTEXT+"/";
    private static AssetsManager instance;

    public String context;
    private List<String> loaded = new ArrayList<>();

    public TextureManager textures;
    public ImageManager images;
    public FontsManager fonts;
    public SoundManager sounds;
    public MusiqueManager musiques;
    public LanguageManager langue;
    public ConfigManager conf;

    /**
     * Construction et premiere resolution de l'I18N
     */
    private AssetsManager() {
        super();
        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(getFileHandleResolver()));
        conf = new ConfigManager(this);
        langue = new LanguageManager(this);
        images = new ImageManager(this);
        textures = new TextureManager(this);
        sounds = new SoundManager(this);
        musiques = new MusiqueManager(this);
        fonts = new FontsManager(this);

        loadContext(GENERAL_CONTEXT);
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
        images.load();
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
        if (fileName.contains(GENERAL_PATH) || "i18n/i18n".equals(fileName))
            Gdx.app.debug("AssetsManager", "Do not cache");
        else
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
    public TextureAtlas getGeneralAtlas(final String name) {
        return getAtlas(GENERAL_PATH + name);
    }
    public TextureAtlas getAtlas(final String name) {
        return textures.get(name);
    }
    public Animation<TextureRegion> getAnimation(Animation.PlayMode playMode, final String... files) {
        final Array<TextureRegion> frames = new Array<>();
        for (final String file : files) {
            frames.addAll(getAtlas(file).getRegions());
        }
        return new Animation<>(1f / 24f, frames, playMode);
    }
    public TextureRegionDrawable getGeneralDrawable(final String name) {
        return getDrawable(GENERAL_PATH + name);
    }
    public TextureRegionDrawable getDrawable(final String name) {
        if (name.endsWith(".png") || name.endsWith(".jpg")) {
            return images.getDrawable(name);
        }
        return textures.getDrawable(name);
    }
    public SpriteDrawable getDrawable(final String name, final Color color) {
        return (SpriteDrawable) getDrawable(name).tint(color);
    }

    public BitmapFont getGeneralFont(final String name, final FontParameter parameter) {
        return getFont(GENERAL_PATH, parameter);
    }
    public BitmapFont getFont(final String name, final FontParameter parameter) {
        return fonts.get(name, parameter);
    }


    @Override
    public synchronized void dispose() {
        super.dispose();
        images.dispose();
        textures.dispose();
        sounds.dispose();
        fonts.dispose();
        unloadAll();
    }

    //--- Getter setter
    public void setContext(final String context) {
        this.context = context;
    }
}
