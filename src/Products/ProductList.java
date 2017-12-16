package Products;
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

    private List<Product> myList = new ArrayList<>();

    /**
     * Products.ProductList Constructor
     */

    private ProductList(){ }

    /**
     * Get instance from singleton if exists or create new one
     * @return instance from singleton
     */

    public static ProductList getInstance(){
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

    public List<Product> getList(){
        return myList;
    }

    /**
     * Adds a new product to the list
     * @param product to be added
     */

    public void addProduct(Product product) {
        getList().add(product);
    }

    /**
     * Deletes a product from the list
     * @param id of the product to be deleted
     */

    public void deleteProduct(int id){
        getList().remove(id);
    }

    /**
     * Gets product with chosen id from list
     * @param id of the product
     * @return Products.Product with id
     */

    public Product getProduct(int id){
        return getList().get(id);
    }

    /**
     * Clear list
     */

    public void resetList(){
        this.myList = new ArrayList<>();
    }

}