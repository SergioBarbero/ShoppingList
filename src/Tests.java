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
        //TODO -- probar métodos varios de añadir, eliminar, etc de una lista
        Product pr = new Product(finalList.getList().size() + 1, "producto3");
        finalList.addProduct(new ChosenProduct(pr, 4));
        Product pr2 = new Product(finalList.getList().size() + 1, "producto4");
        finalList.addProduct(new ChosenProduct(pr2, 50));
    }

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */

    public ProductList getList(){
        return this.finalList;
    }
}
