package com.bebel.youlose.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.abstrait.AbstractGroup;
import com.bebel.youlose.components.abstrait.SpriteActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Acteur se comportant comme un bouton
 */
public class ButtonActor extends AbstractGroup {
    protected SpriteActor simple;
    protected SpriteActor hover;

    public ButtonActor(final String image, final String hoverImage, final AssetsManager manager) {
        final ButtonActor that = this;
        simple = addActor(new SpriteActor(image, manager));
        hover = addActor(new SpriteActor(hoverImage, manager));
        setBounds(0, 0, simple.getWidth(), simple.getHeight());
        hover.setTouchable(Touchable.disabled);
        hover.setAlpha(0);

        simple.addListener(new ClickListener() {
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
}