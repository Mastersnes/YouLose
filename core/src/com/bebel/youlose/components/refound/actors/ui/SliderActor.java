package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

/**
 * Specification de l'acteur Slider
 */
public class SliderActor extends Slider implements IActor {
    public SliderActor(final String slide, final String cursor) {
        this(new SliderStyle(AssetsManager.getInstance().getDrawable(slide), AssetsManager.getInstance().getDrawable(cursor)));
    }

    public SliderActor(final SliderStyle style) {
        super(0, style.background.getMinWidth(), 1, false, style);
    }

    @Override
    public float getPrefWidth() {
        if (getStyle().background != null)
            return getStyle().background.getMinWidth();
        return super.getPrefWidth();
    }

    //-- ActorUtils
    @Override
    public <T extends Actor> T move(final float x, final float y) {
        return (T) ActorUtils.move(this, x, y);
    }
    @Override
    public <T extends Actor> T move(final float x, final float y, final int align) {
        return (T) ActorUtils.move(this, x, y, align);
    }
    @Override
    public <T extends Actor> T setAlpha(int alpha) {
        return (T) ActorUtils.setAlpha(this, alpha);
    }
    @Override
    public float getAlpha() {return ActorUtils.getAlpha(this);}
    @Override
    public boolean addActions(final Action... actions) {
        return ActorUtils.addActions(this, actions);
    }
    @Override
    public boolean addBlockedActions(final Action... actions) {
        return ActorUtils.addBlockedActions(this, actions);
    }
    @Override
    public void stop() {
        ActorUtils.stop(this);
    }
    @Override
    public float centerX() {
        return ActorUtils.centerX(this);
    }
    @Override
    public float centerY() {
        return ActorUtils.centerY(this);
    }
}