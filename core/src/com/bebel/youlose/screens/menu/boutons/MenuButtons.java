package com.bebel.youlose.screens.menu.boutons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.math.Interpolation.slowFast;
import static com.badlogic.gdx.utils.Align.bottomLeft;
import static com.badlogic.gdx.utils.Align.topRight;
import static com.bebel.youlose.components.actions.Actions.finishRun;

/**
 * Sous ecran des boutons principaux du menu "Play", "Options", "Creditsé"
 */
public class MenuButtons extends AbstractGroup {
    private final MenuScreen parent;

    private ButtonActor play;
    private ButtonActor options;
    private ButtonActor credits;
    private float move_duration = 3;

    public MenuButtons(final MenuScreen parent) {
        super();
        this.parent = parent;
        manager.setContext("menu");
        play = putActor(new ButtonActor("options/buttons:play"));
        play.addHover("options/buttons:play_hover");
        play.move(-play.getWidth(), 55);

        options = putActor(new ButtonActor("options/options:options"));
        options.addHover("options/options:options_hover");
        options.move(-options.getWidth(), 283, topRight);

        credits = putActor(new ButtonActor("options/buttons:credits"));
        credits.addHover("options/buttons:credits_hover");
        credits.move(-credits.getWidth(), 30, bottomLeft);
    }

    @Override
    public void refresh(final Color color) {
        play.refresh(color);
        options.refresh(color);
        credits.refresh(color);
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
    public void makeEvents() {
        play.onClick((x, y, pointer, button) -> {
            options.stop(); credits.stop();
            parent.switchTo(MenuScreen.Screens.PLAY);
        });
        options.onClick((x, y, pointer, button) -> {
            play.stop(); credits.stop();
            parent.switchTo(MenuScreen.Screens.OPTIONS);
        });
        credits.onClick((x, y, pointer, button) -> {
            play.stop(); options.stop();
            parent.switchTo(MenuScreen.Screens.PLAY);
            parent.switchTo(MenuScreen.Screens.SLOT);
        });
    }
}
