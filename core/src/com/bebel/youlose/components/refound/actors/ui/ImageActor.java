package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractActor;
import com.bebel.youlose.components.refound.draw.Sprite;

/**
 * Un acteur uniquement composé d'une image
 * Base des acteurs graphiques
 */
public class ImageActor extends AbstractActor implements Refreshable {
    protected Sprite image;

    public ImageActor(final String image) {
        super();
        create(image);
    }

    public void create(final String image) {
        setName(image);
        refresh(getColor());
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        super.draw(batch, parentAlpha);

        image.setBounds(getX(), getY(), getWidth(), getHeight());
        image.setOrigin(getOriginX(), getOriginY());
        image.setScale(getScaleX(), getScaleY());
        image.setRotation(getRotation());
        image.setAlpha(getAlpha());

        image.draw(batch, parentAlpha);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        image.mul(color);
    }

    @Override
    public void refresh(final Color color) {
        setImage(getName());
        setColor(color);
        setSize(image.getWidth(), image.getHeight());
    }

    protected void setImage(final String image) {
        setImage(manager.getSprite(image, getColor()));
    }

    protected void setImage(final Sprite image) {
        this.image = image;
    }

    public ImageActor flipH() {
        image.flip(!image.isFlipX(), image.isFlipY());
        return this;
    }

    public ImageActor flipV() {
        image.flip(image.isFlipX(), !image.isFlipY());
        return this;
    }
}