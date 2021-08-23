package com.estafet.learning;

import java.lang.reflect.Field;
import java.util.List;

abstract public class Orders {

    protected int orderNumber;
    protected String clientDetails;
    protected List<String> articles;
    protected double totalAmount;
    protected static final int MAX_NUMBER_OF_ORDERS_PER_CLIENT;

    static {
        MAX_NUMBER_OF_ORDERS_PER_CLIENT = 10000;
    }


    Orders() {
        System.out.println("I am an Orders() constructor.");
    }


    Orders(int orderNumber, double totalAmount) {
        System.out.println("I am an Orders(int orderNumber, double totalAmount) constructor.");
    }


    Orders(String clientDetails, List<String> articles) {
        this.clientDetails = clientDetails;
        this.articles = articles;
        System.out.println("I am an Orders(String[] clientDetails, List<String> articles) constructor.");
    }


    // calling the following constructor: Orders(String[] clientDetails, List<String> articles)
    Orders(int orderNumber, String clientDetails, List<String> articles, double totalAmount) {
        this.orderNumber = orderNumber;
        this.clientDetails = clientDetails;
        this.articles = articles;
        this.totalAmount = totalAmount;
        System.out.println("I am an Orders(int orderNumber, String[] clientDetails, List<String> articles, double totalAmount) constructor.");
    }

    public static void printObjectPropertiesStatic(List<Orders> ordersObjects) {
        for (Orders item : ordersObjects) {
            System.out.println("Item: " + item.toString());
        }
    }

/*    public void printObjectProperties() {
            System.out.println("Item: " + this.toString());
    }*/


    @Override
    public boolean equals(Object obj)
    {
        // checking if both the object references are
        // referring to the same object.
        if (this == obj) {
            System.out.println("Object and passed arg references are referring to the same object");
            return true;
        }
        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // type casting of the argument.
        Orders ordersObj = (Orders) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        boolean areAllFieldsEqual = ordersObj.orderNumber == this.orderNumber && ordersObj.clientDetails == this.clientDetails && ordersObj.articles == this.articles && ordersObj.totalAmount == this.totalAmount;
        System.out.printf("\nOverwritten Order's equals() here: %s\n", areAllFieldsEqual);
        return areAllFieldsEqual;
    }


    @Override
    public int hashCode()
    {
        int  newHashCodeNum = (this.orderNumber * this.clientDetails.hashCode() * (int) this.totalAmount);
        System.out.printf("Overwritten Order's equals() here: %s\n", newHashCodeNum);
        return newHashCodeNum;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }


}


