import java.util.Objects;
import java.util.Scanner;

public class UI extends Manager{

    private int id;
    private String introducedAction;


    /**
     * Initialize an UI, the initial ID will be the one from the next product to insert into the list
     * @param mode mode of game, UI or GUI
     */
    public UI(String mode) {
        super(mode);
        id = ProductList.getInstance().getList().size();
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
    public void askInfoProduct(){
        int id = Integer.parseInt(getIntroduced());
        String introducedAction = getIntroduced();
        boolean inLoop = true;
        while (inLoop) {
            int quantity;
            double price;
            if(Objects.equals(introducedAction, "ADD")){
                System.out.print("Introduce el nombre del producto: ");
                String name = getIntroduced();
                System.out.print("\n Introduce el precio del producto: ");
                price = Double.parseDouble(getIntroduced());
                System.out.print("\n Introduce la cantidad: ");
                quantity = Integer.parseInt(getIntroduced());
                addToList(id, name, price, quantity);
            }else if(introducedAction.equals("DEL")){
                System.out.print("Introduce la id del producto que quieras eliminar: ");
                id = Integer.parseInt(getIntroduced());
                deleteFromList(id);
            }else if(introducedAction.equals("MOD")){
                System.out.print("Introduce la id del producto que quieras modificar: ");
                id = Integer.parseInt(getIntroduced());
                System.out.print("\nIntroduce \"PRICE\" si quieres modificar el precio; \"QUANTITY\" si quieres modificar la cantidad: ");
                String option = getIntroduced();
                System.out.print("");
                if(option.equals("PRICE")){
                    System.out.print("\nIntroduce el precio del producto: ");
                    price = Double.parseDouble(getIntroduced());
                    modifyPriceList(id, price);
                }else if(option.equals("QUANTITY")){
                    System.out.print("Introduce la cantidad: ");
                    quantity = Integer.parseInt(getIntroduced());
                    modifyQuantityList(id, quantity);
                }else{
                    System.out.println("Opción no soportada");
                    inLoop = false;
                }
            }else if(introducedAction.equals("BOUGHT")){
                System.out.print("Introduce la id del producto que quieras marcar como comprado: ");
                id = Integer.parseInt(getIntroduced());
                markAsBoughtList(id);
            }else if(introducedAction.equals("FAV")){
                System.out.print("Introduce la id del producto que quieras marcar como favorito: ");
                id = Integer.parseInt(getIntroduced());
                markAsFavList(id);
            }else{
                inLoop = false;
            }
        }
    }

    /**
     * Adds a product to the list
     * @param id of the product
     * @param name of the product
     * @param price of the product
     * @param quantity of the product
     */
    @Override
    public void addToList(int id, String name, double price, int quantity) {
        ProductList.getInstance().addProduct(new ChosenProduct(new Product(id, name),quantity, false, price, false));
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
        ProductList.getInstance().markAsFavourite(id);
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */
    @Override
    public void markAsBoughtList(int id) {
        ProductList.getInstance().markAsBought(id);
        //TODO: Good idea ProductList.getInstance().getList().get(id).setBought()
    }
}