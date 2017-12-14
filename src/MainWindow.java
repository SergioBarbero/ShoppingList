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
import javafx.stage.Stage;

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

        root.setLeft(addVBox());
        root.setCenter(addGridPane());

        Scene scene = new Scene(root, 900, 900);

        primaryStage.setTitle("Shopping List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.setGridLinesVisible(true);

        //Header
        MenuButton menuButton = new MenuButton("Seleccionar");
        menuButton.getItems().addAll(new MenuItem("Todo"), new MenuItem("Nada"), new MenuItem("Favoritos"), new MenuItem("Comprados"));
        grid.add(menuButton, 1, 0);

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

        return grid;
    }



    private VBox addVBox() {
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

        buttonNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        return vbox;

    }

    public void displayContent(GridPane grid){

        for(int i=0,row=1; i < list.size(); i++,row++ ){
            Text name = new Text(list.get(i).getName());
            Text quantity = new Text(list.get(i).getQuantity() + "");
            Text bought = new Text(list.get(i).getBoughtToString());
            Text price = new Text(list.get(i).getPriceToString());
            CheckBox selected = new CheckBox("");
            grid.add(selected, 2, row, 2, 1);
            grid.add(name, 2, row, 2, 1);
            grid.add(quantity, 3, row, 2, 1);
            grid.add(bought, 4, row, 2, 1);
            grid.add(price, 5, row, 2, 1);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
