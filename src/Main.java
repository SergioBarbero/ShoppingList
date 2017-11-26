import java.io.IOException;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    /**
     * TODO -- Feature Switch: allows developers to work with same file and branch on git, while some features are under development
     */

    private static boolean fsGUI = false;

    /**
     * Main program
     * @param args standard input
     * @throws IOException exception management for write/read from files
     */

    public static void main(String args[]) throws IOException {
        Utilities util = new Utilities();
        mainUI(util);
        if(fsGUI) {
            mainGUI(util);
        }
    }

    /**
     * Function calling text interface
     * @param util object Utilities
     * @throws IOException exception management for write/read from files
     */

    private static void mainUI(Utilities util) throws IOException {
        Manager managerUI = new UI();
        String fileToWrite = managerUI.askListName() + managerUI.askFormatFile();

        util.checkList(fileToWrite);

        managerUI.askInfoProduct(fileToWrite);
    }

    /*
    TODO -- Future Feature: implement graphic interface
     */

    /**
     * Function calling graphic interface
     * @param util object Utilities
     */

    private static void mainGUI(Utilities util){

        //Manager managerUI = new GUI();
        //String fileToWrite = managerGUI.askListName() + managerGUI.askFormatFile();

        //util.checkList(fileToWrite);

        //managerGUI.askInfoProduct(fileToWrite);

    }

}
