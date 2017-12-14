import java.io.IOException;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    //TODO -- Feature Switch: allows some developers to tests GUI while others work with same file without that GUI

    private static boolean GUI = false;

    /**
     * Utility object
     */

    private static Utilities util = null;

    /**
     * Main program
     * @param args standard input
     * @throws IOException exception management for write/read from files
     */

    public static void main(String args[]) throws IOException {
        util = new Utilities();
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

        Manager managerUI = new GUI();

    }

}
