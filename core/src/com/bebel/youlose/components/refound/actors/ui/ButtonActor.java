package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.manager.resources.AssetsManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

/**
 * Acteur representant un bouton
 */
public class ButtonActor extends AbstractGroup implements Refreshable {
    private final ClickCatcher clickCatcher;

    private final ImageActor button;
    private ImageActor hover;
    private String upImage; private Drawable up;
    private String downImage; private Drawable down;

    private boolean disable = false;
    private String disabledImage; private Drawable disabled;

    public ButtonActor(final AssetsManager manager, final String image) {
        super(manager);
        upImage = image;
        addActor(button = new ImageActor(manager, image));
        addListener(clickCatcher = new ClickCatcher());
        refresh();
    }

    public ButtonActor addHover(final String image) {
        addActor(hover = new ImageActor(manager, image));
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
        disabled = manager.getDrawable(image);
        return this;
    }

    public ButtonActor addDown(final String image) {
        downImage = image;
        down = manager.getDrawable(image);
        return this;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        refreshButton();
        super.draw(batch, parentAlpha);
    }

    @Override
    public void refresh() {
        if (hover != null) hover.refresh();

        up = manager.getDrawable(upImage);
        if (downImage != null)
            down = manager.getDrawable(downImage);
        if (disabledImage != null)
            disabled = manager.getDrawable(disabledImage);
        else
            disabled = ((TextureRegionDrawable) up).tint(Color.GRAY);

        button.refresh();

        refreshButton();
    }

    private void refreshButton() {
        Drawable drawable;
        if (hover != null) hover.setVisible(true);

        if (disable) {
            if (hover != null) hover.setVisible(false);
            drawable = disabled;
        }
        else if (clickCatcher.isPressed() && down != null) {
            if (hover != null) hover.setVisible(false);
            drawable = down;
        } else drawable = up;

        button.setImage(drawable);
        setBounds(getX(), getY(), button.getWidth(), button.getHeight());
    }

    //-- Getter setter
    public boolean isDisable() {
        return disable;
    }
    public void setDisable(final boolean disable) {
        this.disable = disable;
    }
}