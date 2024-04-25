import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Country> table = new TableView<>();
        table.setEditable(true);

        // Define columns
        TableColumn<Country, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Country, String> capitalCol = new TableColumn<>("Capital");
        capitalCol.setCellValueFactory(new PropertyValueFactory<>("capital"));

        TableColumn<Country, Number> populationCol = new TableColumn<>("Population");
        populationCol.setCellValueFactory(new PropertyValueFactory<>("population"));

        TableColumn<Country, Number> areaCol = new TableColumn<>("Area");
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));

        // Add columns to table
        table.getColumns().addAll(nameCol, capitalCol, populationCol, areaCol);

        // Layout setup
        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox);

        // Set stage
        primaryStage.setTitle("Country Information Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}