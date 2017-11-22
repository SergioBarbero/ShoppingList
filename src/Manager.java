import java.util.List;

public abstract class Manager {

    private int id;
    private String introducedAction;
    private ProductList myList;
    private double price;
    private String name;
    private int quantity;

    private String mode;

    public Manager(String mode){
    }

    public abstract void addToList(int id, String name, double price, int quantity);

    public abstract void deleteFromList(int id);

    public abstract void modifyPriceList(int id, double price);

    public abstract void modifyQuantityList(int id, int quantity);

    public abstract void markAsFavList(int id);

    public abstract void markAsBoughtList(int id);

    public abstract void askInfoProduct();

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */
    public ProductList getList(){
        return this.myList;
    }
}
