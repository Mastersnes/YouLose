package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Represente une entitée pouvant etre hover
 */
public interface Hoverable {
    default void addHover(final String image) {
        setHover(image);
        final SpriteActor hover = getMe().putActor(getHover());
        hover.setTouchable(Touchable.disabled);
        hover.setAlpha(0);

        getMe().addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer != -1) return;
                hover.addAction(fadeIn(1));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (pointer != -1) return;
                hover.addAction(fadeOut(1));
            }
        });
    }

    /**
     * Renvoi le groupe sur lequel s'applique l'interface
     * @return
     */
    AbstractGroup getMe();

    /**
     * Renvoi l'instance qui hebergera le hover
     * @return
     */
    SpriteActor getHover();
    void setHover(final String image);
}