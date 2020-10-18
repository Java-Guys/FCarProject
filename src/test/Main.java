package test;

import model.*;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        FCarSystem system = new FCarSystem();

        //adding customers

        Customer customer = new Customer(  123456789,
                "Mohamed Hamdy",
                "11111111",
                "south khalifa",
                "Egyptian",
                "driving licence");

        Customer customer1 = new Customer( 516249875,
                "Omar Elkashef",
                "22222222",
                "duhail",
                "Egyptian",
                "driving licence");

        Customer customer2 = new Customer(  957168429,
                "Mahmoud Talkhan",
                "55164672",
                "south khalifa",
                "Egyptian",
                "driving licence");

        Customer customer3 = new Customer( 961486329,
                "Ezeldin Ahmed",
                "33333333",
                "doha",
                "Egyptian",
                "driving licence");


        System.out.println(system.addCustomer(customer));
        System.out.println(system.addCustomer(customer1));
        System.out.println(system.addCustomer(customer2));
        System.out.println(system.addCustomer(customer3));


        //adding cars
        System.out.println(system.addCar(new Car(
                "5884469",
                "toyota-cruser",
                CarType.SUV)));

        System.out.println(system.addCar(new Car(
                "6258458",
                "nissan-sunny",
                CarType.SEDAN)));

        System.out.println(system.addCar(new Car(
                "6518465",
                "matsubishi-lancer",
                CarType.SEDAN)));

        System.out.println(system.addCar(new Car(
                "632594",
                "toyota-prado",
                CarType.SUV)));

        System.out.println(system.addCar(new Car(
                "216894",
                "kia-sportage",
                CarType.SUV)));

        system.printCustomers();
        system.printCars();
        System.out.println();
        System.out.println(system.findCar("5884469"));
        System.out.println(system.findCar("111111"));

        System.out.println(system.deleteCar("6258458"));
        System.out.println(system.deleteCar("555555"));

        System.out.println(system.findCustomer(123456789));
        System.out.println(system.findCustomer(000000000));

        System.out.println(system.deleteCustomer(961486329));
        System.out.println(system.deleteCustomer(000000000));

        System.out.println("Printing available cars: ");
        String string = "_";
        System.out.println(string.repeat(76));
        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
        System.out.println("|" + string.repeat(74) + "|");

        for (Car car : system.getCarByAvailability(false)) {
            System.out.println(car);
        }
        System.out.println("|" + string.repeat(74) + "|");

        Rental rental = new Rental();
        rental.setCustomer(system.findCustomer(111111111));
        rental.setCar(system.findCar("5884469"));
        rental.setStartDate(LocalDate.of(2020, 11,1 ));
        rental.setEndDate(LocalDate.of(2020, 10,25 )); //should not accept end date that before start date
        rental.setEndDate(LocalDate.of(2020, 11,25 )); //should not accept end date that before start date
        rental.setDeposit(2000);
        System.out.println(system.bookCarRental(rental));

        rental.setCustomer(system.findCustomer(123456789));
        System.out.println(system.bookCarRental(rental));

        System.out.println("Printing available cars: ");
        System.out.println(string.repeat(76));
        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
        System.out.println("|" + string.repeat(74) + "|");

        for (Car car : system.getCarByAvailability(false)) {
            System.out.println(car);
        }
        System.out.println("|" + string.repeat(74) + "|");

        for (Rental rentl: system.findCarRentalByCustomerId(customer.getCustomerId())){
            System.out.println(rentl);
        }

        System.out.println("Printing available cars by 24-11: ");
        System.out.println(string.repeat(76));
        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
        System.out.println("|" + string.repeat(74) + "|");

        for (Car car : system.getAvailableCarByDate(LocalDate.of(2020,11,26))) {
            System.out.println(car);
        }
        System.out.println("|" + string.repeat(74) + "|");

        System.out.println(system.returnCar("5884469"));

        system.printCars();



    }
}
