package UI.Rentals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;

import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RentalsController implements Initializable {

    @FXML
    private ComboBox<Customer> CustomerComboBox;

    @FXML
    private ComboBox<Car> CarComboBox;

    @FXML
    private DatePicker StartDateDP;

    @FXML
    private DatePicker EndDateDP;

    @FXML
    private TableView<Rental> RentalsTable;

    @FXML
    private TableColumn<Rental, LocalDate> StartDateColumn;

    @FXML
    private TableColumn<Rental, LocalDate> EndDateColumn;

    @FXML
    private TableColumn<Rental, Double> DepositColumn;

    @FXML
    private TableColumn<Rental, Integer> InvoiceNoColumn;

    @FXML
    private TableColumn<Rental, LocalDate> InvoiceDateColumn;

    @FXML
    private TableColumn<Rental, Double> TotalColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerComboBox.setItems(Data.getInstance().getSystem().getCustomers());
        CustomerComboBox.setValue(Data.getInstance().getSystem().getCustomers().get(1));
        CarComboBox.setItems(Data.getInstance().getSystem().getCarByAvailability(true));
        CarComboBox.setValue(Data.getInstance().getSystem().getCarByAvailability(true).get(1));
        StartDateDP.setValue(LocalDate.now());
        EndDateDP.setValue(LocalDate.now().plusDays(1));
        RentalsTable.setItems(Data.getInstance().getSystem().getRentals());
        StartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        EndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        DepositColumn.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        InvoiceNoColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        InvoiceDateColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        TotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @FXML
    void handleRent(ActionEvent event) {
        System.out.println("Entered handleRent");
        Data.getInstance().getSystem().printRentals();

        Rental rental = new Rental();

        rental.setCustomer(CustomerComboBox.getValue());
        rental.setCar(CarComboBox.getValue());

        rental.setStartDate(StartDateDP.getValue());
        rental.setEndDate(EndDateDP.getValue());
        rental.setDeposit(2000);
        rental.setInvoice(new Invoice(LocalDate.now(),new ArrayList<Payment>()));

        System.out.println(Data.getInstance().getSystem().bookCarRental(rental));
        Data.getInstance().getSystem().printRentals();




    }



}
