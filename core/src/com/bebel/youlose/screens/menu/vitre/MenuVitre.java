package com.bebel.youlose.screens.menu.vitre;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.screens.menu.MenuScreen;
import com.bebel.youlose.screens.menu.MenuSubscreen;

import static com.badlogic.gdx.math.Interpolation.elastic;
import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.bebel.youlose.components.actions.Actions.finishRun;

/**
 * Ecran titre composé d'une vitre, du nom du jeu et d'un scan
 */
public class MenuVitre extends MenuSubscreen {
    private ImageActor led;
    private ImageActor vitre;
    private ImageActor texte;
    private MenuScan carre;

    private FinishRunnable clignotteAction;
    private int delayClignottement;
    private boolean stopClignotte;

    public MenuVitre(final MenuScreen parent) {
        super(parent);
    }

    @Override
    public void create() {
        putActor(led = new ImageActor("vitre/atlas:led"));
        putActor(vitre = new ImageActor("vitre/atlas:vitre"));
        putActor(texte = new ImageActor("vitre/atlas:youlose"));
        putActor(carre = new MenuScan(parent));
    }

    @Override
    public void startSubscreen() {
        setVisible(false);
        led.move(led.centerX(), 206);
        led.setAlpha(0);

        vitre.move(vitre.centerX(), 0);

        texte.setPosition(led.getX(), led.getY());
        texte.setAlpha(0);

        carre.setAlpha(0);
        carre.move(588, 427);

        setY(getHeight());
        refresh(getColor());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        checkClignotte();
    }

    /**
     * Verifie si le clignottement est en cours
     * et le relance aleatoirement sinon
     */
    private void checkClignotte() {
        if (stopClignotte) return;
        if (clignotteAction == null) return;
        if (!isVisible()) return;
        if (led.getAlpha() < 1) return;
        if (delayClignottement-- > 0) return;

        final float randNumber = MathUtils.random(0, 500);
        if (randNumber == 0 && clignotteAction.isFinish()) {
            delayClignottement = 5000;
            addActions(finishRun(clignotte()));
        }
    }

    /**
     * Fait apparaitre la vitre puis la fait clignotter
     *
     * @return
     */
    public FinishRunnableAction appair() {
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addBlockedActions(
                        Actions.moveBy(0, -getHeight(), 3, linear),
                        finishRun(clignotte()),
                        finish()
                );
            }
        });
    }

    /**
     * Fait clignotter l'ecran avant de l'allumer
     *
     * @return
     */
    private FinishRunnable clignotte() {
        if (clignotteAction == null) {
            clignotteAction = new FinishRunnable() {
                @Override
                public void run() {
                    led.addActions(
                            alpha(0),
                            fadeIn(1, elastic),
                            alpha(0),
                            alpha(1)
                    );
                    texte.addActions(
                            alpha(0),
                            fadeIn(1, elastic),
                            alpha(0),
                            alpha(1),
                            finish()
                    );
                    carre.addActions(
                            alpha(0),
                            fadeIn(1, elastic),
                            alpha(0),
                            alpha(1),
                            finish()
                    );
                }
            };
        } else if (clignotteAction.isFinish()) clignotteAction.restart();
        return clignotteAction;
    }

    /**
     * Eteinds l'ecran en clignotant
     *
     * @return
     */
    private FinishRunnable extinction() {
        return new FinishRunnable() {
            @Override
            public void run() {
                led.addActions(
                        fadeOut(1, elastic)
                );
                texte.addActions(
                        fadeOut(1, elastic)
                );
                carre.addActions(
                        fadeOut(1, elastic),
                        finish()
                );
            }
        };
    }

    /**
     * Eteinds l'ecran et enleve la vitre
     *
     * @return
     */
    public FinishRunnableAction disappair() {
        stop();
        stopClignotte = true;
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                addBlockedActions(
                        finishRun(extinction()),
                        Actions.moveBy(0, getHeight(), 3, linear),
                        finish()
                );
            }
        });
    }

    @Override
    public void makeSpecificEvents() {
    }
}
