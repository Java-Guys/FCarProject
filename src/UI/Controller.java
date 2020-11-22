package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Data;

import java.io.IOException;

/**
 * @author M-Hamdy-M
 * @author Omar Alkashef
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * Creation Date : 4-11-2020
 * @version 2
 */

public class Controller {
    @FXML
    private VBox main;


    /**
     * @param event
     */
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

    /**
     * @param event
     */
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
    }

    /**
     * @param event
     */
    @FXML
    public void showRentalsDialog(ActionEvent event) {
        System.out.println("rentals selected");
        if (Data.getInstance().getSystem().getCustomers().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There are no registered customers!", ButtonType.OK);
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
