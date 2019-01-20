package com.bebel.youlose.menu;

import com.bebel.youlose.components.AbstractGroup;
import com.bebel.youlose.components.Element;

public class MenuScreen extends AbstractGroup {
    public MenuScreen() {
        addActor(new Element("menu", "background.jpg"));
        final Element play = new Element("menu", "play.png");
        play.setPosition(20, 20);
        addActor(play);
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }
}
