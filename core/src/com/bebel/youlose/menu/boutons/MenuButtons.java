package com.bebel.youlose.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.ButtonActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.math.Interpolation.slowFast;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.components.actions.Actions.finishRun;

public class MenuButtons extends AbstractGroup {
    private ButtonActor play;
    private ButtonActor options;
    private ButtonActor credits;
    private float move_duration = 3;

    public MenuButtons(final AssetsManager manager) {
        super(manager);
        manager.setContext("menu");
        play = putActor(new ButtonActor(manager, "text-button/play.png"));
        play.addHover("text-button/play_hover.png");
        play.move(-play.getWidth(), 55);

        options = putActor(new ButtonActor(manager, "text-button/options.png"));
        options.addHover("text-button/options_hover.png");
        options.move(-options.getWidth(), 283, top | right);

        credits = putActor(new ButtonActor(manager, "text-button/credits.png"));
        credits.addHover("text-button/credits_hover.png");
        credits.setPosition(-credits.getWidth(), 30, bottom | left);

        addAction(appair());
    }

    @Override
    public boolean refresh() {
        play.refresh();
        options.refresh();
        credits.refresh();
        return true;
    }


    public FinishRunnableAction appair() {
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                play.addActionBloc(Actions.moveBy(play.getWidth(), 0, move_duration, fastSlow));
                options.addActionBloc(Actions.moveBy(-options.getWidth(), 0, move_duration, fastSlow));
                credits.addActionBloc(sequence(
                        Actions.moveBy(credits.getWidth(), 0, move_duration, fastSlow),
                        Actions.run(()->setTouchable(Touchable.childrenOnly)),
                        finish()
                ));
            }
        });
    }

    public FinishRunnableAction disappair() {
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                play.addActionBloc(Actions.moveBy(-play.getWidth(), 0, move_duration, slowFast));
                options.addActionBloc(Actions.moveBy(options.getWidth(), 0, move_duration, slowFast));
                credits.addActionBloc(sequence(
                        Actions.moveBy(-credits.getWidth(), 0, move_duration, slowFast),
                        finish()
                ));
            }
        });
    }

    public void makeEvents(final MenuScreen parent) {
        play.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.PLAY));
        options.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.OPTIONS));
        credits.onClick((x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.CREDITS));
    }
}
