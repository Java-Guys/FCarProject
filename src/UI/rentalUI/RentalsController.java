package UI.rentalUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RentalsController implements Initializable {

    @FXML
    private ComboBox<Visitor> visitorComboBox;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        visitorComboBox.setItems(Data.getInstance().getVisitors());
        visitorComboBox.setValue(Data.getInstance().getVisitors().get(0));
        carComboBox.setItems(Data.getInstance().getSystem().getCarByAvailability(true));
        carComboBox.setValue(Data.getInstance().getSystem().getCarByAvailability(true).get(1));
        startDateDP.setValue(LocalDate.now());
        endDateDP.setValue(LocalDate.now().plusDays(1));
        rentalsTable.setItems(Data.getInstance().getSystem().getRentals());
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        invoiceNoColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        invoiceDateColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @FXML
    void handleRent(ActionEvent event) {
        System.out.println("Entered handleRent");
        Data.getInstance().getSystem().printRentals();

        Rental rental = new Rental();

        rental.setCustomer(visitorComboBox.getValue());
        rental.setCar(carComboBox.getValue());

        rental.setStartDate(startDateDP.getValue());
        rental.setEndDate(endDateDP.getValue());
        rental.setDeposit(2000);
        rental.setInvoice(new Invoice(LocalDate.now(),new ArrayList<Payment>()));

        System.out.println(Data.getInstance().getSystem().bookCarRental(rental));
        Data.getInstance().getSystem().printRentals();




    }



}
