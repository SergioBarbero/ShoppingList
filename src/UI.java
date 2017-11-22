import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UI extends Manager{

    private int id;


    /**
     * Initialize an UI, the initial ID will be the one from the next product to insert into the list
     * @param mode mode of game, UI or GUI
     */
    public UI(String mode) {
        super(mode);
    }

    public UI(){

    }

    /**
     * Gets a String from the user
     * @return String returned
     */
    public String getIntroduced(){
        boolean written = false;
        String option = "";
        while (!written){
            option = new Scanner(System.in).next();
            written = true;
        }
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

            if(getIntroduced().equals("ADD")){
                //Runtime.getRuntime().exec("cls");
                System.out.print("Introduce el nombre del producto: ");
                String name = getIntroduced();
                System.out.print("Introduce la cantidad: ");
                quantity = Integer.parseInt(getIntroduced());
                addToList(name, quantity);
            }else if(getIntroduced().equals("DEL")){
                //Runtime.getRuntime().exec("cls");
                System.out.print("Introduce la id del producto que quieras eliminar: ");
                id = Integer.parseInt(getIntroduced());
                deleteFromList(id);
            }else if(getIntroduced().equals("MOD")){
                //Runtime.getRuntime().exec("cls");
                System.out.print("Introduce la id del producto que quieras modificar: ");
                id = Integer.parseInt(getIntroduced());
                boolean innerLoop = true;
                while(innerLoop) {
                    System.out.println("¿Qué desea modificar?");
                    System.out.println("QUANTITY -> modificar cantidad");
                    System.out.println("PRICE -> modificar precio");
                    System.out.println("CANCEL -> cancelar modificación");
                    String option = getIntroduced();
                    if (option.equals("QUANTITY")) {
                        System.out.print("Introduce la cantidad: ");
                        quantity = Integer.parseInt(getIntroduced());
                        modifyQuantityList(id, quantity);
                        innerLoop = false;
                    } else if (option.equals("PRICE")) {
                        System.out.print("Introduce el precio del producto: ");
                        price = Double.parseDouble(getIntroduced());
                        modifyPriceList(id, price);
                        innerLoop = false;
                    } else if(option.equals("CANCEL")) {
                        System.out.println("Cancelando modificación...");
                        innerLoop = false;
                    } else {
                        System.out.println("Opción no soportada");
                    }
                }
            }else if(getIntroduced().equals("BUY")){
                //Runtime.getRuntime().exec("cls");
                System.out.print("Introduce la id del producto que quieras marcar como comprado/no comprado: ");
                id = Integer.parseInt(getIntroduced());
                markAsBoughtList(id);
            }else if(getIntroduced().equals("FAV")) {
                //Runtime.getRuntime().exec("cls");
                System.out.print("Introduce la id del producto que quieras marcar como favorito/no favorito: ");
                id = Integer.parseInt(getIntroduced());
                markAsFavList(id);
            }else if(getIntroduced().equals("VER")){
                //Runtime.getRuntime().exec("cls");
                printList();
                new Scanner(System.in).next();
            }else if(getIntroduced().equals("EXIT")){
                System.out.println("Finalizando edición de lista...");
                inLoop = false;
            } else{
                System.out.println("Opción no soportada");
            }
        }
    }

    @Override
    public void printList(){
        System.out.println("ID\tName\tQuantity\tBought\tPrice\tFavorite");
        for(ChosenProduct cpr: ProductList.getInstance().getList()){
            System.out.println(cpr.getId() + cpr.getName() + cpr.getQuantity() + cpr.getBoughtToString() + cpr.getPriceToString() + cpr.getFavoriteToString());
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
     * Modifies the price of a product in the list
     * @param id of the product
     * @param price new price
     */
    @Override
    public void modifyPriceList(int id, double price) {
        ProductList.getInstance().getList().get(id).setPrice(id);
    }

    /**
     * Modifies the quantity of products in the list
     * @param id of the product
     * @param quantity new quantity
     */
    @Override
    public void modifyQuantityList(int id, int quantity) {
        ProductList.getInstance().getList().get(id).setQuantity(quantity);
    }

    /**
     * It marks the product as favorite
     * @param id of the product
     */
    @Override
    public void markAsFavList(int id) {
        ChosenProduct pr = ProductList.getInstance().getList().get(id);
        pr.setFavorite(!pr.getFavorite());
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */
    @Override
    public void markAsBoughtList(int id) {
        ChosenProduct pr = ProductList.getInstance().getList().get(id);
        pr.setBought(!pr.getBought());
    }
}