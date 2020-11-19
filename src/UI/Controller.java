package UI;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Data;

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
        } catch (IOException e) {
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
            activeStage.setScene(new Scene(root));
        } catch (IOException e) {
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
        System.out.println("rentals seleted");
        if (Data.getInstance().getSystem().getCustomers().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There are no registered customers!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (Data.getInstance().getSystem().getCarByAvailability(true).size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There are no cars to be rented!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UI/rentalUI/RentalsWindow.fxml"));
            Stage activeStage = (Stage) main.getScene().getWindow();
            activeStage.setScene(new Scene(root));

        } catch (IOException e) {
            System.out.println("Error while Rentals Loading Window!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }


}
