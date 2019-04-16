package com.bebel.youlose.manager.save;

import com.bebel.youloseClient.bean.GameData;
import com.bebel.youloseClient.enums.SaveType;

/**
 * Manager de sauvegarde
 */
public class GameSave extends GameData {

    public GameSave(final SaveType type) {
        super(type);
    }

    @Override
    public void reset() {
        super.reset();
        save();
    }

    public void save() {
        SaveManager.getInstance().save(this);
    }
    public void delete() {
        super.reset();
        used = false;
        save();
    }
}
