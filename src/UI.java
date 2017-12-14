import java.io.IOException;
import java.util.Scanner;

public class UI extends Manager{


    /**
     * UI constructor
     */

    public UI(){
        super();
    }

    /**
     * Gets a String from the user
     * @return String returned
     */

    private String getIntroduced(){
        return  new Scanner(System.in).next();
    }

    /**
     * Asks user for something
     * @param question to tell user what we need
     * @return answer from user
     */

    @Override
    public String askUser(String question){
        System.out.println(question);
        return getIntroduced();
    }

    /**
     * Asks user for list's name
     * @return name of the list
     */

    @Override
    public String askFileName(){
        return askUser("Introduzca en nombre de la lista: ");
    }

    /**
     * Ask the user for file format
     * @return format of the file
     */

    @Override
    public String askFormatFile(){
        String format = ".tsv";
        boolean formatLoop = true;
        while (formatLoop) {
            printFormatsAvailable();
            switch (getIntroduced()){
                case "1":
                    format = ".tsv";
                    formatLoop = false;
                    break;
                default:
                    System.out.println("Opción no soportada");
            }
        }
        return format;
    }

    /**
     * Displays available formats for the file
     */

    private void printFormatsAvailable(){
        System.out.println("Formatos de archivo disponibles:");    //\u00BF
        System.out.println("1.- '.tsv'");
    }

    /**
     * Ask the user for directory name
     * @return directory name
     */

    @Override
    public String askFolderName(){

        String folderName = getUtil().getListsPath();

        getUtil().checkFolder(folderName);

        return folderName;
    }




    /**
     * Ask information to the user
     * @throws IOException exception management for write/read from files
     */

    @Override
    public void askInfoProduct() throws IOException {

        boolean inLoop = true;
        boolean cambios = false;

        while (inLoop) {
            printOptions();
            switch(getIntroduced()){
                case "ADD":
                    cambios = caseAdd();
                    break;
                case "DEL":
                    caseDel();
                    cambios = true;
                    break;
                case "MOD":
                    cambios = caseMod();
                    break;
                case "BUY":
                    caseBuy();
                    cambios = true;
                    break;
                case "FAV":
                    caseFav();
                    cambios = true;
                    break;
                case "VIEW":
                    caseView();
                    break;
                case "SAVE":
                    caseSave(nameOfTheList);
                    cambios = false;
                    break;
                case "HELP":
                    caseHelp();
                    break;
                case "EXIT":
                    caseExit(cambios, nameOfTheList);
                    inLoop = false;
                    break;
                default:
                    System.out.println("Opción no soportada");
            }
        }
    }

    /**
     * Displays available options from main menu
     */

    private void printOptions(){
        System.out.println("\n\t¿Que operación desea realizar?");    //\u00BF
        System.out.println("ADD -> Añade nuevo producto");  //\u00F1
        System.out.println("DEL -> Elimina un producto");
        System.out.println("MOD -> Modifica un producto");
        System.out.println("BUY -> Marcar producto como comprado o no comprado");
        System.out.println("FAV -> Marcar producto como favorito o no favorito");
        System.out.println("VIEW -> Visualización de la lista");
        System.out.println("SAVE -> Guardar los cambios en el fichero");
        System.out.println("HELP -> Muestra información básica del programa");
        System.out.println("EXIT -> Dejar de editar la lista y salir");
    }

    /**
     * Add case from main menu
     */

    private boolean caseAdd(){
        String name = askUser("Introduce el nombre del producto: ");
        if(getUtil().productOnListByName(name)){
            System.out.println("ERROR: Producto ya existente");
            return false;
        }
        int quantity = Integer.parseInt(askUser("Introduce la cantidad: "));
        String noQuantity = "NO";
        if(quantity < 1){
            quantity = 0;
            noQuantity = askUser("Cantidad insuficiente.\n" +
                    "¿Desea marcar el producto como favorito para evitar que se elimine? (SI / NO) ");
            if (!noQuantity.equals("SI")){
                return false;
            }
        }
        addToList(name, quantity);
        if(noQuantity.equals("SI")){
            markAsFavList(ProductList.getInstance().getId(name));
        }
        return true;
    }

    /**
     * Delete case from main menu
     */

    private boolean caseDel(){
        int id = Integer.parseInt(askUser("Introduce la id del producto que quieras eliminar: "));
        if(!getUtil().productOnListById(id)){
            System.out.println("ERROR: Producto no existente");
            return false;
        }
        deleteFromList(id);
        return true;
    }

    /**
     * Modify case from main menu
     */

    private boolean caseMod(){
        boolean modify = true;
        boolean innerLoop = true;
        int id = Integer.parseInt(askUser("Introduce la id del producto que quieras modificar: "));
        if(!getUtil().productOnListById(id)){
            innerLoop = false;
            System.out.println("ERROR: Producto no existente");
        }

        while(innerLoop) {
            printModOptions();
            switch(getIntroduced()) {
                case "NAME":
                    innerLoop = caseModName(id);
                    break;
                case "QUANTITY":
                    caseModQuantity(id);
                    innerLoop = false;
                    break;
                case "PRICE":
                    caseModPrice(id);
                    innerLoop = false;
                    break;
                case "CANCEL":
                    System.out.println("Cancelando modificación...");
                    innerLoop = false;
                    modify = false;
                    break;
                default:
                    System.out.println("Opción no soportada");
            }
        }
        return modify;
    }

