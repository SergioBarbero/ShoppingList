import java.util.Scanner;

public class UI extends Manager{


    public UI(String mode) {
        super(mode);
    }

    public String getIntroduced(){
        boolean written = false;
        String option = "";
        while (!written){
            option = new Scanner(System.in).next();
            written = true;
        }
        return  option;
    }

    public void askInfoProduct(){
        int id = Integer.parseInt(getIntroduced());
        String introducedAction = getIntroduced();
        if (introducedAction != null) {
            if(introducedAction == "ADD"){

            }else if(introducedAction == "DELETE"){

            }else if(introducedAction == "MODIFY"){
                String option = getIntroduced();
                if(option == "PRICE"){

                }else if(option == "QUANTITY"){

                }
            }else if(introducedAction == "BOUGHT"){

            }else if(introducedAction == "FAV"){

            }
        }
    }

    @Override
    public void addToList(int id, String name, double price, int quantity) {

    }

    @Override
    public void deleteFromList(int id) {

    }

    @Override
    public void modifyPriceList(int id, double price) {

    }

    @Override
    public void modifyQuantityList(int id, int quantity) {

    }

    @Override
    public void markAsFavList(int id) {

    }

    @Override
    public void markAsBoughtList(int id) {

    }
}
