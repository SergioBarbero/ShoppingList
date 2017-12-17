import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends Application  {

    Button actions[];
    private Button buttonNew;
    private Button buttonDel;
    private Button buttonModify;
    private Button buttonBought;
    private Button buttonFav;
    private GridPane content;
    BorderPane root = new BorderPane();
    private List<Product> list = ProductList.getInstance().getList();
    CheckBox[] selected;
    Manager session = new GUI();

    public MainWindow() throws IOException {
        session.loadDB();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {



        root.setLeft(actions());
        root.setCenter(content());

        Scene scene = new Scene(root, 900, 900);

        primaryStage.setTitle("Shopping List");
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newProductStage = new Stage();
                GridPane newProductPane = new GridPane();

                //Blocking our parent Stage until this one is closed
                newProductStage.initModality(Modality.WINDOW_MODAL);
                newProductStage.initOwner(primaryStage);

                //New Window
                Scene scene = new Scene(newProductPane, 500, 200);

                newProductStage.setTitle("New Product");
                newProductStage.setScene(scene);
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
            }
        });
    }


    public GridPane content() {
        content = new GridPane();
        content.setHgap(10);
        content.setVgap(10);
        content.setPadding(new Insets(10, 10, 10, 10));

        addHeader();
        displayContent();
        return content;
    }

    public void disableEnableButtons(){
        int counter = 0;
        for(int i = 0; i <selected.length; i++){
            if(selected[i].isSelected()) {
                counter++;
            }
        }
        for(int i= 0; i < actions.length; i++){
            actions[i].setDisable(false);
        }
        if(counter == 0){
            onlyNewButtonEnabled();
        }else if(counter > 1){
            actions[2].setDisable(true);
        }
    }

    /**
     * Returns the id of the selected row whether it's unique, otherwise returns -1
     * @return
     */
    public int idUnique(){
        int counter = 0;
        boolean unique = true;
        int i = 0;
        int index = 0;
        while(unique){
            if(selected[i].isSelected()){
                counter++;
                index = i;
                if(counter > 1){
                    unique = false;
                }
            }
            i++;
        }
        if(!unique)
            index = -1;
        return index;
    }

    public List<String> namesSelected(){
        List<String> listNames = new ArrayList<String>();
        for(int i = 0; i <selected.length; i++){
            if(selected[i].isSelected()) {
                listNames.add(list.get(i).getName());
            }
        }
        return listNames;
    }



    public void addHeader(){

        MenuButton selection = new MenuButton("Seleccionar");
        MenuItem itemAll = new MenuItem("Todo");
        MenuItem itemNothing =  new MenuItem("Nada");
        MenuItem itemFav = new MenuItem("Favoritos");
        MenuItem itemBought = new MenuItem("Comprados");

        selection.getItems().addAll(itemAll, itemNothing, itemFav, itemBought);


        itemAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i <selected.length; i++){
                    selected[i].setSelected(true);
                }
                disableEnableButtons();
            }
        });

        itemNothing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i <selected.length; i++){
                    selected[i].setSelected(false);
                }
                disableEnableButtons();
            }
        });

        itemBought.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i <selected.length; i++){
                    selected[i].setSelected(false);
                    if(list.get(i).getBought()) {
                        selected[i].setSelected(true);
                    }
                }
                disableEnableButtons();
            }
        });

        itemFav.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i <selected.length; i++){
                    selected[i].setSelected(false);
                    if(list.get(i).getFavorite()) {
                        selected[i].setSelected(true);
                    }
                }
                disableEnableButtons();
            }
        });

        content.add(selection, 1, 0);

        //Header
        Text name = new Text("Nombre");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        content.add(name, 2, 0);

        Text quantity = new Text("Cantidad");
        quantity.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        content.add(quantity, 3, 0);

        Text bought = new Text("Comprado");
        bought.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        content.add(bought, 4, 0);

        Text price = new Text("Precio");
        price.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        content.add(price, 5, 0);

        Text fav = new Text("Favorito");
        fav.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        content.add(fav, 6, 0);
    }


    private VBox actions() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Acciones");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        actions = new Button[] {
                buttonNew = new Button("Nuevo"),
                buttonDel = new Button("Eliminar"),
                buttonModify = new Button("Modificar"),
                buttonBought = new Button("Marcar como comprado"),
                buttonFav = new Button("Marcar como favorito")
        };
        vbox.getChildren().addAll(actions);

        onlyNewButtonEnabled();

        buttonDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(String name : namesSelected()){
                    session.getListUtil().deleteFromList(session.getListUtil().product);
                }
                root.setCenter(content());
            }
        });

        buttonFav.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Integer> ids = idsSelected();
                for(Integer id : ids){
                    if(id != 0)
                        --id;
                    session.getListUtil().markAsFavList(id);
                }
                root.setCenter(content());
            }
        });

        buttonBought.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Integer> ids = idsSelected();
                for(Integer id : ids){
                    if(id != 0)
                        --id;
                    session.getListUtil().markAsBoughtList(id);
                }
                root.setCenter(content());
                System.out.println(list.toString());
            }
        });

        return vbox;

    }

    private void onlyNewButtonEnabled(){
        for (int i=0; i<5; i++) {
            if (i != 0)
                actions[i].setDisable(true);
        }
    }

    public void displayContent(){

        selected = new CheckBox[list.size()];
        for(int i=0,row=1; i < list.size(); i++,row++ ){
            Text name = new Text(list.get(i).getName());
            Text quantity = new Text(list.get(i).getQuantity() + "");
            Text bought = new Text(list.get(i).getBoughtToString());
            Text price = new Text(list.get(i).getPriceToString());
            Text fav = new Text(list.get(i).getFavoriteToString());
            selected[i] = new CheckBox();
            content.add(selected[i], 1, row, 2, 1);
            content.add(name, 2, row, 2, 1);
            content.add(quantity, 3, row, 2, 1);
            content.add(bought, 4, row, 2, 1);
            content.add(price, 5, row, 2, 1);
            content.add(fav, 6, row, 2, 1);
        }

       for(int i= 0; i < selected.length; i++){
            selected[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    disableEnableButtons();
                }
            });
        }

    }

    public static void main(String[] args) throws IOException {
        //Manager managerGUI = new GUI();
        //managerGUI.loadDB();
        launch(args);
    }
}
