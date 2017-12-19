package UserInterface;
import Persistence.*;
import Products.ProductListOperations;
import Utilities.GeneralUtilities;

import java.io.IOException;

public abstract class UIManager {

    /**
     * Persistance manager
     */

    private PersistanceManager pManager = null;

    /**
     * List utilities
     */

    private ProductListOperations lUtil = null;

    /**
     * Gernal utilities
     */

    private GeneralUtilities gUtil = null;

    UIManager() throws IOException {
        this.pManager = askPersistenceSystem();
        this.lUtil = new ProductListOperations();
        this.gUtil = new GeneralUtilities();
    }

    /**
     * Creates file persistem system
     * @return new file persistence
     */

    FilePersistence NewFilePersistence(){
        return new FilePersistence();
    }

    /**
     * Creates database persistem system
     * @return new database persistence
     */

    DBPersistence NewDBPersistence(){
        return new DBPersistence();
    }

    public abstract PersistanceManager askPersistenceSystem() throws IOException;

    /**
     * Gets persistence system
     * @return persistence system
     */

    public PersistanceManager getPersistence(){
        return this.pManager;
    }

    /**
     * Gets utilities for general methods
     * @return genreal util
     */

    GeneralUtilities getGeneralUtil() {
        return gUtil;
    }

    /**
     * Gets utilities for the list instance
     * @return list util
     */

    ProductListOperations getListUtil() {
        return lUtil;
    }

    public abstract String askUser(String question);

    public abstract void askInfoProduct(String[] args) throws IOException;

    public abstract void displayList();

}
