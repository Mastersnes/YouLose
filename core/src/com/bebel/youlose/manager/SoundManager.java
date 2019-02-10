package com.bebel.youlose.manager;

import com.badlogic.gdx.audio.Sound;

/**
 * Manager de son
 */
public class SoundManager extends AbstractSubManager<Sound> {
    public SoundManager(final AssetsManager manager) {
        super(manager);
    }

    @Override
    protected String getPath(final String language) {
        final StringBuilder path = new StringBuilder("sounds/");
        path.append(parent.context).append("/");
        return path.toString();
    }

    @Override
    protected Class<Sound> getType() {
        return Sound.class;
    }


    @Override
    public void dispose() {

    }
}
