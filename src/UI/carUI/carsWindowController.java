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
import model.CarType;
import model.Data;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.format.TextStyle;
import java.util.Optional;

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


    public void initialize() {
        plateNoColumn.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        dailyRentalRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRentalRate"));
        carsTable.setItems(Data.getInstance().getSystem().getCars());
    }

//    @FXML
//    public void addCar() {
//        Car car = new Car("new plate no", "new model", CarType.SEDAN, 6000);
//        Data.getInstance().getSystem().addCar(car);
//    }

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
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            AddCarController controller = fxmlLoader.getController();
            try {
                Car car = controller.processResult();
                Data.getInstance().getSystem().addCar(car);
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert.showAndWait();
            }

        }
    }

    @FXML
    public void showUpdateCarDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        if (carsTable.getSelectionModel().getSelectedItem() == null) {
            dialog.setTitle("Message");
            dialog.setHeaderText("Message");
            dialog.setContentText("Please select a car to update or add a new one!");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            return;

        }
        dialog.setTitle("Update Car");
        dialog.setHeaderText("Please change The information you need to update");
        Car selectedCar = carsTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI/carUI/AddCarDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
            AddCarController controller = loader.getController();
            controller.populateFields(selectedCar.getPlateNo(), selectedCar.getModel(), selectedCar.getType(), selectedCar.getIsAvailable(), selectedCar.getDailyRentalRate());
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
                Data.getInstance().getSystem().getCars().set(Data.getInstance().getSystem().getCars().indexOf(selectedCar), newCar);
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert.showAndWait();
            }

        }

        System.out.println(selectedCar.toString());
    }

    @FXML
    public void deleteCar() {
        Dialog<ButtonType> dialog = new Dialog<>();
        if (carsTable.getSelectionModel().getSelectedItem() == null) {
            dialog.setTitle("Message");
            dialog.setHeaderText("Message");
            dialog.setContentText("Please select a car to delete!");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        dialog.setTitle("Delete confirmation");
        Car selectedCar = carsTable.getSelectionModel().getSelectedItem();
        dialog.setHeaderText("Are you sure you want to delete car with plate no " + selectedCar.getPlateNo() + "?");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
//            Data.getInstance().getSystem().getCars().remove(selectedCar);
            Data.getInstance().getSystem().deleteCar(selectedCar.getPlateNo());
        }

    }

    @FXML
    public void saveCars() {
        Data.getInstance().saveCars();
        Data.getInstance().saveRentals();
    }

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
