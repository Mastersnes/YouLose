package com.bebel.youlose.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ButtonActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.math.Interpolation.slowFast;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.components.actions.Actions.finishRun;
import static com.bebel.youlose.utils.ActorUtils.*;
import static com.bebel.youlose.utils.EventUtils.onClick;

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
        move(play, -play.getWidth(), 55);

        options = putActor(new ButtonActor(manager, "text-button/options.png"));
        options.addHover("text-button/options_hover.png");
        move(options, -options.getWidth(), 283, top | right);

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
        stop(this);
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addBlockedActions(play, Actions.moveBy(play.getWidth(), 0, move_duration, fastSlow));
                addBlockedActions(options, Actions.moveBy(-options.getWidth(), 0, move_duration, fastSlow));
                addBlockedActions(credits,
                    Actions.moveBy(credits.getWidth(), 0, move_duration, fastSlow),
                    Actions.run(()->setTouchable(Touchable.childrenOnly)),
                    finish()
                );
            }
        });
    }

    public FinishRunnableAction disappair() {
        stop(this);
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addBlockedActions(play, Actions.moveBy(-play.getWidth(), 0, move_duration, slowFast));
                addBlockedActions(options, Actions.moveBy(options.getWidth(), 0, move_duration, slowFast));
                addBlockedActions(credits,
                    Actions.moveBy(-credits.getWidth(), 0, move_duration, slowFast),
                    finish()
                );
            }
        });
    }

    public void makeEvents(final MenuScreen parent) {
        onClick(play, (x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.PLAY));
        onClick(options, (x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.OPTIONS));
        onClick(credits, (x, y, pointer, button) -> parent.switchTo(MenuScreen.Screens.CREDITS));
    }
}
