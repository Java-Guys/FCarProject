package model;

import java.util.ArrayList;

/**
 * @author Omar Alkashef
 * Creation Date : 14-10-2020
 * @version 1
 */

public class FCarSystem {
    private ArrayList<Customer> customers;
    private ArrayList<Car> cars;
    private ArrayList<Rental> rentals;

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
    public String addCar(Car car){
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getPlateNo() == car.getPlateNo()){
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
    public Car findCar(String plateNo){
        for(int i = 0; i < cars.size(); i++){
            if (cars.get(i).getPlateNo() == plateNo){
                return cars.get(i);
            }
        }
        return null;
    }

    /**
     * @param plateNo
     * @return message
     */
    public String deleteCar(String plateNo){
        for(int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlateNo() == plateNo) {
                cars.remove(i);
                for(int j = 0; j < rentals.size(); j++){
                    if(rentals.get(j).getCar().getPlateNo() == plateNo){
                        rentals.remove(j);
                    }
                }
                return "Deleted Car successfully";
            }
        }
        return "Couldn't find the Car";
    }

    /**
     * @param customer
     * @return message
     */
    public String addCustomer(Customer customer){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerId() == customer.getCustomerId()){
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
    public Customer finCustomer(int customerId){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerId() == customerId){
                return customers.get(i);
            }
        }
        return null;
    }

    /**
     * @param customerId
     * @return message
     */
    public String deleteCustomer(int customerId){
        for(int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) {
                customers.remove(i);
                return "Delete Customer successfully";
            }
        }
        return "Couldn't find the Customer";
    }


}
