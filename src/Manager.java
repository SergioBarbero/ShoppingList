import java.io.IOException;

public abstract class Manager {


    String nameOfTheList;
    private FilesUtilities fUtil;
    private ListUtilities lUtil;

    public Manager() {
        this.fUtil = new FilesUtilities();
        this.lUtil = new ListUtilities();
    }

    /**
     * Gets name of
     * @return
     */

    public String getNameOfTheList(){
        return nameOfTheList;
    }

    /**
     * Gets utilities for the files instance
     * @return files util
     */

    public FilesUtilities getFilesUtil() {
        return fUtil;
    }

    /**
     * Gets utilities for the list instance
     * @return list util
     */

    public ListUtilities getListUtil() {
        return lUtil;
    }

    /**
     * Gets folder location for list
     * @return directory name
     */

    protected String askFolderName(){
        String folderName = getFilesUtil().getDataFolder();

        getFilesUtil().checkFolder(folderName);

        return folderName;
    }

    /**
     * Gets full path (folder+file name) for the list location
     * @return full path for the file
     * @throws IOException exception management for write/read from files
     */

    protected String getDataPath() throws IOException {
        String folderName = askFolderName();
        String fullName = folderName + getFilesUtil().getDataFile();

        getFilesUtil().checkFile(fullName);

        return fullName;
    }

    /**
     * Sets name of the list
     * @throws IOException exception management for write/read from files
     */

    public void loadDB() throws IOException{
        nameOfTheList = getDataPath();
    }

    public abstract String askUser(String question);

    public abstract void askInfoProduct() throws IOException;

    public abstract void displayList();

}
