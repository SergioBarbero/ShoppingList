import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sergio Barbero Bascones
 * @Author Víctor de Castro Hurtado
 */

public class Main {

    /**
     * Main program
     * @param args
     * @throws IOException
     */

    public static void main(String args[]) throws IOException {
        Utilities util = new Utilities();
        Manager managerUI;

        String user = util.askUser();
        String fileType = util.askFormatFile();
        String fileToWrite = user + fileType;

        ProductList userList = util.checkList(fileToWrite);
        managerUI = new UI(userList);

        //userList = util.manageTests(userList);
        managerUI.askInfoProduct();

        util.writeList(fileToWrite, userList, user);
    }

}
