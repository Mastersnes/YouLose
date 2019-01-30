package com.bebel.youlose.components.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bebel.youlose.components.interfaces.Hoverable;
import com.bebel.youlose.components.interfaces.Inactivable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Acteur se comportant comme un bouton
 */
public class ButtonActor extends AbstractGroup implements Hoverable, Inactivable {
    protected String simpleImage;
    protected SpriteActor simple;
    protected String hoverImage;
    protected SpriteActor hover;

    public ButtonActor(final String image, final AssetsManager manager) {
        super(manager);
        this.simpleImage = image;
        simple = putActor(new SpriteActor(image, manager));
        setBounds(0, 0, simple.getWidth(), simple.getHeight());
    }

    /**
     * Ajoute une action durant laquel l'interaction est interdite
     * @param action
     */
    public void addActionBloc(final Action action) {
        final ButtonActor that = this;
        this.setTouchable(Touchable.disabled);
        final SequenceAction sequence = new SequenceAction(action);
        sequence.addAction(Actions.run(() -> that.setTouchable(Touchable.enabled)));
        super.addAction(sequence);
    }

    @Override
    public AbstractGroup getMe() {
        return this;
    }

    @Override
    public SpriteActor getHover() {
        return hover;
    }

    @Override
    public void setHover(final String image) {
        this.hoverImage = image;
        this.hover = new SpriteActor(image, manager);
    }

    @Override
    public boolean refresh() {
        simple.setDrawable(new TextureRegionDrawable(manager.getTexture(simpleImage)));
        if (hover != null)
            hover.setDrawable(new TextureRegionDrawable(manager.getTexture(hoverImage)));
        return false;
    }
}