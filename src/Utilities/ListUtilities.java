package Utilities;
import Products.*;

public class ListUtilities {

    /**
     * Utilities.ListUtilities constructor
     */

    public ListUtilities(){

    }

    /**
     * Know if a product exists by his name
     * @param name of the product
     * @return true/false
     */

    public boolean productByNameExist(String name){
        return productIDByName(name) > -1;
    }

    /**
     * Gets id of a product by his name
     * @param name of the product
     * @return id of the product
     */

    public int productIDByName(String name){
        for(Product pr: ProductList.getInstance().getList()){
            if(pr.getName().equals(name)){
                return pr.getId();
            }
        }
        return -1;
    }

    /**
     * Adds a product to the list
     * @param name of the product
     * @param quantity of the product
     */

    public void addToList(String name, int quantity) {
        int id = ProductList.getInstance().getList().size();
        ProductList.getInstance().addProduct(new Product(id, name, quantity));
    }

    /**
     * Deletes a product from my list
     * @param id of the product
     */

    public void deleteFromList(int id) {
        ProductList pLI = ProductList.getInstance();
        pLI.deleteProduct(id);
        for(int i = id; i < pLI.getList().size(); i++){
            pLI.getProduct(i).setId(pLI.getProduct(i).getId()-1);
        }
    }

    /**
     * Modifies name of product
     * @param id of the product
     * @param name new name for the product
     */

    public void modifyNameList(int id, String name) {
        ProductList.getInstance().getProduct(id).setName(name);
    }

    /**
     * Modifies the price of a product in the list
     * @param id of the product
     * @param price new price
     */

    public void modifyPriceList(int id, double price) {
        ProductList.getInstance().getProduct(id).setPrice(price);
    }

    /**
     * Modifies the quantity of products in the list
     * @param id of the product
     * @param quantity new quantity
     */

    public void modifyQuantityList(int id, int quantity) {
        ProductList.getInstance().getProduct(id).setQuantity(quantity);
    }

    /**
     * It marks the product as favorite
     * @param id of the product
     */

    public void markAsFavList(int id) {
        Product pr = ProductList.getInstance().getProduct(id);
        pr.setFavorite(!pr.getFavorite());
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */

    public void markAsBoughtList(int id) {
        Product pr = ProductList.getInstance().getProduct(id);
        pr.setBought(!pr.getBought());
    }

}
