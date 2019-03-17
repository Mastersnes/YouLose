package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.resources.ScreensManager;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.bottomRight;

/**
 * Ecran de la premiere enigme
 */
public class Enigme1 extends AbstractScreen {
    public static final String NAME = "enigme1";

    private ButtonActor quitter;
    private FeuillesGroup feuilles;
    private ImageActor cadre;
    private GlobeActor globe;

    public Enigme1(final LaunchGame parent) {super(parent);}

    @Override
    public void create() {
        addActor(new ImageActor("atlas:fond"));

        putActor(feuilles = new FeuillesGroup(this));

        putActor(cadre = new ImageActor("atlas:cadre_bois"))
                .setTouchable(Touchable.disabled);

        feuilles.create(cadre);

        putActor(globe = new GlobeActor(this));

        putActor(quitter = new ButtonActor("general/quitter.png"))
            .scaleBy(-0.5f);
    }

    @Override
    public void start() {
        cadre.move(220, 49);
        globe.move(114, -75);
        quitter.move(10, 10, bottomRight);
    }

    @Override
    public void makeEvents() {
        quitter.onClick((x, y, button, pointer) -> ScreensManager.getInstance().switchTo(MenuScreen.class));
    }

    @Override
    protected String context() {
        return NAME;
    }
}
