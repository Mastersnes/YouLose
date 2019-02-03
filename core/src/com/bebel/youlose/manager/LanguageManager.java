package com.bebel.youlose.manager;

import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class LanguageManager {
    private AssetsManager parent;
    private Locale language;

    public LanguageManager(final AssetsManager manager) {
        parent = manager;
        setLanguage(Locale.getDefault().getLanguage(), true);
    }

    public void load() {
        parent.load("i18n/i18n", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(this.language, "UTF-8"));
        parent.finishLoading();
        // On remet le langage finalement trouvé
        setLanguage(getI18n().getLocale().getLanguage(), false);
    }

    public I18NBundle getI18n() {
        return parent.get("i18n/i18n", I18NBundle.class);
    }
    public String get(final String key) {
        return getI18n().get(key);
    }

    public void setLanguage(final String language, boolean refresh) {
        if (refresh && parent.isLoaded("i18n/i18n")) parent.unload("i18n/i18n");
        this.language = new Locale(language);
        if (refresh) parent.load(parent.context);
    }
    public String getLanguage() {
        return language.getLanguage();
    }
}
