package Persistence;
import Utilities.*;
import java.io.IOException;

public abstract class PersistanceManager {

    /**
     * Gernal utilities
     */

    private GeneralUtilities gUtil = null;

    /**
     * Persistence manager constructor
     */

    public PersistanceManager(){
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

    public abstract void checkFolder(String folderName);

    public abstract void checkDB() throws IOException;

    protected abstract void readDB() throws IOException;

    public abstract void writeDB() throws IOException;

    public abstract String getDBFolder();

    public abstract String getDBFile();
}
