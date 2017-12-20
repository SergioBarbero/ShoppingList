package Persistence;
import Utilities.GeneralUtilities;

import java.io.IOException;

public abstract class PersistanceManager {

    /**
     * Gernal utilities
     */

    private GeneralUtilities gUtil = null;

    /**
     * Persistence manager constructor
     */

    PersistanceManager(){
        this.gUtil = new GeneralUtilities();
    }

    /**
     * Gets utilities for general methods
     * @return genreal util
     */

    GeneralUtilities getGeneralUtil() {
        return gUtil;
    }

    public abstract void loadDB() throws IOException;

    public abstract void checkDB() throws IOException;

    protected abstract void readDB() throws IOException;

    public abstract void writeDB() throws IOException;

}
