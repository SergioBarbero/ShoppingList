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
     * Product constructor
     * @param id Identifier of my product
     * @param name Name of my product
     */
    public Product(int id, String name){
        this.id = id;
        this.name =  name;
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
     * Set name of my product
     * @param name name of my product
     */
    public void setName(String name) {
        this.name = name;
    }

}
