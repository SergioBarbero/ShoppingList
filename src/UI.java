import java.util.Scanner;

public class UI extends Manager{

    private int id;
    private String introducedAction;
    private double price;
    private String name;
    private int quantity;


    public UI(String mode) {
        super(mode);
        id = ProductList.getInstance().getList().size();
    }

    public String getIntroduced(){
        boolean written = false;
        String option = "";
        while (!written){
            option = new Scanner(System.in).next();
            written = true;
        }
        return  option;
    }

    public void askInfoProduct(){
        int id = Integer.parseInt(getIntroduced());
        String introducedAction = getIntroduced();
        boolean inLoop = true;
        while (inLoop == true) {
            if(introducedAction == "ADD"){
                System.out.print("Introduce el nombre del producto: ");
                name = getIntroduced();
                System.out.print("\n Introduce el precio del producto: ");
                price = Double.parseDouble(getIntroduced());
                System.out.print("\n Introduce la cantidad: ");
                quantity = Integer.parseInt(getIntroduced());
                addToList(id, name, price, quantity);
            }else if(introducedAction == "DEL"){
                System.out.print("Introduce la id del producto que quieras eliminar: ");
                id = Integer.parseInt(getIntroduced());
                deleteFromList(id);
            }else if(introducedAction == "MOD"){
                System.out.print("Introduce la id del producto que quieras modificar: ");
                id = Integer.parseInt(getIntroduced());
                System.out.print("\nIntroduce \"PRICE\" si quieres modificar el precio; \"QUANTITY\" si quieres modificar la cantidad: ");
                String option = getIntroduced();
                System.out.print("");
                if(option == "PRICE"){
                    System.out.print("\nIntroduce el precio del producto: ");
                    price = Double.parseDouble(getIntroduced());
                    modifyPriceList(id, price);
                }else if(option == "QUANTITY"){
                    System.out.print("Introduce la cantidad: ");
                    quantity = Integer.parseInt(getIntroduced());
                    modifyQuantityList(id, quantity);
                }else{
                    System.out.println("Opción no soportada");
                    inLoop = false;
                }
            }else if(introducedAction == "BOUGHT"){
                System.out.print("Introduce la id del producto que quieras marcar como comprado: ");
                id = Integer.parseInt(getIntroduced());
                markAsBoughtList(id);
            }else if(introducedAction == "FAV"){
                System.out.print("Introduce la id del producto que quieras marcar como favorito: ");
                id = Integer.parseInt(getIntroduced());
                markAsFavList(id);
            }else{
                inLoop = false;
            }
        }
    }

    @Override
    public void addToList(int id, String name, double price, int quantity) {
        ProductList.getInstance().addProduct(new ChosenProduct(new Product(id, name),quantity, false, price, false));
    }

    @Override
    public void deleteFromList(int id) {
        ProductList.getInstance().deleteProduct(id);
    }

    @Override
    public void modifyPriceList(int id, double price) {
        ProductList.getInstance().getList().get(id).setPrice(id);
    }

    @Override
    public void modifyQuantityList(int id, int quantity) {

    }

    @Override
    public void markAsFavList(int id) {

    }

    @Override
    public void markAsBoughtList(int id) {

    }
}
