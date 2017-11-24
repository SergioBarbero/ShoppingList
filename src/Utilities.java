import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilities {

    /**
     * Utilities constructor
     */

    public Utilities(){

    }

    /**
     * Ask the user for his name
     * @return name of the user
     */

    public String askUser(){
        System.out.println("Introduzca su nombre de usuario: ");
        //TODO -- que sean sólo números o letras
        return new Scanner(System.in).next();
    }

    /**
     * Ask the user for file format
     * @return format of the file
     */

    public String askFormatFile(){
        String format = ".tsv";
        //TODO -- switch con varios formatos
        return format;
    }

    /**
     *  Checks if the file for this user already exists to open it, or creates a new list
     * @param fileToWrite file where the list should be
     * @return list with products for this user (read from file or created empty)
     */

    public ProductList checkList(String fileToWrite) throws IOException {
        File f = new File(fileToWrite);
        ProductList l = null;
        if ((f.exists())) {
            l = loadList(readList(fileToWrite));
        } else {
            l = createList();
        }
        return l;
    }

    public ProductList loadList(ProductList pL){
        ProductList l = ProductList.getInstance();
        for(ChosenProduct cpr: pL.getList()){
            l.addProduct(cpr);
        }
        return l;
    }

    /**
     * Create a new list
     * @return list empty
     */

    public ProductList createList(){
        System.out.println("Creando nueva lista...");
        return ProductList.getInstance();
    }

    /**
     * Read a list of products from a file
     * @param fileName with the list
     * @return list read
     */

    public ProductList readList(String fileName) throws IOException {

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
                        (aPP[3].equals("YES") ? true : false),
                        (aPP[4].toString().equals(" - ") ? -1 : Double.parseDouble(aPP[4])),
                        (aPP[5].equals("YES") ? true : false));
                list.addProduct(cPr);
            }
        }

        return list;
    }

    /**
     * Gives the user the control to edit the products of the list (or run a bunch of tests)
     * @param list to be edited
     * @return list when the edit is done
     */

    public ProductList manageTests(ProductList list){
        Tests t = new Tests(list);
        return t.getList();
    }

    /**
     * Save the user list to a file
     * @param writeTo file where the list is gonna be saved
     * @param list list to be saved
     * @param user String to make a header and know whos that list
     * @throws IOException
     */

    public void writeList(String writeTo, ProductList list, String user) throws IOException {

        System.out.println("Guardando lista...");

        char del = '\t';
        char fln = '\n';
        Writer pw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(writeTo), "iso-8859-1"));

        pw.append(writeHeader(del, fln, user));
        pw.append(writeBody(del, fln, list));

        pw.flush();
        pw.close();
    }

    /**
     * Writes a header to make things clear in the file
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param user String with user name
     */

    public String writeHeader(char del, char fln, String user){
        StringBuilder sb = new StringBuilder();

        sb.append(del);
        sb.append("Shopping List of " + user);
        sb.append(fln);
        sb.append( "ID");
        sb.append(del);
        sb.append( "Product");
        sb.append(del);
        sb.append( "Quantity");
        sb.append(del);
        sb.append( "Bought");
        sb.append(del);
        sb.append( "Price");
        sb.append(del);
        sb.append( "Favorite");
        sb.append(fln);

        return sb.toString();
    }

    /**
     * Writes all the list lines with all the info available
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param list list with products
     */

    public String writeBody(char del, char fln, ProductList list){
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
