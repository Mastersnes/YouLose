package com.bebel.youlose.menu.vitre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.ImageActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.elastic;
import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.bebel.youlose.components.actions.Actions.finishRun;

public class MenuVitre extends AbstractGroup implements Actionnable, Movable {
    private final MenuScreen parent;

    private boolean clignotte = false;
    private final ImageActor led;
    private final ImageActor vitre;
    private final ImageActor texte;
//    private final MenuScanner scan;

    public MenuVitre(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

//        addActor(new ImageActor(manager, "vitre/ref.png"));

        vitre = new ImageActor(manager, "vitre/vitre.png");

        putActor(led = new ImageActor(manager, "vitre/led.png"))
                .setAlpha(0);
        led.move(169, 206);

        putActor(vitre)
                .move(centerX(vitre), 0);

        putActor(texte = new ImageActor(manager, "vitre/youlose.png"))
                .setAlpha(0);
        texte.move(169, 206);

        setY(getHeight());

        refresh();
    }

    public FinishRunnableAction appair() {
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addActionBloc(
                        sequence(
                                Actions.moveBy(0, -getHeight(), 3, linear),
                                clignotte(),
                                finish()
                        )
                );
            }
        });
    }

    public RunnableAction clignotte() {
        return Actions.run(() -> {
            texte.addActions(fadeOut(0.5f), fadeIn(8f, elastic));
            led.addActions(fadeOut(0.5f), fadeIn(8f, elastic));
        });
    }

    public FinishRunnableAction disappair() {
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    public void makeEvents() {
        texte.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.debug("TEST", "CLICK");
                addAction(clignotte());
                return true;
            }
        });
    }

    @Override
    public boolean refresh() {
        return true;
    }
}
