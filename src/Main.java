import java.io.IOException;
import UserInterface.*;
import javafx.application.Application;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    /**
     * Allows to change between UI and GUI easier
     */

    private static boolean GUI = false;

    /**
     * Main program
     * @param args standard input
     * @throws IOException exception management for writing/reading from files
     */

    public static void main(String args[]) throws IOException {
        if(GUI) {
            mainGUI(args);
        } else{
            mainUI(args);
        }
    }

    /**
     * Function calling text interface
     * @throws IOException exception management for writing/reading from files
     */

    private static void mainUI(String[] args) throws IOException {
        UI managerUI = new UI();
        managerUI.getPersistence().loadDB();
        managerUI.askInfoProduct(args);
    }

    /**
     * Function calling graphic interface
     */

    private static void mainGUI(String[] args) throws IOException {
        Application.launch(GUI.class, args);
    }

}
