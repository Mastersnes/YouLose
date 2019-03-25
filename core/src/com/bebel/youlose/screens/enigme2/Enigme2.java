package com.bebel.youlose.screens.enigme2;

import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.manager.save.SaveInstance;
import com.bebel.youlose.manager.save.SaveManager;

/**
 * Ecran de la premiere enigme
 */
public class Enigme2 extends AbstractScreen {
    public static final String NAME = "enigme2";
    private SaveInstance save;
    public Enigme2(final LaunchGame parent) {super(parent);}

    @Override
    public void create() {
        save = SaveManager.getInstance().getCurrent();
    }

    @Override
    public void start() {
        save = SaveManager.getInstance().getCurrent();
    }

    public void restart() {
        startScreen();
    }

    @Override
    public void makeEvents() {
    }

    @Override
    protected String context() {
        return NAME;
    }

    public SaveInstance getSave() {
        return save;
    }
}
