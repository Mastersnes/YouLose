package com.bebel.youlose.components.refound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Pool;
import com.bebel.youlose.components.interfaces.*;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Abstraction de bouton
 */
public class ButtonActor extends Button implements Movable, Actionnable, Refreshable, Hoverable, Clickable {
    private AssetsManager manager;
    private ImageActor hover;

    /**
     * Constructeur
     * @param manager
     * @param image
     */
    public ButtonActor(final AssetsManager manager, final String image) {
        super(new ButtonStyle(manager.getDrawable(image), null, null));
        getStyle().disabled = ((TextureRegionDrawable)getStyle().up).tint(Color.GRAY);
        setName(image);
        this.manager = manager;
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

    @Override
    public Group getMe() {
        return this;
    }

    @Override
    public ImageActor getHover() {
        return hover;
    }

    @Override
    public void setHover(final String image) {
        hover = new ImageActor(manager, image);
    }
}