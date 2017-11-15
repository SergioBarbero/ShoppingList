import java.util.ArrayList;
import java.util.List;

public class ProductList {

    /**
     * List with products
     */

    private static List<ChosenProduct> myList;

    /**
     * ProductList Constructor
     */

    public ProductList(){
        myList = new ArrayList<>();
    }

    /**
     * Gets the list of products
     * @return myList
     */

    public static List<ChosenProduct> getList(){
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
     * @param quantity to be increased
     */

    public void increaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuantity();
        myList.get(id).setQuantity(myQuantity + quantity);
    }

    /**
     * Decreases the quantity of a product from the list
     * @param id of the product
     * @param quantity to be decreased
     */

    public void decreaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuantity();
        int finalQuantity = myQuantity - quantity;
        if (finalQuantity > 0)
            myList.get(id).setQuantity(finalQuantity);
        else
            myList.get(id).setQuantity(0);
    }

    /**
     * Marks a product as bought
     * @param id of the product
     */

    public void markAsBought(int id){
        myList.get(id).setBought(true);

    }

    /**
     * Marks a product as favorite
     * @param id of the product
     */

    public void markAsFavourite(int id){
        myList.get(id).setFavorite(true);
    }

}
