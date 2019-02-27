package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Sous manager de ressources
 * @param <T> Ressource concernée
 */
public abstract class AbstractSubManager<T> {
    protected AssetsManager parent;

    public AbstractSubManager(final AssetsManager manager) {
        parent = manager;
    }

    /**
     * Charge les ressources du contexte
     */
    public void load() {
        loadAssets(getDir("en").list());
    }

    /**
     * Renvoi la ressource indiqué
     *
     * @param name
     * @return
     */
    public T get(final String name) {
        final String language = parent.conf.getLanguage();
        String filePath = getPath(language, name);

        if (!parent.isLoaded(filePath)) {
            filePath = filePath.replace("/" + language + "/", "/en/");
        }
        return parent.get(filePath, getType());
    }

    /**
     * Permet de charger les ressources recursivement
     *
     * @param root
     */
    protected void loadAssets(final FileHandle[] root) {
        String currentLanguage = parent.conf.getLanguage();

        for (final FileHandle defaultFile : root) {
            if (defaultFile.isDirectory()) loadAssets(defaultFile.list());
            else {
                final FileHandle file = getSpecificFile(currentLanguage, defaultFile.path());
                if (file.exists()) load(file.path());
                else load(defaultFile.path());
            }
        }
    }

    protected void load(final String path) {
        parent.load(path, getType());
    }

    protected FileHandle getDir(final String language) {
        return Gdx.files.internal(getPath(language));
    }
    protected FileHandle getSpecificFile(final String language, final String defaultPath) {
        return Gdx.files.internal(defaultPath.replace("/en/", "/" + language + "/"));
    }
    protected String getPath(final String language, final String name) {
        return new StringBuilder(getPath(language)).append(name).toString();
    }

    protected abstract String getPath(final String language);

    protected abstract Class<T> getType();

    public abstract void dispose();
}
