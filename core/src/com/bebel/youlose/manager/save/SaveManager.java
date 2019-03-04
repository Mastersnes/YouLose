package com.bebel.youlose.manager.save;

/**
 * Manager de sauvegarde
 */
public class SaveManager {
    private static SaveManager instance;

    private SaveInstance gauche;
    private SaveInstance centre;
    private SaveInstance droite;

    private SaveManager() {
        gauche = load(SaveEnum.GAUCHE);
        centre = load(SaveEnum.CENTRE);
        droite = load(SaveEnum.DROITE);
    }

    public static synchronized SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }

    protected SaveInstance load(final SaveEnum saveId) {
        try {
            return new SaveInstance(saveId);
        }catch (final Exception e) {
            return null;
        }
    }

    public SaveInstance getGauche() {
        return gauche;
    }

    public SaveInstance getCentre() {
        return centre;
    }

    public SaveInstance getDroite() {
        return droite;
    }

    public enum SaveEnum {
        GAUCHE, CENTRE, DROITE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
