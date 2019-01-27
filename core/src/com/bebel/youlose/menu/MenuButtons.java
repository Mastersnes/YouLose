package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.ButtonActor;
import com.bebel.youlose.components.abstrait.actions.Actions;
import com.bebel.youlose.components.abstrait.actions.FinishRunnable;
import com.bebel.youlose.components.abstrait.actions.FinishRunnableAction;
import com.bebel.youlose.components.abstrait.actors.AbstractGroup;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.math.Interpolation.slowFast;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.components.abstrait.actions.Actions.finishRun;

public class MenuButtons extends AbstractGroup {
    private ButtonActor play;
    private ButtonActor options;
    private ButtonActor credits;
    private float move_duration = 3;

    public MenuButtons(final AssetsManager manager) {
        manager.setContext("menu");
        play = new ButtonActor("play.png", "play_hover.png", manager);
        play.move(-play.getWidth(), 55);
        addActor(play);

        options = new ButtonActor("options.png", "options_hover.png", manager);
        options.move(-options.getWidth(), 283, top | right);
        addActor(options);

        credits = new ButtonActor("credits.png", "credits_hover.png", manager);
        credits.setPosition(-credits.getWidth(), 30, bottom | left);
        addActor(credits);

        addAction(appair());
    }


    public FinishRunnableAction appair() {
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                play.addActionBloc(Actions.moveBy(play.getWidth(), 0, move_duration, fastSlow));
                options.addActionBloc(Actions.moveBy(-options.getWidth(), 0, move_duration, fastSlow));
                credits.addActionBloc(sequence(
                        Actions.moveBy(credits.getWidth(), 0, move_duration, fastSlow),
                        finish()
                ));
            }
        });
    }

    public FinishRunnableAction disappair() {
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
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return parent.switchTo("play");
            }
        });

        options.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return parent.switchTo("options");
            }
        });

        credits.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return parent.switchTo("credits");
            }
        });
    }
}
