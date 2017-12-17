package UserInterface;
import Persistence.*;
import Utilities.*;
import java.io.IOException;

public abstract class UIManager {

    /**
     * Persistance manager
     */

    private PersistanceManager pManager = null;

    /**
     * List utilities
     */

    private ListUtilities lUtil = null;

    /**
     * Gernal utilities
     */

    private GeneralUtilities gUtil = null;

    UIManager() throws IOException {
        this.pManager = askPersistenceSystem();
        this.lUtil = new ListUtilities();
        this.gUtil = new GeneralUtilities();
    }

    /**
     * Select persistence system
     * @return new instance for persistence system
     */

    private PersistanceManager askPersistenceSystem() throws IOException {
        boolean file = true;

        return file ? new FilePersistence(): new DBPersistence();
    }



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

    ListUtilities getListUtil() {
        return lUtil;
    }

    public abstract String askUser(String question);

    public abstract void askInfoProduct(String[] args) throws IOException;

    public abstract void displayList();

}
