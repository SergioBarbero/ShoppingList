import java.util.List;

public class Tests {

    /**
     * List with products
     */

    List<ChosenProduct> finalList;

    /**
     * Tests constructor
     * @param list with all the products
     */

    public Tests(List<ChosenProduct> list){
        finalList = list;
        //TODO -- probar métodos varios de añadir, eliminar, etc de una lista
        Product pr = new Product(3, "producto3");
        finalList.add(new ChosenProduct(pr, 85, true, 0.99, true));
        Product pr2 = new Product(4, "producto4");
        finalList.add(new ChosenProduct(pr2, 188, true, 2, true));
    }

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */

    public List<ChosenProduct> getList(){
        return this.finalList;
    }
}
