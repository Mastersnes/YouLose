package com.bebel.youlose.screens.menu;

import com.badlogic.gdx.Gdx;
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
import com.bebel.youlose.utils.ActorUtils;

import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.utils.Align.bottomRight;
import static com.bebel.youlose.utils.ActorUtils.addActions;
import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Ecran de menu permettant de switcher vers les differents sous ecrans
 */
public class MenuScreen extends AbstractScreen {
    public static final String NAME = "menu";

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
            case MENU: // Des options ou des credits au menu
                ActorUtils.hide(vitre, slots);
                buttons.refresh(buttons.getColor());
                addActions(getRoot(), background.close(), buttons.appair(), Actions.run(() -> {
                    options.setVisible(false);
                }));
                break;
            case PLAY: // Du menu à la vitre
                ActorUtils.hide(options, slots);
                vitre.setVisible(true);
                addActions(getRoot(), buttons.disappair(), vitre.appair(), Actions.run(() -> vitre.setTouchable(Touchable.childrenOnly)));
                break;
            case OPTIONS: // Du menu aux options
                ActorUtils.hide(vitre, slots);
                options.setVisible(true);
                addActions(getRoot(), buttons.disappair(), background.open(), Actions.run(() -> options.setTouchable(Touchable.childrenOnly)));
                break;
            case CREDITS: // Du menu au credits
                break;
            case SLOT: // De la vitre aux slots
                ActorUtils.hide(options);
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
            save.save();
        }
        ScreensManager.getInstance().switchTo(save.getEmplacement());
    }

    public enum Screens {
        MENU, PLAY, OPTIONS, CREDITS, SLOT
    }

    @Override
    protected String context() {
        return NAME;
    }
    @Override
    protected List<String> nextScreens() {
        return Arrays.asList(Enigme1.NAME);
    }
}
