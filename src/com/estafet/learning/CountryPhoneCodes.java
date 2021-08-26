package com.estafet.learning;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryPhoneCodes {
    public Map<String, String> phoneCodesMap = new HashMap<>();

    public String regexChecker(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        String result = null;

        while(regexMatcher.find()){
            if(regexMatcher.group().length() != 0) {
                result = regexMatcher.group().trim();
                return result;
            }
        }
        return result;
    }

    public void ReadFile() {
        String currentLine = null;
        String regexResult = null;
        String regexResultDigit = null;
        List<String> countries = new ArrayList();;
        List<String> phoneCodes =  new ArrayList();;

        try {
            File myObj = new File("C:\\TrainingPlanProjects\\Sprint_04\\country-codes-noprefix.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                currentLine = myReader.nextLine();
                regexResult = regexChecker("([a-zA-Z]+\\s?)+", currentLine);
                regexResultDigit = regexChecker("\\+?([0-9]\\s?)+", currentLine);
                if(regexResult != null) {
                    countries.add(regexResult);
                }
                else if(regexResultDigit != null) {
                    phoneCodes.add(regexResultDigit);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to open the file.");
            e.printStackTrace();
        }

        for (int i = 0; i < countries.size(); i++) {
                phoneCodesMap.put(countries.get(i), phoneCodes.get(i));
        }
        for (String j : phoneCodesMap.keySet()) {
            System.out.println("key: " + j + " value: " + phoneCodesMap.get(j));
        }
    }
}
