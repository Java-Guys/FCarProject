package UI.carUI;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Car;
import model.CarType;

/**
 * @author M-Hamdy-M
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * Creation Date : 11-11-2020
 * @version 3
 */
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


    private ToggleGroup group = new ToggleGroup();

    /**
     * grouping the radio buttons in a toggle group
     * and setting the default values for the rest fields
     */
    public void initialize() {
        //grouping the availability radio button
        available.setToggleGroup(group);
        notAvailable.setToggleGroup(group);
        group.selectToggle(available);
        type.setValue(CarType.SEDAN);
        type.setItems(FXCollections.observableArrayList(CarType.values()));
    }

    /**
     * @return Car
     * @throws IllegalArgumentException
     */
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

    /**
     * @param car
     */
    public void populateFields(Car car) {
        plateNo.setText(car.getPlateNo());
        model.setText(car.getModel());
        type.setValue(car.getType());
        group.selectToggle(car.getIsAvailable() ? available : notAvailable);
        dailyRentalRate.setText("" + car.getDailyRentalRate());

    }
}
