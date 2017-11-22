import java.util.ArrayList;
import java.util.List;

public class ProductList {

    /**
     * Instance from singleton
     */

    private static ProductList instance = null;

    /**
     * List with products
     */

    private List<ChosenProduct> myList = new ArrayList<>();

    /**
     * ProductList Constructor
     */

    private ProductList(){ }

    /**
     * Get instance from singleton if exists or create new one
     * @return instance from singleton
     */

    public static ProductList getInstance(){
        return ((instance == null) ? new ProductList() : instance);
    }

    /**
     * Gets the list of products
     * @return myList
     */

    public List<ChosenProduct> getList(){
        return myList;
    }

    /**
     * Adds a new product to the list
     * @param product to be added
     * @return true if it was added successfully
     */

    public boolean addProduct(ChosenProduct product) {
        boolean added = false;
        if(!myList.contains(product)){
            myList.add(product);
            added = true;
        }
        return added;
    }

    /**
     * Deletes a product from the list
     * @param id of the product to be deleted
     * @return true if it was deleted successfully
     */

    public boolean deleteProduct(int id){
        boolean deleted = false;
        if((myList.get(id).getBought() || myList.get(id).getQuantity() == 0) && !myList.get(id).getFavorite()){
            myList.remove(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Increases the quantity of a product from the list
     * @param id of the product
     */

    public void increaseQuantity(int id){
        int myQuantity = myList.get(id).getQuantity();
        myList.get(id).setQuantity(++myQuantity);
    }

    /**
     * Decreases the quantity of a product from the list
     * @param id of the product
     */

    public void decreaseQuantity(int id){
        int myQuantity = myList.get(id).getQuantity();
        if(myQuantity > 1) {
            myList.get(id).setQuantity(--myQuantity);
        }else{
            //TODO: Check whether it's favorite or not, if it is, let it, otherwise, delete it
        }
    }

    public void setQuantity(int id, int quantity){
        myList.get(id).setQuantity(quantity);

    }

    /**
     * Marks a product as bought
     * @param id of the product
     */
    public void markAsBought(int id){
        myList.get(id).setBought(!myList.get(id).getBought());

    }

    /**
     * Marks a product as favorite
     * @param id of the product
     */

    public void markAsFavourite(int id){
        myList.get(id).setBought(!myList.get(id).getFavorite());
    }
}