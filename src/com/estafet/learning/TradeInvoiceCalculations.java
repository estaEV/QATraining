package com.estafet.learning;

interface TradeInvoiceCalculations extends InvoiceCalculations {
    @Override
    default double additionalDiscount() {
        int randAdditionalDiscountPercent = (rand.nextInt(4 - 1) + 1)*10;
        //randAdditionalDiscountPercent = 50;
        System.out.println("\nAdditional discount percent from interface TradeInvoiceCalculations is: " + randAdditionalDiscountPercent);
        return randAdditionalDiscountPercent;
    }
    void ExecuteActions();
    //annotation
}
