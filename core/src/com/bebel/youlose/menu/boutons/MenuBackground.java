package com.bebel.youlose.menu.boutons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.resources.AssetsManager;

import static com.badlogic.gdx.math.Interpolation.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.bebel.youlose.components.actions.Actions.finishRun;

/**
 * Fond du menu composé des deux portions de la porte
 */
public class MenuBackground extends AbstractGroup {
    private ImageActor haut;
    private ImageActor bas;

    public MenuBackground(final AssetsManager manager) {
        super(manager);
        manager.setContext("menu");
        setTouchable(Touchable.disabled);
        bas = putActor(new ImageActor(manager, "background/porte_bas.png"));

        haut = putActor(new ImageActor(manager, "background/porte_haut.png"));
        haut.move(0, 0);

    }

    /**
     * Ouvre la porte
     * @return
     */
    public FinishRunnableAction open() {
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
                                finish()
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
        stop();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                haut.addAction(Actions.moveBy(0, -haut.getHeight() + 150, 3, linear));
                bas.addAction(sequence(
                        Actions.moveBy(0, bas.getHeight() - 250, 3, slowFast),
                        finish()
                ));
            }
        });
    }
}
