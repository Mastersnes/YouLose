package com.bebel.youlose.manager.save.properties;

import com.bebel.youlose.utils.SmartProperties;
import com.bebel.youloseClient.bean.Enigme1Data;

/**
 * Enigme1 Properties
 */
public class Enigme1Properties implements IProperties<Enigme1Data> {
    protected final String FEUILLES = "enigme1.feuilles";
    protected final String CASSURE = "enigme1.cassure";

    public Enigme1Properties() {
    }

    @Override
    public Enigme1Data loadData(final SmartProperties prop) {
        final Enigme1Data data = new Enigme1Data();
        data.getFeuilles().addAll(prop.getListInt(FEUILLES));
        data.setCassure(prop.get(CASSURE, 0));
        return data;
    }

    @Override
    public void saveData(final SmartProperties prop, final Enigme1Data data) {
        prop.setListInt(FEUILLES, data.getFeuilles());
        prop.set(CASSURE, data.getCassure());
    }
}
