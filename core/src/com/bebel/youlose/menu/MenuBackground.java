package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.math.Interpolation.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.bebel.youlose.components.actions.Actions.finishRun;

public class MenuBackground extends AbstractGroup {
    private SpriteActor haut;
    private SpriteActor bas;

    public MenuBackground(final AssetsManager manager) {
        super(manager);
        manager.setContext("menu");
        setTouchable(Touchable.disabled);
        bas = putActor(new SpriteActor("porte_bas.png", manager));

        haut = putActor(new SpriteActor("porte_haut.png", manager));
        haut.move(0, 0);

    }

    @Override
    public boolean refresh() {
        return false;
    }

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
