package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.save.SaveManager;

import javax.print.attribute.standard.MediaSize;

import static com.badlogic.gdx.utils.Align.bottomRight;
import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Ecran de la premiere enigme
 */
public class Enigme1 extends AbstractScreen {
    public static String NAME = "Enigme1";
    private ButtonActor quitter;
    private ImageActor feuille;

    public Enigme1(final LaunchGame parent) {super(parent);}

    @Override
    public void create() {
        manager.loadContext("enigme1");

        putActor(feuille = new ImageActor("button.png"))
        .move(50, 50);

        feuille.debug();

        putActor(quitter = new ButtonActor("general/quitter.png"))
            .move(-10, 10, bottomRight)
            .scaleBy(-0.5f);
    }

    @Override
    public void makeEvents() {
        quitter.onClick((x, y, button, pointer) -> Gdx.app.exit());
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.debug("Enigme1", "DISPOSE");
    }
}
