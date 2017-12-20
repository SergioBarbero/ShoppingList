package Persistence;
import java.io.*;
import java.util.List;
import Products.*;

public class FilePersistence extends PersistanceManager{

    /**
     * Name of the file (folder + file)
     */

    private String fullFileName;

    /**
     * Folder name
     */

    private String folderName = "data";

    /**
     * File name
     */

    private String fileName = "database.tsv";

    /**
     * Files utilities constructor
     */

    public FilePersistence(){
        super();
    }

    /**
     * Sets full name of the file
     * @param name of the file
     */

    private void setFullFileName(String name){
        this.fullFileName = name;
    }

    /**
     * Gets full name of the file
     * @return name of the file
     */

    private String getFullFileName(){
        return this.fullFileName;
    }

    /**
     * Gets folder location for list
     * @return directory name
     */

    private String askFolderName(){
        String folderName = getDBFolder();

        checkFolder(folderName);

        return folderName;
    }

    /**
     * Gets full path (folder+file name) for the list location
     * @throws IOException exception management for write/read from files
     */

    private void askDBName() throws IOException {
        String folderName = askFolderName();

        setFullFileName(folderName + getGeneralUtil().getOSseparator() + getDBFile());

        checkDB();
    }

    /**
     * Sets name of the list
     * @throws IOException exception management for write/read from files
     */

    @Override
    public void loadDB() throws IOException{
        askDBName();
    }

    /**
     * Checks if the folder already exists to create there the file, or creates a new folder
     * @param folderName folder where the file should be
     */

    private void checkFolder(String folderName){
        File dir = new File(folderName);
        if ((!dir.exists()) || (dir.exists() && !dir.isDirectory())) {
            dir.mkdir();
        }
    }

    /**
     *  Checks if the file for this user already exists to open it, or creates a new list
     */

    @Override
    public void checkDB() throws IOException {
        String fileToWrite = getFullFileName();
        File f = new File(fileToWrite);
        if ((f.exists())) {
            readDB();
        } else {
            ProductList.getInstance();
        }
    }

    /**
     * Read a list of products from a file
     */

    @Override
    protected void readDB() throws IOException {

        String DBfileName = getFullFileName();
        String del = "\t";

        BufferedReader br = new BufferedReader(new FileReader(DBfileName));
        String line;
        String[] aPP;   //allProductParts;
        int cabeceras = 2;

        while((line = br.readLine()) != null) {
            cabeceras--;
            if(cabeceras < 0) {
                aPP = line.split(del);
                Product Pr = new Product(
                        Integer.parseInt(aPP[0]),
                        aPP[1],
                        Integer.parseInt(aPP[2]),
                        aPP[3].equals("YES"),
                        (aPP[4].equals(" - ") ? -1 : Double.parseDouble(aPP[4])),
                        aPP[5].equals("YES"));
                ProductList.getInstance().addProduct(Pr);
            }
        }
    }

    /**
     * Save the user list to a file
     * @throws IOException exception management for write/read from files
     */

    @Override
    public void writeDB() throws IOException {

        String DBfileName = getFullFileName();
        char del = '\t';
        char fln = '\n';
        Writer pw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DBfileName), "iso-8859-1" /*UTF-8*/));

        pw.append(writeDBfields(del, fln, DBfileName));
        pw.append(writeBody(del, fln, ProductList.getInstance()));

        pw.flush();
        pw.close();
    }

    /**
     * Writes a header to make things clear in the file
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param user String with user name
     */

    private String writeDBfields(char del, char fln, String user){
        return String.valueOf(del) +
                "Shopping List of " +
                user +
                fln +
                "ID" +
                del +
                "Products.Product" +
                del +
                "Quantity" +
                del +
                "Bought" +
                del +
                "Price" +
                del +
                "Favorite" +
                fln;
    }

    /**
     * Writes all the list lines with all the info available
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param list list with products
     */

    private String writeBody(char del, char fln, ProductList list){
        StringBuilder sb = new StringBuilder();
        List<Product> l = list.getList();
        int idx = -1;
        for (Product p: l) {
            idx++;
            sb.append(idx);
            sb.append(del);

            sb.append(p.getName());
            sb.append(del);

            sb.append(p.getQuantity());
            sb.append(del);

            sb.append(p.getBoughtToString());
            sb.append(del);

            sb.append(p.getPriceToString());
            sb.append(del);

            sb.append(p.getFavoriteToString());
            sb.append(fln);
        }
        return sb.toString();
    }

    /**
     * Gets folder name for the file list
     * @return folder name
     */

    private String getDBFolder() {
        return this.folderName;
    }

    /**
     * Gets file name for the list
     * @return file name
     */

    private String getDBFile(){
        return this.fileName;
    }

}
