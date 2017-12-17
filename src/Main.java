import java.io.IOException;

import Persistence.FilePersistence;
import Persistence.PersistanceManager;
import UserInterface.*;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    /**
     * Allows to change between UserInterface.UI and UserInterface.GUI more easy
     */

    private static boolean GUI = false;

    /**
     * Main program
     * @param args standard input
     * @throws IOException exception management for write/read from files
     */

    public static void main(String args[]) throws IOException {
        if(GUI) {
            mainGUI();
        } else{
            mainUI();
        }
    }

    /**
     * Function calling text interface
     * @throws IOException exception management for write/read from files
     */

    private static void mainUI() throws IOException {
        UIManager managerUI = new UI();
        managerUI.getPersistence().loadDB();
        managerUI.askInfoProduct();
    }

    /**
     * Function calling graphic interface
     */

    private static void mainGUI() throws IOException {

        UIManager managerGUI = new GUI();

    }

}
