package com.bebel.youlose.components.abstrait.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Movable {
    private String key;

    public TextActor(final String key, final BitmapFont font) {
        super(key, new LabelStyle(font, null));
        this.key = key;
    }

    /**
     * Permet de raffraichir le texte
     * @param i18n
     */
    public void refreshText(final I18NBundle i18n) {
        Gdx.app.debug("REFRESH", "new texte : " + i18n.getLocale().getLanguage());
        setText(i18n.get(key));
    }
}