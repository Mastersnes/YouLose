package com.bebel.youlose.scenes;

/**
 * Liste des scenes existantes
 */
public enum Scenes {
    MENU(Menu.class),
    GAME(Game.class);

    private final Class<? extends AbstractScene> type;
    private AbstractScene implementation;

    Scenes(final Class<? extends AbstractScene> type) {
        this.type = type;
    }

    /**
     * Retourne l'instance de la scene
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public AbstractScene get() throws IllegalAccessException, InstantiationException {
        if (implementation == null)
            implementation = type.newInstance();
        return implementation;
    }

    /**
     * renvoi le type de la scene
     * @return
     */
    public Class<? extends AbstractScene> getType() {
        return type;
    }
}
