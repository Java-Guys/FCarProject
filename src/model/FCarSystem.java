package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omar Alkashef
 * Creation Date : 14-10-2020
 * @version 1
 */

public class FCarSystem {
    private ArrayList<Customer> customers;
    private ArrayList<Car> cars;
    private ArrayList<Rental> rentals;

    public FCarSystem() {
        customers = new ArrayList<>();
        cars = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    /**
     * @param customers
     * @param cars
     * @param rentals
     */
    public FCarSystem(ArrayList<Customer> customers,
                      ArrayList<Car> cars,
                      ArrayList<Rental> rentals) {
        this.customers = customers;
        this.cars = cars;
        this.rentals = rentals;
    }

    /**
     * @return customers
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * @return cars
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * @param cars
     */
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    /**
     * @return rentals
     */
    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    /**
     * @param rentals
     */
    public void setRentals(ArrayList<Rental> rentals) {
        this.rentals = rentals;
    }


    /**
     * @param car
     * @return message
     */
    public String addCar(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlateNo() == car.getPlateNo()) {
                return "this car already exists";
            }
        }
        cars.add(car);
        return "added car successfully";
    }

    /**
     * @param plateNo
     * @return car
     */
    public Car findCar(String plateNo) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlateNo() == plateNo) {
                return cars.get(i);
            }
        }
        System.out.println("Couldn't find a car with this plate number! ");
        return null;
    }

    /**
     * @param plateNo
     * @return message
     */
    public String deleteCar(String plateNo) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlateNo() == plateNo) {
                cars.remove(i);
                for (int j = 0; j < rentals.size(); j++) {
                    if (rentals.get(j).getCar().getPlateNo() == plateNo) {
                        rentals.remove(j);
                    }
                }
                return "Deleted Car successfully";
            }
        }
        return "Couldn't find a car with this plate number! ";
    }

    /**
     * @param customer
     * @return message
     */
    public String addCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customer.getCustomerId()) {
                return "this customer already exists!";
            }

        }
        customers.add(customer);
        return "Added customer successfully";
    }

    /**
     * @param customerId
     * @return customer
     */
    public Customer findCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) {
                return customers.get(i);
            }
        }
        System.out.println("Couldn't find a customer with this id!");
        return null;
    }

    /**
     * @param customerId
     * @return message
     */
    public String deleteCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) {
                customers.remove(i);
                return "Delete Customer successfully";
            }
        }
        return "Couldn't find the Customer";
    }

    public List<Car> getCarByAvailability(boolean available) {
        List<Car> returnCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() == available) {
                returnCars.add(car);
            }
        }
        return returnCars;
    }

    public String bookCarRental(Rental rental) {
        if (rental.getCustomer() == null || rental.getCar() == null || rental.getStartDate() == null || rental.getEndDate() == null){
            return "Please provide a rental with enough information!";
        }
        if (rental.getDeposit() < 2000){
            return "please deposit 2000QR or more to book a rental";
        }

        for (Car car : cars) {
            if (car.getPlateNo().equals(rental.getCar().getPlateNo())) {
                if (!car.isAvailable()) {
                    return "Car is not available";
                }
                boolean customerExist = false;
                for (Customer customer: customers){
                    if (customer.getCustomerId() == rental.getCustomer().getCustomerId()){
                        customerExist = true;
                        break;
                    }
                }
                if (!customerExist){
                    return "Please add this customer to the list before booking a rental";
                }
                car.setAvailable(false);
                rentals.add(rental);
                return "Rental has been booked successfully";
            }
        }
        return "Could't find a car with this information";
    }

    public Invoice returnCar(String plateNo) {
        Rental activeRental = new Rental();

        for (Rental rental : rentals) {
            if (rental.getCar().getPlateNo().equals(plateNo)) {
                activeRental = rental;
                for (Car car: cars){
                    if (car.getPlateNo().equals(activeRental.getCar().getPlateNo())){
                        car.setAvailable(true);
                    }
                }
                double total = rental.getInvoice().calculateTotalPayment();
                if (total > activeRental.getDeposit()){
                    total =- activeRental.getDeposit();
                    activeRental.setDeposit(0);
                    System.out.println("You should pay QR" + total);
                    System.out.println("Your deposit has been spent");
                }
                else {
                    activeRental.setDeposit(activeRental.getDeposit()-total);
                    System.out.println("You should take back QR"+ activeRental.getDeposit());
                }
                rentals.remove(activeRental);
                return activeRental.getInvoice();
            }
        }
            System.out.println("Couldn't find a rented car with this plate Number!");
            return null;
    }

    public List<Rental> findCarRentalByCustomerId(int customerId) {
        boolean customerExist = false;
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                customerExist = true;
                break;
            }
        }
        if (!customerExist) {
            System.out.println("there are no customer with this id!");
            return null;
        }

        List<Rental> returnRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerId() == customerId) {
                returnRentals.add(rental);
            }
        }
        return returnRentals;
    }

    public String deleteCarRental(int customerId) {
        boolean customerExist = false;
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                customerExist = true;
                break;
            }
        }
        if (!customerExist) {
            System.out.println("there are no customer with this id!");
            return null;
        }
        int counter = 0;
        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerId() == customerId) {
                String plateNo = rental.getCar().getPlateNo();
                for (Car car : cars) {
                    if (car.getPlateNo().equals(plateNo)) {
                        car.setAvailable(true);
                    }
                }
                rentals.remove(rental);
                counter++;
            }
        }
        switch (counter) {
            case 0:
                return "Couldn't find a rental associated with this customer";
            case 1:
                return "deleted rental Successfully";
            default:
                return "deleted rentals Successfully";
        }

    }

    public List<Car> getAvailableCarByDate(LocalDate date) {
        List<Car> returnCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.isAvailable()) {
                returnCars.add(car);
            }
        }

        for (Rental rental : rentals) {
            if (rental.getEndDate().isBefore(date)) {
                returnCars.add(rental.getCar());
            }
        }
        return returnCars;
    }


    //for testing purposes
    public void printCustomers() {
        if (customers.size() < 1){
            System.out.println("There are no customers in the list!");
            return;
        }
        String string = "_";
        System.out.println(string.repeat(112));
        System.out.printf("|%10s|%20s|%15s|%25s|%15s|%20s|\n", "Id", "Name", "Phone number", "Address", "Nationality", "Driving Licence");
        System.out.println("|" + string.repeat(110) + "|");

        for (var customer : customers) {
            System.out.println(customer);
        }
        System.out.println("|" + string.repeat(110) + "|");

    }

    public void printCars() {
        if (cars.size() < 1){
            System.out.println("There are no cars in the list! ");
            return;
        }
        DecimalFormat qr = new DecimalFormat("QR#,##0.00");
        String string = "_";
        System.out.println(string.repeat(76));
        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
        System.out.println("|" + string.repeat(74) + "|");

        for (var car : cars) {
            System.out.println(car);
        }
            System.out.println("|" + string.repeat(74) + "|");
    }

}
