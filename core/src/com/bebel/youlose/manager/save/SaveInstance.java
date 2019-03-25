package com.bebel.youlose.manager.save;

import com.bebel.youlose.screens.enigme1.Enigme1;
import com.bebel.youlose.utils.SmartProperties;

/**
 * Instance de sauvegarde
 */
public class SaveInstance implements ISave {
    private final String USED = "used";
    private final String EMPLACEMENT = "emplacement";

    private final SaveManager.SaveEnum type;
    private boolean used;
    private String emplacement;
    private Enigme1Save enigme1 = new Enigme1Save();

    public SaveInstance(final SaveManager.SaveEnum type) {
        this.type = type;
    }

    @Override
    public void init() {
        used = true;
        emplacement = Enigme1.NAME;
        enigme1.init();
        save();
    }

    public boolean isUsed() {
        return used;
    }
    public void setUsed(final boolean used) {
        this.used = used;
    }

    public String getEmplacement() {
        return emplacement;
    }
    public void setEmplacement(final String emplacement) {
        this.emplacement = emplacement;
    }

    public Enigme1Save getEnigme1() {
        return enigme1;
    }

    public SaveManager.SaveEnum getType() {
        return type;
    }

    // CRUD
    public SaveInstance save() {
        return SaveManager.getInstance().save(this);
    }
    public void delete() {
        used = false;
        save();
    }
    public void loadData(final SmartProperties prop) {
        used = prop.get(USED, false);
        emplacement = prop.get(EMPLACEMENT, Enigme1.NAME);
        enigme1.loadData(prop);
    }
    public void saveData(final SmartProperties prop) {
        prop.set(USED, isUsed());
        prop.set(EMPLACEMENT, getEmplacement());
        enigme1.saveData(prop);
    }
}
