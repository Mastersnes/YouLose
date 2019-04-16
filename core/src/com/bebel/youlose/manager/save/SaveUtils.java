package com.bebel.youlose.manager.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.bebel.youlose.manager.save.properties.GameProperties;
import com.bebel.youlose.utils.SmartProperties;
import com.bebel.youloseClient.client.YouLoseClient;
import com.bebel.youloseClient.enums.SaveType;
import com.bebel.youloseClient.request.GetSaveRequest;

import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.bebel.youlose.utils.SecurityUtils.encrypt;

/**
 * Utilitaire permettant de gerer les sauvegardes
 */
public class SaveUtils {
    private static SaveUtils instance;
    private GameProperties game;
    private YouLoseClient client;

    private SaveUtils() {
        game = new GameProperties();
        client = new YouLoseClient();
    }

    public static SaveUtils getInstance() {
        if (instance == null) {
            instance = new SaveUtils();
        }
        return instance;
    }

    /**
     * Charge la sauvegarde depuis le cloud
     * @param type
     */
    public GameSave loadFromCloud(final SaveType type) {
        final String save = client.getSave("TEST", type);
        return null;
    }

    /**
     * Sauvegarde sur le cloud
     * @param save
     */
    public void saveOnCloud(final GameSave save) {
        client.save("TEST", save.getType(), "");
    }

    /**
     * Charge la sauvegarde depuis l'environnement local
     * @param type
     */
    public GameSave loadFromFile(final SaveType type) {
        try {
            final FileHandle file = Gdx.files.external("/youlose/save/" + type + ".save");
            try {
                final SmartProperties prop = new SmartProperties();
                prop.load(file.read());
                return game.loadData(prop, type);
            } catch (final IOException e) {
                Gdx.app.error("SaveManager", "Erreur lors du chargement de la sauvegarde " + type, e);
            }
        } catch (final GdxRuntimeException e) {
            Gdx.app.log("SaveManager", "La sauvegarde " + type + " n'existe pas.");
        }
        return null;
    }

    /**
     * Sauvegarde dans un fichier
     * @param save
     */
    public void saveOnFile(final GameSave save) {
        final FileHandle file = Gdx.files.external("/youlose/save/" + save.getType() + ".save");
        try (final OutputStreamWriter out = (OutputStreamWriter) file.writer(false)) {
            final SmartProperties prop = new SmartProperties();
            game.saveData(prop, save);
            prop.store(out, null);
        } catch (IOException e) {
            Gdx.app.error("SaveManager", "Erreur lors de la création de la sauvegarde " + save.getType(), e);
        }
    }
}
