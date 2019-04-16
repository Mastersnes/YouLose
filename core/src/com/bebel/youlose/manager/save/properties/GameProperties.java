package com.bebel.youlose.manager.save.properties;

import com.bebel.youlose.manager.save.GameSave;
import com.bebel.youlose.utils.SmartProperties;
import com.bebel.youloseClient.enums.SaveType;

import static com.bebel.youloseClient.enums.Emplacement.ENIGME1;

/**
 * Game Properties
 */
public class GameProperties implements IProperties<GameSave> {
    private final String USED = "used";
    private final String EMPLACEMENT = "emplacement";

    private final Enigme1Properties enigme1 = new Enigme1Properties();

    public GameProperties() {
    }

    @Override
    public GameSave loadData(final SmartProperties prop) {
        // Utiliser la version avec le savetype
        return null;
    }

    public GameSave loadData(final SmartProperties prop, final SaveType type) {
        final GameSave save = new GameSave(type);

        save.setUsed(prop.get(USED, false));
        save.setEmplacement(prop.get(EMPLACEMENT, ENIGME1));
        save.setEnigme1(enigme1.loadData(prop));
        return save;
    }

    @Override
    public void saveData(final SmartProperties prop, final GameSave save) {
        prop.set(USED, save.isUsed());
        prop.set(EMPLACEMENT, save.getEmplacement());
        enigme1.saveData(prop, save.getEnigme1());
    }
}
