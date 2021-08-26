package com.estafet.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DiscountNotApplicableException {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

/*        --------------------- TradeInvoice objects ---------------------*/

        Invoice objectTradeInvoice = new TradeInvoice();
        Invoice objectTradeInvoice2 = new TradeInvoice();
        TradeInvoiceCalculations objectTradeInvoiceCalculations = new TradeInvoice();


        List<Invoice> tradeInvoiceObjectsList = new ArrayList<>();
        tradeInvoiceObjectsList.add(objectTradeInvoice);
        tradeInvoiceObjectsList.add(objectTradeInvoice2);

        objectTradeInvoice.ExecuteActions();
        objectTradeInvoice2.ExecuteActions();

        objectTradeInvoiceCalculations.ExecuteActions();

        /*--------------------- Order objects ---------------------*/

        List<Order> orderObjectsList = new ArrayList<>();
        Order objectOrder = new Order();
        Order objectOrder2 = new Order();

        orderObjectsList.add(objectOrder);
        orderObjectsList.add(objectOrder2);

        for (int i = 0; i < orderObjectsList.size(); i++) {
            orderObjectsList.get(i).ExecuteActionsOnOrder();
        }

        CountryPhoneCodes objectCountryPhoneCodes = new CountryPhoneCodes();
        objectCountryPhoneCodes.ReadFile();


/*        ----------------------- FIND INVOICE AND ORDER BY ID -----------------------         */


        System.out.println("Enter Invoice and Order number: ");
        String searchedInvoiceNumber = sc.nextLine();
        String searchedOrderNumber = sc.nextLine();

        Invoice foundInvoiceObj = findInvoiceByID(tradeInvoiceObjectsList, searchedInvoiceNumber);
        foundInvoiceObj.printObjectProperties();

        Order foundOrderObj = findOrderByID(orderObjectsList, searchedOrderNumber);
        foundOrderObj.printObjectProperties();


/*        ----------------------- COUNTRY PHONE CODES MAP -----------------------         */

    }

    public static Invoice findInvoiceByID(List<Invoice> tradeInvoiceObjectsList, String searchedInvoiceNumber) {
        for (Invoice el : tradeInvoiceObjectsList) {
            if (searchedInvoiceNumber.equals(String.valueOf(el.getInvoiceNumber()))) {
                System.out.println("Invoice Number Exists: " + searchedInvoiceNumber);
                return el;
            }
        }
        System.out.println("INVOICE DOES NOT EXISTS!");
        return null;
    }

    public static Order findOrderByID(List<Order> orderObjectsList, String searchedOrderNumber) {
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
