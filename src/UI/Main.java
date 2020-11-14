package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("FCar Rental Shop");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();



    }
    public static void main(String[] args){
        Data.getInstance().loadVisitors();
        Data.getInstance().loadCars();
        Data.getInstance().loadRentals();


        launch(args);
    }
}
