package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.utils.IActor;

/**
 * Specification de l'acteur TypingLabel
 */
public class TypingActor extends TextActor implements Refreshable, IActor {
    private String originalText;
    private float charProgress;
    private boolean pause;
    public static float SPEED = 18f;


    public TypingActor(final String key, final BitmapFont font) {
        super("", font);
        restart(key);
        pause();
    }

    @Override
    public void refresh(final Color color) {
        originalText = manager.langue.get(getName());

        setColor(color);
        setText(getProgress());
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    public void pause() {
        pause = true;
    }
    public void stop() {
        super.stop();
        charProgress = 0;
        pause = true;
    }
    public void restart(final String newKey) {
        setName(newKey);
        charProgress = 0;
        pause = false;
    }

    public String getProgress() {
        return originalText.substring(0, (int)charProgress);
    }

    @Override
    public void act(final float delta) {
        super.act(delta);

        if (!pause) {
            charProgress += delta * SPEED;
            if (charProgress > originalText.length())
                charProgress = originalText.length();
        }
        setText(getProgress());
    }
}