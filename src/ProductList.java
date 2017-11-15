import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private static List<ChosenProduct> myList;

    public ProductList(){
        myList = new ArrayList<>();
    }

    public static List<ChosenProduct> getList(){
        return myList;
    }

    public boolean addProduct(ChosenProduct product) {
        boolean added = false;
        if(!myList.contains(product)){
            myList.add(product);
            added = true;
        }
        return added;
    }
    
    public boolean deleteProduct(int id){
        boolean deleted = false;
        if((myList.get(id).getBought() || myList.get(id).getQuantity() == 0) && !myList.get(id).getFavorite()){
            myList.remove(id);
            deleted = true;
        }
        return deleted;
    }

    public void increaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuantity();
        myList.get(id).setQuantity(myQuantity + quantity);
    }

    public void decreaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuantity();
        int finalQuantity = myQuantity - quantity;
        if (finalQuantity > 0)
            myList.get(id).setQuantity(finalQuantity);
        else
            myList.get(id).setQuantity(0);
    }

    public void markAsBought(int id){
        myList.get(id).setBought(true);

    }

    public void markAsFavourite(int id){
        myList.get(id).setFavorite(true);
    }

}
