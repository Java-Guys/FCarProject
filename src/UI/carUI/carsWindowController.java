package UI.carUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Car;
import model.Data;

import java.io.IOException;
import java.util.Optional;

/**
 * @author M-Hamdy-M
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * Creation Date : 11-11-2020
 * @version 3
 */

public class carsWindowController {
    @FXML
    private VBox carWindow;
    @FXML
    private TableView<Car> carsTable;
    @FXML
    private TableColumn<Car, String> plateNoColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> typeColumn;
    @FXML
    private TableColumn<Car, Boolean> availabilityColumn;
    @FXML
    private TableColumn<Car, Double> dailyRentalRateColumn;


    /**
     * populate the car tableView
     */
    public void initialize() {
        plateNoColumn.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        dailyRentalRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRentalRate"));
        carsTable.setItems(Data.getInstance().getSystem().getCars());
    }

    /**
     * shows the Add Car Dialog when pressing the Add Button
     */
    @FXML
    public void showAddCarDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(carWindow.getScene().getWindow());
        dialog.setTitle("Add Car");
        dialog.setHeaderText("Please provide the car information");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("UI/carUI/AddCarDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            System.out.println(e.getMessage());
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            AddCarController controller = fxmlLoader.getController();
            try {
                Car car = controller.processResult();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, Data.getInstance().getSystem().addCar(car), ButtonType.OK);
                alert.show();
                return;
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert.show();
            }
        }
    }

    /**
     * shows the Update Car Dialog when pressing the Update Button
     */
    @FXML
    public void showUpdateCarDialog() {
        if (carsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a car to update or add a new one!", ButtonType.OK);
            alert.show();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Car");
        dialog.setHeaderText("Please change The information you need to update");
        Car selectedCar = carsTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI/carUI/AddCarDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
            AddCarController controller = loader.getController();
            controller.populateFields(selectedCar);
        } catch (IOException e) {
            System.out.println("Failed to load the dialog");
            System.out.println(e.getMessage());
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            AddCarController controller = loader.getController();
            try {
                Car newCar = controller.processResult();
                if (Data.getInstance().getSystem().modifyCar(selectedCar, newCar)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Car Updated Successfully", ButtonType.OK);
                    alert.show();
                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "There are already exist a car with this plateNo", ButtonType.OK);
                    alert.show();
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("error here");
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert.showAndWait();
            }

        }

        System.out.println(selectedCar.toString());
    }

    /**
     * Deletes the Car when pressing the Delete Button
     */
    @FXML
    public void deleteCar() {
        Dialog<ButtonType> dialog = new Dialog<>();
        if (carsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please select a car to delete!",
                    ButtonType.OK);
            alert.show();
            return;
        }
        Car selectedCar = carsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete car with plate no " + selectedCar.getPlateNo() + "?",
                ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Alert deleteConfirmationAlert = new Alert(Alert.AlertType.INFORMATION,
                    Data.getInstance().getSystem().deleteCar(selectedCar.getPlateNo()),
                    ButtonType.OK);
            deleteConfirmationAlert.show();
        }
    }

    /**
     * saves the Car in the System to Json File when pressing the Save Button
     */
    @FXML
    public void saveCars() {
        Data.getInstance().saveCars();
        Data.getInstance().saveRentals();
    }

    /**
     * Return Back to Main Window when pressing the Back button
     */
    @FXML
    public void backToMainWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UI/MainWindow.fxml"));
            Stage activeStage = (Stage) carWindow.getScene().getWindow();
            activeStage.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            System.out.println("Failed to load the mainWindow");
            System.out.println(e.getMessage());
        }
    }
}
