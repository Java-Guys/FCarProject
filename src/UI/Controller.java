package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private VBox main;
    @FXML
    private Button cars;
    @FXML
    private Button customers;
    @FXML
    private Button rentals;

    @FXML
    public void showCarsDialog(ActionEvent event) {
        System.out.println("showing car dialog");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("carUI/carsWindow.fxml"));
            Stage activeStage = (Stage) main.getScene().getWindow();
            activeStage.setScene(new Scene(root, 700, 400));
        }catch (IOException e){
            System.out.println("error while loading car window!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void showCustomersDialog(ActionEvent event) {
        System.out.println("show customer dialog");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customerUI/customersWindow.fxml"));
            Stage activeStage = (Stage) main.getScene().getWindow();
            activeStage.setScene(new Scene(root, 1000, 400));
        }catch (IOException e){
            System.out.println("error while loading car window!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("customer/customersWindow.fxml"));
//        }catch (IOException e){
//            System.out.println("Unexcepected error happened");
//        }
//        Stage activeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        activeStage.setScene(new Scene(root, 1000, 400));
    }

    @FXML
    public void showRentalsDialog() {
        System.out.println("show customer dialog");
        System.out.println("rentals seleted");
    }


}
