package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.draw.Sprite;
import com.bebel.youlose.components.refound.event.ClickCatcher;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Acteur representant un bouton
 */
public class ButtonActor extends AbstractGroup implements Refreshable {
    private final ClickCatcher clickCatcher;

    private final ImageActor button;
    private ImageActor hover;
    private String upImage; private Sprite up;
    private String downImage; private Sprite down;

    private boolean disable = false;
    private String disabledImage;
    private Sprite disabled;

    public ButtonActor(final String image) {
        super();
        upImage = image;
        addActor(button = new ImageActor(image));
        addListener(clickCatcher = new ClickCatcher());
        refresh(getColor());
    }

    public ButtonActor addHover(final String image) {
        addActor(hover = new ImageActor(image));
        hover.setTouchable(Touchable.disabled);
        hover.setAlpha(0);
        clickCatcher.enter((x, y, pointer, button) -> {
            if (isTouchable() && pointer != -1) return;
            hover.addAction(fadeIn(1));
        });
        clickCatcher.exit((x, y, pointer, button) -> {
            if (isTouchable() && pointer != -1) return;
            hover.addAction(fadeOut(1));
        });
        return this;
    }

    public ButtonActor onClick(final ClickCatcher.ClickEvent callback) {
        clickCatcher.touchDown(callback);
        return this;
    }

    public ButtonActor addDisable(final String image) {
        disabledImage = image;
        disabled = manager.getSprite(image, getColor());
        return this;
    }

    public ButtonActor addDown(final String image) {
        downImage = image;
        down = manager.getSprite(image, getColor());
        return this;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        refreshButton();
        super.draw(batch, parentAlpha);
    }

    @Override
    public void refresh(final Color color) {
        if (hover != null) {
            hover.refresh(color);
        }

        up = manager.getSprite(upImage, color);
        if (downImage != null)
            down = manager.getSprite(downImage, color);
        if (disabledImage != null)
            disabled = manager.getSprite(disabledImage, color);
        else {
            disabled = new Sprite(up);
            disabled.setColor(Color.GRAY);
        }

        button.refresh(color);

        refreshButton();
    }

    private void refreshButton() {
        Sprite sprite;
        if (hover != null) hover.setVisible(true);

        if (disable) {
            if (hover != null) hover.setVisible(false);
            sprite = disabled;
        }
        else if (clickCatcher.isPressed() && down != null) {
            if (hover != null) hover.setVisible(false);
            sprite = down;
        } else sprite = up;

        button.setImage(sprite);
        setBounds(getX(), getY(), button.getWidth(), button.getHeight());
    }

    @Override
    public void stop() {
        super.stop();
        if (hover != null) hover.setAlpha(0);
    }

    //-- Getter setter
    public boolean isDisable() {
        return disable;
    }
    public void setDisable(final boolean disable) {
        this.disable = disable;
    }

    @Override
    public void makeSpecificEvents() {
    }
}