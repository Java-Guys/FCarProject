package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M-Hamdy-M
 * @author Omar Alkashef
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * Creation Date : 14-10-2020
 * @version 3
 */

public class FCarSystem {
    private List<Customer> customers;
    private List<Car> cars;
    private List<Rental> rentals;

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
    public FCarSystem(List<Customer> customers,
                      List<Car> cars,
                      List<Rental> rentals) {
        this.customers = customers;
        this.cars = cars;
        this.rentals = rentals;
    }

    /**
     * @return customers
     */
    public List<Customer> getCustomers() {
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
    public List<Car> getCars() {
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
    public List<Rental> getRentals() {
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
        if (car == null) {
            return "cannot add null";
        }
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
                for (int j = 0; j < rentals.size(); j++) {
                    if (rentals.get(j).getCar().getPlateNo() == plateNo) {
                        rentals.remove(j);
                    }
                }
                cars.remove(i);
                return "Deleted Car and it's rentals successfully";
            }
        }
        return "Couldn't find a car with this plate number! ";
    }

    /**
     * @param customer
     * @return message
     */
    public String addCustomer(Customer customer) {
        if (customer == null) {
            return "cannot add null";
        }
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
        for (int i = 0; i < getRentals().size(); i++) {
            if (rentals.get(i).getCustomer().getCustomerId() == customerId) {
                return "Can't delete this customer. there are rentals associated with him please finish these rentals first!";

            }
        }
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) {
                customers.remove(i);
                return "Delete Customer successfully";
            }
        }
        return "Couldn't a customer with this id. ";
    }

    /**
     * @param available
     * @return returnCars
     */
    public List<Car> getCarByAvailability(boolean available) {
        List<Car> returnCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() == available) {
                returnCars.add(car);
            }
        }
        return returnCars;
    }

    /**
     * @param rental
     * @return message
     */
    public String bookCarRental(Rental rental) {
        if (rental.getCustomer() == null || rental.getCar() == null || rental.getStartDate() == null) {
            return "Please provide a rental with enough information!";
        }
        if (rental.getDeposit() < 2000) {
            return "please deposit 2000QR or more to book a rental";
        }

        for (Car car : cars) {
            if (car.getPlateNo().equals(rental.getCar().getPlateNo())) {
                if (!car.isAvailable()) {
                    return "Car is not available";
                }
                boolean customerExist = false;
                for (Customer customer : customers) {
                    if (customer.getCustomerId() == rental.getCustomer().getCustomerId()) {
                        car.setAvailable(false);
                        rentals.add(rental);

                        return "Rental has been booked successfully";
                    }
                }

                return "Please add this customer to the list before booking a rental";
            }
        }
        return "Could't find a car with this information";
    }

    /**
     * @param plateNo
     * @return Invoice
     */
    public Invoice returnCar(String plateNo) {
        Rental activeRental = new Rental();

        for (Rental rental : rentals) {
            if (rental.getCar().getPlateNo().equals(plateNo)) {
                activeRental = rental;
                for (Car car : cars) {
                    if (car.getPlateNo().equals(activeRental.getCar().getPlateNo())) {
                        car.setAvailable(true);
                    }
                }
                rental.setEndDate(LocalDate.now());

                //adding the rental payment to the invoice
                if (rental.getInvoice() == null) {
                    Invoice invoice = new Invoice();
                    invoice.setInvoiceDate(LocalDate.now());

                    rental.setInvoice(invoice);
                }
                int period = rental.getEndDate().getDayOfYear() - rental.getStartDate().getDayOfYear();
                Payment payment = new Payment();
                payment.setPaymentDescription("rental for " + period + " days");
                payment.setPrice(rental.getCar().getDailyRentalRate() * period);
                payment.setPaymentDate(LocalDate.now());  // user can change it later
                rental.getInvoice().addPayment(payment);

                double total = rental.getInvoice().calculateTotalPayment();
                if (total > activeRental.getDeposit()) {
                    total -= activeRental.getDeposit();
                    activeRental.setDeposit(0);
                    System.out.println("You should pay QR" + total);
                    System.out.println("Your deposit has been spent");
                } else {
                    activeRental.setDeposit(activeRental.getDeposit() - total);
                    System.out.println("You should take back QR" + activeRental.getDeposit());
                    activeRental.setDeposit(0); //supposing customer has taken the remaining deposit

                }
                rentals.remove(activeRental);
                return activeRental.getInvoice();
            }
        }
        System.out.println("Couldn't find a rented car with this plate Number!");
        return null;
    }

    /**
     * @param customerId
     * @return returnRentals
     */
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

    /**
     * @param customerId
     * @return message
     */
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
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getCustomer().getCustomerId() == customerId) {
                String plateNo = rentals.get(i).getCar().getPlateNo();
                for (Car car : cars) {
                    if (car.getPlateNo().equals(plateNo)) {
                        car.setAvailable(true);
                        break;
                    }
                }
                rentals.remove(i);
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

    /**
     * @param date
     * @return returnCars
     */
    public List<Car> getAvailableCarByDate(LocalDate date) {
        List<Car> returnCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.isAvailable()) {
                returnCars.add(car);
            }
        }

        for (Rental rental : rentals) {
            if (rental.getEndDate() != null && rental.getEndDate().isBefore(date)) {
                returnCars.add(rental.getCar());
            }
        }
        return returnCars;
    }


    /**
     * print list of customers (for testing purposes)
     */
    //for testing purposes
    public void printCustomers() {

        if (customers.size() < 1) {
            System.out.println("There are no customers in the list!");
            return;
        }


        String string = "_";
        System.out.println(string.repeat(115));
        System.out.printf("No |%10s|%20s|%15s|%25s|%15s|%20s|\n",
                "Id", "Name", "Phone number", "Address", "Nationality", "Driving Licence");
        System.out.println("" + string.repeat(114) + "|");

        for (int i = 0; i < getCustomers().size(); i++) {
            System.out.printf("%3d%s\n", i + 1, customers.get(i));
        }
        System.out.println("" + string.repeat(114) + "|");

    }

    /**
     * print list of Cars(for testing purposes)
     */
    public void printCars() {
        if (cars.size() < 1) {
            System.out.println("There are no cars in the list! ");
            return;
        }
        DecimalFormat qr = new DecimalFormat("QR#,##0.00");
        String string = "_";
        System.out.println(string.repeat(81));
        System.out.printf("No  |%10s|%20s|%10s|%10s|%20s|\n",
                "plate No", "model", "available", "type", "daily rental rate");
        System.out.println("" + string.repeat(79) + "|");

        for (int i = 0; i < getCars().size(); i++) {
            System.out.printf("%4d%s\n", i + 1, getCars().get(i));
        }
        System.out.println("" + string.repeat(79) + "|");
    }

    /**
     * print list of rentals(for testing purposes)
     */
    public void printRentals() {
        for (Rental rental : rentals) {
            System.out.println(rental);
            System.out.println();
        }
    }
}
