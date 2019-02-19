package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Refreshable {
    private final AssetsManager manager;

    /**
     * Constructeur
     *
     * @param manager
     * @param key
     * @param font
     */
    public TextActor(final AssetsManager manager, final String key, final BitmapFont font) {
        super(key, new LabelStyle(font, null));
        this.manager = manager;
        setName(key);
    }

    @Override
    public boolean refresh() {
        setText(manager.langue.get(getName()));
        setSize(getPrefWidth(), getPrefHeight());
        return true;
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}