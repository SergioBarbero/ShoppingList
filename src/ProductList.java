import java.util.ArrayList;
import java.util.List;

class ProductList {

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

    static ProductList getInstance(){
        if (instance == null) {
            instance = new ProductList();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Gets the list of products
     * @return myList
     */

    List<ChosenProduct> getList(){
        return myList;
    }

    /**
     * Adds a new product to the list
     * @param product to be added
     * @return true if it was added successfully
     */

    boolean addProduct(ChosenProduct product) {
        boolean added = false;
        if(!getList().contains(product)){
            getList().add(product);
            added = true;
        }
        return added;
    }

    /**
     * Deletes a product from the list
     * @param id of the product to be deleted
     * @return true if it was deleted successfully
     */

    boolean deleteProduct(int id){
        boolean deleted = false;
        if(id<getList().size() && id>=0) {
            getList().remove(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Gets product with chosen id from list
     * @param id of the product
     * @return ChosenProduct with id
     */

    ChosenProduct getChosenProduct(int id){
        return getList().get(id);
    }

    /**
     * Clear list
     */

    void resetList(){
        this.myList = new ArrayList<>();
    }

}