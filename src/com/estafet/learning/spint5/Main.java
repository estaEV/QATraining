package com.estafet.learning.spint5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DiscountNotApplicableException {
        Scanner sc = new Scanner(System.in);
        List<String> menu = new ArrayList<>();
        menu.add("\n0. Exit.");
        menu.add("1. Generate random TradeInvoice objects and save them to a file.");
        menu.add("2. Generate random Order objects and save them to a file.");
        menu.add("3. Search Invoice by ID.");
        menu.add("4. Search Order by ID.");
        menu.add("5. Save phone codes from json to hashmap.");
        menu.add("6. Save Germany zip codes from json to hashmap.");

        boolean isRunning = true;

        List<Invoice> tradeInvoiceObjectsList = null;
        List<Order> orderObjectsList = null;
        while (isRunning) {
            menu.forEach(option -> System.out.println(option));
            System.out.print("\nEnter the selected func(): ");
            int option = sc.nextInt();
            switch (option) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    Invoice objectTradeInvoice = new TradeInvoice();
                    Invoice objectTradeInvoice2 = new TradeInvoice();
                    TradeInvoiceCalculations objectTradeInvoiceCalculations = new TradeInvoice();

                    tradeInvoiceObjectsList = new ArrayList<>();
                    tradeInvoiceObjectsList.add(objectTradeInvoice);
                    tradeInvoiceObjectsList.add(objectTradeInvoice2);

                    for (int i = 0; i < tradeInvoiceObjectsList.size(); i++) {
                        try {
                            tradeInvoiceObjectsList.get(i).ExecuteActions();
                        } catch (IOException e) {
                            System.out.println("Finally the retrown exception from ExeciteActions() TradeInvoice is catched in the main()");
                            e.printStackTrace();
                        }
                    }
                    //objectTradeInvoiceCalculations.ExecuteActions();
                    break;
                case 2:
                    orderObjectsList = new ArrayList<>();
                    Order objectOrder = new Order();
                    Order objectOrder2 = new Order();

                    orderObjectsList.add(objectOrder);
                    orderObjectsList.add(objectOrder2);

                    for (int i = 0; i < orderObjectsList.size(); i++) {
                        orderObjectsList.get(i).ExecuteActionsOnOrder();
                    }
                    break;
                case 3:
                    Invoice foundInvoiceObj = findInvoiceByID(tradeInvoiceObjectsList);
                    foundInvoiceObj.printObjectProperties();
                    break;
                case 4:
                    Order foundOrderObj = findOrderByID(orderObjectsList);
                    foundOrderObj.printObjectProperties();
                    break;
                case 5:
                    CountryPhoneCodes objectCountryPhoneCodes = new CountryPhoneCodes();
                    objectCountryPhoneCodes.generateHashMap();
                    break;
                case 6:
                    CountryPostalCodes objectCountryPostalCodes = new CountryPostalCodes();
                    objectCountryPostalCodes.generateHashMap();
                    break;
            }
        }
    }

    /**
     * Finds an invoice from the provided list using the input invoice number. If found - it prints the object properties.
     * @param tradeInvoiceObjectsList
     * @return
     */
    private static Invoice findInvoiceByID(List<Invoice> tradeInvoiceObjectsList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Invoice number: ");
        String searchedInvoiceNumber = sc.nextLine();
        for (Invoice el : tradeInvoiceObjectsList) {
            if (searchedInvoiceNumber.equals(String.valueOf(el.getInvoiceNumber()))) {
                System.out.println("Invoice Number Exists: " + searchedInvoiceNumber);
                return el;
            }
        }
        System.out.println("INVOICE DOES NOT EXISTS!");
        return null;
    }

    /**
     * Finds an order from the provided list using the input order number. If found - it prints the object properties.
     * @param orderObjectsList
     * @return
     */
    private static Order findOrderByID(List<Order> orderObjectsList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Order number: ");
        String searchedOrderNumber = sc.nextLine();
        for (Order el : orderObjectsList) {
            if (searchedOrderNumber.equals(String.valueOf(el.getOrderNumber()))) {
                System.out.println("Order Number Exists: " + searchedOrderNumber);
                return el;
            }
        }
        System.out.println("ORDER DOES NOT EXISTS!");
        return null;
    }
}
