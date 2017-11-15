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

    public void increaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuanty();
        myList.get(id).setQuantity(myQuantity + quantity);
    }

    public void decreaseQuantity(int id, int quantity){
        int myQuantity = myList.get(id).getQuanty();
        int finalQuantity = myQuantity - quantity;
        if (finalQuantity > 0)
            myList.get(id).setQuantity(finalQuantity);
        else
            myList.get(id).setQuantity(0);
    }

    public void markAsBought(int id){
        myList.get(id).setBought(true);

    }



    public void

}
