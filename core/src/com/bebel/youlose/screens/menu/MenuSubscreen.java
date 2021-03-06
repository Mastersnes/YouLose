package com.bebel.youlose.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;

/**
 * Sous écran du menu
 */
public abstract class MenuSubscreen extends AbstractGroup implements Startable {
    protected final MenuScreen screen;

    public MenuSubscreen(final MenuScreen screen) {
        this.screen = screen;
        create();
    }

    public abstract void create();

    /**
     * Permer de (re)demarrer le sous ecran
     */
    public void start() {
        startSubscreen();
        for (final Actor actor : getChildren()) {
            if (actor instanceof Startable) ((Startable) actor).start();
        }
    }
    public abstract void startSubscreen();

    public MenuScreen getScreen() {
        return screen;
    }
}
