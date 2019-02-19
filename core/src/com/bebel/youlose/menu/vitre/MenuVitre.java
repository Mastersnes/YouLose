package com.bebel.youlose.menu.vitre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ImageActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.math.Interpolation.swing;
import static com.bebel.youlose.components.actions.Actions.finishRun;
import static com.bebel.youlose.utils.ActorUtils.*;

public class MenuVitre extends AbstractGroup {
    private final MenuScreen parent;

    private final ImageActor led;
    private final ImageActor vitre;
    private final ImageActor texte;

    public MenuVitre(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        putActor(led = new ImageActor(manager, "vitre/led.png"));

        putActor(vitre = new ImageActor(manager, "vitre/vitre.png"));
        move(vitre, centerX(vitre), 0);

        putActor(texte = new ImageActor(manager, "vitre/youlose.png"));
        texte.setPosition(led.getX(), led.getY());

        toDebug = texte;

        setY(getHeight());
        refresh();
    }

    public FinishRunnableAction appair() {
        stop(this);
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addBlockedActions(MenuVitre.this,
                        Actions.moveBy(0, -getHeight(), 3, linear),

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
                finish();
            }
        });
    }

    @Override
    public boolean refresh() {
        return true;
    }
}
