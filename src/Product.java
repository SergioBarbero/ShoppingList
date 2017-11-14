public class Product{
    /**
     * Product id
     */
    private int id;
    /**
     * Product name
     */
    private String name;
    /**
     * Product price
     */
    private double price;

    /**
     * Product constructor
     * @param id Identifier of my product
     * @param name Name of my product
     * @param price Price of my product
     */
    public Product(int id, String name, double price){
        this.id = id;
        this.name =  name;
        this.price = price;
    }

    /**
     * Returns name of my product
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns id of my product
     * @return id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Returns price of my product
     * @return price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Set name of my product
     * @param name name of my product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set id of a product
     * @param id id of a product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set price of a product
     * @param price price of a product
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
