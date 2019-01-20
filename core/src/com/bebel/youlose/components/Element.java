package com.bebel.youlose.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Acteur contenant une image, une hitbox et captant les evenements
 */
public class Element extends Actor {
    protected Texture texture;
    private final String name;


    public Element(final String path, final String image) {
        final Element that = this;
        setTouchable(Touchable.enabled);
        name = path + image;
        texture = AssetsManager.getInstance().getTexture(path, image);
        setBounds(0, 0, texture.getWidth(), texture.getHeight());
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return that.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void init(final String path, final String image) {

    }

    private boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.debug("TOUCH", name);
        event.handle();
        return true;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }
}