import java.io.IOException;
import java.util.Scanner;

public class UI extends Manager{

    /*
        TODO -- Future Feature: we should clean cmd window with following line in some parts of the run
        Runtime.getRuntime().exec("cls");
    */

    /**
     * TODO -- Feature Switch: allows developers to work with same file and branch on git, while some features are under development
     */

    private boolean fSFormat = false;

    /**
     * UI constructor
     */

    UI(){

    }

    /**
     * Gets a String from the user
     * @return String returned
     */

    private String getIntroduced(){
        return  new Scanner(System.in).next();
    }

    /**
     * Asks user for list's name
     * @return name of the list
     */

    @Override
    public String askListName(){
        System.out.println("Introduzca en nombre de la lista: ");
        return getIntroduced();
    }

    /**
     * Ask the user for file format
     * @return format of the file
     */

    @Override
    public String askFormatFile(){
        String format = ".tsv";
        if(fSFormat){
            //TODO -- Future Feature: switch with several file types
        }
        return format;
    }

    /**
     * Ask information to the user
     * @param fileName name of file to write the data
     * @throws IOException exception management for write/read from files
     */

    @Override
    public void askInfoProduct(String fileName) throws IOException {

        boolean inLoop = true;
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

                    System.out.print("Introduce el nombre del producto: ");
                    String name = getIntroduced();
                    System.out.print("Introduce la cantidad: ");
                    quantity = Integer.parseInt(getIntroduced());
                    if(!addToList(name, quantity)){
                        System.out.print("ERROR: Producto ya existente");
                    }
                    break;
                case "DEL":
                    //Runtime.getRuntime().exec(clearWindow);

                    System.out.print("Introduce la id del producto que quieras eliminar: ");
                    int id = Integer.parseInt(getIntroduced());
                    if(!deleteFromList(id)){
                        System.out.print("ERROR: Producto no existente");
                    }
                    break;
                case "MOD":
                    //Runtime.getRuntime().exec(clearWindow);

                    System.out.print("Introduce la id del producto que quieras modificar: ");
                    id = Integer.parseInt(getIntroduced());
                    boolean innerLoop = true;
                    while(innerLoop) {
                        System.out.println("¿Que desea modificar?");
                        System.out.println("NAME -> Modificar nombre");
                        System.out.println("QUANTITY -> Modificar cantidad");
                        System.out.println("PRICE -> Modificar precio");
                        System.out.println("CANCEL -> Cancelar modificacion");
                        switch(getIntroduced()) {
                            case "NAME":
                                System.out.print("Introduce el nuevo nombre: ");
                                name = getIntroduced();
                                modifyNameList(id, name);
                                innerLoop = false;
                                break;
                            case "QUANTITY":
                                System.out.print("Introduce la cantidad: ");
                                quantity = Integer.parseInt(getIntroduced());
                                modifyQuantityList(id, quantity);
                                innerLoop = false;
                                break;
                            case "PRICE":
                                System.out.print("Introduce el precio del producto: ");
                                price = Double.parseDouble(getIntroduced());
                                modifyPriceList(id, price);
                                innerLoop = false;
                                break;
                            case "CANCEL":
                                System.out.println("Cancelando modificacion...");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Opcion no soportada");
                        }
                    }
                    break;
                case "BUY":
                    //Runtime.getRuntime().exec(clearWindow);

                    System.out.print("Introduce la id del producto que quieras marcar como comprado/no comprado: ");
                    id = Integer.parseInt(getIntroduced());
                    markAsBoughtList(id);
                    break;
                case "FAV":
                    //Runtime.getRuntime().exec(clearWindow);

                    System.out.print("Introduce la id del producto que quieras marcar como favorito/no favorito: ");
                    id = Integer.parseInt(getIntroduced());
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
                    Utilities util = new Utilities();
                    util.writeList(fileName);
                    util.deleteList();
                    util.checkList(fileName);
                    break;
                case "EXIT":
                    System.out.println("Finalizando edición de lista...");
                    inLoop = false;
                    break;
                default:
                    System.out.println("Opcion no soportada");
            }
        }
    }

    /**
     * Print list into shell
     */

    @Override
    public void printList(){
        System.out.println("\nID\tName\tQuantity\tBought\tPrice\tFavorite");
        for(ChosenProduct cpr: ProductList.getInstance().getList()){
            System.out.println(
                    cpr.getId() + "\t" +
                    cpr.getName() + "\t" +
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
     * @return true/false if able to add product
     */

    @Override
    public boolean addToList(String name, int quantity) {
        int id = ProductList.getInstance().getList().size();
        Product pr = new Product(id, name);
        return ProductList.getInstance().addProduct(new ChosenProduct(pr, quantity));
    }

    /**
     * Deletes a product from my list
     * @param id of the product
     * @return true/false if able to delete product
     */

    @Override
    public boolean deleteFromList(int id) {
        return ProductList.getInstance().deleteProduct(id);
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