package UserInterface;
import Persistence.PersistanceManager;
import java.io.IOException;

public class GUI extends UIManager {

    /**
     * UserInterface.GUI constructor
     */


    public GUI() throws IOException {
        super();

    }

    /**
     * Select persistence system
     * @return new instance for persistence system
     */

    @Override
    public PersistanceManager askPersistenceSystem() {

        boolean file = true;

        // Ask user fot persistence type (file or database) with a pop-up window or similar
        // We have no databse implemented, so it's file persistence by default

        return file ? NewFilePersistence(): NewDBPersistence();
    }

    @Override
    public String askUser(String question) {
        return null;
    }


    @Override
    public void askInfoProduct(String[] args) throws IOException {
    }

    @Override
    public void displayList() {
    }

}
