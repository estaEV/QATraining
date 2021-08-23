package com.estafet.learning;

public class MyGenericList {
    private Invoice[] invoices = new Invoice[10];
    private int nextIndex = 0;

    public void add(Invoice inv) {
        if (nextIndex < invoices.length) {
            invoices[nextIndex] = inv;
            System.out.println("Object added at: " + nextIndex);
            nextIndex++;
        }
    }

}
