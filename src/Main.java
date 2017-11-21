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
        String user = Utilities.askUser();
        String fileType = Utilities.askFormatFile();
        String fileToWrite = user + fileType;

        List userList = Utilities.checkList(fileToWrite);

        userList = Utilities.manageList(userList);

        Utilities.writeList(fileToWrite, userList, user);
    }

}
