class ChosenProduct{

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

    ChosenProduct(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * ChosenProduct constructor with overload
     * @param product pbject Product
     * @param quantity number of this products
     * @param bought boolean if product is already bought
     * @param price price of product
     * @param favorite boolean if product is marked as favorite
     */

    ChosenProduct(Product product, int quantity, boolean bought, double price, boolean favorite){
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

    String getName(){
        return this.product.getName();
    }

    /**
     * Allows the user to change the product name
     * @param name new name to this product
     */

    void setName(String name){
        this.product.setName(name);
    }

    /**
     * Gets the id of the product
     * @return id
     */

    int getId(){
        return this.product.getId();
    }

    /**
     * Gets the quantity of the product
     * @return quantity
     */

    int getQuantity(){
        return this.quantity;
    }

    /**
     * Set a new quantity to the product
     * @param quantity new quantity for the product
     */

    void setQuantity(int quantity){
        this.quantity = quantity;
        if(quantity <= 0){
            this.quantity = 0;
            if(!getFavorite()){
                ProductList.getInstance().deleteProduct(getId());
            }
        }
    }

    /**
     * Get the price of the product
     * @return price of the product
     */

    public double getPrice(){
        return this.price;
    }


    /**
     * Increases the quantity of the product by 1
     */

    public void increaseQuantity(){
        setQuantity(getQuantity()+1);
    }

    /**
     * Decreases the quantity of a product by 1
     */

    public void decreaseQuantity(){
        if(getQuantity() > 0) {
            setQuantity(getQuantity()-1);
        }else if(getQuantity() <= 0){
            setQuantity(0);
            if(!getFavorite()){
                ProductList.getInstance().deleteProduct(getId());
            }
        }
    }

    /**
     * Set a new price to the product
     * @param price new price for the product
     */

    void setPrice(double price){
        this.price = price;
    }

    /**
     * In order to print or save the product price, we convert it,
     *  so if there is no value yet, it desplays " - "
     * @return price of the product or '-' if has no price
     */

    String getPriceToString(){
        return ((this.price < 0) ? " - " : String.valueOf(this.price));
    }

    /**
     * Gets true if the product has already be bought
     * @return boolean if product is bought or not
     */

    boolean getBought(){
        return this.bought;
    }

    /**
     * Sets bought to true/false
     * @param bought if product is bought or not
     */

    void setBought(boolean bought){
        this.bought = bought;
        if(getBought() && !getFavorite()){
            ProductList.getInstance().deleteProduct(getId());
        }
    }

    /**
     * In order to know if our product is already bought or not,
     *  we convert it so it displays YES/NO, instead of TRUE/FALSE
     * @return YES/NO as string
     */

    String getBoughtToString(){
        return ((this.bought) ? "YES" : "NO");
    }

    /**
     * Gets true if the product is a favorite one
     * @return boolean if favorite is bought or not
     */

    boolean getFavorite(){
        return this.favorite;
    }

    /**
     * Sets favorite to true/false
     * @param favorite product is favorite or not
     */

    void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    /**
     * In order to know if our product is a favorite one or not,
     *  we convert it so it displays YES/NO, instead of TRUE/FALSE
     * @return YES/NO as string
     */

    String getFavoriteToString(){
        return ((this.favorite) ? "YES" : "NO");
    }
}
