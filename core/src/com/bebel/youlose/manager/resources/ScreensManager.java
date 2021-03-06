package com.bebel.youlose.manager.resources;

import com.badlogic.gdx.Gdx;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.screens.enigme1.Enigme1;
import com.bebel.youlose.screens.menu.MenuScreen;

import java.util.HashMap;
import java.util.Map;

import static com.bebel.youloseClient.enums.Emplacement.ENIGME1;
import static com.bebel.youloseClient.enums.Emplacement.MENU;

/**
 * Manager des ecrans
 */
public class ScreensManager {
    private static ScreensManager instance;
    private LaunchGame parent;
    private Map<Class<? extends AbstractScreen>, AbstractScreen> screens = new HashMap<>();
    private Map<String, Class<? extends AbstractScreen>> types = new HashMap<>();

    private ScreensManager() {
        types.put(MENU, MenuScreen.class);
        types.put(ENIGME1, Enigme1.class);
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
        try {
            final AbstractScreen screen = screenType.getDeclaredConstructor(LaunchGame.class).newInstance(parent);
            screens.put(screenType, screen);
            parent.setScreen(screen);
        } catch (final Exception e) {
            Gdx.app.error("ScreensManager", "Impossible d'instancier le screen : " + screenType, e);
        }
    }

    public void switchTo(final String screenName) {
        Class<? extends AbstractScreen> screenType = types.get(screenName);
        if (screenType == null) screenType = Enigme1.class;
        switchTo(screenType);
    }

    public void dispose() {
        for (final AbstractScreen screen : screens.values()) {
            screen.dispose();
        }
    }
}
