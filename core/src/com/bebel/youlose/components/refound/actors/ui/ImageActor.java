package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractActor;

/**
 * Un acteur uniquement composé d'une image
 * Base des acteurs graphiques
 */
public class ImageActor extends AbstractActor implements Refreshable {
    protected Drawable image;

    public ImageActor(final String image) {
        super();
        setName(image);
        refresh(getColor());
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        draw(image, batch, parentAlpha);
    }

    protected void draw(final Drawable drawable, final Batch batch, final float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (drawable instanceof SpriteDrawable)
            ((SpriteDrawable)drawable).draw(batch, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        if (drawable instanceof TextureRegionDrawable)
            ((TextureRegionDrawable)drawable).draw(batch, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void refresh(final Color color) {
        setColor(color);
        setImage(getName());
        setSize(image.getMinWidth(), image.getMinHeight());
    }

    protected void setImage(final String image) {
        setImage(manager.getDrawable(image));
    }

    protected void setImage(final Drawable image) {
        this.image = image;
    }
}