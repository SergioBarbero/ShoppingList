import java.io.IOException;
import java.util.Scanner;

public class UI extends Manager{

    //TODO -- Future Feature: we should clean cmd window with following line in some parts of the run
    //Runtime.getRuntime().exec("cls");

    //TODO -- Feature Switch: allows developers to work with same file and branch on git, while some features are under development

    private boolean fSFormat = false;

    /**
     * Utilities
     */

    private Utilities util;

    /**
     * UI constructor
     */

    UI(){
        this.util = new Utilities();
    }

    /**
     * Gets a String from the user
     * @return String returned
     */

    private String getIntroduced(){
        return  new Scanner(System.in).next();
    }

    /**
     * Asks user for something
     * @param question to tell user what we need
     * @return answer from user
     */

    @Override
    public String askUser(String question){
        System.out.println(question);
        return getIntroduced();
    }

    /**
     * Asks user for list's name
     * @return name of the list
     */

    @Override
    public String askListName(){
        return askUser("Introduzca en nombre de la lista: ");
    }

    /**
     * Ask the user for file format
     * @return format of the file
     */

    @Override
    public String askFormatFile(){
        String format = ".tsv";
        if(fSFormat){
            //TODO -- Future Feature: switch with several ways to save the list
        }
        return format;
    }

    /**
     * Ask the user for directory name
     * @return directory name
     */

    @Override
    public String askFolderName(){
        return askUser("Introduzca el nombre de la carpeta donde desea crear/leer su lista: ");
    }

    /**
     * Ask information to the user
     * @param fileName name of file to write the data
     * @throws IOException exception management for write/read from files
     */

    @Override
    public void askInfoProduct(String fileName) throws IOException {

        boolean inLoop = true;
        String name;
        int id;
        int quantity;
        double price;

        while (inLoop) {
            //Runtime.getRuntime().exec(clearWindow);

            System.out.println("\n\t¿Que operación desea realizar?");    //\u00BF
            System.out.println("ADD -> Añade nuevo producto");  //\u00F1
            System.out.println("DEL -> Elimina un producto");
            System.out.println("MOD -> Modifica un producto");
            System.out.println("BUY -> Marcar producto como comprado o no comprado");
            System.out.println("FAV -> Marcar producto como favorito o no favorito");
            System.out.println("VIEW -> Visualización de la lista");
            System.out.println("SAVE -> Guardar los cambios en el fichero");
            System.out.println("EXIT -> Dejar de editar la lista y salir (sin guardar)");

            switch(getIntroduced()){
                case "ADD":
                    //Runtime.getRuntime().exec(clearWindow);
                    name = askUser("Introduce el nombre del producto: ");
                    if(util.productOnListByName(name)){
                        System.out.println("ERROR: Producto ya existente");
                        break;
                    }
                    quantity = Integer.parseInt(askUser("Introduce la cantidad: "));
                    String noQuantity = "NO";
                    if(quantity < 1){
                        quantity = 0;
                        noQuantity = askUser("Cantidad insuficiente.\n" +
                                "¿Desea marcar el producto como favorito para evitar que se elimine? (SI / NO) ");
                        if (!noQuantity.equals("SI")){
                            break;
                        }
                    }
                    addToList(name, quantity);
                    if(noQuantity.equals("SI")){
                        markAsFavList(ProductList.getInstance().getId(name));
                    }

                    break;
                case "DEL":
                    //Runtime.getRuntime().exec(clearWindow);
                    id = Integer.parseInt(askUser("Introduce la id del producto que quieras eliminar: "));
                    if(!util.productOnListById(id)){
                        System.out.println("ERROR: Producto no existente");
                        break;
                    }
                    deleteFromList(id);
                    break;
                case "MOD":
                    boolean innerLoop = true;
                    id = Integer.parseInt(askUser("Introduce la id del producto que quieras modificar: "));
                    //Runtime.getRuntime().exec(clearWindow);
                    if(!util.productOnListById(id)){
                        innerLoop = false;
                        System.out.println("ERROR: Producto no existente");
                    }

                    while(innerLoop) {
                        System.out.println("\n\t¿Qué parámetro desea modificar?");
                        System.out.println("NAME -> Modificar nombre");
                        System.out.println("QUANTITY -> Modificar cantidad");
                        System.out.println("PRICE -> Modificar precio");
                        System.out.println("CANCEL -> Cancelar modificación");
                        switch(getIntroduced()) {
                            case "NAME":
                                name = askUser("Introduce el nuevo nombre: ");
                                if(util.productOnListByName(name)){
                                    System.out.println("ERROR: Producto ya existente");
                                    break;
                                }
                                modifyNameList(id, name);
                                innerLoop = false;
                                break;
                            case "QUANTITY":
                                quantity = Integer.parseInt(askUser("Introduce la cantidad: "));
                                modifyQuantityList(id, quantity);
                                innerLoop = false;
                                break;
                            case "PRICE":
                                price = Double.parseDouble(askUser("Introduce el precio del producto: "));
                                modifyPriceList(id, price);
                                innerLoop = false;
                                break;
                            case "CANCEL":
                                System.out.println("Cancelando modificación...");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Opción no soportada");
                        }
                    }
                    break;
                case "BUY":
                    //Runtime.getRuntime().exec(clearWindow);
                    id = Integer.parseInt(askUser("Introduce la id del producto que quieras marcar como comprado/no comprado: "));
                    markAsBoughtList(id);
                    break;
                case "FAV":
                    //Runtime.getRuntime().exec(clearWindow);
                    id = Integer.parseInt(askUser("Introduce la id del producto que quieras marcar como favorito/no favorito: "));
                    markAsFavList(id);
                    break;
                case "VIEW":
                    //Runtime.getRuntime().exec(clearWindow);
                    printList();
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    break;
                case "SAVE":
                    System.out.println("Guardando lista...");
                    util.writeList(fileName);
                    ProductList.getInstance().resetList();
                    util.checkList(fileName);
                    break;
                case "EXIT":
                    System.out.println("Finalizando edición de lista...");
                    inLoop = false;
                    break;
                default:
                    System.out.println("Opción no soportada");
            }
        }
    }

