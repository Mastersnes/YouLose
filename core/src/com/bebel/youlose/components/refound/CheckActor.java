package com.bebel.youlose.components.refound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

public class CheckActor extends CheckBox implements Movable, Refreshable {
    private final AssetsManager manager;

    public CheckActor(final AssetsManager manager, final BitmapFont font, final String text, final String off, final String on) {
        this(manager, text, new CheckBoxStyle(manager.getDrawable(off), manager.getDrawable(on), font, Color.WHITE));
    }

    public CheckActor(final AssetsManager manager, final String text, final CheckBoxStyle style) {
        super(text, style);
        setName(text);
        this.manager = manager;
        left();
        setDebug(true);
        refresh();
    }

    @Override
    public boolean refresh() {
        setText(manager.langue.get(getName()));
        return true;
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}
