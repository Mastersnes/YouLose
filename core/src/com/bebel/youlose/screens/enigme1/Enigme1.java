package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
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

    public Enigme1(final LaunchGame parent) {super(parent);}

    @Override
    public void create() {
        manager.loadContext("enigme1");

        putActor(quitter = new ButtonActor("general/quitter.png"))
            .move(10, 10, bottomRight)
            .setColor(Color.GREEN);
        quitter.scaleBy(-0.5f);
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
