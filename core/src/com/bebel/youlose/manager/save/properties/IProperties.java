package com.bebel.youlose.manager.save.properties;

import com.bebel.youlose.utils.SmartProperties;

/**
 * interface d'ecriture et lecture de properties
 */
public interface IProperties<SAVE> {
    SAVE loadData(final SmartProperties prop);
    void saveData(final SmartProperties prop, final SAVE save);
}
