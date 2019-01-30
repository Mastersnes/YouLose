package com.bebel.youlose.components.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.components.interfaces.Movable;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Movable {
    private String key;

    public TextActor(final String key, final BitmapFont font) {
        super(key, new LabelStyle(font, null));
        this.key = key;
//        setDebug(true);
    }

    /**
     * Permet de raffraichir le texte
     * @param group
     */
    public void refresh(final AbstractGroup group) {
        final I18NBundle i18n = group.getManager().getI18n();
        setText(i18n.get(key));
        setSize(getPrefWidth(), getPrefHeight());
    }
}