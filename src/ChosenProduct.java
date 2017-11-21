public class ChosenProduct{

    /**
     * Product
     */

    private Product product = null;

    /**
     * Quantity of this product
     */

    private int quantity = 1;

    /**
     * True if this product already bought
     */

    private boolean bought = false;

    /**
     * If we have bought this product, we know how much it cost
     */

    private double price = -1;

    /**
     * We can make this product favorite
     */

    private boolean favorite = false;

    /**
     * Product constructor
     * @param product object Product
     * @param quantity number of this products
     */

    public ChosenProduct(Product product, int quantity, boolean bought, double price, boolean favorite){
        this.product = product;
        this.quantity = quantity;
        this.bought = bought;
        this.price = price;
        this.favorite = favorite;
    }

    /**
     * Returns the name of the product
     * @return name
     */

    public String getName(){
        return this.product.getName();
    }

    /**
     * Allows the user to change the product name
     * @param name new name to this product
     */

    public void changeName(String name){
        this.product.setName(name);
    }

    /**
     * Gets the id of the product
     * @return id
     */

    public int getId(){
        return this.product.getId();
    }

    /**
     * Gets the quantity of the product
     * @return quantity
     */

    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Set a new quantity to the product
     * @param quantity new quantity for the product
     */

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Gets the price of the product
     * @return price
     */

    public double getPrice(){
        return this.price;
    }

    /**
     * Set a new price to the product
     * @param price new price for the product
     */

    public void setPrice(double price){
        this.price = price;
    }

    /**
     * In order to print or save the product price, we convert it,
     *  so if there is no value yet, it desplays " - "
     * @return
     */

    public String getPriceToString(){
        return ((this.price < 0) ? " - " : String.valueOf(this.price));
    }

    /**
     * Gets true if the product has already be bought
     * @return bought
     */

    public boolean getBought(){
        return this.bought;
    }

    /**
     * Sets bought to true/false
     * @param bought
     */

    public void setBought(boolean bought){
        this.bought = bought;
    }

    /**
     * In order to know if our product is already bought or not,
     *  we convert it so it displays YES/NO, instead of TRUE/FALSE
     * @return YES/NO as string
     */

    public String getBoughtToString(){
        return ((this.bought) ? "YES" : "NO");
    }

    /**
     * Gets true if the product is a favorite one
     * @return favorite
     */

    public boolean getFavorite(){
        return this.favorite;
    }

    /**
     * Sets favorite to true/false
     * @param favorite
     */

    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    /**
     * In order to know if our product is a favorite one or not,
     *  we convert it so it displays YES/NO, instead of TRUE/FALSE
     * @return YES/NO as string
     */

    public String getFavoriteToString(){
        return ((this.favorite) ? "YES" : "NO");
    }
}
