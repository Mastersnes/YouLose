package com.bebel.youlose.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.refound.ButtonActor;
import com.bebel.youlose.components.refound.ImageActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.menu.boutons.MenuBackground;
import com.bebel.youlose.menu.boutons.MenuButtons;
import com.bebel.youlose.menu.options.MenuOptions;
import com.bebel.youlose.menu.vitre.MenuVitre;
import com.bebel.youlose.utils.AbstractStage;
import com.bebel.youlose.utils.Constantes;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.right;

public class MenuScreen extends AbstractStage {
    private MenuBackground background;
    private MenuOptions options;
    private MenuButtons buttons;
    private MenuVitre vitre;
    private ButtonActor quitter;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.loadContext("menu");
        addActor(new ImageActor(manager, "background/fond.bmp"));
        options = putActor(new MenuOptions(this, manager));
        background = putActor(new MenuBackground(manager));
        buttons = putActor(new MenuButtons(manager));
        putActor(vitre = new MenuVitre(this, manager));

        putActor(quitter = new ButtonActor(manager, "simple-button/quitter.png"))
            .move(10, 10, bottom | right);
    }

    @Override
    public void makeEvents() {
        buttons.makeEvents(this);
        options.makeEvents();
        vitre.makeEvents();
        quitter.onClick((x, y, button, pointer) -> Gdx.app.exit());
    }

    @Override
    public void afterAct(float delta) {
    }

    @Override
    public boolean refresh() {
        options.refresh();
        buttons.refresh();
        vitre.refresh();
        return true;
    }

    /**
     * Change l'ecran de fond
     * @param subscreen
     */
    public boolean switchTo(final Screens subscreen) {
        buttons.setTouchable(Touchable.disabled);
        options.setTouchable(Touchable.disabled);
        vitre.setTouchable(Touchable.disabled);

        switch (subscreen) {
            case MENU:
                buttons.refresh();
                addActions(background.close(), buttons.appair(), Actions.run(() -> {
                    options.setVisible(false);
                    vitre.setVisible(false);
                }));
                break;
            case PLAY:
                vitre.setVisible(true);
                addActions(buttons.disappair(),vitre.appair(), Actions.run(() -> vitre.setTouchable(Touchable.childrenOnly)));
                break;
            case OPTIONS:
                options.setVisible(true);
                addActions(buttons.disappair(),background.open(), Actions.run(() -> options.setTouchable(Touchable.childrenOnly)));
                break;
            case CREDITS:
                break;
            default:
        }
            return true;
    }

    public enum Screens {
        MENU, PLAY, OPTIONS, CREDITS
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.debug("EXIT", "DISPOSE");
    }
}
