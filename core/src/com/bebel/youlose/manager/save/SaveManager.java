package com.bebel.youlose.manager.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Manager de sauvegarde
 */
public class SaveManager {
    private static SaveManager instance;

    private SaveInstance current;

    private SaveInstance gauche;
    private SaveInstance centre;
    private SaveInstance droite;

    private SaveManager() {
        gauche = loadOrCreate(SaveEnum.GAUCHE);
        centre = loadOrCreate(SaveEnum.CENTRE);
        droite = loadOrCreate(SaveEnum.DROITE);
    }

    public static synchronized SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
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

    public void setCurrent(SaveInstance current) {
        this.current = current;
    }

    public SaveInstance getCurrent() {
        return current;
    }

    public List<SaveInstance> getSaves() {
        return Arrays.asList(gauche, centre, droite);
    }

    public enum SaveEnum {
        GAUCHE, CENTRE, DROITE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public SaveInstance loadOrCreate(final SaveEnum type) {
        try {
            return load(type);
        } catch (final GdxRuntimeException e) {
            Gdx.app.log("SaveManager", "La sauvegarde " + type + " n'existe pas.");
            return save(new SaveInstance(type));
        }
    }

    public SaveInstance load(final SaveEnum type) throws GdxRuntimeException {
        Gdx.app.log("SaveManager", "Chargement de la sauvegarde " + type);

        final SaveInstance saveInstance = new SaveInstance(type);
        final FileHandle file = Gdx.files.external("/youlose/save/" + type + ".save");
        try {
            final Properties prop = new Properties();
            prop.load(file.read());
            saveInstance.loadData(prop);
        } catch (final IOException e) {
            Gdx.app.error("SaveManager", "Erreur lors du chargement de la sauvegarde " + type, e);
        }
        return saveInstance;
    }

    public SaveInstance save(final SaveInstance saveInstance) {
        final SaveEnum type = saveInstance.getType();
        Gdx.app.log("SaveManager", "Creation de la sauvegarde " + type);

        final FileHandle file = Gdx.files.external("/youlose/save/" + type + ".save");
        try (final OutputStreamWriter out = (OutputStreamWriter) file.writer(false)) {
            final Properties prop = new Properties();
            saveInstance.saveData(prop);
            prop.store(out, null);
        } catch (IOException e) {
            Gdx.app.error("SaveManager", "Erreur lors de la création de la sauvegarde " + type, e);
        }
        return saveInstance;
    }
}
