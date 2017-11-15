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
     * @param id identifier of the product
     * @param name of the product
     */

    public Product(int id, String name){
        this.id = id;
        this.name =  name;
    }

    /**
     * Returns name of the product
     * @return name
     */

    public String getName(){
        return this.name;
    }

    /**
     * Returns id of the product
     * @return id
     */

    public int getId(){
        return this.id;
    }

    /**
     * Set the name of the product
     * @param name of the product
     */

    public void setName(String name) {
        this.name = name;
    }

}