    /**
     * Print list into shell
     */

    @Override
    public void printList(){
        System.out.println("\nID\tName\t\tQuantity\tBought\tPrice\tFavorite");
        for(ChosenProduct cpr: ProductList.getInstance().getList()){
            System.out.println(
                    cpr.getId() + "\t" +
                    cpr.getName() + "\t\t" +
                    cpr.getQuantity() + "\t" +
                    cpr.getBoughtToString() + "\t" +
                    cpr.getPriceToString() + "\t" +
                    cpr.getFavoriteToString());
        }
    }

    /**
     * Adds a product to the list
     * @param name of the product
     * @param quantity of the product
     */

    @Override
    public void addToList(String name, int quantity) {
        int id = ProductList.getInstance().getList().size();
        Product pr = new Product(id, name);
        ProductList.getInstance().addProduct(new ChosenProduct(pr, quantity));
    }

    /**
     * Deletes a product from my list
     * @param id of the product
     */

    @Override
    public void deleteFromList(int id) {
        ProductList.getInstance().deleteProduct(id);
    }

    /**
     * Modifies name of product
     * @param id of the product
     * @param name new name for the product
     */

    @Override
    public void modifyNameList(int id, String name) {
        ProductList.getInstance().getChosenProduct(id).setName(name);
    }

    /**
     * Modifies the price of a product in the list
     * @param id of the product
     * @param price new price
     */

    @Override
    public void modifyPriceList(int id, double price) {
        ProductList.getInstance().getChosenProduct(id).setPrice(price);
    }

    /**
     * Modifies the quantity of products in the list
     * @param id of the product
     * @param quantity new quantity
     */

    @Override
    public void modifyQuantityList(int id, int quantity) {
        ProductList.getInstance().getChosenProduct(id).setQuantity(quantity);
    }

    /**
     * It marks the product as favorite
     * @param id of the product
     */

    @Override
    public void markAsFavList(int id) {
        ChosenProduct pr = ProductList.getInstance().getChosenProduct(id);
        pr.setFavorite(!pr.getFavorite());
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */

    @Override
    public void markAsBoughtList(int id) {
        ChosenProduct pr = ProductList.getInstance().getChosenProduct(id);
        pr.setBought(!pr.getBought());
    }
}