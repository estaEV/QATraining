package com.estafet.learning;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.*;

public class CountryPhoneCodes {

    public void ReadFile() {

        String data = null;
        try {
            File myObj = new File("C:\\TrainingPlanProjects\\Sprint_04\\country codes.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        Gson gson = new Gson();
        Map map = gson.fromJson(data, Map.class);

        System.out.println(map);
    }
}
