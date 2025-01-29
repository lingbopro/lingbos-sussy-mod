package me.lingbopro.sussymod;

import org.slf4j.Logger;

public class SharedVariables {
    //<editor-fold desc="基础方法">
    private SharedVariables() {
    }

    private static SharedVariables instance;

    public static SharedVariables getInstance() {
        if (instance == null) {
            instance = new SharedVariables();
        }
        return instance;
    }
    //</editor-fold>

    /// <editor-fold desc="logger" defaultstate="collapsed">
    public Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /// </editor-fold>
}
