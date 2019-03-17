package com.bebel.youlose.screens.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.actions.FinishAnimAction;
import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.screens.menu.MenuScreen;
import com.bebel.youlose.screens.menu.MenuSubscreen;
import com.bebel.youlose.utils.IActor;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;
import static com.badlogic.gdx.math.Interpolation.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.utils.Align.bottomLeft;
import static com.badlogic.gdx.utils.Align.topLeft;
import static com.bebel.youlose.components.actions.Actions.*;

/**
 * Fond du menu composé des deux portions de la porte
 */
public class MenuBackground extends MenuSubscreen {
    private AnimatedActor animationCote;
    private AnimatedActor animationCentre;

    private ImageActor haut;
    private ImageActor bas;
    private boolean close;

    public MenuBackground(final MenuScreen parent) {
        super(parent);
    }

    @Override
    public void create() {
        setTouchable(Touchable.disabled);
        bas = putActor(new ImageActor("background/atlas:porte_bas"));
        haut = putActor(new ImageActor("background/atlas:porte_haut"));

        putActor(animationCote = new AnimatedActor("cote/", NORMAL, 24f));
        putActor(animationCentre = new AnimatedActor("centre/", NORMAL, 24f));
    }


    @Override
    public void startSubscreen() {
        close = true;
        bas.move(0, 0, bottomLeft);
        haut.move(0, 0, topLeft);
        animationCote.move(0, 0, topLeft);
        animationCentre.move(0, 0, topLeft);
    }

    /**
     * Ouvre la porte
     * @return
     */
    public FinishRunnableAction open() {
        if (!close) return emptyRun();
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                haut.addAction(
                        sequence(
                                Actions.moveBy(0, haut.getHeight() - 140, 3, linear),
                                Actions.moveBy(0, -10, 1, slowFast)
                        )
                );
                bas.addAction(
                        sequence(
                                Actions.moveBy(0, -bas.getHeight() + 249, 3, fastSlow),
                                Actions.moveBy(0, 1, 1, elasticOut),
                                finish(() -> close = false)
                        )
                );
            }
        });
    }

    /**
     * Ferme la porte
     * @return
     */
    public FinishRunnableAction close() {
        if (close) return emptyRun();
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                haut.addAction(Actions.moveBy(0, -haut.getHeight() + 150, 3, linear));
                bas.addAction(sequence(
                        Actions.moveBy(0, bas.getHeight() - 250, 3, slowFast),
                        finish(() -> close = true)
                ));
            }
        });
    }

    /**
     * Joue l'animation
     * @return
     */
    public FinishAnimAction play(final AnimatedActor animation, final boolean flipH) {
        if (animationCote.isWorking() || animationCentre.isWorking()) return emptyAnim();
        return finishAnim(animation, flipH, false);
    }

    public AnimatedActor getAnimationCote() {
        return animationCote;
    }

    public AnimatedActor getAnimationCentre() {
        return animationCentre;
    }

    public boolean isClose() {
        return close;
    }

    @Override
    public void makeSpecificEvents() {
    }
}
