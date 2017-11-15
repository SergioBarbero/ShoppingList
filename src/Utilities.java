import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static String askUser(){
        System.out.println("Introduzca su nombre de usuario: ");
        //TODO -- que sean sólo números o letras
        return new Scanner(System.in).next();
    }

    /**
     *  Checks if the file for this user already exists to open it, or creates a new list
     * @param fileToWrite file where the list should be
     * @return list with products for this user (read from file or created empty)
     */

    public static List<ChosenProduct> checkList(String fileToWrite){
        File f = new File(fileToWrite);
        return ((f.exists()) ? readList(fileToWrite) : createList());
    }

    /**
     * Create a new list
     * @return list empty
     */

    public static List<ChosenProduct> createList(){
        return new ProductList().getList();
    }

    /**
     * Read a list of products from a file
     * @param fileName with the list
     * @return list read
     */

    public static List<ChosenProduct> readList(String fileName){
        //TODO -- leer fichero
        return null;
    }

    /**
     * Gives the user the control to edit the products of the list (or run a bunch of tests)
     * @param list to be edited
     * @return list when the edit is done
     */

    public static List<ChosenProduct> manageList(List<ChosenProduct> list){
        return new Tests(list).getList();
    }

    /**
     * Save the user list to a file
     * @param writeTo file where the list is gonna be saved
     * @param list list to be saved
     * @param user String to make a header and know whos that list
     * @throws IOException
     */

    public static void writeList(String writeTo, List<ChosenProduct> list, String user) throws IOException {

        FileWriter pw = new FileWriter(writeTo);
        StringBuilder sb = new StringBuilder();
        char del = '\t';
        char fln = '\n';

        writeHeader(sb, del, fln, user);
        writeBody(sb, del, fln, list);

        pw.write(sb.toString());
        pw.close();

    }

    /**
     * Writes a header to make things clear in the file
     * @param sb StringBuilder to makes strings and save them
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param user String with user name
     */

    public static void writeHeader(StringBuilder sb, char del, char fln, String user){
        sb.append(fln);
        sb.append(del);
        sb.append("Lista de la Compra de " + user);
        sb.append(fln);
        sb.append( "Nombre");
        sb.append(del);
        sb.append( "Cantidad");
        sb.append(del);
        sb.append( "Comprado");
        sb.append(del);
        sb.append( "Precio");
        sb.append(del);
        sb.append( "Favorito");
        sb.append(fln);
    }

    /**
     * Writes all the list lines with all the info available
     * @param sb StringBuilder to makes strings and save them
     * @param del char that separates data
     * @param fln char that ends the line to write a new product
     * @param list list with products
     */

    public static void writeBody(StringBuilder sb, char del, char fln, List<ChosenProduct> list){
        for (ChosenProduct p: list) {
            sb.append(list.get(p.getId()).getName());
            sb.append(del);

            sb.append(list.get(p.getId()).getQuantity());
            sb.append(del);

            sb.append(list.get(p.getId()).getBoughtToString());
            sb.append(del);

            sb.append(list.get(p.getId()).getPriceToString());
            sb.append(del);

            sb.append(list.get(p.getId()).getFavoriteToString());
            sb.append(fln);
        }
    }
}
