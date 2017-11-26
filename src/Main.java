import java.io.IOException;

/**
 * @author Sergio Barbero Bascones
 * @author Víctor de Castro Hurtado
 */

public class Main {

    //TODO -- Feature Switch: allows some developers to tests GUI while others work with same file without that GUI

    private static boolean fsGUI = false;

    //TODO -- Feature Switch: allows developers to run two versions, one of them for users

    private static boolean fsUserVersion = true;

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
        mainUI();
        if(fsGUI) {
            mainGUI();
        }
    }

    /**
     * Function calling text interface
     * @throws IOException exception management for write/read from files
     */

    private static void mainUI() throws IOException {
        Manager managerUI = new UI();
        String folderName = (!fsUserVersion ? "src" + util.getOSseparator(): "") +
                util.getListsPath(managerUI.askFolderName());

        if(util.checkFolder(folderName)){
            System.out.println("Carpeta encontrada.");
        } else{
            System.out.println("Nueva carpeta creada.");
        }

        String fullName = folderName +
                managerUI.askListName() +
                managerUI.askFormatFile();

        if (util.checkList(fullName)) {
            System.out.println("Lista leida desde fichero.");
        } else {
            System.out.println("Nueva lista creada.");
        }

        managerUI.askInfoProduct(fullName);
    }

    //TODO -- Future Feature: implement graphic interface

    /**
     * Function calling graphic interface
     */

    private static void mainGUI(){

        //Manager managerUI = new GUI();
        //String folderName = managerGUI.askFolderName();
        //String fileToWrite = managerGUI.askListName() + managerGUI.askFormatFile();

        //util.checkList(fileToWrite);

        //util.checkList(util.getListsPath(folderName) + fileToWrite);

        //managerGUI.askInfoProduct(fileToWrite);

    }

}
