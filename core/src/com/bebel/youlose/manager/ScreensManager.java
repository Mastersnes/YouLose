package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.AbstractGroup;
import com.bebel.youlose.components.DefaultScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste des ecrans existantes
 */
public class ScreensManager {
    private static ScreensManager instance;
    private LaunchGame parent;
    private Map<Class<? extends AbstractGroup>, DefaultScreen> screens = new HashMap<>();

    private ScreensManager() {
    }

    public static void init(final LaunchGame parent) {
        instance = new ScreensManager();
        instance.parent = parent;
    }

    public static ScreensManager getInstance() {
        return instance;
    }

    /**
     * Permet de switcher sur un ecran
     *
     * @param screenType
     */
    public void switchTo(final Class<? extends AbstractGroup> screenType) {
        DefaultScreen screen = screens.get(screenType);
        if (screen == null) {
            try {
                screen = new DefaultScreen(parent, screenType.newInstance());
                screens.put(screenType, screen);
            } catch (final Exception e) {
                Gdx.app.error("ScreensManager", "Impossible d'instancier le screen : " + screenType, e);
            }
        }
        if (parent.getScreen() != null)
            parent.getScreen().dispose();
        parent.setScreen(screen);
    }
}
