import java.io.*;
import java.util.List;

class Utilities {

    /**
     * Utilities constructor
     */

    Utilities(){

    }

    /**
     *  Checks if the file for this user already exists to open it, or creates a new list
     * @param fileToWrite file where the list should be
     */

    void checkList(String fileToWrite) throws IOException {
        File f = new File(fileToWrite);
        if ((f.exists())) {
            loadList(readList(fileToWrite));
        } else {
            createList();
        }
    }

    /**
     * Load list from local variable to singleton ProductList
     * @param pL product list (empty or dead from file)
     */

    private void loadList(ProductList pL){
        for(ChosenProduct cpr: pL.getList()){
            ProductList.getInstance().addProduct(cpr);
        }
    }

    /**
     * Create a new list
     */

    private void createList(){
        System.out.println("Creando nueva lista...");
        ProductList.getInstance();
    }

    /**
     * Clear list
     */

    void deleteList(){
        ProductList.getInstance().resetList();
    }

    /**
     * Read a list of products from a file
     * @param fileName with the list
     * @return list read
     */

    private ProductList readList(String fileName) throws IOException {

        System.out.println("Leyendo lista desde fichero...");

        ProductList list = ProductList.getInstance();
        String del = "\t";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        String[] aPP;   //allProductParts;
        int cabeceras = 2;

        while((line = br.readLine()) != null) {
            cabeceras--;
            if(cabeceras < 0) {
                aPP = line.split(del);
                Product pr = new Product(
                        Integer.parseInt(aPP[0]),
                        aPP[1]);
                ChosenProduct cPr = new ChosenProduct(
                        pr,
                        Integer.parseInt(aPP[2]),
                        aPP[3].equals("YES"),
                        (aPP[4].equals(" - ") ? -1 : Double.parseDouble(aPP[4])),
                        aPP[5].equals("YES"));
                list.addProduct(cPr);
            }
        }

        return list;
    }

    /**
     * Save the user list to a file
     * @param fileName file where the list is gonna be saved
     * @throws IOException exception management for write/read from files
     */

    void writeList(String fileName) throws IOException {

        char del = '\t';
        char fln = '\n';
        Writer pw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "iso-8859-1"));

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
        List<ChosenProduct> l = list.getList();
        int idx = -1;
        for (ChosenProduct p: l) {
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
}
