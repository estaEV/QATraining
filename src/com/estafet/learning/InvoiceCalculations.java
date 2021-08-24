package com.estafet.learning;

import java.util.List;
import java.util.Random;

interface InvoiceCalculations {
    Random rand = new Random();
    // calculate all the invoice article prices
    void generateRandomTradeInvoiceData();
    void calculateInvoiceWithVAT();
    void cutThemSomeSlack(double additionalDiscountPercent) throws DiscountNotApplicableException, ShippingNotSupported;

    default double additionalDiscount() {
        double randAdditionalDiscountPercent = (rand.nextInt(4- 1) + 1)*10;
        //randAdditionalDiscountPercent = 40;
        System.out.println("\nAdditional discount percent from interface InvoiceCalculations is: " + randAdditionalDiscountPercent);
        return randAdditionalDiscountPercent;
    }
    void printObjectProperties();
}
