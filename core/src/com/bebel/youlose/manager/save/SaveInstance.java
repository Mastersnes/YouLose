package com.bebel.youlose.manager.save;

import com.bebel.youlose.manager.resources.ScreensManager;
import com.bebel.youlose.screens.enigme1.Enigme1;

import java.util.Properties;

import static com.bebel.youlose.utils.SecurityUtils.decrypt;
import static com.bebel.youlose.utils.SecurityUtils.encrypt;

/**
 * Instance de sauvegarde
 */
public class SaveInstance {
    public static final String USED = "used";
    public static final String EMPLACEMENT = "emplacement";

    private final SaveManager.SaveEnum type;
    private boolean used = false;
    private String emplacement = Enigme1.NAME;

    public void setUsed(final boolean used) {
        this.used = used;
    }
    public boolean isUsed() {
        return used;
    }

    public void setEmplacement(final String emplacement) {
        this.emplacement = emplacement;
    }
    public String getEmplacement() {
        return emplacement;
    }

    public SaveManager.SaveEnum getType() {
        return type;
    }

    public SaveInstance(final SaveManager.SaveEnum type) {
        this.type = type;
    }

    public SaveInstance save() {
        return SaveManager.getInstance().save(this);
    }

    public void delete() {
        used = false;
        save();
    }

    public void loadData(final Properties prop) {
        setUsed(Boolean.valueOf(prop.getProperty(USED, Boolean.FALSE.toString())));
        if (isUsed()) {
            setEmplacement(decrypt(prop.getProperty(EMPLACEMENT, "")));
        }
    }

    public void saveData(final Properties prop) {
        prop.setProperty(USED, String.valueOf(isUsed()));
        prop.setProperty(EMPLACEMENT, encrypt(getEmplacement()));
    }
}
