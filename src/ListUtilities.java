
class ListUtilities {

    /**
     * ListUtilities constructor
     */

    public ListUtilities(){

    }

    /**
     * Check if a product with that name is already on the list
     * @param name of the product
     * @return true/false
     */

    public boolean productOnListByName(String name){
        boolean onList = false;
        for(Product pr: ProductList.getInstance().getList()){
            if(pr.getName().equals(name)){
                onList = true;
                break;
            }
        }
        return onList;
    }

    /**
     * Check if a product with that ID is already on the list
     * @param id of the product
     * @return true/false
     */

    public boolean productOnListById(int id){
        boolean onList = false;
        for(Product pr: ProductList.getInstance().getList()){
            if(pr.getId() == id){
                onList = true;
                break;
            }
        }
        return onList;
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
        ProductList.getInstance().deleteProduct(id);
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
