//package test;
//
//import model.*;
//import java.time.LocalDate;
//
///**
// * @author M-Hamdy-M
// * @author Omar Alkashef
// * @author Ezeldin Ahmed
// * @author Mahmoud Shreif
// * Creation Date : 18-10-2020
// * @version 2
// */
//public class App {
//    public static void main(String[] args) {
//
//        FCarSystem system = new FCarSystem();
//
//        //adding customers to the system
//
//        Customer customer = new Visitor(123456789,
//                "Mohamed Hamdy",
//                "11111111",
//                "south khalifa",
//                "Egyptian",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//        Customer customer1 = new Visitor(516249875,
//                "Omar Elkashef",
//                "22222222",
//                "duhail",
//                "Egyptian",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//        Customer customer2 = new Visitor(957168429,
//                "Mahmoud Talkhan",
//                "55164672",
//                "south khalifa",
//                "Egyptian",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//        Customer customer3 = new Visitor(961486329,
//                "Ezeldin Ahmed",
//                "33333333",
//                "doha",
//                "Egyptian",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//        Customer customer4 = new Visitor(356876876,
//                "Ali Hemeda",
//                "44444444",
//                "doha",
//                "Hindi",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//        Customer customer5 = new Visitor(356876876,
//                "Mohamed Akram",
//                "55555555",
//                "doha",
//                "Somalia",
//                "driving licence",
//                18290346,
//                LocalDate.of(2020, 7, 20),
//                LocalDate.of(2022, 7, 20));
//
//
//        System.out.println(system.addCustomer(customer));
//        System.out.println(system.addCustomer(customer1));
//        System.out.println(system.addCustomer(customer2));
//        System.out.println(system.addCustomer(customer3));
//        System.out.println(system.addCustomer(customer4));
//        System.out.println(system.addCustomer(customer5));
//
//
//        //adding cars to the system
//        System.out.println(system.addCar(new Car(
//                "5884469",
//                "toyota-cruser",
//                CarType.SUV,
//                500)));
//
//
//        System.out.println(system.addCar(new Car(
//                "6258458",
//                "nissan-sunny",
//                CarType.SEDAN,
//                80)));
//
//        System.out.println(system.addCar(new Car(
//                "6518465",
//                "matsubishi-lancer",
//                CarType.SEDAN,
//                100)));
//
//        System.out.println(system.addCar(new Car(
//                "632594",
//                "toyota-prado",
//                CarType.SUV,
//                300)));
//
//        System.out.println(system.addCar(new Car(
//                "216894",
//                "kia-sportage",
//                CarType.SUV,
//                150)));
//
//        System.out.println(system.addCar(new Car(
//                "510509",
//                "mini cooper",
//                CarType.SEDAN,
//                600)));
//
//        System.out.println(system.addCar(new Car(
//                "7755098",
//                "Honda city",
//                CarType.SEDAN,
//                2000000)));
//
//        System.out.println(system.addCar(new Car(
//                "6549098",
//                "Honda Civic",
//                CarType.SEDAN,
//                300)));
//
//        System.out.println(system.addCar(new Car(
//                "3256786",
//                "Ferrari spider",
//                CarType.SEDAN,
//                2000)));
//
//
//        system.printCustomers();
//        system.printCars();
//
//
//        System.out.println();
//        //testing findCar method one time with existing plateNo and another time with not existing plate number
//        System.out.println();
//        System.out.println(system.findCar("5884469"));
//        System.out.println(system.findCar("111111"));
//
//        //testing deleteCar method one time with existing plateNo and another time with not existing plate number
//        System.out.println(system.deleteCar("6258458"));
//        System.out.println(system.deleteCar("555555"));
//
//        //testing findCustomer method one time with existing customerId and another time with not existing customerId
//        System.out.println(system.findCustomer(123456789));
//        System.out.println(system.findCustomer(000000000));
//
//        //testing deleteCustomer method one time with existing customerId and another time with not existing customerId
//        System.out.println(system.deleteCustomer(961486329));
//        System.out.println(system.deleteCustomer(000000000));
//
//        //testing getCarByAvailability method to print available cars (all cars are available by now)
//        System.out.println("Printing available cars: ");
//        String string = "_";
//        System.out.println(string.repeat(76));
//        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
//        System.out.println("|" + string.repeat(74) + "|");
//
//        for (Car car : system.getCarByAvailability(true)) {
//            System.out.println(car);
//        }
//        System.out.println("|" + string.repeat(74) + "|");
//
//
//        //creating rental object and initialize its fields to pass it to bookCarRental method
//        Rental rental = new Rental();
//        rental.setCustomer(system.findCustomer(111111111)); //customer will be null because there is no customer with this id
//        rental.setCar(system.findCar("5884469"));
//        rental.setStartDate(LocalDate.of(2020, 11, 1));
//        rental.setEndDate(LocalDate.of(2020, 10, 25)); //should not accept end date that before start date
//        rental.setEndDate(LocalDate.of(2020, 11, 25));
//        rental.setDeposit(2000);
//
//        System.out.println(system.bookCarRental(rental)); //will not accept because customer object is null
//
//        rental.setCustomer(system.findCustomer(customer.getCustomerId()));
//        System.out.println(system.bookCarRental(rental));
//
//        System.out.println("start");
//        Rental rental1 = new Rental();
//        rental1.setCustomer(system.findCustomer(customer1.getCustomerId()));
//        rental1.setCar(system.findCar("510509"));
//        rental1.setStartDate(LocalDate.of(2020, 11, 10));
//        rental1.setEndDate(LocalDate.of(2020, 12, 1));
//
//        System.out.println(system.bookCarRental(rental1)); //should not book the rental until the deposit be more than 2000
//
//        rental1.setDeposit(1000); //should refuse to book until deposit be more than 2000
//        System.out.println(system.bookCarRental(rental1));
//
//        rental1.setDeposit(2000);
//        System.out.println(system.bookCarRental(rental1));
//
//        rental1.getInvoice().addPayment(new Payment("door scratch",
//                500,
//                LocalDate.of(2020, 11, 20)));
//
//
//        //testing getCarByAvailability to print cars that is not available
//        System.out.println("Printing available cars: ");
//        System.out.println(string.repeat(76));
//        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
//        System.out.println("|" + string.repeat(74) + "|");
//
//        for (Car car : system.getCarByAvailability(false)) {
//            System.out.println(car);
//        }
//        System.out.println("|" + string.repeat(74) + "|");
//
//
//        for (Rental rental2 : system.findCarRentalByCustomerId(customer.getCustomerId())) {
//            System.out.println(rental2);
//        }
//
//        System.out.println("Printing available cars by 24-11: ");
//        System.out.println(string.repeat(76));
//        System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
//        System.out.println("|" + string.repeat(74) + "|");
//
//        for (Car car : system.getAvailableCarByDate(LocalDate.of(2020, 11, 24))) {
//            System.out.println(car);
//        }
//        System.out.println("|" + string.repeat(74) + "|");
//
//        System.out.println(system.returnCar("5884469"));
//        System.out.println(system.returnCar("510509"));
//    }
//}
