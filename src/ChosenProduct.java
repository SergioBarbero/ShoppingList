public class ChosenProduct{

    /**
     * Product
     */
    private Product product;

    /**
     * Quantity of this product in list
     */
    private int quantity;

    /**
     * True if this product already bought
     */

    private boolean bought;

    /**
     * If we have bought this product, we know how much it cost
     */

    private double price;

    /**
     * We can make this product favorite
     */

    private boolean favorite;

    /**
     * Product constructor
     * @param product object Product
     * @param quantity number of this products
     */
    public ChosenProduct(Product product, int quantity){
        this.product = product;
        this.bought = false;
        this.favorite = false;
        this.quantity = quantity;
    }

    /**
     * Returns name of my product
     * @return name
     */
    public String getName(){
        return this.product.getName();
    }


    public void changeName(String name){
        this.product.setName(name);
    }

    /**
     * Returns id of my product
     * @return id
     */
    public int getId(){
        return this.product.getId();
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}
