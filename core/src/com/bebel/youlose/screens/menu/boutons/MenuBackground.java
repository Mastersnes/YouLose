package com.bebel.youlose.screens.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.actions.FinishAnimAction;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;
import static com.badlogic.gdx.math.Interpolation.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.bebel.youlose.components.actions.Actions.*;

/**
 * Fond du menu composé des deux portions de la porte
 */
public class MenuBackground extends AbstractGroup {
    private final AnimatedActor animationCote;
    private final AnimatedActor animationCentre;

    private ImageActor haut;
    private ImageActor bas;
    private boolean close;

    public MenuBackground() {
        super();
        setTouchable(Touchable.disabled);
        bas = putActor(new ImageActor("background/atlas:porte_bas"));

        haut = putActor(new ImageActor("background/atlas:porte_haut"))
                .move(0, 0);

        putActor(animationCote = new AnimatedActor("cote/", NORMAL, 24f))
                .move(0, 0);
        putActor(animationCentre = new AnimatedActor("centre/", NORMAL, 24f))
                .move(0, 0);
        close = true;
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
}
