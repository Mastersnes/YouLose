package com.bebel.youlose.manager.save;

import com.bebel.youlose.utils.SmartProperties;

/**
 * Interface de sauvegarde
 */
public interface ISave {
    void init();
    void loadData(final SmartProperties prop);
    void saveData(final SmartProperties prop);
}
