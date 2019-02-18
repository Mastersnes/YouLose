package com.bebel.youlose.menu.vitre;

import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

public class MenuScanner extends AbstractGroup implements Actionnable, Movable {
    private final MenuScreen parent;

    public MenuScanner(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        refresh();
    }

    public void makeEvents() {
    }

    @Override
    public boolean refresh() {
        return true;
    }
}
