import java.io.IOException;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    /**
     * Allows to change between UI and GUI more easy
     */

    private static boolean GUI = false;

    /**
     * Utility object
     */

    private static ListUtilities util = null;

    /**
     * Main program
     * @param args standard input
     * @throws IOException exception management for write/read from files
     */

    public static void main(String args[]) throws IOException {
        util = new ListUtilities();
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
        Manager managerUI = new UI();
        managerUI.loadDB();
        managerUI.askInfoProduct();
    }

    //TODO -- Future Feature: implement graphic interface

    /**
     * Function calling graphic interface
     */

    private static void mainGUI(){

        Manager managerGUI = new GUI();

    }

}
