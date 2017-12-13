import java.io.*;
import java.util.List;

class Utilities {

    /**
     * Utilities constructor
     */

    public Utilities(){

    }

    /**
     *  Checks if the file for this user already exists to open it, or creates a new list
     * @param fileToWrite file where the list should be
     * @return true/false if file already exists or not
     */

    public boolean checkList(String fileToWrite) throws IOException {
        File f = new File(fileToWrite);
        boolean checked = false;
        if ((f.exists())) {

            readList(fileToWrite);
            checked = true;
        } else {
            ProductList.getInstance();
        }
        return checked;
    }

    /**
     * Load list from variable to singleton ProductList
     * @param pL product list (empty or dead from file)
     */

    private void loadList(ProductList pL){
        for(Product pr: pL.getList()){
            ProductList.getInstance().addProduct(pr);
        }
    }

    /**
     * Read a list of products from a file
     * @param fileName with the list
     */

    private void readList(String fileName) throws IOException {

        String del = "\t";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
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
     * @param fileName file where the list is gonna be saved
     * @throws IOException exception management for write/read from files
     */

    public void writeList(String fileName) throws IOException {

        char del = '\t';
        char fln = '\n';
        Writer pw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "iso-8859-1" /*UTF-8*/));

        pw.append(writeHeader(del, fln, fileName));
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

    private String writeHeader(char del, char fln, String user){
        return String.valueOf(del) +
                "Shopping List of " +
                user +
                fln +
                "ID" +
                del +
                "Product" +
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
     * Gets OS name
     * @return name of the OS
     */

    private String getOSname(){
        return System.getProperty("os.name");
    }

    /**
     * Gets the character separator
     * @return separator character for the OS
     */

    public String getOSseparator(){
        String sep = "";
        if (getOSname().startsWith("Windows")){
            sep = "\\";
        } else if(getOSname().startsWith("Linux")){
            sep = "/";
        }
        return sep;
    }

    /**
     * Gets path to write and read lists
     * @param folder name of the folder
     * @return relative path to that folder
     */

    public String getListsPath(String folder) {
        return folder + getOSseparator();
    }

    /**
     * Check if a product with that name is already on the list
     * @param name of the product
     * @return true/false
     */

    public boolean productOnListByName(String name){
        boolean onList = false;
        for(Product pr: ProductList.getInstance().getList()){
            if(pr.getName().equals(name)){
                onList = true;
                break;
            }
        }
        return onList;
    }

    /**
     * Check if a product with that ID is already on the list
     * @param id of the product
     * @return true/false
     */

    public boolean productOnListById(int id){
        boolean onList = false;
        for(Product pr: ProductList.getInstance().getList()){
            if(pr.getId() == id){
                onList = true;
                break;
            }
        }
        return onList;
    }

    /**
     * Checks if the folder already exists to create there the file, or creates a new folder
     * @param folderName folder where the file should be
     * @return true/false if folder already exists or not
     */

    public boolean checkFolder(String folderName){
        File dir = new File(folderName);
        boolean exists = true;
        if ((!dir.exists()) || (dir.exists() && !dir.isDirectory())) {
            dir.mkdir();
            exists = false;
        }
        return exists;
    }

}
