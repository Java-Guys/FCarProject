package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.CarType;
import model.Customer;
import model.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/store/visitors.json"));

            gson.toJson(Data.getInstance().getSystem().getCustomers(), writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
