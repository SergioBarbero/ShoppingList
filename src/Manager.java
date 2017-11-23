import java.io.IOException;
import java.util.List;

public abstract class Manager {

    private ProductList myList;
    private String mode;

    public Manager(String mode){
        this.mode = mode;
    }

    public Manager() {

    }

    public abstract boolean addToList(String name, int quantity);

    public abstract void deleteFromList(int id);

    public abstract void modifyPriceList(int id, double price);

    public abstract void modifyQuantityList(int id, int quantity);

    public abstract void markAsFavList(int id);

    public abstract void markAsBoughtList(int id);

    public abstract void askInfoProduct() throws IOException;

    public abstract void printList();

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */
    public ProductList getList(){
        return this.myList;
    }
}
