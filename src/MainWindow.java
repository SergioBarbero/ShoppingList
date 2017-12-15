import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class MainWindow extends Application  {

    HashMap<String, Button> actions;
    private Button buttonNew;
    private Button buttonDel;
    private Button buttonModify;
    private Button buttonBought;
    private Button buttonFav;
    private List<Product> list = ProductList.getInstance().getList();



    @Override
    public void start(Stage primaryStage) throws Exception {


        BorderPane root = new BorderPane();

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

                Scene scene = new Scene(newProductPane, 900, 200);

                newProductStage.setTitle("New Product");
                newProductStage.setScene(scene);
                newProductStage.show();

                newProductPane.setHgap(10);
                newProductPane.setVgap(10);
                newProductPane.setPadding(new Insets(10, 10, 10, 10));

                addHeader(newProductPane);

                TextField name = new TextField ();
                TextField quantity = new TextField ();
                TextField bought = new TextField ();
                TextField price = new TextField ();
                TextField fav = new TextField ();
                Button submit = new Button("Guardar");

                newProductPane.add(name, 2,1);
                newProductPane.add(quantity, 3,1);
                newProductPane.add(bought, 4,1);
                newProductPane.add(price, 5,1);
                newProductPane.add(fav, 6,1);
                newProductPane.add(submit, 7,1);


            }
        });
    }


    public GridPane content() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        //grid.setGridLinesVisible(true);

        addHeader(grid);

        MenuButton menuButton = new MenuButton("Seleccionar");
        menuButton.getItems().addAll(new MenuItem("Todo"), new MenuItem("Nada"), new MenuItem("Favoritos"), new MenuItem("Comprados"));
        grid.add(menuButton, 1, 0);

        displayContent(grid);

        return grid;
    }

    public void addHeader(GridPane grid){
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


    private VBox actions() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Acciones");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Button acciones[] = new Button[] {
                buttonNew = new Button("Nuevo"),
                buttonDel = new Button("Eliminar"),
                buttonModify = new Button("Modificar"),
                buttonBought = new Button("Marcar como comprado"),
                buttonFav = new Button("Marcar como favorito")
        };

        for (int i=0; i<5; i++) {
            if (i != 0) {
                acciones[i].setDisable(true);
            }
            VBox.setMargin(acciones[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(acciones[i]);
        }



        return vbox;

    }

    public void displayContent(GridPane grid){

        for(int i=0,row=1; i < list.size(); i++,row++ ){
            Text name = new Text(list.get(i).getName());
            Text quantity = new Text(list.get(i).getQuantity() + "");
            Text bought = new Text(list.get(i).getBoughtToString());
            Text price = new Text(list.get(i).getPriceToString());
            Text fav = new Text(list.get(i).getFavoriteToString());
            CheckBox selected = new CheckBox("");
            grid.add(selected, 1, row, 2, 1);
            grid.add(name, 2, row, 2, 1);
            grid.add(quantity, 3, row, 2, 1);
            grid.add(bought, 4, row, 2, 1);
            grid.add(price, 5, row, 2, 1);
            grid.add(fav, 6, row, 2, 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Manager managerGUI = new GUI();
        managerGUI.loadDB();
        launch(args);
    }
}
