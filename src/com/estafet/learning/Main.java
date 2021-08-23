package com.estafet.learning;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.estafet.learning.Order.*;
import static com.estafet.learning.TradeInvoice.*;

public class Main {

    public static void main(String[] args) throws DiscountNotApplicableException {
        Invoice objectTradeInvoice = new TradeInvoice();
        Invoice objectTradeInvoice2 = new TradeInvoice();
        TradeInvoiceCalculations objectTradeInvoiceCalculations = new TradeInvoice();

        List<Invoice> tradeInvoiceObjects = new ArrayList<>();

        tradeInvoiceObjects.add(objectTradeInvoice);
        tradeInvoiceObjects.add(objectTradeInvoice2);

        objectTradeInvoice.ExecuteActions();
        objectTradeInvoice2.ExecuteActions();

        objectTradeInvoiceCalculations.ExecuteActions();

        System.out.println();

//        System.out.println("Are these objects (objectTradeOrder == objectTradeOrder2) equal: " + objectTradeOrder.equals(objectTradeOrder2));
//        System.out.printf("obj 1 and obj 2 hashes: \n%s\n%s\n", objectTradeOrder.hashCode(), objectTradeOrder2.hashCode());

//        System.out.println("Are these objects (objectTradeInvoice == objectTradeInvoice2) equal: " + objectTradeInvoice.equals(objectTradeInvoice2));
//        System.out.printf("obj 1 and obj 2 hashes: \n%s\n%s\n", objectTradeInvoice.hashCode(), objectTradeInvoice2.hashCode());


    }


}
