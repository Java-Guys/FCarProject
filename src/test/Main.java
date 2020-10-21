package test;

import model.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * @author M-Hamdy-M
 * created at: 19-10-2020
 * @version 1
 */
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
                "57489638",
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

        Customer customer5 = new Customer(951784953,
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
                100)));

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
            System.out.println("[3] admin sign in.");
            System.out.println("[4] exit.");
            short choice = scanner.nextShort();
            if (choice == 1) {
                choice = signIn(system);
                if (choice == -1) {
                    continue;
                } else {
                    start(system, choice);
                }
            } else if (choice == 2) {
                singUp(system);
                continue;
            } else if (choice == 3) {
                if (startAdmin(system)) {
                    return;
                }
            } else if (choice == 4) {
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

        if (name == null || phone == null || address == null || nationality == null || licence == null) {
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
            System.out.println("[5] quit.");

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
                    break;

                }
                case 5:
                    return;
                default:
                    System.out.println("Please enter a valid choice! ");
                    continue;
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

    public static boolean startAdmin(FCarSystem system) {
        boolean exit = true;
        while (true) {

            System.out.println("Please choose what you want to do: ");
            System.out.println("[1] add car.");
            System.out.println("[2] delete car. ");
            System.out.println("[3] delete rental by customer");
            System.out.println("[4] find customer. ");
            System.out.println("[5] delete customer. ");
            System.out.println("[6] find car rental by customer ");
            System.out.println("[7] print total income. ");
            System.out.println("[8] back to the main menu. ");
            short choice = scanner.nextShort();

            switch (choice) {
                case 1: {
                    System.out.println("Please enter the car's information here: ");
                    System.out.println("Enter plate number: ");
                    String plateNo = scanner.next();
                    System.out.println("Enter car model: ");
                    scanner.nextLine();
                    String model = scanner.nextLine();
                    System.out.println("Choose car's type: ");
                    System.out.println("[1] SEDAN.");
                    System.out.println("[2] SUV. ");
                    System.out.println("[3] VAN. ");
                    System.out.println("[4] TRUCK. ");
                    choice = scanner.nextShort();
                    CarType type;
                    switch (choice) {
                        case 1:
                            type = CarType.SEDAN;
                            break;
                        case 2:
                            type = CarType.SUV;
                            break;
                        case 3:
                            type = CarType.VAN;
                            break;
                        case 4:
                            type = CarType.TRUCK;
                            break;
                        default:
                            System.out.println("Invalid car type please try again!");
                            continue;
                    }
                    System.out.println("Enter daily rental rate for the car: ");
                    double dailyRate = scanner.nextDouble();
                    Car car = new Car(plateNo, model, type, dailyRate);
                    if (car.getPlateNo() == null || car.getModel() == null || car.getDailyRentalRate() == 0) {
                        System.out.println("Please provide enough information!");
                        continue;
                    }
                    System.out.println(system.addCar(car));
                    continue;
                }
                case 2: {
                    system.printCars();
                    System.out.println("TAKE CARE DELETING A CAR WILL DELETE ALL RENTALS ASSOCIATED WITH IT!!");
                    System.out.println("Please enter the no of the car you want to delete: enter -1 if you want to return");
                    choice = scanner.nextShort();
                    if (choice == -1) {
                        continue;
                    }
                    if (choice < 1 || choice > system.getCars().size()) {
                        System.out.println("out of pounds number please try again!");
                        continue;
                    }
                    System.out.println(system.deleteCar(system.getCars().get(choice - 1).getPlateNo()));
                    continue;
                }
                case 3: {
                    System.out.println("Please enter the id of the customer to delete his rentals: ");
                    int id = scanner.nextInt();
                    System.out.println(system.deleteCarRental(id));
                    continue;
                }

                case 4: {
                    System.out.println("Please enter the id for the customer you are searching for: ");
                    int id = scanner.nextInt();
                    Customer customer = system.findCustomer(id);
                    if (customer != null) {
                        System.out.println("Here is the customer's information: ");
                        System.out.println(customer);
                    }
                    continue;
                }
                case 5: {
                    System.out.println("Please enter the id of the customer you want to delete: ");
                    System.out.println(system.deleteCustomer(scanner.nextInt()));

                    continue;
                }
                case 6: {
                    System.out.println("Please enter the id for the customer to print his active rentals: ");
                    int id = scanner.nextInt();
                    List<Rental> rentals = system.findCarRentalByCustomerId(id);
                    if (rentals != null) {
                        if (rentals.size() < 1) {
                            System.out.println("No rentals associated with this customer! ");
                            continue;
                        }
                        System.out.printf("%3s|%20s|%15s|%15s|%15s|%10s|%10s|\n",
                                "no", "Customer name", "Customer id", "Car plate no", "Car model", "start date", "end date");
                        for (Rental rental : rentals) {
                            System.out.printf("%3d|%20s|%15d|%15s|%15s|%10s|%10s|\n",
                                    rental.getRentalNo(),
                                    rental.getCustomer().getName(),
                                    rental.getCustomer().getCustomerId(),
                                    rental.getCar().getPlateNo(),
                                    rental.getCar().getModel(),
                                    rental.getStartDate(),
                                    rental.getEndDate());

                            System.out.println("Do you want to add payment to any of these rentals? (yes=1,no=2)");
                            choice = scanner.nextShort();
                            boolean rentalExist = false;
                            if (choice == 2) {
                                continue;
                            }
                            if (choice != 1) {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                            if (rentals.size() == 1) {
                                id = rentals.get(0).getRentalNo();
                            } else {
                                System.out.println("Please enter rental number: ");
                                id = scanner.nextInt();
                                for (Rental rental1: rentals){
                                    if (rental1.getRentalNo() == id) {
                                        rentalExist = true;
                                        break;
                                    }
                                }
                                if (!rentalExist) {
                                    System.out.println("No rental with this no please try again!");
                                    continue;
                                }
                            }
                            System.out.println("Enter payment description: ");
                            scanner.nextLine();
                            String desc = scanner.nextLine();
                            System.out.println("Enter payment price: ");
                            double price = scanner.nextDouble();
                            System.out.println("Enter payment date: ");
                            LocalDate date = takeDate();
                            for (Rental rental1: rentals){
                                if (rental1.getRentalNo() == id){
                                    System.out.println(rental1.getInvoice().addPayment(new Payment(desc, price, date)));

                                }
                            }
                        }
                    }

                    continue;
                }
                case 7:{
                    system.printTotalIncome();
                    continue;
                }

                case 8: {
                    exit = false;
                    break;
                }

            }
            return exit;
        }
    }

}
