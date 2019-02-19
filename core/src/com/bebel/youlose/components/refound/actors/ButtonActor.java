package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Abstraction de bouton
 */
public class ButtonActor extends Button implements Refreshable {
    private AssetsManager manager;
    private ImageActor hover;

    /**
     * Constructeur
     *
     * @param manager
     * @param image
     */
    public ButtonActor(final AssetsManager manager, final String image) {
        super(new ButtonStyle(manager.getDrawable(image), null, null));
        getStyle().disabled = ((TextureRegionDrawable) getStyle().up).tint(Color.GRAY);
        setName(image);
        this.manager = manager;
    }

    public void addHover(final String image) {
        addActor(hover = new ImageActor(manager, image));
        hover.setTouchable(Touchable.disabled);
        hover.setAlpha(0);

        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (isTouchable() && pointer != -1) return;
                hover.addAction(fadeIn(1));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (isTouchable() && pointer != -1) return;
                hover.addAction(fadeOut(1));
            }
        });
    }

    @Override
    public boolean refresh() {
        getStyle().up = manager.getDrawable(getName());
        if (hover != null)
            hover.refresh();
        return true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}