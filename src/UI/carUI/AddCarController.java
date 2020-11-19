package UI.carUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import model.Car;
import model.CarType;
import model.Customer;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;

public class AddCarController {
    @FXML
    private TextField plateNo;
    @FXML
    private TextField model;
    @FXML
    private ComboBox type;
    @FXML
    private RadioButton available;
    @FXML
    private RadioButton notAvailable;
    @FXML
    private TextField dailyRentalRate;
    @FXML
    private Text invalid;


    private ToggleGroup group = new ToggleGroup();

    public void initialize() {
        //grouping the availability radio button
        available.setToggleGroup(group);
        notAvailable.setToggleGroup(group);
        group.selectToggle(available);
        type.setValue(CarType.SEDAN);
        type.setItems(FXCollections.observableArrayList(CarType.values()));
        invalid.setVisible(false);

    }

    public Car processResult() throws IllegalArgumentException {
        String inputPlateNo = plateNo.getText().trim();
        String inputModel = model.getText().trim();
        String inputType = type.getValue().toString();
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        System.out.println(selectedRadioButton.getText());
        boolean inputAvailable = selectedRadioButton.getText().equalsIgnoreCase("Yes") ? true : false;
        CarType carType = null;
        switch (inputType) {
            case "suv":
                carType = CarType.SUV;
                break;

            case "sedan":
                carType = CarType.SEDAN;
                break;

            case "van":
                carType = CarType.VAN;
                break;

            case "truck":
                carType = CarType.TRUCK;
                break;

        }
        if (inputPlateNo.equals("") || inputModel.equals("") ){
            System.out.println("Entered");
            throw new IllegalArgumentException("All fields should be filled in order to add a car.");
        }
        try {
            double rentalRate = Double.parseDouble(dailyRentalRate.getText());
            return new Car(inputPlateNo, inputModel, inputAvailable, carType, rentalRate);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            throw new IllegalArgumentException("Rental rate should be a number! ");
        }

    }

    public void populateFields(String initialPlateNo, String initialModel, CarType initialType, Boolean initialAvailability, double initialRentalRate) {
        plateNo.setText(initialPlateNo);
        model.setText(initialModel);
        type.setValue(initialType);
        group.selectToggle(initialAvailability ? available : notAvailable);
        dailyRentalRate.setText("" + initialRentalRate);

    }
}
