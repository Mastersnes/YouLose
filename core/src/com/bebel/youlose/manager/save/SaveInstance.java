package com.bebel.youlose.manager.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bebel.youlose.utils.SecurityUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * Instance de sauvegarde
 */
public class SaveInstance {
    private final SaveManager.SaveEnum type;
    private final String emplacement;

    public SaveInstance(final SaveManager.SaveEnum type) throws IOException {
        this.type = type;
        final FileHandle file = Gdx.files.external("/youlose/save/"+type+".save");
        final Properties prop = new Properties();
        prop.load(file.read());

        emplacement = SecurityUtils.decrypt(prop.getProperty("emplacement", null));
    }

    public void save() {
        final FileHandle file = Gdx.files.external("/youlose/save/"+type+".save");

        try (final OutputStreamWriter out = (OutputStreamWriter) file.writer(false)) {
            final Properties prop = new Properties();
            prop.setProperty("emplacement", SecurityUtils.encrypt(emplacement));

            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
