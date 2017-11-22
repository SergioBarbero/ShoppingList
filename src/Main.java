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
        Manager managerUI = new UI();

        String user = util.askUser();
        String fileType = util.askFormatFile();
        String fileToWrite = user + fileType;

        util.checkList(fileToWrite);

        //userList = util.manageTests(userList);
        managerUI.askInfoProduct();

        ProductList userList = ProductList.getInstance();

        util.writeList(fileToWrite, userList, user);
    }

}
