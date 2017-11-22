import java.util.List;

public class Tests {

    /**
     * List with products
     */

    ProductList finalList;

    /**
     * Tests constructor
     * @param list with all the products
     */

    public Tests(ProductList list){
        this.finalList = list;

        int id = 1;
        insert();
        delete(id);
    }

    public void insert(){
        int id = finalList.getList().size();
        Product pr2 = new Product(id, "producto" + id);
        finalList.addProduct(new ChosenProduct(pr2, 2));
    }

    public void delete(int id){
        finalList.deleteProduct(id);
    }

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */

    public ProductList getList(){
        return this.finalList;
    }
}
