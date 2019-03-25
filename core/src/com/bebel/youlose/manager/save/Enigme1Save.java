package com.bebel.youlose.manager.save;

import com.bebel.youlose.utils.SmartProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance de sauvegarde
 */
public class Enigme1Save implements ISave {
    private final String ENIGME1 = "enigme1.";
    private final String FEUILLES = ENIGME1 + "feuilles";
    private final String CASSURE = ENIGME1 + "cassure";

    private List<Integer> feuilles;
    private int cassure;

    public List<Integer> getFeuilles() {
        return feuilles;
    }
    public int getCassure() {
        return cassure;
    }
    public void setCassure(int cassure) {
        this.cassure = cassure;
    }

    @Override
    public void init() {
        feuilles = new ArrayList<>();
        cassure = 0;
    }

    @Override
    public void loadData(final SmartProperties prop) {
        feuilles = prop.getListInt(FEUILLES);
        cassure = prop.get(CASSURE, 0);
    }

    @Override
    public void saveData(final SmartProperties prop) {
        prop.setListInt(FEUILLES, feuilles);
        prop.set(CASSURE, cassure);
    }
}
