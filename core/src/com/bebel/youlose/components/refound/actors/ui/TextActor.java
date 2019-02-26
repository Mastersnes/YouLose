package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Refreshable, IActor {
    private final AssetsManager manager;

    public TextActor(final AssetsManager manager, final String key, final BitmapFont font) {
        super(key, new LabelStyle(font, null));
        this.manager = manager;
        setName(key);
        refresh();
    }

    @Override
    public void refresh() {
        setText(manager.langue.get(getName()));
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }

    //-- ActorUtils
    @Override
    public <T extends Actor> T move(final float x, final float y) {
        return (T) ActorUtils.move(this, x, y);
    }
    @Override
    public <T extends Actor> T move(final float x, final float y, final int align) {
        return (T) ActorUtils.move(this, x, y, align);
    }
    @Override
    public <T extends Actor> T setAlpha(int alpha) {
        return (T) ActorUtils.setAlpha(this, alpha);
    }
    @Override
    public float getAlpha() {
        return ActorUtils.getAlpha(this);
    }
    @Override
    public boolean addActions(final Action... actions) {
        return ActorUtils.addActions(this, actions);
    }
    @Override
    public boolean addBlockedActions(final Action... actions) {
        return ActorUtils.addBlockedActions(this, actions);
    }
    @Override
    public void stop() {
        ActorUtils.stop(this);
    }
    @Override
    public float centerX() {
        return ActorUtils.centerX(this);
    }
    @Override
    public float centerY() {
        return ActorUtils.centerY(this);
    }
}