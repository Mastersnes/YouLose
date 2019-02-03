package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.ImageActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.utils.Align.*;

/**
 * Represente une entitée pouvant etre survolé
 */
public interface Hoverable {
    default void addHover(final String image) {
        setHover(image);
        final ImageActor hover = getHover();
        getMe().addActor(hover);
        hover.setTouchable(Touchable.disabled);
        hover.setAlpha(0);

        getMe().addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (getMe().isTouchable() && pointer != -1) return;
                hover.addAction(fadeIn(1));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (getMe().isTouchable() && pointer != -1) return;
                hover.addAction(fadeOut(1));
            }
        });
    }

    /**
     * Renvoi le groupe sur lequel s'applique l'interface
     * @return
     */
    Group getMe();

    /**
     * Renvoi l'instance qui hebergera le hover
     * @return
     */
    ImageActor getHover();
    void setHover(final String image);
}