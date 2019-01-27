package com.bebel.youlose.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.abstrait.actions.Actionnable;
import com.bebel.youlose.components.abstrait.actors.SpriteActor;
import com.bebel.youlose.utils.AbstractStage;
import com.bebel.youlose.utils.FontParameter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MenuScreen extends AbstractStage implements Actionnable {
    private MenuBackground background;
    private MenuOptions options;
    private MenuButtons buttons;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.load("menu/options");
        manager.load("menu");
        addActor(new SpriteActor("fond.bmp", manager));
        options = putActor(new MenuOptions(manager));
        background = putActor(new MenuBackground(manager));
        buttons = putActor(new MenuButtons(manager));

        addActions(buttons.disappair(), background.open());
    }

    @Override
    public void makeEvents() {
        buttons.makeEvents(this);
        options.makeEvents();
    }

    @Override
    public void afterAct(float delta) {
    }

    /**
     * Change l'ecran de fond
     * @param subscreen
     */
    public boolean switchTo(final String subscreen) {
        boolean open = true;
        switch (subscreen) {
            case "play":
                options.setVisible(false);
                break;
            case "options":
                options.setVisible(true);
                break;
            case "credits":
                options.setVisible(false);
                break;
            default:
                open = false;
        }
        if (open)
            return addActions(buttons.disappair(),background.open());
        else
            return addActions(background.close(), buttons.appair());
    }
}
