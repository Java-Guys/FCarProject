package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;

/**
 * @author M-Hamdy-M
 * @author Omar Alkashef
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * Creation Date : 4-11-2020
 * @version 1
 */

public class Main extends Application {

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("FCar Rental Shop");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();



    }

    /**
     * @param args
     */
    public static void main(String[] args){
        Data.getInstance().loadCustomers();
        Data.getInstance().loadCars();
        Data.getInstance().loadRentals();


        launch(args);
    }
}
