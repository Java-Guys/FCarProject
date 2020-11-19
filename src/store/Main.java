package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FCarSystem system = new FCarSystem();
        Customer customer = new Visitor(123456789,
                "Mohamed Hamdy",
                "11111111",
                "south khalifa",
                "Egyptian",
                "driving licence",
                18290346,
                LocalDate.of(2020, 7, 20),
                LocalDate.of(2022, 7, 20));

        Customer customer1 = new Visitor(516249875,
                "Omar Elkashef",
                "22222222",
                "duhail",
                "Egyptian",
                "driving licence",
                18290346,
                LocalDate.of(2020, 7, 20),
                LocalDate.of(2022, 7, 20));

        Customer customer2 = new Visitor(957168429,
                "Mahmoud Talkhan",
                "55164672",
                "south khalifa",
                "Egyptian",
                "driving licence",
                18290346,
                LocalDate.of(2020, 7, 20),
                LocalDate.of(2022, 7, 20));

        Customer customer3 = new Visitor(961486329,
                "Ezeldin Ahmed",
                "33333333",
                "doha",
                "Egyptian",
                "driving licence",
                18290346,
                LocalDate.of(2020, 7, 20),
                LocalDate.of(2022, 7, 20));

        Customer customer4 = new Visitor(356876876,
                "Ali Hemeda",
                "44444444",
                "doha",
                "Hindi",
                "driving licence",
                18290346,
                LocalDate.of(2020, 7, 20),
                LocalDate.of(2022, 7, 20));


        System.out.println(system.addCustomer(customer));
        System.out.println(system.addCustomer(customer1));
        System.out.println(system.addCustomer(customer2));
        System.out.println(system.addCustomer(customer3));
        System.out.println(system.addCustomer(customer4));
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


        Rental rental = new Rental(customer, new Car(
                "3256786",
                "Ferrari spider",
                CarType.SEDAN,
                2000),
                LocalDate.of(2020, 10, 15),
                LocalDate.of(2020, 10, 30),
                2000,
                new Invoice(LocalDate.of(2020, 10, 13), new ArrayList<Payment>())
        );
        Rental rental1 = new Rental(customer2, new Car(
                "7755098",
                "Honda city",
                CarType.SEDAN,
                2000000),
                LocalDate.of(2020, 10, 1),
                LocalDate.of(2020, 11, 1),
                3000,
                new Invoice(LocalDate.of(2020, 10, 13), new ArrayList<Payment>())
        );
        Rental rental2 = new Rental(customer3, new Car(
                "216894",
                "kia-sportage",
                CarType.SUV,
                150),
                LocalDate.of(2020, 10, 1),
                LocalDate.of(2020, 10, 18),
                2500,
                new Invoice(LocalDate.of(2020, 10, 13), new ArrayList<Payment>())
        );

        system.bookCarRental(rental);
        system.bookCarRental(rental1);
        system.bookCarRental(rental2);


        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/store/rentals.json"));
            gson.toJson(system.getRentals(), writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

}
