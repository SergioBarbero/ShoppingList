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

    private List<Product> myList = new ArrayList<>();

    /**
     * ProductList Constructor
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
     * @return Product with id
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

    /**
     * Gets ID from product with chosen name
     * @param name of the product
     * @return Id of the product or -1 if no exists
     */

    public int getId(String name){
        for(Product pr: getList()){
            if(name.equals(pr.getName())){
                return pr.getId();
            }
        }
        return -1;
    }

}