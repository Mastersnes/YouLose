package com.bebel.youlose.components.refound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Abstraction de la checkbox
 */
public class CheckActor extends CheckBox implements Movable, Refreshable {
    private AssetsManager manager;

    /**
     * Constructeur
     * @param manager
     * @param font
     * @param text
     * @param off
     * @param on
     */
    public CheckActor(final AssetsManager manager, final BitmapFont font, final String text, final String off, final String on) {
        this(manager, text, new CheckBoxStyle(manager.getDrawable(off), manager.getDrawable(on), font, Color.WHITE));
    }

    /**
     * Constructeur
     * @param manager
     * @param text
     * @param style
     */
    public CheckActor(final AssetsManager manager, final String text, final CheckBoxStyle style) {
        super(text, style);
        setName(text);
        this.manager = manager;
        refresh();
    }

    /**
     * Inverse l'ordre de la checkbox
     */
    public CheckActor reverse() {
        final Label label = getLabel();
        final Image image = getImage();
        clearChildren();
        add(label);
        add(image);
        pack();
        return this;
    }

    /**
     * Inverse l'ordre de la checkbox
     */
    public void onChange(final Runnable run) {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                run.run();
            }
        });
    }

    @Override
    public boolean refresh() {
        setText(manager.langue.get(getName()));
        pack();
        return true;
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}
