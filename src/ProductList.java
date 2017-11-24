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
        //if((myList.get(id).getBought() || myList.get(id).getQuantity() == 0) && !myList.get(id).getFavorite()){
        if(id<getList().size() && id>=0) {
            myList.remove(id);
            deleted = true;
        }
        //}
        return deleted;
    }

}