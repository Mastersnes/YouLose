package com.bebel.youlose.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.menu.boutons.MenuBackground;
import com.bebel.youlose.menu.boutons.MenuButtons;
import com.bebel.youlose.menu.options.MenuOptions;
import com.bebel.youlose.menu.slots.MenuSlots;
import com.bebel.youlose.menu.vitre.MenuVitre;

import static com.badlogic.gdx.utils.Align.bottomRight;
import static com.bebel.youlose.utils.ActorUtils.addActions;
import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Ecran de menu permettant de switcher vers les differents sous ecrans
 */
public class MenuScreen extends AbstractScreen {
    private CircleShape circle;
    private MenuBackground background;
    private MenuOptions options;
    private MenuButtons buttons;
    private MenuVitre vitre;
    private MenuSlots slots;

    private ButtonActor quitter;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.loadContext("menu");

        addActor(new ImageActor(manager, "background/fond.bmp"));
        putActor(options = new MenuOptions(this, manager));
        putActor(background = new MenuBackground(manager));
        putActor(buttons = new MenuButtons(this, manager));
        putActor(vitre = new MenuVitre(this, manager));
        putActor(slots = new MenuSlots(this, manager));

        putActor(quitter = new ButtonActor(manager, "simple-button/quitter.png"));
        move(quitter, 10, 10, bottomRight);
    }

    @Override
    public void makeEvents() {
        buttons.makeEvents();
        options.makeEvents();
        vitre.makeEvents();
        slots.makeEvents();

        quitter.onClick((x, y, button, pointer) -> Gdx.app.exit());
    }

    /**
     * Change l'ecran de fond
     *
     * @param subscreen
     */
    public boolean switchTo(final Screens subscreen) {
        buttons.setTouchable(Touchable.disabled);
        options.setTouchable(Touchable.disabled);
        vitre.setTouchable(Touchable.disabled);
        slots.setTouchable(Touchable.disabled);

        switch (subscreen) {
            case MENU:
                buttons.refresh();
                addActions(getRoot(), background.close(), buttons.appair(), Actions.run(() -> {
                    options.setVisible(false);
                    vitre.setVisible(false);
                    slots.setVisible(false);
                }));
                break;
            case PLAY:
                vitre.setVisible(true);
                addActions(getRoot(), buttons.disappair(), vitre.appair(), Actions.run(() -> vitre.setTouchable(Touchable.childrenOnly)));
                break;
            case OPTIONS:
                options.setVisible(true);
                addActions(getRoot(), buttons.disappair(), background.open(), Actions.run(() -> options.setTouchable(Touchable.childrenOnly)));
                break;
            case CREDITS:
                break;
            case SLOT:
                slots.setVisible(true);
                addActions(getRoot(), vitre.disappair(), background.open(), Actions.run(() -> slots.setTouchable(Touchable.childrenOnly)));
                break;
            default:
        }
        return true;
    }

    public enum Screens {
        MENU, PLAY, OPTIONS, CREDITS, SLOT
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.debug("EXIT", "DISPOSE");
    }
}
