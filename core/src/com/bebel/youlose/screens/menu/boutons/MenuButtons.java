package com.bebel.youlose.screens.menu.boutons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.screens.menu.MenuScreen;
import com.bebel.youlose.screens.menu.MenuSubscreen;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.math.Interpolation.slowFast;
import static com.badlogic.gdx.utils.Align.bottomLeft;
import static com.badlogic.gdx.utils.Align.topRight;
import static com.bebel.youlose.components.actions.Actions.finishRun;

/**
 * Sous ecran des boutons principaux du menu "Play", "Options", "Credits"
 */
public class MenuButtons extends MenuSubscreen {
    private ButtonActor play;
    private ButtonActor options;
    private ButtonActor credits;
    private float move_duration = 3;

    public MenuButtons(final MenuScreen parent) {
        super(parent);
    }

    @Override
    public void create() {
        play = putActor(new ButtonActor("options/buttons:play"));
        play.addHover("options/buttons:play_hover");

        options = putActor(new ButtonActor("options/options:options"));
        options.addHover("options/options:options_hover");

        credits = putActor(new ButtonActor("options/buttons:credits"));
        credits.addHover("options/buttons:credits_hover");
    }

    @Override
    public void startSubscreen() {
        play.move(-play.getWidth(), 55);
        options.move(-options.getWidth(), 283, topRight);
        credits.move(-credits.getWidth(), 30, bottomLeft);
        refresh(getColor());
    }

    /**
     * Fait apparaitre es boutons
     *
     * @return
     */
    public FinishRunnableAction appair() {
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                play.addBlockedActions(Actions.moveBy(play.getWidth(), 0, move_duration, fastSlow));
                options.addBlockedActions(Actions.moveBy(-options.getWidth(), 0, move_duration, fastSlow));
                credits.addBlockedActions(
                        Actions.moveBy(credits.getWidth(), 0, move_duration, fastSlow),
                        Actions.run(() -> setTouchable(Touchable.childrenOnly)),
                        finish()
                );
            }
        });
    }

    /**
     * Fais disparraitre les boutons
     *
     * @return
     */
    public FinishRunnableAction disappair() {
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                play.addBlockedActions(Actions.moveBy(-play.getWidth(), 0, move_duration, slowFast));
                options.addBlockedActions(Actions.moveBy(options.getWidth(), 0, move_duration, slowFast));
                credits.addBlockedActions(
                        Actions.moveBy(-credits.getWidth(), 0, move_duration, slowFast),
                        finish()
                );
            }
        });
    }


    @Override
    public void makeSpecificEvents() {
        play.onClick((x, y, pointer, button) -> {
            options.stop(); credits.stop();
            screen.switchTo(MenuScreen.Screens.PLAY);
        });
        options.onClick((x, y, pointer, button) -> {
            play.stop(); credits.stop();
            screen.switchTo(MenuScreen.Screens.OPTIONS);
        });
        credits.onClick((x, y, pointer, button) -> {
            play.stop(); options.stop();
            screen.switchTo(MenuScreen.Screens.PLAY);
            screen.switchTo(MenuScreen.Screens.SLOT);
        });
    }
}
