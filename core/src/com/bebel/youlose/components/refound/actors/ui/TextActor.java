package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Refreshable, IActor {
    protected final AssetsManager manager;

    public TextActor(final String key, final BitmapFont font) {
        super(key, new LabelStyle(font, null));
        this.manager = AssetsManager.getInstance();
        setName(key);
        refresh(getColor());
    }

    @Override
    public void refresh(final Color color) {
        setColor(color);
        setText(manager.langue.get(getName()));
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    @Override
    public void setText(CharSequence newText) {
        super.setText(newText);
        setName(String.valueOf(newText));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color.r, color.g, color.b, getAlpha());
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