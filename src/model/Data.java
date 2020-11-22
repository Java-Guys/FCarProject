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

/**
 * @author M-Hamdy-M
 * @author Ezeldin Ahmed
 * @author Mahmoud Shreif
 * @author Omar Alkashef
 * Creation Date : 15-10-2020
 * @version 5
 * using Singleton design pattern
 */

public class Data {

    private static Data data = new Data();
    private FCarSystem system;
    private ObservableList<Visitor> visitors;

    /**
     * Private Constructor for Data
     */
    private Data() {
        system = new FCarSystem();
        visitors = FXCollections.observableArrayList();
    }

    /**
     * @return data
     */
    public static Data getInstance() {
        return data;
    }

    /**
     * @return system
     */
    public FCarSystem getSystem(){
        return system;
    }

    /**
     * Loading Cars from Json file to Car List in System
     */
    public void loadCars() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/cars.json"));

            Data.getInstance().getSystem().getCars().setAll(Arrays.asList(gson.fromJson(reader,Car[].class)));
            Data.getInstance().getSystem().printCars();
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load cars");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Loading Customers from Json file to Customer List in System
     */
    public void loadCustomers() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/visitors.json"));
            Data.getInstance().getSystem().getCustomers().setAll(Arrays.asList(gson.fromJson(reader,Visitor[].class)));
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load customers");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loading Rentals from Json file to Rental List in System
     */
    public void loadRentals() {
        System.out.println("Loading rentals");
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("src/store/rentals.json"));
            Data.getInstance().getSystem().getRentals().setAll(Arrays.asList(gson.fromJson(reader,Rental[].class)));
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load rentals");
            e.printStackTrace();
        }

    }


    /**
     * Saving Cars from Car ArrayList in System to Json file
     */
    public void saveCars(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/store/cars.json"));
            gson.toJson(Data.getInstance().getSystem().getCars(), writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("error saving cars!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saving Customers from Customer ArrayList in System to Json file
     */
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

    /**
     * Saving Rentals from Rental ArrayList in System to Json file
     */
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


    /**
     * @return visitors
     */
    public ObservableList<Visitor> getVisitors(){
        reloadVisitors();
        return visitors;
    }

    /**
     * Resync the data in the customer list in the system with the visitors list in the Data class
     */
    public void reloadVisitors(){
        System.out.println("reloading visitor");
        ObservableList<Visitor> visitors = FXCollections.observableArrayList();
        for (Customer customer: system.getCustomers()){
            if (customer instanceof Visitor){
                visitors.add((Visitor)customer);
            }
        }
        this.visitors.setAll(visitors);
    }
}
