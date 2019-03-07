package com.bebel.youlose.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.manager.resources.ScreensManager;
import com.bebel.youlose.manager.save.SaveInstance;
import com.bebel.youlose.manager.save.SaveManager;
import com.bebel.youlose.screens.enigme1.Enigme1;
import com.bebel.youlose.screens.menu.boutons.MenuBackground;
import com.bebel.youlose.screens.menu.boutons.MenuButtons;
import com.bebel.youlose.screens.menu.options.MenuOptions;
import com.bebel.youlose.screens.menu.slots.MenuSlots;
import com.bebel.youlose.screens.menu.vitre.MenuVitre;

import static com.badlogic.gdx.utils.Align.bottomRight;
import static com.bebel.youlose.utils.ActorUtils.addActions;
import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Ecran de menu permettant de switcher vers les differents sous ecrans
 */
public class MenuScreen extends AbstractScreen {
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

        putActor(options = new MenuOptions(this));
        putActor(slots = new MenuSlots(this));
        putActor(background = new MenuBackground());
        putActor(buttons = new MenuButtons(this));
        putActor(vitre = new MenuVitre(this));

        putActor(quitter = new ButtonActor("general/quitter.png"));
        move(quitter, 10, 10, bottomRight);
        switchTo(Screens.MENU);
    }

    @Override
    public void makeEvents() {
        buttons.makeEvents();
        options.makeEvents();
        vitre.makeEvents();
        slots.makeEvents();

        quitter.onClick((x, y, button, pointer) -> Gdx.app.exit());
    }

    public MenuBackground getBackground() {
        return background;
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
                buttons.refresh(buttons.getColor());
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

    public void launchGame(final SaveInstance save) {
        SaveManager.getInstance().setCurrent(save);

        if (!save.isUsed()) {
            save.setEmplacement(Enigme1.NAME);
            save.setUsed(true);
        }
        ScreensManager.getInstance().switchTo(save.getEmplacement());

    }


    public enum Screens {
        MENU, PLAY, OPTIONS, CREDITS, SLOT
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.unloadAll();
        Gdx.app.debug("MenuScreen", "DISPOSE");
    }
}
