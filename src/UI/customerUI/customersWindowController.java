package UI.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author M-Hamdy-M
 * Creation Date : 8-11-2020
 * @version 2
 */

public class customersWindowController {

    @FXML
    private VBox customersWindow;
    @FXML
    private TableView<Visitor> visitorsTable;
    @FXML
    private TableColumn<Visitor, Integer> idColumn;
    @FXML
    private TableColumn<Visitor, String> nameColumn;
    @FXML
    private TableColumn<Visitor, String> phoneColumn;
    @FXML
    private TableColumn<Visitor, String> addressColumn;
    @FXML
    private TableColumn<Visitor, String> nationalityColumn;
    @FXML
    private TableColumn<Visitor, Integer> passportColumn;
    @FXML
    private TableColumn<Visitor, String> licenceColumn;
    @FXML
    private TableColumn<Visitor, LocalDate> entryDateColumn;
    @FXML
    private TableColumn<Visitor, LocalDate> expiryDateColumn;


    /**
     * populating customer tableView
     */
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("address"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("nationality"));
        licenceColumn.setCellValueFactory(new PropertyValueFactory<Visitor, String>("drivingLicence"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("passportNo"));
        entryDateColumn.setCellValueFactory(new PropertyValueFactory<Visitor, LocalDate>("entryDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<Visitor, LocalDate>("visaExpiryDate"));

        visitorsTable.setItems(Data.getInstance().getVisitors());
    }


    /**
     * shows the Add Customer Dialog when pressing the Add Button
     */
    @FXML
    public void showAddCustomerDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(customersWindow.getScene().getWindow());
        dialog.setTitle("Add Customer");
        dialog.setHeaderText("Add Customer");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("UI/customerUI/addAndUpdateCustomerDialog.fxml"));
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

            AddAndUpdateCustomerDialogController controller = fxmlLoader.getController();
            try {
                Visitor visitor = controller.processResult();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, Data.getInstance().getSystem().addCustomer(visitor), ButtonType.OK);
                Data.getInstance().reloadVisitors();
                alert.show();
                return;
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.show();
            }
        }
    }

    /**
     * shows the Update Customer Dialog when pressing the Update Button
     */
    @FXML
    public void showUpdateVisitorDialog() {
        if (visitorsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR
                    , "Please select a customer to update or add a new one!",
                    ButtonType.OK);
            alert.show();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Customer");
        dialog.setHeaderText("Please change The information you need to update");
        Visitor selectedVisitor = visitorsTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("UI/customerUI/addAndUpdateCustomerDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
            AddAndUpdateCustomerDialogController controller = loader.getController();
            controller.populateFields(selectedVisitor);


            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Visitor newVisitor = controller.processResult();
                if (Data.getInstance().getSystem().modifyCustomer(selectedVisitor, newVisitor)) {
                    Data.getInstance().reloadVisitors();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Successfully", ButtonType.OK);
                    alert.show();
                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "There are already exist a customer with this customer ID", ButtonType.OK);
                    alert.show();
                    return;
                }
            }

            System.out.println(selectedVisitor.toString());


        } catch (IOException e) {
            System.out.println("Failed to load the dialog");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.show();
        }

    }

    /**
     * Deletes the Customer when pressing the Delete Button
     */
    @FXML
    public void deleteCustomer() {
        if (visitorsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete!", ButtonType.OK);
            alert.show();
            return;
        }
        Visitor selectedVisitor = visitorsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION
                , "Are you sure you want to delete customer with id:" + selectedVisitor.getCustomerId() + "?",
                ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result =  alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION,
                    Data.getInstance().getSystem().deleteCustomer(selectedVisitor.getCustomerId()),
                    ButtonType.OK);
            Data.getInstance().reloadVisitors();
            alert1.show();
        }
    }

    /**
     * saves the Customer in the System to Json File when pressing the Save Button
     */
    @FXML
    public void saveVisitors() {
        Data.getInstance().saveVisitors();
    }

    /**
     * Return Back to Main Window when pressing the Back button
     */
    @FXML
    public void backToMainWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UI/MainWindow.fxml"));
            Stage activeStage = (Stage) customersWindow.getScene().getWindow();
            activeStage.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            System.out.println("Failed to load the mainWindow");
            System.out.println(e.getMessage());
        }
    }
}
