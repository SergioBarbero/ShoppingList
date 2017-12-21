package Persistence;

public class DBPersistence extends PersistanceManager {

    /**
     * Database utilities constructor
     */

    public DBPersistence(){
        super();
    }

    /**
     * Load product list from database
     */

    @Override
    public void loadDB() {
        //Here we implement our connection with the database and check if the database exists with checkDB()
    }

    /**
     *  Checks if the database already exists to open it, or creates a new list
     */

    @Override
    public void checkDB() {
        //If the database exists we add all the products to the list with readDB()
        //If not, we create a new list
    }

    /**
     * Read a list of products from a database
     */

    @Override
    protected void readDB() {
        //Reads all the info from the database
    }

    /**
     * Save the user list to a database
     */

    @Override
    public void writeDB() {
        //We clean the database and add all the products from the list
        //We could probably do that only merging the changes and taking what changed from list, avoiding doing so much
        // deleting and adding
    }

}
