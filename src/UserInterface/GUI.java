package UserInterface;

import Products.Product;
import Products.ProductList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUI extends Application  {

    /**
     * Array of actions, delete element, new element...
     */
    private Button[] actions;
    /**
     * Button new
     */
    private Button buttonNew;
    /**
     * Button modify
     */
    private Button buttonModify;
    /**
     * My list of products
     */
    private List<Product> list = ProductList.getInstance().getList();
    /**
     * Array of selected products
     */
    private CheckBox[] selected;
    /**
     * Reference to UIManager to save and load lists
     */
    private UIManager operation;
    /**
     * Main pane to insert into the Stage
     */
    private BorderPane root;
    /**
     * Hashmap of products and CheckBoxes, in order to see if a product has been selected
     */
    private HashMap<Product, CheckBox> selectedProducts;

    /**
     * Constructor of the class, it instances a new UIManager and loads our db
     * @throws IOException IOException exception management for write/read from files
     */
    public GUI() throws IOException {
        operation = new UIManager();
        operation.getPersistence().loadDB();
    }

    /**
     * Starts the main Window
     * @param primaryStage Stage to use
     */
    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        root.setLeft(setActions());
        root.setCenter(setContent());

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Shopping List");
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonModify.setOnAction(event -> {
            Stage modProductStage = new Stage();
            GridPane modProductPane = new GridPane();

            //Blocking our parent Stage until this one is closed
            modProductStage.initModality(Modality.WINDOW_MODAL);
            modProductStage.initOwner(primaryStage);

            //New Window
            Scene scene1 = new Scene(modProductPane, 700, 100);

            modProductStage.setTitle("New Product");
            modProductStage.setScene(scene1);
            modProductStage.show();

            modProductPane.setHgap(10);
            modProductPane.setVgap(10);
            modProductPane.setPadding(new Insets(10, 10, 10, 10));

            Text nameLab = new Text("Nombre");
            nameLab.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            Text quantityLab = new Text("Cantidad");
            quantityLab.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            Text priceLab = new Text("Precio");
            priceLab.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            Text[] labs = new Text[]{
                    nameLab, quantityLab, priceLab
            };

            //Adding labels to our grid
            for(int i = 2, index=0; i <= 4; i++, index++){
                modProductPane.add(labs[index], i, 0);
            }

            TextField name = new TextField ();
            TextField quantity = new TextField ();
            TextField price = new TextField();
            Button submit = new Button("Modificar");


            modProductPane.add(name, 2,1);
            modProductPane.add(quantity, 3,1);
            modProductPane.add(price, 4,1);
            modProductPane.add(submit, 7,1);


            for(Map.Entry<Product, CheckBox> entry : selectedProducts.entrySet()){
                if(entry.getValue().isSelected()){
                    Product prodToMod = entry.getKey();
                    name.setText(prodToMod.getName());
                    quantity.setText(prodToMod.getQuantityToString());
                    price.setText(prodToMod.getPriceToString());

                    submit.setOnAction(event12 -> {
                        operation.getListUtil().modifyNameList(prodToMod.getId(),name.getText());
                        operation.getListUtil().modifyQuantityList(prodToMod.getId(),Integer.parseInt(quantity.getText()));
                        if(!price.getText().equals(" - "))
                            operation.getListUtil().modifyPriceList(prodToMod.getId(),Double.parseDouble(price.getText()));
                        root.setCenter(setContent());
                        modProductStage.close();
                        disableEnableButtons();
                    });
                }

            }
        });

        buttonNew.setOnAction(event -> {
            Stage newProductStage = new Stage();
            GridPane newProductPane = new GridPane();

            //Blocking our parent Stage until this one is closed
            newProductStage.initModality(Modality.WINDOW_MODAL);
            newProductStage.initOwner(primaryStage);

            //New Window
            Scene scene12 = new Scene(newProductPane, 500, 100);

            newProductStage.setTitle("Nuevo producto");
            newProductStage.setScene(scene12);
            newProductStage.show();

            newProductPane.setHgap(10);
            newProductPane.setVgap(10);
            newProductPane.setPadding(new Insets(10, 10, 10, 10));

            Text nameLab = new Text("Nombre");
            nameLab.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            newProductPane.add(nameLab, 2, 0);

            Text quantityLab = new Text("Cantidad");
            quantityLab.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            newProductPane.add(quantityLab, 3, 0);

            TextField name = new TextField ();
            TextField quantity = new TextField ();
            Button submit = new Button("Añadir");


            newProductPane.add(name, 2,1);
            newProductPane.add(quantity, 3,1);
            newProductPane.add(submit, 7,1);

            submit.setOnAction(event1 -> {
                Integer q = Integer.parseInt(quantity.getText());
                operation.getListUtil().addToList(name.getText(),q);
                root.setCenter(setContent());
            });

        });
    }


    /**
     * It creates the content into the stage
     * @return grid to load into the stage
     */
    private GridPane setContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        //grid.setGridLinesVisible(true);

        addHeader(grid);

        MenuButton selection = new MenuButton("Seleccionar");

        MenuItem itemAll = new MenuItem("Todo");
        MenuItem itemNothing =  new MenuItem("Nada");
        MenuItem itemFav = new MenuItem("Favoritos");
        MenuItem itemBought = new MenuItem("Comprados");

        selection.getItems().addAll(itemAll, itemNothing, itemFav, itemBought);



        itemAll.setOnAction(event -> {
            for (CheckBox aSelected : selected) {
                aSelected.setSelected(true);
            }
            disableEnableButtons();
        });

        itemNothing.setOnAction(event -> {
            for (CheckBox aSelected : selected) {
                aSelected.setSelected(false);
            }
            disableEnableButtons();
        });

        itemBought.setOnAction(event -> {
            for(int i = 0; i <selected.length; i++){
                selected[i].setSelected(false);
                if(list.get(i).getBought()) {
                    selected[i].setSelected(true);
                }
            }
            disableEnableButtons();
        });

        itemFav.setOnAction(event -> {
            for(int i = 0; i <selected.length; i++){
                selected[i].setSelected(false);
                if(list.get(i).getFavorite()) {
                    selected[i].setSelected(true);
                }
            }
            disableEnableButtons();
        });

        grid.add(selection, 1, 0);
        displayContent(grid);


        return grid;
    }

    private void disableEnableButtons(){
        int counter = 0;
        for (CheckBox aSelected : selected) {
            if (aSelected.isSelected()) {
                counter++;
            }
        }
        for (Button action : actions) {
            action.setDisable(false);
        }
        if(counter == 0){
            onlyNewButtonEnabled();
        }else if(counter > 1){
            actions[2].setDisable(true);
        }

    }

    /**
     * Add header to the grid
     * @param grid to load the header in
     */
    private void addHeader(GridPane grid){
        //Header
        Text name = new Text("Nombre");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(name, 2, 0);

        Text quantity = new Text("Cantidad");
        quantity.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(quantity, 3, 0);

        Text bought = new Text("Comprado");
        bought.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(bought, 4, 0);

        Text price = new Text("Precio");
        price.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(price, 5, 0);

        Text fav = new Text("Favorito");
        fav.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(fav, 6, 0);
    }


    /**
     * Add left margin set of actions
     * @return grid to load such set of actions in
     */
    private VBox setActions() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Acciones");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Button buttonBought;
        Button buttonFav;
        Button buttonDel;
        Button buttonSave;



        actions = new Button[] {
                buttonNew = new Button("Nuevo"),
                buttonDel = new Button("Eliminar"),
                buttonModify = new Button("Modificar"),
                buttonBought = new Button("Marcar como comprado"),
                buttonFav = new Button("Marcar como favorito"),
                buttonSave = new Button("Guardar")
        };
        vbox.getChildren().addAll(actions);
        buttonSave.setFont(Font.font("Arial", FontWeight.BOLD, 20));



        buttonDel.setOnAction(event -> {
            for(Map.Entry<Product, CheckBox> entry : selectedProducts.entrySet()) {
                Product myProd = entry.getKey();
                String name = myProd.getName();
                if(entry.getValue().isSelected()) {
                    int id = operation.getListUtil().productIDByName(name);
                    operation.getListUtil().deleteFromList(id);
                }
            }
            root.setCenter(setContent());
        });

        buttonFav.setOnAction(event -> {
            for(Map.Entry<Product, CheckBox> entry : selectedProducts.entrySet()) {
                Product myProd = entry.getKey();
                String name = myProd.getName();
                if(entry.getValue().isSelected()) {
                    int id = operation.getListUtil().productIDByName(name);
                    operation.getListUtil().markAsFavList(id);
                }
            }
            root.setCenter(setContent());
        });

        buttonBought.setOnAction(event -> {
            for(Map.Entry<Product, CheckBox> entry : selectedProducts.entrySet()) {
                Product myProd = entry.getKey();
                String name = myProd.getName();
                if(entry.getValue().isSelected()) {
                    int id = operation.getListUtil().productIDByName(name);
                    operation.getListUtil().markAsBoughtList(id);
                }
            }
            root.setCenter(setContent());
        });

        buttonSave.setOnAction(event -> {
            try {
                operation.getPersistence().writeDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        onlyNewButtonEnabled();
        return vbox;
    }


    /**
     * Check if just new button is enabled
     */
    private void onlyNewButtonEnabled(){
        for (int i=0; i<5; i++) {
            if (i != 0)
                actions[i].setDisable(true);
        }
    }

    /**
     * Display the content of the list into the grid
     * @param grid to load the content in
     */
    private void displayContent(GridPane grid){
        selected = new CheckBox[list.size()];
        selectedProducts = new HashMap<>();

        for(int i=0,row=1; i < list.size(); i++,row++ ){
            //Getting texts
            selected[i] = new CheckBox("");
            Text name = new Text(list.get(i).getName());
            Text quantity = new Text(list.get(i).getQuantity() + "");
            Text bought = new Text(list.get(i).getBoughtToString());
            Text price = new Text(list.get(i).getPriceToString());
            Text fav = new Text(list.get(i).getFavoriteToString());
            //Adding text to the grid
            grid.add(selected[i], 1, row, 2, 1);
            grid.add(name, 2, row, 2, 1);
            grid.add(quantity, 3, row, 2, 1);
            grid.add(bought, 4, row, 2, 1);
            grid.add(price, 5, row, 2, 1);
            grid.add(fav, 6, row, 2, 1);
            //Adding the product to our hashmap
            selectedProducts.put(list.get(i), selected[i]);
        }

        for (CheckBox aSelected : selected) {
            aSelected.setOnAction(event -> disableEnableButtons());
        }

    }
}
