import java.io.IOException;

public abstract class Manager {


    String nameOfTheList;
    private Utilities util;

    Manager() {
        this.util = new Utilities();
    }


    public String getNameOfTheList(){
        return nameOfTheList;
    }

    public Utilities getUtil() {
        return util;
    }

    /**
     * Creates or read the list within the path given
     * @param folderName folder where the file is going to be
     * @return full path of the file
     * @throws IOException exception management for write/read from files
     */
    private String makeFullName(String folderName) throws IOException {
        String fullName = folderName +
                "database.tsv";

        util.checkList(fullName);

        return fullName;
    }

    /**
     * Ask the user for file name (within a folder)
     * @return full path for the file
     * @throws IOException exception management for write/read from files
     */

    protected String askFullName() throws IOException {
        String folderName = askFolderName();
        return makeFullName(folderName);
    }


    public  void loadDB() throws IOException{
        nameOfTheList = askFullName();
    }

    public abstract void addToList(String name, int quantity);

    public abstract void deleteFromList(int id);

    public abstract void modifyNameList(int id, String name);

    public abstract void modifyPriceList(int id, double price);

    public abstract void modifyQuantityList(int id, int quantity);

    public abstract void markAsFavList(int id);

    public abstract void markAsBoughtList(int id);

    public abstract String askUser(String question);

    public abstract String askFormatFile();

    public abstract String askFolderName();

    protected abstract String askFileName() throws IOException;

    public abstract void askInfoProduct() throws IOException;

    public abstract void printList();

}
