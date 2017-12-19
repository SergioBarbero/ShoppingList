package Persistence;

import java.io.IOException;

public class DBPersistence extends PersistanceManager {

    public DBPersistence(){
        super();
    }

    @Override
    public void loadDB() throws IOException {
        //Here we implement our connection with the database and check if the database exists with checkDB()
    }

    @Override
    public void checkDB() throws IOException {
        //If the database exists we add all the products to the list with readDB()
        //If not, we create a new list
    }

    @Override
    protected void readDB() throws IOException {
        //Reads all the info from the database
    }

    @Override
    public void writeDB() throws IOException {
        //We clean the database and add all the products from the list
        //We could probably do that only merging the changes and taking what changed from list, avoiding doing so much
        // deleting and adding
    }

}
