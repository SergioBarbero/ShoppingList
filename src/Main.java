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
        ProductList userList = ProductList.getInstance();
        Manager managerUI = new UI(userList);

        String user = util.askUser();
        String fileType = util.askFormatFile();
        String fileToWrite = user + fileType;

        util.checkList(fileToWrite);

        //userList = util.manageTests(userList);
        managerUI.askInfoProduct();

        util.writeList(fileToWrite, userList, user);
    }

}
