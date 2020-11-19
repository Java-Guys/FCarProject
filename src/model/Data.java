package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Data {

    private static Data data = new Data();
    private FCarSystem system;
    private ObservableList<Visitor> visitors;

    private Data() {
        system = new FCarSystem();
        visitors = FXCollections.observableArrayList();
    }

    public static Data getInstance() {
        return data;
    }

    public FCarSystem getSystem(){
        return system;
    }

    public void loadCars() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/cars.json"));
            ObservableList<Car> cars = FXCollections.observableArrayList();
            List<Car> carsList = Arrays.asList(gson.fromJson(reader,Car[].class));
            cars.setAll(carsList);
            Data.getInstance().getSystem().setCars(cars);
            Data.getInstance().getSystem().printCars();
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load cars");
            e.printStackTrace();
        }

    }

    public void loadCustomers() {

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/visitors.json"));
            ObservableList<Customer> visitors = FXCollections.observableArrayList();
            List<Visitor> visitorsList = Arrays.asList(gson.fromJson(reader,Visitor[].class));
            visitors.setAll(visitorsList);
            Data.getInstance().getSystem().setCustomers(visitors);
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load customers");
            e.printStackTrace();
        }
    }

    public void saveCars(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/store/cars.json"));
            gson.toJson(Data.getInstance().getSystem().getCars(), writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
    public void saveVisitors(){

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/store/visitors.json"));
            gson.toJson(getVisitors(), writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void saveRentals(){
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

    public void loadRentals() {
        System.out.println("Loading rentals");
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/rentals.json"));
            ObservableList<Rental> rentals = FXCollections.observableArrayList();
            List<Rental> rentalList = Arrays.asList(gson.fromJson(reader,Rental[].class));
            rentals.setAll(rentalList);
            Data.getInstance().getSystem().setRentals(rentals);
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load rentals");
            e.printStackTrace();
        }

    }

    public ObservableList<Visitor> getVisitors(){
        reloadVisitors();
        return visitors;
    }

    public void reloadVisitors(){
        System.out.println("reloading visitor");
        ObservableList<Visitor> visitors = FXCollections.observableArrayList();
        for (Customer customer: system.getCustomers()){
            if (customer instanceof Visitor){
                visitors.add((Visitor)customer);
            }
        }
        System.out.println("setting all");
        this.visitors.setAll(visitors);
    }
}
