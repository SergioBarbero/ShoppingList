package UserInterface;
import javafx.application.Application;
import sun.applet.Main;

import java.io.IOException;

public class GUI extends UIManager {

    /**
     * UserInterface.GUI constructor
     */

    MainWindow userGUI;

    public GUI() throws IOException {
        super();
        userGUI = new MainWindow();
    }

    @Override
    public String askUser(String question) {
        return null;
    }


    @Override
    public void askInfoProduct(String[] args) throws IOException {
        Application.launch(MainWindow.class, args);
    }

    @Override
    public void displayList() {

    }

    private boolean caseDel(int id){
        getListUtil().deleteFromList(id);
        return true;
    }
}
