package com.estafet.learning.spint4;

public class TradeInvoiceNotFoundException extends Exception{
    public TradeInvoiceNotFoundException (String msg) {
        super(msg);
        System.out.println("Currently we are inside TradeInvoiceNotFoundException().");
    }
}
