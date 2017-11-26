import java.io.IOException;

public abstract class Manager {

    Manager() {

    }

    public abstract void addToList(String name, int quantity);

    public abstract void deleteFromList(int id);

    public abstract void modifyNameList(int id, String name);

    public abstract void modifyPriceList(int id, double price);

    public abstract void modifyQuantityList(int id, int quantity);

    public abstract void markAsFavList(int id);

    public abstract void markAsBoughtList(int id);

    public abstract String askUser(String question);

    public abstract String askListName();

    public abstract String askFormatFile();

    public abstract String askFolderName();

    public abstract void askInfoProduct(String fileName) throws IOException;

    public abstract void printList();

}
