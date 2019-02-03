package com.bebel.youlose.components.refound;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Movable, Refreshable {
    private final AssetsManager manager;

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