import java.io.IOException;

public class GUI extends Manager {

    /**
     * GUI constructor
     */

    public GUI(){
        super();
    }

    @Override
    public String askUser(String question) {
        return null;
    }

    @Override
    public void askInfoProduct() throws IOException {

    }

    @Override
    public void displayList() {

    }

    private boolean caseDel(int id){
        getListUtil().deleteFromList(id);
        return true;
    }
}
