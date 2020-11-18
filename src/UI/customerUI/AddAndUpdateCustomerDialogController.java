package UI.customerUI;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Visitor;

import java.time.LocalDate;

public class AddAndUpdateCustomerDialogController {
    @FXML
    private TextField customerId;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField nationality;
    @FXML
    private TextField drivingLicence;
    @FXML
    private TextField passportNo;
    @FXML
    private DatePicker entryDate;
    @FXML
    private DatePicker visaExpiryDate;

    public void initialize() {
        entryDate.setValue(LocalDate.now());
        visaExpiryDate.setValue(LocalDate.now());
    }

    public Visitor processResult() throws IllegalArgumentException {
        String inputName = name.getText();
        String inputPhone = phone.getText();
        String inputAddress = address.getText();
        String inputNationality = nationality.getText();
        String inputDrivingLicence = drivingLicence.getText();
        LocalDate inputEntryDate = entryDate.getValue();
        LocalDate inputVisaExpiryDate = visaExpiryDate.getValue();

        if (inputName.equals("") || inputPhone.equals("") || inputAddress.equals("") || inputNationality.equals("") || inputDrivingLicence.equals("")) {
            throw new IllegalArgumentException("All fields should be filled to add a customer");
        }
        try {

            int inputCustomerId = Integer.parseInt(customerId.getText());
            int inputPassportNo = Integer.parseInt(passportNo.getText());
            return new Visitor(inputCustomerId, inputName, inputPhone, inputAddress, inputNationality, inputDrivingLicence, inputPassportNo, inputEntryDate, inputVisaExpiryDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("customer Id and passport No fields should be numbers!");
        }
    }

    public void populateFields(Visitor visitor) {
        customerId.setText(String.valueOf(visitor.getCustomerId()));
        name.setText(visitor.getName());
        phone.setText(visitor.getPhone());
        address.setText(visitor.getNationality());
        nationality.setText(visitor.getNationality());
        drivingLicence.setText(visitor.getDrivingLicence());
        passportNo.setText(String.valueOf(visitor.getPassportNo()));
        entryDate.setValue(visitor.getEntryDate());
        visaExpiryDate.setValue(visitor.getVisaExpiryDate());

    }

}
