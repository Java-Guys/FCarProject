package UI.rentalUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentalsController {

    @FXML
    private BorderPane rentalsWindow;
    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<Car> carComboBox;

    @FXML
    private DatePicker startDateDP;

    @FXML
    private DatePicker endDateDP;

    @FXML
    private TableView<Rental> rentalsTable;

    @FXML
    private TableColumn<Rental, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Rental, LocalDate> endDateColumn;

    @FXML
    private TableColumn<Rental, Double> depositColumn;

    @FXML
    private TableColumn<Rental, Integer> invoiceNoColumn;

    @FXML
    private TableColumn<Rental, LocalDate> invoiceDateColumn;

    @FXML
    private TableColumn<Rental, Double> totalColumn;



    public void initialize() {

        customerComboBox.setItems(Data.getInstance().getSystem().getCustomers());
        customerComboBox.setValue(Data.getInstance().getSystem().getCustomers().get(0));
        System.out.println(Data.getInstance().getVisitors().get(0));


        carComboBox.setItems(Data.getInstance().getSystem().getCarByAvailability(true));
        carComboBox.setValue(Data.getInstance().getSystem().getCarByAvailability(true).get(0));

        startDateDP.setValue(LocalDate.now());
        endDateDP.setValue(LocalDate.now().plusDays(1));

        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        invoiceNoColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        invoiceDateColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        rentalsTable.setItems(Data.getInstance().getSystem().getRentals());
    }


    @FXML
    void handleRent(ActionEvent event) {
        System.out.println("Entered handleRent");
        if (!carComboBox.getValue().getIsAvailable()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "This car is already rented!", ButtonType.OK);
            alert.show();
            return;
        }
        Data.getInstance().getSystem().printRentals();

        Rental rental = new Rental();

        rental.setCustomer(customerComboBox.getValue());
        rental.setCar(carComboBox.getValue());
        rental.setStartDate(startDateDP.getValue());
        rental.setEndDate(endDateDP.getValue());
        rental.setDeposit(2000);
        rental.getInvoice().setPayments(new ArrayList<Payment>());

        if (rental.getCustomer() == null || rental.getCar() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot place a rental with no customer or car!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        System.out.println(rental);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, Data.getInstance().getSystem().bookCarRental(rental), ButtonType.OK);
        alert.show();
        Data.getInstance().getSystem().printRentals();
        Data.getInstance().saveRentals();
        Data.getInstance().saveCars();
    }

    public void backToMainWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UI/MainWindow.fxml"));
            Stage activeStage = (Stage) rentalsWindow.getScene().getWindow();
            activeStage.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            System.out.println("Failed to load the mainWindow");
            System.out.println(e.getMessage());
        }
    }



}
