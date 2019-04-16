package com.bebel.youlose.screens.enigme2;

import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.manager.save.GameSave;
import com.bebel.youlose.manager.save.SaveManager;

import static com.bebel.youloseClient.enums.Emplacement.ENIGME2;

/**
 * Ecran de la premiere enigme
 */
public class Enigme2 extends AbstractScreen {
    private GameSave save;
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
        return ENIGME2;
    }
}
