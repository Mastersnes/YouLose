package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Represente une entitée pouvant etre clické
 */
public interface Clickable {
    default void onClick(final EventAction run) {
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                run.run(x, y, pointer, button);
                return true;
            }
        });
    }

    boolean addListener(final EventListener clickListener);
}