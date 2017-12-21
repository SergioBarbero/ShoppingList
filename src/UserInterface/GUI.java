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
