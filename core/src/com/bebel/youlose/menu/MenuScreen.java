package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.utils.AbstractStage;

public class MenuScreen extends AbstractStage implements Actionnable {
    private MenuBackground background;
    private MenuOptions options;
    private MenuButtons buttons;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.load("menu");
        addActor(new SpriteActor("fond.bmp", manager));
        options = putActor(new MenuOptions(this, manager));
        background = putActor(new MenuBackground(manager));
        buttons = putActor(new MenuButtons(manager));
    }

    @Override
    public void makeEvents() {
        buttons.makeEvents(this);
        options.makeEvents();
    }

    @Override
    public void afterAct(float delta) {
    }

    @Override
    public boolean refresh() {
        options.refresh();
        buttons.refresh();
        return true;
    }

    /**
     * Change l'ecran de fond
     * @param subscreen
     */
    public boolean switchTo(final Screens subscreen) {
        buttons.setTouchable(Touchable.disabled);
        options.setTouchable(Touchable.disabled);

        switch (subscreen) {
            case MENU:
                buttons.refresh();
                addActions(background.close(), buttons.appair(), Actions.run(() -> options.setVisible(false)));
                break;
            case PLAY:
                break;
            case OPTIONS:
                options.setVisible(true);
                options.setTouchable(Touchable.disabled);
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
}
