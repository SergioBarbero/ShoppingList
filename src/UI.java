import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UI extends Manager{

    private int id;
    ProductList pl;

    /**
     * Initialize an UI, the initial ID will be the one from the next product to insert into the list
     * @param mode mode of game, UI or GUI
     */
    public UI(String mode) {
        super(mode);
    }

    public UI(ProductList prodList){
        pl = prodList;
    }

    /**
     * Gets a String from the user
     * @return String returned
     */
    public String getIntroduced(){
        //boolean written = false;
        //String option = "";
        //while (!written){
        String option = new Scanner(System.in).next();
            //written = true;
        //}
        return  option;
    }

    /**
     * Ask information to the user
     */
    @Override
    public void askInfoProduct() throws IOException {

        /*
        TODO -- sería conveniente borrar la pantalla de comandos en las ocasiones señaladas mediante el comando:
        Runtime.getRuntime().exec("cls");
         */

        /*
        TODO -- solucionar esto que no funciona
         */

        boolean inLoop = true;
        int quantity;
        double price;
        while (inLoop) {
            //Runtime.getRuntime().exec("cls");
            System.out.println("\n\t¿Qué operación desea realizar?");
            System.out.println("ADD -> añade nuevo producto");
            System.out.println("DEL -> elimina un producto");
            System.out.println("MOD -> modifica un producto");
            System.out.println("BUY -> marcar producto como comprado o no comprado");
            System.out.println("FAV -> marcar producto como favorito o no favorito");
            System.out.println("VER -> visualización de la lista");
            System.out.println("EXIT -> dejar de editar y guardar los cambios");

            switch(getIntroduced()){
                case "ADD":
                    //Runtime.getRuntime().exec("cls");
                    System.out.print("Introduce el nombre del producto: ");
                    String name = getIntroduced();
                    System.out.print("Introduce la cantidad: ");
                    quantity = Integer.parseInt(getIntroduced());
                    if(!addToList(name, quantity)){
                        System.out.print("ERROR: Producto ya existente");
                    }
                    break;
                case "DEL":
                    //Runtime.getRuntime().exec("cls");
                    System.out.print("Introduce la id del producto que quieras eliminar: ");
                    id = Integer.parseInt(getIntroduced());
                    deleteFromList(id);
                    break;
                case "MOD":
                    //Runtime.getRuntime().exec("cls");
                    System.out.print("Introduce la id del producto que quieras modificar: ");
                    id = Integer.parseInt(getIntroduced());
                    boolean innerLoop = true;
                    while(innerLoop) {
                        System.out.println("¿Qué desea modificar?");
                        System.out.println("QUANTITY -> modificar cantidad");
                        System.out.println("PRICE -> modificar precio");
                        System.out.println("CANCEL -> cancelar modificación");
                        switch(getIntroduced()) {
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
                                System.out.println("Cancelando modificación...");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Opción no soportada");
                        }
                    }
                    break;
                case "BUY":
                    //Runtime.getRuntime().exec("cls");
                    System.out.print("Introduce la id del producto que quieras marcar como comprado/no comprado: ");
                    id = Integer.parseInt(getIntroduced());
                    markAsBoughtList(id);
                    break;
                case "FAV":
                    //Runtime.getRuntime().exec("cls");
                    System.out.print("Introduce la id del producto que quieras marcar como favorito/no favorito: ");
                    id = Integer.parseInt(getIntroduced());
                    markAsFavList(id);
                    break;
                case "VER":
                    //Runtime.getRuntime().exec("cls");
                    printList();
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
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

    @Override
    public void printList(){
        System.out.println("ID\tName\tQuantity\tBought\tPrice\tFavorite");
        for(ChosenProduct cpr: pl.getList()){
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
     */
    @Override
    public boolean addToList(String name, int quantity) {
        int id = pl.getList().size();
        Product pr = new Product(id, name);
        return pl.addProduct(new ChosenProduct(pr, quantity));
    }

    /**
     * Deletes a product from my list
     * @param id of the product
     */
    @Override
    public void deleteFromList(int id) {
        pl.deleteProduct(id);
    }

    /**
     * Modifies the price of a product in the list
     * @param id of the product
     * @param price new price
     */
    @Override
    public void modifyPriceList(int id, double price) {
        pl.getList().get(id).setPrice(price);
    }

    /**
     * Modifies the quantity of products in the list
     * @param id of the product
     * @param quantity new quantity
     */
    @Override
    public void modifyQuantityList(int id, int quantity) {
        pl.getList().get(id).setQuantity(quantity);
    }

    /**
     * It marks the product as favorite
     * @param id of the product
     */
    @Override
    public void markAsFavList(int id) {
        ChosenProduct pr = pl.getList().get(id);
        pr.setFavorite(!pr.getFavorite());
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */
    @Override
    public void markAsBoughtList(int id) {
        ChosenProduct pr = pl.getList().get(id);
        pr.setBought(!pr.getBought());
    }
}