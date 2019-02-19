package com.bebel.youlose.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.interfaces.event.ClickEvent;

/**
 * Outils pour les evenements
 */
public class EventUtils {
    public synchronized static  void onClick(final Actor actor, final ClickEvent run) {
        actor.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                run.run(x, y, pointer, button);
                return true;
            }
        });
    }
}
