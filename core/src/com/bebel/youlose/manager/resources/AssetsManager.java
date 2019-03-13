package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.draw.Sprite;

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
    public AnimationManager animations;
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
        animations = new AnimationManager(this);
        sounds = new SoundManager(this);
        musiques = new MusiqueManager(this);
        fonts = new FontsManager(this);

        loadContext(GENERAL_CONTEXT, true);
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
        loadContext(context, true);
    }

    /**
     * Chargement du context indiqué
     *
     * @param context
     */
    public void loadContext(final String context, boolean synchrone) {
        final String oldContext = this.context;
        this.context = context;
        images.load();
        textures.load();
        animations.load();
        sounds.load();
        musiques.load();
        fonts.load();
        this.context = oldContext;

        if (synchrone) finishLoading(context);
    }

    public void finishLoading(final String context) {
        this.context = context;
        super.finishLoading();
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
            Gdx.app.debug("AssetsManager", "Do not cache : " + fileName);
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
        unloadContext(null);
    }
    public synchronized void unloadContext(final String context) {
        String fileName;
        for (final Iterator<String> it = loaded.iterator(); it.hasNext(); ) {
            fileName = it.next();
            if (isLoaded(fileName)) {
                if (context == null || fileName.contains("/" + context + "/")) {
                    super.unload(fileName);
                    it.remove();
                }
            }
        }
    }

    //--Utils
    public Animation<TextureRegion> getAnimation(final String key, final Animation.PlayMode playMode, final float fps) {
        return animations.getAnimation(key, playMode, fps);
    }

    public Sprite getSprite(final String name, final Color color) {
        final Sprite sprite;
        if (name.endsWith(".png") || name.endsWith(".jpg")) {
            sprite = new Sprite(images.get(name));
        } else sprite = new Sprite(textures.getTexture(name));

        sprite.setColor(color.r, color.g, color.b, 1f);
        return sprite;
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
