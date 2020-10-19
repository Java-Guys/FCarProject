package test;

import model.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FCarSystem system = new FCarSystem();

        //adding some customers to the system

        Customer customer = new Customer(123456789,
                "Mohamed Hamdy",
                "11111111",
                "south khalifa",
                "Egyptian",
                "driving licence");

        Customer customer1 = new Customer(516249875,
                "Omar Elkashef",
                "22222222",
                "duhail",
                "Egyptian",
                "driving licence");

        Customer customer2 = new Customer(957168429,
                "Mahmoud Talkhan",
                "55164672",
                "south khalifa",
                "Egyptian",
                "driving licence");

        Customer customer3 = new Customer(961486329,
                "Ezeldin Ahmed",
                "33333333",
                "doha",
                "Egyptian",
                "driving licence");

        Customer customer4 = new Customer(356876876,
                "Ali Hemeda",
                "44444444",
                "doha",
                "Hindi",
                "driving licence");

        Customer customer5 = new Customer(356876876,
                "Mohamed Akram",
                "55555555",
                "doha",
                "Somalia",
                "driving licence");


        System.out.println(system.addCustomer(customer));
        System.out.println(system.addCustomer(customer1));
        System.out.println(system.addCustomer(customer2));
        System.out.println(system.addCustomer(customer3));
        System.out.println(system.addCustomer(customer4));
        System.out.println(system.addCustomer(customer5));


        //adding cars to the system
        System.out.println(system.addCar(new Car(
                "5884469",
                "toyota-cruser",
                CarType.SUV,
                500)));


        System.out.println(system.addCar(new Car(
                "6258458",
                "nissan-sunny",
                CarType.SEDAN,
                80)));

        System.out.println(system.addCar(new Car(
                "6518465",
                "matsubishi-lancer",
                CarType.SEDAN,
                100)));

        System.out.println(system.addCar(new Car(
                "632594",
                "toyota-prado",
                CarType.SUV,
                300)));

        System.out.println(system.addCar(new Car(
                "216894",
                "kia-sportage",
                CarType.SUV,
                150)));

        System.out.println(system.addCar(new Car(
                "510509",
                "mini cooper",
                CarType.SEDAN,
                600)));

        System.out.println(system.addCar(new Car(
                "7755098",
                "Honda city",
                CarType.SEDAN,
                2000000)));

        System.out.println(system.addCar(new Car(
                "6549098",
                "Honda Civic",
                CarType.SEDAN,
                300)));

        System.out.println(system.addCar(new Car(
                "3256786",
                "Ferrari spider",
                CarType.SEDAN,
                2000)));

        while (true) {
            System.out.println("please choose what you want to do:");
            System.out.println("[1] sign in.");
            System.out.println("[2] sign up.");
            System.out.println("[3] exit.");
            short choice = scanner.nextShort();
            if (choice == 1) {
                choice = signIn(system);
                if (choice == -1) {
                    continue;
                } else {
                    System.out.println("started");
                    start(system, choice);
                }
            } else if (choice == 2) {
                singUp(system);
                continue;
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Please provide a valid choice! ");
                continue;
            }

        }
    }

    public static short signIn(FCarSystem system) {
        system.printCustomers();
        System.out.println("Please enter the no of the customer: (enter -1 if you are not in the list)");
        short choice = scanner.nextShort();
        if (choice == -1) {
            singUp(system);
            return -1;
        } else if (choice > system.getCustomers().size() || choice < 1) {
            System.out.println("Please provide valid no!");
            return -1;
        } else {
            System.out.println("You have signed in with this customer: ");
            System.out.println(system.getCustomers().get(choice - 1));
            return (short) (choice - 1);
        }
    }

    public static void singUp(FCarSystem system) {
        System.out.println("Enter Your Id number");
        int id = scanner.nextInt();
        System.out.println("Enter Your name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter Your phone number");
        String phone = scanner.next();
        System.out.println("Enter your address: ");
        scanner.nextLine();
        String address = scanner.nextLine();
        System.out.println("Enter your nationality: ");
        String nationality = scanner.next();
        System.out.println("Enter your driving Licence: ");
        scanner.nextLine();
        String licence = scanner.nextLine();

        if (name == null || phone == null || address == null || nationality == null || licence == null){
            System.out.println("Please provide enough right information!");
            return;
        }
        System.out.println(system.addCustomer(new Customer(id, name, phone, address, nationality, licence)));

        System.out.println("You will be directed to the home page");
        return;
    }

    public static void start(FCarSystem system, short customerIndex) {
        while (true) {
            System.out.println("Enter what you want to do: ");
            System.out.println("[1] book a rental.");
            System.out.println("[2] return a car. ");
            System.out.println("[3] print list of available cars.");
            System.out.println("[4] print list of available cars by date. ");
            System.out.println("[] quit.");

            short choice = scanner.nextShort();
            switch (choice) {
                case 1: {
                    while (true) {
                        Rental rental = new Rental();
                        rental.setCustomer(system.getCustomers().get(customerIndex));
                        system.printCars();
                        System.out.println("Please enter the no of the car you want to rent: enter -1 if you want to return");
                        choice = scanner.nextShort();
                        if (choice == -1) {
                            break;
                        }
                        if (choice < 1 || choice > system.getCars().size()) {
                            System.out.println("out of pounds number please try again!");
                            continue;
                        }
                        rental.setCar(system.getCars().get(choice - 1));
                        System.out.println("Please enter the start date of the rental");
                        LocalDate startDate = takeDate();
                        if (startDate == null) {
                            System.out.println("Invalid date!");
                            continue;
                        }
                        rental.setStartDate(startDate);
                        System.out.println("Do You want to specify the end date now? (y=1,n=2)");
                        choice = scanner.nextShort();
                        if (choice == 1) {
                            System.out.println("Please enter the end date of the rental");
                            LocalDate endDate = takeDate();
                            if (endDate == null) {
                                System.out.println("Invalid date!");
                                continue;
                            }
                            rental.setEndDate(endDate);
                        }
                        System.out.println("How much do you want to deposit? (please deposit more than 2000 to be able to rent)");
                        int deposit = scanner.nextInt();
                        rental.setDeposit(deposit);
                        System.out.println(system.bookCarRental(rental));

                        break;
                    }
                    break;
                }
                case 2:
                    while (true) {
                        system.printCars();
                        System.out.println("Please enter the no of the car you want to return: enter -1 if you want to return");
                        choice = scanner.nextShort();
                        if (choice == -1) {
                            break;
                        }
                        if (choice < 1 || choice > system.getCars().size()) {
                            System.out.println("out of pounds number please try again!");
                            continue;
                        }
                        system.returnCar(system.getCars().get(choice - 1).getPlateNo());
                        system.printRentals();
                        break;
                    }
                    break;
                case 3: {
                    System.out.println("Printing available cars: ");
                    String string = "_";
                    System.out.println(string.repeat(76));
                    System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
                    System.out.println("|" + string.repeat(74) + "|");

                    for (Car car : system.getCarByAvailability(true)) {
                        System.out.println(car);
                    }
                    System.out.println("|" + string.repeat(74) + "|");
                    break;
                }
                case 4: {
                    System.out.println("Enter the date: ");
                    LocalDate date = takeDate();
                    if (date == null) {
                        continue;
                    }
                    String string = "_";
                    System.out.println(string.repeat(76));
                    System.out.printf("|%10s|%20s|%10s|%10s|%20s|\n", "plate No", "model", "available", "type", "daily rental rate");
                    System.out.println("|" + string.repeat(74) + "|");

                    for (Car car : system.getAvailableCarByDate(date)) {
                        System.out.println(car);
                    }
                    System.out.println("|" + string.repeat(74) + "|");

                }
            }


        }
    }

    public static LocalDate takeDate() {
        LocalDate returnDate;
        try {
            System.out.println("Please enter the year: ");
            int year = scanner.nextInt();
            System.out.println("Please enter the month: ");
            int month = scanner.nextInt();
            System.out.println("Please enter the day: ");
            int day = scanner.nextInt();
            returnDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println("Invalid date Please try again");
            return null;
        }
        if (returnDate.isBefore(LocalDate.of(2020, 1, 1)) || returnDate.isAfter(LocalDate.of(2050, 1, 1))) {
            System.out.println("Invalid date please provide a date after 2020 and before 2050");
            return null;
        }
        return returnDate;

    }

}
