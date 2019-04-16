package com.bebel.youlose.manager.save;

import com.badlogic.gdx.Gdx;
import com.bebel.youlose.utils.Constantes;
import com.bebel.youloseClient.enums.SaveType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager de sauvegarde
 */
public class SaveManager {
    private static SaveManager instance;
    private GameSave current;
    private Map<SaveType, GameSave> saves = new HashMap<>();

    private SaveManager() {
        loadAll();
    }

    public static synchronized SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }

    public void setCurrent(final GameSave current) {
        this.current = current;
    }
    public GameSave getCurrent() {
        return current;
    }

    public List<GameSave> getSaves() {
        return new ArrayList<>(saves.values());
    }
    public GameSave get(final SaveType type) {
        return saves.get(type);
    }

    public void loadAll()  {
        for (final SaveType saveTypes : SaveType.values()) {
            saves.put(saveTypes, loadOrCreate(saveTypes));
        }
    }

    public GameSave loadOrCreate(final SaveType type) {
            Gdx.app.log("SaveManager", "Chargement de la sauvegarde " + type);

            GameSave saveInstance = null;
            if (Constantes.LOCATION_URL != null && Constantes.LOCATION_URL.contains("/kongregate/")) {
                saveInstance = SaveUtils.getInstance().loadFromCloud(type);
            }

            if (saveInstance == null) saveInstance = SaveUtils.getInstance().loadFromFile(type);
            if (saveInstance == null) saveInstance = save(new GameSave(type));

            return saveInstance;
    }

    public GameSave save(final GameSave saveInstance) {
        final SaveType type = saveInstance.getType();
        Gdx.app.log("SaveManager", "Creation de la sauvegarde " + type);

        if (Constantes.LOCATION_URL != null && Constantes.LOCATION_URL.contains("/kongregate/")) {
            SaveUtils.getInstance().saveOnCloud(saveInstance);
        }
        SaveUtils.getInstance().saveOnFile(saveInstance);

        return saveInstance;
    }
}
