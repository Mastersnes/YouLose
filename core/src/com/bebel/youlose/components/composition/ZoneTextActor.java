package com.bebel.youlose.components.composition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.interfaces.Animable;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TypingActor;
import com.bebel.youlose.components.runnable.OneTimeRunnable;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.utils.Align.bottomLeft;
import static com.bebel.youlose.utils.RunnableUtils.oneTime;

/**
 * Composition d'une zone de texte
 */
public class ZoneTextActor extends AbstractGroup implements Animable {
    private OneTimeRunnable onFinishRun;

    private final BitmapFont defaultFont;
    private final Color defaultColor;

    private final ImageActor cadre;
    private final List<TypingActor> textes = new ArrayList<>();

    private int margeX = 0, margeY = 0;

    public ZoneTextActor(final String cadreImg) {
        this(cadreImg, 0, 0);
    }
    public ZoneTextActor(final String cadreImg, final int margeX, final int margeY) {
        this(cadreImg, null, null, margeX, margeY);
    }
    public ZoneTextActor(final String cadreImg, final BitmapFont font, final Color color, final int margeX, final int margeY) {
        super();
        setMarge(margeX, margeY);
        if (font == null) this.defaultFont = manager.getFont("general/arial.ttf", new FontParameter(20, Color.valueOf("#ced9ad")));
        else defaultFont = font;

        if (color == null) this.defaultColor = Color.valueOf("#ced9ad");
        else defaultColor = color;

        putActor(cadre = new ImageActor(cadreImg));

        refresh(this.defaultColor);
    }

    /**
     * Redefini la marge
     * @param x
     * @param y
     */
    public void setMarge(final int x, final int y) {
        this.margeX = x;
        this.margeY = y;
    }

    @Override
    public void refresh(final Color color) {
        super.refresh(color);
        setSize(cadre.getWidth(), cadre.getHeight());

        for (final TypingActor texte : textes) {
            repositionne(texte);
        }
    }

    public TypingActor add(final String key) {
        return add(key, defaultFont, defaultColor);
    }
    public TypingActor add(final String key, final BitmapFont font, final Color color) {
        final TypingActor newText = new TypingActor(key, font);
        newText.setColor(color);
        return add(newText);
    }
    public TypingActor add(final TypingActor newText) {
        addActor(newText);
        if (textes.size() > 0) {
            final TypingActor lastText = textes.get(textes.size()-1);
            lastText.onFinish(oneTime(()-> newText.restart()));
            newText.pause();
        } else newText.restart();

        textes.add(newText);

        return repositionne(newText);
    }

    /**
     * Termine la ligne en cours et en commence une autre
     * @return
     */
    public ZoneTextActor endLine() {
        if (textes.size() == 0) return this;
        final TypingActor lastText = textes.get(textes.size()-1);
        lastText.endLine();
        return this;
    }

    /**
     * Repositionne le texte indiqué
     * @param texte
     * @return
     */
    private TypingActor repositionne(final TypingActor texte) {
        int index = textes.indexOf(texte);
        if (index < 0) return texte;
        // Si c 'est le premier element, on le place a la position 0
        if (index == 0) texte.move(margeX, margeY);
        else {
            final TypingActor preview = textes.get(index-1);

            float newX = preview.getX() + preview.getMaxWidth();
            boolean depasse = newX + texte.getMaxWidth() > cadre.getWidth();
            // Si il y a un retour a la ligne ou que ca depasse, on revient a la ligne
            if (preview.isEndOfLine() || depasse) texte.move(margeX, preview.getY() - preview.getHeight(), bottomLeft);
            else texte.move(preview.getX() + preview.getMaxWidth(), preview.getY(), bottomLeft);
        }
        return texte;
    }

    /**
     * Retourne le texte courant : le premier texte non terminé
     * @return
     */
    public TypingActor currentText() {
        for (final TypingActor texte : textes) {
            if (!texte.isFinish()) {
                return texte;
            }
        }
        return null;
    }

    @Override
    public void onFinish(final Runnable run) {
        onFinishRun = oneTime(run);
    }

    @Override
    public void pause() {
        final TypingActor texte = currentText();
        if (texte != null) texte.pause();
    }

    @Override
    public void resume() {
        final TypingActor texte = currentText();
        if (texte != null) texte.resume();
    }

    @Override
    public void restart() {
        for (final TypingActor actor : textes) {
            actor.restart();
            actor.pause();
        }
        resume();
        if (onFinishRun != null) onFinishRun.restart();
    }

    @Override
    public void clear() {
        for (final TypingActor actor : textes) {
            removeActor(actor);
        }
        textes.clear();
        onFinishRun = null;
    }

    public boolean isFinish() {
        for (final TypingActor texte : textes) {
            if (!texte.isFinish()) return false;
        }
        return true;
    }

    public void finish() {
        for (final TypingActor texte : textes) {
            texte.finish();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        final TypingActor lastText = textes.get(textes.size()-1);
        if (lastText.isFinish() && onFinishRun != null) onFinishRun.run();
    }

    @Override
    public void setSpeed(float speed) {
        final TypingActor texte = currentText();
        if (texte != null) texte.setSpeed(speed);
    }

    @Override
    public void makeSpecificEvents() {
    }
}
