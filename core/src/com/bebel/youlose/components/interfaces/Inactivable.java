package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Represente une entitée pouvant etre inactivée
 */
public interface Inactivable {
    default void addInactive(final String image, final AssetsManager manager) {
        final SpriteActor inactive = getMe().putActor(new SpriteActor(image, manager));
        inactive.setTouchable(Touchable.disabled);

        getMe().addAction(forever(run(() -> {
            if (getMe().isTouchable())
                inactive.setVisible(false);
            else
                inactive.setVisible(true);
        })));
    }

    /**
     * Renvoi le groupe sur lequel s'applique l'interface
     * @return
     */
    AbstractGroup getMe();
}