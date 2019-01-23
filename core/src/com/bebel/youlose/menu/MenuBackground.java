package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.abstrait.SpriteActor;
import com.bebel.youlose.components.abstrait.AbstractGroup;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.math.Interpolation.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;

public class MenuBackground extends AbstractGroup {
    private SpriteActor haut;
    private SpriteActor bas;

    public MenuBackground(final AssetsManager manager) {
        addActor(new SpriteActor("fond.bmp", manager));

        haut = addActor(new SpriteActor("porte_haut.png", manager));
        haut.setY(WORLD_HEIGHT - haut.getHeight());

        bas = addActor(new SpriteActor("porte_bas.png", manager));
    }

    public void open() {
        haut.addAction(
                sequence(
                        Actions.moveBy(0, haut.getHeight() - 140, 3, linear),
                        Actions.moveBy(0, -10, 1, slowFast)
                )
        );
        bas.addAction(sequence(
                Actions.moveBy(0, -bas.getHeight() + 249, 3, fastSlow),
                Actions.moveBy(0,  1, 1, elasticOut)
        ));
    }
}