    /**
     * Displays available parameters to modify
     */

    private void printModOptions(){
        System.out.println("\n\t¿Qué parámetro desea modificar?");
        System.out.println("NAME -> Modificar nombre");
        System.out.println("QUANTITY -> Modificar cantidad");
        System.out.println("PRICE -> Modificar precio");
        System.out.println("CANCEL -> Cancelar modificación");
    }

    /**
     * Modify name case from modification menu
     * @param id of product to modify
     * @return true/false if could modify name or not
     */

    private boolean caseModName(int id){
        String name = askUser("Introduce el nuevo nombre: ");
        if(getUtil().productOnListByName(name)){
            System.out.println("ERROR: Producto ya existente");
            return true;
        }
        modifyNameList(id, name);
        return false;
    }

    /**
     * Modify quantity case from modification menu
     * @param id of product to modify
     */

    private void caseModQuantity(int id){
        int quantity = Integer.parseInt(askUser("Introduce la cantidad: "));
        modifyQuantityList(id, quantity);
    }

    /**
     * Modify price case from modification menu
     * @param id of product to modify
     */

    private void caseModPrice(int id){
        Double price = Double.parseDouble(askUser("Introduce el precio del producto: "));
        modifyPriceList(id, price);
    }

    /**
     * Bought case from main menu
     */

    private void caseBuy(){
        int id = Integer.parseInt(askUser("Introduce la id del producto que quieras marcar como comprado/no comprado: "));
        markAsBoughtList(id);
    }

    /**
     * Favorite case from main menu
     */

    private void caseFav(){
        int id = Integer.parseInt(askUser("Introduce la id del producto que quieras marcar como favorito/no favorito: "));
        markAsFavList(id);
    }

    /**
     * View case from main menu
     */

    private void caseView(){
        printList();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Saves the list in the actual state to the file
     * @param fileName name of file to be writen
     * @throws IOException exception management for write/read from files
     */

    private void caseSave(String fileName) throws IOException {
        System.out.println("Guardando lista...");
        getUtil().writeList(fileName);
        ProductList.getInstance().resetList();
        getUtil().checkList(fileName);
    }

    /**
     * Help case from main menu
     */

    private void caseHelp() throws IOException {
        getUtil().displayHelpFile();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Exit the program, asking the user if wants to save the list in case he/she didn't recently
     * @param cambios we can know if changes were made
     * @param nameOfTheList name of file to be writen
     * @throws IOException exception management for write/read from files
     */

    private void caseExit(boolean cambios, String nameOfTheList) throws IOException {
        if(cambios && askUser("Se han producido cambios desde la última vez que guardó la lista.\n" +
                "¿Desea guardar los cambios antes de salir? (Y/N)").equals("Y")){
            caseSave(nameOfTheList);
        }
        System.out.println("Cerrando programa.\n\nPulse ENTER para finalizar.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Print list into shell
     */

    @Override
    public void printList(){
        System.out.println("\nID\tName\t\tQuantity\tBought\tPrice\tFavorite");
        for(Product pr: ProductList.getInstance().getList()){
            System.out.println(
                    pr.getId() + "\t" +
                    pr.getName() + "\t\t" +
                    pr.getQuantity() + "\t" +
                    pr.getBoughtToString() + "\t" +
                    pr.getPriceToString() + "\t" +
                    pr.getFavoriteToString());
        }
    }

    /**
     * Adds a product to the list
     * @param name of the product
     * @param quantity of the product
     */

    @Override
    public void addToList(String name, int quantity) {
        int id = ProductList.getInstance().getList().size();
        ProductList.getInstance().addProduct(new Product(id, name, quantity));
    }

    /**
     * Deletes a product from my list
     * @param id of the product
     */

    @Override
    public void deleteFromList(int id) {
        ProductList.getInstance().deleteProduct(id);
    }

    /**
     * Modifies name of product
     * @param id of the product
     * @param name new name for the product
     */

    @Override
    public void modifyNameList(int id, String name) {
        ProductList.getInstance().getProduct(id).setName(name);
    }

    /**
     * Modifies the price of a product in the list
     * @param id of the product
     * @param price new price
     */

    @Override
    public void modifyPriceList(int id, double price) {
        ProductList.getInstance().getProduct(id).setPrice(price);
    }

    /**
     * Modifies the quantity of products in the list
     * @param id of the product
     * @param quantity new quantity
     */

    @Override
    public void modifyQuantityList(int id, int quantity) {
        ProductList.getInstance().getProduct(id).setQuantity(quantity);
    }

    /**
     * It marks the product as favorite
     * @param id of the product
     */

    @Override
    public void markAsFavList(int id) {
        Product pr = ProductList.getInstance().getProduct(id);
        pr.setFavorite(!pr.getFavorite());
    }

    /**
     * It marks a product as bought
     * @param id of the product
     */

    @Override
    public void markAsBoughtList(int id) {
        Product pr = ProductList.getInstance().getProduct(id);
        pr.setBought(!pr.getBought());
    }
}