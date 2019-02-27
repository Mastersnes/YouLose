package com.bebel.youlose.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

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

    public MenuButtons(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        manager.setContext("menu");
        play = putActor(new ButtonActor(manager, "options/buttons:play"));
        play.addHover("options/buttons:play_hover");
        play.move(-play.getWidth(), 55);

        options = putActor(new ButtonActor(manager, "options/options:options"));
        options.addHover("options/options:options_hover");
        options.move(-options.getWidth(), 283, topRight);

        credits = putActor(new ButtonActor(manager, "options/buttons:credits"));
        credits.addHover("options/buttons:credits_hover");
        credits.move(-credits.getWidth(), 30, bottomLeft);

        addAction(appair());
    }

    @Override
    public void refresh() {
        play.refresh();
        options.refresh();
        credits.refresh();
    }

    /**
     * Fait apparaitre es boutons
     *
     * @return
     */
    public FinishRunnableAction appair() {
        stop();
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
        stop();
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
        play.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.PLAY));
        options.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.OPTIONS));
        credits.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.CREDITS));
    }
}
