package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

/**
 * Specification de l'acteur Label
 */
public class TextActor extends Label implements Refreshable, IActor {
    protected final AssetsManager manager;
    protected boolean endOfLine;

    public TextActor(final String key, final BitmapFont font) {
        super(key, new LabelStyle(new BitmapFont(), null));
        this.manager = AssetsManager.getInstance();

        BitmapFont fontStyle = font;
        if (fontStyle == null) fontStyle = manager.getFont("general/arial.ttf", new FontParameter(20, Color.valueOf("#ced9ad")));
        setStyle(new LabelStyle(fontStyle, null));

        setName(key);
        refresh(getColor());
    }

    @Override
    public void refresh(final Color color) {
        setColor(color);
        super.setText(manager.langue.get(getName()));
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    @Override
    public void setText(final CharSequence newText) {
        setText(newText, false);
    }
    public void setText(final CharSequence newText, final boolean changeName) {
        super.setText(newText);
        if (changeName) setName(String.valueOf(newText));
    }

    public void endLine() {
        this.endOfLine = true;
    }

    public boolean isEndOfLine() {
        return endOfLine;
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