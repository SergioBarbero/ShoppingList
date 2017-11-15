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
    }

    /**
     * Get the final list
     * @return finalList with the modified list of products
     */

    public List<ChosenProduct> getList(){
        return this.finalList;
    }
}
