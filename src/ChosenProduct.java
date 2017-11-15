public class ChosenProduct{

    /**
     * Product
     */
    private Product product;

    /**
     * Cuantity of this product in list
     */
    private int cuantity;

    /**
     * True if this product allready bought
     */

    private boolean bought;

    /**
     * If we have bought this product, we know ohw much it costed
     */

    private double price;

    /**
     * We can make this product favorite
     */

    private boolean favorite;

    /**
     * Product constructor
     * @param product object Product
     * @param cuantity number of this products
     */
    public ChosenProduct(Product product, int cuantity){
        this.bought = false;
        this.favorite = false;
        this.product = product;
        this.cuantity = cuantity;
    }

    /**
     * Returns name of my product
     * @return name
     */
    private String getName(){
        return this.product.getName();
    }

    /**
     * Returns id of my product
     * @return id
     */
    public int getId(){
        return this.product.getId();
    }


    public void increaseCuantity(int id, int c){

    }

}
