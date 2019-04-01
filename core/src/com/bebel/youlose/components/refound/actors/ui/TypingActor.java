package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.interfaces.Animable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.runnable.OneTimeRunnable;
import com.bebel.youlose.utils.IActor;

import static com.bebel.youlose.utils.RunnableUtils.oneTime;

/**
 * Specification de l'acteur TypingLabel
 */
public class TypingActor extends TextActor implements Refreshable, IActor, Animable {
    private String originalText;
    private float maxWidth;

    private float progress, speed;
    private boolean pause;

    private OneTimeRunnable onFinishRun;

    public TypingActor(final String key) {
        this(key, null);
    }
    public TypingActor(final String key, final BitmapFont font) {
        super("", font);
        restart(key);
        pause();
        speed = 17f;
    }

    @Override
    public void refresh(final Color color) {
        setOriginalText();

        setColor(color);
        setText(getProgress());
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    private void setOriginalText() {
        originalText = manager.langue.get(getName());

        // Calcul de la taille max
        final boolean oldVisibility = isVisible();
        setVisible(false);
        setText(originalText);
        maxWidth = getPrefWidth();
        setText("");
        setVisible(oldVisibility);
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void resume() {
        pause = false;
    }

    @Override
    public void stop() {
        super.stop();
        progress = 0;
        pause = true;
    }

    @Override
    public void restart() {
        restart(getName());
    }
    public void restart(final String newKey) {
        setName(newKey);
        progress = 0;
        pause = false;
        if (onFinishRun != null) onFinishRun.restart();

        refresh(getColor());
    }

    public String getProgress() {
        return originalText.substring(0, (int) progress);
    }

    @Override
    public void onFinish(final Runnable run) {
        this.onFinishRun = oneTime(run);
    }

    @Override
    public void act(final float delta) {
        super.act(delta);

        if (!pause && !isFinish()) {
            progress += delta * speed;
            if (isFinish()) {
                if (onFinishRun != null) onFinishRun.run();
                progress = originalText.length();
            }
        }
        setText(getProgress());
    }

    @Override
    public void setText(final CharSequence newText) {
        super.setText(newText);
        setSize(getPrefWidth(), getPrefHeight());
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }

    @Override
    public boolean isFinish() {
        return progress >= originalText.length();
    }

    @Override
    public void finish() {
        progress = originalText.length();
    }

    @Override
    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    @Override
    public float getMaxWidth() {
        return maxWidth;
    }
}