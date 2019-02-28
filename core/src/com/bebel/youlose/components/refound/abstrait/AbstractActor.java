package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template d'acteur
 */
public abstract class AbstractActor extends Actor implements IActor {
    protected final AssetsManager manager;

    public AbstractActor() {
        super();
        this.manager = AssetsManager.getInstance();
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        final Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
    }

    public AssetsManager getManager() {
        return manager;
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
    public float getAlpha() { return ActorUtils.getAlpha(this); }
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
