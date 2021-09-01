package com.estafet.learning.spint5;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String msg) {
        super(msg);
        System.out.println("Currently we are inside OrderNotFoundException().");
    }
}
