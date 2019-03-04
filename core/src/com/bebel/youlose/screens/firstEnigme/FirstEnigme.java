package com.bebel.youlose.screens.firstEnigme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.screens.menu.boutons.MenuBackground;
import com.bebel.youlose.screens.menu.boutons.MenuButtons;
import com.bebel.youlose.screens.menu.options.MenuOptions;
import com.bebel.youlose.screens.menu.slots.MenuSlots;
import com.bebel.youlose.screens.menu.vitre.MenuVitre;

import static com.badlogic.gdx.utils.Align.bottomRight;
import static com.bebel.youlose.utils.ActorUtils.addActions;
import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Ecran de la premiere enigme
 */
public class FirstEnigme extends AbstractScreen {
    private ButtonActor quitter;

    public FirstEnigme(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.loadContext("menu");

        putActor(quitter = new ButtonActor("background/atlas:quitter"));
        move(quitter, 10, 10, bottomRight);
    }

    @Override
    public void makeEvents() {
        quitter.onClick((x, y, button, pointer) -> Gdx.app.exit());
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.debug("EXIT", "DISPOSE");
    }
}
