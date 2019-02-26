package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.Gdx;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager des ecrans
 */
public class ScreensManager {
    private static ScreensManager instance;
    private LaunchGame parent;
    private Map<Class<? extends AbstractScreen>, AbstractScreen> screens = new HashMap<>();

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
    public void switchTo(final Class<? extends AbstractScreen> screenType) {
        AbstractScreen screen = screens.get(screenType);
        if (screen == null) {
            try {
                screen = screenType.getDeclaredConstructor(LaunchGame.class).newInstance(parent);
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
