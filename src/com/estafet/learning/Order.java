package com.estafet.learning;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static com.estafet.learning.InvoiceCalculations.rand;
import static com.estafet.learning.TradeInvoice.*;

public class Order implements OrderCalculations{

    private int orderNumber;
    private String clientDetails;
    private Map<String, Double> items;
    private String[] originCountry;
    private String[] destinationCountry;
    private double orderTotalAmountBeforeVAT;
    private double orderDiscountedAmount;
    private double orderTotalAmountBeforeVATWithDiscount;
    private double orderTotalAmountAfterVAT;
    private double orderInvoiceNumber;
    public Map<String, Double> itemsToPass = new LinkedHashMap<String, Double>();

    public String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};


    private static final int MAX_NUMBER_OF_ORDERS_PER_CLIENT;

    static {
        MAX_NUMBER_OF_ORDERS_PER_CLIENT = 10000;
    }

    public Order() {
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(String clientDetails) {
        this.clientDetails = clientDetails;
    }

    public Map<String, Double> getItems() {
        return items;
    }

    public void setItems(Map<String, Double> items) {
        this.items = items;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }

    public String[] getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String[] destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public double getOrderTotalAmountBeforeVATWithDiscount() {
        return orderTotalAmountBeforeVATWithDiscount;
    }

    public void setOrderTotalAmountBeforeVATWithDiscount(double orderTotalAmountBeforeVATWithDiscount) {
        this.orderTotalAmountBeforeVATWithDiscount = orderTotalAmountBeforeVATWithDiscount;
    }

    public double getOrderDiscountedAmount() {
        return orderDiscountedAmount;
    }

    public void setOrderDiscountedAmount(double orderDiscountedAmount) {
        this.orderDiscountedAmount = orderDiscountedAmount;
    }

    public double getOrderTotalAmountBeforeVAT() {
        return orderTotalAmountBeforeVAT;
    }

    public void setOrderTotalAmountBeforeVAT(double orderTotalAmountBeforeVAT) {
        this.orderTotalAmountBeforeVAT = orderTotalAmountBeforeVAT;
    }

    public double getOrderTotalAmountAfterVAT() {
        return orderTotalAmountAfterVAT;
    }

    public void setOrderTotalAmountAfterVAT(double orderTotalAmountAfterVAT) {
        this.orderTotalAmountAfterVAT = orderTotalAmountAfterVAT;
    }

    public double getOrderInvoiceNumber() {
        return orderInvoiceNumber;
    }

    public void setOrderInvoiceNumber(double orderInvoiceNumber) {
        this.orderInvoiceNumber = orderInvoiceNumber;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }


    public void generateRandomOrderData()
    {
        double passTotalAmountBeforeVAT = 0;

        int orderNumber = rand.nextInt(27000 - 19000) + 19000;
        setOrderNumber(orderNumber);
        setClientDetails(UUID.randomUUID().toString());

        // not preciese, because if we have overlapping in the rand number generator we are gonna have 1 item less in the basket.
        int numberOfItemsIntoTheBasket = rand.nextInt(8 - 3) + 3;

        ArrayList<Integer> listOfItems = new ArrayList<>();
        for (int i = 0; i <= numberOfItemsIntoTheBasket; i++)
        {
            int randItemFromTheList = rand.nextInt(8 - 1) + 1;
            if (!(listOfItems.contains(randItemFromTheList)))
            {
                listOfItems.add(randItemFromTheList);
                Object currentItem = articlesDefaultMap.keySet().toArray()[randItemFromTheList];
                double currentItemPrice = articlesDefaultMap.get((articlesDefaultMap.keySet().toArray())[randItemFromTheList]);
                itemsToPass.put((String) currentItem, currentItemPrice);
                //System.out.println(currentItem + " " + currentItemPrice);
                passTotalAmountBeforeVAT += currentItemPrice;
            }
            else
            {
                --numberOfItemsIntoTheBasket;
            }
        }

        setOrderTotalAmountBeforeVAT(passTotalAmountBeforeVAT);
        setItems(itemsToPass);

/*        System.out.println("totalAmountToPass: " + passTotalAmountBeforeVAT);
        for (String i : articlesToPass.keySet()) {
            System.out.println("key: " + i + " value: " + articlesToPass.get(i));
        }*/
    }

    public void cutThemSomeSlackOrder(double additionalDiscountPercent) throws DiscountNotApplicableException, ShippingNotSupported {
        //System.out.printf("Additional discount percent is: %s percents\n\n", additionalDiscountPercent);

        double passDiscountedAmount = 0;
        double passTotalAmountBeforeVATWithDiscount = getOrderTotalAmountBeforeVAT();

/*        if (passTotalAmountBeforeVATWithDiscount < 500 ) {
            throw new DiscountNotApplicableException("Total amount not reached for the particular discount. Minimum amount is 1000. Current amount: " + passTotalAmountBeforeVATWithDiscount);
        }

        if (passTotalAmountBeforeVATWithDiscount < 1500 ) {
            throw new ShippingNotSupported("Shipping is not supported for the following amount: " + passTotalAmountBeforeVATWithDiscount);
        }*/

        double randAdditionalDiscountPercent = additionalDiscountPercent;
        int randDiscountPercent = (rand.nextInt(5 - 1) + 1)*10;

        randDiscountPercent += randAdditionalDiscountPercent;

        passDiscountedAmount = (passTotalAmountBeforeVATWithDiscount*randDiscountPercent)/100;

        passTotalAmountBeforeVATWithDiscount = (passTotalAmountBeforeVATWithDiscount - ((passTotalAmountBeforeVATWithDiscount*randDiscountPercent)/100));
        setOrderDiscountedAmount(passDiscountedAmount);
        setOrderTotalAmountBeforeVATWithDiscount(passTotalAmountBeforeVATWithDiscount);
    }


    public void calculateOrderAmountWithVAT() {
        double passTotalAmountAfterVAT = getOrderTotalAmountBeforeVATWithDiscount();
        passTotalAmountAfterVAT += (passTotalAmountAfterVAT*20)/100;
        setOrderTotalAmountAfterVAT(passTotalAmountAfterVAT);
    }

    public void saveOrderObjectToFile() throws IOException {
        // BufferedWriter bw = null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\TrainingPlanProjects\\Sprint_04\\OrderPropertiesOutput.txt"))) {

            bw.write("\n" + String.valueOf(this.itemsToPass) + ", ");
            bw.write("\n" + this.getOrderNumber() + ", ");
            bw.write("\n" + this.getClientDetails() + ", ");
            bw.write("\n" + this.getOrderTotalAmountBeforeVAT() + ", ");
            bw.write("\n" + this.getOrderTotalAmountBeforeVATWithDiscount() + ", ");
            bw.write("\n" + this.getOrderDiscountedAmount() + ", ");
            bw.write("\n" + this.getOrderDiscountedAmount() + ", ");
            bw.write("\n" + this.getOrderTotalAmountAfterVAT() + ", ");
        } catch(Exception e) {
            System.out.println("Directory or file not found.");
            e.printStackTrace();
        }
        finally {
            System.out.println("Finally still exist in try with resources block.");
/*            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();*/
        }
    }

    public static void printObjectPropertiesStatic(List<Order> ordersObjects) {
        for (Order item : ordersObjects) {
            System.out.println("Item: " + item.toString());
        }
    }

    public void printObjectProperties() {
            System.out.println("Item: " + this.toString());
    }


    // Entry point
    public void ExecuteActionsOnOrder ()
    {
        generateRandomOrderData();
        try {
            cutThemSomeSlackOrder(additionalDiscount());
        } catch (DiscountNotApplicableException | ShippingNotSupported e) {
            e.printStackTrace();
        }
        calculateOrderAmountWithVAT();
        printObjectProperties();
        try {
            saveOrderObjectToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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
        Order ordersObj = (Order) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        boolean areAllFieldsEqual = ordersObj.orderNumber == this.orderNumber && ordersObj.clientDetails == this.clientDetails && ordersObj.items == this.items && ordersObj.orderTotalAmountAfterVAT == this.orderTotalAmountAfterVAT;
        return areAllFieldsEqual;
    }


    @Override
    public int hashCode()
    {
        int  newHashCodeNum = (this.orderNumber * this.clientDetails.hashCode() * (int) this.orderTotalAmountAfterVAT);
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
        Field[] fields = this.getClass().getDeclaredFields();

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


